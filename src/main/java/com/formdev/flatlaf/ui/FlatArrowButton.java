package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicArrowButton;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatArrowButton.class */
public class FlatArrowButton extends BasicArrowButton implements UIResource {
    public static final int DEFAULT_ARROW_WIDTH = 9;
    protected boolean chevron;
    protected Color foreground;
    protected Color disabledForeground;
    protected Color hoverForeground;
    protected Color hoverBackground;
    protected Color pressedForeground;
    protected Color pressedBackground;
    private int arrowWidth;
    private float arrowThickness;
    private float xOffset;
    private float yOffset;
    private boolean roundBorderAutoXOffset;
    private boolean hover;
    private boolean pressed;

    public FlatArrowButton(int direction, String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
        super(direction, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        this.arrowWidth = 9;
        this.arrowThickness = 1.0f;
        this.xOffset = 0.0f;
        this.yOffset = 0.0f;
        this.roundBorderAutoXOffset = true;
        updateStyle(type, foreground, disabledForeground, hoverForeground, hoverBackground, pressedForeground, pressedBackground);
        setOpaque(false);
        setBorder(null);
        if (hoverForeground != null || hoverBackground != null || pressedForeground != null || pressedBackground != null) {
            addMouseListener(new MouseAdapter() { // from class: com.formdev.flatlaf.ui.FlatArrowButton.1
                public void mouseEntered(MouseEvent e) {
                    FlatArrowButton.this.hover = true;
                    FlatArrowButton.this.repaint();
                }

                public void mouseExited(MouseEvent e) {
                    FlatArrowButton.this.hover = false;
                    FlatArrowButton.this.repaint();
                }

                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        FlatArrowButton.this.pressed = true;
                        FlatArrowButton.this.repaint();
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        FlatArrowButton.this.pressed = false;
                        FlatArrowButton.this.repaint();
                    }
                }
            });
        }
    }

    public void updateStyle(String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
        this.chevron = FlatUIUtils.isChevron(type);
        this.foreground = foreground;
        this.disabledForeground = disabledForeground;
        this.hoverForeground = hoverForeground;
        this.hoverBackground = hoverBackground;
        this.pressedForeground = pressedForeground;
        this.pressedBackground = pressedBackground;
    }

    public int getArrowWidth() {
        return this.arrowWidth;
    }

    public void setArrowWidth(int arrowWidth) {
        this.arrowWidth = arrowWidth;
    }

    public float getArrowThickness() {
        return this.arrowThickness;
    }

    public void setArrowThickness(float arrowThickness) {
        this.arrowThickness = arrowThickness;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isHover() {
        return this.hover;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isPressed() {
        return this.pressed;
    }

    public float getXOffset() {
        return this.xOffset;
    }

    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getYOffset() {
        return this.yOffset;
    }

    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public boolean isRoundBorderAutoXOffset() {
        return this.roundBorderAutoXOffset;
    }

    public void setRoundBorderAutoXOffset(boolean roundBorderAutoXOffset) {
        this.roundBorderAutoXOffset = roundBorderAutoXOffset;
    }

    protected Color deriveBackground(Color background) {
        return background;
    }

    protected Color deriveForeground(Color foreground) {
        return FlatUIUtils.deriveColor(foreground, this.foreground);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Color getArrowColor() {
        if (isEnabled()) {
            if (this.pressedForeground != null && isPressed()) {
                return this.pressedForeground;
            }
            if (this.hoverForeground != null && isHover()) {
                return this.hoverForeground;
            }
            return this.foreground;
        }
        return this.disabledForeground;
    }

    public Dimension getPreferredSize() {
        return UIScale.scale(super.getPreferredSize());
    }

    public Dimension getMinimumSize() {
        return UIScale.scale(super.getMinimumSize());
    }

    public void paint(Graphics g) {
        Color color;
        Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
        if (isEnabled()) {
            if (this.pressedBackground != null && isPressed()) {
                color = this.pressedBackground;
            } else if (this.hoverBackground != null && isHover()) {
                color = this.hoverBackground;
            } else {
                color = null;
            }
            Color background = color;
            if (background != null) {
                g.setColor(deriveBackground(background));
                paintBackground((Graphics2D) g);
            }
        }
        g.setColor(deriveForeground(getArrowColor()));
        paintArrow((Graphics2D) g);
        FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
    }

    protected void paintBackground(Graphics2D g) {
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void paintArrow(Graphics2D g) {
        int x = 0;
        if (isRoundBorderAutoXOffset()) {
            JComponent parent = getParent();
            boolean vert = this.direction == 1 || this.direction == 5;
            if (vert && (parent instanceof JComponent) && FlatUIUtils.hasRoundBorder(parent)) {
                x = 0 - UIScale.scale(parent.getComponentOrientation().isLeftToRight() ? 1 : -1);
            }
        }
        FlatUIUtils.paintArrow(g, x, 0, getWidth(), getHeight(), getDirection(), this.chevron, getArrowWidth(), getArrowThickness(), getXOffset(), getYOffset());
    }
}
