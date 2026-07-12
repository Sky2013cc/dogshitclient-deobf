package org.knowm.xchart.style.theme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.BaseSeriesColors;
import org.knowm.xchart.style.colors.ChartColor;
import org.knowm.xchart.style.lines.BaseSeriesLines;
import org.knowm.xchart.style.markers.BaseSeriesMarkers;
import org.knowm.xchart.style.markers.Marker;

/* loaded from: target.jar:org/knowm/xchart/style/theme/AbstractBaseTheme.class */
public abstract class AbstractBaseTheme implements Theme {
    @Override // org.knowm.xchart.style.theme.Theme
    public Font getBaseFont() {
        return BASE_FONT;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getChartBackgroundColor() {
        return ChartColor.WHITE.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getChartFontColor() {
        return ChartColor.BLACK.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public int getChartPadding() {
        return 10;
    }

    @Override // org.knowm.xchart.style.colors.SeriesColors
    public Color[] getSeriesColors() {
        return new BaseSeriesColors().getSeriesColors();
    }

    @Override // org.knowm.xchart.style.markers.SeriesMarkers
    public Marker[] getSeriesMarkers() {
        return new BaseSeriesMarkers().getSeriesMarkers();
    }

    @Override // org.knowm.xchart.style.lines.SeriesLines
    public BasicStroke[] getSeriesLines() {
        return new BaseSeriesLines().getSeriesLines();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Font getChartTitleFont() {
        return getBaseFont().deriveFont(1).deriveFont(14.0f);
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isChartTitleVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isChartTitleBoxVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getChartTitleBoxBackgroundColor() {
        return ChartColor.WHITE.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getChartTitleBoxBorderColor() {
        return ChartColor.WHITE.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public int getChartTitlePadding() {
        return 5;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Font getLegendFont() {
        return getBaseFont().deriveFont(11.0f);
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isLegendVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getLegendBackgroundColor() {
        return ChartColor.WHITE.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getLegendBorderColor() {
        return ChartColor.DARK_GREY.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public int getLegendPadding() {
        return 10;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public int getLegendSeriesLineLength() {
        return 24;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Styler.LegendPosition getLegendPosition() {
        return Styler.LegendPosition.OutsideE;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isXAxisTitleVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isYAxisTitleVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Font getAxisTitleFont() {
        return getBaseFont().deriveFont(1).deriveFont(12.0f);
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isXAxisTicksVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isYAxisTicksVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Font getAxisTickLabelsFont() {
        return getAxisTitleFont();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public int getAxisTickMarkLength() {
        return 3;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public int getAxisTickPadding() {
        return 4;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getAxisTickMarksColor() {
        return ChartColor.DARK_GREY.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public BasicStroke getAxisTickMarksStroke() {
        return BASE_STROKE;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getAxisTickLabelsColor() {
        return ChartColor.BLACK.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isAxisTicksLineVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isAxisTicksMarksVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public int getAxisTitlePadding() {
        return 10;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public int getXAxisTickMarkSpacingHint() {
        return 74;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public int getYAxisTickMarkSpacingHint() {
        return 44;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isPlotGridLinesVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isPlotGridVerticalLinesVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isPlotGridHorizontalLinesVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getPlotBackgroundColor() {
        return ChartColor.WHITE.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getPlotBorderColor() {
        return ChartColor.DARK_GREY.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isPlotBorderVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isPlotTicksMarksVisible() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getPlotGridLinesColor() {
        return ChartColor.GREY.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public BasicStroke getPlotGridLinesStroke() {
        return new BasicStroke(1.0f, 0, 2, 10.0f, new float[]{3.0f, 5.0f}, 0.0f);
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public int getPlotMargin() {
        return 4;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isCursorEnabled() {
        return false;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getCursorColor() {
        return Color.BLACK;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public float getCursorSize() {
        return 1.0f;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Font getCursorFont() {
        return new Font("SansSerif", 0, 16);
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getCursorFontColor() {
        return Color.WHITE;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getCursorBackgroundColor() {
        return Color.GRAY;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public double getAvailableSpaceFill() {
        return 0.9d;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isOverlapped() {
        return false;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isCircular() {
        return true;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public double getStartAngleInDegrees() {
        return 0.0d;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Font getPieFont() {
        return getBaseFont().deriveFont(15.0f);
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public double getLabelsDistance() {
        return 0.67d;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public PieStyler.LabelType getLabelType() {
        return PieStyler.LabelType.Percentage;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean setForceAllLabelsVisible() {
        return false;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public double getDonutThickness() {
        return 0.33d;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isSumVisible() {
        return false;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Font getSumFont() {
        return getBaseFont().deriveFont(15.0f);
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public int getMarkerSize() {
        return 8;
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public Color getErrorBarsColor() {
        return ChartColor.BLACK.getColor();
    }

    @Override // org.knowm.xchart.style.theme.Theme
    public boolean isErrorBarsColorSeriesColor() {
        return false;
    }
}
