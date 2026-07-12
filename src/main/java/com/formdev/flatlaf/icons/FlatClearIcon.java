package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatClearIcon.class */
public class FlatClearIcon extends FlatAbstractIcon {

    @FlatStylingSupport.Styleable
    protected Color clearIconColor;

    @FlatStylingSupport.Styleable
    protected Color clearIconHoverColor;

    @FlatStylingSupport.Styleable
    protected Color clearIconPressedColor;
    private final boolean ignoreButtonState;

    public FlatClearIcon() {
        this(false);
    }

    public FlatClearIcon(boolean ignoreButtonState) {
        super(16, 16, null);
        this.clearIconColor = UIManager.getColor("SearchField.clearIconColor");
        this.clearIconHoverColor = UIManager.getColor("SearchField.clearIconHoverColor");
        this.clearIconPressedColor = UIManager.getColor("SearchField.clearIconPressedColor");
        this.ignoreButtonState = ignoreButtonState;
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
        if (!this.ignoreButtonState && (c instanceof AbstractButton)) {
            ButtonModel model = ((AbstractButton) c).getModel();
            if (model.isPressed() || model.isRollover()) {
                g.setColor(model.isPressed() ? this.clearIconPressedColor : this.clearIconHoverColor);
                Path2D.Float r0 = new Path2D.Float(0);
                r0.append(new Ellipse2D.Float(1.75f, 1.75f, 12.5f, 12.5f), false);
                r0.append(FlatUIUtils.createPath(4.5d, 5.5d, 5.5d, 4.5d, 8.0d, 7.0d, 10.5d, 4.5d, 11.5d, 5.5d, 9.0d, 8.0d, 11.5d, 10.5d, 10.5d, 11.5d, 8.0d, 9.0d, 5.5d, 11.5d, 4.5d, 10.5d, 7.0d, 8.0d), false);
                g.fill(r0);
                return;
            }
        }
        g.setColor(this.clearIconColor);
        Path2D.Float r02 = new Path2D.Float(0, 4);
        r02.moveTo(5.0d, 5.0d);
        r02.lineTo(11.0d, 11.0d);
        r02.moveTo(5.0d, 11.0d);
        r02.lineTo(11.0d, 5.0d);
        g.draw(r02);
    }
}
