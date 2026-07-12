package org.knowm.xchart.internal.style;

import java.awt.BasicStroke;
import java.awt.Color;
import org.knowm.xchart.style.markers.Marker;

/* loaded from: target.jar:org/knowm/xchart/internal/style/SeriesColorMarkerLineStyleCycler.class */
public class SeriesColorMarkerLineStyleCycler {
    private final Color[] seriesColorList;
    private final Marker[] seriesMarkerList;
    private final BasicStroke[] seriesLineStyleList;
    private int colorCounter = 0;
    private int markerCounter = 0;
    private int strokeCounter = 0;

    public SeriesColorMarkerLineStyleCycler(Color[] seriesColorList, Marker[] seriesMarkerList, BasicStroke[] seriesLineStyleList) {
        this.seriesColorList = seriesColorList;
        this.seriesMarkerList = seriesMarkerList;
        this.seriesLineStyleList = seriesLineStyleList;
    }

    public SeriesColorMarkerLineStyle getNextSeriesColorMarkerLineStyle() {
        if (this.colorCounter >= this.seriesColorList.length) {
            this.colorCounter = 0;
            this.strokeCounter++;
        }
        Color[] colorArr = this.seriesColorList;
        int i = this.colorCounter;
        this.colorCounter = i + 1;
        Color seriesColor = colorArr[i];
        if (this.strokeCounter >= this.seriesLineStyleList.length) {
            this.strokeCounter = 0;
        }
        BasicStroke seriesLineStyle = this.seriesLineStyleList[this.strokeCounter];
        if (this.markerCounter >= this.seriesMarkerList.length) {
            this.markerCounter = 0;
        }
        Marker[] markerArr = this.seriesMarkerList;
        int i2 = this.markerCounter;
        this.markerCounter = i2 + 1;
        Marker marker = markerArr[i2];
        return new SeriesColorMarkerLineStyle(seriesColor, marker, seriesLineStyle);
    }
}
