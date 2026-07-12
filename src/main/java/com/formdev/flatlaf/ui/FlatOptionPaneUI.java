package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.SwingUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.beans.PropertyChangeListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicOptionPaneUI;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatOptionPaneUI.class */
public class FlatOptionPaneUI extends BasicOptionPaneUI {
    protected boolean showIcon;
    protected int iconMessageGap;
    protected int messagePadding;
    protected int maxCharactersPerLine;
    private int focusWidth;
    private boolean sameSizeButtons;

    public static ComponentUI createUI(JComponent c) {
        return new FlatOptionPaneUI();
    }

    protected void installDefaults() {
        super.installDefaults();
        this.showIcon = UIManager.getBoolean("OptionPane.showIcon");
        this.iconMessageGap = UIManager.getInt("OptionPane.iconMessageGap");
        this.messagePadding = UIManager.getInt("OptionPane.messagePadding");
        this.maxCharactersPerLine = UIManager.getInt("OptionPane.maxCharactersPerLine");
        this.focusWidth = UIManager.getInt("Component.focusWidth");
        this.sameSizeButtons = FlatUIUtils.getUIBoolean("OptionPane.sameSizeButtons", true);
    }

    protected PropertyChangeListener createPropertyChangeListener() {
        PropertyChangeListener superListener = super.createPropertyChangeListener();
        return e -> {
            JRootPane rootPane;
            superListener.propertyChange(e);
            if (!this.showIcon && "ancestor".equals(e.getPropertyName()) && e.getNewValue() != null && (rootPane = SwingUtilities.getRootPane(this.optionPane)) != null && rootPane.getContentPane().getComponentCount() > 0 && rootPane.getContentPane().getComponent(0) == this.optionPane) {
                rootPane.putClientProperty(FlatClientProperties.TITLE_BAR_SHOW_ICON, false);
            }
        };
    }

    public Dimension getMinimumOptionPaneSize() {
        return UIScale.scale(super.getMinimumOptionPaneSize());
    }

    protected int getMaxCharactersPerLineCount() {
        int max = super.getMaxCharactersPerLineCount();
        return (this.maxCharactersPerLine <= 0 || max != Integer.MAX_VALUE) ? max : this.maxCharactersPerLine;
    }

    protected Container createMessageArea() {
        Component iconMessageSeparator;
        Container messageArea = super.createMessageArea();
        updateAreaPanel(messageArea);
        updateKnownChildPanels(messageArea);
        if (this.iconMessageGap > 0 && (iconMessageSeparator = SwingUtils.getComponentByName(messageArea, "OptionPane.separator")) != null) {
            iconMessageSeparator.setPreferredSize(new Dimension(UIScale.scale(this.iconMessageGap), 1));
        }
        return messageArea;
    }

    protected Container createButtonArea() {
        Container buttonArea = super.createButtonArea();
        updateAreaPanel(buttonArea);
        if (buttonArea.getLayout() instanceof BasicOptionPaneUI.ButtonAreaLayout) {
            BasicOptionPaneUI.ButtonAreaLayout layout = buttonArea.getLayout();
            layout.setPadding(UIScale.scale(layout.getPadding() - (this.focusWidth * 2)));
        }
        return buttonArea;
    }

    protected void addMessageComponents(Container container, GridBagConstraints cons, Object msg, int maxll, boolean internallyCreated) {
        if (this.messagePadding > 0) {
            cons.insets.bottom = UIScale.scale(this.messagePadding);
        }
        if (msg != null && !(msg instanceof Component) && !(msg instanceof Object[]) && !(msg instanceof Icon)) {
            msg = msg.toString();
            if (BasicHTML.isHTMLString((String) msg)) {
                maxll = Integer.MAX_VALUE;
            }
        }
        if (msg instanceof Box) {
            Box box = (Box) msg;
            if ("OptionPane.verticalBox".equals(box.getName()) && (box.getLayout() instanceof BoxLayout) && box.getLayout().getAxis() == 1) {
                box.addPropertyChangeListener("componentOrientation", e -> {
                    float alignX = box.getComponentOrientation().isLeftToRight() ? 0.0f : 1.0f;
                    for (JLabel jLabel : box.getComponents()) {
                        if ((jLabel instanceof JLabel) && "OptionPane.label".equals(jLabel.getName())) {
                            jLabel.setAlignmentX(alignX);
                        }
                    }
                });
            }
        }
        super.addMessageComponents(container, cons, msg, maxll, internallyCreated);
    }

    private void updateAreaPanel(Container area) {
        if (!(area instanceof JPanel)) {
            return;
        }
        JPanel panel = (JPanel) area;
        panel.setBorder(FlatUIUtils.nonUIResource(panel.getBorder()));
        panel.setOpaque(false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a2, code lost:
    
        switch(r10) {
            case 0: goto L25;
            case 1: goto L25;
            case 2: goto L25;
            case 3: goto L25;
            default: goto L26;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00c0, code lost:
    
        r0.setOpaque(false);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void updateKnownChildPanels(Container c) {
        JPanel[] components = c.getComponents();
        int length = components.length;
        for (int i = 0; i < length; i++) {
            JPanel jPanel = components[i];
            if ((jPanel instanceof JPanel) && jPanel.getName() != null) {
                String name = jPanel.getName();
                boolean z = -1;
                switch (name.hashCode()) {
                    case -1021586066:
                        if (name.equals("OptionPane.break")) {
                            z = 3;
                            break;
                        }
                        break;
                    case -870153839:
                        if (name.equals("OptionPane.realBody")) {
                            z = false;
                            break;
                        }
                        break;
                    case 1439207380:
                        if (name.equals("OptionPane.separator")) {
                            z = 2;
                            break;
                        }
                        break;
                    case 1768158035:
                        if (name.equals("OptionPane.body")) {
                            z = true;
                            break;
                        }
                        break;
                }
            }
            if (jPanel instanceof Container) {
                updateKnownChildPanels((Container) jPanel);
            }
        }
    }

    protected boolean getSizeButtonsToSameWidth() {
        return this.sameSizeButtons;
    }
}
