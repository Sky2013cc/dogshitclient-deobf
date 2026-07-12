package org.knowm.xchart.style.markers;

/* loaded from: target.jar:org/knowm/xchart/style/markers/BaseSeriesMarkers.class */
public class BaseSeriesMarkers implements SeriesMarkers {
    private final Marker[] seriesMarkers = {CIRCLE, SQUARE, DIAMOND, TRIANGLE_UP, TRIANGLE_DOWN, CROSS};

    @Override // org.knowm.xchart.style.markers.SeriesMarkers
    public Marker[] getSeriesMarkers() {
        return this.seriesMarkers;
    }
}
