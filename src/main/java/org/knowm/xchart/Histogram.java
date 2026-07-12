package org.knowm.xchart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: target.jar:org/knowm/xchart/Histogram.class */
public class Histogram {
    private final Collection<? extends Number> originalData;
    private final int numBins;
    private final double min;
    private final double max;
    private List<Double> xAxisData;
    private List<Double> yAxisData;

    public Histogram(Collection<? extends Number> data, int numBins) {
        sanityCheck(data, numBins);
        this.numBins = numBins;
        this.originalData = data;
        Double tempMax = Double.valueOf(-1.7976931348623157E308d);
        Double tempMin = Double.valueOf(Double.MAX_VALUE);
        for (Number number : data) {
            double value = number.doubleValue();
            tempMax = value > tempMax.doubleValue() ? Double.valueOf(value) : tempMax;
            if (value < tempMin.doubleValue()) {
                tempMin = Double.valueOf(value);
            }
        }
        this.max = tempMax.doubleValue();
        this.min = tempMin.doubleValue();
        init();
    }

    public Histogram(Collection<? extends Number> data, int numBins, double min, double max) {
        sanityCheck(data, numBins);
        if (max < min) {
            throw new IllegalArgumentException("max cannot be less than min!!!");
        }
        this.numBins = numBins;
        this.originalData = data;
        this.min = min;
        this.max = max;
        init();
    }

    private void sanityCheck(Collection<? extends Number> data, int numBins) {
        if (data == null) {
            throw new IllegalArgumentException("Histogram data cannot be null!!!");
        }
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Histogram data cannot be empty!!!");
        }
        if (data.contains(null)) {
            throw new IllegalArgumentException("Histogram data cannot contain null!!!");
        }
        if (numBins <= 0) {
            throw new IllegalArgumentException("Histogram numBins cannot be less than or equal to 0!!!");
        }
    }

    private void init() {
        double[] tempYAxisData = new double[this.numBins];
        double binSize = (this.max - this.min) / this.numBins;
        Iterator<? extends Number> itr = this.originalData.iterator();
        while (itr.hasNext()) {
            double doubleValue = itr.next().doubleValue();
            if (doubleValue >= this.min && doubleValue <= this.max) {
                int bin = (int) ((doubleValue - this.min) / binSize);
                if (bin < this.numBins) {
                    tempYAxisData[bin] = tempYAxisData[bin] + 1.0d;
                } else {
                    int i = bin - 1;
                    tempYAxisData[i] = tempYAxisData[i] + 1.0d;
                }
            }
        }
        this.yAxisData = new ArrayList(this.numBins);
        for (double d : tempYAxisData) {
            this.yAxisData.add(Double.valueOf(d));
        }
        this.xAxisData = new ArrayList(this.numBins);
        for (int i2 = 0; i2 < this.numBins; i2++) {
            this.xAxisData.add(Double.valueOf(((i2 * (this.max - this.min)) / this.numBins) + this.min + (binSize / 2.0d)));
        }
    }

    public List<Double> getxAxisData() {
        return this.xAxisData;
    }

    public List<Double> getyAxisData() {
        return this.yAxisData;
    }

    public Collection<? extends Number> getOriginalData() {
        return this.originalData;
    }

    public int getNumBins() {
        return this.numBins;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }
}
