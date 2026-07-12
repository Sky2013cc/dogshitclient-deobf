package com.sun.codemodel.internal;

/* loaded from: target.jar:com/sun/codemodel/internal/JContinue.class */
class JContinue implements JStatement {
    private final JLabel label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JContinue(JLabel _label) {
        this.label = _label;
    }

    @Override // com.sun.codemodel.internal.JStatement
    public void state(JFormatter f) {
        if (this.label == null) {
            f.p("continue;").nl();
        } else {
            f.p("continue").p(this.label.label).p(';').nl();
        }
    }
}
