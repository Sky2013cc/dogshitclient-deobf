package org.knowm.xchart;

import org.knowm.xchart.internal.chartpart.RenderableSeries;
import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/PieSeries.class */
public class PieSeries extends Series {
    private PieSeriesRenderStyle chartPieSeriesRenderStyle;
    private Number value;

    /* loaded from: target.jar:org/knowm/xchart/PieSeries$PieSeriesRenderStyle.class */
    public enum PieSeriesRenderStyle {
        Pie,
        Donut
    }

    public PieSeries(String name, Number value) {
        super(name);
        this.chartPieSeriesRenderStyle = null;
        this.value = value;
    }

    public void replaceData(Number value) {
        this.value = value;
    }

    public PieSeriesRenderStyle getChartPieSeriesRenderStyle() {
        return this.chartPieSeriesRenderStyle;
    }

    public PieSeries setChartPieSeriesRenderStyle(PieSeriesRenderStyle chartPieSeriesRenderStyle) {
        this.chartPieSeriesRenderStyle = chartPieSeriesRenderStyle;
        return this;
    }

    public Number getValue() {
        return this.value;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    @Override // org.knowm.xchart.internal.series.Series
    public RenderableSeries.LegendRenderType getLegendRenderType() {
        return null;
    }
}
