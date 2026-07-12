package com.formdev.flatlaf.icons;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatFileViewComputerIcon.class */
public class FlatFileViewComputerIcon extends FlatAbstractIcon {
    public FlatFileViewComputerIcon() {
        super(16, 16, UIManager.getColor("Objects.Grey"));
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setStroke(new BasicStroke(1.0f, 1, 1));
        g.draw(new RoundRectangle2D.Float(2.5f, 3.5f, 11.0f, 7.0f, 2.0f, 2.0f));
        g.drawLine(8, 11, 8, 12);
        g.draw(new Line2D.Float(4.5f, 12.5f, 11.5f, 12.5f));
    }
}
