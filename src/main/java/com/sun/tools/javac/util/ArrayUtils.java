package com.sun.tools.javac.util;

import java.lang.reflect.Array;

/* loaded from: target.jar:com/sun/tools/javac/util/ArrayUtils.class */
public class ArrayUtils {
    private static int calculateNewLength(int i, int i2) {
        while (i < i2 + 1) {
            i *= 2;
        }
        return i;
    }

    public static <T> T[] ensureCapacity(T[] tArr, int i) {
        if (i < tArr.length) {
            return tArr;
        }
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), calculateNewLength(tArr.length, i)));
        System.arraycopy(tArr, 0, tArr2, 0, tArr.length);
        return tArr2;
    }

    public static byte[] ensureCapacity(byte[] bArr, int i) {
        if (i < bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[calculateNewLength(bArr.length, i)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static char[] ensureCapacity(char[] cArr, int i) {
        if (i < cArr.length) {
            return cArr;
        }
        char[] cArr2 = new char[calculateNewLength(cArr.length, i)];
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
        return cArr2;
    }

    public static int[] ensureCapacity(int[] iArr, int i) {
        if (i < iArr.length) {
            return iArr;
        }
        int[] iArr2 = new int[calculateNewLength(iArr.length, i)];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }
}
