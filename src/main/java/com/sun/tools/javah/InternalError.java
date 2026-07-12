package com.sun.tools.javah;

/* loaded from: target.jar:com/sun/tools/javah/InternalError.class */
public class InternalError extends Error {
    private static final long serialVersionUID = 8411861562497165022L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalError(String str, Throwable th) {
        super("Internal error: " + str);
        initCause(th);
    }
}
