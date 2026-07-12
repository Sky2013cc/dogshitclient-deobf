package org.knowm.xchart.style.markers;

/* loaded from: target.jar:org/knowm/xchart/style/markers/MatlabSeriesMarkers.class */
public class MatlabSeriesMarkers implements SeriesMarkers {
    private final Marker[] seriesMarkers = {CIRCLE, SQUARE, DIAMOND};

    @Override // org.knowm.xchart.style.markers.SeriesMarkers
    public Marker[] getSeriesMarkers() {
        return this.seriesMarkers;
    }
}
