package de.rototor.pdfbox.graphics2d;

import java.awt.Paint;
import java.awt.Shape;

/* loaded from: target.jar:de/rototor/pdfbox/graphics2d/IPdfBoxGraphics2DDrawControl.class */
public interface IPdfBoxGraphics2DDrawControl {

    /* loaded from: target.jar:de/rototor/pdfbox/graphics2d/IPdfBoxGraphics2DDrawControl$IDrawControlEnv.class */
    public interface IDrawControlEnv {
        Paint getPaint();

        PdfBoxGraphics2D getGraphics();
    }

    Shape transformShapeBeforeFill(Shape shape, IDrawControlEnv iDrawControlEnv);

    Shape transformShapeBeforeDraw(Shape shape, IDrawControlEnv iDrawControlEnv);

    void afterShapeFill(Shape shape, IDrawControlEnv iDrawControlEnv);

    void afterShapeDraw(Shape shape, IDrawControlEnv iDrawControlEnv);
}
