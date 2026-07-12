package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:com/formdev/flatlaf/ui/FullWindowContentSupport.class */
public class FullWindowContentSupport {
    private static final String KEY_DEBUG_SHOW_PLACEHOLDERS = "FlatLaf.debug.panel.showPlaceholders";
    private static ArrayList<WeakReference<JComponent>> placeholders = new ArrayList<>();

    FullWindowContentSupport() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Dimension getPlaceholderPreferredSize(JComponent c, String options) {
        JRootPane rootPane;
        Rectangle r;
        if (options.startsWith(SystemInfo.isMacOS ? "mac" : "win") && c.isDisplayable() && (rootPane = SwingUtilities.getRootPane(c)) != null) {
            Rectangle rectangle = (Rectangle) rootPane.getClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT_BUTTONS_BOUNDS);
            Rectangle bounds = rectangle;
            if (rectangle != null) {
                if (options.length() > 3 && ((options.contains("leftToRight") && !c.getComponentOrientation().isLeftToRight()) || (options.contains("rightToLeft") && c.getComponentOrientation().isLeftToRight()))) {
                    return new Dimension(0, 0);
                }
                if (SystemInfo.isMacFullWindowContentSupported && FlatNativeMacLibrary.isLoaded() && (r = FlatNativeMacLibrary.getWindowButtonsBounds(SwingUtilities.windowForComponent(c))) != null) {
                    bounds = r;
                }
                int width = bounds.width;
                int height = bounds.height;
                if (options.length() > 3) {
                    if (width == 0 && options.contains("zeroInFullScreen")) {
                        height = 0;
                    }
                    if (options.contains("horizontal")) {
                        height = 0;
                    }
                    if (options.contains("vertical")) {
                        width = 0;
                    }
                }
                return new Dimension(width, height);
            }
        }
        return new Dimension(0, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void registerPlaceholder(JComponent c) {
        synchronized (placeholders) {
            if (indexOfPlaceholder(c) < 0) {
                placeholders.add(new WeakReference<>(c));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void unregisterPlaceholder(JComponent c) {
        synchronized (placeholders) {
            int index = indexOfPlaceholder(c);
            if (index >= 0) {
                placeholders.remove(index);
            }
        }
    }

    private static int indexOfPlaceholder(JComponent c) {
        int size = placeholders.size();
        for (int i = 0; i < size; i++) {
            if (placeholders.get(i).get() == c) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void revalidatePlaceholders(Component container) {
        synchronized (placeholders) {
            if (placeholders.isEmpty()) {
                return;
            }
            Iterator<WeakReference<JComponent>> it = placeholders.iterator();
            while (it.hasNext()) {
                WeakReference<JComponent> ref = it.next();
                JComponent c = ref.get();
                if (c == null) {
                    it.remove();
                } else if (SwingUtilities.isDescendingFrom(c, container)) {
                    c.revalidate();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ComponentListener macInstallListeners(final JRootPane rootPane) {
        ComponentAdapter componentAdapter = new ComponentAdapter() { // from class: com.formdev.flatlaf.ui.FullWindowContentSupport.1
            boolean lastFullScreen;

            public void componentResized(ComponentEvent e) {
                Window window = SwingUtilities.windowForComponent(rootPane);
                if (window == null) {
                    return;
                }
                boolean fullScreen = FlatNativeMacLibrary.isLoaded() && FlatNativeMacLibrary.isWindowFullScreen(window);
                if (fullScreen == this.lastFullScreen) {
                    return;
                }
                this.lastFullScreen = fullScreen;
                FullWindowContentSupport.macUpdateFullWindowContentButtonsBoundsProperty(rootPane);
            }
        };
        rootPane.addComponentListener(componentAdapter);
        return componentAdapter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void macUninstallListeners(JRootPane rootPane, ComponentListener l) {
        if (l != null) {
            rootPane.removeComponentListener(l);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void macUpdateFullWindowContentButtonsBoundsProperty(JRootPane rootPane) {
        Rectangle rectangle;
        if (!SystemInfo.isMacFullWindowContentSupported || !rootPane.isDisplayable()) {
            return;
        }
        Rectangle bounds = null;
        if (FlatClientProperties.clientPropertyBoolean(rootPane, "apple.awt.fullWindowContent", false)) {
            if (FlatNativeMacLibrary.isLoaded()) {
                rectangle = FlatNativeMacLibrary.getWindowButtonsBounds(SwingUtilities.windowForComponent(rootPane));
            } else {
                rectangle = new Rectangle(68, 28);
            }
            bounds = rectangle;
        }
        rootPane.putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT_BUTTONS_BOUNDS, bounds);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void macUninstallFullWindowContentButtonsBoundsProperty(JRootPane rootPane) {
        if (!SystemInfo.isMacFullWindowContentSupported) {
            return;
        }
        rootPane.putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT_BUTTONS_BOUNDS, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void debugPaint(Graphics g, JComponent c) {
        Rectangle bounds;
        if (!UIManager.getBoolean(KEY_DEBUG_SHOW_PLACEHOLDERS)) {
            return;
        }
        int width = c.getWidth();
        int height = c.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        g.setColor(Color.red);
        debugPaintRect(g, new Rectangle(width, height));
        JRootPane rootPane = SwingUtilities.getRootPane(c);
        if (rootPane == null || (bounds = (Rectangle) rootPane.getClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT_BUTTONS_BOUNDS)) == null) {
            return;
        }
        if (bounds.width != width || bounds.height != height) {
            g.setColor(Color.magenta);
            debugPaintRect(g, SwingUtilities.convertRectangle(rootPane, bounds, c));
        }
    }

    private static void debugPaintRect(Graphics g, Rectangle r) {
        g.drawRect(r.x, r.y, r.width - 1, r.height - 1);
        int x2 = (r.x + r.width) - 1;
        int y2 = (r.y + r.height) - 1;
        Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
        g.drawLine(r.x, r.y, x2, y2);
        g.drawLine(r.x, y2, x2, r.y);
        FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
    }
}
