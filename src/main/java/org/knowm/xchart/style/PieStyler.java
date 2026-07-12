package org.knowm.xchart.style;

import java.awt.Color;
import java.awt.Font;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.style.colors.FontColorDetector;
import org.knowm.xchart.style.theme.Theme;

/* loaded from: target.jar:org/knowm/xchart/style/PieStyler.class */
public class PieStyler extends Styler {
    private PieSeries.PieSeriesRenderStyle chartPieSeriesRenderStyle;
    private boolean isCircular;
    private double startAngleInDegrees;
    private double donutThickness;
    private boolean isSumVisible;
    private Font sumFont;
    private String sumFormat;
    private ClockwiseDirectionType clockwiseDirectionType = ClockwiseDirectionType.COUNTER_CLOCKWISE;
    private float sliceBorderWidth = 0.0f;
    private boolean isLabelsVisible;
    private Font labelsFont;
    private Color labelsFontColor;
    private double labelsDistance;
    private LabelType labelType;
    private boolean isForceAllLabelsVisible;
    private boolean isLabelsFontColorAutomaticEnabled;
    private Color labelsFontColorAutomaticLight;
    private Color labelsFontColorAutomaticDark;

    /* loaded from: target.jar:org/knowm/xchart/style/PieStyler$ClockwiseDirectionType.class */
    public enum ClockwiseDirectionType {
        CLOCKWISE,
        COUNTER_CLOCKWISE
    }

    /* loaded from: target.jar:org/knowm/xchart/style/PieStyler$LabelType.class */
    public enum LabelType {
        Value,
        Percentage,
        Name,
        NameAndPercentage,
        NameAndValue
    }

