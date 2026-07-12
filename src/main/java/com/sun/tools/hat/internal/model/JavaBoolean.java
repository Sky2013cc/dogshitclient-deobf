package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaBoolean.class */
public class JavaBoolean extends JavaValue {
    boolean value;

    public JavaBoolean(boolean z) {
        this.value = z;
    }

    @Override // com.sun.tools.hat.internal.model.JavaValue, com.sun.tools.hat.internal.model.JavaThing
    public String toString() {
        return "" + this.value;
    }
}
