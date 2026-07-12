package com.sun.xml.internal.rngom.nc;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/xml/internal/rngom/nc/AnyNameExceptNameClass.class */
public class AnyNameExceptNameClass extends NameClass {
    private final NameClass nameClass;

    public AnyNameExceptNameClass(NameClass nameClass) {
        this.nameClass = nameClass;
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean contains(QName name) {
        return !this.nameClass.contains(name);
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public int containsSpecificity(QName name) {
        return contains(name) ? 0 : -1;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AnyNameExceptNameClass)) {
            return false;
        }
        return this.nameClass.equals(((AnyNameExceptNameClass) obj).nameClass);
    }

    public int hashCode() {
        return this.nameClass.hashCode() ^ (-1);
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public <V> V accept(NameClassVisitor<V> visitor) {
        return visitor.visitAnyNameExcept(this.nameClass);
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean isOpen() {
        return true;
    }
}
