package org.knowm.xchart.style;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.Function;

/* loaded from: target.jar:org/knowm/xchart/style/AxesChartStyler.class */
public abstract class AxesChartStyler extends Styler {
    private boolean xAxisTitleVisible;
    private boolean yAxisTitleVisible;
    private Font axisTitleFont;
    private boolean xAxisTicksVisible;
    private boolean yAxisTicksVisible;
    private Font axisTickLabelsFont;
    private int axisTickMarkLength;
    private int axisTickPadding;
    private Color axisTickMarksColor;
    private BasicStroke axisTickMarksStroke;
    private Color axisTickLabelsColor;
    private boolean isAxisTicksLineVisible;
    private boolean isAxisTicksMarksVisible;
    private int plotMargin;
    private int axisTitlePadding;
    private int xAxisTickMarkSpacingHint;
    private int yAxisTickMarkSpacingHint;
    private boolean isXAxisLogarithmic;
    private boolean isYAxisLogarithmic;
    private Double xAxisMin;
    private Double xAxisMax;
    private boolean isPlotGridHorizontalLinesVisible;
    private boolean isPlotGridVerticalLinesVisible;
    private boolean isPlotTicksMarksVisible;
    private Color plotGridLinesColor;
    private BasicStroke plotGridLinesStroke;
    private Color errorBarsColor;
    private boolean isErrorBarsColorSeriesColor;
    private Locale locale;
    private TimeZone timezone;
    private String datePattern;
    private String xAxisDecimalPattern;
    private String yAxisDecimalPattern;
    private Map<Integer, String> yAxisGroupDecimalPatternMap;
    private boolean xAxisLogarithmicDecadeOnly;
    private boolean yAxisLogarithmicDecadeOnly;
    private Function<Double, String> xAxisTickLabelsFormattingFunction;
    private Function<Double, String> yAxisTickLabelsFormattingFunction;
    private Color xAxisTickLabelsColor;
    private Color yAxisTickLabelsColor;
    private Color xAxisTickMarksColor;
    private Color yAxisTickMarksColor;
    private final HashMap<Integer, Double> yAxisMinMap = new HashMap<>();
    private final HashMap<Integer, Double> yAxisMaxMap = new HashMap<>();
    private int xAxisMaxLabelCount = 0;
    private final Map<Integer, Color> yAxisGroupTickLabelsColorMap = new HashMap();
    private final Map<Integer, Color> yAxisGroupTickMarksColorMap = new HashMap();
    private TextAlignment xAxisLabelAlignment = TextAlignment.Centre;
    private TextAlignment xAxisLabelAlignmentVertical = TextAlignment.Centre;
    private TextAlignment yAxisLabelAlignment = TextAlignment.Left;
    private int xAxisLabelRotation = 0;

    /* loaded from: target.jar:org/knowm/xchart/style/AxesChartStyler$TextAlignment.class */
    public enum TextAlignment {
        Left,
        Centre,
        Right
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.knowm.xchart.style.Styler
    public void setAllStyles() {
        super.setAllStyles();
        this.xAxisTitleVisible = this.theme.isXAxisTitleVisible();
        this.yAxisTitleVisible = this.theme.isYAxisTitleVisible();
        this.axisTitleFont = this.theme.getAxisTitleFont();
        this.xAxisTicksVisible = this.theme.isXAxisTicksVisible();
        this.yAxisTicksVisible = this.theme.isYAxisTicksVisible();
        this.axisTickLabelsFont = this.theme.getAxisTickLabelsFont();
        this.axisTickMarkLength = this.theme.getAxisTickMarkLength();
        this.axisTickPadding = this.theme.getAxisTickPadding();
        this.axisTickMarksColor = this.theme.getAxisTickMarksColor();
        this.axisTickMarksStroke = this.theme.getAxisTickMarksStroke();
        this.axisTickLabelsColor = this.theme.getAxisTickLabelsColor();
        this.isAxisTicksLineVisible = this.theme.isAxisTicksLineVisible();
        this.isAxisTicksMarksVisible = this.theme.isAxisTicksMarksVisible();
        this.plotMargin = this.theme.getPlotMargin();
        this.axisTitlePadding = this.theme.getAxisTitlePadding();
        this.xAxisTickMarkSpacingHint = this.theme.getXAxisTickMarkSpacingHint();
        this.yAxisTickMarkSpacingHint = this.theme.getYAxisTickMarkSpacingHint();
        this.isXAxisLogarithmic = false;
        this.isYAxisLogarithmic = false;
        this.xAxisMin = null;
        this.xAxisMax = null;
        this.yAxisMinMap.clear();
        this.yAxisMaxMap.clear();
        this.isPlotGridVerticalLinesVisible = this.theme.isPlotGridVerticalLinesVisible();
        this.isPlotGridHorizontalLinesVisible = this.theme.isPlotGridHorizontalLinesVisible();
        this.isPlotTicksMarksVisible = this.theme.isPlotTicksMarksVisible();
        this.plotGridLinesColor = this.theme.getPlotGridLinesColor();
        this.plotGridLinesStroke = this.theme.getPlotGridLinesStroke();
        this.errorBarsColor = this.theme.getErrorBarsColor();
        this.isErrorBarsColorSeriesColor = this.theme.isErrorBarsColorSeriesColor();
        this.locale = Locale.getDefault();
        this.timezone = TimeZone.getDefault();
        this.datePattern = null;
        this.xAxisDecimalPattern = null;
        this.yAxisDecimalPattern = null;
        this.yAxisGroupDecimalPatternMap = new HashMap();
        this.xAxisLogarithmicDecadeOnly = true;
        this.yAxisLogarithmicDecadeOnly = true;
    }

