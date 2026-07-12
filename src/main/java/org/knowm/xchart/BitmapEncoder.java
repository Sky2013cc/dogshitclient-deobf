package org.knowm.xchart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageOutputStream;
import org.knowm.xchart.internal.chartpart.Chart;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:org/knowm/xchart/BitmapEncoder.class */
public final class BitmapEncoder {

    /* loaded from: target.jar:org/knowm/xchart/BitmapEncoder$BitmapFormat.class */
    public enum BitmapFormat {
        PNG,
        JPG,
        BMP,
        GIF
    }

    private BitmapEncoder() {
    }

    public static String addFileExtension(String fileName, BitmapFormat bitmapFormat) {
        String fileNameWithFileExtension = fileName;
        String newFileExtension = Constants.NAME_SEPARATOR + bitmapFormat.toString().toLowerCase();
        if (fileName.length() <= newFileExtension.length() || !fileName.substring(fileName.length() - newFileExtension.length(), fileName.length()).equalsIgnoreCase(newFileExtension)) {
            fileNameWithFileExtension = fileName + newFileExtension;
        }
        return fileNameWithFileExtension;
    }

    public static <T extends Chart<?, ?>> void saveBitmap(T chart, String fileName, BitmapFormat bitmapFormat) throws IOException {
        OutputStream out = new FileOutputStream(addFileExtension(fileName, bitmapFormat));
        try {
            saveBitmap(chart, out, bitmapFormat);
            out.close();
        } catch (Throwable th) {
            try {
                out.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static <T extends Chart<?, ?>> void saveBitmap(T chart, OutputStream targetStream, BitmapFormat bitmapFormat) throws IOException {
        BufferedImage bufferedImage = getBufferedImage(chart);
        ImageIO.write(bufferedImage, bitmapFormat.toString().toLowerCase(), targetStream);
    }

    public static <T extends Chart<?, ?>> void saveBitmap(List<T> charts, Integer rows, Integer cols, String fileName, BitmapFormat bitmapFormat) throws IOException {
        OutputStream out = new FileOutputStream(addFileExtension(fileName, bitmapFormat));
        try {
            saveBitmap(charts, rows, cols, out, bitmapFormat);
            out.close();
        } catch (Throwable th) {
            try {
                out.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static <T extends Chart<?, ?>> void saveBitmap(List<T> charts, Integer rows, Integer cols, OutputStream targetStream, BitmapFormat bitmapFormat) throws IOException {
        List<BufferedImage> chartImages = new LinkedList<>();
        for (T c : charts) {
            chartImages.add(getBufferedImage(c));
        }
        BufferedImage bufferedImage = mergeImages(chartImages, rows, cols);
        ImageIO.write(bufferedImage, bitmapFormat.toString().toLowerCase(), targetStream);
    }

    public static <T extends Chart<?, ?>> void saveBitmapWithDPI(T chart, String fileName, BitmapFormat bitmapFormat, int DPI) throws IOException {
        double scaleFactor = DPI / 72.0d;
        BufferedImage bufferedImage = new BufferedImage((int) (chart.getWidth() * scaleFactor), (int) (chart.getHeight() * scaleFactor), 1);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        AffineTransform at = graphics2D.getTransform();
        at.scale(scaleFactor, scaleFactor);
        graphics2D.setTransform(at);
        chart.paint(graphics2D, chart.getWidth(), chart.getHeight());
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(bitmapFormat.toString().toLowerCase());
        if (writers.hasNext()) {
            ImageWriter writer = writers.next();
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(1);
            IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, iwp);
            if (metadata.isReadOnly() || !metadata.isStandardMetadataFormatSupported()) {
                throw new IllegalArgumentException("It is not possible to set the DPI on a bitmap with " + bitmapFormat + " format!! Try another format.");
            }
            setDPI(metadata, DPI);
            File file = new File(addFileExtension(fileName, bitmapFormat));
            FileImageOutputStream output = new FileImageOutputStream(file);
            try {
                writer.setOutput(output);
                IIOImage image = new IIOImage(bufferedImage, (List) null, metadata);
                writer.write((IIOMetadata) null, image, iwp);
                writer.dispose();
                output.close();
            } catch (Throwable th) {
                try {
                    output.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    private static void setDPI(IIOMetadata metadata, int DPI) throws IIOInvalidTreeException {
        double dotsPerMilli = ((1.0d * DPI) / 10.0d) / 2.54d;
        IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
        horiz.setAttribute("value", Double.toString(dotsPerMilli));
        IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
        vert.setAttribute("value", Double.toString(dotsPerMilli));
        IIOMetadataNode dim = new IIOMetadataNode("Dimension");
        dim.appendChild(horiz);
        dim.appendChild(vert);
        IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
        root.appendChild(dim);
        metadata.mergeTree("javax_imageio_1.0", root);
    }

    public static <T extends Chart<?, ?>> void saveJPGWithQuality(T chart, String fileName, float quality) throws IOException {
        BufferedImage bufferedImage = getBufferedImage(chart);
        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter writer = iter.next();
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        iwp.setCompressionMode(2);
        iwp.setCompressionQuality(quality);
        File file = new File(fileName);
        FileImageOutputStream output = new FileImageOutputStream(file);
        try {
            writer.setOutput(output);
            IIOImage image = new IIOImage(bufferedImage, (List) null, (IIOMetadata) null);
            writer.write((IIOMetadata) null, image, iwp);
            writer.dispose();
            output.close();
        } catch (Throwable th) {
            try {
                output.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static <T extends Chart<?, ?>> byte[] getBitmapBytes(T chart, BitmapFormat bitmapFormat) throws IOException {
        BufferedImage bufferedImage = getBufferedImage(chart);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, bitmapFormat.toString().toLowerCase(), baos);
            baos.flush();
            byte[] imageInBytes = baos.toByteArray();
            baos.close();
            return imageInBytes;
        } catch (Throwable th) {
            try {
                baos.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static <T extends Chart<?, ?>> BufferedImage getBufferedImage(T chart) {
        BufferedImage bufferedImage = new BufferedImage(chart.getWidth(), chart.getHeight(), 1);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        chart.paint(graphics2D, chart.getWidth(), chart.getHeight());
        return bufferedImage;
    }

    private static BufferedImage mergeImages(List<BufferedImage> images, Integer rows, Integer cols) {
        BufferedImage first = images.get(0);
        int singleImageWidth = first.getWidth();
        int singleImageHeight = first.getHeight();
        int totalWidth = singleImageWidth * cols.intValue();
        int totalHeight = singleImageHeight * rows.intValue();
        BufferedImage mergedImage = new BufferedImage(totalWidth, totalHeight, 2);
        Graphics g = mergedImage.getGraphics();
        for (int row = 0; row < rows.intValue(); row++) {
            for (int col = 0; col < cols.intValue(); col++) {
                BufferedImage image = images.get((row * cols.intValue()) + col);
                g.drawImage(image, col * singleImageWidth, row * singleImageHeight, (ImageObserver) null);
            }
        }
        return mergedImage;
    }
}
