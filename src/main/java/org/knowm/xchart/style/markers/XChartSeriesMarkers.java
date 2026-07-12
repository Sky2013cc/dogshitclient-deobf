package org.knowm.xchart.style.markers;

/* loaded from: target.jar:org/knowm/xchart/style/markers/XChartSeriesMarkers.class */
public class XChartSeriesMarkers implements SeriesMarkers {
    private final Marker[] seriesMarkers = {CIRCLE, DIAMOND, SQUARE, TRIANGLE_DOWN, TRIANGLE_UP, CROSS, PLUS, RECTANGLE};

    @Override // org.knowm.xchart.style.markers.SeriesMarkers
    public Marker[] getSeriesMarkers() {
        return this.seriesMarkers;
    }
}
