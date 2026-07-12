package com.formdev.flatlaf.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.function.Function;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.TableUI;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTableCellBorder.class */
public class FlatTableCellBorder extends FlatLineBorder {
    protected boolean showCellFocusIndicator;
    private Component c;

    protected FlatTableCellBorder() {
        super(UIManager.getInsets("Table.cellMargins"), UIManager.getColor("Table.cellFocusColor"));
        this.showCellFocusIndicator = UIManager.getBoolean("Table.showCellFocusIndicator");
    }

    @Override // com.formdev.flatlaf.ui.FlatEmptyBorder
    public Insets getBorderInsets(Component c, Insets insets) {
        Insets m = (Insets) getStyleFromTableUI(c, ui -> {
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
        if (this.c != null && (color = (Color) getStyleFromTableUI(this.c, ui -> {
            return ui.cellFocusColor;
        })) != null) {
            return color;
        }
        return super.getLineColor();
    }

    @Override // com.formdev.flatlaf.ui.FlatLineBorder
    public int getArc() {
        Integer selectionArc;
        if (this.c != null && (selectionArc = (Integer) getStyleFromTableUI(this.c, ui -> {
            return Integer.valueOf(ui.selectionArc);
        })) != null) {
            return selectionArc.intValue();
        }
        return super.getArc();
    }

    @Override // com.formdev.flatlaf.ui.FlatLineBorder
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Insets selectionInsets;
        if (c != null && (selectionInsets = (Insets) getStyleFromTableUI(c, ui -> {
            return ui.selectionInsets;
        })) != null) {
            x += selectionInsets.left;
            y += selectionInsets.top;
            width -= selectionInsets.left + selectionInsets.right;
            height -= selectionInsets.top + selectionInsets.bottom;
        }
        this.c = c;
        super.paintBorder(c, g, x, y, width, height);
        this.c = null;
    }

    static <T> T getStyleFromTableUI(Component c, Function<FlatTableUI, T> f) {
        JTable table = SwingUtilities.getAncestorOfClass(JTable.class, c);
        if (table != null) {
            TableUI ui = table.getUI();
            if (ui instanceof FlatTableUI) {
                return f.apply((FlatTableUI) ui);
            }
            return null;
        }
        return null;
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTableCellBorder$Default.class */
    public static class Default extends FlatTableCellBorder {
        @Override // com.formdev.flatlaf.ui.FlatTableCellBorder, com.formdev.flatlaf.ui.FlatLineBorder
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTableCellBorder$Focused.class */
    public static class Focused extends FlatTableCellBorder {
        @Override // com.formdev.flatlaf.ui.FlatTableCellBorder, com.formdev.flatlaf.ui.FlatLineBorder
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            JTable table;
            Border border;
            if (c != null && c.getClass().getName().equals("javax.swing.JTable$BooleanRenderer") && (table = SwingUtilities.getAncestorOfClass(JTable.class, c)) != null && c.getForeground() == table.getSelectionForeground() && c.getBackground() == table.getSelectionBackground() && (border = UIManager.getBorder("Table.focusSelectedCellHighlightBorder")) != null) {
                border.paintBorder(c, g, x, y, width, height);
            } else {
                super.paintBorder(c, g, x, y, width, height);
            }
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTableCellBorder$Selected.class */
    public static class Selected extends FlatTableCellBorder {
        public int maxCheckCellsEditable = 50;

        @Override // com.formdev.flatlaf.ui.FlatTableCellBorder, com.formdev.flatlaf.ui.FlatLineBorder
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            JTable table;
            Boolean b = (Boolean) getStyleFromTableUI(c, ui -> {
                return ui.showCellFocusIndicator;
            });
            boolean showCellFocusIndicator = b != null ? b.booleanValue() : this.showCellFocusIndicator;
            if (!showCellFocusIndicator && (table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, c)) != null && !shouldShowCellFocusIndicator(table)) {
                return;
            }
            super.paintBorder(c, g, x, y, width, height);
        }

        protected boolean shouldShowCellFocusIndicator(JTable table) {
            boolean rowSelectionAllowed = table.getRowSelectionAllowed();
            boolean columnSelectionAllowed = table.getColumnSelectionAllowed();
            if (rowSelectionAllowed && columnSelectionAllowed) {
                return false;
            }
            if (rowSelectionAllowed) {
                if (table.getSelectedRowCount() != 1) {
                    return false;
                }
                int columnCount = table.getColumnCount();
                if (columnCount > this.maxCheckCellsEditable) {
                    return true;
                }
                int selectedRow = table.getSelectedRow();
                for (int column = 0; column < columnCount; column++) {
                    if (table.isCellEditable(selectedRow, column)) {
                        return true;
                    }
                }
                return false;
            }
            if (!columnSelectionAllowed || table.getSelectedColumnCount() != 1) {
                return false;
            }
            int rowCount = table.getRowCount();
            if (rowCount > this.maxCheckCellsEditable) {
                return true;
            }
            int selectedColumn = table.getSelectedColumn();
            for (int row = 0; row < rowCount; row++) {
                if (table.isCellEditable(row, selectedColumn)) {
                    return true;
                }
            }
            return false;
        }
    }
}
