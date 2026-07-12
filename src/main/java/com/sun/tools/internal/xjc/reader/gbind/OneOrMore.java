package com.sun.tools.internal.xjc.reader.gbind;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/gbind/OneOrMore.class */
public final class OneOrMore extends Expression {
    private final Expression child;

    public OneOrMore(Expression child) {
        this.child = child;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
    public ElementSet lastSet() {
        return this.child.lastSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
    public boolean isNullable() {
        return this.child.isNullable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
    public void buildDAG(ElementSet incoming) {
        this.child.buildDAG(ElementSets.union(incoming, this.child.lastSet()));
    }

    public String toString() {
        return this.child.toString() + '+';
    }
}
