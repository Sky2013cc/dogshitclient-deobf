package com.sun.xml.internal.rngom.xml.util;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import java.io.UnsupportedEncodingException;

/* loaded from: target.jar:com/sun/xml/internal/rngom/xml/util/EncodingMap.class */
public abstract class EncodingMap {
    private static final String[] aliases = {"UTF-8", "UTF8", "UTF-16", "Unicode", "UTF-16BE", "UnicodeBigUnmarked", "UTF-16LE", "UnicodeLittleUnmarked", "US-ASCII", "ASCII", "TIS-620", "TIS620"};

    public static String getJavaName(String enc) {
        try {
            SimpleTaglet.EXCLUDED.getBytes(enc);
        } catch (UnsupportedEncodingException e) {
            for (int i = 0; i < aliases.length; i += 2) {
                if (enc.equalsIgnoreCase(aliases[i])) {
                    try {
                        SimpleTaglet.EXCLUDED.getBytes(aliases[i + 1]);
                        return aliases[i + 1];
                    } catch (UnsupportedEncodingException e2) {
                    }
                }
            }
        }
        return enc;
    }

    public static void main(String[] args) {
        System.err.println(getJavaName(args[0]));
    }
}
