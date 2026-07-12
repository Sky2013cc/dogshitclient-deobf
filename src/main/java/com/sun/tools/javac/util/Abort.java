package com.sun.tools.javac.util;

/* loaded from: target.jar:com/sun/tools/javac/util/Abort.class */
public class Abort extends Error {
    private static final long serialVersionUID = 0;

    public Abort(Throwable th) {
        super(th);
    }

    public Abort() {
    }
}
