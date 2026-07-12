package org.apache.pdfbox.pdmodel.graphics.shading;

import java.awt.geom.Point2D;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/graphics/shading/CoordinateColorPair.class */
class CoordinateColorPair {
    final Point2D coordinate;
    final float[] color;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CoordinateColorPair(Point2D p, float[] c) {
        this.coordinate = p;
        this.color = (float[]) c.clone();
    }
}
