package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatFileViewFileIcon.class */
public class FlatFileViewFileIcon extends FlatAbstractIcon {
    private Path2D path;

    public FlatFileViewFileIcon() {
        super(16, 16, UIManager.getColor("Objects.Grey"));
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setStroke(new BasicStroke(1.0f, 1, 1));
        if (this.path == null) {
            this.path = FlatUIUtils.createPath(false, 2.5d, 1.5d + 1.5d, -1.000000000002E12d, 2.5d, 1.5d, 2.5d + 1.5d, 1.5d, 8.8d, 1.5d, 13.5d, 6.2d, 13.5d, 14.5d - 1.5d, -1.000000000002E12d, 13.5d, 14.5d, 13.5d - 1.5d, 14.5d, 2.5d + 1.5d, 14.5d, -1.000000000002E12d, 2.5d, 14.5d, 2.5d, 14.5d - 1.5d, -1.000000000005E12d, -1.000000000001E12d, 8.5d, 2.0d, 8.5d, 6.5d - 1.5d, -1.000000000002E12d, 8.5d, 6.5d, 8.5d + 1.5d, 6.5d, 13.0d, 6.5d);
        }
        g.draw(this.path);
    }
}
