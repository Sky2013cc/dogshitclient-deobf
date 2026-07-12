package org.knowm.xchart.internal.series;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/internal/series/AxesChartSeriesCategory.class */
public abstract class AxesChartSeriesCategory extends MarkerSeries {
    List<?> xData;
    List<? extends Number> yData;
    List<? extends Number> extraValues;

    public AxesChartSeriesCategory(String name, List<?> xData, List<? extends Number> yData, List<? extends Number> extraValues, Series.DataType xAxisDataType) {
        super(name, xAxisDataType);
        this.xData = xData;
        this.yData = yData;
        this.extraValues = extraValues;
        calculateMinMax();
    }

    public void replaceData(List<?> newXData, List<? extends Number> newYData, List<? extends Number> newExtraValues) {
        if (newExtraValues != null && newExtraValues.size() != newYData.size()) {
            throw new IllegalArgumentException("error bars and Y-Axis sizes are not the same!!!");
        }
        if (newXData.size() != newYData.size()) {
            throw new IllegalArgumentException("X and Y-Axis sizes are not the same!!!");
        }
        this.xData = newXData;
        this.yData = newYData;
        this.extraValues = newExtraValues;
        calculateMinMax();
    }

    public void replaceData(List<? extends Number> newYData) {
        this.yData = newYData;
        calculateMinMax();
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeries
    protected void calculateMinMax() {
        double[] yMinMax;
        double[] xMinMax = findMinMax(this.xData, this.xAxisDataType);
        this.xMin = xMinMax[0];
        this.xMax = xMinMax[1];
        if (this.extraValues == null) {
            yMinMax = findMinMax(this.yData, this.yAxisType);
        } else {
            yMinMax = findMinMaxWithErrorBars(this.yData, this.extraValues);
        }
        this.yMin = yMinMax[0];
        this.yMax = yMinMax[1];
    }

    private double[] findMinMaxWithErrorBars(Collection<? extends Number> data, Collection<? extends Number> errorBars) {
        double min = Double.MAX_VALUE;
        double max = -1.7976931348623157E308d;
        Iterator<? extends Number> itr = data.iterator();
        Iterator<? extends Number> ebItr = errorBars.iterator();
        while (itr.hasNext()) {
            double bigDecimal = itr.next().doubleValue();
            double eb = ebItr.next().doubleValue();
            if (bigDecimal - eb < min) {
                min = bigDecimal - eb;
            }
            if (bigDecimal + eb > max) {
                max = bigDecimal + eb;
            }
        }
        return new double[]{min, max};
    }

    double[] findMinMax(Collection<?> data, Series.DataType dataType) {
        double min = Double.MAX_VALUE;
        double max = -1.7976931348623157E308d;
        for (Object dataPoint : data) {
            if (dataPoint != null) {
                double value = 0.0d;
                if (dataType == Series.DataType.Number) {
                    value = ((Number) dataPoint).doubleValue();
                } else if (dataType == Series.DataType.Date) {
                    Date date = (Date) dataPoint;
                    value = date.getTime();
                } else if (dataType == Series.DataType.String) {
                    return new double[]{Double.NaN, Double.NaN};
                }
                if (value < min) {
                    min = value;
                }
                if (value > max) {
                    max = value;
                }
            }
        }
        return new double[]{min, max};
    }

    public Collection<?> getXData() {
        return this.xData;
    }

    public Collection<? extends Number> getYData() {
        return this.yData;
    }

    public Collection<? extends Number> getExtraValues() {
        return this.extraValues;
    }
}
