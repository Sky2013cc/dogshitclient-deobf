package com.sun.codemodel.internal;

/* loaded from: target.jar:com/sun/codemodel/internal/JLabel.class */
public class JLabel implements JStatement {
    final String label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JLabel(String _label) {
        this.label = _label;
    }

    @Override // com.sun.codemodel.internal.JStatement
    public void state(JFormatter f) {
        f.p(this.label + ':').nl();
    }
}
