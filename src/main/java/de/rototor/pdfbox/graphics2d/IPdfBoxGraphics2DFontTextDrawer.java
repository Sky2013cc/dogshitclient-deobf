package de.rototor.pdfbox.graphics2d;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/* loaded from: target.jar:de/rototor/pdfbox/graphics2d/IPdfBoxGraphics2DFontTextDrawer.class */
public interface IPdfBoxGraphics2DFontTextDrawer {

    /* loaded from: target.jar:de/rototor/pdfbox/graphics2d/IPdfBoxGraphics2DFontTextDrawer$IFontTextDrawerEnv.class */
    public interface IFontTextDrawerEnv {
        PDDocument getDocument();

        PDPageContentStream getContentStream();

        Font getFont();

        Paint getPaint();

        void applyPaint(Paint paint, Shape shape) throws IOException;

        FontRenderContext getFontRenderContext();

        PDRectangle getGraphicsBBox();

        PDResources getResources();

        Graphics2D getCalculationGraphics();

        void applyStroke(Stroke stroke) throws IOException;

        AffineTransform getCurrentEffectiveTransform();
    }

    boolean canDrawText(AttributedCharacterIterator attributedCharacterIterator, IFontTextDrawerEnv iFontTextDrawerEnv) throws IOException, FontFormatException;

    void drawText(AttributedCharacterIterator attributedCharacterIterator, IFontTextDrawerEnv iFontTextDrawerEnv) throws IOException, FontFormatException;

    FontMetrics getFontMetrics(Font font, IFontTextDrawerEnv iFontTextDrawerEnv) throws IOException, FontFormatException;
}
