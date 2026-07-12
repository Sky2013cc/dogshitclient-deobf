package com.sun.xml.internal.rngom.nc;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/xml/internal/rngom/nc/NullNameClass.class */
final class NullNameClass extends NameClass {
    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean contains(QName name) {
        return false;
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public int containsSpecificity(QName name) {
        return -1;
    }

    public int hashCode() {
        return NullNameClass.class.hashCode();
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public <V> V accept(NameClassVisitor<V> visitor) {
        return visitor.visitNull();
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean isOpen() {
        return false;
    }

    private Object readResolve() {
        return NameClass.NULL;
    }
}
