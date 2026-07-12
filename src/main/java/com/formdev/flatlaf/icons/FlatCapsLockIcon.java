package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatCapsLockIcon.class */
public class FlatCapsLockIcon extends FlatAbstractIcon {
    private Path2D path;

    public FlatCapsLockIcon() {
        super(16, 16, UIManager.getColor("PasswordField.capsLockIconColor"));
    }

    public Object applyStyleProperty(String key, Object value) {
        boolean z = -1;
        switch (key.hashCode()) {
            case 1739263006:
                if (key.equals("capsLockIconColor")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                Object oldValue = this.color;
                this.color = (Color) value;
                return oldValue;
            default:
                throw new FlatStylingSupport.UnknownStyleException(key);
        }
    }

    public Object getStyleableValue(String key) {
        boolean z = -1;
        switch (key.hashCode()) {
            case 1739263006:
                if (key.equals("capsLockIconColor")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return this.color;
            default:
                return null;
        }
    }

    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    protected void paintIcon(Component c, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        BasicStroke stroke = new BasicStroke(1.0f, 2, 1);
        if (this.path == null) {
            this.path = new Path2D.Float(0);
            this.path.append(new RoundRectangle2D.Float(0.0f, 0.0f, 16.0f, 16.0f, 6.0f, 6.0f), false);
            this.path.append(new Area(stroke.createStrokedShape(new Rectangle2D.Float(5.5f, 11.5f, 5.0f, 2.0f))), false);
            this.path.append(new Area(stroke.createStrokedShape(FlatUIUtils.createPath(2.5d, 7.5d, 8.0d, 2.0d, 13.5d, 7.5d, 10.5d, 7.5d, 10.5d, 9.5d, 5.5d, 9.5d, 5.5d, 7.5d, 2.5d, 7.5d))), false);
        }
        g.fill(this.path);
    }
}
