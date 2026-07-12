package org.knowm.xchart;

import org.knowm.xchart.internal.ChartBuilder;

/* loaded from: target.jar:org/knowm/xchart/HeatMapChartBuilder.class */
public class HeatMapChartBuilder extends ChartBuilder<HeatMapChartBuilder, HeatMapChart> {
    String xAxisTitle = "";
    String yAxisTitle = "";

    public HeatMapChartBuilder xAxisTitle(String xAxisTitle) {
        this.xAxisTitle = xAxisTitle;
        return this;
    }

    public HeatMapChartBuilder yAxisTitle(String yAxisTitle) {
        this.yAxisTitle = yAxisTitle;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.knowm.xchart.internal.ChartBuilder
    public HeatMapChart build() {
        return new HeatMapChart(this);
    }
}
