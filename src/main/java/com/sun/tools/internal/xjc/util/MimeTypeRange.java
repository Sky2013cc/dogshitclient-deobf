package com.sun.tools.internal.xjc.util;

import com.sun.tools.doclint.DocLint;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/internal/xjc/util/MimeTypeRange.class */
public class MimeTypeRange {
    public final String majorType;
    public final String subType;
    public final Map<String, String> parameters;
    public final float q;
    public static final MimeTypeRange ALL = create("*/*");

    public static List<MimeTypeRange> parseRanges(String s) throws ParseException {
        StringCutter cutter = new StringCutter(s, true);
        List<MimeTypeRange> r = new ArrayList<>();
        while (cutter.length() > 0) {
            r.add(new MimeTypeRange(cutter));
        }
        return r;
    }

    public MimeTypeRange(String s) throws ParseException {
        this(new StringCutter(s, true));
    }

    private static MimeTypeRange create(String s) {
        try {
            return new MimeTypeRange(s);
        } catch (ParseException e) {
            throw new Error(e);
        }
    }

    private MimeTypeRange(StringCutter cutter) throws ParseException {
        String value;
        this.parameters = new HashMap();
        this.majorType = cutter.until(RuntimeConstants.SIG_PACKAGE);
        cutter.next(RuntimeConstants.SIG_PACKAGE);
        this.subType = cutter.until("[;,]");
        float q = 1.0f;
        while (cutter.length() > 0) {
            String sep = cutter.next("[;,]");
            if (sep.equals(DocLint.TAGS_SEPARATOR)) {
                break;
            }
            String key = cutter.until("=");
            cutter.next("=");
            char ch = cutter.peek();
            if (ch == '\"') {
                cutter.next(OperatorName.SHOW_TEXT_LINE_AND_SPACE);
                value = cutter.until(OperatorName.SHOW_TEXT_LINE_AND_SPACE);
                cutter.next(OperatorName.SHOW_TEXT_LINE_AND_SPACE);
            } else {
                value = cutter.until("[;,]");
            }
            if (key.equals(OperatorName.SAVE)) {
                q = Float.parseFloat(value);
            } else {
                this.parameters.put(key, value);
            }
        }
        this.q = q;
    }

    public MimeType toMimeType() throws MimeTypeParseException {
        return new MimeType(toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.majorType + '/' + this.subType);
        if (this.q != 1.0f) {
            sb.append("; q=").append(this.q);
        }
        for (Map.Entry<String, String> p : this.parameters.entrySet()) {
            sb.append("; ").append(p.getKey()).append('=').append(p.getValue());
        }
        return sb.toString();
    }

    public static MimeTypeRange merge(Collection<MimeTypeRange> types) {
        if (types.size() == 0) {
            throw new IllegalArgumentException();
        }
        if (types.size() == 1) {
            return types.iterator().next();
        }
        String majorType = null;
        for (MimeTypeRange mt : types) {
            if (majorType == null) {
                majorType = mt.majorType;
            }
            if (!majorType.equals(mt.majorType)) {
                return ALL;
            }
        }
        return create(majorType + "/*");
    }

    public static void main(String[] args) throws ParseException {
        for (MimeTypeRange m : parseRanges(args[0])) {
            System.out.println(m.toString());
        }
    }
}
