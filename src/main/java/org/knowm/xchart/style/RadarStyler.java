package org.knowm.xchart.style;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import org.knowm.xchart.style.theme.Theme;

/* loaded from: target.jar:org/knowm/xchart/style/RadarStyler.class */
public class RadarStyler extends Styler {
    private RadarRenderStyle radarRenderStyle;
    private boolean isCircular;
    private double startAngleInDegrees;
    private boolean radiiTicksMarksVisible;
    private Color radiiTickMarksColor;
    private BasicStroke radiiTickMarksStroke;
    private int radiiTickMarksCount;
    private boolean isRadiiTitleVisible;
    private Font radiiTitleFont;
    private int radiiTitlePadding;
    private int markerSize;
    private boolean isSeriesFilled = true;

    /* loaded from: target.jar:org/knowm/xchart/style/RadarStyler$RadarRenderStyle.class */
    public enum RadarRenderStyle {
        Polygon,
        Circle
    }

    public RadarStyler() {
        setAllStyles();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.knowm.xchart.style.Styler
    public void setAllStyles() {
        super.setAllStyles();
        this.radarRenderStyle = RadarRenderStyle.Polygon;
        this.isCircular = this.theme.isCircular();
        this.startAngleInDegrees = this.theme.getStartAngleInDegrees();
        this.markerSize = this.theme.getMarkerSize();
        this.radiiTicksMarksVisible = this.theme.isAxisTicksMarksVisible();
        this.radiiTickMarksColor = this.theme.getPlotGridLinesColor();
        this.radiiTickMarksStroke = this.theme.getPlotGridLinesStroke();
        this.radiiTickMarksCount = 5;
        this.isRadiiTitleVisible = this.theme.isXAxisTitleVisible() || this.theme.isYAxisTitleVisible();
        this.radiiTitleFont = this.theme.getAxisTitleFont();
        this.radiiTitlePadding = this.theme.getAxisTitlePadding();
    }

    public boolean isCircular() {
        return this.isCircular;
    }

    public RadarStyler setCircular(boolean isCircular) {
        this.isCircular = isCircular;
        return this;
    }

    public double getStartAngleInDegrees() {
        return this.startAngleInDegrees;
    }

    public RadarStyler setStartAngleInDegrees(double startAngleInDegrees) {
        this.startAngleInDegrees = startAngleInDegrees;
        return this;
    }

    public RadarStyler setTheme(Theme theme) {
        this.theme = theme;
        setAllStyles();
        return this;
    }

    @Override // org.knowm.xchart.style.Styler
    public int getMarkerSize() {
        return this.markerSize;
    }

    @Override // org.knowm.xchart.style.Styler
    public RadarStyler setMarkerSize(int markerSize) {
        this.markerSize = markerSize;
        return this;
    }

    public boolean isRadiiTicksMarksVisible() {
        return this.radiiTicksMarksVisible;
    }

    public RadarStyler setRadiiTicksMarksVisible(boolean radiiTicksMarksVisible) {
        this.radiiTicksMarksVisible = radiiTicksMarksVisible;
        return this;
    }

    public Color getRadiiTickMarksColor() {
        return this.radiiTickMarksColor;
    }

    public RadarStyler setRadiiTickMarksColor(Color radiiTickMarksColor) {
        this.radiiTickMarksColor = radiiTickMarksColor;
        return this;
    }

    public BasicStroke getRadiiTickMarksStroke() {
        return this.radiiTickMarksStroke;
    }

    public RadarStyler setRadiiTickMarksStroke(BasicStroke radiiTickMarksStroke) {
        this.radiiTickMarksStroke = radiiTickMarksStroke;
        return this;
    }

    public boolean isRadiiTitleVisible() {
        return this.isRadiiTitleVisible;
    }

    public RadarStyler setRadiiTitleVisible(boolean radiiTitleVisible) {
        this.isRadiiTitleVisible = radiiTitleVisible;
        return this;
    }

    public Font getRadiiTitleFont() {
        return this.radiiTitleFont;
    }

    public RadarStyler setRadiiTitleFont(Font radiiTitleFont) {
        this.radiiTitleFont = radiiTitleFont;
        return this;
    }

    public int getRadiiTitlePadding() {
        return this.radiiTitlePadding;
    }

    public RadarStyler setRadiiTitlePadding(int radiiTitlePadding) {
        this.radiiTitlePadding = radiiTitlePadding;
        return this;
    }

    public int getRadiiTickMarksCount() {
        return this.radiiTickMarksCount;
    }

    public RadarStyler setRadiiTickMarksCount(int radiiTickMarksCount) {
        this.radiiTickMarksCount = radiiTickMarksCount;
        return this;
    }

    public boolean isSeriesFilled() {
        return this.isSeriesFilled;
    }

    public RadarStyler setSeriesFilled(boolean seriesFilled) {
        this.isSeriesFilled = seriesFilled;
        return this;
    }

    public RadarRenderStyle getRadarRenderStyle() {
        return this.radarRenderStyle;
    }

    public RadarStyler setRadarRenderStyle(RadarRenderStyle radarRenderStyle) {
        this.radarRenderStyle = radarRenderStyle;
        return this;
    }
}
