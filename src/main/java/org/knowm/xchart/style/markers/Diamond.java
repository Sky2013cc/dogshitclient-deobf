package org.knowm.xchart.style.markers;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/* loaded from: target.jar:org/knowm/xchart/style/markers/Diamond.class */
public class Diamond extends Marker {
    @Override // org.knowm.xchart.style.markers.Marker
    public void paint(Graphics2D g, double xOffset, double yOffset, int markerSize) {
        g.setStroke(this.stroke);
        double diamondHalfSize = (markerSize / 2.0d) * 1.3d;
        Path2D.Double path = new Path2D.Double();
        path.moveTo(xOffset - diamondHalfSize, yOffset);
        path.lineTo(xOffset, yOffset - diamondHalfSize);
        path.lineTo(xOffset + diamondHalfSize, yOffset);
        path.lineTo(xOffset, yOffset + diamondHalfSize);
        path.closePath();
        g.fill(path);
    }
}
