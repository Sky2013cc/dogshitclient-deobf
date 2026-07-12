package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/TypeComponent.class */
public interface TypeComponent extends Mirror, Accessible {
    String name();

    String signature();

    String genericSignature();

    ReferenceType declaringType();

    boolean isStatic();

    boolean isFinal();

    boolean isSynthetic();
}
