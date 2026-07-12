package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.beans.PropertyChangeListener;
import java.util.function.Predicate;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.BorderUIResource;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatNativeWindowBorder.class */
public class FlatNativeWindowBorder {
    private static final boolean canUseWindowDecorations;
    private static Boolean supported;
    private static Provider nativeProvider;

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatNativeWindowBorder$Provider.class */
    public interface Provider {
        public static final int SW_MAXIMIZE = 3;
        public static final int SW_MINIMIZE = 6;
        public static final int SW_RESTORE = 9;

        boolean hasCustomDecoration(Window window);

        void setHasCustomDecoration(Window window, boolean z);

        void updateTitleBarInfo(Window window, int i, Predicate<Point> predicate, Rectangle rectangle, Rectangle rectangle2, Rectangle rectangle3, Rectangle rectangle4);

        boolean showWindow(Window window, int i);

        boolean isColorizationColorAffectsBorders();

        Color getColorizationColor();

        int getColorizationColorBalance();

        void addChangeListener(ChangeListener changeListener);

        void removeChangeListener(ChangeListener changeListener);
    }

    static {
        canUseWindowDecorations = SystemInfo.isWindows_10_orLater && !((!SystemInfo.isWindows_11_orLater && FlatSystemProperties.getBoolean("sun.java2d.opengl", false)) || SystemInfo.isProjector || SystemInfo.isWebswing || SystemInfo.isWinPE || !FlatSystemProperties.getBoolean(FlatSystemProperties.USE_WINDOW_DECORATIONS, true));
    }

