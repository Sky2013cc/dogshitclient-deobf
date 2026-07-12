package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/SerialFieldTag.class */
public interface SerialFieldTag extends Tag, Comparable<Object> {
    String fieldName();

    String fieldType();

    ClassDoc fieldTypeDoc();

    String description();

    @Override // java.lang.Comparable
    int compareTo(Object obj);
}
