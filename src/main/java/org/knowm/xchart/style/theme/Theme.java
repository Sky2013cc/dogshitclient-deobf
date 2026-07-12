package org.knowm.xchart.style.theme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.ChartColor;
import org.knowm.xchart.style.colors.SeriesColors;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

/* loaded from: target.jar:org/knowm/xchart/style/theme/Theme.class */
public interface Theme extends SeriesMarkers, SeriesLines, SeriesColors {
    public static final Font BASE_FONT = new Font("SansSerif", 0, 10);
    public static final BasicStroke BASE_STROKE = new BasicStroke(1.0f);

    Font getBaseFont();

    Color getChartBackgroundColor();

    Color getChartFontColor();

    int getChartPadding();

    Font getChartTitleFont();

    boolean isChartTitleVisible();

    boolean isChartTitleBoxVisible();

    Color getChartTitleBoxBackgroundColor();

    Color getChartTitleBoxBorderColor();

    int getChartTitlePadding();

    Font getLegendFont();

    boolean isLegendVisible();

    Color getLegendBackgroundColor();

    Color getLegendBorderColor();

    int getLegendPadding();

    int getLegendSeriesLineLength();

    Styler.LegendPosition getLegendPosition();

    boolean isPlotGridLinesVisible();

    boolean isPlotGridVerticalLinesVisible();

    boolean isPlotGridHorizontalLinesVisible();

    Color getPlotBackgroundColor();

    Color getPlotBorderColor();

    boolean isPlotBorderVisible();

    Color getPlotGridLinesColor();

    BasicStroke getPlotGridLinesStroke();

    boolean isPlotTicksMarksVisible();

    int getPlotMargin();

    boolean isXAxisTitleVisible();

    boolean isYAxisTitleVisible();

    Font getAxisTitleFont();

    boolean isXAxisTicksVisible();

    boolean isYAxisTicksVisible();

    Font getAxisTickLabelsFont();

    int getAxisTickMarkLength();

    int getAxisTickPadding();

    Color getAxisTickMarksColor();

    BasicStroke getAxisTickMarksStroke();

    Color getAxisTickLabelsColor();

    boolean isAxisTicksLineVisible();

    boolean isAxisTicksMarksVisible();

    int getAxisTitlePadding();

    int getXAxisTickMarkSpacingHint();

    int getYAxisTickMarkSpacingHint();

    boolean isCursorEnabled();

    Color getCursorColor();

    float getCursorSize();

    Font getCursorFont();

    Color getCursorFontColor();

    Color getCursorBackgroundColor();

    double getAvailableSpaceFill();

    boolean isOverlapped();

    boolean isCircular();

    double getStartAngleInDegrees();

    Font getPieFont();

    double getLabelsDistance();

    PieStyler.LabelType getLabelType();

    boolean setForceAllLabelsVisible();

    double getDonutThickness();

    boolean isSumVisible();

    Font getSumFont();

    int getMarkerSize();

    Color getErrorBarsColor();

    boolean isErrorBarsColorSeriesColor();

    default double getPlotContentSize() {
        return 0.92d;
    }

    default Font getAnnotationTextPanelFont() {
        return new Font("Monospaced", 0, 10);
    }

    default Color getAnnotationTextPanelFontColor() {
        return ChartColor.BLACK.getColor();
    }

    default Color getAnnotationTextPanelBackgroundColor() {
        return ChartColor.WHITE.getColor();
    }

    default Color getAnnotationTextPanelBorderColor() {
        return ChartColor.DARK_GREY.getColor();
    }

    default int getAnnotationTextPanelPadding() {
        return 10;
    }

    default Font getAnnotationTextFont() {
        return new Font("Monospaced", 0, 10);
    }

    default Color getAnnotationTextFontColor() {
        return ChartColor.BLACK.getColor();
    }

    default BasicStroke getAnnotationLineStroke() {
        return BASE_STROKE;
    }

    default Color getAnnotationLineColor() {
        return ChartColor.DARK_GREY.getColor();
    }

    default Color getChartButtonBackgroundColor() {
        return ChartColor.BLUE.getColor().brighter();
    }

    default Color getChartButtonBorderColor() {
        return ChartColor.BLUE.getColor().darker();
    }

    default Color getChartButtonHoverColor() {
        return ChartColor.BLUE.getColor();
    }

    default Color getChartButtonFontColor() {
        return getChartFontColor();
    }

    default Font getChartButtonFont() {
        return getLegendFont();
    }

    default int getChartButtonMargin() {
        return 6;
    }

    default boolean isToolTipsEnabled() {
        return false;
    }

    default Styler.ToolTipType getToolTipType() {
        return Styler.ToolTipType.xAndYLabels;
    }

    default Font getToolTipFont() {
        return BASE_FONT;
    }

    default Color getToolTipBackgroundColor() {
        return ChartColor.WHITE.getColor();
    }

    default Color getToolTipBorderColor() {
        return ChartColor.DARK_GREY.getColor();
    }

    default Color getToolTipHighlightColor() {
        return ChartColor.LIGHT_GREY.getColor();
    }

    default boolean isZoomEnabled() {
        return false;
    }

    default Color getLabelsFontColorAutomaticDark() {
        return Color.BLACK;
    }

    default Color getLabelsFontColorAutomaticLight() {
        return Color.WHITE;
    }

    default boolean isLabelsFontColorAutomaticEnabled() {
        return true;
    }
}
