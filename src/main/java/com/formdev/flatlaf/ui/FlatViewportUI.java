package com.formdev.flatlaf.ui;

import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicViewportUI;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatViewportUI.class */
public class FlatViewportUI extends BasicViewportUI {

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatViewportUI$ViewportPainter.class */
    public interface ViewportPainter {
        void paintViewport(Graphics graphics, JComponent jComponent, JViewport jViewport);
    }

    public static ComponentUI createUI(JComponent c) {
        return FlatUIUtils.createSharedUI(FlatViewportUI.class, FlatViewportUI::new);
    }

    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        JComponent view = ((JViewport) c).getView();
        if (view instanceof JComponent) {
            ViewportPainter ui = JavaCompatibility2.getUI(view);
            if (ui instanceof ViewportPainter) {
                ui.paintViewport(g, view, (JViewport) c);
            }
        }
    }
}
