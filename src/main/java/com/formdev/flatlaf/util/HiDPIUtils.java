package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatSystemProperties;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.text.AttributedCharacterIterator;
import javax.swing.JComponent;
import javax.swing.RepaintManager;

/* loaded from: target.jar:com/formdev/flatlaf/util/HiDPIUtils.class */
public class HiDPIUtils {
    private static Boolean useTextYCorrection;
    private static final float[] SCALE_FACTORS = {1.25f, 1.5f, 1.75f, 2.0f, 2.25f, 2.5f, 3.0f, 3.5f, 4.0f};
    private static final float[] CORRECTION_SEGOE_UI = {-0.5f, -0.5f, -0.625f, -0.75f, -0.75f, -0.75f, -0.75f, -0.75f, -0.875f};
    private static final float[] CORRECTION_TAHOMA = {-0.25f, -0.25f, -0.25f, -0.0f, -0.125f, -0.125f, -0.125f, -0.125f, -0.0f};
    private static final float[] CORRECTION_INTER = {-0.25f, -0.25f, -0.25f, -0.0f, -0.125f, -0.125f, -0.0f, -0.25f, -0.0f};
    private static final float[] CORRECTION_OPEN_SANS = {-0.5f, -0.25f, -0.25f, -0.0f, -0.25f, -0.25f, -0.0f, -0.25f, -0.25f};
    private static Boolean useDebugScaleFactor;

    /* loaded from: target.jar:com/formdev/flatlaf/util/HiDPIUtils$DirtyRegionCallback.class */
    public interface DirtyRegionCallback {
        void addDirtyRegion(JComponent jComponent, int i, int i2, int i3, int i4);
    }

    /* loaded from: target.jar:com/formdev/flatlaf/util/HiDPIUtils$Painter.class */
    public interface Painter {
        void paint(Graphics2D graphics2D, int i, int i2, int i3, int i4, double d);
    }

    public static void paintAtScale1x(Graphics2D g, JComponent c, Painter painter) {
        paintAtScale1x(g, 0, 0, c.getWidth(), c.getHeight(), painter);
    }

    public static void paintAtScale1x(Graphics2D g, int x, int y, int width, int height, Painter painter) {
        double realScaleX;
        double realScaleY;
        AffineTransform t1x;
        AffineTransform t = g.getTransform();
        double scaleX = t.getScaleX();
        double scaleY = t.getScaleY();
        double shearX = t.getShearX();
        double shearY = t.getShearY();
        boolean rotated = shearX != 0.0d || shearY != 0.0d || scaleX <= 0.0d || scaleY <= 0.0d;
        if (rotated) {
            realScaleX = Math.hypot(scaleX, shearX);
            realScaleY = Math.hypot(scaleY, shearY);
        } else {
            realScaleX = Math.abs(scaleX);
            realScaleY = Math.abs(scaleY);
        }
        if (realScaleX == 1.0d && realScaleY == 1.0d) {
            painter.paint(g, x, y, width, height, 1.0d);
            return;
        }
        double px = (x * scaleX) + (y * shearX) + t.getTranslateX();
        double py = (y * scaleY) + (x * shearY) + t.getTranslateY();
        Rectangle2D.Double scaledRect = scale(realScaleX, realScaleY, px, py, width, height);
        try {
            if (rotated) {
                t1x = new AffineTransform(scaleX, shearY, shearX, scaleY, Math.floor(scaledRect.x), Math.floor(scaledRect.y));
                t1x.scale(1.0d / realScaleX, 1.0d / realScaleY);
            } else {
                t1x = new AffineTransform(1.0d, 0.0d, 0.0d, 1.0d, Math.floor(scaledRect.x), Math.floor(scaledRect.y));
            }
            g.setTransform(t1x);
            int swidth = (int) scaledRect.width;
            int sheight = (int) scaledRect.height;
            painter.paint(g, 0, 0, swidth, sheight, realScaleX);
            g.setTransform(t);
        } catch (Throwable th) {
            g.setTransform(t);
            throw th;
        }
    }

