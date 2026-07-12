package com.formdev.flatlaf.icons;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatRevealIcon.class */
public class FlatRevealIcon extends FlatAbstractIcon {
    public FlatRevealIcon() {
        super(16, 16, UIManager.getColor("PasswordField.revealIconColor"));
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        Path2D.Float r0 = new Path2D.Float(0);
        r0.append(new Ellipse2D.Float(5.15f, 6.15f, 5.7f, 5.7f), false);
        r0.append(new Ellipse2D.Float(6.0f, 7.0f, 4.0f, 4.0f), false);
        g.fill(r0);
        Path2D.Float r02 = new Path2D.Float(0);
        r02.append(new Ellipse2D.Float(2.15f, 4.15f, 11.7f, 11.7f), false);
        r02.append(new Ellipse2D.Float(3.0f, 5.0f, 10.0f, 10.0f), false);
        Area area = new Area(r02);
        area.subtract(new Area(new Rectangle2D.Float(0.0f, 9.5f, 16.0f, 16.0f)));
        g.fill(area);
    }
}
