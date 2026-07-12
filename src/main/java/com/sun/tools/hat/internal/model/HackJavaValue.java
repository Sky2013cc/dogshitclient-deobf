package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/HackJavaValue.class */
public class HackJavaValue extends JavaValue {
    private String value;
    private int size;

    public HackJavaValue(String str, int i) {
        this.value = str;
        this.size = i;
    }

    @Override // com.sun.tools.hat.internal.model.JavaValue, com.sun.tools.hat.internal.model.JavaThing
    public String toString() {
        return this.value;
    }

    @Override // com.sun.tools.hat.internal.model.JavaValue, com.sun.tools.hat.internal.model.JavaThing
    public int getSize() {
        return this.size;
    }
}
