package org.knowm.xchart.style.markers;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/* loaded from: target.jar:org/knowm/xchart/style/markers/Oval.class */
public class Oval extends Marker {
    @Override // org.knowm.xchart.style.markers.Marker
    public void paint(Graphics2D g, double xOffset, double yOffset, int markerSize) {
        g.setStroke(this.stroke);
        int markerSize2 = (int) Math.ceil(markerSize * 1.25d);
        double halfSize = markerSize2 / 2.0d;
        g.fill(new Ellipse2D.Double(xOffset - (halfSize / 2.0d), yOffset - halfSize, halfSize, markerSize2));
    }
}
