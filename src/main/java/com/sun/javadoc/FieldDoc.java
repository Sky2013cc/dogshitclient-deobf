package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/FieldDoc.class */
public interface FieldDoc extends MemberDoc {
    Type type();

    boolean isTransient();

    boolean isVolatile();

    SerialFieldTag[] serialFieldTags();

    Object constantValue();

    String constantValueExpression();
}
