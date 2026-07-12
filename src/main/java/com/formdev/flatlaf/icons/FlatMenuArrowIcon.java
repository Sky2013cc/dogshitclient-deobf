package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatMenuArrowIcon.class */
public class FlatMenuArrowIcon extends FlatAbstractIcon {

    @FlatStylingSupport.Styleable
    protected String arrowType;

    @FlatStylingSupport.Styleable
    protected Color arrowColor;

    @FlatStylingSupport.Styleable
    protected Color disabledArrowColor;

    @FlatStylingSupport.Styleable
    protected Color selectionForeground;

    public FlatMenuArrowIcon() {
        super(6, 10, null);
        this.arrowType = UIManager.getString("Component.arrowType");
        this.arrowColor = UIManager.getColor("Menu.icon.arrowColor");
        this.disabledArrowColor = UIManager.getColor("Menu.icon.disabledArrowColor");
        this.selectionForeground = UIManager.getColor("Menu.selectionForeground");
    }

    public Object applyStyleProperty(String key, Object value) {
        return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
    }

    public Map<String, Class<?>> getStyleableInfos() {
        return FlatStylingSupport.getAnnotatedStyleableInfos(this);
    }

    public Object getStyleableValue(String key) {
        return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        if (c != null && !c.getComponentOrientation().isLeftToRight()) {
            g.rotate(Math.toRadians(180.0d), this.width / 2.0d, this.height / 2.0d);
        }
        g.setColor(getArrowColor(c));
        if (FlatUIUtils.isChevron(this.arrowType)) {
            Path2D path = FlatUIUtils.createPath(false, 1.0d, 1.0d, 5.0d, 5.0d, 1.0d, 9.0d);
            g.setStroke(new BasicStroke(1.0f));
            g.draw(path);
            return;
        }
        g.fill(FlatUIUtils.createPath(0.0d, 0.5d, 5.0d, 5.0d, 0.0d, 9.5d));
    }

    protected Color getArrowColor(Component c) {
        if ((c instanceof JMenu) && ((JMenu) c).isSelected() && !isUnderlineSelection()) {
            return this.selectionForeground;
        }
        return (c == null || c.isEnabled()) ? this.arrowColor : this.disabledArrowColor;
    }

    protected boolean isUnderlineSelection() {
        return "underline".equals(UIManager.getString("MenuItem.selectionType"));
    }
}
