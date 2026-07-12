package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatTableHeaderUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatAscendingSortIcon.class */
public class FlatAscendingSortIcon extends FlatAbstractIcon {
    protected boolean chevron;
    protected Color sortIconColor;

    public FlatAscendingSortIcon() {
        super(10, 5, null);
        this.chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));
        this.sortIconColor = UIManager.getColor("Table.sortIconColor");
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        boolean chevron = this.chevron;
        Color sortIconColor = this.sortIconColor;
        JTableHeader tableHeader = SwingUtilities.getAncestorOfClass(JTableHeader.class, c);
        if (tableHeader != null) {
            FlatTableHeaderUI ui = tableHeader.getUI();
            if (ui instanceof FlatTableHeaderUI) {
                FlatTableHeaderUI fui = ui;
                if (fui.arrowType != null) {
                    chevron = FlatUIUtils.isChevron(fui.arrowType);
                }
                if (fui.sortIconColor != null) {
                    sortIconColor = fui.sortIconColor;
                }
            }
        }
        g.setColor(sortIconColor);
        paintArrow(c, g, chevron);
    }

    protected void paintArrow(Component c, Graphics2D g, boolean chevron) {
        if (chevron) {
            Path2D path = FlatUIUtils.createPath(false, 1.0d, 4.0d, 5.0d, 0.0d, 9.0d, 4.0d);
            g.setStroke(new BasicStroke(1.0f));
            g.draw(path);
            return;
        }
        g.fill(FlatUIUtils.createPath(0.5d, 5.0d, 5.0d, 0.0d, 9.5d, 5.0d));
    }
}
