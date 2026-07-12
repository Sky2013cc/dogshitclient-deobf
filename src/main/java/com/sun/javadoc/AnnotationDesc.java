package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/AnnotationDesc.class */
public interface AnnotationDesc {

    /* loaded from: target.jar:com/sun/javadoc/AnnotationDesc$ElementValuePair.class */
    public interface ElementValuePair {
        AnnotationTypeElementDoc element();

        AnnotationValue value();
    }

    AnnotationTypeDoc annotationType();

    ElementValuePair[] elementValues();

    boolean isSynthesized();
}
