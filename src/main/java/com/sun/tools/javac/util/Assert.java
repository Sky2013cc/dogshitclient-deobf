package com.sun.tools.javac.util;

/* loaded from: target.jar:com/sun/tools/javac/util/Assert.class */
public class Assert {
    public static void check(boolean z) {
        if (!z) {
            error();
        }
    }

    public static void checkNull(Object obj) {
        if (obj != null) {
            error();
        }
    }

    public static <T> T checkNonNull(T t) {
        if (t == null) {
            error();
        }
        return t;
    }

    public static void check(boolean z, int i) {
        if (!z) {
            error(String.valueOf(i));
        }
    }

    public static void check(boolean z, long j) {
        if (!z) {
            error(String.valueOf(j));
        }
    }

    public static void check(boolean z, Object obj) {
        if (!z) {
            error(String.valueOf(obj));
        }
    }

    public static void check(boolean z, String str) {
        if (!z) {
            error(str);
        }
    }

    public static void checkNull(Object obj, Object obj2) {
        if (obj != null) {
            error(String.valueOf(obj2));
        }
    }

    public static void checkNull(Object obj, String str) {
        if (obj != null) {
            error(str);
        }
    }

    public static <T> T checkNonNull(T t, String str) {
        if (t == null) {
            error(str);
        }
        return t;
    }

    public static void error() {
        throw new AssertionError();
    }

    public static void error(String str) {
        throw new AssertionError(str);
    }

    private Assert() {
    }
}
