package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.SystemInfo;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import javax.swing.JDialog;
import javax.swing.JFrame;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatNativeLinuxLibrary.class */
class FlatNativeLinuxLibrary {
    private static int API_VERSION_LINUX = 3001;
    static final int MOVE = 8;
    private static Boolean isXWindowSystem;

    private static native boolean xMoveOrResizeWindow(Window window, int i, int i2, int i3);

    private static native boolean xShowWindowMenu(Window window, int i, int i2);

    FlatNativeLinuxLibrary() {
    }

    static boolean isLoaded() {
        return SystemInfo.isLinux && FlatNativeLibrary.isLoaded(API_VERSION_LINUX);
    }

    private static boolean isXWindowSystem() {
        if (isXWindowSystem == null) {
            isXWindowSystem = Boolean.valueOf(Toolkit.getDefaultToolkit().getClass().getName().endsWith(".XToolkit"));
        }
        return isXWindowSystem.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isWMUtilsSupported(Window window) {
        return hasCustomDecoration(window) && isXWindowSystem() && isLoaded();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean moveOrResizeWindow(Window window, MouseEvent e, int direction) {
        Point pt = scale(window, e.getLocationOnScreen());
        return xMoveOrResizeWindow(window, pt.x, pt.y, direction);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean showWindowMenu(Window window, MouseEvent e) {
        Point pt = scale(window, e.getLocationOnScreen());
        return xShowWindowMenu(window, pt.x, pt.y);
    }

    private static Point scale(Window window, Point pt) {
        GraphicsConfiguration gc = window.getGraphicsConfiguration();
        if (gc == null) {
            return pt;
        }
        AffineTransform transform = gc.getDefaultTransform();
        int x = (int) Math.round(pt.x * transform.getScaleX());
        int y = (int) Math.round(pt.y * transform.getScaleY());
        return new Point(x, y);
    }

    private static boolean hasCustomDecoration(Window window) {
        return ((window instanceof JFrame) && JFrame.isDefaultLookAndFeelDecorated() && ((JFrame) window).isUndecorated()) || ((window instanceof JDialog) && JDialog.isDefaultLookAndFeelDecorated() && ((JDialog) window).isUndecorated());
    }
}
