package org.knowm.xchart.internal.series;

import java.util.Arrays;
import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/internal/series/AxesChartSeriesNumericalNoErrorBars.class */
public abstract class AxesChartSeriesNumericalNoErrorBars extends MarkerSeries {
    double[] xDataAll;
    double[] yDataAll;
    double[] extraValuesAll;
    double[] xData;
    double[] yData;
    double[] extraValues;

    public AxesChartSeriesNumericalNoErrorBars(String name, double[] xData, double[] yData, double[] extraValues, Series.DataType xAxisDataType) {
        super(name, xAxisDataType);
        this.xDataAll = xData;
        this.yDataAll = yData;
        this.extraValuesAll = extraValues;
        this.xData = xData;
        this.yData = yData;
        this.extraValues = extraValues;
        calculateMinMax();
    }

    public void replaceData(double[] newXData, double[] newYData, double[] newExtraValues) {
        if (newExtraValues != null && newExtraValues.length != newYData.length) {
            throw new IllegalArgumentException("error bars and Y-Axis sizes are not the same!!!");
        }
        if (newXData.length != newYData.length) {
            throw new IllegalArgumentException("X and Y-Axis sizes are not the same!!!");
        }
        this.xDataAll = newXData;
        this.yDataAll = newYData;
        this.extraValuesAll = newExtraValues;
        this.xData = newXData;
        this.yData = newYData;
        this.extraValues = newExtraValues;
        calculateMinMax();
    }

    public void filterXByIndex(int startIndex, int endIndex) {
        int startIndex2 = Math.max(0, startIndex);
        int endIndex2 = Math.min(this.yDataAll.length, endIndex);
        this.xData = Arrays.copyOfRange(this.xDataAll, startIndex2, endIndex2);
        this.yData = Arrays.copyOfRange(this.yDataAll, startIndex2, endIndex2);
        if (this.extraValuesAll != null) {
            this.extraValues = Arrays.copyOfRange(this.extraValuesAll, startIndex2, endIndex2);
        }
        calculateMinMax();
    }

    public boolean filterXByValue(double minValue, double maxValue) {
        int length = this.xDataAll.length;
        boolean[] filterResult = new boolean[length];
        int remainingDataCount = 0;
        for (int i = 0; i < length; i++) {
            double val = this.xDataAll[i];
            boolean result = val >= minValue && val <= maxValue;
            filterResult[i] = result;
            if (result) {
                remainingDataCount++;
            }
        }
        if (remainingDataCount == length) {
            return false;
        }
        this.xData = new double[remainingDataCount];
        this.yData = new double[remainingDataCount];
        boolean extra = this.extraValuesAll != null;
        if (extra) {
            this.extraValues = new double[remainingDataCount];
        }
        int ind = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (filterResult[i2]) {
                this.xData[ind] = this.xDataAll[i2];
                this.yData[ind] = this.yDataAll[i2];
                if (extra) {
                    this.extraValues[ind] = this.extraValuesAll[i2];
                }
                ind++;
            }
        }
        calculateMinMax();
        return true;
    }

    public void resetFilter() {
        this.xData = this.xDataAll;
        this.yData = this.yDataAll;
        this.extraValues = this.extraValuesAll;
        calculateMinMax();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double[] findMinMax(double[] data) {
        double min = Double.MAX_VALUE;
        double max = -1.7976931348623157E308d;
        for (double dataPoint : data) {
            if (!Double.isNaN(dataPoint)) {
                if (dataPoint < min) {
                    min = dataPoint;
                }
                if (dataPoint > max) {
                    max = dataPoint;
                }
            }
        }
        return new double[]{min, max};
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeries
    protected void calculateMinMax() {
        double[] yMinMax;
        double[] xMinMax = findMinMax(this.xData);
        this.xMin = xMinMax[0];
        this.xMax = xMinMax[1];
        if (this.extraValues == null) {
            yMinMax = findMinMax(this.yData);
        } else {
            yMinMax = findMinMaxWithErrorBars(this.yData, this.extraValues);
        }
        this.yMin = yMinMax[0];
        this.yMax = yMinMax[1];
    }

    private double[] findMinMaxWithErrorBars(double[] data, double[] errorBars) {
        double min = Double.MAX_VALUE;
        double max = -1.7976931348623157E308d;
        for (int i = 0; i < data.length; i++) {
            double d = data[i];
            double eb = errorBars[i];
            if (d - eb < min) {
                min = d - eb;
            }
            if (d + eb > max) {
                max = d + eb;
            }
        }
        return new double[]{min, max};
    }

    public boolean isAllXData() {
        return this.xData.length == this.xDataAll.length;
    }

    public double[] getXData() {
        return this.xData;
    }

    public double[] getYData() {
        return this.yData;
    }

    public double[] getExtraValues() {
        return this.extraValues;
    }
}
