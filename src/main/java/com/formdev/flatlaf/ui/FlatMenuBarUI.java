package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.LookAndFeel;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.plaf.basic.DefaultMenuLayout;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatMenuBarUI.class */
public class FlatMenuBarUI extends BasicMenuBarUI implements FlatStylingSupport.StyleableUI {

    @FlatStylingSupport.Styleable
    protected Insets itemMargins;

    @FlatStylingSupport.Styleable
    protected Insets selectionInsets;

    @FlatStylingSupport.Styleable
    protected Insets selectionEmbeddedInsets;

    @FlatStylingSupport.Styleable
    protected Color hoverBackground;

    @FlatStylingSupport.Styleable
    protected Color selectionBackground;

    @FlatStylingSupport.Styleable
    protected Color selectionForeground;

    @FlatStylingSupport.Styleable
    protected Color underlineSelectionBackground;

    @FlatStylingSupport.Styleable
    protected Color underlineSelectionColor;
    private PropertyChangeListener propertyChangeListener;
    private Map<String, Object> oldStyleValues;
    private AtomicBoolean borderShared;

    @FlatStylingSupport.Styleable
    protected int selectionArc = -1;

    @FlatStylingSupport.Styleable
    protected int underlineSelectionHeight = -1;

    public static ComponentUI createUI(JComponent c) {
        return new FlatMenuBarUI();
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        installStyle();
    }

    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installProperty(this.menuBar, "opaque", false);
        LayoutManager layout = this.menuBar.getLayout();
        if (layout == null || (layout instanceof UIResource)) {
            this.menuBar.setLayout(new FlatMenuBarLayout(this.menuBar));
        }
    }

    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.oldStyleValues = null;
        this.borderShared = null;
    }

    protected void installListeners() {
        super.installListeners();
        this.propertyChangeListener = FlatStylingSupport.createPropertyChangeListener(this.menuBar, this::installStyle, null);
        this.menuBar.addPropertyChangeListener(this.propertyChangeListener);
    }

    protected void uninstallListeners() {
        super.uninstallListeners();
        this.menuBar.removePropertyChangeListener(this.propertyChangeListener);
        this.propertyChangeListener = null;
    }

    protected void installKeyboardActions() {
        super.installKeyboardActions();
        ActionMap map = SwingUtilities.getUIActionMap(this.menuBar);
        if (map != null && !(map.get("takeFocus") instanceof TakeFocusAction)) {
            map.put("takeFocus", new TakeFocusAction("takeFocus"));
        }
    }

    protected void installStyle() {
        try {
            applyStyle(FlatStylingSupport.getResolvedStyle(this.menuBar, "MenuBar"));
        } catch (RuntimeException ex) {
            LoggingFacade.INSTANCE.logSevere(null, ex);
        }
    }

    protected void applyStyle(Object style) {
        this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
    }

    protected Object applyStyleProperty(String key, Object value) {
        if (this.borderShared == null) {
            this.borderShared = new AtomicBoolean(true);
        }
        return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, this.menuBar, this.borderShared);
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableUI
    public Map<String, Class<?>> getStyleableInfos(JComponent c) {
        return FlatStylingSupport.getAnnotatedStyleableInfos(this, this.menuBar.getBorder());
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableUI
    public Object getStyleableValue(JComponent c, String key) {
        return FlatStylingSupport.getAnnotatedStyleableValue(this, this.menuBar.getBorder(), key);
    }

    public void update(Graphics g, JComponent c) {
        Color background = getBackground(c);
        if (background != null) {
            g.setColor(background);
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
        }
        paint(g, c);
    }

    protected Color getBackground(JComponent c) {
        Color background = c.getBackground();
        if (c.isOpaque()) {
            return background;
        }
        if (!(background instanceof UIResource)) {
            return null;
        }
        JRootPane rootPane = SwingUtilities.getRootPane(c);
        if (rootPane == null || !(rootPane.getParent() instanceof Window) || rootPane.getJMenuBar() != c) {
            return background;
        }
        if (useUnifiedBackground(c)) {
            background = FlatUIUtils.getParentBackground(c);
        }
        if (FlatUIUtils.isFullScreen(rootPane)) {
            return background;
        }
        if (FlatRootPaneUI.isMenuBarEmbedded(rootPane)) {
            return null;
        }
        return background;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean useUnifiedBackground(Component c) {
        JRootPane rootPane;
        return UIManager.getBoolean("TitlePane.unifiedBackground") && (rootPane = SwingUtilities.getRootPane(c)) != null && (rootPane.getParent() instanceof Window) && rootPane.getJMenuBar() == c && rootPane.getWindowDecorationStyle() != 0;
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatMenuBarUI$FlatMenuBarLayout.class */
    protected static class FlatMenuBarLayout extends DefaultMenuLayout {
        public FlatMenuBarLayout(Container target) {
            super(target, 2);
        }

        public void layoutContainer(Container target) {
            FlatTitlePane titlePane;
            super.layoutContainer(target);
            JRootPane rootPane = SwingUtilities.getRootPane(target);
            if (rootPane == null || rootPane.getJMenuBar() != target || (titlePane = FlatRootPaneUI.getTitlePane(rootPane)) == null || !titlePane.isMenuBarEmbedded()) {
                return;
            }
            Component horizontalGlue = titlePane.findHorizontalGlue((JMenuBar) target);
            int minTitleWidth = UIScale.scale(titlePane.titleMinimumWidth);
            if (horizontalGlue != null && horizontalGlue.getWidth() < minTitleWidth) {
                int glueIndex = -1;
                Component[] components = target.getComponents();
                int i = components.length - 1;
                while (true) {
                    if (i < 0) {
                        break;
                    }
                    if (components[i] == horizontalGlue) {
                        glueIndex = i;
                        break;
                    }
                    i--;
                }
                if (glueIndex < 0) {
                    return;
                }
                if (target.getComponentOrientation().isLeftToRight()) {
                    int offset = minTitleWidth - horizontalGlue.getWidth();
                    horizontalGlue.setSize(minTitleWidth, horizontalGlue.getHeight());
                    int minGlueX = (target.getWidth() - target.getInsets().right) - minTitleWidth;
                    if (minGlueX < horizontalGlue.getX()) {
                        offset -= horizontalGlue.getX() - minGlueX;
                        horizontalGlue.setLocation(minGlueX, horizontalGlue.getY());
                        int i2 = glueIndex - 1;
                        while (true) {
                            if (i2 < 0) {
                                break;
                            }
                            Component c = components[i2];
                            if (c.getX() > minGlueX) {
                                c.setBounds(minGlueX, c.getY(), 0, c.getHeight());
                                i2--;
                            } else {
                                c.setSize(minGlueX - c.getX(), c.getHeight());
                                break;
                            }
                        }
                    }
                    for (int i3 = glueIndex + 1; i3 < components.length; i3++) {
                        Component c2 = components[i3];
                        c2.setLocation(c2.getX() + offset, c2.getY());
                    }
                    return;
                }
                int offset2 = minTitleWidth - horizontalGlue.getWidth();
                horizontalGlue.setBounds(horizontalGlue.getX() - offset2, horizontalGlue.getY(), minTitleWidth, horizontalGlue.getHeight());
                int minGlueX2 = target.getInsets().left;
                if (minGlueX2 > horizontalGlue.getX()) {
                    offset2 -= horizontalGlue.getX() - minGlueX2;
                    horizontalGlue.setLocation(minGlueX2, horizontalGlue.getY());
                    int x = horizontalGlue.getX() + horizontalGlue.getWidth();
                    int i4 = glueIndex - 1;
                    while (true) {
                        if (i4 < 0) {
                            break;
                        }
                        Component c3 = components[i4];
                        if (c3.getX() + c3.getWidth() < x) {
                            c3.setBounds(x, c3.getY(), 0, c3.getHeight());
                            i4--;
                        } else {
                            c3.setBounds(x, c3.getY(), c3.getWidth() - (x - c3.getX()), c3.getHeight());
                            break;
                        }
                    }
                }
                for (int i5 = glueIndex + 1; i5 < components.length; i5++) {
                    Component c4 = components[i5];
                    c4.setLocation(c4.getX() - offset2, c4.getY());
                }
            }
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatMenuBarUI$TakeFocusAction.class */
    private static class TakeFocusAction extends FlatUIAction {
        TakeFocusAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            MenuElement[] menuElementArr;
            MenuElement menuElement = (JMenuBar) e.getSource();
            MenuElement menu = menuElement.getMenu(0);
            if (menu != null) {
                MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
                if (SystemInfo.isWindows) {
                    menuElementArr = new MenuElement[]{menuElement, menu};
                } else {
                    menuElementArr = new MenuElement[]{menuElement, menu, menu.getPopupMenu()};
                }
                defaultManager.setSelectedPath(menuElementArr);
                FlatLaf.showMnemonics(menuElement);
            }
        }
    }
}
