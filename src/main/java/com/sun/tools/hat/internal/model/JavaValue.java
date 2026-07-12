package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaValue.class */
public abstract class JavaValue extends JavaThing {
    @Override // com.sun.tools.hat.internal.model.JavaThing
    public abstract String toString();

    @Override // com.sun.tools.hat.internal.model.JavaThing
    public boolean isHeapAllocated() {
        return false;
    }

    @Override // com.sun.tools.hat.internal.model.JavaThing
    public int getSize() {
        return 0;
    }
}
