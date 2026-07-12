package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatWindowAbstractIcon.class */
public abstract class FlatWindowAbstractIcon extends FlatAbstractIcon {
    private final int symbolHeight;
    private final Color hoverBackground;
    private final Color pressedBackground;

    protected abstract void paintIconAt1x(Graphics2D graphics2D, int i, int i2, int i3, int i4, double d);

    /* JADX INFO: Access modifiers changed from: protected */
    public FlatWindowAbstractIcon(String windowStyle) {
        this(FlatUIUtils.getSubUIDimension("TitlePane.buttonSize", windowStyle), FlatUIUtils.getSubUIInt("TitlePane.buttonSymbolHeight", windowStyle, 10), FlatUIUtils.getSubUIColor("TitlePane.buttonHoverBackground", windowStyle), FlatUIUtils.getSubUIColor("TitlePane.buttonPressedBackground", windowStyle));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FlatWindowAbstractIcon(Dimension size, int symbolHeight, Color hoverBackground, Color pressedBackground) {
        super(size.width, size.height, null);
        this.symbolHeight = symbolHeight;
        this.hoverBackground = hoverBackground;
        this.pressedBackground = pressedBackground;
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        g.setColor(getForeground(c));
        HiDPIUtils.paintAtScale1x(g, 0, 0, this.width, this.height, this::paintIconAt1x);
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintBackground(Component c, Graphics2D g, int x, int y) {
        Color background = FlatButtonUI.buttonStateColor(c, null, null, null, this.hoverBackground, this.pressedBackground);
        if (background != null) {
            Object oldHint = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g.setColor(FlatUIUtils.deriveColor(background, c.getBackground()));
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldHint);
        }
    }

    protected Color getForeground(Component c) {
        return c.getForeground();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getSymbolHeight() {
        return this.symbolHeight;
    }
}
