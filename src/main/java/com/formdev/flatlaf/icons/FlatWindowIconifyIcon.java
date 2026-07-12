package com.formdev.flatlaf.icons;

import java.awt.Graphics2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatWindowIconifyIcon.class */
public class FlatWindowIconifyIcon extends FlatWindowAbstractIcon {
    public FlatWindowIconifyIcon() {
        this(null);
    }

    public FlatWindowIconifyIcon(String windowStyle) {
        super(windowStyle);
    }

    @Override // com.formdev.flatlaf.icons.FlatWindowAbstractIcon
    protected void paintIconAt1x(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
        int iw = (int) (getSymbolHeight() * scaleFactor);
        int ih = (int) scaleFactor;
        int ix = x + ((width - iw) / 2);
        int iy = y + ((height - ih) / 2);
        g.fillRect(ix, iy, iw, ih);
    }
}
