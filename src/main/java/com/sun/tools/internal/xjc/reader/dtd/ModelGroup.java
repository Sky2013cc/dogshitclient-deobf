package com.sun.tools.internal.xjc.reader.dtd;

import java.util.ArrayList;
import java.util.List;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/dtd/ModelGroup.class */
final class ModelGroup extends Term {
    Kind kind;
    private final List<Term> terms = new ArrayList();
    static final /* synthetic */ boolean $assertionsDisabled;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/internal/xjc/reader/dtd/ModelGroup$Kind.class */
    public enum Kind {
        CHOICE,
        SEQUENCE
    }

    static {
        $assertionsDisabled = !ModelGroup.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.dtd.Term
    public void normalize(List<Block> r, boolean optional) {
        switch (this.kind) {
            case SEQUENCE:
                for (Term t : this.terms) {
                    t.normalize(r, optional);
                }
                return;
            case CHOICE:
                Block b = new Block(isOptional() || optional, isRepeated());
                addAllElements(b);
                r.add(b);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.dtd.Term
    public void addAllElements(Block b) {
        for (Term t : this.terms) {
            t.addAllElements(b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.dtd.Term
    public boolean isOptional() {
        switch (this.kind) {
            case SEQUENCE:
                for (Term t : this.terms) {
                    if (!t.isOptional()) {
                        return false;
                    }
                }
                return true;
            case CHOICE:
                for (Term t2 : this.terms) {
                    if (t2.isOptional()) {
                        return true;
                    }
                }
                return false;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.dtd.Term
    public boolean isRepeated() {
        switch (this.kind) {
            case SEQUENCE:
                return true;
            case CHOICE:
                for (Term t : this.terms) {
                    if (t.isRepeated()) {
                        return true;
                    }
                }
                return false;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setKind(short connectorType) {
        Kind k;
        switch (connectorType) {
            case 0:
                k = Kind.CHOICE;
                break;
            case 1:
                k = Kind.SEQUENCE;
                break;
            default:
                throw new IllegalArgumentException();
        }
        if (!$assertionsDisabled && this.kind != null && k != this.kind) {
            throw new AssertionError();
        }
        this.kind = k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addTerm(Term t) {
        if (t instanceof ModelGroup) {
            ModelGroup mg = (ModelGroup) t;
            if (mg.kind == this.kind) {
                this.terms.addAll(mg.terms);
                return;
            }
        }
        this.terms.add(t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Term wrapUp() {
        switch (this.terms.size()) {
            case 0:
                return EMPTY;
            case 1:
                if ($assertionsDisabled || this.kind == null) {
                    return this.terms.get(0);
                }
                throw new AssertionError();
            default:
                if ($assertionsDisabled || this.kind != null) {
                    return this;
                }
                throw new AssertionError();
        }
    }
}
