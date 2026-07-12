package org.knowm.xchart.style.markers;

import java.awt.Graphics2D;
import java.awt.Polygon;

/* loaded from: target.jar:org/knowm/xchart/style/markers/Trapezoid.class */
public class Trapezoid extends Marker {
    @Override // org.knowm.xchart.style.markers.Marker
    public void paint(Graphics2D g, double xOffset, double yOffset, int markerSize) {
        g.setStroke(this.stroke);
        double d = markerSize / 2.0d;
        Polygon polygon = new Polygon();
        for (int i = 1; i <= 4; i++) {
            polygon.addPoint((int) (xOffset + (markerSize * 0.75d * Math.sin(((i * 2) * 3.141592653589793d) / 5.0d))), (int) (yOffset + (markerSize * 0.25d) + (markerSize * 0.75d * Math.cos(((i * 2) * 3.141592653589793d) / 5.0d))));
        }
        g.fillPolygon(polygon);
    }
}
