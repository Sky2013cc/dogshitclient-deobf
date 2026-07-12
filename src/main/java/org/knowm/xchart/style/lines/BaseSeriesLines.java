package org.knowm.xchart.style.lines;

import java.awt.BasicStroke;

/* loaded from: target.jar:org/knowm/xchart/style/lines/BaseSeriesLines.class */
public class BaseSeriesLines implements SeriesLines {
    private final BasicStroke[] seriesLines = {SOLID, DOT_DOT, DASH_DASH, DASH_DOT};

    @Override // org.knowm.xchart.style.lines.SeriesLines
    public BasicStroke[] getSeriesLines() {
        return this.seriesLines;
    }
}
