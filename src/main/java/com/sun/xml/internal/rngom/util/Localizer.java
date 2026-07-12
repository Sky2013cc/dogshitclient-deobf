package com.sun.xml.internal.rngom.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/xml/internal/rngom/util/Localizer.class */
public class Localizer {
    private final Class cls;
    private ResourceBundle bundle;
    private final Localizer parent;

    public Localizer(Class cls) {
        this(null, cls);
    }

    public Localizer(Localizer parent, Class cls) {
        this.parent = parent;
        this.cls = cls;
    }

    private String getString(String key) {
        try {
            return getBundle().getString(key);
        } catch (MissingResourceException e) {
            if (this.parent != null) {
                return this.parent.getString(key);
            }
            throw e;
        }
    }

    public String message(String key) {
        return MessageFormat.format(getString(key), new Object[0]);
    }

    public String message(String key, Object arg) {
        return MessageFormat.format(getString(key), arg);
    }

    public String message(String key, Object arg1, Object arg2) {
        return MessageFormat.format(getString(key), arg1, arg2);
    }

    public String message(String key, Object[] args) {
        return MessageFormat.format(getString(key), args);
    }

    private ResourceBundle getBundle() {
        String s;
        if (this.bundle == null) {
            String s2 = this.cls.getName();
            int i = s2.lastIndexOf(46);
            if (i > 0) {
                s = s2.substring(0, i + 1);
            } else {
                s = "";
            }
            this.bundle = ResourceBundle.getBundle(s + "Messages");
        }
        return this.bundle;
    }
}
