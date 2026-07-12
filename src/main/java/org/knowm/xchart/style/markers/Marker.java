package org.knowm.xchart.style.markers;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

/* loaded from: target.jar:org/knowm/xchart/style/markers/Marker.class */
public abstract class Marker {
    final BasicStroke stroke = new BasicStroke(1.0f, 0, 2);

    public abstract void paint(Graphics2D graphics2D, double d, double d2, int i);
}
