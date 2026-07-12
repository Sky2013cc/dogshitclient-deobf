package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/Field.class */
public interface Field extends TypeComponent, Comparable<Field> {
    String typeName();

    Type type() throws ClassNotLoadedException;

    boolean isTransient();

    boolean isVolatile();

    boolean isEnumConstant();

    boolean equals(Object obj);

    int hashCode();
}
