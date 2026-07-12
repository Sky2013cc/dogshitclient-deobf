package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.util.ColorFunctions;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatTreeLeafIcon.class */
public class FlatTreeLeafIcon extends FlatAbstractIcon {
    public FlatTreeLeafIcon() {
        super(16, 16, UIManager.getColor("Tree.icon.leafColor"));
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        FlatTreeCollapsedIcon.setStyleColorFromTreeUI(c, g, ui -> {
            return ui.iconLeafColor;
        });
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setStroke(new BasicStroke(1.0f, 1, 1));
        g.draw(new RoundRectangle2D.Float(2.5f, 1.5f, 11.0f, 13.0f, 3.0f, 3.0f));
        g.setColor(ColorFunctions.fade(g.getColor(), 0.6f));
        g.draw(new Line2D.Float(5.5f, 5.5f, 10.5f, 5.5f));
        g.draw(new Line2D.Float(5.5f, 8.0f, 10.5f, 8.0f));
        g.draw(new Line2D.Float(5.5f, 10.5f, 10.5f, 10.5f));
    }
}
