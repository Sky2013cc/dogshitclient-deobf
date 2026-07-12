package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/ParameterizedType.class */
public interface ParameterizedType extends Type {
    @Override // com.sun.javadoc.Type
    ClassDoc asClassDoc();

    Type[] typeArguments();

    Type superclassType();

    Type[] interfaceTypes();

    Type containingType();
}