    public static boolean isSupported() {
        initialize();
        return supported.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object install(JRootPane rootPane) {
        if (!isSupported()) {
            return null;
        }
        Window parent = rootPane.getParent();
        if (parent != null && !(parent instanceof Window)) {
            return null;
        }
        if ((parent instanceof Window) && parent.isDisplayable()) {
            install(parent);
        }
        PropertyChangeListener ancestorListener = e -> {
            Object newValue = e.getNewValue();
            if (newValue instanceof Window) {
                install((Window) newValue);
            } else if (newValue == null && (e.getOldValue() instanceof Window)) {
                uninstall((Window) e.getOldValue());
            }
        };
        rootPane.addPropertyChangeListener("ancestor", ancestorListener);
        return ancestorListener;
    }

    static void install(Window window) {
        if (hasCustomDecoration(window) || UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
            return;
        }
        if (window instanceof JFrame) {
            JFrame frame = (JFrame) window;
            JRootPane rootPane = frame.getRootPane();
            if (!useWindowDecorations(rootPane) || frame.isUndecorated()) {
                return;
            }
            setHasCustomDecoration(frame, true);
            if (!hasCustomDecoration(frame)) {
                return;
            }
            rootPane.setWindowDecorationStyle(1);
            return;
        }
        if (window instanceof JDialog) {
            JDialog dialog = (JDialog) window;
            JRootPane rootPane2 = dialog.getRootPane();
            if (!useWindowDecorations(rootPane2) || dialog.isUndecorated()) {
                return;
            }
            setHasCustomDecoration(dialog, true);
            if (!hasCustomDecoration(dialog)) {
                return;
            }
            rootPane2.setWindowDecorationStyle(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void uninstall(JRootPane rootPane, Object data) {
        if (!isSupported()) {
            return;
        }
        if (data instanceof PropertyChangeListener) {
            rootPane.removePropertyChangeListener("ancestor", (PropertyChangeListener) data);
        }
        if ((UIManager.getLookAndFeel() instanceof FlatLaf) && useWindowDecorations(rootPane)) {
            return;
        }
        Window parent = rootPane.getParent();
        if (parent instanceof Window) {
            uninstall(parent);
        }
    }

    private static void uninstall(Window window) {
        if (!hasCustomDecoration(window)) {
            return;
        }
        setHasCustomDecoration(window, false);
        if (window instanceof JFrame) {
            JFrame frame = (JFrame) window;
            frame.getRootPane().setWindowDecorationStyle(0);
        } else if (window instanceof JDialog) {
            JDialog dialog = (JDialog) window;
            dialog.getRootPane().setWindowDecorationStyle(0);
        }
    }

    private static boolean useWindowDecorations(JRootPane rootPane) {
        return FlatUIUtils.getBoolean(rootPane, FlatSystemProperties.USE_WINDOW_DECORATIONS, FlatClientProperties.USE_WINDOW_DECORATIONS, "TitlePane.useWindowDecorations", false);
    }

    public static boolean hasCustomDecoration(Window window) {
        if (!isSupported()) {
            return false;
        }
        return nativeProvider.hasCustomDecoration(window);
    }

    public static void setHasCustomDecoration(Window window, boolean hasCustomDecoration) {
        if (!isSupported()) {
            return;
        }
        nativeProvider.setHasCustomDecoration(window, hasCustomDecoration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setTitleBarHeightAndHitTestSpots(Window window, int titleBarHeight, Predicate<Point> captionHitTestCallback, Rectangle appIconBounds, Rectangle minimizeButtonBounds, Rectangle maximizeButtonBounds, Rectangle closeButtonBounds) {
        if (!isSupported()) {
            return;
        }
        nativeProvider.updateTitleBarInfo(window, titleBarHeight, captionHitTestCallback, appIconBounds, minimizeButtonBounds, maximizeButtonBounds, closeButtonBounds);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean showWindow(Window window, int cmd) {
        if (!isSupported()) {
            return false;
        }
        return nativeProvider.showWindow(window, cmd);
    }

    private static void initialize() {
        if (supported != null) {
            return;
        }
        supported = false;
        if (!canUseWindowDecorations) {
            return;
        }
        try {
            setNativeProvider(FlatWindowsNativeWindowBorder.getInstance());
        } catch (Exception e) {
        }
    }

    public static void setNativeProvider(Provider provider) {
        if (nativeProvider != null) {
            throw new IllegalStateException();
        }
        nativeProvider = provider;
        supported = Boolean.valueOf(nativeProvider != null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatNativeWindowBorder$WindowTopBorder.class */
    public static class WindowTopBorder extends BorderUIResource.EmptyBorderUIResource {
        private static WindowTopBorder instance;
        private final Color activeLightColor;
        private final Color activeDarkColor;
        private final Color inactiveLightColor;
        private final Color inactiveDarkColor;
        private boolean colorizationAffectsBorders;
        private Color activeColor;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static WindowTopBorder getInstance() {
            if (instance == null) {
                instance = new WindowTopBorder();
            }
            return instance;
        }

        WindowTopBorder() {
            super(1, 0, 0, 0);
            this.activeLightColor = new Color(7368816);
            this.activeDarkColor = new Color(2960943);
            this.inactiveLightColor = new Color(11184810);
            this.inactiveDarkColor = new Color(4803147);
            update();
            installListeners();
        }

        void update() {
            this.colorizationAffectsBorders = isColorizationColorAffectsBorders();
            this.activeColor = calculateActiveBorderColor();
        }

        void installListeners() {
            FlatNativeWindowBorder.nativeProvider.addChangeListener(e -> {
                update();
                for (Window window : Window.getWindows()) {
                    if (window.isDisplayable()) {
                        window.repaint(0, 0, window.getWidth(), 1);
                    }
                }
            });
        }

        boolean isColorizationColorAffectsBorders() {
            return FlatNativeWindowBorder.nativeProvider.isColorizationColorAffectsBorders();
        }

        Color getColorizationColor() {
            return FlatNativeWindowBorder.nativeProvider.getColorizationColor();
        }

        int getColorizationColorBalance() {
            return FlatNativeWindowBorder.nativeProvider.getColorizationColorBalance();
        }

        private Color calculateActiveBorderColor() {
            if (!this.colorizationAffectsBorders) {
                return null;
            }
            Color colorizationColor = getColorizationColor();
            if (colorizationColor != null) {
                int colorizationColorBalance = getColorizationColorBalance();
                if (colorizationColorBalance < 0 || colorizationColorBalance > 100) {
                    colorizationColorBalance = 100;
                }
                if (colorizationColorBalance == 0) {
                    return new Color(14277081);
                }
                if (colorizationColorBalance == 100) {
                    return colorizationColor;
                }
                float alpha = colorizationColorBalance / 100.0f;
                float remainder = 1.0f - alpha;
                int r = Math.round((colorizationColor.getRed() * alpha) + (217.0f * remainder));
                int g = Math.round((colorizationColor.getGreen() * alpha) + (217.0f * remainder));
                int b = Math.round((colorizationColor.getBlue() * alpha) + (217.0f * remainder));
                return new Color(Math.min(Math.max(r, 0), 255), Math.min(Math.max(g, 0), 255), Math.min(Math.max(b, 0), 255));
            }
            Color activeBorderColor = (Color) Toolkit.getDefaultToolkit().getDesktopProperty("win.frame.activeBorderColor");
            return activeBorderColor != null ? activeBorderColor : UIManager.getColor("MenuBar.borderColor");
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Color color;
            Window window = SwingUtilities.windowForComponent(c);
            boolean active = window != null && window.isActive();
            boolean dark = FlatLaf.isLafDark();
            if (active) {
                color = this.activeColor != null ? this.activeColor : dark ? this.activeDarkColor : this.activeLightColor;
            } else {
                color = dark ? this.inactiveDarkColor : this.inactiveLightColor;
            }
            g.setColor(color);
            HiDPIUtils.paintAtScale1x((Graphics2D) g, x, y, width, height, this::paintImpl);
        }

        private void paintImpl(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
            g.fillRect(x, y, width, 1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void repaintBorder(Component c) {
            c.repaint(0, 0, c.getWidth(), 1);
        }
    }
}
