package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatWindowMaximizeIcon.class */
public class FlatWindowMaximizeIcon extends FlatWindowAbstractIcon {
    public FlatWindowMaximizeIcon() {
        this(null);
    }

    public FlatWindowMaximizeIcon(String windowStyle) {
        super(windowStyle);
    }

    @Override // com.formdev.flatlaf.icons.FlatWindowAbstractIcon
    protected void paintIconAt1x(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
        Path2D createRectangle;
        int iwh = (int) (getSymbolHeight() * scaleFactor);
        int ix = x + ((width - iwh) / 2);
        int iy = y + ((height - iwh) / 2);
        float thickness = SystemInfo.isWindows_11_orLater ? (float) scaleFactor : (int) scaleFactor;
        int arc = Math.max((int) (1.5d * scaleFactor), 2);
        if (SystemInfo.isWindows_11_orLater) {
            createRectangle = FlatUIUtils.createRoundRectangle(ix, iy, iwh, iwh, thickness, arc, arc, arc, arc);
        } else {
            createRectangle = FlatUIUtils.createRectangle(ix, iy, iwh, iwh, thickness);
        }
        g.fill(createRectangle);
    }
}
