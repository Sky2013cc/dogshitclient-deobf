package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/LongValue.class */
public interface LongValue extends PrimitiveValue, Comparable<LongValue> {
    long value();

    boolean equals(Object obj);

    int hashCode();
}
