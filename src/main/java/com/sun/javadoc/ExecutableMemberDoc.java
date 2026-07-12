package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/ExecutableMemberDoc.class */
public interface ExecutableMemberDoc extends MemberDoc {
    ClassDoc[] thrownExceptions();

    Type[] thrownExceptionTypes();

    boolean isNative();

    boolean isSynchronized();

    boolean isVarArgs();

    Parameter[] parameters();

    Type receiverType();

    ThrowsTag[] throwsTags();

    ParamTag[] paramTags();

    ParamTag[] typeParamTags();

    String signature();

    String flatSignature();

    TypeVariable[] typeParameters();
}