    public boolean isXAxisTitleVisible() {
        return this.xAxisTitleVisible;
    }

    public AxesChartStyler setXAxisTitleVisible(boolean xAxisTitleVisible) {
        this.xAxisTitleVisible = xAxisTitleVisible;
        return this;
    }

    public boolean isYAxisTitleVisible() {
        return this.yAxisTitleVisible;
    }

    public AxesChartStyler setYAxisTitleVisible(boolean yAxisTitleVisible) {
        this.yAxisTitleVisible = yAxisTitleVisible;
        return this;
    }

    public AxesChartStyler setAxisTitlesVisible(boolean isVisible) {
        this.xAxisTitleVisible = isVisible;
        this.yAxisTitleVisible = isVisible;
        return this;
    }

    public Font getAxisTitleFont() {
        return this.axisTitleFont;
    }

    public AxesChartStyler setAxisTitleFont(Font axisTitleFont) {
        this.axisTitleFont = axisTitleFont;
        return this;
    }

    public boolean isXAxisTicksVisible() {
        return this.xAxisTicksVisible;
    }

    public AxesChartStyler setXAxisTicksVisible(boolean xAxisTicksVisible) {
        this.xAxisTicksVisible = xAxisTicksVisible;
        return this;
    }

    public boolean isYAxisTicksVisible() {
        return this.yAxisTicksVisible;
    }

    public AxesChartStyler setYAxisTicksVisible(boolean yAxisTicksVisible) {
        this.yAxisTicksVisible = yAxisTicksVisible;
        return this;
    }

    public AxesChartStyler setAxisTicksVisible(boolean isVisible) {
        this.xAxisTicksVisible = isVisible;
        this.yAxisTicksVisible = isVisible;
        return this;
    }

    public Font getAxisTickLabelsFont() {
        return this.axisTickLabelsFont;
    }

    public AxesChartStyler setAxisTickLabelsFont(Font axisTicksFont) {
        this.axisTickLabelsFont = axisTicksFont;
        return this;
    }

    public int getAxisTickMarkLength() {
        return this.axisTickMarkLength;
    }

    public AxesChartStyler setAxisTickMarkLength(int axisTickMarkLength) {
        this.axisTickMarkLength = axisTickMarkLength;
        return this;
    }

    public int getAxisTickPadding() {
        return this.axisTickPadding;
    }

    public AxesChartStyler setAxisTickPadding(int axisTickPadding) {
        this.axisTickPadding = axisTickPadding;
        return this;
    }

    public Color getAxisTickMarksColor() {
        return this.axisTickMarksColor;
    }

    public AxesChartStyler setAxisTickMarksColor(Color axisTickColor) {
        this.axisTickMarksColor = axisTickColor;
        return this;
    }

    public BasicStroke getAxisTickMarksStroke() {
        return this.axisTickMarksStroke;
    }

    public AxesChartStyler setAxisTickMarksStroke(BasicStroke axisTickMarksStroke) {
        this.axisTickMarksStroke = axisTickMarksStroke;
        return this;
    }

    public Color getAxisTickLabelsColor() {
        return this.axisTickLabelsColor;
    }

    public AxesChartStyler setAxisTickLabelsColor(Color axisTickLabelsColor) {
        this.axisTickLabelsColor = axisTickLabelsColor;
        return this;
    }

    public boolean isAxisTicksLineVisible() {
        return this.isAxisTicksLineVisible;
    }