    public PieStyler() {
        setAllStyles();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.knowm.xchart.style.Styler
    public void setAllStyles() {
        super.setAllStyles();
        this.chartPieSeriesRenderStyle = PieSeries.PieSeriesRenderStyle.Pie;
        this.isCircular = this.theme.isCircular();
        this.startAngleInDegrees = this.theme.getStartAngleInDegrees();
        this.donutThickness = this.theme.getDonutThickness();
        this.isSumVisible = this.theme.isSumVisible();
        this.sumFont = this.theme.getSumFont();
        this.isLabelsVisible = true;
        this.labelsFont = this.theme.getBaseFont();
        this.labelsFontColor = this.theme.getChartFontColor();
        this.labelsDistance = this.theme.getLabelsDistance();
        this.labelType = this.theme.getLabelType();
        this.isForceAllLabelsVisible = this.theme.setForceAllLabelsVisible();
        this.isLabelsFontColorAutomaticEnabled = this.theme.isLabelsFontColorAutomaticEnabled();
        this.labelsFontColorAutomaticLight = this.theme.getLabelsFontColorAutomaticLight();
        this.labelsFontColorAutomaticDark = this.theme.getLabelsFontColorAutomaticDark();
    }

    public PieSeries.PieSeriesRenderStyle getDefaultSeriesRenderStyle() {
        return this.chartPieSeriesRenderStyle;
    }

    public PieStyler setDefaultSeriesRenderStyle(PieSeries.PieSeriesRenderStyle chartPieSeriesRenderStyle) {
        this.chartPieSeriesRenderStyle = chartPieSeriesRenderStyle;
        return this;
    }

    public boolean isCircular() {
        return this.isCircular;
    }

    public PieStyler setCircular(boolean isCircular) {
        this.isCircular = isCircular;
        return this;
    }

    public double getStartAngleInDegrees() {
        return this.startAngleInDegrees;
    }

    public PieStyler setStartAngleInDegrees(double startAngleInDegrees) {
        this.startAngleInDegrees = startAngleInDegrees;
        return this;
    }

    public double getLabelsDistance() {
        return this.labelsDistance;
    }

    public PieStyler setLabelsDistance(double labelsDistance) {
        this.labelsDistance = labelsDistance;
        return this;
    }

    public LabelType getLabelType() {
        return this.labelType;
    }

    public PieStyler setLabelType(LabelType labelType) {
        this.labelType = labelType;
        return this;
    }

    public boolean isForceAllLabelsVisible() {
        return this.isForceAllLabelsVisible;
    }

    public PieStyler setForceAllLabelsVisible(boolean forceAllLabelsVisible) {
        this.isForceAllLabelsVisible = forceAllLabelsVisible;
        return this;
    }

    public double getDonutThickness() {
        return this.donutThickness;
    }

    public PieStyler setDonutThickness(double donutThickness) {
        this.donutThickness = donutThickness;
        return this;
    }

    public boolean isSumVisible() {
        return this.isSumVisible;
    }

    public PieStyler setSumFormat(String sumFormat) {
        this.sumFormat = sumFormat;
        return this;
    }

    public String getSumFormat() {
        return this.sumFormat;
    }

    public PieStyler setSumVisible(boolean isSumVisible) {
        this.isSumVisible = isSumVisible;
        return this;
    }

    public Font getSumFont() {
        return this.sumFont;
    }

    public PieStyler setSumFont(Font sumFont) {
        this.sumFont = sumFont;
        return this;
    }

    public PieStyler setSumFontSize(float sumFontSize) {
        this.sumFont = this.sumFont.deriveFont(sumFontSize);
        return this;
    }

    public boolean isLabelsVisible() {
        return this.isLabelsVisible;
    }

    public PieStyler setLabelsVisible(boolean labelsVisible) {
        this.isLabelsVisible = labelsVisible;
        return this;
    }

    public Font getLabelsFont() {
        return this.labelsFont;
    }

    public PieStyler setLabelsFont(Font labelsFont) {
        this.labelsFont = labelsFont;
        return this;
    }

    public Color getLabelsFontColor() {
        return this.labelsFontColor;
    }

    public Color getLabelsFontColor(Color backgroundColor) {
        return FontColorDetector.getAutomaticFontColor(backgroundColor, this.labelsFontColorAutomaticDark, this.labelsFontColorAutomaticLight);
    }

    public PieStyler setLabelsFontColor(Color labelsFontColor) {
        this.labelsFontColor = labelsFontColor;
        return this;
    }

    public boolean isLabelsFontColorAutomaticEnabled() {
        return this.isLabelsFontColorAutomaticEnabled;
    }

    public PieStyler setLabelsFontColorAutomaticEnabled(boolean isLabelsFontColorAutomaticEnabled) {
        this.isLabelsFontColorAutomaticEnabled = isLabelsFontColorAutomaticEnabled;
        return this;
    }

    public Color getLabelsFontColorAutomaticLight() {
        return this.labelsFontColorAutomaticLight;
    }

    public PieStyler setLabelsFontColorAutomaticLight(Color labelsFontColorAutomaticLight) {
        this.labelsFontColorAutomaticLight = labelsFontColorAutomaticLight;
        return this;
    }

    public Color getLabelsFontColorAutomaticDark() {
        return this.labelsFontColorAutomaticDark;
    }

    public PieStyler setLabelsFontColorAutomaticDark(Color labelsFontColorAutomaticDark) {
        this.labelsFontColorAutomaticDark = labelsFontColorAutomaticDark;
        return this;
    }

    public PieStyler setTheme(Theme theme) {
        this.theme = theme;
        setAllStyles();
        return this;
    }

    public ClockwiseDirectionType getClockwiseDirectionType() {
        return this.clockwiseDirectionType;
    }

    public PieStyler setClockwiseDirectionType(ClockwiseDirectionType clockwiseDirectionType) {
        this.clockwiseDirectionType = clockwiseDirectionType;
        return this;
    }

    public PieStyler setSliceBorderWidth(double sliceBorderWidth) {
        this.sliceBorderWidth = (float) sliceBorderWidth;
        return this;
    }

    public float getSliceBorderWidth() {
        return this.sliceBorderWidth;
    }
}
