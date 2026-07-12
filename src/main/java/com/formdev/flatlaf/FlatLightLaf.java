package com.formdev.flatlaf;

/* loaded from: target.jar:com/formdev/flatlaf/FlatLightLaf.class */
public class FlatLightLaf extends FlatLaf {
    public static final String NAME = "FlatLaf Light";

    public static boolean setup() {
        return setup(new FlatLightLaf());
    }

    @Deprecated
    public static boolean install() {
        return setup();
    }

    public static void installLafInfo() {
        installLafInfo(NAME, FlatLightLaf.class);
    }

    public String getName() {
        return NAME;
    }

    public String getDescription() {
        return "FlatLaf Light Look and Feel";
    }

    @Override // com.formdev.flatlaf.FlatLaf
    public boolean isDark() {
        return false;
    }
}
