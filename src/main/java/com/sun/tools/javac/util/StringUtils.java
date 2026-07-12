package com.sun.tools.javac.util;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: target.jar:com/sun/tools/javac/util/StringUtils.class */
public class StringUtils {
    public static String toLowerCase(String str) {
        return str.toLowerCase(Locale.US);
    }

    public static String toUpperCase(String str) {
        return str.toUpperCase(Locale.US);
    }

    public static int indexOfIgnoreCase(String str, String str2) {
        return indexOfIgnoreCase(str, str2, 0);
    }

    public static int indexOfIgnoreCase(String str, String str2, int i) {
        Matcher matcher = Pattern.compile(Pattern.quote(str2), 2).matcher(str);
        if (matcher.find(i)) {
            return matcher.start();
        }
        return -1;
    }
}
