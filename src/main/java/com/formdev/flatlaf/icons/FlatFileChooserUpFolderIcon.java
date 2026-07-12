package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatFileChooserUpFolderIcon.class */
public class FlatFileChooserUpFolderIcon extends FlatAbstractIcon {
    private final Color blueColor;

    public FlatFileChooserUpFolderIcon() {
        super(16, 16, UIManager.getColor("Actions.Grey"));
        this.blueColor = UIManager.getColor("Actions.Blue");
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setStroke(new BasicStroke(1.0f, 1, 1));
        g.draw(FlatFileViewDirectoryIcon.createFolderPath());
        g.setColor(this.blueColor);
        g.draw(new Line2D.Float(8.0f, 6.5f, 8.0f, 11.5f));
        g.draw(FlatUIUtils.createPath(false, 5.5d, 9.0d, 8.0d, 6.5d, 10.5d, 9.0d));
    }
}
