package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.icons.FlatFileViewDirectoryIcon;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.ScaledImageIcon;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.function.Function;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.Scrollable;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicFileChooserUI;
import javax.swing.plaf.metal.MetalFileChooserUI;
import javax.swing.table.TableCellRenderer;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatFileChooserUI.class */
public class FlatFileChooserUI extends MetalFileChooserUI {
    private final FlatFileView fileView;
    private FlatShortcutsPanel shortcutsPanel;
    private JScrollPane shortcutsScrollPane;

    static /* synthetic */ boolean access$100() {
        return doNotUseSystemIcons();
    }

    public static ComponentUI createUI(JComponent c) {
        return new FlatFileChooserUI((JFileChooser) c);
    }

    public FlatFileChooserUI(JFileChooser filechooser) {
        super(filechooser);
        this.fileView = new FlatFileView();
    }

    public void installComponents(JFileChooser fc) {
        super.installComponents(fc);
        patchUI(fc);
        if (!UIManager.getBoolean("FileChooser.noPlacesBar")) {
            FlatShortcutsPanel panel = createShortcutsPanel(fc);
            if (panel.getComponentCount() > 0) {
                this.shortcutsPanel = panel;
                this.shortcutsScrollPane = new JScrollPane(this.shortcutsPanel, 20, 31);
                this.shortcutsScrollPane.setBorder(BorderFactory.createEmptyBorder());
                fc.add(this.shortcutsScrollPane, "Before");
                fc.addPropertyChangeListener(this.shortcutsPanel);
            }
        }
    }

    public void uninstallComponents(JFileChooser fc) {
        super.uninstallComponents(fc);
        if (this.shortcutsPanel != null) {
            fc.removePropertyChangeListener(this.shortcutsPanel);
            this.shortcutsPanel = null;
            this.shortcutsScrollPane = null;
        }
    }

