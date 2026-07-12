package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/BooleanValue.class */
public interface BooleanValue extends PrimitiveValue {
    boolean value();

    boolean equals(Object obj);

    int hashCode();
}
