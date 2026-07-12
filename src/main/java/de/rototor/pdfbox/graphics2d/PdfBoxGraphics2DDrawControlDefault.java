package de.rototor.pdfbox.graphics2d;

import de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DDrawControl;
import java.awt.Shape;

/* loaded from: target.jar:de/rototor/pdfbox/graphics2d/PdfBoxGraphics2DDrawControlDefault.class */
public class PdfBoxGraphics2DDrawControlDefault implements IPdfBoxGraphics2DDrawControl {
    public static final PdfBoxGraphics2DDrawControlDefault INSTANCE = new PdfBoxGraphics2DDrawControlDefault();

    protected PdfBoxGraphics2DDrawControlDefault() {
    }

    @Override // de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DDrawControl
    public Shape transformShapeBeforeFill(Shape shape, IPdfBoxGraphics2DDrawControl.IDrawControlEnv env) {
        return shape;
    }

    @Override // de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DDrawControl
    public Shape transformShapeBeforeDraw(Shape shape, IPdfBoxGraphics2DDrawControl.IDrawControlEnv env) {
        return shape;
    }

    @Override // de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DDrawControl
    public void afterShapeFill(Shape shape, IPdfBoxGraphics2DDrawControl.IDrawControlEnv env) {
    }

    @Override // de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DDrawControl
    public void afterShapeDraw(Shape shape, IPdfBoxGraphics2DDrawControl.IDrawControlEnv env) {
    }
}
