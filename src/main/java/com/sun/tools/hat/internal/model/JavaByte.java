package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaByte.class */
public class JavaByte extends JavaValue {
    byte value;

    public JavaByte(byte b) {
        this.value = b;
    }

    @Override // com.sun.tools.hat.internal.model.JavaValue, com.sun.tools.hat.internal.model.JavaThing
    public String toString() {
        return "0x" + Integer.toString(this.value & 255, 16);
    }
}
