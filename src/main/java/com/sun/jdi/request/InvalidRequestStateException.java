package com.sun.jdi.request;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/request/InvalidRequestStateException.class */
public class InvalidRequestStateException extends RuntimeException {
    private static final long serialVersionUID = -3774632428543322148L;

    public InvalidRequestStateException() {
    }

    public InvalidRequestStateException(String str) {
        super(str);
    }
}
