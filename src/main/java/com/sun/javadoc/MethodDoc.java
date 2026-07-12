package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/MethodDoc.class */
public interface MethodDoc extends ExecutableMemberDoc {
    boolean isAbstract();

    boolean isDefault();

    Type returnType();

    ClassDoc overriddenClass();

    Type overriddenType();

    MethodDoc overriddenMethod();

    boolean overrides(MethodDoc methodDoc);
}
