package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/VMDisconnectedException.class */
public class VMDisconnectedException extends RuntimeException {
    private static final long serialVersionUID = 2892975269768351637L;

    public VMDisconnectedException() {
    }

    public VMDisconnectedException(String str) {
        super(str);
    }
}
