package org.knowm.xchart.style.markers;

/* loaded from: target.jar:org/knowm/xchart/style/markers/GGPlot2SeriesMarkers.class */
public class GGPlot2SeriesMarkers implements SeriesMarkers {
    private final Marker[] seriesMarkers = {CIRCLE, DIAMOND};

    @Override // org.knowm.xchart.style.markers.SeriesMarkers
    public Marker[] getSeriesMarkers() {
        return this.seriesMarkers;
    }
}
