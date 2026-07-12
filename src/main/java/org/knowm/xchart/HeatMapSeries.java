package org.knowm.xchart;

import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.knowm.xchart.internal.chartpart.RenderableSeries;
import org.knowm.xchart.internal.series.AxesChartSeries;
import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/HeatMapSeries.class */
public class HeatMapSeries extends AxesChartSeries {
    List<?> xData;
    List<?> yData;
    List<? extends Number[]> heatData;
    double min;
    double max;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public HeatMapSeries(String name, List<?> xData, List<?> yData, List<Number[]> list) {
        super(name, getDataType(xData), getDataType(yData));
        this.xData = xData;
        this.yData = yData;
        this.heatData = list;
        calculateMinMax();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void replaceData(List<?> xData, List<?> yData, List<Number[]> list) {
        this.xData = xData;
        this.yData = yData;
        this.heatData = list;
        calculateMinMax();
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeries
    protected void calculateMinMax() {
        Number number;
        this.min = Double.MAX_VALUE;
        this.max = Double.MIN_VALUE;
        for (Number[] numbers : this.heatData) {
            if (numbers != null && (number = numbers[2]) != null) {
                if (this.min > number.doubleValue()) {
                    this.min = number.doubleValue();
                }
                if (this.max < number.doubleValue()) {
                    this.max = number.doubleValue();
                }
            }
        }
        this.xMin = getMin(this.xData, this.xMin);
        this.xMax = getMax(this.xData, this.xMax);
        this.yMin = getMin(this.yData, this.yMin);
        this.yMax = getMax(this.yData, this.yMax);
    }

    private static double getMin(List<?> list, double defaultValue) {
        if (list.isEmpty() || !(list.get(0) instanceof Number)) {
            return defaultValue;
        }
        return ((Number) list.stream().map(x -> {
            return (Number) x;
        }).min(Comparator.comparing((v0) -> {
            return v0.doubleValue();
        })).orElse(Double.valueOf(defaultValue))).doubleValue();
    }

    private static double getMax(List<?> list, double defaultValue) {
        if (list.isEmpty() || !(list.get(0) instanceof Number)) {
            return defaultValue;
        }
        return ((Number) list.stream().map(x -> {
            return (Number) x;
        }).max(Comparator.comparing((v0) -> {
            return v0.doubleValue();
        })).orElse(Double.valueOf(defaultValue))).doubleValue();
    }

    @Override // org.knowm.xchart.internal.series.Series
    public RenderableSeries.LegendRenderType getLegendRenderType() {
        return null;
    }

    private static Series.DataType getDataType(List<?> data) {
        Series.DataType axisType;
        Iterator<?> itr = data.iterator();
        Object dataPoint = itr.next();
        if (dataPoint instanceof Number) {
            axisType = Series.DataType.Number;
        } else if (dataPoint instanceof Date) {
            axisType = Series.DataType.Date;
        } else if (dataPoint instanceof String) {
            axisType = Series.DataType.String;
        } else {
            throw new IllegalArgumentException("Series data must be either Number, Date or String type!!!");
        }
        return axisType;
    }

    public List<?> getXData() {
        return this.xData;
    }

    public List<?> getYData() {
        return this.yData;
    }

    public List<? extends Number[]> getHeatData() {
        return this.heatData;
    }

    public double getMin() {
        return this.min;
    }

    public HeatMapSeries setMin(double min) {
        this.min = min;
        return this;
    }

    public double getMax() {
        return this.max;
    }

    public HeatMapSeries setMax(double max) {
        this.max = max;
        return this;
    }
}
