package org.apache.pdfbox.pdmodel.graphics.blend;

import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/graphics/blend/BlendMode.class */
public class BlendMode {
    private static final BlendChannelFunction fNormal = (src, dest) -> {
        return src;
    };
    private static final BlendChannelFunction fMultiply = (src, dest) -> {
        return src * dest;
    };
    private static final BlendChannelFunction fScreen = (src, dest) -> {
        return (src + dest) - (src * dest);
    };
    private static final BlendChannelFunction fOverlay = (src, dest) -> {
        return ((double) dest) <= 0.5d ? 2.0f * dest * src : (2.0f * ((src + dest) - (src * dest))) - 1.0f;
    };
    private static final BlendChannelFunction fDarken = Math::min;
    private static final BlendChannelFunction fLighten = Math::max;
    private static final BlendChannelFunction fColorDodge = (src, dest) -> {
        if (Float.compare(dest, 0.0f) == 0) {
            return 0.0f;
        }
        if (dest >= 1.0f - src) {
            return 1.0f;
        }
        return dest / (1.0f - src);
    };
    private static final BlendChannelFunction fColorBurn = (src, dest) -> {
        if (Float.compare(dest, 1.0f) == 0) {
            return 1.0f;
        }
        if (1.0f - dest >= src) {
            return 0.0f;
        }
        return 1.0f - ((1.0f - dest) / src);
    };
    private static final BlendChannelFunction fHardLight = (src, dest) -> {
        return ((double) src) <= 0.5d ? 2.0f * dest * src : (2.0f * ((src + dest) - (src * dest))) - 1.0f;
    };
    private static final BlendChannelFunction fSoftLight = (src, dest) -> {
        if (src <= 0.5d) {
            return dest - (((1.0f - (2.0f * src)) * dest) * (1.0f - dest));
        }
        float d = ((double) dest) <= 0.25d ? ((((16.0f * dest) - 12.0f) * dest) + 4.0f) * dest : (float) Math.sqrt(dest);
        return dest + (((2.0f * src) - 1.0f) * (d - dest));
    };
    private static final BlendChannelFunction fDifference = (src, dest) -> {
        return Math.abs(dest - src);
    };
    private static final BlendChannelFunction fExclusion = (src, dest) -> {
        return (dest + src) - ((2.0f * dest) * src);
    };
    private static final BlendFunction fHue = (src, dest, result) -> {
        float[] temp = new float[3];
        getSaturationRGB(dest, src, temp);
        getLuminosityRGB(dest, temp, result);
    };
    private static final BlendFunction fSaturation = BlendMode::getSaturationRGB;
    private static final BlendFunction fColor = (src, dest, result) -> {
        getLuminosityRGB(dest, src, result);
    };
    private static final BlendFunction fLuminosity = BlendMode::getLuminosityRGB;
    public static final BlendMode NORMAL = new BlendMode(COSName.NORMAL, fNormal, null);
    public static final BlendMode COMPATIBLE = NORMAL;
    public static final BlendMode MULTIPLY = new BlendMode(COSName.MULTIPLY, fMultiply, null);
    public static final BlendMode SCREEN = new BlendMode(COSName.SCREEN, fScreen, null);
    public static final BlendMode OVERLAY = new BlendMode(COSName.OVERLAY, fOverlay, null);
    public static final BlendMode DARKEN = new BlendMode(COSName.DARKEN, fDarken, null);
    public static final BlendMode LIGHTEN = new BlendMode(COSName.LIGHTEN, fLighten, null);
    public static final BlendMode COLOR_DODGE = new BlendMode(COSName.COLOR_DODGE, fColorDodge, null);
    public static final BlendMode COLOR_BURN = new BlendMode(COSName.COLOR_BURN, fColorBurn, null);
    public static final BlendMode HARD_LIGHT = new BlendMode(COSName.HARD_LIGHT, fHardLight, null);
    public static final BlendMode SOFT_LIGHT = new BlendMode(COSName.SOFT_LIGHT, fSoftLight, null);
    public static final BlendMode DIFFERENCE = new BlendMode(COSName.DIFFERENCE, fDifference, null);
    public static final BlendMode EXCLUSION = new BlendMode(COSName.EXCLUSION, fExclusion, null);
    public static final BlendMode HUE = new BlendMode(COSName.HUE, null, fHue);
    public static final BlendMode SATURATION = new BlendMode(COSName.SATURATION, null, fSaturation);
    public static final BlendMode COLOR = new BlendMode(COSName.COLOR, null, fColor);
    public static final BlendMode LUMINOSITY = new BlendMode(COSName.LUMINOSITY, null, fLuminosity);
    private static final Map<COSName, BlendMode> BLEND_MODES = createBlendModeMap();
    private final COSName name;
    private final BlendChannelFunction blendChannel;
    private final BlendFunction blend;
    private final boolean isSeparable;

    @FunctionalInterface
    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/graphics/blend/BlendMode$BlendChannelFunction.class */
    public interface BlendChannelFunction {
        float blendChannel(float f, float f2);
    }

    @FunctionalInterface
    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/graphics/blend/BlendMode$BlendFunction.class */
    public interface BlendFunction {
        void blend(float[] fArr, float[] fArr2, float[] fArr3);
    }

