package com.sun.tools.internal.xjc.reader.dtd;

import java.util.List;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/dtd/Occurence.class */
final class Occurence extends Term {
    final Term term;
    final boolean isOptional;
    final boolean isRepeated;

    Occurence(Term term, boolean optional, boolean repeated) {
        this.term = term;
        this.isOptional = optional;
        this.isRepeated = repeated;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Term wrap(Term t, int occurence) {
        switch (occurence) {
            case 0:
                return new Occurence(t, true, true);
            case 1:
                return new Occurence(t, false, true);
            case 2:
                return new Occurence(t, true, false);
            case 3:
                return t;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.dtd.Term
    public void normalize(List<Block> r, boolean optional) {
        if (this.isRepeated) {
            Block b = new Block(this.isOptional || optional, true);
            addAllElements(b);
            r.add(b);
            return;
        }
        this.term.normalize(r, optional || this.isOptional);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.dtd.Term
    public void addAllElements(Block b) {
        this.term.addAllElements(b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.dtd.Term
    public boolean isOptional() {
        return this.isOptional || this.term.isOptional();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.dtd.Term
    public boolean isRepeated() {
        return this.isRepeated || this.term.isRepeated();
    }
}
