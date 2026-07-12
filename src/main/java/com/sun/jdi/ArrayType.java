package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/ArrayType.class */
public interface ArrayType extends ReferenceType {
    ArrayReference newInstance(int i);

    String componentSignature();

    String componentTypeName();

    Type componentType() throws ClassNotLoadedException;
}
