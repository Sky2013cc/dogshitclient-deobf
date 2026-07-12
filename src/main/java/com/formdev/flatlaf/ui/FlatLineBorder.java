package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.LabelUI;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatLineBorder.class */
public class FlatLineBorder extends FlatEmptyBorder {
    private final Color lineColor;
    private final float lineThickness;
    private final int arc;

    public FlatLineBorder(Insets insets, Color lineColor) {
        this(insets, lineColor, 1.0f, -1);
    }

    public FlatLineBorder(Insets insets, Color lineColor, float lineThickness, int arc) {
        super(insets);
        this.lineColor = lineColor;
        this.lineThickness = lineThickness;
        this.arc = arc;
    }

    public FlatLineBorder(Insets insets, int arc) {
        this(insets, null, 0.0f, arc);
    }

    public Color getLineColor() {
        return this.lineColor;
    }

    public float getLineThickness() {
        return this.lineThickness;
    }

    public int getArc() {
        return this.arc;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        LabelUI ui;
        if ((c instanceof JComponent) && ((JComponent) c).getClientProperty("FlatLaf.internal.FlatPopupFactory.popupUsesNativeBorder") != null) {
            return;
        }
        Color lineColor = getLineColor();
        float lineThickness = getLineThickness();
        if (lineColor == null || lineThickness <= 0.0f) {
            return;
        }
        int arc = getArc();
        if (arc < 0) {
            if (c instanceof JLabel) {
                ui = ((JLabel) c).getUI();
            } else {
                ui = c instanceof JPanel ? ((JPanel) c).getUI() : null;
            }
            LabelUI labelUI = ui;
            if (labelUI instanceof FlatLabelUI) {
                arc = ((FlatLabelUI) labelUI).arc;
            } else if (labelUI instanceof FlatPanelUI) {
                arc = ((FlatPanelUI) labelUI).arc;
            }
            if (arc < 0) {
                arc = 0;
            }
        }
        Graphics2D g2 = g.create();
        try {
            FlatUIUtils.setRenderingHints(g2);
            FlatUIUtils.paintOutlinedComponent(g2, x, y, width, height, 0.0f, 0.0f, 0.0f, UIScale.scale(lineThickness), UIScale.scale(arc), null, lineColor, null);
            g2.dispose();
        } catch (Throwable th) {
            g2.dispose();
            throw th;
        }
    }
}
