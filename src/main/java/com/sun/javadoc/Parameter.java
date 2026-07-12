package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/Parameter.class */
public interface Parameter {
    Type type();

    String name();

    String typeName();

    String toString();

    AnnotationDesc[] annotations();
}
