package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.ScaledImageIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatInternalFrameTitlePane.class */
public class FlatInternalFrameTitlePane extends BasicInternalFrameTitlePane {
    private JLabel titleLabel;
    private JPanel buttonPanel;

    public FlatInternalFrameTitlePane(JInternalFrame f) {
        super(f);
    }

    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installBorder(this, "InternalFrameTitlePane.border");
    }

    protected PropertyChangeListener createPropertyChangeListener() {
        return new FlatPropertyChangeHandler();
    }

    protected LayoutManager createLayout() {
        return new BorderLayout(UIScale.scale(4), 0);
    }

    protected void createButtons() {
        super.createButtons();
        this.iconButton.setContentAreaFilled(false);
        this.maxButton.setContentAreaFilled(false);
        this.closeButton.setContentAreaFilled(false);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        this.iconButton.setBorder(emptyBorder);
        this.maxButton.setBorder(emptyBorder);
        this.closeButton.setBorder(emptyBorder);
        updateButtonsVisibility();
    }

    protected void addSubComponents() {
        this.titleLabel = new JLabel(this.frame.getTitle());
        this.titleLabel.setFont(FlatUIUtils.nonUIResource(getFont()));
        this.titleLabel.setMinimumSize(new Dimension(UIScale.scale(32), 1));
        updateFrameIcon();
        updateColors();
        this.buttonPanel = new JPanel() { // from class: com.formdev.flatlaf.ui.FlatInternalFrameTitlePane.1
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                int height = size.height;
                if (!FlatInternalFrameTitlePane.this.iconButton.isVisible()) {
                    height = Math.max(height, FlatInternalFrameTitlePane.this.iconButton.getPreferredSize().height);
                }
                if (!FlatInternalFrameTitlePane.this.maxButton.isVisible()) {
                    height = Math.max(height, FlatInternalFrameTitlePane.this.maxButton.getPreferredSize().height);
                }
                if (!FlatInternalFrameTitlePane.this.closeButton.isVisible()) {
                    height = Math.max(height, FlatInternalFrameTitlePane.this.closeButton.getPreferredSize().height);
                }
                return new Dimension(size.width, height);
            }
        };
        this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, 2));
        this.buttonPanel.setOpaque(false);
        this.buttonPanel.add(this.iconButton);
        this.buttonPanel.add(this.maxButton);
        this.buttonPanel.add(this.closeButton);
        add(this.titleLabel, "Center");
        add(this.buttonPanel, "After");
    }

    protected void updateFrameIcon() {
        Icon frameIcon = this.frame.getFrameIcon();
        if (frameIcon != null && (frameIcon.getIconWidth() == 0 || frameIcon.getIconHeight() == 0)) {
            frameIcon = null;
        } else if (frameIcon instanceof ImageIcon) {
            frameIcon = new ScaledImageIcon((ImageIcon) frameIcon);
        }
        this.titleLabel.setIcon(frameIcon);
    }

    protected void updateColors() {
        Color background = FlatUIUtils.nonUIResource(this.frame.isSelected() ? this.selectedTitleColor : this.notSelectedTitleColor);
        Color foreground = FlatUIUtils.nonUIResource(this.frame.isSelected() ? this.selectedTextColor : this.notSelectedTextColor);
        this.titleLabel.setForeground(foreground);
        this.iconButton.setBackground(background);
        this.iconButton.setForeground(foreground);
        this.maxButton.setBackground(background);
        this.maxButton.setForeground(foreground);
        this.closeButton.setBackground(background);
        this.closeButton.setForeground(foreground);
    }

    protected void updateButtonsVisibility() {
        this.iconButton.setVisible(this.frame.isIconifiable());
        this.maxButton.setVisible(this.frame.isMaximizable());
        this.closeButton.setVisible(this.frame.isClosable());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Rectangle getFrameIconBounds() {
        Icon icon = this.titleLabel.getIcon();
        if (icon == null) {
            return null;
        }
        int iconWidth = icon.getIconWidth();
        int iconHeight = icon.getIconHeight();
        boolean leftToRight = this.titleLabel.getComponentOrientation().isLeftToRight();
        int x = this.titleLabel.getX() + (leftToRight ? 0 : this.titleLabel.getWidth() - iconWidth);
        int y = this.titleLabel.getY() + ((this.titleLabel.getHeight() - iconHeight) / 2);
        return new Rectangle(x, y, iconWidth, iconHeight);
    }

    protected void assembleSystemMenu() {
    }

    protected void showSystemMenu() {
    }

    public void paintComponent(Graphics g) {
        paintTitleBackground(g);
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatInternalFrameTitlePane$FlatPropertyChangeHandler.class */
    protected class FlatPropertyChangeHandler extends BasicInternalFrameTitlePane.PropertyChangeHandler {
        protected FlatPropertyChangeHandler() {
            super(FlatInternalFrameTitlePane.this);
        }

        public void propertyChange(PropertyChangeEvent e) {
            String propertyName = e.getPropertyName();
            boolean z = -1;
            switch (propertyName.hashCode()) {
                case -1010695135:
                    if (propertyName.equals("opaque")) {
                        z = 7;
                        break;
                    }
                    break;
                case -737546925:
                    if (propertyName.equals("iconable")) {
                        z = 3;
                        break;
                    }
                    break;
                case 110371416:
                    if (propertyName.equals("title")) {
                        z = false;
                        break;
                    }
                    break;
                case 544791430:
                    if (propertyName.equals("frameIcon")) {
                        z = true;
                        break;
                    }
                    break;
                case 1092709095:
                    if (propertyName.equals("closable")) {
                        z = 5;
                        break;
                    }
                    break;
                case 1191572123:
                    if (propertyName.equals("selected")) {
                        z = 2;
                        break;
                    }
                    break;
                case 1247047827:
                    if (propertyName.equals("componentOrientation")) {
                        z = 6;
                        break;
                    }
                    break;
                case 1354515859:
                    if (propertyName.equals("maximizable")) {
                        z = 4;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    FlatInternalFrameTitlePane.this.titleLabel.setText(FlatInternalFrameTitlePane.this.frame.getTitle());
                    break;
                case true:
                    FlatInternalFrameTitlePane.this.updateFrameIcon();
                    break;
                case true:
                    FlatInternalFrameTitlePane.this.updateColors();
                    break;
                case true:
                case true:
                case true:
                    FlatInternalFrameTitlePane.this.updateButtonsVisibility();
                    FlatInternalFrameTitlePane.this.enableActions();
                    FlatInternalFrameTitlePane.this.revalidate();
                    FlatInternalFrameTitlePane.this.repaint();
                    return;
                case true:
                    FlatInternalFrameTitlePane.this.applyComponentOrientation(FlatInternalFrameTitlePane.this.frame.getComponentOrientation());
                    break;
                case true:
                    return;
            }
            super.propertyChange(e);
        }
    }
}
