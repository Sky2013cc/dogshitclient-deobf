package org.knowm.xchart.style;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.knowm.xchart.style.colors.ChartColor;
import org.knowm.xchart.style.theme.Theme;

/* loaded from: target.jar:org/knowm/xchart/style/DialStyler.class */
public class DialStyler extends Styler {
    private boolean isCircular;
    private boolean axisTicksMarksVisible;
    private Color axisTickMarksColor;
    private BasicStroke axisTickMarksStroke;
    private boolean axisTitleVisible;
    private Font axisTitleFont;
    private int axisTitlePadding;
    private boolean isLabelsVisible;
    private Font labelsFont;
    private double[] axisTickValues = {0.0d, 0.1d, 0.2d, 0.3d, 0.4d, 0.5d, 0.6d, 0.7d, 0.8d, 0.9d, 1.0d};
    private String[] axisTickLabels = {PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES, "10", "20", "30", "40", "50", "60", "70", "80", PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_90_DEGREES, "100"};
    private boolean axisTickLabelsVisible = true;
    private double arcAngle = 270.0d;
    private double donutThickness = 0.17d;
    private double lowerFrom = 0.0d;
    private double lowerTo = 0.2d;
    private Color lowerColor = ChartColor.GREEN.getColor();
    private double middleFrom = 0.2d;
    private double middleTo = 0.8d;
    private Color middleColor = Color.LIGHT_GRAY;
    private double upperFrom = 0.8d;
    private double upperTo = 1.0d;
    private Color upperColor = ChartColor.RED.getColor();
    private double arrowLengthPercentage = 0.7d;
    private double arrowArcAngle = 20.0d;
    private double arrowArcPercentage = 0.15d;
    private Color arrowColor = ChartColor.BLUE.getColor();

