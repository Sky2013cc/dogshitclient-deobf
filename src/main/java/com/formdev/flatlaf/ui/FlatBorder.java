package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicBorders;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatBorder.class */
public class FlatBorder extends BasicBorders.MarginBorder implements FlatStylingSupport.StyleableBorder {

    @FlatStylingSupport.Styleable
    protected int focusWidth = UIManager.getInt("Component.focusWidth");

    @FlatStylingSupport.Styleable
    protected float innerFocusWidth = FlatUIUtils.getUIFloat("Component.innerFocusWidth", 0.0f);

    @FlatStylingSupport.Styleable
    protected float innerOutlineWidth = FlatUIUtils.getUIFloat("Component.innerOutlineWidth", 0.0f);

    @FlatStylingSupport.Styleable
    protected float borderWidth = FlatUIUtils.getUIFloat("Component.borderWidth", 1.0f);

    @FlatStylingSupport.Styleable
    protected Color focusColor = UIManager.getColor("Component.focusColor");

    @FlatStylingSupport.Styleable
    protected Color borderColor = UIManager.getColor("Component.borderColor");

    @FlatStylingSupport.Styleable
    protected Color disabledBorderColor = UIManager.getColor("Component.disabledBorderColor");

    @FlatStylingSupport.Styleable
    protected Color focusedBorderColor = UIManager.getColor("Component.focusedBorderColor");

    @FlatStylingSupport.Styleable(dot = true)
    protected Color errorBorderColor = UIManager.getColor("Component.error.borderColor");

    @FlatStylingSupport.Styleable(dot = true)
    protected Color errorFocusedBorderColor = UIManager.getColor("Component.error.focusedBorderColor");

    @FlatStylingSupport.Styleable(dot = true)
    protected Color warningBorderColor = UIManager.getColor("Component.warning.borderColor");

    @FlatStylingSupport.Styleable(dot = true)
    protected Color warningFocusedBorderColor = UIManager.getColor("Component.warning.focusedBorderColor");

    @FlatStylingSupport.Styleable(dot = true)
    protected Color customBorderColor = UIManager.getColor("Component.custom.borderColor");

    @FlatStylingSupport.Styleable
    protected String outline;

    @FlatStylingSupport.Styleable
    protected Color outlineColor;

