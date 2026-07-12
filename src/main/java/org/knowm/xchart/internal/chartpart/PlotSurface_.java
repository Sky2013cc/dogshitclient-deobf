package org.knowm.xchart.internal.chartpart;

import java.awt.geom.Rectangle2D;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.Styler;

/* loaded from: target.jar:org/knowm/xchart/internal/chartpart/PlotSurface_.class */
public abstract class PlotSurface_<ST extends Styler, S extends Series> implements ChartPart {
    final Chart<ST, S> chart;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlotSurface_(Chart<ST, S> chart) {
        this.chart = chart;
    }

    @Override // org.knowm.xchart.internal.chartpart.ChartPart
    public Rectangle2D getBounds() {
        return this.chart.getPlot().getBounds();
    }
}
