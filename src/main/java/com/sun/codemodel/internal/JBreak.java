package com.sun.codemodel.internal;

/* loaded from: target.jar:com/sun/codemodel/internal/JBreak.class */
final class JBreak implements JStatement {
    private final JLabel label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JBreak(JLabel _label) {
        this.label = _label;
    }

    @Override // com.sun.codemodel.internal.JStatement
    public void state(JFormatter f) {
        if (this.label == null) {
            f.p("break;").nl();
        } else {
            f.p("break").p(this.label.label).p(';').nl();
        }
    }
}
