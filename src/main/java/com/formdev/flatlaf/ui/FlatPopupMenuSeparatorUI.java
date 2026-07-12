package com.formdev.flatlaf.ui;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatPopupMenuSeparatorUI.class */
public class FlatPopupMenuSeparatorUI extends FlatSeparatorUI {
    public static ComponentUI createUI(JComponent c) {
        if (FlatUIUtils.canUseSharedUI(c)) {
            return FlatUIUtils.createSharedUI(FlatPopupMenuSeparatorUI.class, () -> {
                return new FlatPopupMenuSeparatorUI(true);
            });
        }
        return new FlatPopupMenuSeparatorUI(false);
    }

    protected FlatPopupMenuSeparatorUI(boolean shared) {
        super(shared);
    }

    @Override // com.formdev.flatlaf.ui.FlatSeparatorUI
    protected String getPropertyPrefix() {
        return "PopupMenuSeparator";
    }

    @Override // com.formdev.flatlaf.ui.FlatSeparatorUI
    String getStyleType() {
        return "PopupMenuSeparator";
    }
}
