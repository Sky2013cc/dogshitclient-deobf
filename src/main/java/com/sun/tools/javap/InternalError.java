package com.sun.tools.javap;

/* loaded from: target.jar:com/sun/tools/javap/InternalError.class */
public class InternalError extends Error {
    private static final long serialVersionUID = 8114054446416187030L;
    public final Object[] args;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalError(Throwable th, Object... objArr) {
        super("Internal error", th);
        this.args = objArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalError(Object... objArr) {
        super("Internal error");
        this.args = objArr;
    }
}
