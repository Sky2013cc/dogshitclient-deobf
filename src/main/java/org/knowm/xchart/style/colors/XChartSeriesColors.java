package org.knowm.xchart.style.colors;

import java.awt.Color;
import kotlin.text.Typography;

/* loaded from: target.jar:org/knowm/xchart/style/colors/XChartSeriesColors.class */
public class XChartSeriesColors implements SeriesColors {
    public static final Color BLUE = new Color(0, 55, 255, 180);
    public static final Color ORANGE = new Color(255, 172, 0, 180);
    public static final Color PURPLE = new Color(128, 0, 255, 180);
    public static final Color GREEN = new Color(0, 205, 0, 180);
    public static final Color RED = new Color(205, 0, 0, 180);
    public static final Color YELLOW = new Color(255, Typography.times, 0, 180);
    public static final Color MAGENTA = new Color(255, 0, 255, 180);
    public static final Color PINK = new Color(255, 166, 201, 180);
    public static final Color LIGHT_GREY = new Color(207, 207, 207, 180);
    public static final Color CYAN = new Color(0, 255, 255, 180);
    public static final Color BROWN = new Color(102, 56, 10, 180);
    public static final Color BLACK = new Color(0, 0, 0, 180);
    private final Color[] seriesColors = {BLUE, ORANGE, PURPLE, GREEN, RED, YELLOW, MAGENTA, PINK, LIGHT_GREY, CYAN, BROWN, BLACK};

    @Override // org.knowm.xchart.style.colors.SeriesColors
    public Color[] getSeriesColors() {
        return this.seriesColors;
    }
}
