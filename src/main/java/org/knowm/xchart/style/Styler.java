package org.knowm.xchart.style;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import org.knowm.xchart.style.colors.ChartColor;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.theme.GGPlot2Theme;
import org.knowm.xchart.style.theme.MatlabTheme;
import org.knowm.xchart.style.theme.Theme;
import org.knowm.xchart.style.theme.XChartTheme;

/* loaded from: target.jar:org/knowm/xchart/style/Styler.class */
public abstract class Styler {
    private Font baseFont;
    private Color chartBackgroundColor;
    private Color chartFontColor;
    private int chartPadding;
    private Color[] seriesColors;
    private BasicStroke[] seriesLines;
    private Marker[] seriesMarkers;
    private Font chartTitleFont;
    private boolean isChartTitleVisible;
    private boolean isChartTitleBoxVisible;
    private Color chartTitleBoxBackgroundColor;
    private Color chartTitleBoxBorderColor;
    private int chartTitlePadding;
    private boolean isLegendVisible;
    private Color legendBackgroundColor;
    private Color legendBorderColor;
    private Font legendFont;
    private int legendPadding;
    private int legendSeriesLineLength;
    private LegendPosition legendPosition;
    private Color plotBackgroundColor;
    private Color plotBorderColor;
    private boolean isPlotBorderVisible;
    private double plotContentSize;
    private Color annotationTextPanelBackgroundColor;
    private Color annotationTextPanelBorderColor;
    private Font annotationTextPanelFont;
    private Color annotationTextPanelFontColor;
    private int annotationTextPanelPadding;
    private Font annotationTextFont;
    private Color annotationTextFontColor;
    private BasicStroke annotationLineStroke;
    private Color annotationLineColor;
    private Color chartButtonBackgroundColor;
    private Color chartButtonBorderColor;
    private Color chartButtonFontColor;
    private Font chartButtonFont;
    private int chartButtonMargin;
    private ChartButtonPosition chartButtonPosition;
    private boolean isToolTipsEnabled;
    private boolean isToolTipsAlwaysVisible;
    private ToolTipType toolTipType;
    private Color toolTipBackgroundColor;
    private Color toolTipBorderColor;
    private Font toolTipFont;
    private Color toolTipHighlightColor;
    private String decimalPattern;
    private int yAxisLeftWidthHint;
    private Color xAxisTitleColor;
    private Color yAxisTitleColor;
    private int markerSize;
    Theme theme = new XChartTheme();
    private LegendLayout legendLayout = LegendLayout.Vertical;
    private boolean antiAlias = true;
    private final HashMap<Integer, YAxisPosition> yAxisAlignmentMap = new HashMap<>();
    private boolean showWithinAreaPoint = false;
    private final Map<Integer, Color> yAxisGroupTitleColorMap = new HashMap();

    /* loaded from: target.jar:org/knowm/xchart/style/Styler$ChartButtonPosition.class */
    public enum ChartButtonPosition {
        InsideNW,
        InsideNE,
        InsideSE,
        InsideSW,
        InsideN,
        InsideS
    }

    /* loaded from: target.jar:org/knowm/xchart/style/Styler$LegendLayout.class */
    public enum LegendLayout {
        Vertical,
        Horizontal
    }

    /* loaded from: target.jar:org/knowm/xchart/style/Styler$LegendPosition.class */
    public enum LegendPosition {
        OutsideE,
        InsideNW,
        InsideNE,
        InsideSE,
        InsideSW,
        InsideN,
        InsideS,
        OutsideS
    }

    /* loaded from: target.jar:org/knowm/xchart/style/Styler$ToolTipType.class */
    public enum ToolTipType {
        xLabels,
        yLabels,
        xAndYLabels
    }

