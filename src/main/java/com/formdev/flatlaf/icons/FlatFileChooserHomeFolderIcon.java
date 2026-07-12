package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatFileChooserHomeFolderIcon.class */
public class FlatFileChooserHomeFolderIcon extends FlatAbstractIcon {
    public FlatFileChooserHomeFolderIcon() {
        super(16, 16, UIManager.getColor("Actions.Grey"));
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setStroke(new BasicStroke(1.0f, 1, 1));
        g.draw(FlatUIUtils.createPath(false, 6.5d, 13.0d, 6.5d, 9.5d, 9.5d, 9.5d, 9.5d, 13.0d));
        g.draw(FlatUIUtils.createPath(false, 3.5d, 6.5d, 3.5d, 12.5d, -1.000000000002E12d, 3.5d, 13.5d, 4.5d, 13.5d, 11.5d, 13.5d, -1.000000000002E12d, 12.5d, 13.5d, 12.5d, 12.5d, 12.5d, 6.5d));
        g.draw(FlatUIUtils.createPath(false, 1.5d, 8.5d, 8.0d, 2.0d, 14.5d, 8.5d));
    }
}
