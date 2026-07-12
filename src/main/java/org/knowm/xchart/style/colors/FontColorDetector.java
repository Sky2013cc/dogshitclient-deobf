package org.knowm.xchart.style.colors;

import java.awt.Color;

/* loaded from: target.jar:org/knowm/xchart/style/colors/FontColorDetector.class */
public class FontColorDetector {
    private static final int BRIGHTNESS_THRESHOLD = 130;
    private static final double RED_FACTOR = 0.241d;
    private static final double GREEN_FACTOR = 0.587d;
    private static final double BLUE_FACTOR = 0.114d;

    public static Color getAutomaticFontColor(Color backgroundColor, Color darkForegroundColor, Color lightForegroundColor) {
        double backgroundColorPerceivedBrightness = Math.sqrt((Math.pow(backgroundColor.getRed(), 2.0d) * RED_FACTOR) + (Math.pow(backgroundColor.getGreen(), 2.0d) * GREEN_FACTOR) + (Math.pow(backgroundColor.getBlue(), 2.0d) * BLUE_FACTOR));
        if (backgroundColorPerceivedBrightness < 130.0d) {
            return lightForegroundColor;
        }
        return darkForegroundColor;
    }
}
