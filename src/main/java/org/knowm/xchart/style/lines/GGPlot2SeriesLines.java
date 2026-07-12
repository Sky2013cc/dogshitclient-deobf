package org.knowm.xchart.style.lines;

import java.awt.BasicStroke;

/* loaded from: target.jar:org/knowm/xchart/style/lines/GGPlot2SeriesLines.class */
public class GGPlot2SeriesLines implements SeriesLines {
    private final BasicStroke[] seriesLines = {SOLID, DOT_DOT, DASH_DASH};

    @Override // org.knowm.xchart.style.lines.SeriesLines
    public BasicStroke[] getSeriesLines() {
        return this.seriesLines;
    }
}
