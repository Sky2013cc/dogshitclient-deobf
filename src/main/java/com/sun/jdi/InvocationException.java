package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/InvocationException.class */
public class InvocationException extends Exception {
    private static final long serialVersionUID = 6066780907971918568L;
    ObjectReference exception;

    public InvocationException(ObjectReference objectReference) {
        super("Exception occurred in target VM");
        this.exception = objectReference;
    }

    public ObjectReference exception() {
        return this.exception;
    }
}
