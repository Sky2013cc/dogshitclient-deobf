package com.sun.xml.internal.rngom.nc;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/xml/internal/rngom/nc/SimpleNameClass.class */
public class SimpleNameClass extends NameClass {
    public final QName name;

    public SimpleNameClass(QName name) {
        this.name = name;
    }

    public SimpleNameClass(String nsUri, String localPart) {
        this(new QName(nsUri, localPart));
    }

    public SimpleNameClass(String nsUri, String localPart, String prefix) {
        this(new QName(nsUri, localPart, prefix));
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean contains(QName name) {
        return this.name.equals(name);
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public int containsSpecificity(QName name) {
        return contains(name) ? 2 : -1;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SimpleNameClass)) {
            return false;
        }
        SimpleNameClass other = (SimpleNameClass) obj;
        return this.name.equals(other.name);
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public <V> V accept(NameClassVisitor<V> visitor) {
        return visitor.visitName(this.name);
    }

    @Override // com.sun.xml.internal.rngom.nc.NameClass
    public boolean isOpen() {
        return false;
    }
}
