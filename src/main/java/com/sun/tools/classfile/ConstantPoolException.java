package com.sun.tools.classfile;

/* loaded from: target.jar:com/sun/tools/classfile/ConstantPoolException.class */
public class ConstantPoolException extends Exception {
    private static final long serialVersionUID = -2324397349644754565L;
    public final int index;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstantPoolException(int i) {
        this.index = i;
    }
}
