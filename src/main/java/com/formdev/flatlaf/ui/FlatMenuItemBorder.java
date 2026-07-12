package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatMenuItemBorder.class */
public class FlatMenuItemBorder extends FlatMarginBorder {
    private final Insets menuBarItemMargins = UIManager.getInsets("MenuBar.itemMargins");

    @Override // com.formdev.flatlaf.ui.FlatMarginBorder
    public Insets getBorderInsets(Component c, Insets insets) {
        Insets insets2;
        JMenuBar parent = c.getParent();
        if (parent instanceof JMenuBar) {
            FlatMenuBarUI ui = parent.getUI();
            if ((ui instanceof FlatMenuBarUI) && ui.itemMargins != null) {
                insets2 = ui.itemMargins;
            } else {
                insets2 = this.menuBarItemMargins;
            }
            Insets margins = insets2;
            insets.top = UIScale.scale(margins.top);
            insets.left = UIScale.scale(margins.left);
            insets.bottom = UIScale.scale(margins.bottom);
            insets.right = UIScale.scale(margins.right);
            return insets;
        }
        return super.getBorderInsets(c, insets);
    }
}
