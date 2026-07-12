package org.knowm.xchart;

import org.knowm.xchart.internal.chartpart.RenderableSeries;
import org.knowm.xchart.internal.series.AxesChartSeriesNumericalNoErrorBars;
import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/XYSeries.class */
public class XYSeries extends AxesChartSeriesNumericalNoErrorBars {
    private XYSeriesRenderStyle xySeriesRenderStyle;
    private boolean smooth;

    public XYSeries(String name, double[] xData, double[] yData, double[] errorBars, Series.DataType axisType) {
        super(name, xData, yData, errorBars, axisType);
        this.xySeriesRenderStyle = null;
    }

    public XYSeriesRenderStyle getXYSeriesRenderStyle() {
        return this.xySeriesRenderStyle;
    }

    public XYSeries setXYSeriesRenderStyle(XYSeriesRenderStyle chartXYSeriesRenderStyle) {
        this.xySeriesRenderStyle = chartXYSeriesRenderStyle;
        return this;
    }

    @Override // org.knowm.xchart.internal.series.Series
    public RenderableSeries.LegendRenderType getLegendRenderType() {
        return this.xySeriesRenderStyle.getLegendRenderType();
    }

    public boolean isSmooth() {
        return this.smooth;
    }

    public void setSmooth(boolean smooth) {
        this.smooth = smooth;
    }

    /* loaded from: target.jar:org/knowm/xchart/XYSeries$XYSeriesRenderStyle.class */
    public enum XYSeriesRenderStyle implements RenderableSeries {
        Line(RenderableSeries.LegendRenderType.Line),
        Area(RenderableSeries.LegendRenderType.Line),
        Step(RenderableSeries.LegendRenderType.Line),
        StepArea(RenderableSeries.LegendRenderType.Line),
        PolygonArea(RenderableSeries.LegendRenderType.Box),
        Scatter(RenderableSeries.LegendRenderType.Scatter);

        private final RenderableSeries.LegendRenderType legendRenderType;

        XYSeriesRenderStyle(RenderableSeries.LegendRenderType legendRenderType) {
            this.legendRenderType = legendRenderType;
        }

        @Override // org.knowm.xchart.internal.chartpart.RenderableSeries
        public RenderableSeries.LegendRenderType getLegendRenderType() {
            return this.legendRenderType;
        }
    }
}
