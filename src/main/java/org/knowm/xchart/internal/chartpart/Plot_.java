package org.knowm.xchart.internal.chartpart;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.Styler;

/* loaded from: target.jar:org/knowm/xchart/internal/chartpart/Plot_.class */
public class Plot_<ST extends Styler, S extends Series> implements ChartPart {
    final Chart<ST, S> chart;
    Rectangle2D bounds;
    PlotSurface_<ST, S> plotSurface;
    PlotContent_<ST, S> plotContent;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Plot_(Chart<ST, S> chart) {
        this.chart = chart;
    }

    @Override // org.knowm.xchart.internal.chartpart.ChartPart
    public void paint(Graphics2D g) {
        this.plotSurface.paint(g);
        if (this.chart.getSeriesMap().isEmpty()) {
            return;
        }
        this.plotContent.paint(g);
    }

    @Override // org.knowm.xchart.internal.chartpart.ChartPart
    public Rectangle2D getBounds() {
        return this.bounds;
    }
}
