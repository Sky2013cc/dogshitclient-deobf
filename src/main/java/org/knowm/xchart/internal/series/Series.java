package org.knowm.xchart.internal.series;

import java.awt.Color;
import org.knowm.xchart.internal.chartpart.RenderableSeries;

/* loaded from: target.jar:org/knowm/xchart/internal/series/Series.class */
public abstract class Series {
    private final String name;
    private String label;
    private Color fillColor;
    private boolean showInLegend = true;
    private boolean isEnabled = true;
    private int yAxisGroup = 0;
    private String yAxisDecimalPattern;

    /* loaded from: target.jar:org/knowm/xchart/internal/series/Series$DataType.class */
    public enum DataType {
        Number,
        Date,
        String
    }

    public abstract RenderableSeries.LegendRenderType getLegendRenderType();

    /* JADX INFO: Access modifiers changed from: protected */
    public Series(String name) {
        if (name == null || name.length() < 1) {
            throw new IllegalArgumentException("Series name cannot be null or zero-length!!!");
        }
        this.name = name;
        this.label = name;
    }

    public Color getFillColor() {
        return this.fillColor;
    }

    public Series setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public String getLabel() {
        return this.label;
    }

    public Series setLabel(String label) {
        this.label = label;
        return this;
    }

    public boolean isShowInLegend() {
        return this.showInLegend;
    }

    public Series setShowInLegend(boolean showInLegend) {
        this.showInLegend = showInLegend;
        return this;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public Series setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public int getYAxisGroup() {
        return this.yAxisGroup;
    }

    public Series setYAxisGroup(int yAxisGroup) {
        this.yAxisGroup = yAxisGroup;
        return this;
    }

    public String getYAxisDecimalPattern() {
        return this.yAxisDecimalPattern;
    }

    public Series setYAxisDecimalPattern(String yAxisDecimalPattern) {
        this.yAxisDecimalPattern = yAxisDecimalPattern;
        return this;
    }
}