    /* loaded from: target.jar:org/knowm/xchart/style/Styler$YAxisPosition.class */
    public enum YAxisPosition {
        Left,
        Right
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAllStyles() {
        this.baseFont = this.theme.getBaseFont();
        this.chartBackgroundColor = this.theme.getChartBackgroundColor();
        this.chartFontColor = this.theme.getChartFontColor();
        this.chartPadding = this.theme.getChartPadding();
        this.seriesColors = this.theme.getSeriesColors();
        this.seriesLines = this.theme.getSeriesLines();
        this.seriesMarkers = this.theme.getSeriesMarkers();
        this.chartTitleFont = this.theme.getChartTitleFont();
        this.isChartTitleVisible = this.theme.isChartTitleVisible();
        this.isChartTitleBoxVisible = this.theme.isChartTitleBoxVisible();
        this.chartTitleBoxBackgroundColor = this.theme.getChartTitleBoxBackgroundColor();
        this.chartTitleBoxBorderColor = this.theme.getChartTitleBoxBorderColor();
        this.chartTitlePadding = this.theme.getChartTitlePadding();
        this.isLegendVisible = this.theme.isLegendVisible();
        this.legendBackgroundColor = this.theme.getLegendBackgroundColor();
        this.legendBorderColor = this.theme.getLegendBorderColor();
        this.legendFont = this.theme.getLegendFont();
        this.legendPadding = this.theme.getLegendPadding();
        this.legendSeriesLineLength = this.theme.getLegendSeriesLineLength();
        this.legendPosition = this.theme.getLegendPosition();
        this.plotBackgroundColor = this.theme.getPlotBackgroundColor();
        this.plotBorderColor = this.theme.getPlotBorderColor();
        this.isPlotBorderVisible = this.theme.isPlotBorderVisible();
        this.plotContentSize = this.theme.getPlotContentSize();
        this.annotationTextPanelBackgroundColor = this.theme.getAnnotationTextPanelBackgroundColor();
        this.annotationTextPanelBorderColor = this.theme.getAnnotationTextPanelBorderColor();
        this.annotationTextPanelFont = this.theme.getAnnotationTextPanelFont();
        this.annotationTextPanelFontColor = this.theme.getAnnotationTextPanelFontColor();
        this.annotationTextPanelPadding = this.theme.getAnnotationTextPanelPadding();
        this.annotationTextFont = this.theme.getAnnotationTextFont();
        this.annotationTextFontColor = this.theme.getAnnotationTextFontColor();
        this.annotationLineStroke = this.theme.getAnnotationLineStroke();
        this.annotationLineColor = this.theme.getAnnotationLineColor();
        this.chartButtonBackgroundColor = ChartColor.LIGHT_GREY.getColor();
        this.chartButtonBorderColor = ChartColor.DARK_GREY.getColor();
        this.chartButtonFontColor = getChartFontColor();
        this.chartButtonFont = getBaseFont().deriveFont(11.0f);
        this.chartButtonMargin = 6;
        this.chartButtonPosition = ChartButtonPosition.InsideN;
        this.isToolTipsEnabled = this.theme.isToolTipsEnabled();
        this.toolTipType = this.theme.getToolTipType();
        this.toolTipBackgroundColor = this.theme.getToolTipBackgroundColor();
        this.toolTipBorderColor = this.theme.getToolTipBorderColor();
        this.toolTipFont = this.theme.getToolTipFont();
        this.toolTipHighlightColor = this.theme.getToolTipHighlightColor();
        this.decimalPattern = null;
        this.markerSize = this.theme.getMarkerSize();
    }

    public Font getBaseFont() {
        return this.baseFont;
    }

    public Styler setBaseFont(Font baseFont) {
        this.baseFont = baseFont;
        return this;
    }

    public Color getChartBackgroundColor() {
        return this.chartBackgroundColor;
    }

    public Styler setChartBackgroundColor(Color color) {
        this.chartBackgroundColor = color;
        return this;
    }

    public Color getChartFontColor() {
        return this.chartFontColor;
    }

    public Styler setChartFontColor(Color color) {
        this.chartFontColor = color;
        return this;
    }

    public int getChartPadding() {
        return this.chartPadding;
    }

    public Styler setChartPadding(int chartPadding) {
        this.chartPadding = chartPadding;
        return this;
    }

    public Color[] getSeriesColors() {
        return this.seriesColors;
    }

    public Styler setSeriesColors(Color[] seriesColors) {
        this.seriesColors = seriesColors;
        return this;
    }

    public BasicStroke[] getSeriesLines() {
        return this.seriesLines;
    }

    public Styler setSeriesLines(BasicStroke[] seriesLines) {
        this.seriesLines = seriesLines;
        return this;
    }

    public Marker[] getSeriesMarkers() {
        return this.seriesMarkers;
    }

    public Styler setSeriesMarkers(Marker[] seriesMarkers) {
        this.seriesMarkers = seriesMarkers;
        return this;
    }