    public AxesChartStyler setAxisTicksLineVisible(boolean isAxisTicksLineVisible) {
        this.isAxisTicksLineVisible = isAxisTicksLineVisible;
        return this;
    }

    public boolean isAxisTicksMarksVisible() {
        return this.isAxisTicksMarksVisible;
    }

    public AxesChartStyler setAxisTicksMarksVisible(boolean isAxisTicksMarksVisible) {
        this.isAxisTicksMarksVisible = isAxisTicksMarksVisible;
        return this;
    }

    public int getPlotMargin() {
        return this.plotMargin;
    }

    public AxesChartStyler setPlotMargin(int plotMargin) {
        this.plotMargin = plotMargin;
        return this;
    }

    public int getAxisTitlePadding() {
        return this.axisTitlePadding;
    }

    public AxesChartStyler setAxisTitlePadding(int axisTitlePadding) {
        this.axisTitlePadding = axisTitlePadding;
        return this;
    }

    public int getXAxisTickMarkSpacingHint() {
        return this.xAxisTickMarkSpacingHint;
    }

    public AxesChartStyler setXAxisTickMarkSpacingHint(int xAxisTickMarkSpacingHint) {
        this.xAxisTickMarkSpacingHint = xAxisTickMarkSpacingHint;
        return this;
    }

    public int getYAxisTickMarkSpacingHint() {
        return this.yAxisTickMarkSpacingHint;
    }

    public AxesChartStyler setYAxisTickMarkSpacingHint(int yAxisTickMarkSpacingHint) {
        if (yAxisTickMarkSpacingHint < 0) {
            throw new IllegalArgumentException("yAxisTickMarkSpacingHint cannot be less than 0 !!!");
        }
        this.yAxisTickMarkSpacingHint = yAxisTickMarkSpacingHint;
        return this;
    }

    public boolean isXAxisLogarithmic() {
        return this.isXAxisLogarithmic;
    }

    public AxesChartStyler setXAxisLogarithmic(boolean isXAxisLogarithmic) {
        this.isXAxisLogarithmic = isXAxisLogarithmic;
        return this;
    }

    public boolean isYAxisLogarithmic() {
        return this.isYAxisLogarithmic;
    }

    public AxesChartStyler setYAxisLogarithmic(boolean isYAxisLogarithmic) {
        this.isYAxisLogarithmic = isYAxisLogarithmic;
        return this;
    }

    public Double getXAxisMin() {
        return this.xAxisMin;
    }

    public AxesChartStyler setXAxisMin(Double xAxisMin) {
        this.xAxisMin = xAxisMin;
        return this;
    }

    public Double getXAxisMax() {
        return this.xAxisMax;
    }

    public AxesChartStyler setXAxisMax(Double xAxisMax) {
        this.xAxisMax = xAxisMax;
        return this;
    }

    public AxesChartStyler setYAxisMin(Integer yAxisGroup, Double yAxisMin) {
        this.yAxisMinMap.put(yAxisGroup, yAxisMin);
        return this;
    }

    public Double getYAxisMin() {
        return this.yAxisMinMap.get(null);
    }

    public AxesChartStyler setYAxisMin(Double yAxisMin) {
        this.yAxisMinMap.put(null, yAxisMin);
        return this;
    }

    public Double getYAxisMin(Integer yAxisGroup) {
        return this.yAxisMinMap.get(yAxisGroup);
    }

    public AxesChartStyler setYAxisMax(Integer yAxisGroup, Double yAxisMax) {
        this.yAxisMaxMap.put(yAxisGroup, yAxisMax);
        return this;
    }

    public Double getYAxisMax() {
        return this.yAxisMaxMap.get(null);
    }

    public AxesChartStyler setYAxisMax(Double yAxisMax) {
        this.yAxisMaxMap.put(null, yAxisMax);
        return this;
    }

    public Double getYAxisMax(Integer yAxisGroup) {
        return this.yAxisMaxMap.get(yAxisGroup);
    }

    public int getXAxisMaxLabelCount() {
        return this.xAxisMaxLabelCount;
    }

    public AxesChartStyler setXAxisMaxLabelCount(int xAxisMaxLabelCount) {
        this.xAxisMaxLabelCount = xAxisMaxLabelCount;
        return this;
    }

    public boolean isPlotGridLinesVisible() {
        return this.isPlotGridHorizontalLinesVisible && this.isPlotGridVerticalLinesVisible;
    }

    public AxesChartStyler setPlotGridLinesVisible(boolean isPlotGridLinesVisible) {
        this.isPlotGridHorizontalLinesVisible = isPlotGridLinesVisible;
        this.isPlotGridVerticalLinesVisible = isPlotGridLinesVisible;
        return this;
    }

