package com.sun.tools.doclets.internal.toolkit.util;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/util/DocletAbortException.class */
public class DocletAbortException extends RuntimeException {
    private static final long serialVersionUID = -9131058909576418984L;

    public DocletAbortException(String str) {
        super(str);
    }

    public DocletAbortException(Throwable th) {
        super(th);
    }
}
