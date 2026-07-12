package com.formdev.flatlaf.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.function.Function;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ListUI;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatListCellBorder.class */
public class FlatListCellBorder extends FlatLineBorder {
    protected boolean showCellFocusIndicator;
    private Component c;

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatListCellBorder$Focused.class */
    public static class Focused extends FlatListCellBorder {
    }

    protected FlatListCellBorder() {
        super(UIManager.getInsets("List.cellMargins"), UIManager.getColor("List.cellFocusColor"));
        this.showCellFocusIndicator = UIManager.getBoolean("List.showCellFocusIndicator");
    }

    @Override // com.formdev.flatlaf.ui.FlatEmptyBorder
    public Insets getBorderInsets(Component c, Insets insets) {
        Insets m = (Insets) getStyleFromListUI(c, ui -> {
            return ui.cellMargins;
        });
        if (m != null) {
            return scaleInsets(c, insets, m.top, m.left, m.bottom, m.right);
        }
        return super.getBorderInsets(c, insets);
    }

    @Override // com.formdev.flatlaf.ui.FlatLineBorder
    public Color getLineColor() {
        Color color;
        if (this.c != null && (color = (Color) getStyleFromListUI(this.c, ui -> {
            return ui.cellFocusColor;
        })) != null) {
            return color;
        }
        return super.getLineColor();
    }

    @Override // com.formdev.flatlaf.ui.FlatLineBorder
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        this.c = c;
        super.paintBorder(c, g, x, y, width, height);
        this.c = null;
    }

    static <T> T getStyleFromListUI(Component c, Function<FlatListUI, T> f) {
        JList<?> list = SwingUtilities.getAncestorOfClass(JList.class, c);
        if (list != null) {
            ListUI ui = list.getUI();
            if (ui instanceof FlatListUI) {
                return f.apply((FlatListUI) ui);
            }
            return null;
        }
        return null;
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatListCellBorder$Default.class */
    public static class Default extends FlatListCellBorder {
        @Override // com.formdev.flatlaf.ui.FlatListCellBorder, com.formdev.flatlaf.ui.FlatLineBorder
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatListCellBorder$Selected.class */
    public static class Selected extends FlatListCellBorder {
        @Override // com.formdev.flatlaf.ui.FlatListCellBorder, com.formdev.flatlaf.ui.FlatLineBorder
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Boolean b = (Boolean) getStyleFromListUI(c, ui -> {
                return ui.showCellFocusIndicator;
            });
            boolean showCellFocusIndicator = b != null ? b.booleanValue() : this.showCellFocusIndicator;
            if (!showCellFocusIndicator) {
                return;
            }
            JList<?> list = SwingUtilities.getAncestorOfClass(JList.class, c);
            if (list != null && list.getMinSelectionIndex() == list.getMaxSelectionIndex()) {
                return;
            }
            super.paintBorder(c, g, x, y, width, height);
        }
    }
}
