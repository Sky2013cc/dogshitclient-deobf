package org.knowm.xchart;

import java.util.List;
import org.knowm.xchart.internal.chartpart.RenderableSeries;
import org.knowm.xchart.internal.series.AxesChartSeriesCategory;
import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/BoxSeries.class */
public class BoxSeries extends AxesChartSeriesCategory {
    public BoxSeries(String name, List<?> xData, List<? extends Number> yData, List<? extends Number> extraValues, Series.DataType xAxisDataType) {
        super(name, xData, yData, extraValues, xAxisDataType);
    }

    @Override // org.knowm.xchart.internal.series.Series
    public RenderableSeries.LegendRenderType getLegendRenderType() {
        return null;
    }
}
