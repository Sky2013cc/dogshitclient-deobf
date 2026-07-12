package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/ByteValue.class */
public interface ByteValue extends PrimitiveValue, Comparable<ByteValue> {
    byte value();

    boolean equals(Object obj);

    int hashCode();
}
