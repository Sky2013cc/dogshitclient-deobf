package org.knowm.xchart.style.colors;

import java.awt.Color;

/* loaded from: target.jar:org/knowm/xchart/style/colors/PrinterFriendlySeriesColors.class */
public class PrinterFriendlySeriesColors implements SeriesColors {
    private static final Color RED = new Color(228, 26, 28, 180);
    private static final Color GREEN = new Color(55, 126, 184, 180);
    private static final Color BLUE = new Color(77, 175, 74, 180);
    private static final Color PURPLE = new Color(152, 78, 163, 180);
    private static final Color ORANGE = new Color(255, 127, 0, 180);
    private final Color[] seriesColors = {RED, GREEN, BLUE, PURPLE, ORANGE};

    @Override // org.knowm.xchart.style.colors.SeriesColors
    public Color[] getSeriesColors() {
        return this.seriesColors;
    }
}
