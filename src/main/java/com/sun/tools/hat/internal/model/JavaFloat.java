package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaFloat.class */
public class JavaFloat extends JavaValue {
    float value;

    public JavaFloat(float f) {
        this.value = f;
    }

    @Override // com.sun.tools.hat.internal.model.JavaValue, com.sun.tools.hat.internal.model.JavaThing
    public String toString() {
        return Float.toString(this.value);
    }
}
