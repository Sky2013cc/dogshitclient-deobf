package org.knowm.xchart.style.markers;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/* loaded from: target.jar:org/knowm/xchart/style/markers/Circle.class */
public class Circle extends Marker {
    @Override // org.knowm.xchart.style.markers.Marker
    public void paint(Graphics2D g, double xOffset, double yOffset, int markerSize) {
        g.setStroke(this.stroke);
        double halfSize = markerSize / 2.0d;
        g.fill(new Ellipse2D.Double(xOffset - halfSize, yOffset - halfSize, markerSize, markerSize));
    }
}
