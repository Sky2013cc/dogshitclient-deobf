package com.formdev.flatlaf.util;

import java.awt.image.RGBImageFilter;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/formdev/flatlaf/util/GrayFilter.class */
public class GrayFilter extends RGBImageFilter {
    private final float brightness;
    private final float contrast;
    private final int alpha;
    private final int origContrast;
    private final int origBrightness;

    public static GrayFilter createDisabledIconFilter(boolean dark) {
        if (dark) {
            return new GrayFilter(-20, -70, 100);
        }
        return new GrayFilter(25, -25, 100);
    }

    public GrayFilter(int brightness, int contrast, int alpha) {
        this.origBrightness = Math.max(-100, Math.min(100, brightness));
        this.origContrast = Math.max(-100, Math.min(100, contrast));
        this.alpha = Math.max(0, Math.min(100, alpha));
        this.brightness = (float) (Math.pow(this.origBrightness, 3.0d) / 10000.0d);
        this.contrast = this.origContrast / 100.0f;
        this.canFilterIndexColorModel = true;
    }

    public GrayFilter() {
        this(0, 0, 100);
    }

    public int getBrightness() {
        return this.origBrightness;
    }

    public int getContrast() {
        return this.origContrast;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public int filterRGB(int x, int y, int rgb) {
        int gray;
        int gray2;
        int i;
        int gray3 = (int) ((0.3d * ((rgb >> 16) & 255)) + (0.59d * ((rgb >> 8) & 255)) + (0.11d * (rgb & 255)));
        if (this.brightness >= 0.0f) {
            gray = (int) ((gray3 + (this.brightness * 255.0f)) / (1.0f + this.brightness));
        } else {
            gray = (int) (gray3 / (1.0f - this.brightness));
        }
        if (this.contrast >= 0.0f) {
            if (gray >= 127) {
                gray2 = (int) (gray + ((255 - gray) * this.contrast));
            } else {
                gray2 = (int) (gray - (gray * this.contrast));
            }
        } else {
            gray2 = (int) (127.0f + ((gray - 127) * (this.contrast + 1.0f)));
        }
        if (this.alpha != 100) {
            i = ((((rgb >> 24) & 255) * this.alpha) / 100) << 24;
        } else {
            i = rgb & Constants.TM_MASK;
        }
        int a = i;
        return a | (gray2 << 16) | (gray2 << 8) | gray2;
    }
}
