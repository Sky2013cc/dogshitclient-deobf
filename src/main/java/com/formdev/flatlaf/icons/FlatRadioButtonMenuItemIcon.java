package com.formdev.flatlaf.icons;

import java.awt.Graphics2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatRadioButtonMenuItemIcon.class */
public class FlatRadioButtonMenuItemIcon extends FlatCheckBoxMenuItemIcon {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.formdev.flatlaf.icons.FlatCheckBoxMenuItemIcon
    public void paintCheckmark(Graphics2D g2) {
        g2.fillOval(4, 4, 7, 7);
    }
}
