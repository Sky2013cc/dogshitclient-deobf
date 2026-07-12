package com.sun.xml.internal.rngom.nc;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/xml/internal/rngom/nc/OverlapDetector.class */
class OverlapDetector implements NameClassVisitor<Void> {
    private NameClass nc1;
    private NameClass nc2;
    private boolean overlaps = false;
    static final String IMPOSSIBLE = "��";

    private OverlapDetector(NameClass nc1, NameClass nc2) {
        this.nc1 = nc1;
        this.nc2 = nc2;
        nc1.accept(this);
        nc2.accept(this);
    }

    private void probe(QName name) {
        if (this.nc1.contains(name) && this.nc2.contains(name)) {
            this.overlaps = true;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitChoice(NameClass nc1, NameClass nc2) {
        nc1.accept(this);
        nc2.accept(this);
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitNsName(String ns) {
        probe(new QName(ns, IMPOSSIBLE));
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitNsNameExcept(String ns, NameClass ex) {
        probe(new QName(ns, IMPOSSIBLE));
        ex.accept(this);
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitAnyName() {
        probe(new QName(IMPOSSIBLE, IMPOSSIBLE));
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitAnyNameExcept(NameClass ex) {
        probe(new QName(IMPOSSIBLE, IMPOSSIBLE));
        ex.accept(this);
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitName(QName name) {
        probe(name);
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.internal.rngom.nc.NameClassVisitor
    public Void visitNull() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean overlap(NameClass nc1, NameClass nc2) {
        if (nc2 instanceof SimpleNameClass) {
            SimpleNameClass snc = (SimpleNameClass) nc2;
            return nc1.contains(snc.name);
        }
        if (nc1 instanceof SimpleNameClass) {
            SimpleNameClass snc2 = (SimpleNameClass) nc1;
            return nc2.contains(snc2.name);
        }
        return new OverlapDetector(nc1, nc2).overlaps;
    }
}
