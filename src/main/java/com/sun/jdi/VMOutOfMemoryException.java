package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/VMOutOfMemoryException.class */
public class VMOutOfMemoryException extends RuntimeException {
    private static final long serialVersionUID = 71504228548910686L;

    public VMOutOfMemoryException() {
    }

    public VMOutOfMemoryException(String str) {
        super(str);
    }
}
