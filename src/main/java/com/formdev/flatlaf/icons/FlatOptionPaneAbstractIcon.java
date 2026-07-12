package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatOptionPaneAbstractIcon.class */
public abstract class FlatOptionPaneAbstractIcon extends FlatAbstractIcon {
    protected final Color foreground;

    protected abstract Shape createOutside();

    protected abstract Shape createInside();

    /* JADX INFO: Access modifiers changed from: protected */
    public FlatOptionPaneAbstractIcon(String colorKey, String defaultColorKey) {
        super(32, 32, FlatUIUtils.getUIColor(colorKey, defaultColorKey));
        this.foreground = UIManager.getColor("OptionPane.icon.foreground");
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        if (this.foreground != null) {
            g.fill(createOutside());
            g.setColor(this.foreground);
            g.fill(createInside());
        } else {
            Path2D.Float r0 = new Path2D.Float(0);
            r0.append(createOutside(), false);
            r0.append(createInside(), false);
            g.fill(r0);
        }
    }
}
