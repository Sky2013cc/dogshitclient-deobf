package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/CharValue.class */
public interface CharValue extends PrimitiveValue, Comparable<CharValue> {
    char value();

    boolean equals(Object obj);

    int hashCode();
}
