package com.formdev.flatlaf;

import java.io.InputStream;
import javax.swing.LookAndFeel;
import javax.swing.UIDefaults;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

/* loaded from: target.jar:com/formdev/flatlaf/FlatDefaultsAddon.class */
public abstract class FlatDefaultsAddon {
    public InputStream getDefaults(Class<?> lafClass) {
        Class<?> addonClass = getClass();
        String propertiesName = '/' + addonClass.getPackage().getName().replace('.', '/') + '/' + UIDefaultsLoader.simpleClassName(lafClass) + ".properties";
        return addonClass.getResourceAsStream(propertiesName);
    }

    public void afterDefaultsLoading(LookAndFeel laf, UIDefaults defaults) {
    }

    public int getPriority() {
        return Config.MAX_MULTI_BYTE_RANGES_NUM;
    }
}
