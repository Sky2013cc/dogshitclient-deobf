package de.rototor.pdfbox.graphics2d;

import java.awt.Color;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;

/* loaded from: target.jar:de/rototor/pdfbox/graphics2d/PdfBoxGraphics2DColor.class */
public class PdfBoxGraphics2DColor extends Color implements IPdfBoxGraphics2DColor {
    private final PDColor color;
    private final int alpha;
    private final boolean overprint;

    public PdfBoxGraphics2DColor(PDColor color) {
        this(color, 255);
    }

    public PdfBoxGraphics2DColor(PDColor color, int alpha) {
        this(color, alpha, false);
    }

    public PdfBoxGraphics2DColor(PDColor color, int alpha, boolean overprint) {
        super(toRGBValue(color, alpha));
        this.color = color;
        this.alpha = alpha;
        this.overprint = overprint;
    }

    private static int toRGBValue(PDColor color, int alpha) {
        try {
            return (color.toRGB() & 16777215) | (alpha << 24);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAlpha() {
        return this.alpha;
    }

    @Override // de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DColor
    public boolean isOverprint() {
        return this.overprint;
    }

    @Override // de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DColor
    public PDColor toPDColor() {
        return this.color;
    }
}
