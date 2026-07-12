package com.sun.tools.javac.util;

import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: target.jar:com/sun/tools/javac/util/Convert.class */
public class Convert {
    public static int string2int(String str, int i) throws NumberFormatException {
        if (i == 10) {
            return Integer.parseInt(str, i);
        }
        int i2 = Integer.MAX_VALUE / (i / 2);
        int i3 = 0;
        for (char c : str.toCharArray()) {
            int digit = Character.digit(c, i);
            if (i3 < 0 || i3 > i2 || i3 * i > Integer.MAX_VALUE - digit) {
                throw new NumberFormatException();
            }
            i3 = (i3 * i) + digit;
        }
        return i3;
    }

    public static long string2long(String str, int i) throws NumberFormatException {
        if (i == 10) {
            return Long.parseLong(str, i);
        }
        char[] charArray = str.toCharArray();
        long j = LongCompanionObject.MAX_VALUE / (i / 2);
        long j2 = 0;
        for (char c : charArray) {
            int digit = Character.digit(c, i);
            if (j2 < 0 || j2 > j || j2 * i > LongCompanionObject.MAX_VALUE - digit) {
                throw new NumberFormatException();
            }
            j2 = (j2 * i) + digit;
        }
        return j2;
    }

    public static int utf2chars(byte[] bArr, int i, char[] cArr, int i2, int i3) {
        int i4 = i;
        int i5 = i2;
        int i6 = i + i3;
        while (i4 < i6) {
            int i7 = i4;
            i4++;
            int i8 = bArr[i7] & 255;
            if (i8 >= 224) {
                int i9 = i4 + 1;
                int i10 = ((i8 & 15) << 12) | ((bArr[i4] & 63) << 6);
                i4 = i9 + 1;
                i8 = i10 | (bArr[i9] & 63);
            } else if (i8 >= 192) {
                i4++;
                i8 = ((i8 & 31) << 6) | (bArr[i4] & 63);
            }
            int i11 = i5;
            i5++;
            cArr[i11] = (char) i8;
        }
        return i5;
    }

    public static char[] utf2chars(byte[] bArr, int i, int i2) {
        char[] cArr = new char[i2];
        int utf2chars = utf2chars(bArr, i, cArr, 0, i2);
        char[] cArr2 = new char[utf2chars];
        System.arraycopy(cArr, 0, cArr2, 0, utf2chars);
        return cArr2;
    }

    public static char[] utf2chars(byte[] bArr) {
        return utf2chars(bArr, 0, bArr.length);
    }

    public static String utf2string(byte[] bArr, int i, int i2) {
        char[] cArr = new char[i2];
        return new String(cArr, 0, utf2chars(bArr, i, cArr, 0, i2));
    }

    public static String utf2string(byte[] bArr) {
        return utf2string(bArr, 0, bArr.length);
    }

    public static int chars2utf(char[] cArr, int i, byte[] bArr, int i2, int i3) {
        int i4 = i2;
        int i5 = i + i3;
        for (int i6 = i; i6 < i5; i6++) {
            char c = cArr[i6];
            if (1 <= c && c <= 127) {
                int i7 = i4;
                i4++;
                bArr[i7] = (byte) c;
            } else if (c <= 2047) {
                int i8 = i4;
                int i9 = i4 + 1;
                bArr[i8] = (byte) (192 | (c >> 6));
                i4 = i9 + 1;
                bArr[i9] = (byte) (128 | (c & '?'));
            } else {
                int i10 = i4;
                int i11 = i4 + 1;
                bArr[i10] = (byte) (224 | (c >> '\f'));
                int i12 = i11 + 1;
                bArr[i11] = (byte) (128 | ((c >> 6) & 63));
                i4 = i12 + 1;
                bArr[i12] = (byte) (128 | (c & '?'));
            }
        }
        return i4;
    }

    public static byte[] chars2utf(char[] cArr, int i, int i2) {
        byte[] bArr = new byte[i2 * 3];
        int chars2utf = chars2utf(cArr, i, bArr, 0, i2);
        byte[] bArr2 = new byte[chars2utf];
        System.arraycopy(bArr, 0, bArr2, 0, chars2utf);
        return bArr2;
    }

    public static byte[] chars2utf(char[] cArr) {
        return chars2utf(cArr, 0, cArr.length);
    }

    public static byte[] string2utf(String str) {
        return chars2utf(str.toCharArray());
    }

    public static String quote(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(quote(str.charAt(i)));
        }
        return sb.toString();
    }

    public static String quote(char c) {
        switch (c) {
            case '\b':
                return "\\b";
            case '\t':
                return "\\t";
            case '\n':
                return "\\n";
            case '\f':
                return "\\f";
            case '\r':
                return "\\r";
            case '\"':
                return "\\\"";
            case '\'':
                return "\\'";
            case '\\':
                return "\\\\";
            default:
                return isPrintableAscii(c) ? String.valueOf(c) : String.format("\\u%04x", Integer.valueOf(c));
        }
    }

    private static boolean isPrintableAscii(char c) {
        return c >= ' ' && c <= '~';
    }

    public static String escapeUnicode(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (str.charAt(i) > 255) {
                StringBuilder sb = new StringBuilder();
                sb.append(str.substring(0, i));
                while (i < length) {
                    char charAt = str.charAt(i);
                    if (charAt > 255) {
                        sb.append("\\u");
                        sb.append(Character.forDigit((charAt >> '\f') % 16, 16));
                        sb.append(Character.forDigit((charAt >> '\b') % 16, 16));
                        sb.append(Character.forDigit((charAt >> 4) % 16, 16));
                        sb.append(Character.forDigit(charAt % 16, 16));
                    } else {
                        sb.append(charAt);
                    }
                    i++;
                }
                str = sb.toString();
            } else {
                i++;
            }
        }
        return str;
    }

    public static Name shortName(Name name) {
        return name.subName(name.lastIndexOf((byte) 46) + 1, name.getByteLength());
    }

    public static String shortName(String str) {
        return str.substring(str.lastIndexOf(46) + 1);
    }

    public static Name packagePart(Name name) {
        return name.subName(0, name.lastIndexOf((byte) 46));
    }

    public static String packagePart(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf < 0 ? "" : str.substring(0, lastIndexOf);
    }

    public static List<Name> enclosingCandidates(Name name) {
        List<Name> nil = List.nil();
        while (true) {
            List<Name> list = nil;
            int lastIndexOf = name.lastIndexOf((byte) 36);
            if (lastIndexOf > 0) {
                name = name.subName(0, lastIndexOf);
                nil = list.prepend(name);
            } else {
                return list;
            }
        }
    }
}
