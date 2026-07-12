package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatStylingSupport;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatRadioButtonIcon.class */
public class FlatRadioButtonIcon extends FlatCheckBoxIcon {

    @FlatStylingSupport.Styleable
    protected float centerDiameter = getUIFloat("RadioButton.icon.centerDiameter", 8.0f, this.style);

    @Override // com.formdev.flatlaf.icons.FlatCheckBoxIcon
    protected String getPropertyPrefix() {
        return "RadioButton.";
    }

    @Override // com.formdev.flatlaf.icons.FlatCheckBoxIcon
    protected void paintFocusBorder(Component c, Graphics2D g) {
        float wh = 15.0f + (this.focusWidth * 2.0f);
        g.fill(new Ellipse2D.Float(-this.focusWidth, -this.focusWidth, wh, wh));
    }

    @Override // com.formdev.flatlaf.icons.FlatCheckBoxIcon
    protected void paintBorder(Component c, Graphics2D g, float borderWidth) {
        if (borderWidth == 0.0f) {
            return;
        }
        g.fillOval(0, 0, 15, 15);
    }

    @Override // com.formdev.flatlaf.icons.FlatCheckBoxIcon
    protected void paintBackground(Component c, Graphics2D g, float borderWidth) {
        float wh = 15.0f - (borderWidth * 2.0f);
        g.fill(new Ellipse2D.Float(borderWidth, borderWidth, wh, wh));
    }

    @Override // com.formdev.flatlaf.icons.FlatCheckBoxIcon
    protected void paintCheckmark(Component c, Graphics2D g) {
        float xy = (15.0f - this.centerDiameter) / 2.0f;
        g.fill(new Ellipse2D.Float(xy, xy, this.centerDiameter, this.centerDiameter));
    }
}
