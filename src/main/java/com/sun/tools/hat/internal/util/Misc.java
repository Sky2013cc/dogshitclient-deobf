package com.sun.tools.hat.internal.util;

import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/hat/internal/util/Misc.class */
public class Misc {
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final String toHex(int i) {
        char[] cArr = new char[8];
        int i2 = 0;
        for (int i3 = 28; i3 >= 0; i3 -= 4) {
            int i4 = i2;
            i2++;
            cArr[i4] = digits[(i >> i3) & 15];
        }
        return "0x" + new String(cArr);
    }

    public static final String toHex(long j) {
        return "0x" + Long.toHexString(j);
    }

    public static final long parseHex(String str) {
        long j;
        int i;
        long j2 = 0;
        if (str.length() < 2 || str.charAt(0) != '0' || str.charAt(1) != 'x') {
            return -1L;
        }
        for (int i2 = 2; i2 < str.length(); i2++) {
            long j3 = j2 * 16;
            char charAt = str.charAt(i2);
            if (charAt >= '0' && charAt <= '9') {
                j = j3;
                i = charAt - '0';
            } else if (charAt >= 'a' && charAt <= 'f') {
                j = j3;
                i = (charAt - 'a') + 10;
            } else if (charAt >= 'A' && charAt <= 'F') {
                j = j3;
                i = (charAt - 'A') + 10;
            } else {
                throw new NumberFormatException("" + charAt + " is not a valid hex digit");
            }
            j2 = j + i;
        }
        return j2;
    }

    public static String encodeHtml(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '<') {
                stringBuffer.append("&lt;");
            } else if (charAt == '>') {
                stringBuffer.append("&gt;");
            } else if (charAt == '\"') {
                stringBuffer.append("&quot;");
            } else if (charAt == '\'') {
                stringBuffer.append("&#039;");
            } else if (charAt == '&') {
                stringBuffer.append("&amp;");
            } else if (charAt < ' ') {
                stringBuffer.append("&#" + Integer.toString(charAt) + RuntimeConstants.SIG_ENDCLASS);
            } else {
                int i2 = charAt & 65535;
                if (i2 > 127) {
                    stringBuffer.append("&#" + Integer.toString(i2) + RuntimeConstants.SIG_ENDCLASS);
                } else {
                    stringBuffer.append(charAt);
                }
            }
        }
        return stringBuffer.toString();
    }
}
