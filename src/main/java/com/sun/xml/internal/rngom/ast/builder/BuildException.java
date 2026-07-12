package com.sun.xml.internal.rngom.ast.builder;

/* loaded from: target.jar:com/sun/xml/internal/rngom/ast/builder/BuildException.class */
public class BuildException extends RuntimeException {
    private final Throwable cause;

    public BuildException(Throwable cause) {
        if (cause == null) {
            throw new NullPointerException("null cause");
        }
        this.cause = cause;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
