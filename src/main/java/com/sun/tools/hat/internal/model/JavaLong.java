package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaLong.class */
public class JavaLong extends JavaValue {
    long value;

    public JavaLong(long j) {
        this.value = j;
    }

    @Override // com.sun.tools.hat.internal.model.JavaValue, com.sun.tools.hat.internal.model.JavaThing
    public String toString() {
        return Long.toString(this.value);
    }
}
