package com.formdev.flatlaf.icons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatFileChooserNewFolderIcon.class */
public class FlatFileChooserNewFolderIcon extends FlatAbstractIcon {
    private final Color greenColor;

    public FlatFileChooserNewFolderIcon() {
        super(16, 16, UIManager.getColor("Actions.Grey"));
        this.greenColor = UIManager.getColor("Actions.Green");
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setStroke(new BasicStroke(1.0f, 1, 1));
        g.draw(FlatFileViewDirectoryIcon.createFolderPath());
        g.setColor(this.greenColor);
        g.draw(new Line2D.Float(5.5f, 9.0f, 10.5f, 9.0f));
        g.draw(new Line2D.Float(8.0f, 6.5f, 8.0f, 11.5f));
    }
}
