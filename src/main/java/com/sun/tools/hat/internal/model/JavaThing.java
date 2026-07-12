package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaThing.class */
public abstract class JavaThing {
    public abstract boolean isHeapAllocated();

    public abstract int getSize();

    public abstract String toString();

    public JavaThing dereference(Snapshot snapshot, JavaField javaField) {
        return this;
    }

    public boolean isSameTypeAs(JavaThing javaThing) {
        return getClass() == javaThing.getClass();
    }

    public int compareTo(JavaThing javaThing) {
        return toString().compareTo(javaThing.toString());
    }
}
