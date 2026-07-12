package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Supplier;
import javax.swing.DesktopManager;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatWindowResizer.class */
public abstract class FlatWindowResizer implements PropertyChangeListener, ComponentListener {
    protected static final Integer WINDOW_RESIZER_LAYER = Integer.valueOf(JLayeredPane.DRAG_LAYER.intValue() + 1);
    protected final JComponent resizeComp;
    protected final int borderDragThickness = FlatUIUtils.getUIInt("RootPane.borderDragThickness", 5);
    protected final int cornerDragWidth = FlatUIUtils.getUIInt("RootPane.cornerDragWidth", 16);
    protected final boolean honorFrameMinimumSizeOnResize = UIManager.getBoolean("RootPane.honorFrameMinimumSizeOnResize");
    protected final boolean honorDialogMinimumSizeOnResize = UIManager.getBoolean("RootPane.honorDialogMinimumSizeOnResize");
    protected final DragBorderComponent topDragComp = createDragBorderComponent(6, 8, 7);
    protected final DragBorderComponent bottomDragComp = createDragBorderComponent(4, 9, 5);
    protected final DragBorderComponent leftDragComp = createDragBorderComponent(6, 10, 4);
    protected final DragBorderComponent rightDragComp = createDragBorderComponent(7, 11, 5);

    protected abstract boolean isWindowResizable();

    protected abstract Rectangle getWindowBounds();

    protected abstract void setWindowBounds(Rectangle rectangle);

    protected abstract boolean limitToParentBounds();

    protected abstract Rectangle getParentBounds();

    protected abstract boolean honorMinimumSizeOnResize();

    protected abstract boolean honorMaximumSizeOnResize();

    protected abstract Dimension getWindowMinimumSize();

    protected abstract Dimension getWindowMaximumSize();

    protected FlatWindowResizer(JComponent resizeComp) {
        this.resizeComp = resizeComp;
        JComponent layeredPane = resizeComp instanceof JRootPane ? ((JRootPane) resizeComp).getLayeredPane() : resizeComp;
        Object cons = layeredPane instanceof JLayeredPane ? WINDOW_RESIZER_LAYER : null;
        layeredPane.add(this.topDragComp, cons, 0);
        layeredPane.add(this.bottomDragComp, cons, 1);
        layeredPane.add(this.leftDragComp, cons, 2);
        layeredPane.add(this.rightDragComp, cons, 3);
        resizeComp.addComponentListener(this);
        resizeComp.addPropertyChangeListener("ancestor", this);
        if (resizeComp.isDisplayable()) {
            addNotify();
        }
    }

    protected DragBorderComponent createDragBorderComponent(int leadingResizeDir, int centerResizeDir, int trailingResizeDir) {
        return new DragBorderComponent(leadingResizeDir, centerResizeDir, trailingResizeDir);
    }

    public void uninstall() {
        removeNotify();
        this.resizeComp.removeComponentListener(this);
        this.resizeComp.removePropertyChangeListener("ancestor", this);
        Container cont = this.topDragComp.getParent();
        cont.remove(this.topDragComp);
        cont.remove(this.bottomDragComp);
        cont.remove(this.leftDragComp);
        cont.remove(this.rightDragComp);
    }

