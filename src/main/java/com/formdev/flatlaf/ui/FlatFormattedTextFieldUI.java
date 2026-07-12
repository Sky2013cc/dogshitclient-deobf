package com.formdev.flatlaf.ui;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatFormattedTextFieldUI.class */
public class FlatFormattedTextFieldUI extends FlatTextFieldUI {
    public static ComponentUI createUI(JComponent c) {
        return new FlatFormattedTextFieldUI();
    }

    protected String getPropertyPrefix() {
        return "FormattedTextField";
    }

    @Override // com.formdev.flatlaf.ui.FlatTextFieldUI
    String getStyleType() {
        return "FormattedTextField";
    }
}
