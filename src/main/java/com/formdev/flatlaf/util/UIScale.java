package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatSystemProperties;
import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Method;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;

/* loaded from: target.jar:com/formdev/flatlaf/util/UIScale.class */
public class UIScale {
    private static final boolean DEBUG = false;
    private static PropertyChangeSupport changeSupport;
    private static Boolean jreHiDPI;
    private static float scaleFactor = 1.0f;
    private static boolean initialized;

    public static void addPropertyChangeListener(PropertyChangeListener listener) {
        if (changeSupport == null) {
            changeSupport = new PropertyChangeSupport(UIScale.class);
        }
        changeSupport.addPropertyChangeListener(listener);
    }

    public static void removePropertyChangeListener(PropertyChangeListener listener) {
        if (changeSupport == null) {
            return;
        }
        changeSupport.removePropertyChangeListener(listener);
    }

    public static boolean isSystemScalingEnabled() {
        if (jreHiDPI != null) {
            return jreHiDPI.booleanValue();
        }
        jreHiDPI = false;
        if (SystemInfo.isJava_9_orLater) {
            jreHiDPI = true;
        } else if (SystemInfo.isJetBrainsJVM) {
            try {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Class<?> sunGeClass = Class.forName("sun.java2d.SunGraphicsEnvironment");
                if (sunGeClass.isInstance(ge)) {
                    Method m = sunGeClass.getDeclaredMethod("isUIScaleOn", new Class[0]);
                    jreHiDPI = (Boolean) m.invoke(ge, new Object[0]);
                }
            } catch (Throwable th) {
            }
        }
        return jreHiDPI.booleanValue();
    }

    public static double getSystemScaleFactor(Graphics2D g) {
        if (isSystemScalingEnabled()) {
            return getSystemScaleFactor(g.getDeviceConfiguration());
        }
        return 1.0d;
    }

    public static double getSystemScaleFactor(GraphicsConfiguration gc) {
        if (!isSystemScalingEnabled() || gc == null) {
            return 1.0d;
        }
        return gc.getDefaultTransform().getScaleX();
    }

