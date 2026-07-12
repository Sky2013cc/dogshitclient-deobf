package com.formdev.flatlaf;

import com.formdev.flatlaf.ui.FlatNativeWindowBorder;
import com.formdev.flatlaf.ui.FlatPopupFactory;
import com.formdev.flatlaf.ui.FlatRootPaneUI;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.JavaCompatibility2;
import com.formdev.flatlaf.util.FontUtils;
import com.formdev.flatlaf.util.GrayFilter;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.MultiResolutionImageSupport;
import com.formdev.flatlaf.util.StringUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.LookAndFeel;
import javax.swing.PopupFactory;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.IconUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.text.StyleContext;
import javax.swing.text.html.HTMLEditorKit;

/* loaded from: target.jar:com/formdev/flatlaf/FlatLaf.class */
public abstract class FlatLaf extends BasicLookAndFeel {
    private static final String DESKTOPFONTHINTS = "awt.font.desktophints";
    private static List<Object> customDefaultsSources;
    private static Map<String, String> globalExtraDefaults;
    private Map<String, String> extraDefaults;
    private static Function<String, Color> systemColorGetter;
    private String desktopPropertyName;
    private String desktopPropertyName2;
    private PropertyChangeListener desktopPropertyListener;
    private static boolean aquaLoaded;
    private static boolean updateUIPending;
    private PopupFactory oldPopupFactory;
    private MnemonicHandler mnemonicHandler;
    private boolean subMenuUsabilityHelperInstalled;
    private Consumer<UIDefaults> postInitialization;
    private List<Function<Object, Object>> uiDefaultsGetters;
    private static String preferredFontFamily;
    private static String preferredLightFontFamily;
    private static String preferredSemiboldFontFamily;
    private static String preferredMonospacedFontFamily;
    public static final Object NULL_VALUE;

    /* loaded from: target.jar:com/formdev/flatlaf/FlatLaf$DisabledIconProvider.class */
    public interface DisabledIconProvider {
        Icon getDisabledIcon();
    }

    public abstract boolean isDark();

    static {
        if (SystemInfo.isWindows && System.getProperty("sun.java2d.d3d.onscreen") == null && System.getProperty("sun.java2d.d3d") == null && System.getProperty("sun.java2d.noddraw") == null) {
            System.setProperty("sun.java2d.d3d.onscreen", Constants.FALSE);
        }
        NULL_VALUE = new Object();
    }

    public static void disableWindowsD3Donscreen() {
    }