    private static Map<COSName, BlendMode> createBlendModeMap() {
        Map<COSName, BlendMode> map = new HashMap<>(13);
        map.put(COSName.NORMAL, NORMAL);
        map.put(COSName.COMPATIBLE, NORMAL);
        map.put(COSName.MULTIPLY, MULTIPLY);
        map.put(COSName.SCREEN, SCREEN);
        map.put(COSName.OVERLAY, OVERLAY);
        map.put(COSName.DARKEN, DARKEN);
        map.put(COSName.LIGHTEN, LIGHTEN);
        map.put(COSName.COLOR_DODGE, COLOR_DODGE);
        map.put(COSName.COLOR_BURN, COLOR_BURN);
        map.put(COSName.HARD_LIGHT, HARD_LIGHT);
        map.put(COSName.SOFT_LIGHT, SOFT_LIGHT);
        map.put(COSName.DIFFERENCE, DIFFERENCE);
        map.put(COSName.EXCLUSION, EXCLUSION);
        map.put(COSName.HUE, HUE);
        map.put(COSName.SATURATION, SATURATION);
        map.put(COSName.LUMINOSITY, LUMINOSITY);
        map.put(COSName.COLOR, COLOR);
        return map;
    }

    private BlendMode(COSName name, BlendChannelFunction blendChannel, BlendFunction blend) {
        this.name = name;
        this.blendChannel = blendChannel;
        this.blend = blend;
        this.isSeparable = blendChannel != null;
    }

    public COSName getCOSName() {
        return this.name;
    }

    public boolean isSeparableBlendMode() {
        return this.isSeparable;
    }

    public BlendChannelFunction getBlendChannelFunction() {
        return this.blendChannel;
    }

    public BlendFunction getBlendFunction() {
        return this.blend;
    }

    public static BlendMode getInstance(COSBase cosBlendMode) {
        BlendMode result = null;
        if (cosBlendMode instanceof COSName) {
            result = BLEND_MODES.get(cosBlendMode);
        } else if (cosBlendMode instanceof COSArray) {
            COSArray cosBlendModeArray = (COSArray) cosBlendMode;
            for (int i = 0; i < cosBlendModeArray.size(); i++) {
                COSBase cosBase = cosBlendModeArray.getObject(i);
                if (cosBase instanceof COSName) {
                    result = BLEND_MODES.get(cosBase);
                    if (result != null) {
                        break;
                    }
                }
            }
        }
        return result != null ? result : NORMAL;
    }

    private static int get255Value(float val) {
        return (int) Math.floor(((double) val) >= 1.0d ? 255.0d : val * 255.0d);
    }

    private static void getSaturationRGB(float[] srcValues, float[] dstValues, float[] result) {
        int scalemin;
        int scalemax;
        int rd = get255Value(dstValues[0]);
        int gd = get255Value(dstValues[1]);
        int bd = get255Value(dstValues[2]);
        int minb = Math.min(rd, Math.min(gd, bd));
        int maxb = Math.max(rd, Math.max(gd, bd));
        if (minb == maxb) {
            result[0] = gd / 255.0f;
            result[1] = gd / 255.0f;
            result[2] = gd / 255.0f;
            return;
        }
        int rs = get255Value(srcValues[0]);
        int gs = get255Value(srcValues[1]);
        int bs = get255Value(srcValues[2]);
        int mins = Math.min(rs, Math.min(gs, bs));
        int maxs = Math.max(rs, Math.max(gs, bs));
        int scale = ((maxs - mins) << 16) / (maxb - minb);
        int y = ((((rd * 77) + (gd * 151)) + (bd * 28)) + 128) >> 8;
        int r = y + ((((rd - y) * scale) + 32768) >> 16);
        int g = y + ((((gd - y) * scale) + 32768) >> 16);
        int b = y + ((((bd - y) * scale) + 32768) >> 16);
        if (((r | g | b) & 256) == 256) {
            int min = Math.min(r, Math.min(g, b));
            int max = Math.max(r, Math.max(g, b));
            if (min < 0) {
                scalemin = (y << 16) / (y - min);
            } else {
                scalemin = 65536;
            }
            if (max > 255) {
                scalemax = ((255 - y) << 16) / (max - y);
            } else {
                scalemax = 65536;
            }
            int scale2 = Math.min(scalemin, scalemax);
            r = y + ((((r - y) * scale2) + 32768) >> 16);
            g = y + ((((g - y) * scale2) + 32768) >> 16);
            b = y + ((((b - y) * scale2) + 32768) >> 16);
        }
        result[0] = r / 255.0f;
        result[1] = g / 255.0f;
        result[2] = b / 255.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getLuminosityRGB(float[] srcValues, float[] dstValues, float[] result) {
        int scale;
        int rd = get255Value(dstValues[0]);
        int gd = get255Value(dstValues[1]);
        int bd = get255Value(dstValues[2]);
        int rs = get255Value(srcValues[0]);
        int gs = get255Value(srcValues[1]);
        int bs = get255Value(srcValues[2]);
        int delta = (((((rs - rd) * 77) + ((gs - gd) * 151)) + ((bs - bd) * 28)) + 128) >> 8;
        int r = rd + delta;
        int g = gd + delta;
        int b = bd + delta;
        if (((r | g | b) & 256) == 256) {
            int y = ((((rs * 77) + (gs * 151)) + (bs * 28)) + 128) >> 8;
            if (delta > 0) {
                int max = Math.max(r, Math.max(g, b));
                scale = max == y ? 0 : ((255 - y) << 16) / (max - y);
            } else {
                int min = Math.min(r, Math.min(g, b));
                scale = y == min ? 0 : (y << 16) / (y - min);
            }
            r = y + ((((r - y) * scale) + 32768) >> 16);
            g = y + ((((g - y) * scale) + 32768) >> 16);
            b = y + ((((b - y) * scale) + 32768) >> 16);
        }
        result[0] = r / 255.0f;
        result[1] = g / 255.0f;
        result[2] = b / 255.0f;
    }
}
