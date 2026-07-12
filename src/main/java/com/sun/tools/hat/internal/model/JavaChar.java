package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaChar.class */
public class JavaChar extends JavaValue {
    char value;

    public JavaChar(char c) {
        this.value = c;
    }

    @Override // com.sun.tools.hat.internal.model.JavaValue, com.sun.tools.hat.internal.model.JavaThing
    public String toString() {
        return "" + this.value;
    }
}
