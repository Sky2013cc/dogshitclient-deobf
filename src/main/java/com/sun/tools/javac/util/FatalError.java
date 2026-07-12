package com.sun.tools.javac.util;

/* loaded from: target.jar:com/sun/tools/javac/util/FatalError.class */
public class FatalError extends Error {
    private static final long serialVersionUID = 0;

    public FatalError(JCDiagnostic jCDiagnostic) {
        super(jCDiagnostic.toString());
    }

    public FatalError(JCDiagnostic jCDiagnostic, Throwable th) {
        super(jCDiagnostic.toString(), th);
    }

    public FatalError(String str) {
        super(str);
    }
}
