package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/InvalidStackFrameException.class */
public class InvalidStackFrameException extends RuntimeException {
    private static final long serialVersionUID = -1919378296505827922L;

    public InvalidStackFrameException() {
    }

    public InvalidStackFrameException(String str) {
        super(str);
    }
}
