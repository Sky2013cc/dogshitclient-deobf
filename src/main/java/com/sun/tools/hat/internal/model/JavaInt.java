package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaInt.class */
public class JavaInt extends JavaValue {
    int value;

    public JavaInt(int i) {
        this.value = i;
    }

    @Override // com.sun.tools.hat.internal.model.JavaValue, com.sun.tools.hat.internal.model.JavaThing
    public String toString() {
        return "" + this.value;
    }
}
