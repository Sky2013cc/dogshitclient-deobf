package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatFileViewFloppyDriveIcon.class */
public class FlatFileViewFloppyDriveIcon extends FlatAbstractIcon {
    public FlatFileViewFloppyDriveIcon() {
        super(16, 16, UIManager.getColor("Objects.Grey"));
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setStroke(new BasicStroke(1.0f, 1, 1));
        g.draw(FlatUIUtils.createPath(3.5d, 2.5d, 11.5d, 2.5d, 11.5d, 2.5d, 13.5d, 4.5d, 13.5d, 12.5d, -1.000000000002E12d, 13.5d, 13.5d, 12.5d, 13.5d, 3.5d, 13.5d, -1.000000000002E12d, 2.5d, 13.5d, 2.5d, 12.5d, 2.5d, 3.5d, -1.000000000002E12d, 2.5d, 2.5d, 3.5d, 2.5d));
        g.draw(FlatUIUtils.createPath(false, 4.5d, 13.0d, 4.5d, 9.5d, 11.5d, 9.5d, 11.5d, 13.0d));
        g.draw(FlatUIUtils.createPath(false, 5.5d, 3.0d, 5.5d, 5.5d, 10.5d, 5.5d, 10.5d, 3.0d));
    }
}
