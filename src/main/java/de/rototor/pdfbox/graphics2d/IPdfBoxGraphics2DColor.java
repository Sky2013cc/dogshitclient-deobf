package de.rototor.pdfbox.graphics2d;

import org.apache.pdfbox.pdmodel.graphics.color.PDColor;

/* loaded from: target.jar:de/rototor/pdfbox/graphics2d/IPdfBoxGraphics2DColor.class */
public interface IPdfBoxGraphics2DColor {
    PDColor toPDColor();

    boolean isOverprint();
}
