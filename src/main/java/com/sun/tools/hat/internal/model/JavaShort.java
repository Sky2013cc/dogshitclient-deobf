package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaShort.class */
public class JavaShort extends JavaValue {
    short value;

    public JavaShort(short s) {
        this.value = s;
    }

    @Override // com.sun.tools.hat.internal.model.JavaValue, com.sun.tools.hat.internal.model.JavaThing
    public String toString() {
        return "" + ((int) this.value);
    }
}
