package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/DoubleValue.class */
public interface DoubleValue extends PrimitiveValue, Comparable<DoubleValue> {
    double value();

    boolean equals(Object obj);

    int hashCode();
}
