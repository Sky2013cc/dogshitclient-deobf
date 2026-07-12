package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatInternalFrameMaximizeIcon.class */
public class FlatInternalFrameMaximizeIcon extends FlatInternalFrameAbstractIcon {
    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        paintBackground(c, g);
        g.setColor(c.getForeground());
        g.fill(FlatUIUtils.createRectangle((this.width / 2) - 4, (this.height / 2) - 4, 8.0f, 8.0f, 1.0f));
    }
}