    private static void initialize() {
        if (initialized) {
            return;
        }
        initialized = true;
        if (!isUserScalingEnabled()) {
            return;
        }
        PropertyChangeListener listener = new PropertyChangeListener() { // from class: com.formdev.flatlaf.util.UIScale.1
            @Override // java.beans.PropertyChangeListener
            public void propertyChange(PropertyChangeEvent e) {
                String propertyName = e.getPropertyName();
                boolean z = -1;
                switch (propertyName.hashCode()) {
                    case -1595277186:
                        if (propertyName.equals("lookAndFeel")) {
                            z = false;
                            break;
                        }
                        break;
                    case -437367248:
                        if (propertyName.equals("defaultFont")) {
                            z = true;
                            break;
                        }
                        break;
                    case 298310441:
                        if (propertyName.equals("Label.font")) {
                            z = 2;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        if (e.getNewValue() instanceof LookAndFeel) {
                            UIManager.getLookAndFeelDefaults().addPropertyChangeListener(this);
                        }
                        UIScale.updateScaleFactor();
                        return;
                    case true:
                    case true:
                        UIScale.updateScaleFactor();
                        return;
                    default:
                        return;
                }
            }
        };
        UIManager.addPropertyChangeListener(listener);
        UIManager.getDefaults().addPropertyChangeListener(listener);
        UIManager.getLookAndFeelDefaults().addPropertyChangeListener(listener);
        updateScaleFactor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateScaleFactor() {
        if (!isUserScalingEnabled()) {
            return;
        }
        float customScaleFactor = getCustomScaleFactor();
        if (customScaleFactor > 0.0f) {
            setUserScaleFactor(customScaleFactor, false);
            return;
        }
        Font font = null;
        if (UIManager.getLookAndFeel() instanceof FlatLaf) {
            font = UIManager.getFont("defaultFont");
        }
        if (font == null) {
            font = UIManager.getFont("Label.font");
        }
        setUserScaleFactor(computeFontScaleFactor(font), true);
    }

    public static float computeFontScaleFactor(Font font) {
        Font uiFont;
        if (SystemInfo.isWindows && (font instanceof UIResource) && ((uiFont = (Font) Toolkit.getDefaultToolkit().getDesktopProperty("win.messagebox.font")) == null || uiFont.getSize() == font.getSize())) {
            if (isSystemScalingEnabled()) {
                return 1.0f;
            }
            Font winFont = (Font) Toolkit.getDefaultToolkit().getDesktopProperty("win.defaultGUI.font");
            return computeScaleFactor(winFont != null ? winFont : font);
        }
        return computeScaleFactor(font);
    }

    private static float computeScaleFactor(Font font) {
        String customFontSizeDivider = System.getProperty("flatlaf.uiScale.fontSizeDivider");
        if (customFontSizeDivider != null) {
            try {
                return font.getSize() / Math.max(Integer.parseInt(customFontSizeDivider), 10);
            } catch (NumberFormatException e) {
            }
        }
        float fontSizeDivider = 12.0f;
        if (SystemInfo.isWindows) {
            if ("Tahoma".equals(font.getFamily())) {
                fontSizeDivider = 11.0f;
            }
        } else if (SystemInfo.isMacOS) {
            fontSizeDivider = 13.0f;
        } else if (SystemInfo.isLinux) {
            fontSizeDivider = SystemInfo.isKDE ? 13.0f : 15.0f;
        }
        return font.getSize() / fontSizeDivider;
    }

    private static boolean isUserScalingEnabled() {
        return FlatSystemProperties.getBoolean(FlatSystemProperties.UI_SCALE_ENABLED, true);
    }

    public static FontUIResource applyCustomScaleFactor(FontUIResource font) {
        if (!isUserScalingEnabled()) {
            return font;
        }
        float scaleFactor2 = getCustomScaleFactor();
        if (scaleFactor2 <= 0.0f) {
            return font;
        }
        float fontScaleFactor = computeScaleFactor(font);
        if (scaleFactor2 == fontScaleFactor) {
            return font;
        }
        int newFontSize = Math.max(Math.round((font.getSize() / fontScaleFactor) * scaleFactor2), 1);
        return new FontUIResource(font.deriveFont(newFontSize));
    }

    private static float getCustomScaleFactor() {
        return parseScaleFactor(System.getProperty(FlatSystemProperties.UI_SCALE));
    }

    private static float parseScaleFactor(String s) {
        if (s == null) {
            return -1.0f;
        }
        float units = 1.0f;
        if (s.endsWith(SimpleTaglet.EXCLUDED)) {
            s = s.substring(0, s.length() - 1);
        } else if (s.endsWith("dpi")) {
            units = 96.0f;
            s = s.substring(0, s.length() - 3);
        } else if (s.endsWith("%")) {
            units = 100.0f;
            s = s.substring(0, s.length() - 1);
        }
        try {
            float scale = Float.parseFloat(s);
            if (scale > 0.0f) {
                return scale / units;
            }
            return -1.0f;
        } catch (NumberFormatException e) {
            return -1.0f;
        }
    }

    public static float getUserScaleFactor() {
        initialize();
        return scaleFactor;
    }

    private static void setUserScaleFactor(float scaleFactor2, boolean normalize) {
        float f;
        if (normalize) {
            if (scaleFactor2 < 1.0f) {
                if (FlatSystemProperties.getBoolean(FlatSystemProperties.UI_SCALE_ALLOW_SCALE_DOWN, false)) {
                    f = Math.round(scaleFactor2 * 10.0f) / 10.0f;
                } else {
                    f = 1.0f;
                }
                scaleFactor2 = f;
            } else if (scaleFactor2 > 1.0f) {
                scaleFactor2 = Math.round(scaleFactor2 * 4.0f) / 4.0f;
            }
        }
        float scaleFactor3 = Math.max(scaleFactor2, 0.1f);
        float oldScaleFactor = scaleFactor;
        scaleFactor = scaleFactor3;
        if (changeSupport != null) {
            changeSupport.firePropertyChange("userScaleFactor", Float.valueOf(oldScaleFactor), Float.valueOf(scaleFactor3));
        }
    }

    public static float scale(float value) {
        initialize();
        return scaleFactor == 1.0f ? value : value * scaleFactor;
    }

    public static int scale(int value) {
        initialize();
        return scaleFactor == 1.0f ? value : Math.round(value * scaleFactor);
    }

    public static int scale2(int value) {
        initialize();
        return scaleFactor == 1.0f ? value : (int) (value * scaleFactor);
    }

    public static float unscale(float value) {
        initialize();
        return scaleFactor == 1.0f ? value : value / scaleFactor;
    }

    public static int unscale(int value) {
        initialize();
        return scaleFactor == 1.0f ? value : Math.round(value / scaleFactor);
    }

    public static void scaleGraphics(Graphics2D g) {
        initialize();
        if (scaleFactor != 1.0f) {
            g.scale(scaleFactor, scaleFactor);
        }
    }

    public static Dimension scale(Dimension dimension) {
        initialize();
        if (dimension == null || scaleFactor == 1.0f) {
            return dimension;
        }
        if (dimension instanceof UIResource) {
            return new DimensionUIResource(scale(dimension.width), scale(dimension.height));
        }
        return new Dimension(scale(dimension.width), scale(dimension.height));
    }

    public static Insets scale(Insets insets) {
        initialize();
        if (insets == null || scaleFactor == 1.0f) {
            return insets;
        }
        if (insets instanceof UIResource) {
            return new InsetsUIResource(scale(insets.top), scale(insets.left), scale(insets.bottom), scale(insets.right));
        }
        return new Insets(scale(insets.top), scale(insets.left), scale(insets.bottom), scale(insets.right));
    }
}
