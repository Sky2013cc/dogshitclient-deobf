package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.icons.FlatCheckBoxIcon;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatViewportUI;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTableUI.class */
public class FlatTableUI extends BasicTableUI implements FlatStylingSupport.StyleableUI, FlatViewportUI.ViewportPainter {
    protected boolean showHorizontalLines;
    protected boolean showVerticalLines;

    @FlatStylingSupport.Styleable
    protected boolean showTrailingVerticalLine;
    protected Dimension intercellSpacing;

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
    private boolean oldShowHorizontalLines;
    private boolean oldShowVerticalLines;
    private Dimension oldIntercellSpacing;
    private TableCellRenderer oldBooleanRenderer;
    private PropertyChangeListener propertyChangeListener;
    private ComponentListener outsideAlternateRowsListener;
    private ListSelectionListener rowSelectionListener;
    private TableColumnModelListener columnSelectionListener;
    private Map<String, Object> oldStyleValues;

    public static ComponentUI createUI(JComponent c) {
        return new FlatTableUI();
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        installStyle();
    }

    protected void installDefaults() {
        super.installDefaults();
        this.showHorizontalLines = UIManager.getBoolean("Table.showHorizontalLines");
        this.showVerticalLines = UIManager.getBoolean("Table.showVerticalLines");
        this.showTrailingVerticalLine = UIManager.getBoolean("Table.showTrailingVerticalLine");
        this.intercellSpacing = UIManager.getDimension("Table.intercellSpacing");
        this.selectionBackground = UIManager.getColor("Table.selectionBackground");
        this.selectionForeground = UIManager.getColor("Table.selectionForeground");
        this.selectionInactiveBackground = UIManager.getColor("Table.selectionInactiveBackground");
        this.selectionInactiveForeground = UIManager.getColor("Table.selectionInactiveForeground");
        this.selectionInsets = UIManager.getInsets("Table.selectionInsets");
        this.selectionArc = UIManager.getInt("Table.selectionArc");
        toggleSelectionColors();
        int rowHeight = FlatUIUtils.getUIInt("Table.rowHeight", 16);
        if (rowHeight > 0) {
            LookAndFeel.installProperty(this.table, "rowHeight", Integer.valueOf(UIScale.scale(rowHeight)));
        }
        FlatTablePropertyWatcher watcher = FlatTablePropertyWatcher.get(this.table);
        if (watcher != null) {
            watcher.enabled = false;
        }
        if (!this.showHorizontalLines && (watcher == null || !watcher.showHorizontalLinesChanged)) {
            this.oldShowHorizontalLines = this.table.getShowHorizontalLines();
            this.table.setShowHorizontalLines(false);
        }
        if (!this.showVerticalLines && (watcher == null || !watcher.showVerticalLinesChanged)) {
            this.oldShowVerticalLines = this.table.getShowVerticalLines();
            this.table.setShowVerticalLines(false);
        }
        if (this.intercellSpacing != null && (watcher == null || !watcher.intercellSpacingChanged)) {
            this.oldIntercellSpacing = this.table.getIntercellSpacing();
            this.table.setIntercellSpacing(this.intercellSpacing);
        }
        if (watcher != null) {
            watcher.enabled = true;
        } else {
            this.table.addPropertyChangeListener(new FlatTablePropertyWatcher());
        }
        this.oldBooleanRenderer = this.table.getDefaultRenderer(Boolean.class);
        if (this.oldBooleanRenderer instanceof UIResource) {
            this.table.setDefaultRenderer(Boolean.class, new FlatBooleanRenderer());
        } else {
            this.oldBooleanRenderer = null;
        }
    }

    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.selectionBackground = null;
        this.selectionForeground = null;
        this.selectionInactiveBackground = null;
        this.selectionInactiveForeground = null;
        this.oldStyleValues = null;
        FlatTablePropertyWatcher watcher = FlatTablePropertyWatcher.get(this.table);
        if (watcher != null) {
            watcher.enabled = false;
        }
        if (!this.showHorizontalLines && this.oldShowHorizontalLines && !this.table.getShowHorizontalLines() && (watcher == null || !watcher.showHorizontalLinesChanged)) {
            this.table.setShowHorizontalLines(true);
        }
        if (!this.showVerticalLines && this.oldShowVerticalLines && !this.table.getShowVerticalLines() && (watcher == null || !watcher.showVerticalLinesChanged)) {
            this.table.setShowVerticalLines(true);
        }
        if (this.intercellSpacing != null && this.table.getIntercellSpacing().equals(this.intercellSpacing) && (watcher == null || !watcher.intercellSpacingChanged)) {
            this.table.setIntercellSpacing(this.oldIntercellSpacing);
        }
        if (watcher != null) {
            watcher.enabled = true;
        }
        if (this.table.getDefaultRenderer(Boolean.class) instanceof FlatBooleanRenderer) {
            if (this.oldBooleanRenderer instanceof Component) {
                SwingUtilities.updateComponentTreeUI(this.oldBooleanRenderer);
            }
            this.table.setDefaultRenderer(Boolean.class, this.oldBooleanRenderer);
        }
        this.oldBooleanRenderer = null;
    }

    protected void installListeners() {
        super.installListeners();
        this.propertyChangeListener = e -> {
            String propertyName = e.getPropertyName();
            boolean z = -1;
            switch (propertyName.hashCode()) {
                case -2143440643:
                    if (propertyName.equals("selectionModel")) {
                        z = false;
                        break;
                    }
                    break;
                case -851518093:
                    if (propertyName.equals("columnModel")) {
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
                case 1859588534:
                    if (propertyName.equals(FlatClientProperties.COMPONENT_FOCUS_OWNER)) {
                        z = 2;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    if (this.rowSelectionListener != null) {
                        Object oldModel = e.getOldValue();
                        Object newModel = e.getNewValue();
                        if (oldModel != null) {
                            ((ListSelectionModel) oldModel).removeListSelectionListener(this.rowSelectionListener);
                        }
                        if (newModel != null) {
                            ((ListSelectionModel) newModel).addListSelectionListener(this.rowSelectionListener);
                            return;
                        }
                        return;
                    }
                    return;
                case true:
                    if (this.columnSelectionListener != null) {
                        Object oldModel2 = e.getOldValue();
                        Object newModel2 = e.getNewValue();
                        if (oldModel2 != null) {
                            ((TableColumnModel) oldModel2).removeColumnModelListener(this.columnSelectionListener);
                        }
                        if (newModel2 != null) {
                            ((TableColumnModel) newModel2).addColumnModelListener(this.columnSelectionListener);
                            return;
                        }
                        return;
                    }
                    return;
                case true:
                    toggleSelectionColors();
                    return;
                case true:
                case true:
                    installStyle();
                    this.table.revalidate();
                    HiDPIUtils.repaint(this.table);
                    return;
                default:
                    return;
            }
        };
        this.table.addPropertyChangeListener(this.propertyChangeListener);
        if (this.selectionArc > 0) {
            installRepaintRoundedSelectionListeners();
        }
    }

    protected void uninstallListeners() {
        super.uninstallListeners();
        this.table.removePropertyChangeListener(this.propertyChangeListener);
        this.propertyChangeListener = null;
        if (this.outsideAlternateRowsListener != null) {
            this.table.removeComponentListener(this.outsideAlternateRowsListener);
            this.outsideAlternateRowsListener = null;
        }
        if (this.rowSelectionListener != null) {
            this.table.getSelectionModel().removeListSelectionListener(this.rowSelectionListener);
            this.rowSelectionListener = null;
        }
        if (this.columnSelectionListener != null) {
            this.table.getColumnModel().removeColumnModelListener(this.columnSelectionListener);
            this.columnSelectionListener = null;
        }
    }

    protected FocusListener createFocusListener() {
        return new BasicTableUI.FocusHandler() { // from class: com.formdev.flatlaf.ui.FlatTableUI.1
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                FlatTableUI.this.toggleSelectionColors();
            }

            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                EventQueue.invokeLater(() -> {
                    FlatTableUI.this.toggleSelectionColors();
                });
            }
        };
    }

    protected void installKeyboardActions() {
        ActionMap map;
        super.installKeyboardActions();
        if (UIManager.getBoolean("Table.editorSelectAllOnStartEditing") && (map = SwingUtilities.getUIActionMap(this.table)) != null) {
            StartEditingAction.install(map, "startEditing");
        }
    }

    protected void installStyle() {
        try {
            applyStyle(FlatStylingSupport.getResolvedStyle(this.table, "Table"));
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
            Color selBg = this.table.getSelectionBackground();
            if (selBg == oldSelectionBackground) {
                this.table.setSelectionBackground(this.selectionBackground);
            } else if (selBg == oldSelectionInactiveBackground) {
                this.table.setSelectionBackground(this.selectionInactiveBackground);
            }
        }
        if (this.selectionForeground != oldSelectionForeground) {
            Color selFg = this.table.getSelectionForeground();
            if (selFg == oldSelectionForeground) {
                this.table.setSelectionForeground(this.selectionForeground);
            } else if (selFg == oldSelectionInactiveForeground) {
                this.table.setSelectionForeground(this.selectionInactiveForeground);
            }
        }
    }

    protected Object applyStyleProperty(String key, Object value) {
        if ("rowHeight".equals(key) && (value instanceof Integer)) {
            value = Integer.valueOf(UIScale.scale(((Integer) value).intValue()));
        } else if ("selectionArc".equals(key) && (value instanceof Integer) && ((Integer) value).intValue() > 0) {
            installRepaintRoundedSelectionListeners();
        }
        return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.table, key, value);
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
        if (this.table == null) {
            return;
        }
        if (FlatUIUtils.isPermanentFocusOwner(this.table)) {
            if (this.table.getSelectionBackground() == this.selectionInactiveBackground) {
                this.table.setSelectionBackground(this.selectionBackground);
            }
            if (this.table.getSelectionForeground() == this.selectionInactiveForeground) {
                this.table.setSelectionForeground(this.selectionForeground);
                return;
            }
            return;
        }
        if (this.table.getSelectionBackground() == this.selectionBackground) {
            this.table.setSelectionBackground(this.selectionInactiveBackground);
        }
        if (this.table.getSelectionForeground() == this.selectionForeground) {
            this.table.setSelectionForeground(this.selectionInactiveForeground);
        }
    }

    public void paint(Graphics g, JComponent c) {
        FlatTableHeaderUI.fixDraggedAndResizingColumns(this.table.getTableHeader());
        final boolean horizontalLines = this.table.getShowHorizontalLines();
        final boolean verticalLines = this.table.getShowVerticalLines();
        if (horizontalLines || verticalLines) {
            final boolean hideLastVerticalLine = hideLastVerticalLine();
            final int tableWidth = this.table.getWidth();
            JTableHeader header = this.table.getTableHeader();
            final boolean isDragging = (header == null || header.getDraggedColumn() == null) ? false : true;
            double systemScaleFactor = UIScale.getSystemScaleFactor((Graphics2D) g);
            final double lineThickness = (1.0d / systemScaleFactor) * ((int) systemScaleFactor);
            final double lineOffset = (1.0d - lineThickness) + 0.05d;
            g = new Graphics2DProxy((Graphics2D) g) { // from class: com.formdev.flatlaf.ui.FlatTableUI.2
                @Override // com.formdev.flatlaf.util.Graphics2DProxy
                public void drawLine(int x1, int y1, int x2, int y2) {
                    if (hideLastVerticalLine && verticalLines && x1 == x2 && y1 == 0 && x1 == tableWidth - 1 && wasInvokedFromPaintGrid()) {
                        return;
                    }
                    if (isDragging && SystemInfo.isJava_9_orLater && (((horizontalLines && y1 == y2) || (verticalLines && x1 == x2)) && wasInvokedFromMethod("paintDraggedArea"))) {
                        if (y1 == y2) {
                            super.fill(new Rectangle2D.Double(x1, y1, (x2 - x1) + 1, lineThickness));
                            return;
                        } else {
                            if (x1 == x2) {
                                super.fill(new Rectangle2D.Double(x1, y1, lineThickness, (y2 - y1) + 1));
                                return;
                            }
                            return;
                        }
                    }
                    super.drawLine(x1, y1, x2, y2);
                }

                @Override // com.formdev.flatlaf.util.Graphics2DProxy
                public void fillRect(int x, int y, int width, int height) {
                    if (hideLastVerticalLine && verticalLines && width == 1 && y == 0 && x == tableWidth - 1 && wasInvokedFromPaintGrid()) {
                        return;
                    }
                    if (lineThickness != 1.0d) {
                        if (horizontalLines && height == 1 && wasInvokedFromPaintGrid()) {
                            super.fill(new Rectangle2D.Double(x, y + lineOffset, width, lineThickness));
                            return;
                        } else if (verticalLines && width == 1 && y == 0 && wasInvokedFromPaintGrid()) {
                            super.fill(new Rectangle2D.Double(x + lineOffset, y, lineThickness, height));
                            return;
                        }
                    }
                    super.fillRect(x, y, width, height);
                }

                private boolean wasInvokedFromPaintGrid() {
                    return wasInvokedFromMethod("paintGrid");
                }

                private boolean wasInvokedFromMethod(String methodName) {
                    return StackUtils.wasInvokedFrom(BasicTableUI.class.getName(), methodName, 8);
                }
            };
        }
        if (this.selectionArc > 0 || (this.selectionInsets != null && !FlatUIUtils.isInsetsEmpty(this.selectionInsets))) {
            g = new RoundedSelectionGraphics(g, UIManager.getColor("Table.alternateRowColor"));
        }
        super.paint(g, c);
    }

    protected boolean hideLastVerticalLine() {
        if (this.showTrailingVerticalLine) {
            return false;
        }
        JViewport unwrappedParent = SwingUtilities.getUnwrappedParent(this.table);
        Container viewportParent = unwrappedParent != null ? unwrappedParent.getParent() : null;
        if (!(viewportParent instanceof JScrollPane) || this.table.getX() + this.table.getWidth() < unwrappedParent.getWidth()) {
            return false;
        }
        JScrollPane scrollPane = (JScrollPane) viewportParent;
        JViewport rowHeader = scrollPane.getRowHeader();
        return scrollPane.getComponentOrientation().isLeftToRight() ? unwrappedParent != rowHeader : unwrappedParent == rowHeader || rowHeader == null;
    }

    @Override // com.formdev.flatlaf.ui.FlatViewportUI.ViewportPainter
    public void paintViewport(Graphics g, JComponent c, JViewport viewport) {
        Color alternateColor;
        int viewportWidth = viewport.getWidth();
        int viewportHeight = viewport.getHeight();
        if (viewport.isOpaque()) {
            g.setColor(this.table.getBackground());
            g.fillRect(0, 0, viewportWidth, viewportHeight);
        }
        boolean paintOutside = UIManager.getBoolean("Table.paintOutsideAlternateRows");
        if (paintOutside && (alternateColor = UIManager.getColor("Table.alternateRowColor")) != null) {
            int rowCount = this.table.getRowCount();
            int tableHeight = this.table.getHeight();
            if (tableHeight < viewportHeight) {
                int tableWidth = this.table.getWidth();
                int rowHeight = this.table.getRowHeight();
                g.setColor(alternateColor);
                int x = viewport.getComponentOrientation().isLeftToRight() ? 0 : viewportWidth - tableWidth;
                int y = tableHeight;
                int row = rowCount;
                while (y < viewportHeight) {
                    if (row % 2 != 0) {
                        paintAlternateRowBackground(g, -1, -1, x, y, tableWidth, rowHeight);
                    }
                    y += rowHeight;
                    row++;
                }
                if (this.outsideAlternateRowsListener == null && this.table.getAutoResizeMode() == 0) {
                    this.outsideAlternateRowsListener = new FlatOutsideAlternateRowsListener();
                    this.table.addComponentListener(this.outsideAlternateRowsListener);
                }
            }
        }
    }

    protected void paintAlternateRowBackground(Graphics g, int row, int column, int x, int y, int width, int height) {
        Insets insets = this.selectionInsets != null ? (Insets) this.selectionInsets.clone() : null;
        float scale = UIScale.scale(this.selectionArc / 2.0f);
        float arcBottomRight = scale;
        float arcBottomLeft = scale;
        float arcTopRight = scale;
        float arcTopLeft = scale;
        if (column >= 0) {
            if (column > 0) {
                if (insets != null) {
                    insets.left = 0;
                }
                if (this.table.getComponentOrientation().isLeftToRight()) {
                    arcBottomLeft = 0.0f;
                    arcTopLeft = 0.0f;
                } else {
                    arcBottomRight = 0.0f;
                    arcTopRight = 0.0f;
                }
            }
            if (column < this.table.getColumnCount() - 1) {
                if (insets != null) {
                    insets.right = 0;
                }
                if (this.table.getComponentOrientation().isLeftToRight()) {
                    arcBottomRight = 0.0f;
                    arcTopRight = 0.0f;
                } else {
                    arcBottomLeft = 0.0f;
                    arcTopLeft = 0.0f;
                }
            }
        }
        FlatUIUtils.paintSelection((Graphics2D) g, x, y, width, height, UIScale.scale(insets), arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight, 0);
    }

    protected void paintCellSelection(Graphics g, int row, int column, int x, int y, int width, int height) {
        boolean rowSelAllowed = this.table.getRowSelectionAllowed();
        boolean colSelAllowed = this.table.getColumnSelectionAllowed();
        boolean rowSelOnly = rowSelAllowed && !colSelAllowed;
        boolean colSelOnly = colSelAllowed && !rowSelAllowed;
        boolean cellOnlySel = rowSelAllowed && colSelAllowed;
        boolean leftSelected = column > 0 && (rowSelOnly || this.table.isCellSelected(row, column - 1));
        boolean topSelected = row > 0 && (colSelOnly || this.table.isCellSelected(row - 1, column));
        boolean rightSelected = column < this.table.getColumnCount() - 1 && (rowSelOnly || this.table.isCellSelected(row, column + 1));
        boolean bottomSelected = row < this.table.getRowCount() - 1 && (colSelOnly || this.table.isCellSelected(row + 1, column));
        if (!this.table.getComponentOrientation().isLeftToRight()) {
            leftSelected = rightSelected;
            rightSelected = leftSelected;
        }
        Insets insets = this.selectionInsets != null ? (Insets) this.selectionInsets.clone() : null;
        if (insets != null) {
            if (rowSelOnly && leftSelected) {
                insets.left = 0;
            }
            if (rowSelOnly && rightSelected) {
                insets.right = 0;
            }
            if (colSelOnly && topSelected) {
                insets.top = 0;
            }
            if (colSelOnly && bottomSelected) {
                insets.bottom = 0;
            }
        }
        float scale = UIScale.scale(this.selectionArc / 2.0f);
        float arcBottomRight = scale;
        float arcBottomLeft = scale;
        float arcTopRight = scale;
        float arcTopLeft = scale;
        if (this.selectionArc > 0) {
            boolean hasRowGap = ((!rowSelOnly && !cellOnlySel) || insets == null || (insets.top == 0 && insets.bottom == 0)) ? false : true;
            boolean hasColGap = ((!colSelOnly && !cellOnlySel) || insets == null || (insets.left == 0 && insets.right == 0)) ? false : true;
            if (leftSelected && !hasColGap) {
                arcBottomLeft = 0.0f;
                arcTopLeft = 0.0f;
            }
            if (rightSelected && !hasColGap) {
                arcBottomRight = 0.0f;
                arcTopRight = 0.0f;
            }
            if (topSelected && !hasRowGap) {
                arcTopRight = 0.0f;
                arcTopLeft = 0.0f;
            }
            if (bottomSelected && !hasRowGap) {
                arcBottomRight = 0.0f;
                arcBottomLeft = 0.0f;
            }
        }
        FlatUIUtils.paintSelection((Graphics2D) g, x, y, width, height, UIScale.scale(insets), arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight, 0);
    }

    public static void paintCellSelection(JTable table, Graphics g, int row, int column, int x, int y, int width, int height) {
        if (!(table.getUI() instanceof FlatTableUI)) {
            return;
        }
        FlatTableUI ui = table.getUI();
        ui.paintCellSelection(g, row, column, x, y, width, height);
    }

    private void installRepaintRoundedSelectionListeners() {
        if (this.rowSelectionListener == null) {
            this.rowSelectionListener = this::repaintRoundedRowSelection;
            this.table.getSelectionModel().addListSelectionListener(this.rowSelectionListener);
        }
        if (this.columnSelectionListener == null) {
            this.columnSelectionListener = new TableColumnModelListener() { // from class: com.formdev.flatlaf.ui.FlatTableUI.3
                public void columnSelectionChanged(ListSelectionEvent e) {
                    FlatTableUI.this.repaintRoundedColumnSelection(e);
                }

                public void columnRemoved(TableColumnModelEvent e) {
                }

                public void columnMoved(TableColumnModelEvent e) {
                }

                public void columnMarginChanged(ChangeEvent e) {
                }

                public void columnAdded(TableColumnModelEvent e) {
                }
            };
            this.table.getColumnModel().addColumnModelListener(this.columnSelectionListener);
        }
    }

    private void repaintRoundedRowSelection(ListSelectionEvent e) {
        if (this.selectionArc <= 0 || !this.table.getRowSelectionAllowed()) {
            return;
        }
        int rowCount = this.table.getRowCount();
        int columnCount = this.table.getColumnCount();
        if (rowCount <= 0 || columnCount <= 0) {
            return;
        }
        int firstRow = Math.max(0, Math.min(e.getFirstIndex() - 1, rowCount - 1));
        int lastRow = Math.max(0, Math.min(e.getLastIndex() + 1, rowCount - 1));
        Rectangle firstRect = this.table.getCellRect(firstRow, 0, false);
        Rectangle lastRect = this.table.getCellRect(lastRow, columnCount - 1, false);
        this.table.repaint(firstRect.union(lastRect));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void repaintRoundedColumnSelection(ListSelectionEvent e) {
        if (this.selectionArc <= 0 || !this.table.getColumnSelectionAllowed()) {
            return;
        }
        int rowCount = this.table.getRowCount();
        int columnCount = this.table.getColumnCount();
        if (rowCount <= 0 || columnCount <= 0) {
            return;
        }
        int firstRow = 0;
        int lastRow = rowCount - 1;
        if (this.table.getRowSelectionAllowed()) {
            firstRow = this.table.getSelectionModel().getMinSelectionIndex();
            lastRow = this.table.getSelectionModel().getMaxSelectionIndex();
        }
        int firstColumn = Math.max(0, Math.min(e.getFirstIndex() - 1, columnCount - 1));
        int lastColumn = Math.max(0, Math.min(e.getLastIndex() + 1, columnCount - 1));
        Rectangle firstRect = this.table.getCellRect(firstRow, firstColumn, false);
        Rectangle lastRect = this.table.getCellRect(lastRow, lastColumn, false);
        this.table.repaint(firstRect.union(lastRect));
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTableUI$RoundedSelectionGraphics.class */
    private class RoundedSelectionGraphics extends Graphics2DProxy {
        private final Color alternateRowColor;
        private boolean inPaintSelection;

        RoundedSelectionGraphics(Graphics delegate, Color alternateRowColor) {
            super((Graphics2D) delegate);
            this.alternateRowColor = alternateRowColor;
        }

        @Override // com.formdev.flatlaf.util.Graphics2DProxy
        public Graphics create() {
            return new RoundedSelectionGraphics(super.create(), this.alternateRowColor);
        }

        @Override // com.formdev.flatlaf.util.Graphics2DProxy
        public Graphics create(int x, int y, int width, int height) {
            return new RoundedSelectionGraphics(super.create(x, y, width, height), this.alternateRowColor);
        }

        @Override // com.formdev.flatlaf.util.Graphics2DProxy
        public void fillRect(int x, int y, int width, int height) {
            if (fillCellSelection(x, y, width, height)) {
                return;
            }
            super.fillRect(x, y, width, height);
        }

        @Override // com.formdev.flatlaf.util.Graphics2DProxy
        public void fill(Shape shape) {
            if (shape instanceof Rectangle2D) {
                Rectangle2D r = (Rectangle2D) shape;
                double x = r.getX();
                double y = r.getY();
                double width = r.getWidth();
                double height = r.getHeight();
                if (x == ((int) x) && y == ((int) y) && width == ((int) width) && height == ((int) height) && fillCellSelection((int) x, (int) y, (int) width, (int) height)) {
                    return;
                }
            }
            super.fill(shape);
        }

        private boolean fillCellSelection(int x, int y, int width, int height) {
            Component rendererComponent;
            if (!this.inPaintSelection && x == 0 && y == 0) {
                Color color = getColor();
                if ((color == FlatTableUI.this.table.getSelectionBackground() || (this.alternateRowColor != null && color == this.alternateRowColor)) && (rendererComponent = findActiveRendererComponent()) != null && width == rendererComponent.getWidth() && height == rendererComponent.getHeight()) {
                    Point location = rendererComponent.getLocation();
                    int row = FlatTableUI.this.table.rowAtPoint(location);
                    int column = FlatTableUI.this.table.columnAtPoint(location);
                    if (row >= 0 && column >= 0) {
                        this.inPaintSelection = true;
                        if (color == FlatTableUI.this.table.getSelectionBackground()) {
                            FlatTableUI.this.paintCellSelection(this, row, column, x, y, width, height);
                        } else {
                            FlatTableUI.this.paintAlternateRowBackground(this, row, column, x, y, width, height);
                        }
                        this.inPaintSelection = false;
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        private Component findActiveRendererComponent() {
            int count = FlatTableUI.this.rendererPane.getComponentCount();
            for (int i = 0; i < count; i++) {
                Component c = FlatTableUI.this.rendererPane.getComponent(i);
                if (c.getWidth() > 0 && c.getHeight() > 0) {
                    return c;
                }
            }
            return null;
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTableUI$FlatOutsideAlternateRowsListener.class */
    private class FlatOutsideAlternateRowsListener extends ComponentAdapter {
        private FlatOutsideAlternateRowsListener() {
        }

        public void componentHidden(ComponentEvent e) {
            Container viewport = SwingUtilities.getUnwrappedParent(FlatTableUI.this.table);
            if (viewport instanceof JViewport) {
                HiDPIUtils.repaint(viewport);
            }
        }

        public void componentMoved(ComponentEvent e) {
            repaintAreaBelowTable();
        }

        public void componentResized(ComponentEvent e) {
            repaintAreaBelowTable();
        }

        private void repaintAreaBelowTable() {
            int viewportHeight;
            int tableHeight;
            Container viewport = SwingUtilities.getUnwrappedParent(FlatTableUI.this.table);
            if ((viewport instanceof JViewport) && (tableHeight = FlatTableUI.this.table.getHeight()) < (viewportHeight = viewport.getHeight())) {
                HiDPIUtils.repaint(viewport, 0, tableHeight, viewport.getWidth(), viewportHeight - tableHeight);
            }
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTableUI$FlatTablePropertyWatcher.class */
    private static class FlatTablePropertyWatcher implements PropertyChangeListener {
        boolean enabled;
        boolean showHorizontalLinesChanged;
        boolean showVerticalLinesChanged;
        boolean intercellSpacingChanged;

        private FlatTablePropertyWatcher() {
            this.enabled = true;
        }

        static FlatTablePropertyWatcher get(JTable table) {
            for (PropertyChangeListener l : table.getPropertyChangeListeners()) {
                if (l instanceof FlatTablePropertyWatcher) {
                    return (FlatTablePropertyWatcher) l;
                }
            }
            return null;
        }

        @Override // java.beans.PropertyChangeListener
        public void propertyChange(PropertyChangeEvent e) {
            if (!this.enabled) {
                return;
            }
            String propertyName = e.getPropertyName();
            boolean z = -1;
            switch (propertyName.hashCode()) {
                case -1072428756:
                    if (propertyName.equals("showVerticalLines")) {
                        z = true;
                        break;
                    }
                    break;
                case 377924734:
                    if (propertyName.equals("showHorizontalLines")) {
                        z = false;
                        break;
                    }
                    break;
                case 823320616:
                    if (propertyName.equals("rowMargin")) {
                        z = 2;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    this.showHorizontalLinesChanged = true;
                    return;
                case true:
                    this.showVerticalLinesChanged = true;
                    return;
                case true:
                    this.intercellSpacingChanged = true;
                    return;
                default:
                    return;
            }
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTableUI$FlatBooleanRenderer.class */
    private static class FlatBooleanRenderer extends DefaultTableCellRenderer implements UIResource {
        private boolean selected;

        FlatBooleanRenderer() {
            setHorizontalAlignment(0);
            setIcon(new FlatCheckBoxIcon() { // from class: com.formdev.flatlaf.ui.FlatTableUI.FlatBooleanRenderer.1
                @Override // com.formdev.flatlaf.icons.FlatCheckBoxIcon
                protected boolean isSelected(Component c) {
                    return FlatBooleanRenderer.this.selected;
                }
            });
        }

        protected void setValue(Object value) {
            this.selected = value != null && ((Boolean) value).booleanValue();
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTableUI$StartEditingAction.class */
    private static class StartEditingAction extends FlatUIAction {
        static void install(ActionMap map, String key) {
            Action oldAction = map.get(key);
            if (oldAction == null || (oldAction instanceof StartEditingAction)) {
                return;
            }
            map.put(key, new StartEditingAction(oldAction));
        }

        private StartEditingAction(Action delegate) {
            super(delegate);
        }

        public void actionPerformed(ActionEvent e) {
            JTable table = (JTable) e.getSource();
            Component oldEditorComp = table.getEditorComponent();
            this.delegate.actionPerformed(e);
            JTextField editorComponent = table.getEditorComponent();
            if (oldEditorComp == null && (editorComponent instanceof JTextField)) {
                editorComponent.selectAll();
            }
        }
    }
}
