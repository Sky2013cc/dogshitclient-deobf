package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/VMCannotBeModifiedException.class */
public class VMCannotBeModifiedException extends UnsupportedOperationException {
    private static final long serialVersionUID = -4063879815130164009L;

    public VMCannotBeModifiedException() {
    }

    public VMCannotBeModifiedException(String str) {
        super(str);
    }
}
