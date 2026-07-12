package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicListUI;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatListUI.class */
public class FlatListUI extends BasicListUI implements FlatStylingSupport.StyleableUI {

    @FlatStylingSupport.Styleable
    protected Color selectionBackground;

    @FlatStylingSupport.Styleable
    protected Color selectionForeground;

    @FlatStylingSupport.Styleable
    protected Color selectionInactiveBackground;

    @FlatStylingSupport.Styleable
    protected Color selectionInactiveForeground;

    @FlatStylingSupport.Styleable
    protected Insets selectionInsets;

    @FlatStylingSupport.Styleable
    protected int selectionArc;

    @FlatStylingSupport.Styleable
    protected Insets cellMargins;

    @FlatStylingSupport.Styleable
    protected Color cellFocusColor;

    @FlatStylingSupport.Styleable
    protected Boolean showCellFocusIndicator;
    private Map<String, Object> oldStyleValues;

    public static ComponentUI createUI(JComponent c) {
        return new FlatListUI();
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
        installStyle();
    }

    protected void installDefaults() {
        super.installDefaults();
        this.selectionBackground = UIManager.getColor("List.selectionBackground");
        this.selectionForeground = UIManager.getColor("List.selectionForeground");
        this.selectionInactiveBackground = UIManager.getColor("List.selectionInactiveBackground");
        this.selectionInactiveForeground = UIManager.getColor("List.selectionInactiveForeground");
        this.selectionInsets = UIManager.getInsets("List.selectionInsets");
        this.selectionArc = UIManager.getInt("List.selectionArc");
        toggleSelectionColors();
    }

    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.selectionBackground = null;
        this.selectionForeground = null;
        this.selectionInactiveBackground = null;
        this.selectionInactiveForeground = null;
        this.oldStyleValues = null;
    }

    protected FocusListener createFocusListener() {
        return new BasicListUI.FocusHandler() { // from class: com.formdev.flatlaf.ui.FlatListUI.1
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                FlatListUI.this.toggleSelectionColors();
            }

            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                EventQueue.invokeLater(() -> {
                    FlatListUI.this.toggleSelectionColors();
                });
            }
        };
    }

    protected PropertyChangeListener createPropertyChangeListener() {
        PropertyChangeListener superListener = super.createPropertyChangeListener();
        return e -> {
            superListener.propertyChange(e);
            String propertyName = e.getPropertyName();
            boolean z = -1;
            switch (propertyName.hashCode()) {
                case 1030195901:
                    if (propertyName.equals(FlatClientProperties.STYLE_CLASS)) {
                        z = 2;
                        break;
                    }
                    break;
                case 1545413499:
                    if (propertyName.equals(FlatClientProperties.STYLE)) {
                        z = true;
                        break;
                    }
                    break;
                case 1859588534:
                    if (propertyName.equals(FlatClientProperties.COMPONENT_FOCUS_OWNER)) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    toggleSelectionColors();
                    return;
                case true:
                case true:
                    installStyle();
                    this.list.revalidate();
                    HiDPIUtils.repaint(this.list);
                    return;
                default:
                    return;
            }
        };
    }

    protected ListSelectionListener createListSelectionListener() {
        ListSelectionListener superListener = super.createListSelectionListener();
        return e -> {
            superListener.valueChanged(e);
            if (useUnitedRoundedSelection(true, true) && !this.list.isSelectionEmpty() && this.list.getMaxSelectionIndex() - this.list.getMinSelectionIndex() >= 1) {
                int size = this.list.getModel().getSize();
                int firstIndex = Math.min(Math.max(e.getFirstIndex(), 0), size - 1);
                int lastIndex = Math.min(Math.max(e.getLastIndex(), 0), size - 1);
                Rectangle r = getCellBounds(this.list, firstIndex, lastIndex);
                if (r != null) {
                    int arc = (int) Math.ceil(UIScale.scale(this.selectionArc / 2.0f));
                    HiDPIUtils.repaint(this.list, r.x - arc, r.y - arc, r.width + (arc * 2), r.height + (arc * 2));
                }
            }
        };
    }

    protected void installStyle() {
        try {
            applyStyle(FlatStylingSupport.getResolvedStyle(this.list, PDListAttributeObject.OWNER_LIST));
        } catch (RuntimeException ex) {
            LoggingFacade.INSTANCE.logSevere(null, ex);
        }
    }

    protected void applyStyle(Object style) {
        Color oldSelectionBackground = this.selectionBackground;
        Color oldSelectionForeground = this.selectionForeground;
        Color oldSelectionInactiveBackground = this.selectionInactiveBackground;
        Color oldSelectionInactiveForeground = this.selectionInactiveForeground;
        this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
        if (this.selectionBackground != oldSelectionBackground) {
            Color selBg = this.list.getSelectionBackground();
            if (selBg == oldSelectionBackground) {
                this.list.setSelectionBackground(this.selectionBackground);
            } else if (selBg == oldSelectionInactiveBackground) {
                this.list.setSelectionBackground(this.selectionInactiveBackground);
            }
        }
        if (this.selectionForeground != oldSelectionForeground) {
            Color selFg = this.list.getSelectionForeground();
            if (selFg == oldSelectionForeground) {
                this.list.setSelectionForeground(this.selectionForeground);
            } else if (selFg == oldSelectionInactiveForeground) {
                this.list.setSelectionForeground(this.selectionInactiveForeground);
            }
        }
    }

    protected Object applyStyleProperty(String key, Object value) {
        return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.list, key, value);
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableUI
    public Map<String, Class<?>> getStyleableInfos(JComponent c) {
        return FlatStylingSupport.getAnnotatedStyleableInfos(this);
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableUI
    public Object getStyleableValue(JComponent c, String key) {
        return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleSelectionColors() {
        if (this.list == null) {
            return;
        }
        if (FlatUIUtils.isPermanentFocusOwner(this.list)) {
            if (this.list.getSelectionBackground() == this.selectionInactiveBackground) {
                this.list.setSelectionBackground(this.selectionBackground);
            }
            if (this.list.getSelectionForeground() == this.selectionInactiveForeground) {
                this.list.setSelectionForeground(this.selectionForeground);
                return;
            }
            return;
        }
        if (this.list.getSelectionBackground() == this.selectionBackground) {
            this.list.setSelectionBackground(this.selectionInactiveBackground);
        }
        if (this.list.getSelectionForeground() == this.selectionForeground) {
            this.list.setSelectionForeground(this.selectionInactiveForeground);
        }
    }

    protected void paintCell(Graphics g, int row, Rectangle rowBounds, ListCellRenderer cellRenderer, ListModel dataModel, ListSelectionModel selModel, int leadIndex) {
        int cx;
        int cw;
        int i;
        boolean isSelected = selModel.isSelectedIndex(row);
        Component rendererComponent = cellRenderer.getListCellRendererComponent(this.list, dataModel.getElementAt(row), row, isSelected, FlatUIUtils.isPermanentFocusOwner(this.list) && row == leadIndex);
        boolean isFileList = Boolean.TRUE.equals(this.list.getClientProperty("List.isFileList"));
        if (isFileList) {
            cw = Math.min(rowBounds.width, rendererComponent.getPreferredSize().width + 4);
            if (this.list.getComponentOrientation().isLeftToRight()) {
                i = rowBounds.x;
            } else {
                i = rowBounds.x + (rowBounds.width - cw);
            }
            cx = i;
        } else {
            cx = rowBounds.x;
            cw = rowBounds.width;
        }
        if (isSelected && !isFileList && (((rendererComponent instanceof DefaultListCellRenderer) || (rendererComponent instanceof BasicComboBoxRenderer)) && (this.selectionArc > 0 || (this.selectionInsets != null && !FlatUIUtils.isInsetsEmpty(this.selectionInsets))))) {
            g = new C1RoundedSelectionGraphics(g, rowBounds, rendererComponent, row);
        }
        this.rendererPane.paintComponent(g, rendererComponent, this.list, cx, rowBounds.y, cw, rowBounds.height, true);
    }

    /* renamed from: com.formdev.flatlaf.ui.FlatListUI$1RoundedSelectionGraphics, reason: invalid class name */
    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatListUI$1RoundedSelectionGraphics.class */
    class C1RoundedSelectionGraphics extends Graphics2DProxy {
        private boolean inPaintSelection;
        final /* synthetic */ Rectangle val$rowBounds;
        final /* synthetic */ Component val$rendererComponent;
        final /* synthetic */ int val$row;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C1RoundedSelectionGraphics(Graphics delegate, Rectangle rectangle, Component component, int i) {
            super((Graphics2D) delegate);
            this.val$rowBounds = rectangle;
            this.val$rendererComponent = component;
            this.val$row = i;
        }

        @Override // com.formdev.flatlaf.util.Graphics2DProxy
        public Graphics create() {
            return new C1RoundedSelectionGraphics(super.create(), this.val$rowBounds, this.val$rendererComponent, this.val$row);
        }

        @Override // com.formdev.flatlaf.util.Graphics2DProxy
        public Graphics create(int x, int y, int width, int height) {
            return new C1RoundedSelectionGraphics(super.create(x, y, width, height), this.val$rowBounds, this.val$rendererComponent, this.val$row);
        }

        @Override // com.formdev.flatlaf.util.Graphics2DProxy
        public void fillRect(int x, int y, int width, int height) {
            if (!this.inPaintSelection && x == 0 && y == 0 && width == this.val$rowBounds.width && height == this.val$rowBounds.height && getColor() == this.val$rendererComponent.getBackground()) {
                this.inPaintSelection = true;
                FlatListUI.this.paintCellSelection(this, this.val$row, x, y, width, height);
                this.inPaintSelection = false;
                return;
            }
            super.fillRect(x, y, width, height);
        }
    }

    protected void paintCellSelection(Graphics g, int row, int x, int y, int width, int height) {
        float scale = UIScale.scale(this.selectionArc / 2.0f);
        float arcBottomRight = scale;
        float arcBottomLeft = scale;
        float arcTopRight = scale;
        float arcTopLeft = scale;
        if (this.list.getLayoutOrientation() == 0) {
            if (useUnitedRoundedSelection(true, false)) {
                if (row > 0 && this.list.isSelectedIndex(row - 1)) {
                    arcTopRight = 0.0f;
                    arcTopLeft = 0.0f;
                }
                if (row < this.list.getModel().getSize() - 1 && this.list.isSelectedIndex(row + 1)) {
                    arcBottomRight = 0.0f;
                    arcBottomLeft = 0.0f;
                }
            }
        } else {
            Rectangle r = null;
            if (useUnitedRoundedSelection(true, false)) {
                r = getCellBounds(this.list, row, row);
                int topIndex = locationToIndex(this.list, new Point(r.x, r.y - 1));
                int bottomIndex = locationToIndex(this.list, new Point(r.x, r.y + r.height));
                if (topIndex >= 0 && topIndex != row && this.list.isSelectedIndex(topIndex)) {
                    arcTopRight = 0.0f;
                    arcTopLeft = 0.0f;
                }
                if (bottomIndex >= 0 && bottomIndex != row && this.list.isSelectedIndex(bottomIndex)) {
                    arcBottomRight = 0.0f;
                    arcBottomLeft = 0.0f;
                }
            }
            if (useUnitedRoundedSelection(false, true)) {
                if (r == null) {
                    r = getCellBounds(this.list, row, row);
                }
                int leftIndex = locationToIndex(this.list, new Point(r.x - 1, r.y));
                int rightIndex = locationToIndex(this.list, new Point(r.x + r.width, r.y));
                boolean ltr = this.list.getComponentOrientation().isLeftToRight();
                if (!ltr && leftIndex >= 0 && leftIndex != row && leftIndex == locationToIndex(this.list, new Point(r.x - 1, r.y - 1))) {
                    leftIndex = -1;
                }
                if (ltr && rightIndex >= 0 && rightIndex != row && rightIndex == locationToIndex(this.list, new Point(r.x + r.width, r.y - 1))) {
                    rightIndex = -1;
                }
                if (leftIndex >= 0 && leftIndex != row && this.list.isSelectedIndex(leftIndex)) {
                    arcBottomLeft = 0.0f;
                    arcTopLeft = 0.0f;
                }
                if (rightIndex >= 0 && rightIndex != row && this.list.isSelectedIndex(rightIndex)) {
                    arcBottomRight = 0.0f;
                    arcTopRight = 0.0f;
                }
            }
        }
        FlatUIUtils.paintSelection((Graphics2D) g, x, y, width, height, UIScale.scale(this.selectionInsets), arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight, 0);
    }

    private boolean useUnitedRoundedSelection(boolean vertical, boolean horizontal) {
        return this.selectionArc > 0 && (this.selectionInsets == null || ((vertical && this.selectionInsets.top == 0 && this.selectionInsets.bottom == 0) || (horizontal && this.selectionInsets.left == 0 && this.selectionInsets.right == 0)));
    }

    public static void paintCellSelection(JList<?> list, Graphics g, int row, int x, int y, int width, int height) {
        if (!(list.getUI() instanceof FlatListUI)) {
            return;
        }
        FlatListUI ui = list.getUI();
        ui.paintCellSelection(g, row, x, y, width, height);
    }
}
