package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.swing.CellRendererPane;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTreeUI.class */
public class FlatTreeUI extends BasicTreeUI implements FlatStylingSupport.StyleableUI {

    @FlatStylingSupport.Styleable
    protected Color selectionBackground;

    @FlatStylingSupport.Styleable
    protected Color selectionForeground;

    @FlatStylingSupport.Styleable
    protected Color selectionInactiveBackground;

    @FlatStylingSupport.Styleable
    protected Color selectionInactiveForeground;

    @FlatStylingSupport.Styleable
    protected Color selectionBorderColor;

    @FlatStylingSupport.Styleable
    protected Insets selectionInsets;

    @FlatStylingSupport.Styleable
    protected int selectionArc;

    @FlatStylingSupport.Styleable
    protected boolean wideSelection;

    @FlatStylingSupport.Styleable
    protected boolean showCellFocusIndicator;
    protected boolean showDefaultIcons;

    @FlatStylingSupport.Styleable(dot = true)
    public String iconArrowType;

    @FlatStylingSupport.Styleable(dot = true)
    public Color iconExpandedColor;

    @FlatStylingSupport.Styleable(dot = true)
    public Color iconCollapsedColor;

    @FlatStylingSupport.Styleable(dot = true)
    public Color iconLeafColor;

    @FlatStylingSupport.Styleable(dot = true)
    public Color iconClosedColor;

    @FlatStylingSupport.Styleable(dot = true)
    public Color iconOpenColor;

    @FlatStylingSupport.Styleable
    protected boolean paintSelection = true;
    private Icon defaultLeafIcon;
    private Icon defaultClosedIcon;
    private Icon defaultOpenIcon;
    private boolean paintLines;
    private Color defaultCellNonSelectionBackground;
    private Color defaultSelectionBackground;
    private Color defaultSelectionForeground;
    private Color defaultSelectionBorderColor;
    private Map<String, Object> oldStyleValues;

