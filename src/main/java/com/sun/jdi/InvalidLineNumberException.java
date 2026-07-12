package com.sun.jdi;

import jdk.Exported;

@Exported
@Deprecated
/* loaded from: target.jar:com/sun/jdi/InvalidLineNumberException.class */
public class InvalidLineNumberException extends RuntimeException {
    private static final long serialVersionUID = 4048709912372692875L;

    public InvalidLineNumberException() {
    }

    public InvalidLineNumberException(String str) {
        super(str);
    }
}
