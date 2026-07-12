package org.knowm.xchart.style.theme;

import java.awt.BasicStroke;
import java.awt.Color;
import org.knowm.xchart.style.colors.ChartColor;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import org.knowm.xchart.style.lines.XChartSeriesLines;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.XChartSeriesMarkers;

/* loaded from: target.jar:org/knowm/xchart/style/theme/XChartTheme.class */
public class XChartTheme extends AbstractBaseTheme {
    @Override // org.knowm.xchart.style.theme.AbstractBaseTheme, org.knowm.xchart.style.theme.Theme
    public Color getChartBackgroundColor() {
        return ChartColor.GREY.getColor();
    }

    @Override // org.knowm.xchart.style.theme.AbstractBaseTheme, org.knowm.xchart.style.colors.SeriesColors
    public Color[] getSeriesColors() {
        return new XChartSeriesColors().getSeriesColors();
    }

    @Override // org.knowm.xchart.style.theme.AbstractBaseTheme, org.knowm.xchart.style.markers.SeriesMarkers
    public Marker[] getSeriesMarkers() {
        return new XChartSeriesMarkers().getSeriesMarkers();
    }

    @Override // org.knowm.xchart.style.theme.AbstractBaseTheme, org.knowm.xchart.style.lines.SeriesLines
    public BasicStroke[] getSeriesLines() {
        return new XChartSeriesLines().getSeriesLines();
    }

    @Override // org.knowm.xchart.style.theme.AbstractBaseTheme, org.knowm.xchart.style.theme.Theme
    public boolean isChartTitleBoxVisible() {
        return false;
    }

    @Override // org.knowm.xchart.style.theme.AbstractBaseTheme, org.knowm.xchart.style.theme.Theme
    public Color getChartTitleBoxBackgroundColor() {
        return ChartColor.GREY.getColor();
    }

    @Override // org.knowm.xchart.style.theme.AbstractBaseTheme, org.knowm.xchart.style.theme.Theme
    public Color getChartTitleBoxBorderColor() {
        return ChartColor.GREY.getColor();
    }

    @Override // org.knowm.xchart.style.theme.AbstractBaseTheme, org.knowm.xchart.style.theme.Theme
    public boolean isPlotTicksMarksVisible() {
        return false;
    }
}
