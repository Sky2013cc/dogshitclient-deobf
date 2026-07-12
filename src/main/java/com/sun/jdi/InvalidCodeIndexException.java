package com.sun.jdi;

import jdk.Exported;

@Exported
@Deprecated
/* loaded from: target.jar:com/sun/jdi/InvalidCodeIndexException.class */
public class InvalidCodeIndexException extends RuntimeException {
    private static final long serialVersionUID = 7416010225133747805L;

    public InvalidCodeIndexException() {
    }

    public InvalidCodeIndexException(String str) {
        super(str);
    }
}
