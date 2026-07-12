package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/ObjectCollectedException.class */
public class ObjectCollectedException extends RuntimeException {
    private static final long serialVersionUID = -1928428056197269588L;

    public ObjectCollectedException() {
    }

    public ObjectCollectedException(String str) {
        super(str);
    }
}
