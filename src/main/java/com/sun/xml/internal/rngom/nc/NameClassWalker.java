package com.sun.xml.internal.rngom.nc;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/xml/internal/rngom/nc/NameClassWalker.class */
public class NameClassWalker implements NameClassVisitor<Void> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitChoice(NameClass nc1, NameClass nc2) {
        nc1.accept(this);
        return (Void) nc2.accept(this);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitNsName(String ns) {
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitNsNameExcept(String ns, NameClass nc) {
        return (Void) nc.accept(this);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitAnyName() {
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitAnyNameExcept(NameClass nc) {
        return (Void) nc.accept(this);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitName(QName name) {
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitNull() {
        return null;
    }
}
