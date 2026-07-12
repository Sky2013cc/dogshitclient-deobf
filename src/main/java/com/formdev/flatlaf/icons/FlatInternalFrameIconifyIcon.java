package com.formdev.flatlaf.icons;

import java.awt.Component;
import java.awt.Graphics2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatInternalFrameIconifyIcon.class */
public class FlatInternalFrameIconifyIcon extends FlatInternalFrameAbstractIcon {
    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        paintBackground(c, g);
        g.setColor(c.getForeground());
        g.fillRect((this.width / 2) - 4, this.height / 2, 8, 1);
    }
}
