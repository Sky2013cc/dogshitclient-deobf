package org.knowm.xchart.style.markers;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/* loaded from: target.jar:org/knowm/xchart/style/markers/TriangleUp.class */
public class TriangleUp extends Marker {
    @Override // org.knowm.xchart.style.markers.Marker
    public void paint(Graphics2D g, double xOffset, double yOffset, int markerSize) {
        g.setStroke(this.stroke);
        double halfSize = markerSize / 2.0d;
        Path2D.Double path = new Path2D.Double();
        path.moveTo(xOffset - halfSize, ((yOffset - halfSize) + markerSize) - 1.0d);
        path.lineTo((xOffset - halfSize) + markerSize, ((yOffset - halfSize) + markerSize) - 1.0d);
        path.lineTo(xOffset, (yOffset - halfSize) - 1.0d);
        path.closePath();
        g.fill(path);
    }
}
