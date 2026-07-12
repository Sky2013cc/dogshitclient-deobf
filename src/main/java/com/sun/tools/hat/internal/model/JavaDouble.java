package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaDouble.class */
public class JavaDouble extends JavaValue {
    double value;

    public JavaDouble(double d) {
        this.value = d;
    }

    @Override // com.sun.tools.hat.internal.model.JavaValue, com.sun.tools.hat.internal.model.JavaThing
    public String toString() {
        return Double.toString(this.value);
    }
}
