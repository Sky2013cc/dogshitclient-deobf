package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/ThrowsTag.class */
public interface ThrowsTag extends Tag {
    String exceptionName();

    String exceptionComment();

    ClassDoc exception();

    Type exceptionType();
}