    public Font getChartTitleFont() {
        return this.chartTitleFont;
    }

    public Styler setChartTitleFont(Font chartTitleFont) {
        this.chartTitleFont = chartTitleFont;
        return this;
    }

    public boolean isChartTitleVisible() {
        return this.isChartTitleVisible;
    }

    public Styler setChartTitleVisible(boolean isChartTitleVisible) {
        this.isChartTitleVisible = isChartTitleVisible;
        return this;
    }

    public boolean isChartTitleBoxVisible() {
        return this.isChartTitleBoxVisible;
    }

    public Styler setChartTitleBoxVisible(boolean isChartTitleBoxVisible) {
        this.isChartTitleBoxVisible = isChartTitleBoxVisible;
        return this;
    }

    public Color getChartTitleBoxBackgroundColor() {
        return this.chartTitleBoxBackgroundColor;
    }

    public Styler setChartTitleBoxBackgroundColor(Color chartTitleBoxBackgroundColor) {
        this.chartTitleBoxBackgroundColor = chartTitleBoxBackgroundColor;
        return this;
    }

    public Color getChartTitleBoxBorderColor() {
        return this.chartTitleBoxBorderColor;
    }

    public Styler setChartTitleBoxBorderColor(Color chartTitleBoxBorderColor) {
        this.chartTitleBoxBorderColor = chartTitleBoxBorderColor;
        return this;
    }

    public int getChartTitlePadding() {
        return this.chartTitlePadding;
    }

    public Styler setChartTitlePadding(int chartTitlePadding) {
        this.chartTitlePadding = chartTitlePadding;
        return this;
    }

    public boolean isLegendVisible() {
        return this.isLegendVisible;
    }

    public Styler setLegendVisible(boolean isLegendVisible) {
        this.isLegendVisible = isLegendVisible;
        return this;
    }

    public Color getLegendBackgroundColor() {
        return this.legendBackgroundColor;
    }

    public Styler setLegendBackgroundColor(Color color) {
        this.legendBackgroundColor = color;
        return this;
    }

    public Color getLegendBorderColor() {
        return this.legendBorderColor;
    }

    public Styler setLegendBorderColor(Color legendBorderColor) {
        this.legendBorderColor = legendBorderColor;
        return this;
    }

    public Font getLegendFont() {
        return this.legendFont;
    }

    public Styler setLegendFont(Font font) {
        this.legendFont = font;
        return this;
    }

    public int getLegendPadding() {
        return this.legendPadding;
    }

    public Styler setLegendPadding(int legendPadding) {
        this.legendPadding = legendPadding;
        return this;
    }

    public int getLegendSeriesLineLength() {
        return this.legendSeriesLineLength;
    }

    public Styler setLegendSeriesLineLength(int legendSeriesLineLength) {
        this.legendSeriesLineLength = Math.max(legendSeriesLineLength, 0);
        return this;
    }

    public LegendPosition getLegendPosition() {
        return this.legendPosition;
    }

    public Styler setLegendPosition(LegendPosition legendPosition) {
        this.legendPosition = legendPosition;
        return this;
    }

    public LegendLayout getLegendLayout() {
        return this.legendLayout;
    }

    public Styler setLegendLayout(LegendLayout legendLayout) {
        this.legendLayout = legendLayout;
        return this;
    }

    public Color getPlotBackgroundColor() {
        return this.plotBackgroundColor;
    }

    public Styler setPlotBackgroundColor(Color plotBackgroundColor) {
        this.plotBackgroundColor = plotBackgroundColor;
        return this;
    }

    public Color getPlotBorderColor() {
        return this.plotBorderColor;
    }

    public Styler setPlotBorderColor(Color plotBorderColor) {
        this.plotBorderColor = plotBorderColor;
        return this;
    }

    public boolean isPlotBorderVisible() {
        return this.isPlotBorderVisible;
    }

    public Styler setPlotBorderVisible(boolean isPlotBorderVisible) {
        this.isPlotBorderVisible = isPlotBorderVisible;
        return this;
    }

    public double getPlotContentSize() {
        return this.plotContentSize;
    }

    public Styler setPlotContentSize(double plotContentSize) {
        if (plotContentSize < 0.0d || plotContentSize > 1.0d) {
            throw new IllegalArgumentException("Plot content size must be tween 0 and 1!!!");
        }
        this.plotContentSize = plotContentSize;
        return this;
    }

