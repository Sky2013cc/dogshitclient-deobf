package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.util.Map;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatPopupMenuBorder.class */
public class FlatPopupMenuBorder extends FlatLineBorder implements FlatStylingSupport.StyleableBorder {
    private Color borderColor;

    public FlatPopupMenuBorder() {
        super(UIManager.getInsets("PopupMenu.borderInsets"), UIManager.getColor("PopupMenu.borderColor"));
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableBorder
    public Object applyStyleProperty(String key, Object value) {
        boolean z = -1;
        switch (key.hashCode()) {
            case 722830999:
                if (key.equals("borderColor")) {
                    z = true;
                    break;
                }
                break;
            case 1103974978:
                if (key.equals("borderInsets")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return applyStyleProperty((Insets) value);
            case true:
                Object oldValue = getLineColor();
                this.borderColor = (Color) value;
                return oldValue;
            default:
                throw new FlatStylingSupport.UnknownStyleException(key);
        }
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableBorder
    public Map<String, Class<?>> getStyleableInfos() {
        Map<String, Class<?>> infos = new FlatStylingSupport.StyleableInfosMap<>();
        infos.put("borderInsets", Insets.class);
        infos.put("borderColor", Color.class);
        return infos;
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableBorder
    public Object getStyleableValue(String key) {
        boolean z = -1;
        switch (key.hashCode()) {
            case 722830999:
                if (key.equals("borderColor")) {
                    z = true;
                    break;
                }
                break;
            case 1103974978:
                if (key.equals("borderInsets")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return getStyleableValue();
            case true:
                return this.borderColor;
            default:
                return null;
        }
    }

    @Override // com.formdev.flatlaf.ui.FlatLineBorder
    public Color getLineColor() {
        return this.borderColor != null ? this.borderColor : super.getLineColor();
    }

    @Override // com.formdev.flatlaf.ui.FlatEmptyBorder
    public Insets getBorderInsets(Component c, Insets insets) {
        if ((c instanceof Container) && ((Container) c).getComponentCount() > 0 && (((Container) c).getComponent(0) instanceof JScrollPane)) {
            int scale = UIScale.scale(1);
            insets.bottom = scale;
            insets.right = scale;
            insets.top = scale;
            insets.left = scale;
            return insets;
        }
        return super.getBorderInsets(c, insets);
    }
}
