package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.plaf.UIResource;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatAbstractIcon.class */
public abstract class FlatAbstractIcon implements Icon, UIResource {
    protected final int width;
    protected final int height;
    protected Color color;

    protected abstract void paintIcon(Component component, Graphics2D graphics2D);

    public FlatAbstractIcon(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            FlatUIUtils.setRenderingHints(g2);
            paintBackground(c, g2, x, y);
            g2.translate(x, y);
            UIScale.scaleGraphics(g2);
            if (this.color != null) {
                g2.setColor(this.color);
            }
            paintIcon(c, g2);
            g2.dispose();
        } catch (Throwable th) {
            g2.dispose();
            throw th;
        }
    }

    protected void paintBackground(Component c, Graphics2D g, int x, int y) {
    }

    public int getIconWidth() {
        return UIScale.scale(this.width);
    }

    public int getIconHeight() {
        return UIScale.scale(this.height);
    }
}
