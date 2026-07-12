package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/TypeVariable.class */
public interface TypeVariable extends Type {
    Type[] bounds();

    ProgramElementDoc owner();

    AnnotationDesc[] annotations();
}
