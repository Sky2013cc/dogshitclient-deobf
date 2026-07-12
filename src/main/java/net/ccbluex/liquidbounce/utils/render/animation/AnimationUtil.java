package net.ccbluex.liquidbounce.utils.render.animation;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import com.sun.tools.doclets.internal.toolkit.util.VisibleMemberMap;
import jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants;
import kotlin.Metadata;
import net.minecraft.client.Minecraft;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: AnimationUtil.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0011\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tJ&\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tJ&\u0010\u0013\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tJ&\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tJ&\u0010\u0015\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tJ&\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tJ&\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tJ&\u0010\u001c\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tJ&\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tJ&\u0010\u001e\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tJ\u000e\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006 "}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/animation/AnimationUtil;", "", Constants.CTOR, "()V", "debugFPS", "", "getDebugFPS", "()F", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_BASE, "", "current", "target", "speed", "linear", "startTime", "", "duration", VisibleMemberMap.STARTLEVEL, AsmConstants.END, "easeInQuad", "easeOutQuad", "easeInOutQuad", "easeInElastic", SimpleTaglet.TYPE, OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "c", OperatorName.SET_LINE_DASHPATTERN, "easeOutElastic", "easeInOutElastic", "easeInBack", "easeOutBack", "breathe", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/animation/AnimationUtil.class */
public final class AnimationUtil {

    @NotNull
    public static final AnimationUtil INSTANCE = new AnimationUtil();

    private AnimationUtil() {
    }

    public final float getDebugFPS() {
        return Math.max(Minecraft.func_175610_ah(), 60.0f);
    }

    public final double base(double current, double target, double speed) {
        return ((int) ((current + ((target - current) * (speed / (getDebugFPS() / 60.0d)))) * 1000)) / 1000.0d;
    }

    public final double linear(long startTime, long duration, double start, double end) {
        return ((end - start) * (((System.currentTimeMillis() - startTime) * 1.0d) / duration)) + start;
    }

    public final double easeInQuad(long startTime, long duration, double start, double end) {
        return ((end - start) * Math.pow(((System.currentTimeMillis() - startTime) * 1.0d) / duration, 2.0d)) + start;
    }

    public final double easeOutQuad(long startTime, long duration, double start, double end) {
        float x = (((float) (System.currentTimeMillis() - startTime)) * 1.0f) / ((float) duration);
        float y = ((-x) * x) + (2 * x);
        return start + ((end - start) * y);
    }

    public final double easeInOutQuad(long startTime, long duration, double start, double end) {
        float t = ((((float) (System.currentTimeMillis() - startTime)) * 1.0f) / ((float) duration)) * 2.0f;
        if (t < 1.0f) {
            return (((end - start) / 2) * t * t) + start;
        }
        float t2 = t - 1.0f;
        return (((-(end - start)) / 2) * ((t2 * (t2 - 2)) - 1)) + start;
    }

    public final double easeInElastic(double t, double b, double c, double d) {
        double s;
        double a = c;
        if (t == 0.0d) {
            return b;
        }
        double t2 = t / d;
        if (t2 == 1.0d) {
            return b + c;
        }
        double p = d * 0.3d;
        if (a < Math.abs(c)) {
            a = c;
            s = p / 4.0d;
        } else {
            s = (p / 6.283185307179586d) * Math.asin(c / a);
        }
        double t3 = t2 - 1.0d;
        return (-(a * Math.pow(2.0d, 10 * t3) * Math.sin((((t3 * d) - s) * 6.283185307179586d) / p))) + b;
    }

    public final double easeOutElastic(double t, double b, double c, double d) {
        double s;
        double a = c;
        if (t == 0.0d) {
            return b;
        }
        double t2 = t / d;
        if (t2 == 1.0d) {
            return b + c;
        }
        double p = d * 0.3d;
        if (a < Math.abs(c)) {
            a = c;
            s = p / 4.0d;
        } else {
            s = (p / 6.283185307179586d) * Math.asin(c / a);
        }
        return (a * Math.pow(2.0d, (-10) * t2) * Math.sin((((t2 * d) - s) * 6.283185307179586d) / p)) + c + b;
    }

    public final double easeInOutElastic(double t, double b, double c, double d) {
        double s;
        double a = c;
        if (t == 0.0d) {
            return b;
        }
        double t2 = t / (d / 2);
        if (t2 == 2.0d) {
            return b + c;
        }
        double p = d * 0.44999999999999996d;
        if (a < Math.abs(c)) {
            a = c;
            s = p / 4.0d;
        } else {
            s = (p / 6.283185307179586d) * Math.asin(c / a);
        }
        if (t2 < 1.0d) {
            double t3 = t2 - 1.0d;
            return ((-0.5d) * a * Math.pow(2.0d, 10 * t3) * Math.sin((((t3 * d) - s) * 6.283185307179586d) / p)) + b;
        }
        double t4 = t2 - 1.0d;
        return (a * Math.pow(2.0d, (-10) * t4) * Math.sin((((t4 * d) - s) * 6.283185307179586d) / p) * 0.5d) + c + b;
    }

    public final double easeInBack(double t, double b, double c, double d) {
        double t2 = t / d;
        return (c * t2 * t2 * (((1.70158d + 1) * t2) - 1.70158d)) + b;
    }

    public final double easeOutBack(double t, double b, double c, double d) {
        double t2 = (t / d) - 1;
        return (c * ((t2 * t2 * (((1.70158d + 1) * t2) + 1.70158d)) + 1)) + b;
    }

    public final float breathe(float duration) {
        float progress = ((float) (System.currentTimeMillis() % duration)) / duration;
        return 0.5f * ((float) (Math.sin(6.283185307179586d * progress) + 1));
    }
}
