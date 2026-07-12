package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.JavaCompatibility;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTextFieldUI.class */
public class FlatTextFieldUI extends BasicTextFieldUI implements FlatStylingSupport.StyleableUI {

    @FlatStylingSupport.Styleable
    protected int minimumWidth;
    private Color background;

    @FlatStylingSupport.Styleable
    protected Color disabledBackground;

    @FlatStylingSupport.Styleable
    protected Color inactiveBackground;

    @FlatStylingSupport.Styleable
    protected Color placeholderForeground;

    @FlatStylingSupport.Styleable
    protected Color focusedBackground;

    @FlatStylingSupport.Styleable
    protected int iconTextGap;

    @FlatStylingSupport.Styleable
    protected Icon leadingIcon;

    @FlatStylingSupport.Styleable
    protected Icon trailingIcon;
    protected JComponent leadingComponent;
    protected JComponent trailingComponent;
    protected JComponent clearButton;

    @FlatStylingSupport.Styleable
    protected boolean showClearButton;
    private Color oldDisabledBackground;
    private Color oldInactiveBackground;
    private Insets defaultMargin;
    private FocusListener focusListener;
    private DocumentListener documentListener;
    private Map<String, Object> oldStyleValues;
    private AtomicBoolean borderShared;

    public static ComponentUI createUI(JComponent c) {
        return new FlatTextFieldUI();
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
        this.leadingIcon = (Icon) FlatClientProperties.clientProperty(c, FlatClientProperties.TEXT_FIELD_LEADING_ICON, null, Icon.class);
        this.trailingIcon = (Icon) FlatClientProperties.clientProperty(c, FlatClientProperties.TEXT_FIELD_TRAILING_ICON, null, Icon.class);
        installLeadingComponent();
        installTrailingComponent();
        installClearButton();
        installStyle();
    }

