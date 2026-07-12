package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/InconsistentDebugInfoException.class */
public class InconsistentDebugInfoException extends RuntimeException {
    private static final long serialVersionUID = 7964236415376861808L;

    public InconsistentDebugInfoException() {
    }

    public InconsistentDebugInfoException(String str) {
        super(str);
    }
}