    public DialStyler() {
        setAllStyles();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.knowm.xchart.style.Styler
    public void setAllStyles() {
        super.setAllStyles();
        this.isCircular = this.theme.isCircular();
        this.axisTickMarksColor = this.theme.getAxisTickMarksColor();
        this.axisTickMarksStroke = this.theme.getAxisTickMarksStroke();
        this.axisTicksMarksVisible = this.theme.isAxisTicksMarksVisible();
        this.axisTitleVisible = this.theme.isXAxisTitleVisible() || this.theme.isYAxisTitleVisible();
        this.axisTitleFont = this.theme.getAxisTitleFont();
        this.axisTitlePadding = this.theme.getAxisTitlePadding();
        this.isLabelsVisible = true;
        this.labelsFont = this.theme.getBaseFont();
    }

    public DialStyler setTheme(Theme theme) {
        this.theme = theme;
        setAllStyles();
        return this;
    }

    public boolean isCircular() {
        return this.isCircular;
    }

    public DialStyler setCircular(boolean isCircular) {
        this.isCircular = isCircular;
        return this;
    }

    public boolean isAxisTicksMarksVisible() {
        return this.axisTicksMarksVisible;
    }

    public DialStyler setAxisTicksMarksVisible(boolean axisTicksMarksVisible) {
        this.axisTicksMarksVisible = axisTicksMarksVisible;
        return this;
    }

    public Color getAxisTickMarksColor() {
        return this.axisTickMarksColor;
    }

    public DialStyler setAxisTickMarksColor(Color axisTickMarksColor) {
        this.axisTickMarksColor = axisTickMarksColor;
        return this;
    }

    public BasicStroke getAxisTickMarksStroke() {
        return this.axisTickMarksStroke;
    }

    public DialStyler setAxisTickMarksStroke(BasicStroke axisTickMarksStroke) {
        this.axisTickMarksStroke = axisTickMarksStroke;
        return this;
    }

    public boolean isAxisTitleVisible() {
        return this.axisTitleVisible;
    }

    public DialStyler setAxisTitleVisible(boolean axisTitleVisible) {
        this.axisTitleVisible = axisTitleVisible;
        return this;
    }

    public Font getAxisTitleFont() {
        return this.axisTitleFont;
    }

    public DialStyler setAxisTitleFont(Font axisTitleFont) {
        this.axisTitleFont = axisTitleFont;
        return this;
    }

    public int getAxisTitlePadding() {
        return this.axisTitlePadding;
    }

    public DialStyler setAxisTitlePadding(int axisTitlePadding) {
        this.axisTitlePadding = axisTitlePadding;
        return this;
    }

    public double[] getAxisTickValues() {
        return this.axisTickValues;
    }

    public DialStyler setAxisTickValues(double[] axisTickValues) {
        this.axisTickValues = axisTickValues;
        return this;
    }

    public String[] getAxisTickLabels() {
        return this.axisTickLabels;
    }

    public DialStyler setAxisTickLabels(String[] axisTickLabels) {
        this.axisTickLabels = axisTickLabels;
        return this;
    }

    public double getMiddleFrom() {
        return this.middleFrom;
    }

    public DialStyler setMiddleFrom(double middleFrom) {
        this.middleFrom = middleFrom;
        return this;
    }

    public double getMiddleTo() {
        return this.middleTo;
    }

    public DialStyler setMiddleTo(double middleTo) {
        this.middleTo = middleTo;
        return this;
    }

    public Color getMiddleColor() {
        return this.middleColor;
    }

    public DialStyler setMiddleColor(Color middleColor) {
        this.middleColor = middleColor;
        return this;
    }

    public double getLowerFrom() {
        return this.lowerFrom;
    }

    public DialStyler setLowerFrom(double lowerFrom) {
        this.lowerFrom = lowerFrom;
        return this;
    }

    public double getLowerTo() {
        return this.lowerTo;
    }

    public DialStyler setLowerTo(double lowerTo) {
        this.lowerTo = lowerTo;
        return this;
    }

    public Color getLowerColor() {
        return this.lowerColor;
    }

    public DialStyler setLowerColor(Color lowerColor) {
        this.lowerColor = lowerColor;
        return this;
    }

    public double getUpperFrom() {
        return this.upperFrom;
    }

    public DialStyler setUpperFrom(double upperFrom) {
        this.upperFrom = upperFrom;
        return this;
    }

    public double getUpperTo() {
        return this.upperTo;
    }

    public DialStyler setUpperTo(double upperTo) {
        this.upperTo = upperTo;
        return this;
    }

    public Color getUpperColor() {
        return this.upperColor;
    }

    public DialStyler setUpperColor(Color upperColor) {
        this.upperColor = upperColor;
        return this;
    }

    public double getArcAngle() {
        return this.arcAngle;
    }

    public DialStyler setArcAngle(double arcAngle) {
        this.arcAngle = arcAngle;
        return this;
    }

    public boolean isAxisTickLabelsVisible() {
        return this.axisTickLabelsVisible;
    }

    public DialStyler setAxisTickLabelsVisible(boolean axisTickLabelsVisible) {
        this.axisTickLabelsVisible = axisTickLabelsVisible;
        return this;
    }

    public double getDonutThickness() {
        return this.donutThickness;
    }

    public DialStyler setDonutThickness(double donutThickness) {
        this.donutThickness = donutThickness;
        return this;
    }

    public double getArrowLengthPercentage() {
        return this.arrowLengthPercentage;
    }

    public DialStyler setArrowLengthPercentage(double arrowLengthPercentage) {
        this.arrowLengthPercentage = arrowLengthPercentage;
        return this;
    }

    public double getArrowArcAngle() {
        return this.arrowArcAngle;
    }

    public DialStyler setArrowArcAngle(double arrowArcAngle) {
        this.arrowArcAngle = arrowArcAngle;
        return this;
    }

    public double getArrowArcPercentage() {
        return this.arrowArcPercentage;
    }

    public DialStyler setArrowArcPercentage(double arrowArcPercentage) {
        this.arrowArcPercentage = arrowArcPercentage;
        return this;
    }

    public Color getArrowColor() {
        return this.arrowColor;
    }

    public DialStyler setArrowColor(Color color) {
        this.arrowColor = color;
        return this;
    }

    public boolean isLabelsVisible() {
        return this.isLabelsVisible;
    }

    public DialStyler setLabelVisible(boolean labelsVisible) {
        this.isLabelsVisible = labelsVisible;
        return this;
    }

    public Font getLabelsFont() {
        return this.labelsFont;
    }

    public DialStyler setLabelFont(Font labelsFont) {
        this.labelsFont = labelsFont;
        return this;
    }
}
