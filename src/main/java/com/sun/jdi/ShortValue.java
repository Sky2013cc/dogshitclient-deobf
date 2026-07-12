package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/ShortValue.class */
public interface ShortValue extends PrimitiveValue, Comparable<ShortValue> {
    short value();

    boolean equals(Object obj);

    int hashCode();
}
