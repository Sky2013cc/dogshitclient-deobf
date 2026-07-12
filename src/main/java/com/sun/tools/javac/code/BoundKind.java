package com.sun.tools.javac.code;

/* loaded from: target.jar:com/sun/tools/javac/code/BoundKind.class */
public enum BoundKind {
    EXTENDS("? extends "),
    SUPER("? super "),
    UNBOUND("?");

    private final String name;

    BoundKind(String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
