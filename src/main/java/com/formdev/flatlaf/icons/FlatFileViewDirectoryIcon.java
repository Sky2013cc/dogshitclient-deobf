package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatFileViewDirectoryIcon.class */
public class FlatFileViewDirectoryIcon extends FlatAbstractIcon {
    private Path2D path;

    public FlatFileViewDirectoryIcon() {
        super(16, 16, UIManager.getColor("Objects.Grey"));
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        if (this.path == null) {
            this.path = createFolderPath();
        }
        g.draw(this.path);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Path2D createFolderPath() {
        return FlatUIUtils.createPath(14.5d, 13.5d - 1.5d, -1.000000000002E12d, 14.5d, 13.5d, 14.5d - 1.5d, 13.5d, 1.5d + 1.5d, 13.5d, -1.000000000002E12d, 1.5d, 13.5d, 1.5d, 13.5d - 1.5d, 1.5d, 2.5d + 1.5d, -1.000000000002E12d, 1.5d, 2.5d, 1.5d + 1.5d, 2.5d, 6.5d - 0.5d, 2.5d, -1.000000000002E12d, 6.5d, 2.5d, 6.5d + 0.5d, 2.5d + 0.5d, 8.5d, 4.5d, 14.5d - 1.5d, 4.5d, -1.000000000002E12d, 14.5d, 4.5d, 14.5d, 4.5d + 1.5d);
    }
}
