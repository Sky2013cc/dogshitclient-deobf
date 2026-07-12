package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/ClassNotPreparedException.class */
public class ClassNotPreparedException extends RuntimeException {
    private static final long serialVersionUID = -6120698967144079642L;

    public ClassNotPreparedException() {
    }

    public ClassNotPreparedException(String str) {
        super(str);
    }
}
