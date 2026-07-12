package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.icons.FlatHelpButtonIcon;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeEvent;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.View;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatButtonUI.class */
public class FlatButtonUI extends BasicButtonUI implements FlatStylingSupport.StyleableUI {

    @FlatStylingSupport.Styleable
    protected int minimumWidth;
    protected int iconTextGap;
    protected Color background;
    protected Color foreground;
    protected Color startBackground;
    protected Color endBackground;

    @FlatStylingSupport.Styleable
    protected Color focusedBackground;

    @FlatStylingSupport.Styleable
    protected Color focusedForeground;

    @FlatStylingSupport.Styleable
    protected Color hoverBackground;

    @FlatStylingSupport.Styleable
    protected Color hoverForeground;

    @FlatStylingSupport.Styleable
    protected Color pressedBackground;

    @FlatStylingSupport.Styleable
    protected Color pressedForeground;

    @FlatStylingSupport.Styleable
    protected Color selectedBackground;

    @FlatStylingSupport.Styleable
    protected Color selectedForeground;

    @FlatStylingSupport.Styleable
    protected Color disabledBackground;

    @FlatStylingSupport.Styleable
    protected Color disabledText;

    @FlatStylingSupport.Styleable
    protected Color disabledSelectedBackground;

    @FlatStylingSupport.Styleable
    protected Color disabledSelectedForeground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color defaultBackground;
    protected Color defaultEndBackground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color defaultForeground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color defaultFocusedBackground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color defaultFocusedForeground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color defaultHoverBackground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color defaultHoverForeground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color defaultPressedBackground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color defaultPressedForeground;

    @FlatStylingSupport.Styleable(dot = true)
    protected boolean defaultBoldText;

    @FlatStylingSupport.Styleable
    protected boolean paintShadow;

    @FlatStylingSupport.Styleable
    protected int shadowWidth;

    @FlatStylingSupport.Styleable
    protected Color shadowColor;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color defaultShadowColor;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color toolbarHoverBackground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color toolbarHoverForeground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color toolbarPressedBackground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color toolbarPressedForeground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color toolbarSelectedBackground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color toolbarSelectedForeground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color toolbarDisabledSelectedBackground;

    @FlatStylingSupport.Styleable(dot = true)
    protected Color toolbarDisabledSelectedForeground;

    @FlatStylingSupport.Styleable
    protected String buttonType;

    @FlatStylingSupport.Styleable
    protected boolean squareSize;

    @FlatStylingSupport.Styleable
    protected int minimumHeight;
    private Icon helpButtonIcon;
    private Insets defaultMargin;
    private final boolean shared;
    private boolean helpButtonIconShared = true;
    private boolean defaults_initialized = false;
    private Map<String, Object> oldStyleValues;
    private AtomicBoolean borderShared;
    static final int TYPE_OTHER = -1;
    static final int TYPE_SQUARE = 0;
    static final int TYPE_ROUND_RECT = 1;
    private static Rectangle viewR = new Rectangle();
    private static Rectangle textR = new Rectangle();
    private static Rectangle iconR = new Rectangle();