    public void doLayout() {
        if (!this.topDragComp.isVisible()) {
            return;
        }
        int width = this.resizeComp.getWidth();
        int height = this.resizeComp.getHeight();
        if (width == 0 || height == 0) {
            return;
        }
        Insets resizeInsets = getResizeInsets();
        int thickness = UIScale.scale(this.borderDragThickness);
        int topThickness = Math.max(resizeInsets.top, thickness);
        int bottomThickness = Math.max(resizeInsets.bottom, thickness);
        int leftThickness = Math.max(resizeInsets.left, thickness);
        int rightThickness = Math.max(resizeInsets.right, thickness);
        int y2 = 0 + topThickness;
        int height2 = (height - topThickness) - bottomThickness;
        this.topDragComp.setBounds(0, 0, width, topThickness);
        this.bottomDragComp.setBounds(0, (0 + height) - bottomThickness, width, bottomThickness);
        this.leftDragComp.setBounds(0, y2, leftThickness, height2);
        this.rightDragComp.setBounds((0 + width) - rightThickness, y2, rightThickness, height2);
        int cornerDelta = UIScale.scale(this.cornerDragWidth - this.borderDragThickness);
        this.topDragComp.setCornerDragWidths(leftThickness + cornerDelta, rightThickness + cornerDelta);
        this.bottomDragComp.setCornerDragWidths(leftThickness + cornerDelta, rightThickness + cornerDelta);
        this.leftDragComp.setCornerDragWidths(cornerDelta, cornerDelta);
        this.rightDragComp.setCornerDragWidths(cornerDelta, cornerDelta);
    }

    protected Insets getResizeInsets() {
        return new Insets(0, 0, 0, 0);
    }

    protected void addNotify() {
        updateVisibility();
    }

    protected void removeNotify() {
        updateVisibility();
    }

    protected void updateVisibility() {
        boolean visible = isWindowResizable();
        if (visible == this.topDragComp.isVisible()) {
            return;
        }
        this.topDragComp.setVisible(visible);
        this.bottomDragComp.setVisible(visible);
        this.leftDragComp.setVisible(visible);
        this.rightDragComp.setEnabled(visible);
        if (visible) {
            this.rightDragComp.setVisible(true);
            doLayout();
        } else {
            this.rightDragComp.setBounds(0, 0, 1, 1);
        }
    }

    boolean isDialog() {
        return false;
    }

    protected void beginResizing(int resizeDir) {
    }

    protected void endResizing() {
    }

    @Override // java.beans.PropertyChangeListener
    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();
        boolean z = -1;
        switch (propertyName.hashCode()) {
            case -973829677:
                if (propertyName.equals("ancestor")) {
                    z = false;
                    break;
                }
                break;
            case 2144232107:
                if (propertyName.equals("resizable")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (e.getNewValue() != null) {
                    addNotify();
                    return;
                } else {
                    removeNotify();
                    return;
                }
            case true:
                updateVisibility();
                return;
            default:
                return;
        }
    }

