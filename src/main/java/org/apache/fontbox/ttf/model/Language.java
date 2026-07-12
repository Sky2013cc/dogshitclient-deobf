package org.apache.fontbox.ttf.model;

/* loaded from: target.jar:org/apache/fontbox/ttf/model/Language.class */
public enum Language {
    BENGALI(new String[]{"bng2", "beng"}),
    LATIN(new String[]{"latn"}),
    UNSPECIFIED(new String[0]);

    private final String[] scriptNames;

    Language(String[] scriptNames) {
        this.scriptNames = scriptNames;
    }

    public String[] getScriptNames() {
        return this.scriptNames;
    }
}
