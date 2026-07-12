package com.formdev.flatlaf.icons;

import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatTreeExpandedIcon.class */
public class FlatTreeExpandedIcon extends FlatTreeCollapsedIcon {
    public FlatTreeExpandedIcon() {
        super(UIManager.getColor("Tree.icon.expandedColor"));
    }

    @Override // com.formdev.flatlaf.icons.FlatTreeCollapsedIcon
    void setStyleColorFromTreeUI(Component c, Graphics2D g) {
        setStyleColorFromTreeUI(c, g, ui -> {
            return ui.iconExpandedColor;
        });
    }

    @Override // com.formdev.flatlaf.icons.FlatTreeCollapsedIcon
    void rotate(Component c, Graphics2D g) {
        g.rotate(Math.toRadians(90.0d), this.width / 2.0d, this.height / 2.0d);
    }
}
