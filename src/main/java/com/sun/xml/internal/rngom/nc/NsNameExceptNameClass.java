package com.sun.xml.internal.rngom.nc;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/xml/internal/rngom/nc/NsNameExceptNameClass.class */
public class NsNameExceptNameClass extends NameClass {
    private final NameClass nameClass;
    private final String namespaceURI;

    public NsNameExceptNameClass(String namespaceURI, NameClass nameClass) {
        this.namespaceURI = namespaceURI;
        this.nameClass = nameClass;
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean contains(QName name) {
        return this.namespaceURI.equals(name.getNamespaceURI()) && !this.nameClass.contains(name);
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public int containsSpecificity(QName name) {
        return contains(name) ? 1 : -1;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof NsNameExceptNameClass)) {
            return false;
        }
        NsNameExceptNameClass other = (NsNameExceptNameClass) obj;
        return this.namespaceURI.equals(other.namespaceURI) && this.nameClass.equals(other.nameClass);
    }

    public int hashCode() {
        return this.namespaceURI.hashCode() ^ this.nameClass.hashCode();
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public <V> V accept(NameClassVisitor<V> visitor) {
        return visitor.visitNsNameExcept(this.namespaceURI, this.nameClass);
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean isOpen() {
        return true;
    }
}
