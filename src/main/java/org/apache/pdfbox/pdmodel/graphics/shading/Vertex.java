package org.apache.pdfbox.pdmodel.graphics.shading;

import java.awt.geom.Point2D;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/graphics/shading/Vertex.class */
class Vertex {
    final Point2D point;
    final float[] color;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Vertex(Point2D p, float[] c) {
        this.point = p;
        this.color = (float[]) c.clone();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (float f : this.color) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(String.format("%3.2f", Float.valueOf(f)));
        }
        return "Vertex{ " + this.point + ", colors=[" + ((Object) sb) + "] }";
    }
}
