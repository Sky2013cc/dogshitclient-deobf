package org.knowm.xchart.style.colors;

import java.awt.Color;

/* loaded from: target.jar:org/knowm/xchart/style/colors/ChartColor.class */
public enum ChartColor {
    BLACK(new Color(0, 0, 0)),
    DARK_GREY(new Color(130, 130, 130)),
    GREY(new Color(210, 210, 210)),
    LIGHT_GREY(new Color(230, 230, 230)),
    WHITE(new Color(255, 255, 255)),
    RED(new Color(237, 67, 55)),
    BLUE(new Color(67, 55, 237)),
    GREEN(new Color(67, 237, 55));

    final Color color;

    ChartColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public Color getColorTranslucent() {
        return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 128);
    }
}