    public static ComponentUI createUI(JComponent c) {
        if (FlatUIUtils.canUseSharedUI(c) && !FlatUIUtils.needsLightAWTPeer(c)) {
            return FlatUIUtils.createSharedUI(FlatButtonUI.class, () -> {
                return new FlatButtonUI(true);
            });
        }
        return new FlatButtonUI(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FlatButtonUI(boolean shared) {
        this.shared = shared;
    }

    public void installUI(JComponent c) {
        if (FlatUIUtils.needsLightAWTPeer(c)) {
            FlatUIUtils.runWithLightAWTPeerUIDefaults(() -> {
                installUIImpl(c);
            });
        } else {
            installUIImpl(c);
        }
    }

    private void installUIImpl(JComponent c) {
        super.installUI(c);
        installStyle((AbstractButton) c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void installDefaults(AbstractButton b) {
        Color bg;
        super.installDefaults(b);
        if (!this.defaults_initialized) {
            String prefix = getPropertyPrefix();
            this.minimumWidth = UIManager.getInt(prefix + "minimumWidth");
            this.iconTextGap = FlatUIUtils.getUIInt(prefix + "iconTextGap", 4);
            this.background = UIManager.getColor(prefix + "background");
            this.foreground = UIManager.getColor(prefix + "foreground");
            this.startBackground = UIManager.getColor(prefix + "startBackground");
            this.endBackground = UIManager.getColor(prefix + "endBackground");
            this.focusedBackground = UIManager.getColor(prefix + "focusedBackground");
            this.focusedForeground = UIManager.getColor(prefix + "focusedForeground");
            this.hoverBackground = UIManager.getColor(prefix + "hoverBackground");
            this.hoverForeground = UIManager.getColor(prefix + "hoverForeground");
            this.pressedBackground = UIManager.getColor(prefix + "pressedBackground");
            this.pressedForeground = UIManager.getColor(prefix + "pressedForeground");
            this.selectedBackground = UIManager.getColor(prefix + "selectedBackground");
            this.selectedForeground = UIManager.getColor(prefix + "selectedForeground");
            this.disabledBackground = UIManager.getColor(prefix + "disabledBackground");
            this.disabledText = UIManager.getColor(prefix + "disabledText");
            this.disabledSelectedBackground = UIManager.getColor(prefix + "disabledSelectedBackground");
            this.disabledSelectedForeground = UIManager.getColor(prefix + "disabledSelectedForeground");
            this.defaultBackground = FlatUIUtils.getUIColor("Button.default.startBackground", "Button.default.background");
            this.defaultEndBackground = UIManager.getColor("Button.default.endBackground");
            this.defaultForeground = UIManager.getColor("Button.default.foreground");
            this.defaultFocusedBackground = UIManager.getColor("Button.default.focusedBackground");
            this.defaultFocusedForeground = UIManager.getColor("Button.default.focusedForeground");
            this.defaultHoverBackground = UIManager.getColor("Button.default.hoverBackground");
            this.defaultHoverForeground = UIManager.getColor("Button.default.hoverForeground");
            this.defaultPressedBackground = UIManager.getColor("Button.default.pressedBackground");
            this.defaultPressedForeground = UIManager.getColor("Button.default.pressedForeground");
            this.defaultBoldText = UIManager.getBoolean("Button.default.boldText");
            this.paintShadow = UIManager.getBoolean("Button.paintShadow");
            this.shadowWidth = FlatUIUtils.getUIInt("Button.shadowWidth", 2);
            this.shadowColor = UIManager.getColor("Button.shadowColor");
            this.defaultShadowColor = UIManager.getColor("Button.default.shadowColor");
            this.toolbarHoverBackground = UIManager.getColor(prefix + "toolbar.hoverBackground");
            this.toolbarHoverForeground = UIManager.getColor(prefix + "toolbar.hoverForeground");
            this.toolbarPressedBackground = UIManager.getColor(prefix + "toolbar.pressedBackground");
            this.toolbarPressedForeground = UIManager.getColor(prefix + "toolbar.pressedForeground");
            this.toolbarSelectedBackground = UIManager.getColor(prefix + "toolbar.selectedBackground");
            this.toolbarSelectedForeground = UIManager.getColor(prefix + "toolbar.selectedForeground");
            this.toolbarDisabledSelectedBackground = UIManager.getColor(prefix + "toolbar.disabledSelectedBackground");
            this.toolbarDisabledSelectedForeground = UIManager.getColor(prefix + "toolbar.disabledSelectedForeground");
            this.helpButtonIcon = UIManager.getIcon("HelpButton.icon");
            this.defaultMargin = UIManager.getInsets(prefix + "margin");
            this.helpButtonIconShared = true;
            this.defaults_initialized = true;
        }
        if (this.startBackground != null && ((bg = b.getBackground()) == null || (bg instanceof UIResource))) {
            b.setBackground(this.startBackground);
        }
        LookAndFeel.installProperty(b, "opaque", false);
        LookAndFeel.installProperty(b, "iconTextGap", Integer.valueOf(UIScale.scale(this.iconTextGap)));
        MigLayoutVisualPadding.install(b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void uninstallDefaults(AbstractButton b) {
        super.uninstallDefaults(b);
        this.oldStyleValues = null;
        this.borderShared = null;
        MigLayoutVisualPadding.uninstall(b);
        this.defaults_initialized = false;
    }

    protected BasicButtonListener createButtonListener(AbstractButton b) {
        return new FlatButtonListener(b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void propertyChange(AbstractButton b, PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();
        boolean z = -1;
        switch (propertyName.hashCode()) {
            case -1302441837:
                if (propertyName.equals(FlatClientProperties.MINIMUM_WIDTH)) {
                    z = 2;
                    break;
                }
                break;
            case -1134471216:
                if (propertyName.equals(FlatClientProperties.SQUARE_SIZE)) {
                    z = true;
                    break;
                }
                break;
            case -691370713:
                if (propertyName.equals(FlatClientProperties.OUTLINE)) {
                    z = 5;
                    break;
                }
                break;
            case 3213227:
                if (propertyName.equals("html")) {
                    z = false;
                    break;
                }
                break;
            case 1030195901:
                if (propertyName.equals(FlatClientProperties.STYLE_CLASS)) {
                    z = 7;
                    break;
                }
                break;
            case 1428734622:
                if (propertyName.equals(FlatClientProperties.BUTTON_TYPE)) {
                    z = 4;
                    break;
                }
                break;
            case 1545413499:
                if (propertyName.equals(FlatClientProperties.STYLE)) {
                    z = 6;
                    break;
                }
                break;
            case 2140981242:
                if (propertyName.equals(FlatClientProperties.MINIMUM_HEIGHT)) {
                    z = 3;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                FlatHTML.updateRendererCSSFontBaseSize(b);
                return;
            case true:
            case true:
            case true:
                b.revalidate();
                return;
            case true:
                b.revalidate();
                HiDPIUtils.repaint(b);
                return;
            case true:
                HiDPIUtils.repaint(b);
                return;
            case true:
            case true:
                if (this.shared && FlatStylingSupport.hasStyleProperty(b)) {
                    b.updateUI();
                } else {
                    installStyle(b);
                }
                b.revalidate();
                HiDPIUtils.repaint(b);
                return;
            default:
                return;
        }
    }

    protected void installStyle(AbstractButton b) {
        try {
            applyStyle(b, FlatStylingSupport.getResolvedStyle(b, getStyleType()));
        } catch (RuntimeException ex) {
            LoggingFacade.INSTANCE.logSevere(null, ex);
        }
    }

    String getStyleType() {
        return "Button";
    }

    protected void applyStyle(AbstractButton b, Object style) {
        this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, (key, value) -> {
            return applyStyleProperty(b, key, value);
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object applyStyleProperty(AbstractButton b, String key, Object value) {
        if (key.startsWith("help.")) {
            if (!(this.helpButtonIcon instanceof FlatHelpButtonIcon)) {
                return new FlatStylingSupport.UnknownStyleException(key);
            }
            if (this.helpButtonIconShared) {
                this.helpButtonIcon = FlatStylingSupport.cloneIcon(this.helpButtonIcon);
                this.helpButtonIconShared = false;
            }
            return ((FlatHelpButtonIcon) this.helpButtonIcon).applyStyleProperty(key.substring("help.".length()), value);
        }
        if ("iconTextGap".equals(key) && (value instanceof Integer)) {
            value = Integer.valueOf(UIScale.scale(((Integer) value).intValue()));
        }
        if (this.borderShared == null) {
            this.borderShared = new AtomicBoolean(true);
        }
        return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, b, this.borderShared);
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableUI
    public Map<String, Class<?>> getStyleableInfos(JComponent c) {
        Map<String, Class<?>> infos = FlatStylingSupport.getAnnotatedStyleableInfos(this, c.getBorder());
        if (this.helpButtonIcon instanceof FlatHelpButtonIcon) {
            FlatStylingSupport.putAllPrefixKey(infos, "help.", ((FlatHelpButtonIcon) this.helpButtonIcon).getStyleableInfos());
        }
        return infos;
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableUI
    public Object getStyleableValue(JComponent c, String key) {
        if (key.startsWith("help.")) {
            if (this.helpButtonIcon instanceof FlatHelpButtonIcon) {
                return ((FlatHelpButtonIcon) this.helpButtonIcon).getStyleableValue(key.substring("help.".length()));
            }
            return null;
        }
        return FlatStylingSupport.getAnnotatedStyleableValue(this, c.getBorder(), key);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isContentAreaFilled(Component c) {
        return !(c instanceof AbstractButton) || ((AbstractButton) c).isContentAreaFilled();
    }

    public static boolean isFocusPainted(Component c) {
        return !(c instanceof AbstractButton) || ((AbstractButton) c).isFocusPainted();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isDefaultButton(Component c) {
        return (c instanceof JButton) && ((JButton) c).isDefaultButton();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isIconOnlyOrSingleCharacterButton(Component c) {
        if (!(c instanceof JButton) && !(c instanceof JToggleButton)) {
            return false;
        }
        Icon icon = ((AbstractButton) c).getIcon();
        String text = ((AbstractButton) c).getText();
        return (icon != null && (text == null || text.isEmpty())) || (icon == null && text != null && ("...".equals(text) || text.length() == 1 || (text.length() == 2 && Character.isSurrogatePair(text.charAt(0), text.charAt(1)))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getButtonType(Component c) {
        String value;
        if (!(c instanceof AbstractButton) || (value = getButtonTypeStr((AbstractButton) c)) == null) {
            return -1;
        }
        boolean z = -1;
        switch (value.hashCode()) {
            case -894674659:
                if (value.equals(FlatClientProperties.BUTTON_TYPE_SQUARE)) {
                    z = false;
                    break;
                }
                break;
            case -5109614:
                if (value.equals(FlatClientProperties.BUTTON_TYPE_ROUND_RECT)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return 0;
            case true:
                return 1;
            default:
                return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isHelpButton(Component c) {
        return (c instanceof JButton) && FlatClientProperties.BUTTON_TYPE_HELP.equals(getButtonTypeStr((JButton) c));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isToolBarButton(Component c) {
        return (c.getParent() instanceof JToolBar) || ((c instanceof AbstractButton) && FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON.equals(getButtonTypeStr((AbstractButton) c)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isBorderlessButton(Component c) {
        return (c instanceof AbstractButton) && FlatClientProperties.BUTTON_TYPE_BORDERLESS.equals(getButtonTypeStr((AbstractButton) c));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getButtonTypeStr(AbstractButton c) {
        Object value = c.getClientProperty(FlatClientProperties.BUTTON_TYPE);
        if (value instanceof String) {
            return (String) value;
        }
        FlatButtonUI ui = c.getUI();
        if (ui instanceof FlatButtonUI) {
            return ui.buttonType;
        }
        return null;
    }

    public void update(Graphics g, JComponent c) {
        if (c.isOpaque()) {
            FlatUIUtils.paintParentBackground(g, c);
        }
        if (isHelpButton(c)) {
            this.helpButtonIcon.paintIcon(c, g, 0, 0);
            return;
        }
        if (isContentAreaFilled(c)) {
            paintBackground(g, c);
        }
        paint(g, c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void paintBackground(Graphics g, JComponent c) {
        JTextField textField;
        Color background = getBackground(c);
        if (background == null) {
            return;
        }
        Graphics2D g2 = g.create();
        try {
            FlatUIUtils.setRenderingHints(g2);
            boolean def = isDefaultButton(c);
            boolean isToolBarButton = isToolBarButton(c);
            float focusWidth = isToolBarButton ? 0.0f : FlatUIUtils.getBorderFocusWidth(c);
            float arc = FlatUIUtils.getBorderArc(c);
            float textFieldArc = 0.0f;
            if (isToolBarButton && ((String) FlatClientProperties.clientProperty(c, FlatClientProperties.STYLE_CLASS, "", String.class)).contains("inTextField") && (textField = SwingUtilities.getAncestorOfClass(JTextField.class, c)) != null) {
                textFieldArc = FlatUIUtils.getBorderArc(textField);
            }
            int x = 0;
            int y = 0;
            int width = c.getWidth();
            int height = c.getHeight();
            if (isToolBarButton && (c.getBorder() instanceof FlatButtonBorder)) {
                Insets spacing = UIScale.scale(c.getBorder().toolbarSpacingInsets);
                x = 0 + spacing.left;
                y = 0 + spacing.top;
                width -= spacing.left + spacing.right;
                height -= spacing.top + spacing.bottom;
                textFieldArc -= spacing.top + spacing.bottom;
            }
            if (arc < textFieldArc) {
                arc = textFieldArc;
            }
            Color shadowColor = def ? this.defaultShadowColor : this.shadowColor;
            if (this.paintShadow && shadowColor != null && this.shadowWidth > 0 && focusWidth > 0.0f && c.isEnabled() && !isToolBarButton && !isBorderlessButton(c) && (!isFocusPainted(c) || !FlatUIUtils.isPermanentFocusOwner(c))) {
                g2.setColor(shadowColor);
                g2.fill(new RoundRectangle2D.Float(focusWidth, focusWidth + UIScale.scale(this.shadowWidth), width - (focusWidth * 2.0f), height - (focusWidth * 2.0f), arc, arc));
            }
            Color startBg = def ? this.defaultBackground : this.startBackground;
            Color endBg = def ? this.defaultEndBackground : this.endBackground;
            if (background == startBg && endBg != null && !startBg.equals(endBg)) {
                g2.setPaint(new GradientPaint(0.0f, 0.0f, startBg, 0.0f, height, endBg));
            } else {
                g2.setColor(FlatUIUtils.deriveColor(background, getBackgroundBase(c, def)));
            }
            FlatUIUtils.paintComponentBackground(g2, x, y, width, height, focusWidth, arc);
            g2.dispose();
        } catch (Throwable th) {
            g2.dispose();
            throw th;
        }
    }

    public void paint(Graphics g, JComponent c) {
        Graphics g2 = FlatLabelUI.createGraphicsHTMLTextYCorrection(g, c);
        AbstractButton b = (AbstractButton) c;
        String clippedText = layout(b, b.getFontMetrics(b.getFont()), b.getWidth(), b.getHeight());
        clearTextShiftOffset();
        ButtonModel model = b.getModel();
        if (model.isArmed() && model.isPressed()) {
            paintButtonPressed(g2, b);
        }
        if (b.getIcon() != null) {
            paintIcon(g2, b, iconR);
        }
        if (clippedText != null && !clippedText.isEmpty()) {
            View view = (View) b.getClientProperty("html");
            if (view != null) {
                if (b.isEnabled()) {
                    FlatHTML.updateRendererCSSForeground(view, getForeground(b));
                }
                view.paint(g2, textR);
            } else {
                paintText(g2, b, textR, clippedText);
            }
        }
        if (b.isFocusPainted() && b.hasFocus()) {
            paintFocus(g2, b, viewR, textR, iconR);
        }
    }

    protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
        int xOffset = defaultBoldPlainWidthDiff(c) / 2;
        if (xOffset > 0) {
            boolean ltr = c.getComponentOrientation().isLeftToRight();
            switch (((AbstractButton) c).getHorizontalTextPosition()) {
                case 2:
                    iconRect.x += xOffset;
                    break;
                case 4:
                    iconRect.x -= xOffset;
                    break;
                case 10:
                    iconRect.x += ltr ? xOffset : -xOffset;
                    break;
                case 11:
                    iconRect.x -= ltr ? xOffset : -xOffset;
                    break;
            }
        }
        super.paintIcon(g, c, iconRect);
    }

    protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
        if (isHelpButton(b)) {
            return;
        }
        if (this.defaultBoldText && isDefaultButton(b) && (b.getFont() instanceof UIResource)) {
            Font boldFont = g.getFont().deriveFont(1);
            g.setFont(boldFont);
            int boldWidth = b.getFontMetrics(boldFont).stringWidth(text);
            if (boldWidth > textRect.width) {
                textRect.x -= (boldWidth - textRect.width) / 2;
                textRect.width = boldWidth;
            }
        }
        paintText(g, b, textRect, text, getForeground(b));
    }

    public static void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text, Color foreground) {
        FontMetrics fm = b.getFontMetrics(b.getFont());
        int mnemonicIndex = FlatLaf.isShowMnemonics() ? b.getDisplayedMnemonicIndex() : -1;
        g.setColor(foreground);
        FlatUIUtils.drawStringUnderlineCharAt(b, g, text, mnemonicIndex, textRect.x, textRect.y + fm.getAscent());
    }

    protected Color getBackground(JComponent c) {
        Color color;
        boolean toolBarButton = isToolBarButton(c) || isBorderlessButton(c);
        if (((AbstractButton) c).isSelected()) {
            Color color2 = toolBarButton ? this.toolbarSelectedBackground : this.selectedBackground;
            if (toolBarButton) {
                color = this.toolbarDisabledSelectedBackground != null ? this.toolbarDisabledSelectedBackground : this.toolbarSelectedBackground;
            } else {
                color = this.disabledSelectedBackground;
            }
            return buttonStateColor(c, color2, color, null, null, toolBarButton ? this.toolbarPressedBackground : this.pressedBackground);
        }
        if (toolBarButton) {
            Color bg = c.getBackground();
            return buttonStateColor(c, isCustomBackground(bg) ? bg : null, null, null, this.toolbarHoverBackground, this.toolbarPressedBackground);
        }
        boolean def = isDefaultButton(c);
        return buttonStateColor(c, getBackgroundBase(c, def), this.disabledBackground, isCustomBackground(c.getBackground()) ? null : def ? this.defaultFocusedBackground : this.focusedBackground, def ? this.defaultHoverBackground : this.hoverBackground, def ? this.defaultPressedBackground : this.pressedBackground);
    }

    protected Color getBackgroundBase(JComponent c, boolean def) {
        if (FlatUIUtils.isAWTPeer(c)) {
            return this.background;
        }
        Color bg = c.getBackground();
        if (isCustomBackground(bg)) {
            return bg;
        }
        return def ? this.defaultBackground : bg;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isCustomBackground(Color bg) {
        return bg != this.background && (this.startBackground == null || bg != this.startBackground);
    }

    public static Color buttonStateColor(Component c, Color enabledColor, Color disabledColor, Color focusedColor, Color hoverColor, Color pressedColor) {
        if (c == null) {
            return enabledColor;
        }
        if (!c.isEnabled()) {
            return disabledColor;
        }
        if (c instanceof AbstractButton) {
            ButtonModel model = ((AbstractButton) c).getModel();
            if (pressedColor != null && model.isPressed()) {
                return pressedColor;
            }
            if (hoverColor != null && model.isRollover()) {
                return hoverColor;
            }
        }
        if (focusedColor != null && isFocusPainted(c) && FlatUIUtils.isPermanentFocusOwner(c)) {
            return focusedColor;
        }
        return enabledColor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Color getForeground(JComponent c) {
        Color color;
        Color color2;
        Color fg = c.getForeground();
        boolean toolBarButton = isToolBarButton(c) || isBorderlessButton(c);
        if (((AbstractButton) c).isSelected()) {
            if (toolBarButton) {
                color = this.toolbarSelectedForeground != null ? this.toolbarSelectedForeground : fg;
            } else {
                color = isCustomForeground(fg) ? fg : this.selectedForeground;
            }
            if (toolBarButton) {
                color2 = this.toolbarDisabledSelectedForeground != null ? this.toolbarDisabledSelectedForeground : this.disabledText;
            } else {
                color2 = this.disabledSelectedForeground != null ? this.disabledSelectedForeground : this.disabledText;
            }
            return buttonStateColor(c, color, color2, null, null, toolBarButton ? this.toolbarPressedForeground : this.pressedForeground);
        }
        if (toolBarButton) {
            return buttonStateColor(c, fg, this.disabledText, null, this.toolbarHoverForeground, this.toolbarPressedForeground);
        }
        boolean def = isDefaultButton(c);
        return buttonStateColor(c, getForegroundBase(c, def), this.disabledText, isCustomForeground(fg) ? null : def ? this.defaultFocusedForeground : this.focusedForeground, def ? this.defaultHoverForeground : this.hoverForeground, def ? this.defaultPressedForeground : this.pressedForeground);
    }

    protected Color getForegroundBase(JComponent c, boolean def) {
        Color fg = c.getForeground();
        if (isCustomForeground(fg)) {
            return fg;
        }
        return def ? this.defaultForeground : fg;
    }

    protected boolean isCustomForeground(Color fg) {
        return fg != this.foreground;
    }

    public Dimension getPreferredSize(JComponent c) {
        if (isHelpButton(c)) {
            return new Dimension(this.helpButtonIcon.getIconWidth(), this.helpButtonIcon.getIconHeight());
        }
        Dimension prefSize = super.getPreferredSize(c);
        if (prefSize == null) {
            return null;
        }
        prefSize.width += defaultBoldPlainWidthDiff(c);
        boolean isIconOnlyOrSingleCharacter = isIconOnlyOrSingleCharacterButton(c);
        if (FlatClientProperties.clientPropertyBoolean(c, FlatClientProperties.SQUARE_SIZE, this.squareSize)) {
            int max = Math.max(prefSize.width, prefSize.height);
            prefSize.height = max;
            prefSize.width = max;
        } else if (isIconOnlyOrSingleCharacter && ((AbstractButton) c).getIcon() == null) {
            prefSize.width = Math.max(prefSize.width, prefSize.height);
        } else if (!isIconOnlyOrSingleCharacter && !isToolBarButton(c) && (c.getBorder() instanceof FlatButtonBorder) && hasDefaultMargins(c)) {
            int fw = Math.round(FlatUIUtils.getBorderFocusWidth(c) * 2.0f);
            prefSize.width = Math.max(prefSize.width, UIScale.scale(FlatUIUtils.minimumWidth(c, this.minimumWidth)) + fw);
            prefSize.height = Math.max(prefSize.height, UIScale.scale(FlatUIUtils.minimumHeight(c, this.minimumHeight)) + fw);
        }
        return prefSize;
    }

    private int defaultBoldPlainWidthDiff(JComponent c) {
        String text;
        if (!this.defaultBoldText || !isDefaultButton(c) || !(c.getFont() instanceof UIResource) || (text = ((AbstractButton) c).getText()) == null || text.isEmpty()) {
            return 0;
        }
        Font font = c.getFont();
        Font boldFont = font.deriveFont(1);
        int boldWidth = c.getFontMetrics(boldFont).stringWidth(text);
        int plainWidth = c.getFontMetrics(font).stringWidth(text);
        if (boldWidth > plainWidth) {
            return boldWidth - plainWidth;
        }
        return 0;
    }

    private boolean hasDefaultMargins(JComponent c) {
        Insets margin = ((AbstractButton) c).getMargin();
        return (margin instanceof UIResource) && Objects.equals(margin, this.defaultMargin);
    }

    public int getBaseline(JComponent c, int width, int height) {
        return getBaselineImpl(c, width, height);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getBaselineImpl(JComponent c, int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException();
        }
        AbstractButton b = (AbstractButton) c;
        String text = b.getText();
        if (text == null || text.isEmpty()) {
            return -1;
        }
        FontMetrics fm = b.getFontMetrics(b.getFont());
        layout(b, fm, width, height);
        View view = (View) b.getClientProperty("html");
        if (view != null) {
            int baseline = BasicHTML.getHTMLBaseline(view, textR.width, textR.height);
            return baseline >= 0 ? textR.y + baseline : baseline;
        }
        return textR.y + fm.getAscent();
    }

    private static String layout(AbstractButton b, FontMetrics fm, int width, int height) {
        Insets insets = b.getInsets();
        viewR.setBounds(insets.left, insets.top, (width - insets.left) - insets.right, (height - insets.top) - insets.bottom);
        textR.setBounds(0, 0, 0, 0);
        iconR.setBounds(0, 0, 0, 0);
        String text = b.getText();
        return SwingUtilities.layoutCompoundLabel(b, fm, text, b.getIcon(), b.getVerticalAlignment(), b.getHorizontalAlignment(), b.getVerticalTextPosition(), b.getHorizontalTextPosition(), viewR, iconR, textR, text != null ? b.getIconTextGap() : 0);
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatButtonUI$FlatButtonListener.class */
    protected class FlatButtonListener extends BasicButtonListener {
        private final AbstractButton b;

        protected FlatButtonListener(AbstractButton b) {
            super(b);
            this.b = b;
        }

        public void propertyChange(PropertyChangeEvent e) {
            super.propertyChange(e);
            FlatButtonUI.this.propertyChange(this.b, e);
        }

        public void stateChanged(ChangeEvent e) {
            HiDPIUtils.repaint(this.b);
            AbstractButton b = (AbstractButton) e.getSource();
            JToolBar parent = b.getParent();
            if (parent instanceof JToolBar) {
                JToolBar toolBar = parent;
                FlatToolBarUI ui = toolBar.getUI();
                if (ui instanceof FlatToolBarUI) {
                    ui.repaintButtonGroup(b);
                }
            }
        }

        public void focusGained(FocusEvent e) {
            super.focusGained(e);
            HiDPIUtils.repaint(this.b);
        }

        public void focusLost(FocusEvent e) {
            super.focusLost(e);
            HiDPIUtils.repaint(this.b);
        }
    }
}
