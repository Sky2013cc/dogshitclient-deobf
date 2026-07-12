package org.knowm.xchart.style.colors;

import java.awt.Color;

/* loaded from: target.jar:org/knowm/xchart/style/colors/BaseSeriesColors.class */
public class BaseSeriesColors implements SeriesColors {
    private final Color[] seriesColors = {new Color(141, 211, 199), new Color(255, 255, 179), new Color(190, 186, 218), new Color(251, 128, 114), new Color(128, 177, 211), new Color(253, 180, 98), new Color(179, 222, 105), new Color(252, 205, 229), new Color(217, 217, 217), new Color(188, 128, 189), new Color(204, 235, 197), new Color(255, 237, 111)};

    @Override // org.knowm.xchart.style.colors.SeriesColors
    public Color[] getSeriesColors() {
        return this.seriesColors;
    }
}
