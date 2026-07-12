package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Map;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatSearchIcon.class */
public class FlatSearchIcon extends FlatAbstractIcon {

    @FlatStylingSupport.Styleable
    protected Color searchIconColor;

    @FlatStylingSupport.Styleable
    protected Color searchIconHoverColor;

    @FlatStylingSupport.Styleable
    protected Color searchIconPressedColor;
    private final boolean ignoreButtonState;
    private Area area;

    public FlatSearchIcon() {
        this(false);
    }

    public FlatSearchIcon(boolean ignoreButtonState) {
        super(16, 16, null);
        this.searchIconColor = UIManager.getColor("SearchField.searchIconColor");
        this.searchIconHoverColor = UIManager.getColor("SearchField.searchIconHoverColor");
        this.searchIconPressedColor = UIManager.getColor("SearchField.searchIconPressedColor");
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.formdev.flatlaf.icons.FlatAbstractIcon
    public void paintIcon(Component c, Graphics2D g) {
        Color buttonStateColor;
        if (this.ignoreButtonState) {
            buttonStateColor = this.searchIconColor;
        } else {
            buttonStateColor = FlatButtonUI.buttonStateColor(c, this.searchIconColor, this.searchIconColor, null, this.searchIconHoverColor, this.searchIconPressedColor);
        }
        g.setColor(buttonStateColor);
        if (this.area == null) {
            this.area = new Area(new Ellipse2D.Float(2.0f, 2.0f, 10.0f, 10.0f));
            this.area.subtract(new Area(new Ellipse2D.Float(3.0f, 3.0f, 8.0f, 8.0f)));
            this.area.add(new Area(FlatUIUtils.createPath(10.813d, 9.75d, 14.0d, 12.938d, 12.938d, 14.0d, 9.75d, 10.813d)));
        }
        g.fill(this.area);
    }
}
