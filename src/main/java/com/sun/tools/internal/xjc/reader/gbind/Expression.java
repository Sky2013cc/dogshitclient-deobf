package com.sun.tools.internal.xjc.reader.gbind;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/gbind/Expression.class */
public abstract class Expression {
    public static final Expression EPSILON = new Expression() { // from class: com.sun.tools.internal.xjc.reader.gbind.Expression.1
        @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
        ElementSet lastSet() {
            return ElementSet.EMPTY_SET;
        }

        @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
        boolean isNullable() {
            return true;
        }

        @Override // com.sun.tools.internal.xjc.reader.gbind.Expression
        void buildDAG(ElementSet incoming) {
        }

        public String toString() {
            return TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ElementSet lastSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isNullable();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void buildDAG(ElementSet elementSet);
}
