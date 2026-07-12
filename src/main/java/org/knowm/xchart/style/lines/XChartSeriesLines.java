package org.knowm.xchart.style.lines;

import java.awt.BasicStroke;

/* loaded from: target.jar:org/knowm/xchart/style/lines/XChartSeriesLines.class */
public class XChartSeriesLines implements SeriesLines {
    private final BasicStroke[] seriesLines = {SOLID, DASH_DOT, DASH_DASH, DOT_DOT};

    @Override // org.knowm.xchart.style.lines.SeriesLines
    public BasicStroke[] getSeriesLines() {
        return this.seriesLines;
    }
}
