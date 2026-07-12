package com.sun.tools.hat.internal.util;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/* loaded from: target.jar:com/sun/tools/hat/internal/util/CompositeEnumeration.class */
public class CompositeEnumeration implements Enumeration {
    Enumeration e1;
    Enumeration e2;

    public CompositeEnumeration(Enumeration enumeration, Enumeration enumeration2) {
        this.e1 = enumeration;
        this.e2 = enumeration2;
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.e1.hasMoreElements() || this.e2.hasMoreElements();
    }

    @Override // java.util.Enumeration
    public Object nextElement() {
        if (this.e1.hasMoreElements()) {
            return this.e1.nextElement();
        }
        if (this.e2.hasMoreElements()) {
            return this.e2.nextElement();
        }
        throw new NoSuchElementException();
    }
}
