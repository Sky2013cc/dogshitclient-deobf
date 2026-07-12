package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Rectangle;
import java.awt.Window;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatNativeMacLibrary.class */
public class FlatNativeMacLibrary {
    private static int API_VERSION_MACOS = 2001;
    public static final int BUTTONS_SPACING_DEFAULT = 0;
    public static final int BUTTONS_SPACING_MEDIUM = 1;
    public static final int BUTTONS_SPACING_LARGE = 2;

    public static native boolean setWindowRoundedBorder(Window window, float f, float f2, int i);

    public static native boolean setWindowButtonsSpacing(Window window, int i);

    public static native Rectangle getWindowButtonsBounds(Window window);

    public static native boolean isWindowFullScreen(Window window);

    public static native boolean toggleWindowFullScreen(Window window);

    public static boolean isLoaded() {
        return SystemInfo.isMacOS && FlatNativeLibrary.isLoaded(API_VERSION_MACOS);
    }
}
