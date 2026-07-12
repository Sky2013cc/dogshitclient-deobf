package com.sun.tools.internal.xjc.reader.gbind;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/gbind/Choice.class */
public final class Choice extends Expression {
    private final Expression lhs;
    private final Expression rhs;
    private final boolean isNullable;

    public Choice(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.isNullable = lhs.isNullable() || rhs.isNullable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
    public boolean isNullable() {
        return this.isNullable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
    public ElementSet lastSet() {
        return ElementSets.union(this.lhs.lastSet(), this.rhs.lastSet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
    public void buildDAG(ElementSet incoming) {
        this.lhs.buildDAG(incoming);
        this.rhs.buildDAG(incoming);
    }

    public String toString() {
        return '(' + this.lhs.toString() + '|' + this.rhs.toString() + ')';
    }
}
