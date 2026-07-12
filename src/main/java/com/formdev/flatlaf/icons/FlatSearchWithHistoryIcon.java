package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatSearchWithHistoryIcon.class */
public class FlatSearchWithHistoryIcon extends FlatSearchIcon {
    public FlatSearchWithHistoryIcon() {
        this(false);
    }

    public FlatSearchWithHistoryIcon(boolean ignoreButtonState) {
        super(ignoreButtonState);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.formdev.flatlaf.icons.FlatSearchIcon, com.formdev.flatlaf.icons.FlatAbstractIcon
    public void paintIcon(Component c, Graphics2D g) {
        g.translate(-2, 0);
        super.paintIcon(c, g);
        g.translate(2, 0);
        g.fill(FlatUIUtils.createPath(11.0d, 7.0d, 16.0d, 7.0d, 13.5d, 10.0d));
    }
}
