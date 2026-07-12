package com.formdev.flatlaf;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

/* loaded from: target.jar:com/formdev/flatlaf/FlatPropertiesLaf.class */
public class FlatPropertiesLaf extends FlatLaf {
    private final String name;
    private final String baseTheme;
    private final boolean dark;
    private final Properties properties;

    public FlatPropertiesLaf(String name, File propertiesFile) throws IOException {
        this(name, new FileInputStream(propertiesFile));
    }

    public FlatPropertiesLaf(String name, InputStream in) throws IOException {
        this(name, loadProperties(in));
    }

    private static Properties loadProperties(InputStream in) throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(in);
            if (in != null) {
                in.close();
            }
            return properties;
        } catch (Throwable th) {
            if (in != null) {
                try {
                    in.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public FlatPropertiesLaf(String name, Properties properties) {
        this.name = name;
        this.properties = properties;
        this.baseTheme = properties.getProperty("@baseTheme", "light");
        this.dark = "dark".equalsIgnoreCase(this.baseTheme) || "darcula".equalsIgnoreCase(this.baseTheme) || "macdark".equalsIgnoreCase(this.baseTheme);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.name;
    }

    @Override // com.formdev.flatlaf.FlatLaf
    public boolean isDark() {
        return this.dark;
    }

    public Properties getProperties() {
        return this.properties;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.formdev.flatlaf.FlatLaf
    public ArrayList<Class<?>> getLafClassesForDefaultsLoading() {
        ArrayList<Class<?>> lafClasses = new ArrayList<>();
        lafClasses.add(FlatLaf.class);
        String lowerCase = this.baseTheme.toLowerCase(Locale.ENGLISH);
        boolean z = -1;
        switch (lowerCase.hashCode()) {
            case -181434073:
                if (lowerCase.equals("maclight")) {
                    z = 5;
                    break;
                }
                break;
            case 3075958:
                if (lowerCase.equals("dark")) {
                    z = 2;
                    break;
                }
                break;
            case 102970646:
                if (lowerCase.equals("light")) {
                    z = true;
                    break;
                }
                break;
            case 570230263:
                if (lowerCase.equals("intellij")) {
                    z = 3;
                    break;
                }
                break;
            case 825185605:
                if (lowerCase.equals("macdark")) {
                    z = 6;
                    break;
                }
                break;
            case 1441429116:
                if (lowerCase.equals("darcula")) {
                    z = 4;
                    break;
                }
                break;
        }
        switch (z) {
            case true:
            default:
                lafClasses.add(FlatLightLaf.class);
                break;
            case true:
                lafClasses.add(FlatDarkLaf.class);
                break;
            case true:
                lafClasses.add(FlatLightLaf.class);
                lafClasses.add(FlatIntelliJLaf.class);
                break;
            case true:
                lafClasses.add(FlatDarkLaf.class);
                lafClasses.add(FlatDarculaLaf.class);
                break;
            case true:
                lafClasses.add(FlatLightLaf.class);
                lafClasses.add(FlatMacLightLaf.class);
                break;
            case true:
                lafClasses.add(FlatDarkLaf.class);
                lafClasses.add(FlatMacDarkLaf.class);
                break;
        }
        return lafClasses;
    }

    @Override // com.formdev.flatlaf.FlatLaf
    protected Properties getAdditionalDefaults() {
        return this.properties;
    }
}
