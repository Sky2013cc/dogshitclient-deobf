package org.knowm.xchart.internal.series;

import java.awt.BasicStroke;
import java.awt.Color;
import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/internal/series/AxesChartSeries.class */
public abstract class AxesChartSeries extends Series {
    final Series.DataType xAxisDataType;
    final Series.DataType yAxisType;
    protected double xMin;
    protected double xMax;
    protected double yMin;
    protected double yMax;
    private BasicStroke stroke;
    private Color lineColor;
    private float lineWidth;

    protected abstract void calculateMinMax();

    /* JADX INFO: Access modifiers changed from: protected */
    public AxesChartSeries(String name, Series.DataType xAxisDataType) {
        super(name);
        this.lineWidth = -1.0f;
        this.xAxisDataType = xAxisDataType;
        this.yAxisType = Series.DataType.Number;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AxesChartSeries(String name, Series.DataType xAxisDataType, Series.DataType yAxisDataType) {
        super(name);
        this.lineWidth = -1.0f;
        this.xAxisDataType = xAxisDataType;
        this.yAxisType = yAxisDataType;
    }

    public double getXMin() {
        return this.xMin;
    }

    public double getXMax() {
        return this.xMax;
    }

    public double getYMin() {
        return this.yMin;
    }

    public double getYMax() {
        return this.yMax;
    }

    public BasicStroke getLineStyle() {
        return this.stroke;
    }

    public AxesChartSeries setLineStyle(BasicStroke basicStroke) {
        this.stroke = basicStroke;
        if (this.lineWidth > 0.0f) {
            this.stroke = new BasicStroke(this.lineWidth, this.stroke.getEndCap(), this.stroke.getLineJoin(), this.stroke.getMiterLimit(), this.stroke.getDashArray(), this.stroke.getDashPhase());
        }
        return this;
    }

    public Color getLineColor() {
        return this.lineColor;
    }

    public AxesChartSeries setLineColor(Color color) {
        this.lineColor = color;
        return this;
    }

    public float getLineWidth() {
        return this.lineWidth;
    }

    public AxesChartSeries setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    public Series.DataType getxAxisDataType() {
        return this.xAxisDataType;
    }

    public Series.DataType getyAxisDataType() {
        return this.yAxisType;
    }
}
