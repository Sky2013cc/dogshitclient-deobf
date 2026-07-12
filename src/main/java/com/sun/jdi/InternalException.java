package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/InternalException.class */
public class InternalException extends RuntimeException {
    private static final long serialVersionUID = -9171606393104480607L;
    private int errorCode;

    public InternalException() {
        this.errorCode = 0;
    }

    public InternalException(String str) {
        super(str);
        this.errorCode = 0;
    }

    public InternalException(int i) {
        this.errorCode = i;
    }

    public InternalException(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    public int errorCode() {
        return this.errorCode;
    }
}