    public Color getAnnotationTextPanelBackgroundColor() {
        return this.annotationTextPanelBackgroundColor;
    }

    public Styler setAnnotationTextPanelBackgroundColor(Color color) {
        this.annotationTextPanelBackgroundColor = color;
        return this;
    }

    public Color getAnnotationTextPanelBorderColor() {
        return this.annotationTextPanelBorderColor;
    }

    public Styler setAnnotationTextPanelBorderColor(Color borderColor) {
        this.annotationTextPanelBorderColor = borderColor;
        return this;
    }

    public Font getAnnotationTextPanelFont() {
        return this.annotationTextPanelFont;
    }

    public Styler setAnnotationTextPanelFont(Font font) {
        this.annotationTextPanelFont = font;
        return this;
    }

    public Color getAnnotationTextPanelFontColor() {
        return this.annotationTextPanelFontColor;
    }

    public Styler setAnnotationTextPanelFontColor(Color annotationTextPanelFontColor) {
        this.annotationTextPanelFontColor = annotationTextPanelFontColor;
        return this;
    }

    public int getAnnotationTextPanelPadding() {
        return this.annotationTextPanelPadding;
    }

    public Styler setAnnotationTextPanelPadding(int annotationTextPanelPadding) {
        this.annotationTextPanelPadding = annotationTextPanelPadding;
        return this;
    }

    public Font getAnnotationTextFont() {
        return this.annotationTextFont;
    }

    public Styler setAnnotationTextFont(Font annotationTextFont) {
        this.annotationTextFont = annotationTextFont;
        return this;
    }

    public Color getAnnotationTextFontColor() {
        return this.annotationTextFontColor;
    }

    public Styler setAnnotationTextFontColor(Color annotationTextFontColor) {
        this.annotationTextFontColor = annotationTextFontColor;
        return this;
    }

    public BasicStroke getAnnotationLineStroke() {
        return this.annotationLineStroke;
    }

    public Styler setAnnotationLineStroke(BasicStroke annotationLineStroke) {
        this.annotationLineStroke = annotationLineStroke;
        return this;
    }

    public Color getAnnotationLineColor() {
        return this.annotationLineColor;
    }

    public Styler setAnnotationLineColor(Color annotationLineColor) {
        this.annotationLineColor = annotationLineColor;
        return this;
    }

    public Color getChartButtonBackgroundColor() {
        return this.chartButtonBackgroundColor;
    }

    public Styler setChartButtonBackgroundColor(Color chartButtonBackgroundColor) {
        this.chartButtonBackgroundColor = chartButtonBackgroundColor;
        return this;
    }

    public Color getChartButtonBorderColor() {
        return this.chartButtonBorderColor;
    }

    public Styler setChartButtonBorderColor(Color chartButtonBorderColor) {
        this.chartButtonBorderColor = chartButtonBorderColor;
        return this;
    }

    public Color getChartButtonFontColor() {
        return this.chartButtonFontColor;
    }

    public Styler setChartButtonFontColor(Color chartButtonFontColor) {
        this.chartButtonFontColor = chartButtonFontColor;
        return this;
    }

    public Font getChartButtonFont() {
        return this.chartButtonFont;
    }

    public Styler setChartButtonFont(Font chartButtonFont) {
        this.chartButtonFont = chartButtonFont;
        return this;
    }

    public int getChartButtonMargin() {
        return this.chartButtonMargin;
    }

    public Styler setChartButtonMargin(int chartButtonMargin) {
        this.chartButtonMargin = chartButtonMargin;
        return this;
    }

    public ChartButtonPosition getChartButtonPosition() {
        return this.chartButtonPosition;
    }

    public Styler setChartButtonPosition(ChartButtonPosition chartButtonPosition) {
        this.chartButtonPosition = chartButtonPosition;
        return this;
    }

    public boolean isToolTipsEnabled() {
        return this.isToolTipsEnabled;
    }

    public Styler setToolTipsEnabled(boolean toolTipsEnabled) {
        this.isToolTipsEnabled = toolTipsEnabled;
        return this;
    }

    public boolean isToolTipsAlwaysVisible() {
        return this.isToolTipsAlwaysVisible;
    }