    public void componentResized(ComponentEvent e) {
        doLayout();
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatWindowResizer$WindowResizer.class */
    public static class WindowResizer extends FlatWindowResizer implements WindowStateListener {
        protected Window window;
        private final JComponent centerComp;
        private final boolean limitResizeToScreenBounds;

        public WindowResizer(JRootPane rootPane) {
            super(rootPane);
            this.centerComp = new JPanel();
            this.centerComp.setOpaque(false);
            this.centerComp.setVisible(false);
            rootPane.getLayeredPane().add(this.centerComp, WINDOW_RESIZER_LAYER, 4);
            this.limitResizeToScreenBounds = SystemInfo.isLinux;
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        public void uninstall() {
            Container cont = this.topDragComp.getParent();
            cont.remove(this.centerComp);
            super.uninstall();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        public void doLayout() {
            super.doLayout();
            if (this.centerComp != null && this.centerComp.isVisible()) {
                this.centerComp.setBounds(0, 0, this.resizeComp.getWidth(), this.resizeComp.getHeight());
            }
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected void addNotify() {
            Window parent = this.resizeComp.getParent();
            this.window = parent instanceof Window ? parent : null;
            if (this.window instanceof Frame) {
                this.window.addPropertyChangeListener("resizable", this);
                this.window.addWindowStateListener(this);
            }
            super.addNotify();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected void removeNotify() {
            if (this.window instanceof Frame) {
                this.window.removePropertyChangeListener("resizable", this);
                this.window.removeWindowStateListener(this);
            }
            this.window = null;
            super.removeNotify();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected boolean isWindowResizable() {
            if (FlatUIUtils.isFullScreen(this.resizeComp)) {
                return false;
            }
            if (this.window instanceof Frame) {
                return this.window.isResizable() && (this.window.getExtendedState() & 6) == 0;
            }
            if (this.window instanceof Dialog) {
                return this.window.isResizable();
            }
            return false;
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected Rectangle getWindowBounds() {
            return this.window.getBounds();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected void setWindowBounds(Rectangle r) {
            this.window.setBounds(r);
            doLayout();
            if (Toolkit.getDefaultToolkit().isDynamicLayoutActive()) {
                this.window.validate();
                this.resizeComp.repaint();
            }
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected boolean limitToParentBounds() {
            return (!this.limitResizeToScreenBounds || this.window == null || this.window.getGraphicsConfiguration() == null) ? false : true;
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected Rectangle getParentBounds() {
            GraphicsConfiguration gc = this.window.getGraphicsConfiguration();
            Rectangle bounds = gc.getBounds();
            Insets insets = this.window.getToolkit().getScreenInsets(gc);
            return new Rectangle(bounds.x + insets.left, bounds.y + insets.top, (bounds.width - insets.left) - insets.right, (bounds.height - insets.top) - insets.bottom);
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected boolean honorMinimumSizeOnResize() {
            return (this.honorFrameMinimumSizeOnResize && (this.window instanceof Frame)) || (this.honorDialogMinimumSizeOnResize && (this.window instanceof Dialog));
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected boolean honorMaximumSizeOnResize() {
            return false;
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected Dimension getWindowMinimumSize() {
            return this.window.getMinimumSize();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected Dimension getWindowMaximumSize() {
            return this.window.getMaximumSize();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        boolean isDialog() {
            return this.window instanceof Dialog;
        }

        public void windowStateChanged(WindowEvent e) {
            updateVisibility();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected void beginResizing(int resizeDir) {
            this.centerComp.setBounds(0, 0, this.resizeComp.getWidth(), this.resizeComp.getHeight());
            this.centerComp.setCursor(Cursor.getPredefinedCursor(resizeDir));
            this.centerComp.setVisible(true);
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected void endResizing() {
            this.centerComp.setVisible(false);
            this.centerComp.setCursor((Cursor) null);
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatWindowResizer$InternalFrameResizer.class */
    public static class InternalFrameResizer extends FlatWindowResizer {
        protected final Supplier<DesktopManager> desktopManager;

        public InternalFrameResizer(JInternalFrame frame, Supplier<DesktopManager> desktopManager) {
            super(frame);
            this.desktopManager = desktopManager;
            frame.addPropertyChangeListener("resizable", this);
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        public void uninstall() {
            getFrame().removePropertyChangeListener("resizable", this);
            super.uninstall();
        }

        private JInternalFrame getFrame() {
            return this.resizeComp;
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected Insets getResizeInsets() {
            return getFrame().getInsets();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected boolean isWindowResizable() {
            return getFrame().isResizable();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected Rectangle getWindowBounds() {
            return getFrame().getBounds();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected void setWindowBounds(Rectangle r) {
            this.desktopManager.get().resizeFrame(getFrame(), r.x, r.y, r.width, r.height);
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected boolean limitToParentBounds() {
            return true;
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected Rectangle getParentBounds() {
            return new Rectangle(getFrame().getParent().getSize());
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected boolean honorMinimumSizeOnResize() {
            return true;
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected boolean honorMaximumSizeOnResize() {
            return true;
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected Dimension getWindowMinimumSize() {
            return getFrame().getMinimumSize();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected Dimension getWindowMaximumSize() {
            return getFrame().getMaximumSize();
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected void beginResizing(int resizeDir) {
            int direction = 0;
            switch (resizeDir) {
                case 4:
                    direction = 6;
                    break;
                case 5:
                    direction = 4;
                    break;
                case 6:
                    direction = 8;
                    break;
                case 7:
                    direction = 2;
                    break;
                case 8:
                    direction = 1;
                    break;
                case 9:
                    direction = 5;
                    break;
                case 10:
                    direction = 7;
                    break;
                case 11:
                    direction = 3;
                    break;
            }
            this.desktopManager.get().beginResizingFrame(getFrame(), direction);
        }

        @Override // com.formdev.flatlaf.ui.FlatWindowResizer
        protected void endResizing() {
            this.desktopManager.get().endResizingFrame(getFrame());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatWindowResizer$DragBorderComponent.class */
    public class DragBorderComponent extends JComponent implements MouseListener, MouseMotionListener {
        private final int leadingResizeDir;
        private final int centerResizeDir;
        private final int trailingResizeDir;
        private int resizeDir = -1;
        private int leadingCornerDragWidth;
        private int trailingCornerDragWidth;
        private int dragLeftOffset;
        private int dragRightOffset;
        private int dragTopOffset;
        private int dragBottomOffset;

        protected DragBorderComponent(int leadingResizeDir, int centerResizeDir, int trailingResizeDir) {
            this.leadingResizeDir = leadingResizeDir;
            this.centerResizeDir = centerResizeDir;
            this.trailingResizeDir = trailingResizeDir;
            setResizeDir(centerResizeDir);
            setVisible(false);
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        void setCornerDragWidths(int leading, int trailing) {
            this.leadingCornerDragWidth = leading;
            this.trailingCornerDragWidth = trailing;
        }

        protected void setResizeDir(int resizeDir) {
            if (this.resizeDir == resizeDir) {
                return;
            }
            this.resizeDir = resizeDir;
            setCursor(Cursor.getPredefinedCursor(resizeDir));
        }

        public Dimension getPreferredSize() {
            int thickness = UIScale.scale(FlatWindowResizer.this.borderDragThickness);
            return new Dimension(thickness, thickness);
        }

        protected void paintComponent(Graphics g) {
            super.paintChildren(g);
            FlatWindowResizer.this.updateVisibility();
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            if (!SwingUtilities.isLeftMouseButton(e) || !FlatWindowResizer.this.isWindowResizable()) {
                return;
            }
            int xOnScreen = e.getXOnScreen();
            int yOnScreen = e.getYOnScreen();
            Rectangle windowBounds = FlatWindowResizer.this.getWindowBounds();
            this.dragLeftOffset = xOnScreen - windowBounds.x;
            this.dragTopOffset = yOnScreen - windowBounds.y;
            this.dragRightOffset = (windowBounds.x + windowBounds.width) - xOnScreen;
            this.dragBottomOffset = (windowBounds.y + windowBounds.height) - yOnScreen;
            FlatWindowResizer.this.beginResizing(this.resizeDir);
        }

        public void mouseReleased(MouseEvent e) {
            if (!SwingUtilities.isLeftMouseButton(e) || !FlatWindowResizer.this.isWindowResizable()) {
                return;
            }
            this.dragBottomOffset = 0;
            this.dragTopOffset = 0;
            this.dragRightOffset = 0;
            this.dragLeftOffset = 0;
            FlatWindowResizer.this.endResizing();
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            int i;
            boolean topOrBottom = this.centerResizeDir == 8 || this.centerResizeDir == 9;
            int xy = topOrBottom ? e.getX() : e.getY();
            int wh = topOrBottom ? getWidth() : getHeight();
            if (xy <= this.leadingCornerDragWidth) {
                i = this.leadingResizeDir;
            } else if (xy >= wh - this.trailingCornerDragWidth) {
                i = this.trailingResizeDir;
            } else {
                i = this.centerResizeDir;
            }
            setResizeDir(i);
        }

        public void mouseDragged(MouseEvent e) {
            if (!SwingUtilities.isLeftMouseButton(e) || !FlatWindowResizer.this.isWindowResizable()) {
                return;
            }
            int xOnScreen = e.getXOnScreen();
            int yOnScreen = e.getYOnScreen();
            Rectangle oldBounds = FlatWindowResizer.this.getWindowBounds();
            Rectangle newBounds = new Rectangle(oldBounds);
            if (this.resizeDir == 8 || this.resizeDir == 6 || this.resizeDir == 7) {
                newBounds.y = yOnScreen - this.dragTopOffset;
                if (FlatWindowResizer.this.limitToParentBounds()) {
                    newBounds.y = Math.max(newBounds.y, FlatWindowResizer.this.getParentBounds().y);
                }
                newBounds.height += oldBounds.y - newBounds.y;
            }
            if (this.resizeDir == 9 || this.resizeDir == 4 || this.resizeDir == 5) {
                newBounds.height = (yOnScreen + this.dragBottomOffset) - newBounds.y;
                if (FlatWindowResizer.this.limitToParentBounds()) {
                    Rectangle parentBounds = FlatWindowResizer.this.getParentBounds();
                    int parentBottomY = parentBounds.y + parentBounds.height;
                    if (newBounds.y + newBounds.height > parentBottomY) {
                        newBounds.height = parentBottomY - newBounds.y;
                    }
                }
            }
            if (this.resizeDir == 10 || this.resizeDir == 6 || this.resizeDir == 4) {
                newBounds.x = xOnScreen - this.dragLeftOffset;
                if (FlatWindowResizer.this.limitToParentBounds()) {
                    newBounds.x = Math.max(newBounds.x, FlatWindowResizer.this.getParentBounds().x);
                }
                newBounds.width += oldBounds.x - newBounds.x;
            }
            if (this.resizeDir == 11 || this.resizeDir == 7 || this.resizeDir == 5) {
                newBounds.width = (xOnScreen + this.dragRightOffset) - newBounds.x;
                if (FlatWindowResizer.this.limitToParentBounds()) {
                    Rectangle parentBounds2 = FlatWindowResizer.this.getParentBounds();
                    int parentRightX = parentBounds2.x + parentBounds2.width;
                    if (newBounds.x + newBounds.width > parentRightX) {
                        newBounds.width = parentRightX - newBounds.x;
                    }
                }
            }
            Dimension minimumSize = FlatWindowResizer.this.honorMinimumSizeOnResize() ? FlatWindowResizer.this.getWindowMinimumSize() : null;
            if (minimumSize == null) {
                minimumSize = UIScale.scale(new Dimension(150, 50));
            }
            if (newBounds.width < minimumSize.width) {
                changeWidth(oldBounds, newBounds, minimumSize.width);
            }
            if (newBounds.height < minimumSize.height) {
                changeHeight(oldBounds, newBounds, minimumSize.height);
            }
            if (FlatWindowResizer.this.honorMaximumSizeOnResize()) {
                Dimension maximumSize = FlatWindowResizer.this.getWindowMaximumSize();
                if (newBounds.width > maximumSize.width) {
                    changeWidth(oldBounds, newBounds, maximumSize.width);
                }
                if (newBounds.height > maximumSize.height) {
                    changeHeight(oldBounds, newBounds, maximumSize.height);
                }
            }
            if (!newBounds.equals(oldBounds)) {
                FlatWindowResizer.this.setWindowBounds(newBounds);
            }
        }

        private void changeWidth(Rectangle oldBounds, Rectangle newBounds, int width) {
            if (newBounds.x != oldBounds.x) {
                newBounds.x -= width - newBounds.width;
            }
            newBounds.width = width;
        }

        private void changeHeight(Rectangle oldBounds, Rectangle newBounds, int height) {
            if (newBounds.y != oldBounds.y) {
                newBounds.y -= height - newBounds.height;
            }
            newBounds.height = height;
        }
    }
}
