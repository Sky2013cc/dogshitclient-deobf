package org.knowm.xchart.internal.chartpart;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/* loaded from: target.jar:org/knowm/xchart/internal/chartpart/ChartPart.class */
public interface ChartPart {
    public static final BasicStroke SOLID_STROKE = new BasicStroke(1.0f, 0, 2, 10.0f, new float[]{3.0f, 0.0f}, 0.0f);

    Rectangle2D getBounds();

    void paint(Graphics2D graphics2D);
}
