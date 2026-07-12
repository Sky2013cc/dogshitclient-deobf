package com.formdev.flatlaf;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.LoggingFacade;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* loaded from: target.jar:com/formdev/flatlaf/SubMenuUsabilityHelper.class */
class SubMenuUsabilityHelper implements ChangeListener {
    private static final String KEY_USE_SAFE_TRIANGLE = "Menu.useSafeTriangle";
    private static final String KEY_SHOW_SAFE_TRIANGLE = "FlatLaf.debug.menu.showSafeTriangle";
    private static SubMenuUsabilityHelper instance;
    private boolean eventQueuePushNotSupported;
    private SubMenuEventQueue subMenuEventQueue;
    private SafeTrianglePainter safeTrianglePainter;
    private boolean changePending;
    private int mouseX;
    private int mouseY;
    private int targetX;
    private int targetTopY;
    private int targetBottomY;
    private Rectangle invokerBounds;

    SubMenuUsabilityHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized boolean install() {
        if (instance != null || !FlatSystemProperties.getBoolean(FlatSystemProperties.USE_SUB_MENU_SAFE_TRIANGLE, true)) {
            return false;
        }
        instance = new SubMenuUsabilityHelper();
        MenuSelectionManager.defaultManager().addChangeListener(instance);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void uninstall() {
        if (instance == null) {
            return;
        }
        MenuSelectionManager.defaultManager().removeChangeListener(instance);
        instance.uninstallEventQueue();
        instance = null;
    }

    public void stateChanged(ChangeEvent e) {
        if (this.eventQueuePushNotSupported || !FlatUIUtils.getUIBoolean(KEY_USE_SAFE_TRIANGLE, true)) {
            return;
        }
        synchronized (this) {
            if (this.changePending) {
                return;
            }
            this.changePending = true;
            EventQueue.invokeLater(() -> {
                synchronized (this) {
                    this.changePending = false;
                }
                menuSelectionChanged();
            });
        }
    }

    private void menuSelectionChanged() {
        Rectangle rectangle;
        int i;
        JPopupMenu[] selectedPath = MenuSelectionManager.defaultManager().getSelectedPath();
        int subMenuIndex = findSubMenu(selectedPath);
        if (subMenuIndex < 0 || subMenuIndex != selectedPath.length - 1) {
            uninstallEventQueue();
            return;
        }
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point mouseLocation = pointerInfo != null ? pointerInfo.getLocation() : new Point();
        this.mouseX = mouseLocation.x;
        this.mouseY = mouseLocation.y;
        JPopupMenu popup = selectedPath[subMenuIndex];
        if (!popup.isShowing()) {
            uninstallEventQueue();
            return;
        }
        Component invoker = popup.getInvoker();
        if (invoker != null && invoker.isShowing()) {
            rectangle = new Rectangle(invoker.getLocationOnScreen(), invoker.getSize());
        } else {
            rectangle = null;
        }
        this.invokerBounds = rectangle;
        if (this.invokerBounds != null && !this.invokerBounds.contains(this.mouseX, this.mouseY)) {
            uninstallEventQueue();
            return;
        }
        Point popupLocation = popup.getLocationOnScreen();
        Dimension popupSize = popup.getSize();
        if (this.mouseX < popupLocation.x + (popupSize.width / 2)) {
            i = popupLocation.x;
        } else {
            i = popupLocation.x + popupSize.width;
        }
        this.targetX = i;
        this.targetTopY = popupLocation.y;
        this.targetBottomY = popupLocation.y + popupSize.height;
        if (this.subMenuEventQueue == null) {
            SubMenuEventQueue queue = new SubMenuEventQueue();
            try {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                toolkit.getSystemEventQueue().push(queue);
                if (toolkit.getSystemEventQueue() != queue) {
                    this.eventQueuePushNotSupported = true;
                    LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to push submenu event queue. Disabling submenu safe triangle.", null);
                    return;
                }
                this.subMenuEventQueue = queue;
            } catch (RuntimeException ex) {
                this.eventQueuePushNotSupported = true;
                LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to push submenu event queue. Disabling submenu safe triangle.", ex);
                return;
            }
        }
        if (this.safeTrianglePainter == null && UIManager.getBoolean(KEY_SHOW_SAFE_TRIANGLE)) {
            this.safeTrianglePainter = new SafeTrianglePainter(popup);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uninstallEventQueue() {
        if (this.subMenuEventQueue != null) {
            this.subMenuEventQueue.uninstall();
            this.subMenuEventQueue = null;
        }
        if (this.safeTrianglePainter != null) {
            this.safeTrianglePainter.uninstall();
            this.safeTrianglePainter = null;
        }
    }

    private int findSubMenu(MenuElement[] path) {
        for (int i = path.length - 1; i >= 1; i--) {
            if ((path[i] instanceof JPopupMenu) && (path[i - 1] instanceof JMenu) && !((JMenu) path[i - 1]).isTopLevelMenu()) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Polygon createSafeTriangle() {
        return new Polygon(new int[]{this.mouseX, this.targetX, this.targetX}, new int[]{this.mouseY, this.targetTopY, this.targetBottomY}, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/formdev/flatlaf/SubMenuUsabilityHelper$SubMenuEventQueue.class */
    public class SubMenuEventQueue extends EventQueue {
        private Timer mouseUpdateTimer = new Timer(50, e -> {
            SubMenuUsabilityHelper.this.mouseX = this.newMouseX;
            SubMenuUsabilityHelper.this.mouseY = this.newMouseY;
            if (SubMenuUsabilityHelper.this.safeTrianglePainter != null) {
                SubMenuUsabilityHelper.this.safeTrianglePainter.repaint();
            }
        });
        private Timer timeoutTimer;
        private int newMouseX;
        private int newMouseY;
        private AWTEvent lastMouseEvent;

        SubMenuEventQueue() {
            this.mouseUpdateTimer.setRepeats(false);
            this.timeoutTimer = new Timer(200, e -> {
                if (SubMenuUsabilityHelper.this.invokerBounds != null && !SubMenuUsabilityHelper.this.invokerBounds.contains(this.newMouseX, this.newMouseY)) {
                    if (this.lastMouseEvent != null) {
                        postEvent(this.lastMouseEvent);
                        this.lastMouseEvent = null;
                    }
                    SubMenuUsabilityHelper.this.uninstallEventQueue();
                }
            });
            this.timeoutTimer.setRepeats(false);
        }

        void uninstall() {
            this.mouseUpdateTimer.stop();
            this.mouseUpdateTimer = null;
            this.timeoutTimer.stop();
            this.timeoutTimer = null;
            this.lastMouseEvent = null;
            super.pop();
        }

        protected void dispatchEvent(AWTEvent e) {
            int id = e.getID();
            if ((e instanceof MouseEvent) && (id == 503 || id == 506)) {
                this.newMouseX = ((MouseEvent) e).getXOnScreen();
                this.newMouseY = ((MouseEvent) e).getYOnScreen();
                if (SubMenuUsabilityHelper.this.safeTrianglePainter != null) {
                    SubMenuUsabilityHelper.this.safeTrianglePainter.repaint();
                }
                this.mouseUpdateTimer.stop();
                this.timeoutTimer.stop();
                if (SubMenuUsabilityHelper.this.createSafeTriangle().contains(this.newMouseX, this.newMouseY)) {
                    this.mouseUpdateTimer.start();
                    this.timeoutTimer.start();
                    this.lastMouseEvent = e;
                    return;
                } else {
                    SubMenuUsabilityHelper.this.mouseX = this.newMouseX;
                    SubMenuUsabilityHelper.this.mouseY = this.newMouseY;
                }
            }
            super.dispatchEvent(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/formdev/flatlaf/SubMenuUsabilityHelper$SafeTrianglePainter.class */
    public class SafeTrianglePainter extends JComponent {
        SafeTrianglePainter(JPopupMenu popup) {
            RootPaneContainer windowForComponent = SwingUtilities.windowForComponent(popup.getInvoker());
            if (windowForComponent instanceof RootPaneContainer) {
                JLayeredPane layeredPane = windowForComponent.getLayeredPane();
                setSize(layeredPane.getSize());
                layeredPane.add(this, Integer.valueOf(JLayeredPane.POPUP_LAYER.intValue() + 1));
            }
        }

        void uninstall() {
            Container parent = getParent();
            if (parent != null) {
                parent.remove(this);
                parent.repaint();
            }
        }

        protected void paintComponent(Graphics g) {
            Point locationOnScreen = getLocationOnScreen();
            g.translate(-locationOnScreen.x, -locationOnScreen.y);
            g.setColor(Color.red);
            ((Graphics2D) g).draw(SubMenuUsabilityHelper.this.createSafeTriangle());
        }
    }
}