    public boolean isPlotGridHorizontalLinesVisible() {
        return this.isPlotGridHorizontalLinesVisible;
    }

    public AxesChartStyler setPlotGridHorizontalLinesVisible(boolean isPlotGridHorizontalLinesVisible) {
        this.isPlotGridHorizontalLinesVisible = isPlotGridHorizontalLinesVisible;
        return this;
    }

    public boolean isPlotGridVerticalLinesVisible() {
        return this.isPlotGridVerticalLinesVisible;
    }

    public AxesChartStyler setPlotGridVerticalLinesVisible(boolean isPlotGridVerticalLinesVisible) {
        this.isPlotGridVerticalLinesVisible = isPlotGridVerticalLinesVisible;
        return this;
    }

    public boolean isPlotTicksMarksVisible() {
        return this.isPlotTicksMarksVisible;
    }

    public AxesChartStyler setPlotTicksMarksVisible(boolean isPlotTicksMarksVisible) {
        this.isPlotTicksMarksVisible = isPlotTicksMarksVisible;
        return this;
    }

    public Color getPlotGridLinesColor() {
        return this.plotGridLinesColor;
    }

    public AxesChartStyler setPlotGridLinesColor(Color plotGridLinesColor) {
        this.plotGridLinesColor = plotGridLinesColor;
        return this;
    }

    public BasicStroke getPlotGridLinesStroke() {
        return this.plotGridLinesStroke;
    }

    public AxesChartStyler setPlotGridLinesStroke(BasicStroke plotGridLinesStroke) {
        this.plotGridLinesStroke = plotGridLinesStroke;
        return this;
    }

    public Color getErrorBarsColor() {
        return this.errorBarsColor;
    }

    public AxesChartStyler setErrorBarsColor(Color errorBarsColor) {
        this.errorBarsColor = errorBarsColor;
        return this;
    }

    public boolean isErrorBarsColorSeriesColor() {
        return this.isErrorBarsColorSeriesColor;
    }