    public static ComponentUI createUI(JComponent c) {
        return new FlatTreeUI();
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        installStyle();
    }

    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installBorder(this.tree, "Tree.border");
        this.selectionBackground = UIManager.getColor("Tree.selectionBackground");
        this.selectionForeground = UIManager.getColor("Tree.selectionForeground");
        this.selectionInactiveBackground = UIManager.getColor("Tree.selectionInactiveBackground");
        this.selectionInactiveForeground = UIManager.getColor("Tree.selectionInactiveForeground");
        this.selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
        this.selectionInsets = UIManager.getInsets("Tree.selectionInsets");
        this.selectionArc = UIManager.getInt("Tree.selectionArc");
        this.wideSelection = UIManager.getBoolean("Tree.wideSelection");
        this.showCellFocusIndicator = UIManager.getBoolean("Tree.showCellFocusIndicator");
        this.showDefaultIcons = UIManager.getBoolean("Tree.showDefaultIcons");
        this.defaultLeafIcon = UIManager.getIcon("Tree.leafIcon");
        this.defaultClosedIcon = UIManager.getIcon("Tree.closedIcon");
        this.defaultOpenIcon = UIManager.getIcon("Tree.openIcon");
        this.paintLines = UIManager.getBoolean("Tree.paintLines");
        this.defaultCellNonSelectionBackground = UIManager.getColor("Tree.textBackground");
        this.defaultSelectionBackground = this.selectionBackground;
        this.defaultSelectionForeground = this.selectionForeground;
        this.defaultSelectionBorderColor = this.selectionBorderColor;
        int rowHeight = FlatUIUtils.getUIInt("Tree.rowHeight", 16);
        if (rowHeight > 0) {
            LookAndFeel.installProperty(this.tree, "rowHeight", Integer.valueOf(UIScale.scale(rowHeight)));
        }
        setLeftChildIndent(UIScale.scale(getLeftChildIndent()));
        setRightChildIndent(UIScale.scale(getRightChildIndent()));
    }

    protected void uninstallDefaults() {
        super.uninstallDefaults();
        LookAndFeel.uninstallBorder(this.tree);
        this.selectionBackground = null;
        this.selectionForeground = null;
        this.selectionInactiveBackground = null;
        this.selectionInactiveForeground = null;
        this.selectionBorderColor = null;
        this.defaultLeafIcon = null;
        this.defaultClosedIcon = null;
        this.defaultOpenIcon = null;
        this.defaultCellNonSelectionBackground = null;
        this.defaultSelectionBackground = null;
        this.defaultSelectionForeground = null;
        this.defaultSelectionBorderColor = null;
        this.oldStyleValues = null;
    }

    protected void updateRenderer() {
        super.updateRenderer();
        if (!this.showDefaultIcons && (this.currentCellRenderer instanceof DefaultTreeCellRenderer)) {
            DefaultTreeCellRenderer renderer = this.currentCellRenderer;
            if (renderer.getLeafIcon() == this.defaultLeafIcon && renderer.getClosedIcon() == this.defaultClosedIcon && renderer.getOpenIcon() == this.defaultOpenIcon) {
                renderer.setLeafIcon((Icon) null);
                renderer.setClosedIcon((Icon) null);
                renderer.setOpenIcon((Icon) null);
            }
        }
    }

    protected MouseListener createMouseListener() {
        return new BasicTreeUI.MouseHandler() { // from class: com.formdev.flatlaf.ui.FlatTreeUI.1
            public void mousePressed(MouseEvent e) {
                super.mousePressed(handleWideMouseEvent(e));
            }

            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(handleWideMouseEvent(e));
            }

            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(handleWideMouseEvent(e));
            }

            private MouseEvent handleWideMouseEvent(MouseEvent e) {
                if (!FlatTreeUI.this.isWideSelection() || !FlatTreeUI.this.tree.isEnabled() || !SwingUtilities.isLeftMouseButton(e) || e.isConsumed()) {
                    return e;
                }
                int x = e.getX();
                int y = e.getY();
                TreePath path = FlatTreeUI.this.getClosestPathForLocation(FlatTreeUI.this.tree, x, y);
                if (path != null && !FlatTreeUI.this.isLocationInExpandControl(path, x, y)) {
                    Rectangle bounds = FlatTreeUI.this.getPathBounds(FlatTreeUI.this.tree, path);
                    if (bounds == null || y < bounds.y || y >= bounds.y + bounds.height) {
                        return e;
                    }
                    int newX = Math.max(bounds.x, Math.min(x, (bounds.x + bounds.width) - 1));
                    if (newX == x) {
                        return e;
                    }
                    return new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers() | e.getModifiersEx(), newX, e.getY(), e.getClickCount(), e.isPopupTrigger(), e.getButton());
                }
                return e;
            }
        };
    }

    protected PropertyChangeListener createPropertyChangeListener() {
        PropertyChangeListener superListener = super.createPropertyChangeListener();
        return e -> {
            superListener.propertyChange(e);
            if (e.getSource() == this.tree) {
                String propertyName = e.getPropertyName();
                boolean z = -1;
                switch (propertyName.hashCode()) {
                    case -1996890221:
                        if (propertyName.equals(FlatClientProperties.TREE_WIDE_SELECTION)) {
                            z = false;
                            break;
                        }
                        break;
                    case -1909533756:
                        if (propertyName.equals("dropLocation")) {
                            z = 2;
                            break;
                        }
                        break;
                    case -1609594047:
                        if (propertyName.equals("enabled")) {
                            z = 5;
                            break;
                        }
                        break;
                    case 98965108:
                        if (propertyName.equals(FlatClientProperties.TREE_PAINT_SELECTION)) {
                            z = true;
                            break;
                        }
                        break;
                    case 1030195901:
                        if (propertyName.equals(FlatClientProperties.STYLE_CLASS)) {
                            z = 4;
                            break;
                        }
                        break;
                    case 1545413499:
                        if (propertyName.equals(FlatClientProperties.STYLE)) {
                            z = 3;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                    case true:
                        HiDPIUtils.repaint(this.tree);
                        return;
                    case true:
                        if (isWideSelection()) {
                            JTree.DropLocation oldValue = (JTree.DropLocation) e.getOldValue();
                            repaintWideDropLocation(oldValue);
                            repaintWideDropLocation(this.tree.getDropLocation());
                            return;
                        }
                        return;
                    case true:
                    case true:
                        installStyle();
                        this.tree.revalidate();
                        HiDPIUtils.repaint(this.tree);
                        return;
                    case true:
                        if (!this.showDefaultIcons && (this.currentCellRenderer instanceof DefaultTreeCellRenderer) && this.currentCellRenderer.getClass() != DefaultTreeCellRenderer.class && this.treeState != null) {
                            this.treeState.invalidateSizes();
                            updateSize();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
    }

    private void repaintWideDropLocation(JTree.DropLocation loc) {
        Rectangle r;
        if (loc != null && !isDropLine(loc) && (r = this.tree.getPathBounds(loc.getPath())) != null) {
            HiDPIUtils.repaint(this.tree, 0, r.y, this.tree.getWidth(), r.height);
        }
    }

    protected TreeSelectionListener createTreeSelectionListener() {
        TreeSelectionListener superListener = super.createTreeSelectionListener();
        return e -> {
            TreePath[] changedPaths;
            superListener.valueChanged(e);
            if (useUnitedRoundedSelection() && this.tree.getSelectionCount() > 1 && (changedPaths = e.getPaths()) != null) {
                if (changedPaths.length > 4) {
                    HiDPIUtils.repaint(this.tree);
                    return;
                }
                int arc = (int) Math.ceil(UIScale.scale(this.selectionArc / 2.0f));
                for (TreePath path : changedPaths) {
                    Rectangle r = getPathBounds(this.tree, path);
                    if (r != null) {
                        HiDPIUtils.repaint(this.tree, r.x, r.y - arc, r.width, r.height + (arc * 2));
                    }
                }
            }
        };
    }

    public Rectangle getPathBounds(JTree tree, TreePath path) {
        Rectangle bounds = super.getPathBounds(tree, path);
        if (bounds != null && isWideSelection() && UIManager.getBoolean("FlatLaf.experimental.tree.widePathForLocation") && StackUtils.wasInvokedFrom(JTree.class.getName(), "getPathForLocation", 5)) {
            bounds.x = 0;
            bounds.width = tree.getWidth();
        }
        return bounds;
    }

    protected void installStyle() {
        try {
            applyStyle(FlatStylingSupport.getResolvedStyle(this.tree, "Tree"));
        } catch (RuntimeException ex) {
            LoggingFacade.INSTANCE.logSevere(null, ex);
        }
    }

    protected void applyStyle(Object style) {
        this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
    }

    protected Object applyStyleProperty(String key, Object value) {
        if ("rowHeight".equals(key) && (value instanceof Integer)) {
            value = Integer.valueOf(UIScale.scale(((Integer) value).intValue()));
        }
        return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.tree, key, value);
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableUI
    public Map<String, Class<?>> getStyleableInfos(JComponent c) {
        return FlatStylingSupport.getAnnotatedStyleableInfos(this);
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableUI
    public Object getStyleableValue(JComponent c, String key) {
        return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
    }

    public void paint(Graphics g, JComponent c) {
        TreePath path;
        Rectangle bounds;
        if (this.treeState == null) {
            return;
        }
        Rectangle clipBounds = g.getClipBounds();
        TreePath firstPath = getClosestPathForLocation(this.tree, 0, clipBounds.y);
        Enumeration<TreePath> visiblePaths = this.treeState.getVisiblePathsFrom(firstPath);
        if (visiblePaths != null) {
            Insets insets = this.tree.getInsets();
            HashSet<TreePath> verticalLinePaths = this.paintLines ? new HashSet<>() : null;
            ArrayList<Runnable> paintLinesLater = this.paintLines ? new ArrayList<>() : null;
            ArrayList<Runnable> paintExpandControlsLater = this.paintLines ? new ArrayList<>() : null;
            if (this.paintLines) {
                TreePath parentPath = firstPath.getParentPath();
                while (true) {
                    TreePath path2 = parentPath;
                    if (path2 == null) {
                        break;
                    }
                    verticalLinePaths.add(path2);
                    parentPath = path2.getParentPath();
                }
            }
            Rectangle boundsBuffer = new Rectangle();
            boolean rootVisible = isRootVisible();
            int row = this.treeState.getRowForPath(firstPath);
            boolean leftToRight = this.tree.getComponentOrientation().isLeftToRight();
            int treeWidth = this.tree.getWidth();
            while (visiblePaths.hasMoreElements() && (path = visiblePaths.nextElement()) != null && (bounds = this.treeState.getBounds(path, boundsBuffer)) != null) {
                if (leftToRight) {
                    bounds.x += insets.left;
                } else {
                    bounds.x = (treeWidth - insets.right) - (bounds.x + bounds.width);
                }
                bounds.y += insets.top;
                boolean isLeaf = this.treeModel.isLeaf(path.getLastPathComponent());
                boolean isExpanded = isLeaf ? false : this.treeState.getExpandedState(path);
                boolean hasBeenExpanded = isLeaf ? false : this.tree.hasBeenExpanded(path);
                paintRow(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
                if (this.paintLines) {
                    TreePath parentPath2 = path.getParentPath();
                    if (parentPath2 != null) {
                        verticalLinePaths.add(parentPath2);
                    }
                    if (parentPath2 != null || (rootVisible && row == 0)) {
                        Rectangle bounds2 = new Rectangle(bounds);
                        int row2 = row;
                        paintLinesLater.add(() -> {
                            paintHorizontalPartOfLeg(g, clipBounds, insets, bounds2, path, row2, isExpanded, hasBeenExpanded, isLeaf);
                        });
                    }
                }
                if (shouldPaintExpandControl(path, row, isExpanded, hasBeenExpanded, isLeaf)) {
                    if (this.paintLines) {
                        Rectangle bounds22 = new Rectangle(bounds);
                        int row22 = row;
                        paintExpandControlsLater.add(() -> {
                            paintExpandControl(g, clipBounds, insets, bounds22, path, row22, isExpanded, hasBeenExpanded, isLeaf);
                        });
                    } else {
                        paintExpandControl(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
                    }
                }
                if (bounds.y + bounds.height >= clipBounds.y + clipBounds.height) {
                    break;
                } else {
                    row++;
                }
            }
            if (this.paintLines) {
                Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
                Iterator<Runnable> it = paintLinesLater.iterator();
                while (it.hasNext()) {
                    Runnable r = it.next();
                    r.run();
                }
                g.setColor(Color.green);
                Iterator<TreePath> it2 = verticalLinePaths.iterator();
                while (it2.hasNext()) {
                    paintVerticalPartOfLeg(g, clipBounds, insets, it2.next());
                }
                if (oldRenderingHints != null) {
                    FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
                }
                Iterator<Runnable> it3 = paintExpandControlsLater.iterator();
                while (it3.hasNext()) {
                    Runnable r2 = it3.next();
                    r2.run();
                }
            }
        }
        paintDropLine(g);
        this.rendererPane.removeAll();
    }

    protected void paintRow(Graphics g, Rectangle clipBounds, Insets insets, Rectangle bounds, TreePath path, int row, boolean isExpanded, boolean hasBeenExpanded, boolean isLeaf) {
        Color color;
        boolean isEditing = this.editingComponent != null && this.editingRow == row;
        boolean isSelected = this.tree.isRowSelected(row);
        boolean isDropRow = isDropRow(row);
        boolean needsSelectionPainting = (isSelected || isDropRow) && isPaintSelection();
        if (isEditing) {
            if (isSelected && isWideSelection()) {
                Color oldColor = g.getColor();
                g.setColor(this.selectionInactiveBackground);
                paintWideSelection(g, bounds, row);
                g.setColor(oldColor);
                return;
            }
            return;
        }
        boolean hasFocus = FlatUIUtils.isPermanentFocusOwner(this.tree);
        boolean cellHasFocus = hasFocus && row == getLeadSelectionRow();
        if (!hasFocus && isSelected && (this.tree.getParent() instanceof CellRendererPane)) {
            hasFocus = FlatUIUtils.isPermanentFocusOwner(this.tree.getParent().getParent());
        }
        DefaultTreeCellRenderer treeCellRendererComponent = this.currentCellRenderer.getTreeCellRendererComponent(this.tree, path.getLastPathComponent(), isSelected, isExpanded, isLeaf, row, cellHasFocus);
        Color oldBackgroundSelectionColor = null;
        if (isSelected && !hasFocus && !isDropRow) {
            oldBackgroundSelectionColor = setRendererBackgroundSelectionColor(treeCellRendererComponent, this.selectionInactiveBackground);
            setRendererForeground(treeCellRendererComponent, this.selectionInactiveForeground);
        } else if (isSelected) {
            if (this.selectionBackground != this.defaultSelectionBackground) {
                oldBackgroundSelectionColor = setRendererBackgroundSelectionColor(treeCellRendererComponent, this.selectionBackground);
            }
            if (this.selectionForeground != this.defaultSelectionForeground) {
                setRendererForeground(treeCellRendererComponent, this.selectionForeground);
            }
        }
        Color oldBorderSelectionColor = null;
        if (isSelected && hasFocus && (!this.showCellFocusIndicator || this.tree.getMinSelectionRow() == this.tree.getMaxSelectionRow())) {
            oldBorderSelectionColor = setRendererBorderSelectionColor(treeCellRendererComponent, null);
        } else if (hasFocus && this.selectionBorderColor != this.defaultSelectionBorderColor) {
            oldBorderSelectionColor = setRendererBorderSelectionColor(treeCellRendererComponent, this.selectionBorderColor);
        }
        if (needsSelectionPainting) {
            Color oldColor2 = g.getColor();
            if (isDropRow) {
                color = UIManager.getColor("Tree.dropCellBackground");
            } else if (treeCellRendererComponent instanceof DefaultTreeCellRenderer) {
                color = treeCellRendererComponent.getBackgroundSelectionColor();
            } else {
                color = hasFocus ? this.selectionBackground : this.selectionInactiveBackground;
            }
            g.setColor(color);
            if (isWideSelection()) {
                paintWideSelection(g, bounds, row);
            } else {
                paintCellBackground(g, treeCellRendererComponent, bounds, row, true);
            }
            g.setColor(oldColor2);
        } else if (treeCellRendererComponent instanceof DefaultTreeCellRenderer) {
            DefaultTreeCellRenderer renderer = treeCellRendererComponent;
            Color bg = renderer.getBackgroundNonSelectionColor();
            if (bg != null && !bg.equals(this.defaultCellNonSelectionBackground)) {
                Color oldColor3 = g.getColor();
                g.setColor(bg);
                paintCellBackground(g, treeCellRendererComponent, bounds, row, false);
                g.setColor(oldColor3);
            }
        }
        this.rendererPane.paintComponent(g, treeCellRendererComponent, this.tree, bounds.x, bounds.y, bounds.width, bounds.height, true);
        if (oldBackgroundSelectionColor != null) {
            treeCellRendererComponent.setBackgroundSelectionColor(oldBackgroundSelectionColor);
        }
        if (oldBorderSelectionColor != null) {
            treeCellRendererComponent.setBorderSelectionColor(oldBorderSelectionColor);
        }
    }

    private Color setRendererBackgroundSelectionColor(Component rendererComponent, Color color) {
        Color oldColor = null;
        if (rendererComponent instanceof DefaultTreeCellRenderer) {
            DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) rendererComponent;
            if (renderer.getBackgroundSelectionColor() == this.defaultSelectionBackground) {
                oldColor = renderer.getBackgroundSelectionColor();
                renderer.setBackgroundSelectionColor(color);
            }
        } else if (rendererComponent.getBackground() == this.defaultSelectionBackground) {
            rendererComponent.setBackground(color);
        }
        return oldColor;
    }

    private void setRendererForeground(Component rendererComponent, Color color) {
        if (rendererComponent.getForeground() == this.defaultSelectionForeground) {
            rendererComponent.setForeground(color);
        }
    }

    private Color setRendererBorderSelectionColor(Component rendererComponent, Color color) {
        Color oldColor = null;
        if (rendererComponent instanceof DefaultTreeCellRenderer) {
            DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) rendererComponent;
            if (renderer.getBorderSelectionColor() == this.defaultSelectionBorderColor) {
                oldColor = renderer.getBorderSelectionColor();
                renderer.setBorderSelectionColor(color);
            }
        }
        return oldColor;
    }

    private void paintWideSelection(Graphics g, Rectangle bounds, int row) {
        float scale = UIScale.scale(this.selectionArc / 2.0f);
        float arcBottom = scale;
        float arcTop = scale;
        if (useUnitedRoundedSelection()) {
            if (row > 0 && this.tree.isRowSelected(row - 1)) {
                arcTop = 0.0f;
            }
            if (row < this.tree.getRowCount() - 1 && this.tree.isRowSelected(row + 1)) {
                arcBottom = 0.0f;
            }
        }
        FlatUIUtils.paintSelection((Graphics2D) g, 0, bounds.y, this.tree.getWidth(), bounds.height, UIScale.scale(this.selectionInsets), arcTop, arcTop, arcBottom, arcBottom, 0);
    }

    private void paintCellBackground(Graphics g, Component rendererComponent, Rectangle bounds, int row, boolean paintSelection) {
        int i;
        int xOffset = 0;
        int imageOffset = 0;
        if (rendererComponent instanceof JLabel) {
            JLabel label = (JLabel) rendererComponent;
            Icon icon = label.isEnabled() ? label.getIcon() : label.getDisabledIcon();
            if (icon != null && label.getText() != null) {
                i = icon.getIconWidth() + Math.max(label.getIconTextGap() - 1, 0);
            } else {
                i = 0;
            }
            imageOffset = i;
            xOffset = label.getComponentOrientation().isLeftToRight() ? imageOffset : 0;
        }
        if (paintSelection) {
            float scale = UIScale.scale(this.selectionArc / 2.0f);
            float arcBottomRight = scale;
            float arcBottomLeft = scale;
            float arcTopRight = scale;
            float arcTopLeft = scale;
            if (useUnitedRoundedSelection()) {
                if (row > 0 && this.tree.isRowSelected(row - 1)) {
                    Rectangle r = getPathBounds(this.tree, this.tree.getPathForRow(row - 1));
                    arcTopLeft = Math.min(arcTopLeft, r.x - bounds.x);
                    arcTopRight = Math.min(arcTopRight, (bounds.x + bounds.width) - (r.x + r.width));
                }
                if (row < this.tree.getRowCount() - 1 && this.tree.isRowSelected(row + 1)) {
                    Rectangle r2 = getPathBounds(this.tree, this.tree.getPathForRow(row + 1));
                    arcBottomLeft = Math.min(arcBottomLeft, r2.x - bounds.x);
                    arcBottomRight = Math.min(arcBottomRight, (bounds.x + bounds.width) - (r2.x + r2.width));
                }
            }
            FlatUIUtils.paintSelection((Graphics2D) g, bounds.x + xOffset, bounds.y, bounds.width - imageOffset, bounds.height, UIScale.scale(this.selectionInsets), arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight, 0);
            return;
        }
        g.fillRect(bounds.x + xOffset, bounds.y, bounds.width - imageOffset, bounds.height);
    }

    private boolean useUnitedRoundedSelection() {
        return this.selectionArc > 0 && (this.selectionInsets == null || (this.selectionInsets.top == 0 && this.selectionInsets.bottom == 0));
    }

    protected void paintVerticalLine(Graphics g, JComponent c, int x, int top, int bottom) {
        ((Graphics2D) g).fill(new Rectangle2D.Float(x, top, UIScale.scale(1.0f), bottom - top));
    }

    protected void paintHorizontalLine(Graphics g, JComponent c, int y, int left, int right) {
        ((Graphics2D) g).fill(new Rectangle2D.Float(left, y, right - left, UIScale.scale(1.0f)));
    }

    private boolean isDropRow(int row) {
        JTree.DropLocation dropLocation = this.tree.getDropLocation();
        return dropLocation != null && dropLocation.getChildIndex() == -1 && this.tree.getRowForPath(dropLocation.getPath()) == row;
    }

    protected Rectangle getDropLineRect(JTree.DropLocation loc) {
        Rectangle r = super.getDropLineRect(loc);
        return isWideSelection() ? new Rectangle(0, r.y, this.tree.getWidth(), r.height) : r;
    }

    protected boolean isWideSelection() {
        return FlatClientProperties.clientPropertyBoolean(this.tree, FlatClientProperties.TREE_WIDE_SELECTION, this.wideSelection);
    }

    protected boolean isPaintSelection() {
        return FlatClientProperties.clientPropertyBoolean(this.tree, FlatClientProperties.TREE_PAINT_SELECTION, this.paintSelection);
    }
}