    public Styler setToolTipsAlwaysVisible(boolean toolTipsAlwaysVisible) {
        this.isToolTipsAlwaysVisible = toolTipsAlwaysVisible;
        return this;
    }

    public ToolTipType getToolTipType() {
        return this.toolTipType;
    }

    public Styler setToolTipType(ToolTipType toolTipType) {
        this.toolTipType = toolTipType;
        return this;
    }

    public Color getToolTipBackgroundColor() {
        return this.toolTipBackgroundColor;
    }

    public Styler setToolTipBackgroundColor(Color toolTipBackgroundColor) {
        this.toolTipBackgroundColor = toolTipBackgroundColor;
        return this;
    }

    public Color getToolTipBorderColor() {
        return this.toolTipBorderColor;
    }

    public Styler setToolTipBorderColor(Color toolTipBorderColor) {
        this.toolTipBorderColor = toolTipBorderColor;
        return this;
    }

    public Font getToolTipFont() {
        return this.toolTipFont;
    }

    public Styler setToolTipFont(Font toolTipFont) {
        this.toolTipFont = toolTipFont;
        return this;
    }

    public Color getToolTipHighlightColor() {
        return this.toolTipHighlightColor;
    }

    public Styler setToolTipHighlightColor(Color toolTipHighlightColor) {
        this.toolTipHighlightColor = toolTipHighlightColor;
        return this;
    }

    public String getDecimalPattern() {
        return this.decimalPattern;
    }

    public Styler setDecimalPattern(String decimalPattern) {
        this.decimalPattern = decimalPattern;
        return this;
    }

    public YAxisPosition getYAxisGroupPosistion(int yAxisGroup) {
        return this.yAxisAlignmentMap.get(Integer.valueOf(yAxisGroup));
    }

    public Styler setYAxisGroupPosition(int yAxisGroup, YAxisPosition yAxisPosition) {
        this.yAxisAlignmentMap.put(Integer.valueOf(yAxisGroup), yAxisPosition);
        return this;
    }

    public boolean getAntiAlias() {
        return this.antiAlias;
    }

    public Styler setAntiAlias(boolean newVal) {
        this.antiAlias = newVal;
        return this;
    }

    public int getYAxisLeftWidthHint() {
        return this.yAxisLeftWidthHint;
    }

    public Styler setYAxisLeftWidthHint(int yAxisLeftWidthHint) {
        this.yAxisLeftWidthHint = yAxisLeftWidthHint;
        return this;
    }

    public Styler setShowWithinAreaPoint(boolean showWithinAreaPoint) {
        this.showWithinAreaPoint = showWithinAreaPoint;
        return this;
    }

    public boolean getShowWithinAreaPoint() {
        return this.showWithinAreaPoint;
    }

    public Color getXAxisTitleColor() {
        return this.xAxisTitleColor;
    }

    public Styler setXAxisTitleColor(Color xAxisTitleColor) {
        this.xAxisTitleColor = xAxisTitleColor;
        return this;
    }

    public Color getYAxisTitleColor() {
        return this.yAxisTitleColor;
    }

    public Styler setYAxisTitleColor(Color yAxisColor) {
        this.yAxisTitleColor = yAxisColor;
        return this;
    }

    public Color getYAxisGroupTitleColor(int yAxisGroup) {
        Color color = this.yAxisGroupTitleColorMap.get(Integer.valueOf(yAxisGroup));
        if (color == null) {
            return this.yAxisTitleColor;
        }
        return color;
    }

    public Styler setYAxisGroupTitleColor(int yAxisGroup, Color yAxisColor) {
        this.yAxisGroupTitleColorMap.put(Integer.valueOf(yAxisGroup), yAxisColor);
        return this;
    }

    public int getMarkerSize() {
        return this.markerSize;
    }

    public Styler setMarkerSize(int markerSize) {
        this.markerSize = markerSize;
        return this;
    }

    /* loaded from: target.jar:org/knowm/xchart/style/Styler$ChartTheme.class */
    public enum ChartTheme {
        XChart,
        GGPlot2,
        Matlab;

        public Theme newInstance(ChartTheme chartTheme) {
            switch (chartTheme) {
                case GGPlot2:
                    return new GGPlot2Theme();
                case Matlab:
                    return new MatlabTheme();
                case XChart:
                default:
                    return new XChartTheme();
            }
        }
    }

    public Theme getTheme() {
        return this.theme;
    }
}