    public AxesChartStyler setErrorBarsColorSeriesColor(boolean isErrorBarsColorSeriesColor) {
        this.isErrorBarsColorSeriesColor = isErrorBarsColorSeriesColor;
        return this;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public AxesChartStyler setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public TimeZone getTimezone() {
        return this.timezone;
    }

    public AxesChartStyler setTimezone(TimeZone timezone) {
        this.timezone = timezone;
        return this;
    }

    public String getDatePattern() {
        return this.datePattern;
    }

    public AxesChartStyler setDatePattern(String datePattern) {
        this.datePattern = datePattern;
        return this;
    }

    public String getXAxisDecimalPattern() {
        return this.xAxisDecimalPattern;
    }

    public AxesChartStyler setXAxisDecimalPattern(String xAxisDecimalPattern) {
        this.xAxisDecimalPattern = xAxisDecimalPattern;
        return this;
    }

    public String getYAxisDecimalPattern() {
        return this.yAxisDecimalPattern;
    }

    public AxesChartStyler setYAxisDecimalPattern(String yAxisDecimalPattern) {
        this.yAxisDecimalPattern = yAxisDecimalPattern;
        return this;
    }

    public Map<Integer, String> getYAxisGroupDecimalPatternMap() {
        return this.yAxisGroupDecimalPatternMap;
    }

    public void putYAxisGroupDecimalPatternMap(int yIndex, String yAxisDecimalPattern) {
        this.yAxisGroupDecimalPatternMap.put(Integer.valueOf(yIndex), yAxisDecimalPattern);
    }

    public boolean isXAxisLogarithmicDecadeOnly() {
        return this.xAxisLogarithmicDecadeOnly;
    }

    public AxesChartStyler setXAxisLogarithmicDecadeOnly(boolean xAxisLogarithmicDecadeOnly) {
        this.xAxisLogarithmicDecadeOnly = xAxisLogarithmicDecadeOnly;
        return this;
    }

    public boolean isYAxisLogarithmicDecadeOnly() {
        return this.yAxisLogarithmicDecadeOnly;
    }

    public AxesChartStyler setYAxisLogarithmicDecadeOnly(boolean yAxisLogarithmicDecadeOnly) {
        this.yAxisLogarithmicDecadeOnly = yAxisLogarithmicDecadeOnly;
        return this;
    }

    public Function<Double, String> getxAxisTickLabelsFormattingFunction() {
        return this.xAxisTickLabelsFormattingFunction;
    }

    public AxesChartStyler setxAxisTickLabelsFormattingFunction(Function<Double, String> xAxisTickLabelsFormattingFunction) {
        this.xAxisTickLabelsFormattingFunction = xAxisTickLabelsFormattingFunction;
        return this;
    }

    public Function<Double, String> getyAxisTickLabelsFormattingFunction() {
        return this.yAxisTickLabelsFormattingFunction;
    }

    public AxesChartStyler setyAxisTickLabelsFormattingFunction(Function<Double, String> yAxisTickLabelsFormattingFunction) {
        this.yAxisTickLabelsFormattingFunction = yAxisTickLabelsFormattingFunction;
        return this;
    }

    public Color getXAxisTickLabelsColor() {
        if (this.xAxisTickLabelsColor == null) {
            return this.axisTickLabelsColor;
        }
        return this.xAxisTickLabelsColor;
    }

    public AxesChartStyler setXAxisTickLabelsColor(Color xAxisTickLabelsColor) {
        this.xAxisTickLabelsColor = xAxisTickLabelsColor;
        return this;
    }

    public Color getYAxisTickLabelsColor() {
        if (this.yAxisTickLabelsColor == null) {
            return this.axisTickLabelsColor;
        }
        return this.yAxisTickLabelsColor;
    }

    public AxesChartStyler setYAxisTickLabelsColor(Color yAxisTickLabelsColor) {
        this.yAxisTickLabelsColor = yAxisTickLabelsColor;
        return this;
    }

    public Color getXAxisTickMarksColor() {
        if (this.xAxisTickMarksColor == null) {
            return this.axisTickMarksColor;
        }
        return this.xAxisTickMarksColor;
    }

    public AxesChartStyler setXAxisTickMarksColor(Color xAxisTickMarksColor) {
        this.xAxisTickMarksColor = xAxisTickMarksColor;
        return this;
    }

    public Color getYAxisTickMarksColor() {
        if (this.yAxisTickMarksColor == null) {
            return this.axisTickMarksColor;
        }
        return this.yAxisTickMarksColor;
    }

    public AxesChartStyler setYAxisTickMarksColor(Color yAxisTickMarksColor) {
        this.yAxisTickMarksColor = yAxisTickMarksColor;
        return this;
    }

    public Color getYAxisGroupTickLabelsColorMap(int yAxisGroup) {
        Color color = this.yAxisGroupTickLabelsColorMap.get(Integer.valueOf(yAxisGroup));
        if (color == null) {
            color = getYAxisTickLabelsColor();
        }
        return color;
    }

    public AxesChartStyler setYAxisGroupTickLabelsColorMap(int yAxisGroup, Color yAxisTickLabelsColor) {
        this.yAxisGroupTickLabelsColorMap.put(Integer.valueOf(yAxisGroup), yAxisTickLabelsColor);
        return this;
    }

    public Color getYAxisGroupTickMarksColorMap(int yAxisGroup) {
        Color color = this.yAxisGroupTickMarksColorMap.get(Integer.valueOf(yAxisGroup));
        if (color == null) {
            color = getYAxisTickMarksColor();
        }
        return color;
    }

    public AxesChartStyler setYAxisGroupTickMarksColorMap(int yAxisGroup, Color yAxisTickMarksColor) {
        this.yAxisGroupTickMarksColorMap.put(Integer.valueOf(yAxisGroup), yAxisTickMarksColor);
        return this;
    }

    public TextAlignment getXAxisLabelAlignment() {
        return this.xAxisLabelAlignment;
    }

    public AxesChartStyler setXAxisLabelAlignment(TextAlignment xAxisLabelAlignment) {
        this.xAxisLabelAlignment = xAxisLabelAlignment;
        return this;
    }

    public TextAlignment getXAxisLabelAlignmentVertical() {
        return this.xAxisLabelAlignmentVertical;
    }

    public AxesChartStyler setXAxisLabelAlignmentVertical(TextAlignment xAxisLabelAlignmentVertical) {
        this.xAxisLabelAlignmentVertical = xAxisLabelAlignmentVertical;
        return this;
    }

    public TextAlignment getYAxisLabelAlignment() {
        return this.yAxisLabelAlignment;
    }

    public AxesChartStyler setYAxisLabelAlignment(TextAlignment yAxisLabelAlignment) {
        this.yAxisLabelAlignment = yAxisLabelAlignment;
        return this;
    }

    public int getXAxisLabelRotation() {
        return this.xAxisLabelRotation;
    }

    public AxesChartStyler setXAxisLabelRotation(int xAxisLabelRotation) {
        this.xAxisLabelRotation = xAxisLabelRotation;
        return this;
    }
}