    private static Rectangle2D.Double scale(double scaleX, double scaleY, double px, double py, int width, int height) {
        double newX = normalize(px);
        double newY = normalize(py);
        double newWidth = normalize(px + (width * scaleX)) - newX;
        double newHeight = normalize(py + (height * scaleY)) - newY;
        return new Rectangle2D.Double(newX, newY, newWidth, newHeight);
    }

    private static double normalize(double value) {
        return Math.floor(value + 0.25d) + 0.25d;
    }

    private static boolean useTextYCorrection() {
        if (useTextYCorrection == null) {
            useTextYCorrection = Boolean.valueOf(FlatSystemProperties.getBoolean(FlatSystemProperties.USE_TEXT_Y_CORRECTION, true));
        }
        return useTextYCorrection.booleanValue();
    }

    public static float computeTextYCorrection(Graphics2D g) {
        if (!useTextYCorrection() || !SystemInfo.isWindows) {
            return 0.0f;
        }
        if (!SystemInfo.isJava_9_orLater) {
            float scaleFactor = getUserScaleFactor();
            if (scaleFactor > 1.0f) {
                String family = g.getFont().getFamily();
                boolean z = -1;
                switch (family.hashCode()) {
                    case -1272274913:
                        if (family.equals("Segoe UI Light")) {
                            z = true;
                            break;
                        }
                        break;
                    case -803832663:
                        if (family.equals("Open Sans")) {
                            z = 4;
                            break;
                        }
                        break;
                    case -282906057:
                        if (family.equals("Noto Sans")) {
                            z = 3;
                            break;
                        }
                        break;
                    case 976449354:
                        if (family.equals("Segoe UI Semibold")) {
                            z = 2;
                            break;
                        }
                        break;
                    case 1122284041:
                        if (family.equals("Segoe UI")) {
                            z = false;
                            break;
                        }
                        break;
                    case 2015806707:
                        if (family.equals("Verdana")) {
                            z = 5;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                    case true:
                    case true:
                        return -(((scaleFactor == 2.25f || scaleFactor == 4.0f) ? 0.875f : 0.625f) * scaleFactor);
                    case true:
                    case true:
                        return -(0.3f * scaleFactor);
                    case true:
                        return -((scaleFactor < 2.0f ? 0.4f : 0.3f) * scaleFactor);
                    default:
                        return 0.0f;
                }
            }
            return 0.0f;
        }
        String family2 = g.getFont().getFamily();
        boolean z2 = -1;
        switch (family2.hashCode()) {
            case -1841836187:
                if (family2.equals("Roboto")) {
                    z2 = 11;
                    break;
                }
                break;
            case -1797328664:
                if (family2.equals("Tahoma")) {
                    z2 = 6;
                    break;
                }
                break;
            case -1272274913:
                if (family2.equals("Segoe UI Light")) {
                    z2 = true;
                    break;
                }
                break;
            case -1119520233:
                if (family2.equals("Inter SemiBold")) {
                    z2 = 10;
                    break;
                }
                break;
            case -803832663:
                if (family2.equals("Open Sans")) {
                    z2 = 15;
                    break;
                }
                break;
            case -378125933:
                if (family2.equals("Inter Semi Bold")) {
                    z2 = 9;
                    break;
                }
                break;
            case -282906057:
                if (family2.equals("Noto Sans")) {
                    z2 = 14;
                    break;
                }
                break;
            case -227670902:
                if (family2.equals("SansSerif")) {
                    z2 = 5;
                    break;
                }
                break;
            case 70808764:
                if (family2.equals("Inter")) {
                    z2 = 7;
                    break;
                }
                break;
            case 116582576:
                if (family2.equals("Roboto Medium")) {
                    z2 = 13;
                    break;
                }
                break;
            case 280053883:
                if (family2.equals("Roboto Light")) {
                    z2 = 12;
                    break;
                }
                break;
            case 976449354:
                if (family2.equals("Segoe UI Semibold")) {
                    z2 = 2;
                    break;
                }
                break;
            case 1122284041:
                if (family2.equals("Segoe UI")) {
                    z2 = false;
                    break;
                }
                break;
            case 1629507730:
                if (family2.equals("Inter Light")) {
                    z2 = 8;
                    break;
                }
                break;
            case 2015806707:
                if (family2.equals("Verdana")) {
                    z2 = 3;
                    break;
                }
                break;
            case 2046749032:
                if (family2.equals("Dialog")) {
                    z2 = 4;
                    break;
                }
                break;
        }
        switch (z2) {
            case false:
            case true:
            case true:
            case true:
            case true:
            case true:
                return correctionForScaleY(g, CORRECTION_SEGOE_UI);
            case true:
                return correctionForScaleY(g, CORRECTION_TAHOMA);
            case true:
            case true:
            case true:
            case true:
            case true:
            case true:
            case true:
                return correctionForScaleY(g, CORRECTION_INTER);
            case true:
            case true:
                return correctionForScaleY(g, CORRECTION_OPEN_SANS);
            default:
                return 0.0f;
        }
    }

    private static float correctionForScaleY(Graphics2D g, float[] correction) {
        if (correction.length != 9) {
            throw new IllegalArgumentException();
        }
        double scaleY = g.getTransform().getScaleY();
        if (scaleY < 1.25d) {
            return 0.0f;
        }
        return correction[scaleFactor2index((float) scaleY)];
    }

    private static int scaleFactor2index(float scaleFactor) {
        for (int i = 0; i < SCALE_FACTORS.length; i++) {
            if (scaleFactor <= SCALE_FACTORS[i]) {
                return i;
            }
        }
        return SCALE_FACTORS.length - 1;
    }

    private static boolean useDebugScaleFactor() {
        if (useDebugScaleFactor == null) {
            useDebugScaleFactor = Boolean.valueOf(FlatSystemProperties.getBoolean("FlatLaf.debug.HiDPIUtils.useDebugScaleFactor", false));
        }
        return useDebugScaleFactor.booleanValue();
    }

    private static float getUserScaleFactor() {
        if (!useDebugScaleFactor()) {
            return UIScale.getUserScaleFactor();
        }
        return Float.parseFloat(System.getProperty("FlatLaf.debug.HiDPIUtils.debugScaleFactor", "1"));
    }

    public static void drawStringWithYCorrection(JComponent c, Graphics2D g, String text, int x, int y) {
        drawStringUnderlineCharAtWithYCorrection(c, g, text, -1, x, y);
    }

    public static void drawStringUnderlineCharAtWithYCorrection(JComponent c, Graphics2D g, String text, int underlinedIndex, int x, int y) {
        float yCorrection = computeTextYCorrection(g);
        if (yCorrection != 0.0f) {
            g.translate(0.0d, yCorrection);
            JavaCompatibility.drawStringUnderlineCharAt(c, g, text, underlinedIndex, x, y);
            g.translate(0.0d, -yCorrection);
            return;
        }
        JavaCompatibility.drawStringUnderlineCharAt(c, g, text, underlinedIndex, x, y);
    }

    public static Graphics2D createGraphicsTextYCorrection(Graphics2D g) {
        final float yCorrection = computeTextYCorrection(g);
        if (yCorrection == 0.0f) {
            return g;
        }
        return new Graphics2DProxy(g) { // from class: com.formdev.flatlaf.util.HiDPIUtils.1
            @Override // com.formdev.flatlaf.util.Graphics2DProxy
            public void drawString(String str, int x, int y) {
                super.drawString(str, x, y + yCorrection);
            }

            @Override // com.formdev.flatlaf.util.Graphics2DProxy
            public void drawString(String str, float x, float y) {
                super.drawString(str, x, y + yCorrection);
            }

            @Override // com.formdev.flatlaf.util.Graphics2DProxy
            public void drawString(AttributedCharacterIterator iterator, int x, int y) {
                super.drawString(iterator, x, y + yCorrection);
            }

            @Override // com.formdev.flatlaf.util.Graphics2DProxy
            public void drawString(AttributedCharacterIterator iterator, float x, float y) {
                super.drawString(iterator, x, y + yCorrection);
            }

            @Override // com.formdev.flatlaf.util.Graphics2DProxy
            public void drawChars(char[] data, int offset, int length, int x, int y) {
                super.drawChars(data, offset, length, x, Math.round(y + yCorrection));
            }

            @Override // com.formdev.flatlaf.util.Graphics2DProxy
            public void drawBytes(byte[] data, int offset, int length, int x, int y) {
                super.drawBytes(data, offset, length, x, Math.round(y + yCorrection));
            }

            @Override // com.formdev.flatlaf.util.Graphics2DProxy
            public void drawGlyphVector(GlyphVector g2, float x, float y) {
                super.drawGlyphVector(g2, x, y + yCorrection);
            }
        };
    }

    public static void repaint(Component c) {
        repaint(c, 0, 0, c.getWidth(), c.getHeight());
    }

    public static void repaint(Component c, Rectangle r) {
        repaint(c, r.x, r.y, r.width, r.height);
    }

    public static void repaint(Component c, int x, int y, int width, int height) {
        c.repaint(x, y, width, height);
        if (!(RepaintManager.currentManager(c) instanceof HiDPIRepaintManager) && needsSpecialRepaint(c, x, y, width, height)) {
            int x2 = x + c.getX();
            int y2 = y + c.getY();
            Container parent = c.getParent();
            while (true) {
                Container container = parent;
                if (container != null) {
                    x2 += container.getX();
                    y2 += container.getY();
                    if (x2 + width >= container.getWidth() || y2 + height >= container.getHeight()) {
                        parent = container.getParent();
                    } else {
                        container.repaint(x2, y2, width, height);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private static boolean needsSpecialRepaint(Component c, int x, int y, int width, int height) {
        if (!SystemInfo.isJava_9_orLater || !SystemInfo.isWindows || width <= 0 || height <= 0 || c == null) {
            return false;
        }
        int compWidth = c.getWidth();
        int compHeight = c.getHeight();
        if (compWidth <= 0 || compHeight <= 0) {
            return false;
        }
        if (x + width < compWidth && y + height < compHeight) {
            return false;
        }
        if (!c.isOpaque()) {
            int x2 = x;
            int y2 = y;
            Container parent = c.getParent();
            while (true) {
                Container container = parent;
                if (container == null) {
                    break;
                }
                x2 += container.getX();
                y2 += container.getY();
                if (!container.isOpaque()) {
                    parent = container.getParent();
                } else if (x2 + width < container.getWidth() && y2 + height < container.getHeight()) {
                    return false;
                }
            }
        }
        double scaleFactor = UIScale.getSystemScaleFactor(c.getGraphicsConfiguration());
        double fraction = scaleFactor - ((int) scaleFactor);
        if (fraction == 0.0d || fraction == 0.5d) {
            return false;
        }
        return true;
    }

    public static void installHiDPIRepaintManager() {
        if (!SystemInfo.isJava_9_orLater || !SystemInfo.isWindows) {
            return;
        }
        RepaintManager manager = RepaintManager.currentManager((Component) null);
        if (manager.getClass() == RepaintManager.class) {
            RepaintManager.setCurrentManager(new HiDPIRepaintManager());
        }
    }

    public static void addDirtyRegion(JComponent c, int x, int y, int width, int height, DirtyRegionCallback callback) {
        if (needsSpecialRepaint(c, x, y, width, height)) {
            int x2 = x + c.getX();
            int y2 = y + c.getY();
            Container parent = c.getParent();
            while (true) {
                Container container = parent;
                if (container == null) {
                    break;
                }
                if (x2 + width < container.getWidth() && y2 + height < container.getHeight() && (container instanceof JComponent)) {
                    callback.addDirtyRegion((JComponent) container, x2, y2, width, height);
                    return;
                } else {
                    x2 += container.getX();
                    y2 += container.getY();
                    parent = container.getParent();
                }
            }
        }
        callback.addDirtyRegion(c, x, y, width, height);
    }

    /* loaded from: target.jar:com/formdev/flatlaf/util/HiDPIUtils$HiDPIRepaintManager.class */
    public static class HiDPIRepaintManager extends RepaintManager {
        public void addDirtyRegion(JComponent c, int x, int y, int w, int h) {
            HiDPIUtils.addDirtyRegion(c, x, y, w, h, (x$0, x$1, x$2, x$3, x$4) -> {
                super.addDirtyRegion(x$0, x$1, x$2, x$3, x$4);
            });
        }
    }
}
