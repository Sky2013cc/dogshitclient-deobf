package com.sun.xml.internal.rngom.util;

import com.sun.tools.javac.util.Position;

/* loaded from: target.jar:com/sun/xml/internal/rngom/util/Utf16.class */
public abstract class Utf16 {
    public static boolean isSurrogate(char c) {
        return (c & 63488) == 55296;
    }

    public static boolean isSurrogate1(char c) {
        return (c & 64512) == 55296;
    }

    public static boolean isSurrogate2(char c) {
        return (c & 64512) == 56320;
    }

    public static int scalarValue(char c1, char c2) {
        return (((c1 & 1023) << 10) | (c2 & 1023)) + 65536;
    }

    public static char surrogate1(int c) {
        return (char) (((c - 65536) >> 10) | 55296);
    }

    public static char surrogate2(int c) {
        return (char) (((c - 65536) & Position.MAXCOLUMN) | 56320);
    }
}
