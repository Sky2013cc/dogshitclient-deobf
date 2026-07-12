package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/PrimitiveValue.class */
public interface PrimitiveValue extends Value {
    boolean booleanValue();

    byte byteValue();

    char charValue();

    short shortValue();

    int intValue();

    long longValue();

    float floatValue();

    double doubleValue();
}
