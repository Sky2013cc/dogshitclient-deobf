package org.knowm.xchart.style.markers;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/* loaded from: target.jar:org/knowm/xchart/style/markers/Square.class */
public class Square extends Marker {
    @Override // org.knowm.xchart.style.markers.Marker
    public void paint(Graphics2D g, double xOffset, double yOffset, int markerSize) {
        g.setStroke(this.stroke);
        double halfSize = markerSize / 2.0d;
        g.fill(new Rectangle2D.Double(xOffset - halfSize, yOffset - halfSize, markerSize, markerSize));
    }
}
