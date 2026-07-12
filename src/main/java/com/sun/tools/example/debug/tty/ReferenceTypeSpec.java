package com.sun.tools.example.debug.tty;

import com.sun.jdi.ReferenceType;
import com.sun.jdi.request.ClassPrepareRequest;

/* loaded from: target.jar:com/sun/tools/example/debug/tty/ReferenceTypeSpec.class */
interface ReferenceTypeSpec {
    boolean matches(ReferenceType referenceType);

    ClassPrepareRequest createPrepareRequest();

    int hashCode();

    boolean equals(Object obj);
}
