package com.sun.tools.javac.api;

import java.util.Locale;

/* loaded from: target.jar:com/sun/tools/javac/api/Formattable.class */
public interface Formattable {
    String toString(Locale locale, Messages messages);

    String getKind();

    /* loaded from: target.jar:com/sun/tools/javac/api/Formattable$LocalizedString.class */
    public static class LocalizedString implements Formattable {
        String key;

        public LocalizedString(String str) {
            this.key = str;
        }

        @Override // com.sun.tools.javac.api.Formattable
        public String toString(Locale locale, Messages messages) {
            return messages.getLocalizedString(locale, this.key, new Object[0]);
        }

        @Override // com.sun.tools.javac.api.Formattable
        public String getKind() {
            return "LocalizedString";
        }

        public String toString() {
            return this.key;
        }
    }
}
