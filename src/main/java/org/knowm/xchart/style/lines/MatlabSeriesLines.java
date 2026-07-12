package org.knowm.xchart.style.lines;

import java.awt.BasicStroke;

/* loaded from: target.jar:org/knowm/xchart/style/lines/MatlabSeriesLines.class */
public class MatlabSeriesLines implements SeriesLines {
    private final BasicStroke[] seriesLines = {SOLID, DASH_DASH, DOT_DOT};

    @Override // org.knowm.xchart.style.lines.SeriesLines
    public BasicStroke[] getSeriesLines() {
        return this.seriesLines;
    }
}
