package com.sun.xml.internal.xsom.impl.util;

import java.io.IOException;
import java.net.URL;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/util/Uri.class */
public class Uri {
    private static final String HEX_DIGITS = "0123456789abcdef";
    private static String utf8 = "UTF-8";
    private static String excluded = "<>\"{}|\\^`";

    public static boolean isValid(String s) {
        return isValidPercent(s) && isValidFragment(s) && isValidScheme(s);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002e, code lost:
    
        if (r6 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0031, code lost:
    
        r6 = new java.lang.StringBuffer();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003c, code lost:
    
        if (r9 <= r8) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003f, code lost:
    
        r6.append(r5.substring(r8, r9));
        r8 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0051, code lost:
    
        if (r9 != r0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005d, code lost:
    
        if (r9 >= r0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0069, code lost:
    
        if (isExcluded(r5.charAt(r9)) == false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0072, code lost:
    
        r0 = r5.substring(r8, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007b, code lost:
    
        r11 = r0.getBytes(com.sun.xml.internal.xsom.impl.util.Uri.utf8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008a, code lost:
    
        com.sun.xml.internal.xsom.impl.util.Uri.utf8 = "UTF8";
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x008f, code lost:
    
        r11 = r0.getBytes(com.sun.xml.internal.xsom.impl.util.Uri.utf8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x009f, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00e9, code lost:
    
        return r6.toString();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String escapeDisallowedChars(String s) {
        byte[] bytes;
        StringBuffer buf = null;
        int len = s.length();
        int i = 0;
        while (true) {
            int done = i;
            int i2 = done;
            while (true) {
                if (i2 == len) {
                    if (done == 0) {
                        return s;
                    }
                } else {
                    if (isExcluded(s.charAt(i2))) {
                        break;
                    }
                    i2++;
                }
            }
            i = i2;
        }
        for (int j = 0; j < bytes.length; j++) {
            buf.append('%');
            buf.append(HEX_DIGITS.charAt((bytes[j] & 255) >> 4));
            buf.append(HEX_DIGITS.charAt(bytes[j] & 15));
        }
        i = i2;
    }

    private static boolean isExcluded(char c) {
        return c <= ' ' || c >= 127 || excluded.indexOf(c) >= 0;
    }

    private static boolean isAlpha(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    private static boolean isHexDigit(char c) {
        return ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F') || isDigit(c);
    }

    private static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isSchemeChar(char c) {
        return isAlpha(c) || isDigit(c) || c == '+' || c == '-' || c == '.';
    }

    private static boolean isValidPercent(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '%' && (i + 2 >= len || !isHexDigit(s.charAt(i + 1)) || !isHexDigit(s.charAt(i + 2)))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidFragment(String s) {
        int i = s.indexOf(35);
        return i < 0 || s.indexOf(35, i + 1) < 0;
    }

    private static boolean isValidScheme(String s) {
        if (!isAbsolute(s)) {
            return true;
        }
        int i = s.indexOf(58);
        if (i == 0 || i + 1 == s.length() || !isAlpha(s.charAt(0))) {
            return false;
        }
        do {
            i--;
            if (i <= 0) {
                return true;
            }
        } while (isSchemeChar(s.charAt(i)));
        return false;
    }

    public static String resolve(String baseUri, String uriReference) throws IOException {
        if (isAbsolute(uriReference)) {
            return uriReference;
        }
        if (baseUri == null) {
            throw new IOException("Unable to resolve relative URI " + uriReference + " without a base URI");
        }
        if (!isAbsolute(baseUri)) {
            throw new IOException("Unable to resolve relative URI " + uriReference + " because base URI is not absolute: " + baseUri);
        }
        return new URL(new URL(baseUri), uriReference).toString();
    }

    public static boolean hasFragmentId(String uri) {
        return uri.indexOf(35) >= 0;
    }

    public static boolean isAbsolute(String uri) {
        int i = uri.indexOf(58);
        if (i < 0) {
            return false;
        }
        while (true) {
            i--;
            if (i >= 0) {
                switch (uri.charAt(i)) {
                    case '#':
                    case '/':
                    case '?':
                        return false;
                }
            }
            return true;
        }
    }
}
