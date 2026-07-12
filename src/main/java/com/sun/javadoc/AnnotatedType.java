package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/AnnotatedType.class */
public interface AnnotatedType extends Type {
    AnnotationDesc[] annotations();

    Type underlyingType();
}
