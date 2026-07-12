package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/NativeMethodException.class */
public class NativeMethodException extends RuntimeException {
    private static final long serialVersionUID = 3924951669039469992L;

    public NativeMethodException() {
    }

    public NativeMethodException(String str) {
        super(str);
    }
}
