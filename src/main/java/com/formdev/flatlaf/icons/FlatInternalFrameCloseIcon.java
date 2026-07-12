package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatButtonUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatInternalFrameCloseIcon.class */
public class FlatInternalFrameCloseIcon extends FlatInternalFrameAbstractIcon {
    private final Color hoverForeground;
    private final Color pressedForeground;

    public FlatInternalFrameCloseIcon() {
        super(UIManager.getDimension("InternalFrame.buttonSize"), UIManager.getColor("InternalFrame.closeHoverBackground"), UIManager.getColor("InternalFrame.closePressedBackground"));
        this.hoverForeground = UIManager.getColor("InternalFrame.closeHoverForeground");
        this.pressedForeground = UIManager.getColor("InternalFrame.closePressedForeground");
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        paintBackground(c, g);
        g.setColor(FlatButtonUI.buttonStateColor(c, c.getForeground(), null, null, this.hoverForeground, this.pressedForeground));
        float mx = this.width / 2.0f;
        float my = this.height / 2.0f;
        Path2D.Float r0 = new Path2D.Float(0, 4);
        r0.moveTo(mx - 3.25f, my - 3.25f);
        r0.lineTo(mx + 3.25f, my + 3.25f);
        r0.moveTo(mx - 3.25f, my + 3.25f);
        r0.lineTo(mx + 3.25f, my - 3.25f);
        g.setStroke(new BasicStroke(1.0f));
        g.draw(r0);
    }
}
