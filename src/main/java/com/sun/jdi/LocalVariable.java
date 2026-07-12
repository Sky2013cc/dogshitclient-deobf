package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/LocalVariable.class */
public interface LocalVariable extends Mirror, Comparable<LocalVariable> {
    String name();

    String typeName();

    Type type() throws ClassNotLoadedException;

    String signature();

    String genericSignature();

    boolean isVisible(StackFrame stackFrame);

    boolean isArgument();

    boolean equals(Object obj);

    int hashCode();
}
