package de.erichseifert.vectorgraphics2d.pdf;

import de.erichseifert.vectorgraphics2d.util.DataUtils;
import de.erichseifert.vectorgraphics2d.util.GraphicsUtils;
import java.awt.Font;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/pdf/d.class */
final class d extends a {
    private static final String[] d = {"PDF", "Text", "ImageB", "ImageC", "ImageI"};
    private final List<String> e;
    private final Map<String, e> f;
    private final Map<Font, String> g;
    private final Map<PDFObject, String> h;
    private final Map<Double, String> i;
    private final AtomicInteger j;
    private final AtomicInteger k;
    private final AtomicInteger l;

    public d() {
        super(null, null, false);
        this.j = new AtomicInteger();
        this.k = new AtomicInteger();
        this.l = new AtomicInteger();
        this.e = new LinkedList();
        this.f = new HashMap();
        this.g = new HashMap();
        this.h = new HashMap();
        this.i = new HashMap();
        String[] strArr = d;
        this.e.clear();
        this.e.addAll(Arrays.asList(strArr));
    }

    private static <T> String a(Map<T, String> map, T t, String str, AtomicInteger atomicInteger) {
        String str2 = map.get(t);
        String str3 = str2;
        if (str2 == null) {
            str3 = String.format("%s%d", str, Integer.valueOf(atomicInteger.getAndIncrement()));
            map.put(t, str3);
        }
        return str3;
    }

    public final String a(Font font) {
        Font physicalFont = GraphicsUtils.getPhysicalFont(font);
        String a = a(this.g, physicalFont, "Fnt", this.j);
        this.f.put(a, new e("WinAnsiEncoding", physicalFont.getPSName()));
        return a;
    }

    public final String a(PDFObject pDFObject) {
        Map map = (Map) this.a.get("XObject");
        Map map2 = map;
        if (map == null) {
            map2 = new LinkedHashMap();
            this.a.put("XObject", map2);
        }
        String a = a(this.h, pDFObject, "Img", this.k);
        map2.put(a, pDFObject);
        return a;
    }

    public final String a(Double d2) {
        Map map = (Map) this.a.get("ExtGState");
        Map map2 = map;
        if (map == null) {
            map2 = new LinkedHashMap();
            this.a.put("ExtGState", map2);
        }
        String a = a(this.i, d2, "Trp", this.l);
        map2.put(a, DataUtils.map(new String[]{"Type", "ca", "CA"}, new Object[]{"ExtGState", d2, d2}));
        return a;
    }

    public final List<String> a() {
        return Collections.unmodifiableList(this.e);
    }

    public final Map<String, e> b() {
        return Collections.unmodifiableMap(this.f);
    }
}
