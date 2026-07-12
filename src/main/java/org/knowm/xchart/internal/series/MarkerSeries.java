package org.knowm.xchart.internal.series;

import java.awt.Color;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.markers.Marker;

/* loaded from: target.jar:org/knowm/xchart/internal/series/MarkerSeries.class */
public abstract class MarkerSeries extends AxesChartSeries {
    private Marker marker;
    private Color markerColor;

    /* JADX INFO: Access modifiers changed from: protected */
    public MarkerSeries(String name, Series.DataType xAxisDataType) {
        super(name, xAxisDataType);
    }

    public Marker getMarker() {
        return this.marker;
    }

    public MarkerSeries setMarker(Marker marker) {
        this.marker = marker;
        return this;
    }

    public Color getMarkerColor() {
        return this.markerColor;
    }

    public MarkerSeries setMarkerColor(Color color) {
        this.markerColor = color;
        return this;
    }
}
