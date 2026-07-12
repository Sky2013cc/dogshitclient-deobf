package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/IntegerValue.class */
public interface IntegerValue extends PrimitiveValue, Comparable<IntegerValue> {
    int value();

    boolean equals(Object obj);

    int hashCode();
}
