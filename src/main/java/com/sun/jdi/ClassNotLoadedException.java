package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/ClassNotLoadedException.class */
public class ClassNotLoadedException extends Exception {
    private static final long serialVersionUID = -6242978768444298722L;
    private String className;

    public ClassNotLoadedException(String str) {
        this.className = str;
    }

    public ClassNotLoadedException(String str, String str2) {
        super(str2);
        this.className = str;
    }

    public String className() {
        return this.className;
    }
}
