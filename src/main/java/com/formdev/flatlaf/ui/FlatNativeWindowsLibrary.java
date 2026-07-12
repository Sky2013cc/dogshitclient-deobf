package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Window;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatNativeWindowsLibrary.class */
public class FlatNativeWindowsLibrary {
    public static final int DWMWCP_DEFAULT = 0;
    public static final int DWMWCP_DONOTROUND = 1;
    public static final int DWMWCP_ROUND = 2;
    public static final int DWMWCP_ROUNDSMALL = 3;
    public static final int DWMWA_USE_IMMERSIVE_DARK_MODE = 20;
    public static final int DWMWA_BORDER_COLOR = 34;
    public static final int DWMWA_CAPTION_COLOR = 35;
    public static final int DWMWA_TEXT_COLOR = 36;
    public static final int DWMWA_COLOR_DEFAULT = -1;
    public static final int DWMWA_COLOR_NONE = -2;
    private static int API_VERSION_WINDOWS = WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY;
    private static long osBuildNumber = Long.MIN_VALUE;
    public static final Color COLOR_NONE = new Color(0, true);

    private static native long getOSBuildNumberImpl();

    public static native long getHWND(Window window);

    public static native boolean setWindowCornerPreference(long j, int i);

    public static native boolean dwmSetWindowAttributeBOOL(long j, int i, boolean z);

    public static native boolean dwmSetWindowAttributeDWORD(long j, int i, int i2);

    public static boolean isLoaded() {
        return SystemInfo.isWindows && FlatNativeLibrary.isLoaded(API_VERSION_WINDOWS);
    }

    public static long getOSBuildNumber() {
        if (osBuildNumber == Long.MIN_VALUE) {
            osBuildNumber = getOSBuildNumberImpl();
        }
        return osBuildNumber;
    }

    public static boolean dwmSetWindowAttributeCOLORREF(long hwnd, int attribute, Color color) {
        int i;
        if (color == COLOR_NONE) {
            i = -2;
        } else if (color != null) {
            i = color.getRed() | (color.getGreen() << 8) | (color.getBlue() << 16);
        } else {
            i = -1;
        }
        int rgb = i;
        return dwmSetWindowAttributeDWORD(hwnd, attribute, rgb);
    }
}