    private void patchUI(JFileChooser fc) {
        int maximumRowCount;
        JPanel component = fc.getComponent(0);
        if ((component instanceof JPanel) && (component.getLayout() instanceof BorderLayout)) {
            JPanel component2 = component.getComponent(0);
            if ((component2 instanceof JPanel) && (component2.getLayout() instanceof BoxLayout)) {
                Insets margin = UIManager.getInsets("Button.margin");
                AbstractButton[] components = component2.getComponents();
                for (int i = components.length - 1; i >= 0; i--) {
                    AbstractButton abstractButton = components[i];
                    if ((abstractButton instanceof JButton) || (abstractButton instanceof JToggleButton)) {
                        AbstractButton b = abstractButton;
                        b.putClientProperty(FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON);
                        b.setMargin(margin);
                        b.setFocusable(false);
                    } else if (abstractButton instanceof Box.Filler) {
                        component2.remove(i);
                    }
                }
            }
        }
        try {
            JComboBox component3 = component.getComponent(2);
            if ((component3 instanceof JComboBox) && (maximumRowCount = UIManager.getInt("ComboBox.maximumRowCount")) > 0) {
                component3.setMaximumRowCount(maximumRowCount);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        BorderLayout layout = fc.getLayout();
        if (layout instanceof BorderLayout) {
            BorderLayout borderLayout = layout;
            borderLayout.setHgap(8);
            Component north = borderLayout.getLayoutComponent("North");
            Component lineEnd = borderLayout.getLayoutComponent("After");
            Component center = borderLayout.getLayoutComponent("Center");
            Component south = borderLayout.getLayoutComponent("South");
            if (north != null && lineEnd != null && center != null && south != null) {
                JPanel p = new JPanel(new BorderLayout(0, 11));
                p.add(north, "North");
                p.add(lineEnd, "After");
                p.add(center, "Center");
                p.add(south, "South");
                fc.add(p, "Center");
            }
        }
    }

    protected JPanel createDetailsView(JFileChooser fc) {
        JPanel p = super.createDetailsView(fc);
        if (!SystemInfo.isWindows) {
            return p;
        }
        JScrollPane scrollPane = null;
        JScrollPane[] components = p.getComponents();
        int length = components.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            JScrollPane jScrollPane = components[i];
            if (!(jScrollPane instanceof JScrollPane)) {
                i++;
            } else {
                scrollPane = jScrollPane;
                break;
            }
        }
        if (scrollPane == null) {
            return p;
        }
        JTable view = scrollPane.getViewport().getView();
        if (!(view instanceof JTable)) {
            return p;
        }
        JTable table = view;
        final TableCellRenderer defaultRenderer = table.getDefaultRenderer(Object.class);
        table.setDefaultRenderer(Object.class, new TableCellRenderer() { // from class: com.formdev.flatlaf.ui.FlatFileChooserUI.1
            public Component getTableCellRendererComponent(JTable table2, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if ((value instanceof String) && ((String) value).startsWith("\u200e")) {
                    String str = (String) value;
                    char[] buf = new char[str.length()];
                    int j = 0;
                    for (int i2 = 0; i2 < buf.length; i2++) {
                        char ch = str.charAt(i2);
                        if (ch != 8206 && ch != 8207) {
                            int i3 = j;
                            j++;
                            buf[i3] = ch;
                        }
                    }
                    value = new String(buf, 0, j);
                }
                return defaultRenderer.getTableCellRendererComponent(table2, value, isSelected, hasFocus, row, column);
            }
        });
        return p;
    }

    protected FlatShortcutsPanel createShortcutsPanel(JFileChooser fc) {
        return new FlatShortcutsPanel(fc);
    }

    public Dimension getPreferredSize(JComponent c) {
        Dimension prefSize = super.getPreferredSize(c);
        Dimension minSize = getMinimumSize(c);
        int shortcutsPanelWidth = this.shortcutsScrollPane != null ? this.shortcutsScrollPane.getPreferredSize().width : 0;
        return new Dimension(Math.max(prefSize.width, minSize.width + shortcutsPanelWidth), Math.max(prefSize.height, minSize.height));
    }

    public Dimension getMinimumSize(JComponent c) {
        return UIScale.scale(super.getMinimumSize(c));
    }

    public FileView getFileView(JFileChooser fc) {
        return doNotUseSystemIcons() ? super.getFileView(fc) : this.fileView;
    }

    public void clearIconCache() {
        if (doNotUseSystemIcons()) {
            super.clearIconCache();
        } else {
            this.fileView.clearIconCache();
        }
    }

    private static boolean doNotUseSystemIcons() {
        return SystemInfo.isWindows && SystemInfo.isX86 && SystemInfo.isJava_17_orLater && SystemInfo.javaVersion < SystemInfo.toVersion(17, 0, 3, 0);
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatFileChooserUI$FlatFileView.class */
    private class FlatFileView extends BasicFileChooserUI.BasicFileView {
        private FlatFileView() {
            super(FlatFileChooserUI.this);
        }

        public Icon getIcon(File f) {
            Icon icon = getCachedIcon(f);
            if (icon != null) {
                return icon;
            }
            if (f != null) {
                try {
                    icon = FlatFileChooserUI.this.getFileChooser().getFileSystemView().getSystemIcon(f);
                } catch (NullPointerException e) {
                }
                if (icon != null) {
                    if (icon instanceof ImageIcon) {
                        icon = new ScaledImageIcon((ImageIcon) icon);
                    }
                    cacheIcon(f, icon);
                    return icon;
                }
            }
            Icon icon2 = super.getIcon(f);
            if (icon2 instanceof ImageIcon) {
                icon2 = new ScaledImageIcon((ImageIcon) icon2);
                cacheIcon(f, icon2);
            }
            return icon2;
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatFileChooserUI$FlatShortcutsPanel.class */
    public static class FlatShortcutsPanel extends JToolBar implements PropertyChangeListener, Scrollable {
        private final JFileChooser fc;
        private final Dimension buttonSize;
        private final Dimension iconSize;
        private final Function<File[], File[]> filesFunction;
        private final Function<File, String> displayNameFunction;
        private final Function<File, Icon> iconFunction;
        protected final File[] files;
        protected final JToggleButton[] buttons;
        protected final ButtonGroup buttonGroup;

        public FlatShortcutsPanel(JFileChooser fc) {
            super(1);
            this.buttonGroup = new ButtonGroup();
            this.fc = fc;
            setFloatable(false);
            putClientProperty(FlatClientProperties.STYLE, "hoverButtonGroupBackground: null");
            this.buttonSize = UIScale.scale(getUIDimension("FileChooser.shortcuts.buttonSize", 84, 64));
            this.iconSize = getUIDimension("FileChooser.shortcuts.iconSize", 32, 32);
            this.filesFunction = (Function) UIManager.get("FileChooser.shortcuts.filesFunction");
            this.displayNameFunction = (Function) UIManager.get("FileChooser.shortcuts.displayNameFunction");
            this.iconFunction = (Function) UIManager.get("FileChooser.shortcuts.iconFunction");
            FileSystemView fsv = fc.getFileSystemView();
            File[] files = JavaCompatibility2.getChooserShortcutPanelFiles(fsv);
            files = this.filesFunction != null ? this.filesFunction.apply(files) : files;
            ArrayList<File> filesList = new ArrayList<>();
            ArrayList<JToggleButton> buttonsList = new ArrayList<>();
            for (File file : files) {
                if (file != null) {
                    file = fsv.isFileSystemRoot(file) ? fsv.createFileObject(file.getAbsolutePath()) : file;
                    String name = getDisplayName(fsv, file);
                    Icon icon = getIcon(fsv, file);
                    if (name != null) {
                        int lastSepIndex = name.lastIndexOf(File.separatorChar);
                        if (lastSepIndex >= 0 && lastSepIndex < name.length() - 1) {
                            name = name.substring(lastSepIndex + 1);
                        }
                        if (icon instanceof ImageIcon) {
                            icon = new ScaledImageIcon((ImageIcon) icon, this.iconSize.width, this.iconSize.height);
                        } else if (icon != null) {
                            icon = new ShortcutIcon(icon, this.iconSize.width, this.iconSize.height);
                        }
                        JToggleButton button = createButton(name, icon, file.toString());
                        File f = file;
                        button.addActionListener(e -> {
                            fc.setCurrentDirectory(f);
                        });
                        add(button);
                        this.buttonGroup.add(button);
                        filesList.add(file);
                        buttonsList.add(button);
                    }
                }
            }
            this.files = (File[]) filesList.toArray(new File[filesList.size()]);
            this.buttons = (JToggleButton[]) buttonsList.toArray(new JToggleButton[buttonsList.size()]);
            directoryChanged(fc.getCurrentDirectory());
        }

        private Dimension getUIDimension(String key, int defaultWidth, int defaultHeight) {
            Dimension size = UIManager.getDimension(key);
            if (size == null) {
                size = new Dimension(defaultWidth, defaultHeight);
            }
            return size;
        }

        protected JToggleButton createButton(String name, Icon icon, String toolTip) {
            JToggleButton button = new JToggleButton(name, icon);
            button.setToolTipText(toolTip);
            button.setVerticalTextPosition(3);
            button.setHorizontalTextPosition(0);
            button.setAlignmentX(0.5f);
            button.setIconTextGap(0);
            button.setPreferredSize(this.buttonSize);
            button.setMaximumSize(this.buttonSize);
            return button;
        }

        protected String getDisplayName(FileSystemView fsv, File file) {
            String name;
            if (this.displayNameFunction != null && (name = this.displayNameFunction.apply(file)) != null) {
                return name;
            }
            return fsv.getSystemDisplayName(file);
        }

        protected Icon getIcon(FileSystemView fsv, File file) {
            Icon icon;
            if (this.iconFunction != null && (icon = this.iconFunction.apply(file)) != null) {
                return icon;
            }
            try {
                if (FlatFileChooserUI.access$100()) {
                    return new FlatFileViewDirectoryIcon();
                }
                try {
                } catch (Exception ex) {
                    if (!"java.lang.reflect.InaccessibleObjectException".equals(ex.getClass().getName())) {
                        LoggingFacade.INSTANCE.logSevere(null, ex);
                    }
                }
                if (SystemInfo.isJava_17_orLater) {
                    return (Icon) fsv.getClass().getMethod("getSystemIcon", File.class, Integer.TYPE, Integer.TYPE).invoke(fsv, file, Integer.valueOf(this.iconSize.width), Integer.valueOf(this.iconSize.height));
                }
                if (this.iconSize.width > 16 || this.iconSize.height > 16) {
                    Class<?> cls = Class.forName("sun.awt.shell.ShellFolder");
                    if (cls.isInstance(file)) {
                        Method m = file.getClass().getMethod("getIcon", Boolean.TYPE);
                        m.setAccessible(true);
                        Image image = (Image) m.invoke(file, true);
                        if (image != null) {
                            return new ImageIcon(image);
                        }
                    }
                }
                return fsv.getSystemIcon(file);
            } catch (NullPointerException e) {
                return new FlatFileViewDirectoryIcon();
            }
        }

        protected void directoryChanged(File file) {
            if (file != null) {
                String absolutePath = file.getAbsolutePath();
                for (int i = 0; i < this.files.length; i++) {
                    if (this.files[i].equals(file) || this.files[i].getAbsolutePath().equals(absolutePath)) {
                        this.buttons[i].setSelected(true);
                        return;
                    }
                }
            }
            this.buttonGroup.clearSelection();
        }

        @Override // java.beans.PropertyChangeListener
        public void propertyChange(PropertyChangeEvent e) {
            String propertyName = e.getPropertyName();
            boolean z = -1;
            switch (propertyName.hashCode()) {
                case -1295632505:
                    if (propertyName.equals("directoryChanged")) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    directoryChanged(this.fc.getCurrentDirectory());
                    return;
                default:
                    return;
            }
        }

        public Dimension getPreferredScrollableViewportSize() {
            if (getComponentCount() > 0) {
                Insets insets = getInsets();
                int height = (getComponent(0).getPreferredSize().height * 5) + insets.top + insets.bottom;
                return new Dimension(getPreferredSize().width, height);
            }
            return getPreferredSize();
        }

        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
            if (orientation == 1 && getComponentCount() > 0) {
                return getComponent(0).getPreferredSize().height;
            }
            return getScrollableBlockIncrement(visibleRect, orientation, direction) / 10;
        }

        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
            return orientation == 1 ? visibleRect.height : visibleRect.width;
        }

        public boolean getScrollableTracksViewportWidth() {
            return true;
        }

        public boolean getScrollableTracksViewportHeight() {
            return false;
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatFileChooserUI$ShortcutIcon.class */
    private static class ShortcutIcon implements Icon {
        private final Icon icon;
        private final int iconWidth;
        private final int iconHeight;

        ShortcutIcon(Icon icon, int iconWidth, int iconHeight) {
            this.icon = icon;
            this.iconWidth = iconWidth;
            this.iconHeight = iconHeight;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = g.create();
            try {
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                double scale = getIconWidth() / this.icon.getIconWidth();
                g2.translate(x, y);
                g2.scale(scale, scale);
                this.icon.paintIcon(c, g2, 0, 0);
                g2.dispose();
            } catch (Throwable th) {
                g2.dispose();
                throw th;
            }
        }

        public int getIconWidth() {
            return UIScale.scale(this.iconWidth);
        }

        public int getIconHeight() {
            return UIScale.scale(this.iconHeight);
        }
    }
}
