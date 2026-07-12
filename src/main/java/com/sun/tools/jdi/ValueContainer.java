package com.sun.tools.jdi;

import com.sun.jdi.ClassNotLoadedException;
import com.sun.jdi.Type;

/* loaded from: target.jar:com/sun/tools/jdi/ValueContainer.class */
interface ValueContainer {
    Type type() throws ClassNotLoadedException;

    Type findType(String str) throws ClassNotLoadedException;

    String typeName();

    String signature();
}
