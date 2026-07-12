package org.knowm.xchart;

import java.awt.Color;
import org.knowm.xchart.internal.chartpart.RenderableSeries;
import org.knowm.xchart.internal.series.MarkerSeries;
import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/OHLCSeries.class */
public class OHLCSeries extends MarkerSeries {
    private double[] xData;
    private double[] openData;
    private double[] highData;
    private double[] lowData;
    private double[] closeData;
    private long[] volumeData;
    private double[] yData;
    private OHLCSeriesRenderStyle ohlcSeriesRenderStyle;
    private Color upColor;
    private Color downColor;

    public OHLCSeries(String name, double[] xData, double[] openData, double[] highData, double[] lowData, double[] closeData, Series.DataType xAxisDataType) {
        this(name, xData, openData, highData, lowData, closeData, null, xAxisDataType);
    }

    public OHLCSeries(String name, double[] xData, double[] openData, double[] highData, double[] lowData, double[] closeData, long[] volumeData, Series.DataType xAxisDataType) {
        super(name, xAxisDataType);
        this.xData = xData;
        this.openData = openData;
        this.highData = highData;
        this.lowData = lowData;
        this.closeData = closeData;
        this.volumeData = volumeData;
        calculateMinMax();
    }

    public OHLCSeries(String name, double[] xData, double[] yData, Series.DataType xAxisDataType) {
        super(name, xAxisDataType);
        this.xData = xData;
        this.yData = yData;
        this.ohlcSeriesRenderStyle = OHLCSeriesRenderStyle.Line;
        calculateMinMax();
    }

    public OHLCSeriesRenderStyle getOhlcSeriesRenderStyle() {
        return this.ohlcSeriesRenderStyle;
    }

    public OHLCSeries setOhlcSeriesRenderStyle(OHLCSeriesRenderStyle ohlcSeriesRenderStyle) {
        if (this.yData == null && ohlcSeriesRenderStyle == OHLCSeriesRenderStyle.Line) {
            throw new IllegalArgumentException("Series name >" + getName() + "<, yData is equal to null and cannot be set to OHLCSeriesRenderStyle.Line");
        }
        if (this.yData != null && ohlcSeriesRenderStyle != OHLCSeriesRenderStyle.Line) {
            throw new IllegalArgumentException("Series name >" + getName() + "<, yData is not equal to null and can only be set to OHLCSeriesRenderStyle.Line");
        }
        this.ohlcSeriesRenderStyle = ohlcSeriesRenderStyle;
        return this;
    }

    public Color getUpColor() {
        return this.upColor;
    }

    public OHLCSeries setUpColor(Color color) {
        this.upColor = color;
        return this;
    }

    public Color getDownColor() {
        return this.downColor;
    }

    public OHLCSeries setDownColor(Color color) {
        this.downColor = color;
        return this;
    }

    @Override // org.knowm.xchart.internal.series.Series
    public RenderableSeries.LegendRenderType getLegendRenderType() {
        return this.ohlcSeriesRenderStyle.getLegendRenderType();
    }

    void replaceData(double[] newXData, double[] newOpenData, double[] newHighData, double[] newLowData, double[] newCloseData) {
        replaceData(newXData, newOpenData, newHighData, newLowData, newCloseData, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceData(double[] newXData, double[] newOpenData, double[] newHighData, double[] newLowData, double[] newCloseData, long[] newVolumeData) {
        this.xData = newXData;
        this.openData = newOpenData;
        this.highData = newHighData;
        this.lowData = newLowData;
        this.closeData = newCloseData;
        this.volumeData = newVolumeData;
        calculateMinMax();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceData(double[] newXData, double[] newYData) {
        this.xData = newXData;
        this.yData = newYData;
        calculateMinMax();
    }

    private double[] findMinMax(double[] lows, double[] highs) {
        double min = Double.MAX_VALUE;
        double max = -1.7976931348623157E308d;
        for (int i = 0; i < highs.length; i++) {
            if (!Double.isNaN(highs[i]) && highs[i] > max) {
                max = highs[i];
            }
            if (!Double.isNaN(lows[i]) && lows[i] < min) {
                min = lows[i];
            }
        }
        return new double[]{min, max};
    }

    @Override // org.knowm.xchart.internal.series.AxesChartSeries
    protected void calculateMinMax() {
        double[] yMinMax;
        double[] xMinMax = findMinMax(this.xData, this.xData);
        this.xMin = xMinMax[0];
        this.xMax = xMinMax[1];
        if (this.yData == null) {
            yMinMax = findMinMax(this.lowData, this.highData);
        } else {
            yMinMax = findMinMax(this.yData, this.yData);
        }
        this.yMin = yMinMax[0];
        this.yMax = yMinMax[1];
    }

    public double[] getXData() {
        return this.xData;
    }

    public double[] getOpenData() {
        return this.openData;
    }

    public double[] getHighData() {
        return this.highData;
    }

    public double[] getLowData() {
        return this.lowData;
    }

    public double[] getCloseData() {
        return this.closeData;
    }

    public long[] getVolumeData() {
        return this.volumeData;
    }

    public double[] getYData() {
        return this.yData;
    }

    /* loaded from: target.jar:org/knowm/xchart/OHLCSeries$OHLCSeriesRenderStyle.class */
    public enum OHLCSeriesRenderStyle implements RenderableSeries {
        Candle(RenderableSeries.LegendRenderType.Line),
        HiLo(RenderableSeries.LegendRenderType.Line),
        Line(RenderableSeries.LegendRenderType.Line);

        private final RenderableSeries.LegendRenderType legendRenderType;

        OHLCSeriesRenderStyle(RenderableSeries.LegendRenderType legendRenderType) {
            this.legendRenderType = legendRenderType;
        }

        @Override // org.knowm.xchart.internal.chartpart.RenderableSeries
        public RenderableSeries.LegendRenderType getLegendRenderType() {
            return this.legendRenderType;
        }
    }
}
