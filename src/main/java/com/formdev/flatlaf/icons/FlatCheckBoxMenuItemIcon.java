package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatStylingSupport;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatCheckBoxMenuItemIcon.class */
public class FlatCheckBoxMenuItemIcon extends FlatAbstractIcon {

    @FlatStylingSupport.Styleable
    protected Color checkmarkColor;

    @FlatStylingSupport.Styleable
    protected Color disabledCheckmarkColor;

    @FlatStylingSupport.Styleable
    protected Color selectionForeground;

    public FlatCheckBoxMenuItemIcon() {
        super(15, 15, null);
        this.checkmarkColor = UIManager.getColor("CheckBoxMenuItem.icon.checkmarkColor");
        this.disabledCheckmarkColor = UIManager.getColor("CheckBoxMenuItem.icon.disabledCheckmarkColor");
        this.selectionForeground = UIManager.getColor("MenuItem.selectionForeground");
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
    protected void paintIcon(Component c, Graphics2D g2) {
        boolean selected = (c instanceof AbstractButton) && ((AbstractButton) c).isSelected();
        if (selected) {
            g2.setColor(getCheckmarkColor(c));
            paintCheckmark(g2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void paintCheckmark(Graphics2D g2) {
        Path2D.Float path = new Path2D.Float(1, 3);
        path.moveTo(4.5f, 7.5f);
        path.lineTo(6.6f, 10.0f);
        path.lineTo(11.25f, 3.5f);
        g2.setStroke(new BasicStroke(1.9f, 1, 1));
        g2.draw(path);
    }

    protected Color getCheckmarkColor(Component c) {
        if ((c instanceof JMenuItem) && ((JMenuItem) c).isArmed() && !isUnderlineSelection()) {
            return this.selectionForeground;
        }
        return c.isEnabled() ? this.checkmarkColor : this.disabledCheckmarkColor;
    }

    protected boolean isUnderlineSelection() {
        return "underline".equals(UIManager.getString("MenuItem.selectionType"));
    }
}
