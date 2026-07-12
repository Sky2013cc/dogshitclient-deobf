package com.sun.tools.internal.xjc.reader.dtd;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/dtd/Term.class */
public abstract class Term {
    static final Term EMPTY = new Term() { // from class: com.sun.tools.internal.xjc.reader.dtd.Term.1
        @Override // com.sun.tools.internal.xjc.reader.dtd.Term
        void normalize(List<Block> r, boolean optional) {
        }

        @Override // com.sun.tools.internal.xjc.reader.dtd.Term
        void addAllElements(Block b) {
        }

        @Override // com.sun.tools.internal.xjc.reader.dtd.Term
        boolean isOptional() {
            return false;
        }

        @Override // com.sun.tools.internal.xjc.reader.dtd.Term
        boolean isRepeated() {
            return false;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void normalize(List<Block> list, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void addAllElements(Block block);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isOptional();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isRepeated();
}
