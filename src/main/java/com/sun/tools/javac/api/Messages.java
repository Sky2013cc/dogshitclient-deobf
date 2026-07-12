package com.sun.tools.javac.api;

import java.util.Locale;
import java.util.MissingResourceException;

/* loaded from: target.jar:com/sun/tools/javac/api/Messages.class */
public interface Messages {
    void add(String str) throws MissingResourceException;

    String getLocalizedString(Locale locale, String str, Object... objArr);
}
