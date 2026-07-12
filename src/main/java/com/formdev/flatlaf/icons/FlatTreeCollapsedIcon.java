package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatTreeUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.function.Function;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.TreeUI;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatTreeCollapsedIcon.class */
public class FlatTreeCollapsedIcon extends FlatAbstractIcon {
    private final boolean chevron;
    private Path2D path;

    public FlatTreeCollapsedIcon() {
        this(UIManager.getColor("Tree.icon.collapsedColor"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlatTreeCollapsedIcon(Color color) {
        super(11, 11, color);
        this.chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        setStyleColorFromTreeUI(c, g);
        rotate(c, g);
        String arrowType = (String) getStyleFromTreeUI(c, ui -> {
            return ui.iconArrowType;
        });
        boolean chevron = arrowType != null ? FlatUIUtils.isChevron(arrowType) : this.chevron;
        if (chevron) {
            g.setStroke(new BasicStroke(1.0f, 1, 0));
            if (this.path == null) {
                this.path = FlatUIUtils.createPath(false, 3.5d, 1.5d, 7.5d, 5.5d, 3.5d, 9.5d);
            }
            g.draw(this.path);
            return;
        }
        if (this.path == null) {
            this.path = FlatUIUtils.createPath(2.0d, 1.0d, 2.0d, 10.0d, 10.0d, 5.5d);
        }
        g.fill(this.path);
    }

    void setStyleColorFromTreeUI(Component c, Graphics2D g) {
        setStyleColorFromTreeUI(c, g, ui -> {
            return ui.iconCollapsedColor;
        });
    }

    void rotate(Component c, Graphics2D g) {
        if (!c.getComponentOrientation().isLeftToRight()) {
            g.rotate(Math.toRadians(180.0d), this.width / 2.0d, this.height / 2.0d);
        }
    }

    static <T> T getStyleFromTreeUI(Component c, Function<FlatTreeUI, T> f) {
        JTree ancestorOfClass;
        if (c instanceof JTree) {
            ancestorOfClass = (JTree) c;
        } else {
            ancestorOfClass = SwingUtilities.getAncestorOfClass(JTree.class, c);
        }
        JTree tree = ancestorOfClass;
        if (tree != null) {
            TreeUI ui = tree.getUI();
            if (ui instanceof FlatTreeUI) {
                return f.apply((FlatTreeUI) ui);
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setStyleColorFromTreeUI(Component c, Graphics2D g, Function<FlatTreeUI, Color> f) {
        Color color = (Color) getStyleFromTreeUI(c, f);
        if (color != null) {
            g.setColor(color);
        }
    }
}
