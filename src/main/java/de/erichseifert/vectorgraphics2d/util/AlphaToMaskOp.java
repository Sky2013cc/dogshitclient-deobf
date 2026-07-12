package de.erichseifert.vectorgraphics2d.util;

import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/AlphaToMaskOp.class */
public class AlphaToMaskOp implements BufferedImageOp {
    private final boolean a;

    public AlphaToMaskOp(boolean z) {
        this.a = z;
    }

    public AlphaToMaskOp() {
        this(false);
    }

    public boolean isInverted() {
        return this.a;
    }

    public BufferedImage filter(BufferedImage bufferedImage, BufferedImage bufferedImage2) {
        int i;
        ColorModel colorModel = bufferedImage.getColorModel();
        if (bufferedImage2 == null) {
            bufferedImage2 = createCompatibleDestImage(bufferedImage, colorModel);
        } else {
            if (bufferedImage2.getWidth() != bufferedImage.getWidth() || bufferedImage2.getHeight() != bufferedImage.getHeight()) {
                throw new IllegalArgumentException("Source and destination images have different dimensions.");
            }
            if (bufferedImage2.getColorModel() != colorModel) {
                throw new IllegalArgumentException("Color models don't match.");
            }
        }
        if (colorModel.hasAlpha()) {
            WritableRaster raster = bufferedImage.getRaster();
            WritableRaster raster2 = bufferedImage2.getRaster();
            for (int i2 = 0; i2 < raster.getHeight(); i2++) {
                for (int i3 = 0; i3 < raster.getWidth(); i3++) {
                    int rgb = colorModel.getRGB(raster.getDataElements(i3, i2, (Object) null));
                    int i4 = rgb >>> 24;
                    if ((i4 >= 127 && !isInverted()) || (i4 < 127 && isInverted())) {
                        i = rgb | Constants.TM_MASK;
                    } else {
                        i = rgb & 16777215;
                    }
                    raster2.setDataElements(i3, i2, colorModel.getDataElements(i, (Object) null));
                }
            }
        }
        return bufferedImage2;
    }

    public Rectangle2D getBounds2D(BufferedImage bufferedImage) {
        Rectangle2D.Double r0 = new Rectangle2D.Double();
        r0.setRect(bufferedImage.getRaster().getBounds());
        return r0;
    }

    public BufferedImage createCompatibleDestImage(BufferedImage bufferedImage, ColorModel colorModel) {
        if (colorModel == null) {
            colorModel = bufferedImage.getColorModel();
        }
        WritableRaster createCompatibleWritableRaster = colorModel.createCompatibleWritableRaster(bufferedImage.getWidth(), bufferedImage.getHeight());
        boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        Hashtable hashtable = null;
        if (bufferedImage.getPropertyNames() != null) {
            hashtable = new Hashtable();
            for (String str : bufferedImage.getPropertyNames()) {
                hashtable.put(str, bufferedImage.getProperty(str));
            }
        }
        BufferedImage bufferedImage2 = new BufferedImage(colorModel, createCompatibleWritableRaster, isAlphaPremultiplied, hashtable);
        bufferedImage.copyData(createCompatibleWritableRaster);
        return bufferedImage2;
    }

    public Point2D getPoint2D(Point2D point2D, Point2D point2D2) {
        if (point2D2 == null) {
            point2D2 = new Point2D.Double();
        }
        point2D2.setLocation(point2D);
        return point2D2;
    }

    public RenderingHints getRenderingHints() {
        return null;
    }
}
