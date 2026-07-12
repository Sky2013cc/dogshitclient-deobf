package com.sun.tools.internal.xjc.util;

import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/xjc/util/Util.class */
public final class Util {
    private Util() {
    }

    public static String getSystemProperty(String name) {
        try {
            return System.getProperty(name);
        } catch (SecurityException e) {
            return null;
        }
    }

    public static boolean equals(Locator lhs, Locator rhs) {
        return lhs.getLineNumber() == rhs.getLineNumber() && lhs.getColumnNumber() == rhs.getColumnNumber() && equals(lhs.getSystemId(), rhs.getSystemId()) && equals(lhs.getPublicId(), rhs.getPublicId());
    }

    private static boolean equals(String lhs, String rhs) {
        if (lhs == null && rhs == null) {
            return true;
        }
        if (lhs == null || rhs == null) {
            return false;
        }
        return lhs.equals(rhs);
    }

    public static String getSystemProperty(Class clazz, String name) {
        return getSystemProperty(clazz.getName() + '.' + name);
    }
}
