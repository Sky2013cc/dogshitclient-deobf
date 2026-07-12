package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatNativeWindowBorder;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Predicate;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatWindowsNativeWindowBorder.class */
class FlatWindowsNativeWindowBorder implements FlatNativeWindowBorder.Provider {
    private final Map<Window, WndProc> windowsMap = Collections.synchronizedMap(new IdentityHashMap());
    private final EventListenerList listenerList = new EventListenerList();
    private Timer fireStateChangedTimer;
    private boolean colorizationUpToDate;
    private boolean colorizationColorAffectsBorders;
    private Color colorizationColor;
    private int colorizationColorBalance;
    private static FlatWindowsNativeWindowBorder instance;

    private static native int registryGetIntValue(String str, String str2, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FlatNativeWindowBorder.Provider getInstance() {
        if (!SystemInfo.isWindows_10_orLater || !FlatNativeWindowsLibrary.isLoaded()) {
            return null;
        }
        if (instance == null) {
            instance = new FlatWindowsNativeWindowBorder();
        }
        return instance;
    }

    private FlatWindowsNativeWindowBorder() {
    }

    @Override // com.formdev.flatlaf.ui.FlatNativeWindowBorder.Provider
    public boolean hasCustomDecoration(Window window) {
        return this.windowsMap.containsKey(window);
    }

    @Override // com.formdev.flatlaf.ui.FlatNativeWindowBorder.Provider
    public void setHasCustomDecoration(Window window, boolean hasCustomDecoration) {
        if (hasCustomDecoration) {
            install(window);
        } else {
            uninstall(window);
        }
    }

    private void install(Window window) {
        if (!SystemInfo.isWindows_10_orLater) {
            return;
        }
        if (!(window instanceof JFrame) && !(window instanceof JDialog)) {
            return;
        }
        if (!(window instanceof Frame) || !((Frame) window).isUndecorated()) {
            if (((window instanceof Dialog) && ((Dialog) window).isUndecorated()) || this.windowsMap.containsKey(window)) {
                return;
            }
            try {
                WndProc wndProc = new WndProc(window);
                if (wndProc.hwnd == 0) {
                    return;
                }
                this.windowsMap.put(window, wndProc);
            } catch (UnsatisfiedLinkError ex) {
                LoggingFacade.INSTANCE.logSevere(null, ex);
            }
        }
    }

    private void uninstall(Window window) {
        WndProc wndProc = this.windowsMap.remove(window);
        if (wndProc != null) {
            wndProc.uninstall();
        }
    }

    @Override // com.formdev.flatlaf.ui.FlatNativeWindowBorder.Provider
    public void updateTitleBarInfo(Window window, int titleBarHeight, Predicate<Point> captionHitTestCallback, Rectangle appIconBounds, Rectangle minimizeButtonBounds, Rectangle maximizeButtonBounds, Rectangle closeButtonBounds) {
        WndProc wndProc = this.windowsMap.get(window);
        if (wndProc == null) {
            return;
        }
        wndProc.titleBarHeight = titleBarHeight;
        wndProc.captionHitTestCallback = captionHitTestCallback;
        wndProc.appIconBounds = cloneRectange(appIconBounds);
        wndProc.minimizeButtonBounds = cloneRectange(minimizeButtonBounds);
        wndProc.maximizeButtonBounds = cloneRectange(maximizeButtonBounds);
        wndProc.closeButtonBounds = cloneRectange(closeButtonBounds);
    }

    private static Rectangle cloneRectange(Rectangle rect) {
        if (rect != null) {
            return new Rectangle(rect);
        }
        return null;
    }

    @Override // com.formdev.flatlaf.ui.FlatNativeWindowBorder.Provider
    public boolean showWindow(Window window, int cmd) {
        WndProc wndProc = this.windowsMap.get(window);
        if (wndProc == null) {
            return false;
        }
        wndProc.showWindow(wndProc.hwnd, cmd);
        return true;
    }

    @Override // com.formdev.flatlaf.ui.FlatNativeWindowBorder.Provider
    public boolean isColorizationColorAffectsBorders() {
        updateColorization();
        return this.colorizationColorAffectsBorders;
    }

    @Override // com.formdev.flatlaf.ui.FlatNativeWindowBorder.Provider
    public Color getColorizationColor() {
        updateColorization();
        return this.colorizationColor;
    }

    @Override // com.formdev.flatlaf.ui.FlatNativeWindowBorder.Provider
    public int getColorizationColorBalance() {
        updateColorization();
        return this.colorizationColorBalance;
    }

    private void updateColorization() {
        if (this.colorizationUpToDate) {
            return;
        }
        this.colorizationUpToDate = true;
        this.colorizationColorAffectsBorders = registryGetIntValue("SOFTWARE\\Microsoft\\Windows\\DWM", "ColorPrevalence", -1) > 0;
        int value = registryGetIntValue("SOFTWARE\\Microsoft\\Windows\\DWM", "ColorizationColor", -1);
        this.colorizationColor = value != -1 ? new Color(value) : null;
        this.colorizationColorBalance = registryGetIntValue("SOFTWARE\\Microsoft\\Windows\\DWM", "ColorizationColorBalance", -1);
    }

    @Override // com.formdev.flatlaf.ui.FlatNativeWindowBorder.Provider
    public void addChangeListener(ChangeListener l) {
        this.listenerList.add(ChangeListener.class, l);
    }

    @Override // com.formdev.flatlaf.ui.FlatNativeWindowBorder.Provider
    public void removeChangeListener(ChangeListener l) {
        this.listenerList.remove(ChangeListener.class, l);
    }

    private void fireStateChanged() {
        Object[] listeners = this.listenerList.getListenerList();
        if (listeners.length == 0) {
            return;
        }
        ChangeEvent e = new ChangeEvent(this);
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ChangeListener.class) {
                ((ChangeListener) listeners[i + 1]).stateChanged(e);
            }
        }
    }

    void fireStateChangedLaterOnce() {
        EventQueue.invokeLater(() -> {
            if (this.fireStateChangedTimer != null) {
                this.fireStateChangedTimer.restart();
                return;
            }
            this.fireStateChangedTimer = new Timer(OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT, e -> {
                this.fireStateChangedTimer = null;
                this.colorizationUpToDate = false;
                fireStateChanged();
            });
            this.fireStateChangedTimer.setRepeats(false);
            this.fireStateChangedTimer.start();
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatWindowsNativeWindowBorder$WndProc.class */
    public class WndProc implements PropertyChangeListener {
        private static final int HTCLIENT = 1;
        private static final int HTCAPTION = 2;
        private static final int HTSYSMENU = 3;
        private static final int HTMINBUTTON = 8;
        private static final int HTMAXBUTTON = 9;
        private static final int HTTOP = 12;
        private static final int HTCLOSE = 20;
        private Window window;
        private final long hwnd;
        private int titleBarHeight;
        private Predicate<Point> captionHitTestCallback;
        private Rectangle appIconBounds;
        private Rectangle minimizeButtonBounds;
        private Rectangle maximizeButtonBounds;
        private Rectangle closeButtonBounds;

        private native long installImpl(Window window);

        private native void uninstallImpl(long j);

        private native void updateFrame(long j, int i);

        private native void setWindowBackground(long j, int i, int i2, int i3);

        /* JADX INFO: Access modifiers changed from: private */
        public native void showWindow(long j, int i);

        WndProc(Window window) {
            this.window = window;
            this.hwnd = installImpl(window);
            if (this.hwnd == 0) {
                return;
            }
            updateFrame(this.hwnd, window instanceof JFrame ? ((JFrame) window).getExtendedState() : 0);
            updateWindowBackground();
            window.addPropertyChangeListener("background", this);
        }

        void uninstall() {
            this.window.removePropertyChangeListener("background", this);
            uninstallImpl(this.hwnd);
            this.window = null;
        }

        @Override // java.beans.PropertyChangeListener
        public void propertyChange(PropertyChangeEvent e) {
            updateWindowBackground();
        }

        private void updateWindowBackground() {
            Color bg = this.window.getBackground();
            if (bg != null) {
                setWindowBackground(this.hwnd, bg.getRed(), bg.getGreen(), bg.getBlue());
            }
        }

        private int onNcHitTest(int x, int y, boolean isOnResizeBorder) {
            Point pt = scaleDown(x, y);
            if (contains(this.appIconBounds, pt)) {
                return 3;
            }
            if (contains(this.minimizeButtonBounds, pt)) {
                return 8;
            }
            if (contains(this.maximizeButtonBounds, pt)) {
                return 9;
            }
            if (contains(this.closeButtonBounds, pt)) {
                return 20;
            }
            if (isOnResizeBorder) {
                return 12;
            }
            boolean isOnTitleBar = pt.y < this.titleBarHeight;
            if (isOnTitleBar) {
                try {
                    if (this.captionHitTestCallback == null) {
                        return 2;
                    }
                    if (!this.captionHitTestCallback.test(pt)) {
                        return 1;
                    }
                    return 2;
                } catch (Throwable th) {
                    return 2;
                }
            }
            return 1;
        }

        private boolean contains(Rectangle rect, Point pt) {
            return rect != null && rect.contains(pt);
        }

        private Point scaleDown(int x, int y) {
            GraphicsConfiguration gc = this.window.getGraphicsConfiguration();
            if (gc == null) {
                return new Point(x, y);
            }
            AffineTransform t = gc.getDefaultTransform();
            return new Point(clipRound(x / t.getScaleX()), clipRound(y / t.getScaleY()));
        }

        private int clipRound(double value) {
            double value2 = value - 0.5d;
            if (value2 < -2.147483648E9d) {
                return Integer.MIN_VALUE;
            }
            if (value2 > 2.147483647E9d) {
                return Integer.MAX_VALUE;
            }
            return (int) Math.ceil(value2);
        }

        private boolean isFullscreen() {
            GraphicsConfiguration gc = this.window.getGraphicsConfiguration();
            return gc != null && gc.getDevice().getFullScreenWindow() == this.window;
        }

        private void fireStateChangedLaterOnce() {
            FlatWindowsNativeWindowBorder.this.fireStateChangedLaterOnce();
        }
    }
}