    public static boolean setup(LookAndFeel newLookAndFeel) {
        try {
            UIManager.setLookAndFeel(newLookAndFeel);
            return true;
        } catch (Exception ex) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to setup look and feel '" + newLookAndFeel.getClass().getName() + "'.", ex);
            return false;
        }
    }

    @Deprecated
    public static boolean install(LookAndFeel newLookAndFeel) {
        return setup(newLookAndFeel);
    }

    public static void installLafInfo(String lafName, Class<? extends LookAndFeel> lafClass) {
        UIManager.installLookAndFeel(new UIManager.LookAndFeelInfo(lafName, lafClass.getName()));
    }

    public String getID() {
        return "FlatLaf - " + getName();
    }

    public static boolean isLafDark() {
        FlatLaf lookAndFeel = UIManager.getLookAndFeel();
        return (lookAndFeel instanceof FlatLaf) && lookAndFeel.isDark();
    }

    public boolean getSupportsWindowDecorations() {
        if (SystemInfo.isProjector || SystemInfo.isWebswing || SystemInfo.isWinPE) {
            return false;
        }
        if (SystemInfo.isWindows_10_orLater && FlatNativeWindowBorder.isSupported()) {
            return false;
        }
        return SystemInfo.isWindows_10_orLater || SystemInfo.isLinux;
    }

    public boolean isNativeLookAndFeel() {
        return false;
    }

    public boolean isSupportedLookAndFeel() {
        return true;
    }

    public Icon getDisabledIcon(JComponent component, Icon icon) {
        ImageFilter createDisabledIconFilter;
        if (icon instanceof DisabledIconProvider) {
            Icon disabledIcon = ((DisabledIconProvider) icon).getDisabledIcon();
            return !(disabledIcon instanceof UIResource) ? new IconUIResource(disabledIcon) : disabledIcon;
        }
        if (icon instanceof ImageIcon) {
            Object grayFilter = UIManager.get("Component.grayFilter");
            if (grayFilter instanceof ImageFilter) {
                createDisabledIconFilter = (ImageFilter) grayFilter;
            } else {
                createDisabledIconFilter = GrayFilter.createDisabledIconFilter(isDark());
            }
            ImageFilter filter = createDisabledIconFilter;
            Function<Image, Image> mapper = img -> {
                return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), filter));
            };
            Image image = ((ImageIcon) icon).getImage();
            return new ImageIconUIResource(MultiResolutionImageSupport.map(image, mapper));
        }
        return null;
    }

    public void initialize() {
        if (UIManager.getLookAndFeel() != this) {
            return;
        }
        if (SystemInfo.isMacOS) {
            initializeAqua();
        }
        super.initialize();
        this.oldPopupFactory = PopupFactory.getSharedInstance();
        PopupFactory.setSharedInstance(new FlatPopupFactory());
        this.mnemonicHandler = new MnemonicHandler();
        this.mnemonicHandler.install();
        this.subMenuUsabilityHelperInstalled = SubMenuUsabilityHelper.install();
        if (SystemInfo.isWindows) {
            this.desktopPropertyName = "win.messagebox.font";
        } else if (SystemInfo.isLinux) {
            this.desktopPropertyName = "gnome.Gtk/FontName";
            this.desktopPropertyName2 = "gnome.Xft/DPI";
        }
        if (this.desktopPropertyName != null) {
            this.desktopPropertyListener = e -> {
                if (!FlatSystemProperties.getBoolean(FlatSystemProperties.UPDATE_UI_ON_SYSTEM_FONT_CHANGE, true)) {
                    return;
                }
                String propertyName = e.getPropertyName();
                if (this.desktopPropertyName.equals(propertyName) || propertyName.equals(this.desktopPropertyName2)) {
                    reSetLookAndFeel();
                } else if (DESKTOPFONTHINTS.equals(propertyName) && (UIManager.getLookAndFeel() instanceof FlatLaf)) {
                    putAATextInfo(UIManager.getLookAndFeelDefaults());
                    updateUILater();
                }
            };
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.getDesktopProperty("dummy");
            toolkit.addPropertyChangeListener(this.desktopPropertyName, this.desktopPropertyListener);
            if (this.desktopPropertyName2 != null) {
                toolkit.addPropertyChangeListener(this.desktopPropertyName2, this.desktopPropertyListener);
            }
            toolkit.addPropertyChangeListener(DESKTOPFONTHINTS, this.desktopPropertyListener);
        }
        this.postInitialization = defaults -> {
            Color linkColor = defaults.getColor("Component.linkColor");
            if (linkColor != null) {
                new HTMLEditorKit().getStyleSheet().addRule(String.format("a, address { color: #%06x; }", Integer.valueOf(linkColor.getRGB() & 16777215)));
            }
        };
    }

    public void uninitialize() {
        if (UIManager.getLookAndFeel() != this) {
            return;
        }
        if (this.desktopPropertyListener != null) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.removePropertyChangeListener(this.desktopPropertyName, this.desktopPropertyListener);
            if (this.desktopPropertyName2 != null) {
                toolkit.removePropertyChangeListener(this.desktopPropertyName2, this.desktopPropertyListener);
            }
            toolkit.removePropertyChangeListener(DESKTOPFONTHINTS, this.desktopPropertyListener);
            this.desktopPropertyName = null;
            this.desktopPropertyName2 = null;
            this.desktopPropertyListener = null;
        }
        if (this.oldPopupFactory != null) {
            PopupFactory.setSharedInstance(this.oldPopupFactory);
            this.oldPopupFactory = null;
        }
        if (this.mnemonicHandler != null) {
            this.mnemonicHandler.uninstall();
            this.mnemonicHandler = null;
        }
        if (this.subMenuUsabilityHelperInstalled) {
            SubMenuUsabilityHelper.uninstall();
            this.subMenuUsabilityHelperInstalled = false;
        }
        new HTMLEditorKit().getStyleSheet().addRule("a, address { color: blue; }");
        this.postInitialization = null;
        super.uninitialize();
    }

    private void initializeAqua() {
        BasicLookAndFeel aquaLaf;
        if (aquaLoaded) {
            return;
        }
        aquaLoaded = true;
        try {
            if (SystemInfo.isJava_9_orLater) {
                Method m = UIManager.class.getMethod("createLookAndFeel", String.class);
                aquaLaf = (BasicLookAndFeel) m.invoke(null, "Mac OS X");
            } else {
                aquaLaf = (BasicLookAndFeel) Class.forName("com.apple.laf.AquaLookAndFeel").asSubclass(BasicLookAndFeel.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            }
            PopupFactory oldPopupFactory = PopupFactory.getSharedInstance();
            aquaLaf.initialize();
            aquaLaf.uninitialize();
            PopupFactory.setSharedInstance(oldPopupFactory);
        } catch (Exception ex) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to initialize Aqua look and feel 'com.apple.laf.AquaLookAndFeel'.", ex);
            throw new IllegalStateException();
        }
    }

    public UIDefaults getDefaults() {
        UIDefaults defaults = new FlatUIDefaults(1500, 0.75f);
        initClassDefaults(defaults);
        initSystemColorDefaults(defaults);
        initComponentDefaults(defaults);
        defaults.put("laf.dark", Boolean.valueOf(isDark()));
        initResourceBundle(defaults, "com.formdev.flatlaf.resources.Bundle");
        putDefaults(defaults, defaults.getColor("control"), "Button.disabledBackground", "EditorPane.disabledBackground", "EditorPane.inactiveBackground", "FormattedTextField.disabledBackground", "PasswordField.disabledBackground", "RootPane.background", "Spinner.disabledBackground", "TextArea.disabledBackground", "TextArea.inactiveBackground", "TextField.disabledBackground", "TextPane.disabledBackground", "TextPane.inactiveBackground", "ToggleButton.disabledBackground");
        putDefaults(defaults, defaults.getColor("textInactiveText"), "Button.disabledText", "CheckBox.disabledText", "CheckBoxMenuItem.disabledForeground", "Menu.disabledForeground", "MenuItem.disabledForeground", "RadioButton.disabledText", "RadioButtonMenuItem.disabledForeground", "Spinner.disabledForeground", "ToggleButton.disabledText");
        putDefaults(defaults, defaults.getColor("textText"), "DesktopIcon.foreground", "RootPane.foreground");
        initFonts(defaults);
        initIconColors(defaults, isDark());
        FlatInputMaps.initInputMaps(defaults);
        Object icon = defaults.remove("InternalFrame.icon");
        defaults.put("InternalFrame.icon", icon);
        defaults.put("TitlePane.icon", icon);
        ServiceLoader<FlatDefaultsAddon> addonLoader = ServiceLoader.load(FlatDefaultsAddon.class);
        List<FlatDefaultsAddon> addons = new ArrayList<>();
        Iterator<FlatDefaultsAddon> it = addonLoader.iterator();
        while (it.hasNext()) {
            FlatDefaultsAddon addon = it.next();
            addons.add(addon);
        }
        addons.sort((addon1, addon2) -> {
            return addon1.getPriority() - addon2.getPriority();
        });
        List<Class<?>> lafClassesForDefaultsLoading = getLafClassesForDefaultsLoading();
        if (lafClassesForDefaultsLoading != null) {
            UIDefaultsLoader.loadDefaultsFromProperties(lafClassesForDefaultsLoading, addons, getAdditionalDefaults(), isDark(), defaults);
        } else {
            UIDefaultsLoader.loadDefaultsFromProperties(getClass(), addons, getAdditionalDefaults(), isDark(), defaults);
        }
        initDefaultFont(defaults);
        if (SystemInfo.isMacOS && Boolean.getBoolean("apple.laf.useScreenMenuBar")) {
            defaults.put("MenuBarUI", "com.apple.laf.AquaMenuBarUI");
            defaults.put("MenuBar.backgroundPainter", BorderFactory.createEmptyBorder());
        }
        putAATextInfo(defaults);
        applyAdditionalDefaults(defaults);
        for (FlatDefaultsAddon addon3 : addons) {
            addon3.afterDefaultsLoading(this, defaults);
        }
        defaults.put("laf.scaleFactor", t -> {
            return Float.valueOf(UIScale.getUserScaleFactor());
        });
        if (this.postInitialization != null) {
            this.postInitialization.accept(defaults);
            this.postInitialization = null;
        }
        return defaults;
    }

    void applyAdditionalDefaults(UIDefaults defaults) {
    }

    protected List<Class<?>> getLafClassesForDefaultsLoading() {
        return null;
    }

    protected Properties getAdditionalDefaults() {
        if (globalExtraDefaults == null && this.extraDefaults == null) {
            return null;
        }
        Properties properties = new Properties();
        if (globalExtraDefaults != null) {
            properties.putAll(globalExtraDefaults);
        }
        if (this.extraDefaults != null) {
            properties.putAll(this.extraDefaults);
        }
        return properties;
    }

    private void initResourceBundle(UIDefaults defaults, String bundleName) {
        defaults.addResourceBundle(bundleName);
        if (defaults.get("TabbedPane.moreTabsButtonToolTipText") != null) {
            return;
        }
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName, defaults.getDefaultLocale());
            Enumeration<String> keys = bundle.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                String value = bundle.getString(key);
                String baseKey = StringUtils.removeTrailing(key, ".textAndMnemonic");
                if (baseKey != key) {
                    String text = value.replace("&", "");
                    String mnemonic = null;
                    int index = value.indexOf(38);
                    if (index >= 0) {
                        mnemonic = Integer.toString(Character.toUpperCase(value.charAt(index + 1)));
                    }
                    defaults.put(baseKey + "Text", text);
                    if (mnemonic != null) {
                        defaults.put(baseKey + "Mnemonic", mnemonic);
                    }
                } else {
                    defaults.put(key, value);
                }
            }
        } catch (MissingResourceException ex) {
            LoggingFacade.INSTANCE.logSevere(null, ex);
        }
    }

    private void initFonts(UIDefaults defaults) {
        Object activeFont = new ActiveFont(null, null, -1, 0, 0, 0, 0.0f);
        List<String> fontKeys = new ArrayList<>(50);
        for (Object key : defaults.keySet()) {
            if ((key instanceof String) && (((String) key).endsWith(".font") || ((String) key).endsWith("Font"))) {
                fontKeys.add((String) key);
            }
        }
        Iterator<String> it = fontKeys.iterator();
        while (it.hasNext()) {
            defaults.put(it.next(), activeFont);
        }
        defaults.put("RootPane.font", activeFont);
        defaults.put("TitlePane.font", activeFont);
    }

    private void initDefaultFont(UIDefaults defaults) {
        String fontName;
        FontUIResource uiFont = null;
        if (SystemInfo.isWindows) {
            Font winFont = (Font) Toolkit.getDefaultToolkit().getDesktopProperty("win.messagebox.font");
            if (winFont != null) {
                if (SystemInfo.isWinPE) {
                    Font winPEFont = (Font) Toolkit.getDefaultToolkit().getDesktopProperty("win.defaultGUI.font");
                    if (winPEFont != null) {
                        uiFont = createCompositeFont(winPEFont.getFamily(), winPEFont.getStyle(), winFont.getSize());
                    }
                } else {
                    uiFont = createCompositeFont(winFont.getFamily(), winFont.getStyle(), winFont.getSize());
                }
            }
        } else if (SystemInfo.isMacOS) {
            if (SystemInfo.isMacOS_10_15_Catalina_orLater) {
                if (SystemInfo.isJetBrainsJVM_11_orLater) {
                    fontName = ".AppleSystemUIFont";
                } else {
                    fontName = "Helvetica Neue";
                }
            } else if (SystemInfo.isMacOS_10_11_ElCapitan_orLater) {
                fontName = ".SF NS Text";
            } else {
                fontName = "Lucida Grande";
            }
            uiFont = createCompositeFont(fontName, 0, 13);
        } else if (SystemInfo.isLinux) {
            FontUIResource font = LinuxFontPolicy.getFont();
            uiFont = font instanceof FontUIResource ? font : new FontUIResource(font);
        }
        if (uiFont == null) {
            uiFont = createCompositeFont("SansSerif", 0, 12);
        }
        if (preferredFontFamily != null) {
            FontUIResource preferredFont = createCompositeFont(preferredFontFamily, uiFont.getStyle(), uiFont.getSize());
            if (!ActiveFont.isFallbackFont(preferredFont) || ActiveFont.isDialogFamily(preferredFontFamily)) {
                uiFont = preferredFont;
            }
        }
        Object defaultFont = defaults.remove("defaultFont");
        if (defaultFont instanceof ActiveFont) {
            FontUIResource fontUIResource = uiFont;
            uiFont = ((ActiveFont) defaultFont).derive(fontUIResource, fontSize -> {
                return Math.round(fontSize * UIScale.computeFontScaleFactor(fontUIResource));
            });
        }
        defaults.put("defaultFont", UIScale.applyCustomScaleFactor(uiFont));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FontUIResource createCompositeFont(String family, int style, int size) {
        FontUtils.loadFontFamily(family);
        FontUIResource font = StyleContext.getDefaultStyleContext().getFont(family, style, size);
        return font instanceof FontUIResource ? font : new FontUIResource(font);
    }

    public static UIDefaults.ActiveValue createActiveFontValue(float scaleFactor) {
        return new ActiveFont(null, null, -1, 0, 0, 0, scaleFactor);
    }

    public static void initIconColors(UIDefaults defaults, boolean dark) {
        for (FlatIconColors c : FlatIconColors.values()) {
            if (c.light == (!dark) || c.dark == dark) {
                defaults.put(c.key, new ColorUIResource(c.rgb));
            }
        }
    }

    private void putAATextInfo(UIDefaults defaults) {
        Map<Object, Object> hints;
        Object aaHint;
        if (SystemInfo.isMacOS && SystemInfo.isJetBrainsJVM) {
            defaults.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            return;
        }
        if (SystemInfo.isJava_9_orLater) {
            Object desktopHints = Toolkit.getDefaultToolkit().getDesktopProperty(DESKTOPFONTHINTS);
            if (desktopHints == null) {
                desktopHints = fallbackAATextInfo();
            }
            if ((desktopHints instanceof Map) && (aaHint = (hints = (Map) desktopHints).get(RenderingHints.KEY_TEXT_ANTIALIASING)) != null && aaHint != RenderingHints.VALUE_TEXT_ANTIALIAS_OFF && aaHint != RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT) {
                defaults.put(RenderingHints.KEY_TEXT_ANTIALIASING, aaHint);
                defaults.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, hints.get(RenderingHints.KEY_TEXT_LCD_CONTRAST));
                return;
            }
            return;
        }
        try {
            Object key = Class.forName("sun.swing.SwingUtilities2").getField("AA_TEXT_PROPERTY_KEY").get(null);
            Object value = Class.forName("sun.swing.SwingUtilities2$AATextInfo").getMethod("getAATextInfo", Boolean.TYPE).invoke(null, true);
            if (value == null) {
                value = fallbackAATextInfo();
            }
            defaults.put(key, value);
        } catch (Exception ex) {
            LoggingFacade.INSTANCE.logSevere(null, ex);
            throw new RuntimeException(ex);
        }
    }

    private Object fallbackAATextInfo() {
        if (System.getProperty("awt.useSystemAAFontSettings") != null) {
            return null;
        }
        Object aaHint = null;
        if (SystemInfo.isLinux) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            if (toolkit.getDesktopProperty("gnome.Xft/Antialias") == null && toolkit.getDesktopProperty("fontconfig/Antialias") == null) {
                aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
            }
        }
        if (aaHint == null) {
            return null;
        }
        if (SystemInfo.isJava_9_orLater) {
            Map<Object, Object> hints = new HashMap<>();
            hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, aaHint);
            hints.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, null);
            return hints;
        }
        try {
            return Class.forName("sun.swing.SwingUtilities2$AATextInfo").getConstructor(Object.class, Integer.class).newInstance(aaHint, null);
        } catch (Exception ex) {
            LoggingFacade.INSTANCE.logSevere(null, ex);
            throw new RuntimeException(ex);
        }
    }

    private void putDefaults(UIDefaults defaults, Object value, String... keys) {
        for (String key : keys) {
            defaults.put(key, value);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Object> getCustomDefaultsSources() {
        return customDefaultsSources;
    }

    public static void registerCustomDefaultsSource(String packageName) {
        registerCustomDefaultsSource(packageName, null);
    }

    public static void unregisterCustomDefaultsSource(String packageName) {
        unregisterCustomDefaultsSource(packageName, null);
    }

    public static void registerCustomDefaultsSource(String packageName, ClassLoader classLoader) {
        if (customDefaultsSources == null) {
            customDefaultsSources = new ArrayList();
        }
        customDefaultsSources.add(packageName);
        customDefaultsSources.add(classLoader);
    }

    public static void unregisterCustomDefaultsSource(String packageName, ClassLoader classLoader) {
        if (customDefaultsSources == null) {
            return;
        }
        int size = customDefaultsSources.size();
        for (int i = 0; i < size - 1; i++) {
            Object source = customDefaultsSources.get(i);
            if (packageName.equals(source) && customDefaultsSources.get(i + 1) == classLoader) {
                customDefaultsSources.remove(i + 1);
                customDefaultsSources.remove(i);
                return;
            }
        }
    }

    public static void registerCustomDefaultsSource(URL packageUrl) {
        if (customDefaultsSources == null) {
            customDefaultsSources = new ArrayList();
        }
        customDefaultsSources.add(packageUrl);
    }

    public static void unregisterCustomDefaultsSource(URL packageUrl) {
        if (customDefaultsSources == null) {
            return;
        }
        customDefaultsSources.remove(packageUrl);
    }

    public static void registerCustomDefaultsSource(File folder) {
        if (customDefaultsSources == null) {
            customDefaultsSources = new ArrayList();
        }
        customDefaultsSources.add(folder);
    }

    public static void unregisterCustomDefaultsSource(File folder) {
        if (customDefaultsSources == null) {
            return;
        }
        customDefaultsSources.remove(folder);
    }

    public static Map<String, String> getGlobalExtraDefaults() {
        return globalExtraDefaults;
    }

    public static void setGlobalExtraDefaults(Map<String, String> globalExtraDefaults2) {
        globalExtraDefaults = globalExtraDefaults2;
    }

    public Map<String, String> getExtraDefaults() {
        return this.extraDefaults;
    }

    public void setExtraDefaults(Map<String, String> extraDefaults) {
        this.extraDefaults = extraDefaults;
    }

    public static Object parseDefaultsValue(String key, String value, Class<?> valueType) throws IllegalArgumentException {
        Object val = UIDefaultsLoader.parseValue(key, UIDefaultsLoader.resolveValueFromUIManager(value), valueType, null, v -> {
            return UIDefaultsLoader.resolveValueFromUIManager(v);
        }, Collections.emptyList());
        if (val instanceof UIDefaults.LazyValue) {
            val = ((UIDefaults.LazyValue) val).createValue((UIDefaults) null);
        } else if (val instanceof UIDefaults.ActiveValue) {
            val = ((UIDefaults.ActiveValue) val).createValue((UIDefaults) null);
        }
        return val;
    }

    public static Function<String, Color> getSystemColorGetter() {
        return systemColorGetter;
    }

    public static void setSystemColorGetter(Function<String, Color> systemColorGetter2) {
        systemColorGetter = systemColorGetter2;
    }

    private static void reSetLookAndFeel() {
        EventQueue.invokeLater(() -> {
            LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
            try {
                UIManager.setLookAndFeel(lookAndFeel);
                PropertyChangeEvent e = new PropertyChangeEvent(UIManager.class, "lookAndFeel", lookAndFeel, lookAndFeel);
                for (PropertyChangeListener l : UIManager.getPropertyChangeListeners()) {
                    l.propertyChange(e);
                }
                updateUI();
            } catch (UnsupportedLookAndFeelException e2) {
                LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to reinitialize look and feel '" + lookAndFeel.getClass().getName() + "'.", e2);
            }
        });
    }

    public static void updateUI() {
        for (Component component : Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(component);
        }
    }

    public static void updateUILater() {
        synchronized (FlatLaf.class) {
            if (updateUIPending) {
                return;
            }
            updateUIPending = true;
            EventQueue.invokeLater(() -> {
                updateUI();
                synchronized (FlatLaf.class) {
                    updateUIPending = false;
                }
            });
        }
    }

    public static boolean supportsNativeWindowDecorations() {
        return SystemInfo.isWindows_10_orLater && FlatNativeWindowBorder.isSupported();
    }

    public static boolean isUseNativeWindowDecorations() {
        return UIManager.getBoolean("TitlePane.useWindowDecorations");
    }

    public static void setUseNativeWindowDecorations(boolean enabled) {
        UIManager.put("TitlePane.useWindowDecorations", Boolean.valueOf(enabled));
        if (!(UIManager.getLookAndFeel() instanceof FlatLaf)) {
            return;
        }
        for (RootPaneContainer rootPaneContainer : Window.getWindows()) {
            if (isDisplayableFrameOrDialog(rootPaneContainer)) {
                FlatRootPaneUI.updateNativeWindowBorder(rootPaneContainer.getRootPane());
            }
        }
    }

    public static void revalidateAndRepaintAllFramesAndDialogs() {
        JMenuBar jMenuBar;
        for (JFrame jFrame : Window.getWindows()) {
            if (isDisplayableFrameOrDialog(jFrame)) {
                if (jFrame instanceof JFrame) {
                    jMenuBar = jFrame.getJMenuBar();
                } else if (jFrame instanceof JDialog) {
                    jMenuBar = ((JDialog) jFrame).getJMenuBar();
                } else {
                    jMenuBar = null;
                }
                JMenuBar menuBar = jMenuBar;
                if (menuBar != null) {
                    menuBar.revalidate();
                }
                jFrame.revalidate();
                jFrame.repaint();
            }
        }
    }

    public static void repaintAllFramesAndDialogs() {
        for (Window w : Window.getWindows()) {
            if (isDisplayableFrameOrDialog(w)) {
                w.repaint();
            }
        }
    }

    private static boolean isDisplayableFrameOrDialog(Window w) {
        return w.isDisplayable() && ((w instanceof JFrame) || (w instanceof JDialog));
    }

    public static boolean isShowMnemonics() {
        return MnemonicHandler.isShowMnemonics();
    }

    public static void showMnemonics(Component c) {
        MnemonicHandler.showMnemonics(true, c);
    }

    public static void hideMnemonics() {
        MnemonicHandler.showMnemonics(false, null);
    }

    public final boolean equals(Object obj) {
        return super/*java.lang.Object*/.equals(obj);
    }

    public final int hashCode() {
        return super/*java.lang.Object*/.hashCode();
    }

    public void registerUIDefaultsGetter(Function<Object, Object> uiDefaultsGetter) {
        if (this.uiDefaultsGetters == null) {
            this.uiDefaultsGetters = new ArrayList();
        }
        this.uiDefaultsGetters.remove(uiDefaultsGetter);
        this.uiDefaultsGetters.add(uiDefaultsGetter);
        FlatUIUtils.setUseSharedUIs(false);
    }

    public void unregisterUIDefaultsGetter(Function<Object, Object> uiDefaultsGetter) {
        if (this.uiDefaultsGetters == null) {
            return;
        }
        this.uiDefaultsGetters.remove(uiDefaultsGetter);
        if (this.uiDefaultsGetters.isEmpty()) {
            FlatUIUtils.setUseSharedUIs(true);
        }
    }

    public static void runWithUIDefaultsGetter(Function<Object, Object> uiDefaultsGetter, Runnable runnable) {
        FlatLaf lookAndFeel = UIManager.getLookAndFeel();
        if (lookAndFeel instanceof FlatLaf) {
            lookAndFeel.registerUIDefaultsGetter(uiDefaultsGetter);
            try {
                runnable.run();
                return;
            } finally {
                lookAndFeel.unregisterUIDefaultsGetter(uiDefaultsGetter);
            }
        }
        runnable.run();
    }

    public static Map<String, Class<?>> getStyleableInfos(JComponent c) {
        FlatStylingSupport.StyleableUI ui = JavaCompatibility2.getUI(c);
        if (ui instanceof FlatStylingSupport.StyleableUI) {
            return ui.getStyleableInfos(c);
        }
        return null;
    }

    public static <T> T getStyleableValue(JComponent jComponent, String str) {
        FlatStylingSupport.StyleableUI ui = JavaCompatibility2.getUI(jComponent);
        if (ui instanceof FlatStylingSupport.StyleableUI) {
            return (T) ui.getStyleableValue(jComponent, str);
        }
        return null;
    }

    public static String getPreferredFontFamily() {
        return preferredFontFamily;
    }

    public static void setPreferredFontFamily(String preferredFontFamily2) {
        preferredFontFamily = preferredFontFamily2;
    }

    public static String getPreferredLightFontFamily() {
        return preferredLightFontFamily;
    }

    public static void setPreferredLightFontFamily(String preferredLightFontFamily2) {
        preferredLightFontFamily = preferredLightFontFamily2;
    }

    public static String getPreferredSemiboldFontFamily() {
        return preferredSemiboldFontFamily;
    }

    public static void setPreferredSemiboldFontFamily(String preferredSemiboldFontFamily2) {
        preferredSemiboldFontFamily = preferredSemiboldFontFamily2;
    }

    public static String getPreferredMonospacedFontFamily() {
        return preferredMonospacedFontFamily;
    }

    public static void setPreferredMonospacedFontFamily(String preferredMonospacedFontFamily2) {
        preferredMonospacedFontFamily = preferredMonospacedFontFamily2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/formdev/flatlaf/FlatLaf$FlatUIDefaults.class */
    public class FlatUIDefaults extends UIDefaults {
        private UIDefaults metalDefaults;

        FlatUIDefaults(int initialCapacity, float loadFactor) {
            super(initialCapacity, loadFactor);
        }

        public Object get(Object key) {
            return get(key, null);
        }

        public Object get(Object key, Locale l) {
            Object value = getFromUIDefaultsGetters(key);
            if (value != null) {
                if (value != FlatLaf.NULL_VALUE) {
                    return value;
                }
                return null;
            }
            Object value2 = super.get(key, l);
            if (value2 != null) {
                return value2;
            }
            if ((key instanceof String) && ((String) key).startsWith("FileChooser.")) {
                return getFromMetal((String) key, l);
            }
            return null;
        }

        private Object getFromUIDefaultsGetters(Object key) {
            List<Function<Object, Object>> uiDefaultsGetters = FlatLaf.this.uiDefaultsGetters;
            if (uiDefaultsGetters == null) {
                return null;
            }
            for (int i = uiDefaultsGetters.size() - 1; i >= 0; i--) {
                Object value = uiDefaultsGetters.get(i).apply(key);
                if (value != null) {
                    return value;
                }
            }
            return null;
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [com.formdev.flatlaf.FlatLaf$FlatUIDefaults$1] */
        private synchronized Object getFromMetal(String key, Locale l) {
            if (this.metalDefaults == null) {
                this.metalDefaults = new MetalLookAndFeel() { // from class: com.formdev.flatlaf.FlatLaf.FlatUIDefaults.1
                    protected void initClassDefaults(UIDefaults table) {
                    }

                    protected void initSystemColorDefaults(UIDefaults table) {
                    }
                }.getDefaults();
                this.metalDefaults.clear();
            }
            return this.metalDefaults.get(key, l);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/formdev/flatlaf/FlatLaf$ActiveFont.class */
    public static class ActiveFont implements UIDefaults.ActiveValue {
        private final String baseFontKey;
        private final List<String> families;
        private final int style;
        private final int styleChange;
        private final int absoluteSize;
        private final int relativeSize;
        private final float scaleSize;
        private FontUIResource font;
        private Font lastBaseFont;
        private boolean inCreateValue;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ActiveFont(String baseFontKey, List<String> families, int style, int styleChange, int absoluteSize, int relativeSize, float scaleSize) {
            this.baseFontKey = baseFontKey;
            this.families = families;
            this.style = style;
            this.styleChange = styleChange;
            this.absoluteSize = absoluteSize;
            this.relativeSize = relativeSize;
            this.scaleSize = scaleSize;
        }

        public synchronized Object createValue(UIDefaults table) {
            if (this.inCreateValue) {
                throw new IllegalStateException("FlatLaf: endless recursion in font");
            }
            Font baseFont = null;
            this.inCreateValue = true;
            try {
                if (this.baseFontKey != null) {
                    baseFont = (Font) UIDefaultsLoader.lazyUIManagerGet(this.baseFontKey);
                }
                if (baseFont == null) {
                    baseFont = UIManager.getFont("defaultFont");
                }
                if (baseFont == null) {
                    baseFont = UIManager.getFont("Label.font");
                }
                if (this.lastBaseFont != baseFont) {
                    this.lastBaseFont = baseFont;
                    this.font = derive(baseFont, fontSize -> {
                        return UIScale.scale(fontSize);
                    });
                }
                return this.font;
            } finally {
                this.inCreateValue = false;
            }
        }

        FontUIResource derive(Font baseFont, IntUnaryOperator scale) {
            int i;
            int i2;
            int baseStyle = baseFont.getStyle();
            int baseSize = baseFont.getSize();
            if (this.style != -1) {
                i = this.style;
            } else if (this.styleChange != 0) {
                i = (baseStyle & (((this.styleChange >> 16) & 65535) ^ (-1))) | (this.styleChange & 65535);
            } else {
                i = baseStyle;
            }
            int newStyle = i;
            if (this.absoluteSize > 0) {
                i2 = scale.applyAsInt(this.absoluteSize);
            } else if (this.relativeSize != 0) {
                i2 = baseSize + scale.applyAsInt(this.relativeSize);
            } else if (this.scaleSize > 0.0f) {
                i2 = Math.round(baseSize * this.scaleSize);
            } else {
                i2 = baseSize;
            }
            int newSize = i2;
            if (newSize <= 0) {
                newSize = 1;
            }
            if (this.families != null && !this.families.isEmpty()) {
                String preferredFamily = preferredFamily(this.families);
                if (preferredFamily != null) {
                    FontUIResource createCompositeFont = FlatLaf.createCompositeFont(preferredFamily, newStyle, newSize);
                    if (!isFallbackFont(createCompositeFont) || isDialogFamily(preferredFamily)) {
                        return toUIResource(createCompositeFont);
                    }
                }
                for (String family : this.families) {
                    FontUIResource createCompositeFont2 = FlatLaf.createCompositeFont(family, newStyle, newSize);
                    if (!isFallbackFont(createCompositeFont2) || isDialogFamily(family)) {
                        return toUIResource(createCompositeFont2);
                    }
                }
            }
            if (newStyle != baseStyle || newSize != baseSize) {
                if ("Ubuntu Medium".equalsIgnoreCase(baseFont.getName()) && "Ubuntu Light".equalsIgnoreCase(baseFont.getFamily())) {
                    FontUIResource createCompositeFont3 = FlatLaf.createCompositeFont("Ubuntu Medium", newStyle, newSize);
                    if (!isFallbackFont(createCompositeFont3)) {
                        return toUIResource(createCompositeFont3);
                    }
                }
                return toUIResource(baseFont.deriveFont(newStyle, newSize));
            }
            return toUIResource(baseFont);
        }

        private FontUIResource toUIResource(Font font) {
            if (font instanceof FontUIResource) {
                return (FontUIResource) font;
            }
            return new FontUIResource(font);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isFallbackFont(Font font) {
            return "Dialog".equalsIgnoreCase(font.getFamily());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isDialogFamily(String family) {
            return family.equalsIgnoreCase("Dialog");
        }

        private static String preferredFamily(List<String> families) {
            Iterator<String> it = families.iterator();
            while (it.hasNext()) {
                String family = it.next().toLowerCase(Locale.ENGLISH);
                if (family.endsWith(" light") || family.endsWith("-thin")) {
                    return FlatLaf.preferredLightFontFamily;
                }
                if (family.endsWith(" semibold") || family.endsWith("-medium")) {
                    return FlatLaf.preferredSemiboldFontFamily;
                }
                if (family.equals("monospaced")) {
                    return FlatLaf.preferredMonospacedFontFamily;
                }
            }
            return null;
        }
    }

    /* loaded from: target.jar:com/formdev/flatlaf/FlatLaf$ImageIconUIResource.class */
    private static class ImageIconUIResource extends ImageIcon implements UIResource {
        ImageIconUIResource(Image image) {
            super(image);
        }
    }
}
