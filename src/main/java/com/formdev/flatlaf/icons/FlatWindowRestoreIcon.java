package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatWindowRestoreIcon.class */
public class FlatWindowRestoreIcon extends FlatWindowAbstractIcon {
    public FlatWindowRestoreIcon() {
        this(null);
    }

    public FlatWindowRestoreIcon(String windowStyle) {
        super(windowStyle);
    }

    @Override // com.formdev.flatlaf.icons.FlatWindowAbstractIcon
    protected void paintIconAt1x(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
        Path2D createRectangle;
        Path2D createRectangle2;
        int iwh = (int) (getSymbolHeight() * scaleFactor);
        int ix = x + ((width - iwh) / 2);
        int iy = y + ((height - iwh) / 2);
        float thickness = SystemInfo.isWindows_11_orLater ? (float) scaleFactor : (int) scaleFactor;
        int arc = Math.max((int) (1.5d * scaleFactor), 2);
        int arcOuter = (int) (arc + (1.5d * scaleFactor));
        int rwh = (int) ((getSymbolHeight() - 2) * scaleFactor);
        int ro2 = iwh - rwh;
        if (SystemInfo.isWindows_11_orLater) {
            createRectangle = FlatUIUtils.createRoundRectangle(ix + ro2, iy, rwh, rwh, thickness, arc, arcOuter, arc, arc);
        } else {
            createRectangle = FlatUIUtils.createRectangle(ix + ro2, iy, rwh, rwh, thickness);
        }
        Path2D r1 = createRectangle;
        if (SystemInfo.isWindows_11_orLater) {
            createRectangle2 = FlatUIUtils.createRoundRectangle(ix, iy + ro2, rwh, rwh, thickness, arc, arc, arc, arc);
        } else {
            createRectangle2 = FlatUIUtils.createRectangle(ix, iy + ro2, rwh, rwh, thickness);
        }
        Path2D r2 = createRectangle2;
        Area area = new Area(r1);
        if (SystemInfo.isWindows_11_orLater) {
            area.subtract(new Area(new Rectangle2D.Float(ix, (float) (iy + scaleFactor), rwh, rwh)));
            area.subtract(new Area(new Rectangle2D.Float((float) (ix + scaleFactor), iy + ro2, rwh, rwh)));
        } else {
            area.subtract(new Area(new Rectangle2D.Float(ix, iy + ro2, rwh, rwh)));
        }
        g.fill(area);
        g.fill(r2);
    }
}
