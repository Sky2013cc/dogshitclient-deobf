package com.formdev.flatlaf;

/* loaded from: target.jar:com/formdev/flatlaf/FlatDarkLaf.class */
public class FlatDarkLaf extends FlatLaf {
    public static final String NAME = "FlatLaf Dark";

    public static boolean setup() {
        return setup(new FlatDarkLaf());
    }

    @Deprecated
    public static boolean install() {
        return setup();
    }

    public static void installLafInfo() {
        installLafInfo(NAME, FlatDarkLaf.class);
    }

    public String getName() {
        return NAME;
    }

    public String getDescription() {
        return "FlatLaf Dark Look and Feel";
    }

    @Override // com.formdev.flatlaf.FlatLaf
    public boolean isDark() {
        return true;
    }
}
