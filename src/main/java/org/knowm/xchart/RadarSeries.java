package org.knowm.xchart;

import java.awt.BasicStroke;
import java.awt.Color;
import org.knowm.xchart.internal.chartpart.RenderableSeries;
import org.knowm.xchart.internal.series.MarkerSeries;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.markers.Marker;

/* loaded from: target.jar:org/knowm/xchart/RadarSeries.class */
public class RadarSeries extends MarkerSeries {
    private BasicStroke stroke;
    private Color lineColor;
    private float lineWidth;
    private Marker marker;
    private Color markerColor;
    private double[] values;
    private String[] tooltipOverrides;

    public RadarSeries(String name, double[] values, String[] tooltipOverrides) {
        super(name, Series.DataType.Number);
        this.values = values;
        this.tooltipOverrides = tooltipOverrides;
    }

    public double[] getValues() {
        return this.values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public String[] getTooltipOverrides() {
        return this.tooltipOverrides;
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeries
    protected void calculateMinMax() {
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeries
    public BasicStroke getLineStyle() {
        return this.stroke;
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeries
    public RadarSeries setLineStyle(BasicStroke basicStroke) {
        this.stroke = basicStroke;
        if (this.lineWidth > 0.0f) {
            this.stroke = new BasicStroke(this.lineWidth, this.stroke.getEndCap(), this.stroke.getLineJoin(), this.stroke.getMiterLimit(), this.stroke.getDashArray(), this.stroke.getDashPhase());
        }
        return this;
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeries
    public Color getLineColor() {
        return this.lineColor;
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeries
    public RadarSeries setLineColor(Color color) {
        this.lineColor = color;
        return this;
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeries
    public float getLineWidth() {
        return this.lineWidth;
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeries
    public RadarSeries setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    @Override // org.knowm.xchart.internal.series.MarkerSeries
    public Marker getMarker() {
        return this.marker;
    }

    @Override // org.knowm.xchart.internal.series.MarkerSeries
    public RadarSeries setMarker(Marker marker) {
        this.marker = marker;
        return this;
    }

    @Override // org.knowm.xchart.internal.series.MarkerSeries
    public Color getMarkerColor() {
        return this.markerColor;
    }

    @Override // org.knowm.xchart.internal.series.MarkerSeries
    public RadarSeries setMarkerColor(Color color) {
        this.markerColor = color;
        return this;
    }

    @Override // org.knowm.xchart.internal.series.Series
    public RenderableSeries.LegendRenderType getLegendRenderType() {
        return RenderableSeries.LegendRenderType.Line;
    }

    public void setTooltipOverrides(String[] tooltipOverrides) {
        this.tooltipOverrides = tooltipOverrides;
    }
}