    public void uninstallUI(JComponent c) {
        uninstallLeadingComponent();
        uninstallTrailingComponent();
        uninstallClearButton();
        super.uninstallUI(c);
        this.leadingIcon = null;
        this.trailingIcon = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void installDefaults() {
        super.installDefaults();
        String prefix = getPropertyPrefix();
        this.minimumWidth = UIManager.getInt("Component.minimumWidth");
        this.background = UIManager.getColor(prefix + ".background");
        this.disabledBackground = UIManager.getColor(prefix + ".disabledBackground");
        this.inactiveBackground = UIManager.getColor(prefix + ".inactiveBackground");
        this.placeholderForeground = UIManager.getColor(prefix + ".placeholderForeground");
        this.focusedBackground = UIManager.getColor(prefix + ".focusedBackground");
        this.iconTextGap = FlatUIUtils.getUIInt(prefix + ".iconTextGap", 4);
        this.defaultMargin = UIManager.getInsets(prefix + ".margin");
        LookAndFeel.installProperty(getComponent(), "opaque", false);
        MigLayoutVisualPadding.install(getComponent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void uninstallDefaults() {
        super.uninstallDefaults();
        this.background = null;
        this.disabledBackground = null;
        this.inactiveBackground = null;
        this.placeholderForeground = null;
        this.focusedBackground = null;
        this.oldDisabledBackground = null;
        this.oldInactiveBackground = null;
        this.oldStyleValues = null;
        this.borderShared = null;
        MigLayoutVisualPadding.uninstall(getComponent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void installListeners() {
        super.installListeners();
        this.focusListener = new FlatUIUtils.RepaintFocusListener(getComponent(), null);
        getComponent().addFocusListener(this.focusListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void uninstallListeners() {
        super.uninstallListeners();
        getComponent().removeFocusListener(this.focusListener);
        this.focusListener = null;
        if (this.documentListener != null) {
            getComponent().getDocument().removeDocumentListener(this.documentListener);
            this.documentListener = null;
        }
    }

    protected Caret createCaret() {
        return new FlatCaret(UIManager.getString("TextComponent.selectAllOnFocusPolicy"), UIManager.getBoolean("TextComponent.selectAllOnMouseClick"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();
        if ("editable".equals(propertyName) || "enabled".equals(propertyName)) {
            updateBackground();
        } else {
            super.propertyChange(e);
        }
        JTextComponent c = getComponent();
        String propertyName2 = e.getPropertyName();
        boolean z = -1;
        switch (propertyName2.hashCode()) {
            case -1750549472:
                if (propertyName2.equals(FlatClientProperties.TEXT_FIELD_TRAILING_ICON)) {
                    z = 8;
                    break;
                }
                break;
            case -1609594047:
                if (propertyName2.equals("enabled")) {
                    z = 12;
                    break;
                }
                break;
            case -1498561705:
                if (propertyName2.equals(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON)) {
                    z = 11;
                    break;
                }
                break;
            case -1302441837:
                if (propertyName2.equals(FlatClientProperties.MINIMUM_WIDTH)) {
                    z = 4;
                    break;
                }
                break;
            case -742334409:
                if (propertyName2.equals(FlatClientProperties.COMPONENT_ROUND_RECT)) {
                    z = true;
                    break;
                }
                break;
            case -691370713:
                if (propertyName2.equals(FlatClientProperties.OUTLINE)) {
                    z = 2;
                    break;
                }
                break;
            case -68661834:
                if (propertyName2.equals(FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT)) {
                    z = 10;
                    break;
                }
                break;
            case 151255029:
                if (propertyName2.equals(FlatClientProperties.PLACEHOLDER_TEXT)) {
                    z = false;
                    break;
                }
                break;
            case 645363156:
                if (propertyName2.equals(FlatClientProperties.TEXT_FIELD_LEADING_ICON)) {
                    z = 7;
                    break;
                }
                break;
            case 861720859:
                if (propertyName2.equals(Constants.ATTRVALUE_DOCUMENT)) {
                    z = 14;
                    break;
                }
                break;
            case 1030195901:
                if (propertyName2.equals(FlatClientProperties.STYLE_CLASS)) {
                    z = 6;
                    break;
                }
                break;
            case 1373006790:
                if (propertyName2.equals(FlatClientProperties.TEXT_FIELD_PADDING)) {
                    z = 3;
                    break;
                }
                break;
            case 1545413499:
                if (propertyName2.equals(FlatClientProperties.STYLE)) {
                    z = 5;
                    break;
                }
                break;
            case 1602416228:
                if (propertyName2.equals("editable")) {
                    z = 13;
                    break;
                }
                break;
            case 1636664450:
                if (propertyName2.equals(FlatClientProperties.TEXT_FIELD_LEADING_COMPONENT)) {
                    z = 9;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
            case true:
            case true:
                HiDPIUtils.repaint(c);
                return;
            case true:
                c.revalidate();
                return;
            case true:
            case true:
                installStyle();
                c.revalidate();
                HiDPIUtils.repaint(c);
                return;
            case true:
                this.leadingIcon = e.getNewValue() instanceof Icon ? (Icon) e.getNewValue() : null;
                HiDPIUtils.repaint(c);
                return;
            case true:
                this.trailingIcon = e.getNewValue() instanceof Icon ? (Icon) e.getNewValue() : null;
                HiDPIUtils.repaint(c);
                return;
            case true:
                uninstallLeadingComponent();
                installLeadingComponent();
                c.revalidate();
                HiDPIUtils.repaint(c);
                return;
            case true:
                uninstallTrailingComponent();
                installTrailingComponent();
                c.revalidate();
                HiDPIUtils.repaint(c);
                return;
            case true:
                uninstallClearButton();
                installClearButton();
                c.revalidate();
                HiDPIUtils.repaint(c);
                return;
            case true:
            case true:
                updateClearButton();
                return;
            case true:
                if (this.documentListener != null) {
                    if (e.getOldValue() instanceof Document) {
                        ((Document) e.getOldValue()).removeDocumentListener(this.documentListener);
                    }
                    if (e.getNewValue() instanceof Document) {
                        ((Document) e.getNewValue()).addDocumentListener(this.documentListener);
                    }
                    updateClearButton();
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void installDocumentListener() {
        if (this.documentListener != null) {
            return;
        }
        this.documentListener = new FlatDocumentListener();
        getComponent().getDocument().addDocumentListener(this.documentListener);
    }

    protected void documentChanged(DocumentEvent e) {
        if (this.clearButton != null) {
            updateClearButton();
        }
    }

    protected void installStyle() {
        try {
            applyStyle(FlatStylingSupport.getResolvedStyle(getComponent(), getStyleType()));
        } catch (RuntimeException ex) {
            LoggingFacade.INSTANCE.logSevere(null, ex);
        }
    }

    String getStyleType() {
        return "TextField";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void applyStyle(Object style) {
        this.oldDisabledBackground = this.disabledBackground;
        this.oldInactiveBackground = this.inactiveBackground;
        boolean oldShowClearButton = this.showClearButton;
        this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
        updateBackground();
        if (this.showClearButton != oldShowClearButton) {
            uninstallClearButton();
            installClearButton();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object applyStyleProperty(String key, Object value) {
        if (this.borderShared == null) {
            this.borderShared = new AtomicBoolean(true);
        }
        return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, getComponent(), this.borderShared);
    }

    public Map<String, Class<?>> getStyleableInfos(JComponent c) {
        return FlatStylingSupport.getAnnotatedStyleableInfos(this, getComponent().getBorder());
    }

    public Object getStyleableValue(JComponent c, String key) {
        return FlatStylingSupport.getAnnotatedStyleableValue(this, getComponent().getBorder(), key);
    }

    private void updateBackground() {
        updateBackground(getComponent(), this.background, this.disabledBackground, this.inactiveBackground, this.oldDisabledBackground, this.oldInactiveBackground);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateBackground(JTextComponent c, Color background, Color disabledBackground, Color inactiveBackground, Color oldDisabledBackground, Color oldInactiveBackground) {
        Color color;
        Color oldBackground = c.getBackground();
        if (!(oldBackground instanceof UIResource)) {
            return;
        }
        if (oldBackground != background && oldBackground != disabledBackground && oldBackground != inactiveBackground && oldBackground != oldDisabledBackground && oldBackground != oldInactiveBackground) {
            return;
        }
        if (!c.isEnabled()) {
            color = disabledBackground;
        } else if (!c.isEditable()) {
            color = inactiveBackground;
        } else {
            color = background;
        }
        Color newBackground = color;
        if (newBackground != oldBackground) {
            c.setBackground(newBackground);
        }
    }

    protected void paintSafely(Graphics g) {
        paintBackground(g, getComponent(), this.focusedBackground);
        paintPlaceholder(g);
        if (hasLeadingIcon() || hasTrailingIcon()) {
            paintIcons(g, new Rectangle(getIconsRect()));
        }
        super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D) g));
    }

    protected void paintBackground(Graphics g) {
    }

    static void paintBackground(Graphics g, JTextComponent c, Color focusedBackground) {
        if (!c.isOpaque() && FlatUIUtils.getOutsideFlatBorder(c) == null && FlatUIUtils.hasOpaqueBeenExplicitlySet(c)) {
            return;
        }
        float focusWidth = FlatUIUtils.getBorderFocusWidth(c);
        float arc = FlatUIUtils.getBorderArc(c);
        if (c.isOpaque() && (focusWidth > 0.0f || arc > 0.0f)) {
            FlatUIUtils.paintParentBackground(g, c);
        }
        Graphics2D g2 = g.create();
        try {
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(getBackground(c, focusedBackground));
            FlatUIUtils.paintComponentBackground(g2, 0, 0, c.getWidth(), c.getHeight(), focusWidth, arc);
            g2.dispose();
        } catch (Throwable th) {
            g2.dispose();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Color getBackground(JTextComponent c, Color focusedBackground) {
        Color background = c.getBackground();
        if (!(background instanceof UIResource)) {
            return background;
        }
        if (focusedBackground != null && FlatUIUtils.isPermanentFocusOwner(c)) {
            return focusedBackground;
        }
        return background;
    }

    protected void paintPlaceholder(Graphics g) {
        JComboBox component = getComponent();
        if (component.getDocument().getLength() > 0) {
            return;
        }
        JComboBox parent = component.getParent();
        String placeholder = (String) FlatClientProperties.clientProperty(parent instanceof JComboBox ? parent : component, FlatClientProperties.PLACEHOLDER_TEXT, null, String.class);
        if (placeholder == null) {
            return;
        }
        Rectangle r = getVisibleEditorRect();
        FontMetrics fm = component.getFontMetrics(component.getFont());
        int x = r.x;
        int y = r.y + fm.getAscent() + ((r.height - fm.getHeight()) / 2);
        String clippedPlaceholder = JavaCompatibility.getClippedString(component, fm, placeholder, r.width);
        int stringWidth = fm.stringWidth(clippedPlaceholder);
        int halign = component instanceof JTextField ? ((JTextField) component).getHorizontalAlignment() : 10;
        if (halign == 10) {
            halign = isLeftToRight() ? 2 : 4;
        } else if (halign == 11) {
            halign = isLeftToRight() ? 4 : 2;
        }
        if (halign == 4) {
            x += r.width - stringWidth;
        } else if (halign == 0) {
            x = Math.max(0, (x + (r.width / 2)) - (stringWidth / 2));
        }
        g.setColor(this.placeholderForeground);
        FlatUIUtils.drawString(component, g, clippedPlaceholder, x, y);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void paintIcons(Graphics g, Rectangle r) {
        boolean ltr = isLeftToRight();
        Icon leftIcon = ltr ? this.leadingIcon : this.trailingIcon;
        Icon rightIcon = ltr ? this.trailingIcon : this.leadingIcon;
        if (leftIcon != null) {
            int x = r.x;
            int y = r.y + Math.round((r.height - leftIcon.getIconHeight()) / 2.0f);
            leftIcon.paintIcon(getComponent(), g, x, y);
            int w = leftIcon.getIconWidth() + UIScale.scale(this.iconTextGap);
            r.x += w;
            r.width -= w;
        }
        if (rightIcon != null) {
            int iconWidth = rightIcon.getIconWidth();
            int x2 = (r.x + r.width) - iconWidth;
            int y2 = r.y + Math.round((r.height - rightIcon.getIconHeight()) / 2.0f);
            rightIcon.paintIcon(getComponent(), g, x2, y2);
            r.width -= iconWidth + UIScale.scale(this.iconTextGap);
        }
    }

    public Dimension getPreferredSize(JComponent c) {
        return applyMinimumWidth(c, applyExtraSize(super.getPreferredSize(c)), this.minimumWidth);
    }

    public Dimension getMinimumSize(JComponent c) {
        return applyMinimumWidth(c, applyExtraSize(super.getMinimumSize(c)), this.minimumWidth);
    }

    private Dimension applyExtraSize(Dimension size) {
        size.width += getLeadingIconWidth() + getTrailingIconWidth();
        for (JComponent comp : getLeadingComponents()) {
            if (comp != null && comp.isVisible()) {
                size.width += comp.getPreferredSize().width;
            }
        }
        for (JComponent comp2 : getTrailingComponents()) {
            if (comp2 != null && comp2.isVisible()) {
                size.width += comp2.getPreferredSize().width;
            }
        }
        return size;
    }

    private Dimension applyMinimumWidth(JComponent c, Dimension size, int minimumWidth) {
        if ((c instanceof JTextField) && ((JTextField) c).getColumns() > 0) {
            return size;
        }
        if (!hasDefaultMargins(c, this.defaultMargin)) {
            return size;
        }
        Container parent = c.getParent();
        if ((parent instanceof JComboBox) || (parent instanceof JSpinner) || (parent != null && (parent.getParent() instanceof JSpinner))) {
            return size;
        }
        int minimumWidth2 = FlatUIUtils.minimumWidth(c, minimumWidth);
        float focusWidth = FlatUIUtils.getBorderFocusWidth(c);
        size.width = Math.max(size.width, UIScale.scale(minimumWidth2) + Math.round(focusWidth * 2.0f));
        return size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasDefaultMargins(JComponent c, Insets defaultMargin) {
        Insets margin = ((JTextComponent) c).getMargin();
        return (margin instanceof UIResource) && Objects.equals(margin, defaultMargin);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Rectangle getVisibleEditorRect() {
        Rectangle r = getIconsRect();
        if (r == null) {
            return null;
        }
        int leading = getLeadingIconWidth();
        int trailing = getTrailingIconWidth();
        if (leading != 0 || trailing != 0) {
            boolean ltr = isLeftToRight();
            int left = ltr ? leading : trailing;
            int right = ltr ? trailing : leading;
            r.x += left;
            r.width -= left + right;
        }
        Insets padding = getPadding();
        if (padding != null) {
            r = FlatUIUtils.subtractInsets(r, padding);
        }
        r.width = Math.max(r.width, 0);
        r.height = Math.max(r.height, 0);
        return r;
    }

    protected Rectangle getIconsRect() {
        Rectangle r = super.getVisibleEditorRect();
        if (r == null) {
            return null;
        }
        boolean ltr = isLeftToRight();
        JComponent[] leftComponents = ltr ? getLeadingComponents() : getTrailingComponents();
        JComponent[] rightComponents = ltr ? getTrailingComponents() : getLeadingComponents();
        boolean leftVisible = false;
        boolean rightVisible = false;
        for (JComponent leftComponent : leftComponents) {
            if (leftComponent != null && leftComponent.isVisible()) {
                int w = leftComponent.getPreferredSize().width;
                r.x += w;
                r.width -= w;
                leftVisible = true;
            }
        }
        for (JComponent rightComponent : rightComponents) {
            if (rightComponent != null && rightComponent.isVisible()) {
                r.width -= rightComponent.getPreferredSize().width;
                rightVisible = true;
            }
        }
        if (leftVisible || (!ltr ? hasTrailingIcon() : hasLeadingIcon())) {
            Insets margin = getComponent().getMargin();
            int newLeftMargin = Math.min(margin.left, margin.top);
            if (newLeftMargin < margin.left) {
                int diff = UIScale.scale(margin.left - newLeftMargin);
                r.x -= diff;
                r.width += diff;
            }
        }
        if (rightVisible || (!ltr ? hasLeadingIcon() : hasTrailingIcon())) {
            Insets margin2 = getComponent().getMargin();
            int newRightMargin = Math.min(margin2.right, margin2.top);
            if (newRightMargin < margin2.left) {
                r.width += UIScale.scale(margin2.right - newRightMargin);
            }
        }
        r.width = Math.max(r.width, 0);
        r.height = Math.max(r.height, 0);
        return r;
    }

    protected boolean hasLeadingIcon() {
        return this.leadingIcon != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasTrailingIcon() {
        return this.trailingIcon != null;
    }

    protected int getLeadingIconWidth() {
        if (this.leadingIcon != null) {
            return this.leadingIcon.getIconWidth() + UIScale.scale(this.iconTextGap);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getTrailingIconWidth() {
        if (this.trailingIcon != null) {
            return this.trailingIcon.getIconWidth() + UIScale.scale(this.iconTextGap);
        }
        return 0;
    }

    boolean isLeftToRight() {
        return getComponent().getComponentOrientation().isLeftToRight();
    }

    protected Insets getPadding() {
        return UIScale.scale((Insets) FlatClientProperties.clientProperty(getComponent(), FlatClientProperties.TEXT_FIELD_PADDING, null, Insets.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void scrollCaretToVisible() {
        FlatCaret caret = getComponent().getCaret();
        if (caret instanceof FlatCaret) {
            caret.scrollCaretToVisible();
        }
    }

    protected void installLeadingComponent() {
        JTextComponent c = getComponent();
        this.leadingComponent = (JComponent) FlatClientProperties.clientProperty(c, FlatClientProperties.TEXT_FIELD_LEADING_COMPONENT, null, JComponent.class);
        if (this.leadingComponent != null) {
            prepareLeadingOrTrailingComponent(this.leadingComponent);
            installLayout();
            c.add(this.leadingComponent);
        }
    }

    protected void installTrailingComponent() {
        JTextComponent c = getComponent();
        this.trailingComponent = (JComponent) FlatClientProperties.clientProperty(c, FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT, null, JComponent.class);
        if (this.trailingComponent != null) {
            prepareLeadingOrTrailingComponent(this.trailingComponent);
            installLayout();
            c.add(this.trailingComponent);
        }
    }

    protected void uninstallLeadingComponent() {
        if (this.leadingComponent != null) {
            getComponent().remove(this.leadingComponent);
            this.leadingComponent = null;
        }
    }

    protected void uninstallTrailingComponent() {
        if (this.trailingComponent != null) {
            getComponent().remove(this.trailingComponent);
            this.trailingComponent = null;
        }
    }

    protected void installClearButton() {
        JTextComponent c = getComponent();
        if (FlatClientProperties.clientPropertyBoolean(c, FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, this.showClearButton)) {
            this.clearButton = createClearButton();
            updateClearButton();
            installDocumentListener();
            installLayout();
            c.add(this.clearButton);
        }
    }

    protected void uninstallClearButton() {
        if (this.clearButton != null) {
            getComponent().remove(this.clearButton);
            this.clearButton = null;
        }
    }

    protected JComponent createClearButton() {
        JButton button = new JButton();
        button.setName("TextField.clearButton");
        button.putClientProperty(FlatClientProperties.STYLE_CLASS, "clearButton");
        button.putClientProperty(FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON);
        button.setCursor(Cursor.getDefaultCursor());
        button.addActionListener(e -> {
            clearButtonClicked();
        });
        return button;
    }

    protected void clearButtonClicked() {
        JTextComponent c = getComponent();
        Object callback = c.getClientProperty(FlatClientProperties.TEXT_FIELD_CLEAR_CALLBACK);
        if (callback instanceof Runnable) {
            ((Runnable) callback).run();
        } else if (callback instanceof Consumer) {
            ((Consumer) callback).accept(c);
        } else {
            c.setText("");
        }
    }

    protected void updateClearButton() {
        if (this.clearButton == null) {
            return;
        }
        JTextComponent c = getComponent();
        boolean visible = c.isEnabled() && c.isEditable() && c.getDocument().getLength() > 0;
        if (visible != this.clearButton.isVisible()) {
            this.clearButton.setVisible(visible);
            c.revalidate();
            HiDPIUtils.repaint(c);
        }
    }

    protected JComponent[] getLeadingComponents() {
        return new JComponent[]{this.leadingComponent};
    }

    protected JComponent[] getTrailingComponents() {
        return new JComponent[]{this.trailingComponent, this.clearButton};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void prepareLeadingOrTrailingComponent(JComponent c) {
        c.putClientProperty(FlatClientProperties.STYLE_CLASS, "inTextField");
        if ((c instanceof JButton) || (c instanceof JToggleButton)) {
            c.putClientProperty(FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON);
            if (!c.isCursorSet()) {
                c.setCursor(Cursor.getDefaultCursor());
                return;
            }
            return;
        }
        if (c instanceof JToolBar) {
            for (JComponent jComponent : c.getComponents()) {
                if (jComponent instanceof JComponent) {
                    jComponent.putClientProperty(FlatClientProperties.STYLE_CLASS, "inTextField");
                }
            }
            if (!c.isCursorSet()) {
                c.setCursor(Cursor.getDefaultCursor());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void installLayout() {
        JTextComponent c = getComponent();
        LayoutManager oldLayout = c.getLayout();
        if (!(oldLayout instanceof FlatTextFieldLayout)) {
            c.setLayout(new FlatTextFieldLayout(oldLayout));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTextFieldUI$FlatTextFieldLayout.class */
    public class FlatTextFieldLayout implements LayoutManager2, UIResource {
        private final LayoutManager delegate;

        FlatTextFieldLayout(LayoutManager delegate) {
            this.delegate = delegate;
        }

        public void addLayoutComponent(String name, Component comp) {
            if (this.delegate != null) {
                this.delegate.addLayoutComponent(name, comp);
            }
        }

        public void removeLayoutComponent(Component comp) {
            if (this.delegate != null) {
                this.delegate.removeLayoutComponent(comp);
            }
        }

        public Dimension preferredLayoutSize(Container parent) {
            if (this.delegate != null) {
                return this.delegate.preferredLayoutSize(parent);
            }
            return null;
        }

        public Dimension minimumLayoutSize(Container parent) {
            if (this.delegate != null) {
                return this.delegate.minimumLayoutSize(parent);
            }
            return null;
        }

        public void layoutContainer(Container parent) {
            if (this.delegate != null) {
                this.delegate.layoutContainer(parent);
            }
            int ow = FlatUIUtils.getBorderFocusAndLineWidth(FlatTextFieldUI.this.getComponent());
            int h = (parent.getHeight() - ow) - ow;
            boolean ltr = FlatTextFieldUI.this.isLeftToRight();
            JComponent[] leftComponents = ltr ? FlatTextFieldUI.this.getLeadingComponents() : FlatTextFieldUI.this.getTrailingComponents();
            JComponent[] rightComponents = ltr ? FlatTextFieldUI.this.getTrailingComponents() : FlatTextFieldUI.this.getLeadingComponents();
            int x = ow;
            for (JComponent leftComponent : leftComponents) {
                if (leftComponent != null && leftComponent.isVisible()) {
                    int cw = leftComponent.getPreferredSize().width;
                    leftComponent.setBounds(x, ow, cw, h);
                    x += cw;
                }
            }
            int x2 = parent.getWidth() - ow;
            for (JComponent rightComponent : rightComponents) {
                if (rightComponent != null && rightComponent.isVisible()) {
                    int cw2 = rightComponent.getPreferredSize().width;
                    x2 -= cw2;
                    rightComponent.setBounds(x2, ow, cw2, h);
                }
            }
        }

        public void addLayoutComponent(Component comp, Object constraints) {
            if (this.delegate instanceof LayoutManager2) {
                this.delegate.addLayoutComponent(comp, constraints);
            }
        }

        public Dimension maximumLayoutSize(Container target) {
            if (this.delegate instanceof LayoutManager2) {
                return this.delegate.maximumLayoutSize(target);
            }
            return null;
        }

        public float getLayoutAlignmentX(Container target) {
            if (this.delegate instanceof LayoutManager2) {
                return this.delegate.getLayoutAlignmentX(target);
            }
            return 0.5f;
        }

        public float getLayoutAlignmentY(Container target) {
            if (this.delegate instanceof LayoutManager2) {
                return this.delegate.getLayoutAlignmentY(target);
            }
            return 0.5f;
        }

        public void invalidateLayout(Container target) {
            if (this.delegate instanceof LayoutManager2) {
                this.delegate.invalidateLayout(target);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTextFieldUI$FlatDocumentListener.class */
    public class FlatDocumentListener implements DocumentListener {
        private FlatDocumentListener() {
        }

        public void insertUpdate(DocumentEvent e) {
            FlatTextFieldUI.this.documentChanged(e);
        }

        public void removeUpdate(DocumentEvent e) {
            FlatTextFieldUI.this.documentChanged(e);
        }

        public void changedUpdate(DocumentEvent e) {
            FlatTextFieldUI.this.documentChanged(e);
        }
    }
}
