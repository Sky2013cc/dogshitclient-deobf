package de.rototor.pdfbox.graphics2d;

import de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DFontTextDrawer;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

/* loaded from: target.jar:de/rototor/pdfbox/graphics2d/PdfBoxGraphics2DFontTextDrawerDefaultFonts.class */
public class PdfBoxGraphics2DFontTextDrawerDefaultFonts extends PdfBoxGraphics2DFontTextDrawer {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // de.rototor.pdfbox.graphics2d.PdfBoxGraphics2DFontTextDrawer
    public PDFont mapFont(Font font, IPdfBoxGraphics2DFontTextDrawer.IFontTextDrawerEnv env) throws IOException, FontFormatException {
        Standard14Fonts.FontName standardFontName = mapDefaultFonts(font);
        if (standardFontName != null) {
            return new PDType1Font(standardFontName);
        }
        PDFont pdFont = super.mapFont(font, env);
        if (pdFont != null) {
            return pdFont;
        }
        return new PDType1Font(chooseMatchingHelvetica(font));
    }

    public static Standard14Fonts.FontName mapDefaultFonts(Font font) {
        if (fontNameEqualsAnyOf(font, "SansSerif", "Dialog", "DialogInput", "Arial", "Helvetica")) {
            return chooseMatchingHelvetica(font);
        }
        if (fontNameEqualsAnyOf(font, "Monospaced", "courier", "courier new")) {
            return chooseMatchingCourier(font);
        }
        if (fontNameEqualsAnyOf(font, "Serif", "Times", "Times New Roman", "Times Roman")) {
            return chooseMatchingTimes(font);
        }
        if (fontNameEqualsAnyOf(font, "Symbol")) {
            return Standard14Fonts.FontName.SYMBOL;
        }
        if (fontNameEqualsAnyOf(font, "ZapfDingbats", "Dingbats")) {
            return Standard14Fonts.FontName.ZAPF_DINGBATS;
        }
        return null;
    }

    private static boolean fontNameEqualsAnyOf(Font font, String... names) {
        String name = font.getName();
        for (String fontName : names) {
            if (fontName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static Standard14Fonts.FontName chooseMatchingTimes(Font font) {
        if ((font.getStyle() & 3) == 3) {
            return Standard14Fonts.FontName.TIMES_BOLD_ITALIC;
        }
        if ((font.getStyle() & 2) == 2) {
            return Standard14Fonts.FontName.TIMES_ITALIC;
        }
        if ((font.getStyle() & 1) == 1) {
            return Standard14Fonts.FontName.TIMES_BOLD;
        }
        return Standard14Fonts.FontName.TIMES_ROMAN;
    }

    public static Standard14Fonts.FontName chooseMatchingCourier(Font font) {
        if ((font.getStyle() & 3) == 3) {
            return Standard14Fonts.FontName.COURIER_BOLD_OBLIQUE;
        }
        if ((font.getStyle() & 2) == 2) {
            return Standard14Fonts.FontName.COURIER_OBLIQUE;
        }
        if ((font.getStyle() & 1) == 1) {
            return Standard14Fonts.FontName.COURIER_BOLD;
        }
        return Standard14Fonts.FontName.COURIER;
    }

    public static Standard14Fonts.FontName chooseMatchingHelvetica(Font font) {
        if ((font.getStyle() & 3) == 3) {
            return Standard14Fonts.FontName.HELVETICA_BOLD_OBLIQUE;
        }
        if ((font.getStyle() & 2) == 2) {
            return Standard14Fonts.FontName.HELVETICA_OBLIQUE;
        }
        if ((font.getStyle() & 1) == 1) {
            return Standard14Fonts.FontName.HELVETICA_BOLD;
        }
        return Standard14Fonts.FontName.HELVETICA;
    }
}
