package org.knowm.xchart;

import org.knowm.xchart.internal.chartpart.RenderableSeries;
import org.knowm.xchart.internal.series.NoMarkersSeries;
import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/BubbleSeries.class */
public class BubbleSeries extends NoMarkersSeries {
    private BubbleSeriesRenderStyle bubbleSeriesRenderStyle;

    public BubbleSeries(String name, double[] xData, double[] yData, double[] bubbleSizes) {
        super(name, xData, yData, bubbleSizes, Series.DataType.Number);
        this.bubbleSeriesRenderStyle = null;
    }

    public BubbleSeriesRenderStyle getBubbleSeriesRenderStyle() {
        return this.bubbleSeriesRenderStyle;
    }

    public void setBubbleSeriesRenderStyle(BubbleSeriesRenderStyle bubbleSeriesRenderStyle) {
        this.bubbleSeriesRenderStyle = bubbleSeriesRenderStyle;
    }

    @Override // org.knowm.xchart.internal.series.Series
    public RenderableSeries.LegendRenderType getLegendRenderType() {
        return this.bubbleSeriesRenderStyle.getLegendRenderType();
    }

    /* loaded from: target.jar:org/knowm/xchart/BubbleSeries$BubbleSeriesRenderStyle.class */
    public enum BubbleSeriesRenderStyle implements RenderableSeries {
        Round(RenderableSeries.LegendRenderType.Box);

        private final RenderableSeries.LegendRenderType legendRenderType;

        BubbleSeriesRenderStyle(RenderableSeries.LegendRenderType legendRenderType) {
            this.legendRenderType = legendRenderType;
        }

        @Override // org.knowm.xchart.internal.chartpart.RenderableSeries
        public RenderableSeries.LegendRenderType getLegendRenderType() {
            return this.legendRenderType;
        }
    }
}
