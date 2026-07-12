package com.sun.xml.internal.rngom.nc;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/xml/internal/rngom/nc/AnyNameClass.class */
final class AnyNameClass extends NameClass {
    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean contains(QName name) {
        return true;
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public int containsSpecificity(QName name) {
        return 0;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return AnyNameClass.class.hashCode();
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public <V> V accept(NameClassVisitor<V> visitor) {
        return visitor.visitAnyName();
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean isOpen() {
        return true;
    }
}