    @FlatStylingSupport.Styleable
    protected Color outlineFocusedColor;

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableBorder
    public Object applyStyleProperty(String key, Object value) {
        return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableBorder
    public Map<String, Class<?>> getStyleableInfos() {
        return FlatStylingSupport.getAnnotatedStyleableInfos(this);
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableBorder
    public Object getStyleableValue(String key) {
        return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        float f;
        Graphics2D g2 = g.create();
        try {
            FlatUIUtils.setRenderingHints(g2);
            float focusWidth = UIScale.scale(getFocusWidth(c));
            float focusInnerWidth = 0.0f;
            float borderWidth = UIScale.scale(getBorderWidth(c));
            float arc = UIScale.scale(getArc(c));
            Paint outlineColor = getOutlineColor(c);
            Paint paint = null;
            if (outlineColor != null || isFocused(c)) {
                if (!isCellEditor(c) && !(c instanceof JScrollPane)) {
                    f = outlineColor != null ? this.innerOutlineWidth : getInnerFocusWidth(c);
                } else {
                    f = 0.0f;
                }
                float innerWidth = f;
                if (focusWidth > 0.0f || innerWidth > 0.0f) {
                    paint = outlineColor != null ? outlineColor : getFocusColor(c);
                    focusInnerWidth = borderWidth + UIScale.scale(innerWidth);
                }
            }
            Paint borderColor = outlineColor != null ? outlineColor : getBorderColor(c);
            FlatUIUtils.paintOutlinedComponent(g2, x, y, width, height, focusWidth, 1.0f, focusInnerWidth, borderWidth, arc, paint, borderColor, null, c instanceof JScrollPane);
            g2.dispose();
        } catch (Throwable th) {
            g2.dispose();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Color getOutlineColor(Component c) {
        if (!(c instanceof JComponent)) {
            return null;
        }
        Object outline = ((JComponent) c).getClientProperty(FlatClientProperties.OUTLINE);
        if (outline == null) {
            outline = this.outline;
        }
        if (outline == null) {
            if (this.outlineColor != null && this.outlineFocusedColor != null) {
                outline = new Color[]{this.outlineFocusedColor, this.outlineColor};
            } else if (this.outlineColor != null) {
                outline = this.outlineColor;
            } else if (this.outlineFocusedColor != null) {
                outline = this.outlineFocusedColor;
            }
        }
        if (outline instanceof String) {
            String str = (String) outline;
            boolean z = -1;
            switch (str.hashCode()) {
                case 96784904:
                    if (str.equals(FlatClientProperties.OUTLINE_ERROR)) {
                        z = false;
                        break;
                    }
                    break;
                case 1124446108:
                    if (str.equals(FlatClientProperties.OUTLINE_WARNING)) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    return isFocused(c) ? this.errorFocusedBorderColor : this.errorBorderColor;
                case true:
                    return isFocused(c) ? this.warningFocusedBorderColor : this.warningBorderColor;
                default:
                    return null;
            }
        }
        if (outline instanceof Color) {
            Color color = (Color) outline;
            if (!isFocused(c) && (this.customBorderColor instanceof DerivedColor)) {
                color = this.customBorderColor.derive(color);
            }
            return color;
        }
        if (!(outline instanceof Color[]) || ((Color[]) outline).length < 2) {
            return null;
        }
        return ((Color[]) outline)[isFocused(c) ? (char) 0 : (char) 1];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Color getFocusColor(Component c) {
        return this.focusColor;
    }

    protected Paint getBorderColor(Component c) {
        if (isEnabled(c)) {
            return isFocused(c) ? this.focusedBorderColor : this.borderColor;
        }
        return this.disabledBorderColor;
    }

    protected boolean isEnabled(Component c) {
        Component view;
        if ((c instanceof JScrollPane) && (view = FlatScrollPaneUI.getView((JScrollPane) c)) != null && !isEnabled(view)) {
            return false;
        }
        return c.isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isFocused(Component c) {
        if (c instanceof JScrollPane) {
            return FlatScrollPaneUI.isPermanentFocusOwner((JScrollPane) c);
        }
        if (c instanceof JComboBox) {
            return FlatComboBoxUI.isPermanentFocusOwner((JComboBox) c);
        }
        if (c instanceof JSpinner) {
            return FlatSpinnerUI.isPermanentFocusOwner((JSpinner) c);
        }
        return FlatUIUtils.isPermanentFocusOwner(c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isCellEditor(Component c) {
        return FlatUIUtils.isCellEditor(c);
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        float focusWidth = UIScale.scale(getFocusWidth(c));
        int ow = Math.round(focusWidth + UIScale.scale(getLineWidth(c)));
        Insets insets2 = super.getBorderInsets(c, insets);
        insets2.top = UIScale.scale(insets2.top) + ow;
        insets2.left = UIScale.scale(insets2.left) + ow;
        insets2.bottom = UIScale.scale(insets2.bottom) + ow;
        insets2.right = UIScale.scale(insets2.right) + ow;
        if (isCellEditor(c)) {
            insets2.bottom = 0;
            insets2.top = 0;
            if (c.getComponentOrientation().isLeftToRight()) {
                insets2.right = 0;
            } else {
                insets2.left = 0;
            }
        }
        return insets2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getFocusWidth(Component c) {
        if (isCellEditor(c)) {
            return 0;
        }
        return this.focusWidth;
    }

    protected float getInnerFocusWidth(Component c) {
        return this.innerFocusWidth;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLineWidth(Component c) {
        return 1;
    }

    protected float getBorderWidth(Component c) {
        return this.borderWidth;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getArc(Component c) {
        return 0;
    }
}
