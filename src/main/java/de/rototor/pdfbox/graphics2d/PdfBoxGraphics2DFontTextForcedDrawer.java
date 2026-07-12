package de.rototor.pdfbox.graphics2d;

import de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DFontTextDrawer;
import java.text.AttributedCharacterIterator;

/* loaded from: target.jar:de/rototor/pdfbox/graphics2d/PdfBoxGraphics2DFontTextForcedDrawer.class */
public class PdfBoxGraphics2DFontTextForcedDrawer extends PdfBoxGraphics2DFontTextDrawerDefaultFonts {
    @Override // de.rototor.pdfbox.graphics2d.PdfBoxGraphics2DFontTextDrawer, de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DFontTextDrawer
    public boolean canDrawText(AttributedCharacterIterator iterator, IPdfBoxGraphics2DFontTextDrawer.IFontTextDrawerEnv env) {
        return true;
    }
}
