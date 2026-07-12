package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/ParamTag.class */
public interface ParamTag extends Tag {
    String parameterName();

    String parameterComment();

    boolean isTypeParameter();
}
