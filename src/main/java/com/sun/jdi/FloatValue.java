package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/FloatValue.class */
public interface FloatValue extends PrimitiveValue, Comparable<FloatValue> {
    float value();

    boolean equals(Object obj);

    int hashCode();
}
