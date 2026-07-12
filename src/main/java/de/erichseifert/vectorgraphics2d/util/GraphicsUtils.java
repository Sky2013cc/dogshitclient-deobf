package de.erichseifert.vectorgraphics2d.util;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.color.ColorSpace;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.PriorityQueue;
import javax.swing.ImageIcon;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/GraphicsUtils.class */
public abstract class GraphicsUtils {
    private static final FontRenderContext a = new FontRenderContext((AffineTransform) null, false, true);
    private static final a b = new a(0);

    GraphicsUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean hasAlpha(Image image) {
        ColorModel colorModel;
        if (image instanceof BufferedImage) {
            colorModel = ((BufferedImage) image).getColorModel();
        } else {
            PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, 1, 1, false);
            try {
                pixelGrabber.grabPixels();
                colorModel = pixelGrabber.getColorModel();
            } catch (InterruptedException unused) {
                return false;
            }
        }
        return colorModel.hasAlpha();
    }

    public static boolean usesAlpha(Image image) {
        WritableRaster alphaRaster;
        if (image == null || (alphaRaster = toBufferedImage(image).getAlphaRaster()) == null) {
            return false;
        }
        DataBuffer dataBuffer = alphaRaster.getDataBuffer();
        for (int i = 0; i < dataBuffer.getSize(); i++) {
            if (dataBuffer.getElem(i) < 255) {
                return true;
            }
        }
        return false;
    }

    public static BufferedImage toBufferedImage(RenderedImage renderedImage) {
        if (renderedImage instanceof BufferedImage) {
            return (BufferedImage) renderedImage;
        }
        ColorModel colorModel = renderedImage.getColorModel();
        WritableRaster createCompatibleWritableRaster = colorModel.createCompatibleWritableRaster(renderedImage.getWidth(), renderedImage.getHeight());
        boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        Hashtable hashtable = null;
        if (renderedImage.getPropertyNames() != null) {
            hashtable = new Hashtable();
            for (String str : renderedImage.getPropertyNames()) {
                hashtable.put(str, renderedImage.getProperty(str));
            }
        }
        BufferedImage bufferedImage = new BufferedImage(colorModel, createCompatibleWritableRaster, isAlphaPremultiplied, hashtable);
        renderedImage.copyData(createCompatibleWritableRaster);
        return bufferedImage;
    }

    public static BufferedImage toBufferedImage(Image image) {
        BufferedImage bufferedImage;
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        Image image2 = new ImageIcon(image).getImage();
        boolean hasAlpha = hasAlpha(image2);
        GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        int i = 1;
        if (hasAlpha) {
            i = 3;
        }
        try {
            bufferedImage = localGraphicsEnvironment.getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(image2.getWidth((ImageObserver) null), image2.getHeight((ImageObserver) null), i);
        } catch (HeadlessException unused) {
            bufferedImage = null;
        }
        if (bufferedImage == null) {
            int i2 = 1;
            if (hasAlpha) {
                i2 = 2;
            }
            bufferedImage = new BufferedImage(image2.getWidth((ImageObserver) null), image2.getHeight((ImageObserver) null), i2);
        }
        Graphics2D createGraphics = bufferedImage.createGraphics();
        createGraphics.drawImage(image2, 0, 0, (ImageObserver) null);
        createGraphics.dispose();
        return bufferedImage;
    }

    public static Shape clone(Shape shape) {
        Line2D.Float r7;
        if (shape == null) {
            return null;
        }
        if (shape instanceof Line2D) {
            Line2D.Float r0 = shape instanceof Line2D.Float ? new Line2D.Float() : new Line2D.Double();
            r7 = r0;
            ((Line2D) r0).setLine((Line2D) shape);
        } else if (shape instanceof Rectangle) {
            r7 = new Rectangle((Rectangle) shape);
        } else if (shape instanceof Rectangle2D) {
            Line2D.Float r02 = shape instanceof Rectangle2D.Float ? new Rectangle2D.Float() : new Rectangle2D.Double();
            r7 = r02;
            ((Rectangle2D) r02).setRect((Rectangle2D) shape);
        } else if (shape instanceof RoundRectangle2D) {
            Line2D.Float r03 = shape instanceof RoundRectangle2D.Float ? new RoundRectangle2D.Float() : new RoundRectangle2D.Double();
            r7 = r03;
            ((RoundRectangle2D) r03).setRoundRect((RoundRectangle2D) shape);
        } else if (shape instanceof Ellipse2D) {
            Line2D.Float r04 = shape instanceof Ellipse2D.Float ? new Ellipse2D.Float() : new Ellipse2D.Double();
            r7 = r04;
            ((Ellipse2D) r04).setFrame(((Ellipse2D) shape).getFrame());
        } else if (shape instanceof Arc2D) {
            Line2D.Float r05 = shape instanceof Arc2D.Float ? new Arc2D.Float() : new Arc2D.Double();
            r7 = r05;
            ((Arc2D) r05).setArc((Arc2D) shape);
        } else if (shape instanceof Polygon) {
            Polygon polygon = (Polygon) shape;
            r7 = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
        } else if (shape instanceof CubicCurve2D) {
            Line2D.Float r06 = shape instanceof CubicCurve2D.Float ? new CubicCurve2D.Float() : new CubicCurve2D.Double();
            r7 = r06;
            ((CubicCurve2D) r06).setCurve((CubicCurve2D) shape);
        } else if (shape instanceof QuadCurve2D) {
            Line2D.Float r07 = shape instanceof QuadCurve2D.Float ? new QuadCurve2D.Float() : new QuadCurve2D.Double();
            r7 = r07;
            ((QuadCurve2D) r07).setCurve((QuadCurve2D) shape);
        } else if (shape instanceof Path2D.Float) {
            r7 = new Path2D.Float(shape);
        } else {
            r7 = new Path2D.Double(shape);
        }
        return r7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/GraphicsUtils$a.class */
    public static class a implements Comparator<Font> {
        private static final int[] a = {0, 2, 1, 3};

        private a() {
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(Font font, Font font2) {
            Font font3 = font;
            Font font4 = font2;
            if (font3 == font4) {
                return 0;
            }
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            int[] iArr = a;
            for (int i = 0; i < 4; i++) {
                int i2 = iArr[i];
                hashSet.add(font3.deriveFont(i2).getPSName());
                hashSet2.add(font4.deriveFont(i2).getPSName());
            }
            if (hashSet.size() < hashSet2.size()) {
                return 1;
            }
            if (hashSet.size() > hashSet2.size()) {
                return -1;
            }
            return font3.getName().compareTo(font4.getName());
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    private static boolean a(String str) {
        return "Dialog".equals(str) || "DialogInput".equals(str) || "SansSerif".equals(str) || "Serif".equals(str) || "Monospaced".equals(str);
    }

    public static Font getPhysicalFont(Font font, String str) {
        if (!a(font.getFamily())) {
            return font;
        }
        TextLayout textLayout = new TextLayout(str, font, a);
        PriorityQueue priorityQueue = new PriorityQueue(1, b);
        for (Font font2 : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
            if (!a(font2.getFamily())) {
                Font deriveFont = font2.deriveFont(font.getStyle(), font.getSize2D());
                TextLayout textLayout2 = new TextLayout(str, deriveFont, a);
                if (textLayout2.getBounds().equals(textLayout.getBounds()) && textLayout2.getAscent() == textLayout.getAscent() && textLayout2.getDescent() == textLayout.getDescent() && textLayout2.getLeading() == textLayout.getLeading() && textLayout2.getAdvance() == textLayout.getAdvance() && textLayout2.getVisibleAdvance() == textLayout.getVisibleAdvance()) {
                    priorityQueue.add(deriveFont);
                }
            }
        }
        if (priorityQueue.isEmpty()) {
            return font;
        }
        return (Font) priorityQueue.poll();
    }

    public static Font getPhysicalFont(Font font) {
        return getPhysicalFont(font, "Falsches Üben von Xylophonmusik quält jeden größeren Zwerg");
    }

    public static BufferedImage getAlphaImage(BufferedImage bufferedImage) {
        WritableRaster alphaRaster = bufferedImage.getAlphaRaster();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        ComponentColorModel componentColorModel = new ComponentColorModel(ColorSpace.getInstance(1003), new int[]{8}, false, true, 1, 0);
        BufferedImage bufferedImage2 = new BufferedImage(componentColorModel, componentColorModel.createCompatibleWritableRaster(width, height), false, (Hashtable) null);
        int[] iArr = new int[bufferedImage.getWidth() * alphaRaster.getNumBands()];
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            alphaRaster.getPixels(0, i, bufferedImage.getWidth(), 1, iArr);
            if (bufferedImage.getTransparency() == 2) {
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    if (iArr[i2] > 0) {
                        iArr[i2] = 255;
                    }
                }
            }
            bufferedImage2.getRaster().setPixels(0, i, bufferedImage.getWidth(), 1, iArr);
        }
        return bufferedImage2;
    }

    public static boolean equals(Shape shape, Shape shape2) {
        PathIterator pathIterator = shape.getPathIterator((AffineTransform) null);
        PathIterator pathIterator2 = shape2.getPathIterator((AffineTransform) null);
        if (pathIterator.getWindingRule() != pathIterator2.getWindingRule()) {
            return false;
        }
        double[] dArr = new double[6];
        double[] dArr2 = new double[6];
        while (!pathIterator.isDone()) {
            if (pathIterator.currentSegment(dArr) != pathIterator2.currentSegment(dArr2)) {
                return false;
            }
            for (int i = 0; i < 6; i++) {
                if (dArr[i] != dArr2[i]) {
                    return false;
                }
            }
            pathIterator.next();
            pathIterator2.next();
        }
        if (!pathIterator2.isDone()) {
            return false;
        }
        return true;
    }
}
