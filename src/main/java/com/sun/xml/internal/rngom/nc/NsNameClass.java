package com.sun.xml.internal.rngom.nc;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/xml/internal/rngom/nc/NsNameClass.class */
public final class NsNameClass extends NameClass {
    private final String namespaceUri;

    public NsNameClass(String namespaceUri) {
        this.namespaceUri = namespaceUri;
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean contains(QName name) {
        return this.namespaceUri.equals(name.getNamespaceURI());
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public int containsSpecificity(QName name) {
        return contains(name) ? 1 : -1;
    }

    public int hashCode() {
        return this.namespaceUri.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof NsNameClass)) {
            return false;
        }
        return this.namespaceUri.equals(((NsNameClass) obj).namespaceUri);
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public <V> V accept(NameClassVisitor<V> visitor) {
        return visitor.visitNsName(this.namespaceUri);
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean isOpen() {
        return true;
    }
}
