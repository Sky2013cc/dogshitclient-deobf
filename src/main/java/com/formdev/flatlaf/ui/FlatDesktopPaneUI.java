package com.formdev.flatlaf.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicDesktopPaneUI;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatDesktopPaneUI.class */
public class FlatDesktopPaneUI extends BasicDesktopPaneUI {
    private LayoutDockListener layoutDockListener;
    private boolean layoutDockPending;

    public static ComponentUI createUI(JComponent c) {
        return new FlatDesktopPaneUI();
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        layoutDockLaterOnce();
    }

    protected void installListeners() {
        super.installListeners();
        this.layoutDockListener = new LayoutDockListener();
        this.desktop.addContainerListener(this.layoutDockListener);
        this.desktop.addComponentListener(this.layoutDockListener);
    }

    protected void uninstallListeners() {
        super.uninstallListeners();
        this.desktop.removeContainerListener(this.layoutDockListener);
        this.desktop.removeComponentListener(this.layoutDockListener);
        this.layoutDockListener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void layoutDockLaterOnce() {
        if (this.layoutDockPending) {
            return;
        }
        this.layoutDockPending = true;
        EventQueue.invokeLater(() -> {
            this.layoutDockPending = false;
            if (this.desktop != null) {
                layoutDock();
            }
        });
    }

    protected void layoutDock() {
        Dimension desktopSize = this.desktop.getSize();
        int x = 0;
        int y = desktopSize.height;
        int rowHeight = 0;
        for (JInternalFrame.JDesktopIcon jDesktopIcon : this.desktop.getComponents()) {
            if (jDesktopIcon instanceof JInternalFrame.JDesktopIcon) {
                JInternalFrame.JDesktopIcon icon = jDesktopIcon;
                Dimension iconSize = icon.getPreferredSize();
                if (x + iconSize.width > desktopSize.width) {
                    x = 0;
                    y -= rowHeight;
                    rowHeight = 0;
                }
                icon.setLocation(x, y - iconSize.height);
                x += iconSize.width;
                rowHeight = Math.max(iconSize.height, rowHeight);
            }
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatDesktopPaneUI$LayoutDockListener.class */
    private class LayoutDockListener extends ComponentAdapter implements ContainerListener {
        private LayoutDockListener() {
        }

        public void componentAdded(ContainerEvent e) {
            FlatDesktopPaneUI.this.layoutDockLaterOnce();
        }

        public void componentRemoved(ContainerEvent e) {
            FlatDesktopPaneUI.this.layoutDockLaterOnce();
        }

        public void componentResized(ComponentEvent e) {
            FlatDesktopPaneUI.this.layoutDockLaterOnce();
        }
    }
}
