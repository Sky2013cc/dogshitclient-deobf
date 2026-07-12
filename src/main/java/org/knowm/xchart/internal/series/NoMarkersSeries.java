package org.knowm.xchart.internal.series;

import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/internal/series/NoMarkersSeries.class */
public abstract class NoMarkersSeries extends AxesChartSeriesNumericalNoErrorBars {
    /* JADX INFO: Access modifiers changed from: protected */
    public NoMarkersSeries(String name, double[] xData, double[] yData, double[] extraValues, Series.DataType axisType) {
        super(name, xData, yData, extraValues, axisType);
        this.extraValues = extraValues;
        calculateMinMax();
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeriesNumericalNoErrorBars, org.knowm.xchart.internal.series.AxesChartSeries
    protected void calculateMinMax() {
        double[] xMinMax = findMinMax(this.xData);
        this.xMin = xMinMax[0];
        this.xMax = xMinMax[1];
        double[] yMinMax = findMinMax(this.yData);
        this.yMin = yMinMax[0];
        this.yMax = yMinMax[1];
    }
}
