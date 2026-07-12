package net.ccbluex.liquidbounce.utils.render;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import kotlin.Metadata;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: AnimationUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005¨\u0006\n"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/AnimationUtils;", "", Constants.CTOR, "()V", "easeOut", "", SimpleTaglet.TYPE, OperatorName.SET_LINE_DASHPATTERN, "easeOutElastic", SimpleTaglet.EXCLUDED, "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/AnimationUtils.class */
public final class AnimationUtils {

    @NotNull
    public static final AnimationUtils INSTANCE = new AnimationUtils();

    private AnimationUtils() {
    }

    public final float easeOut(float t, float d) {
        return ((float) Math.pow((t / d) - 1, 3)) + 1;
    }

    public final float easeOutElastic(float x) {
        if (!(x == 0.0f)) {
            if (!(x == 1.0f)) {
                return (((float) Math.pow(2.0f, (-10) * x)) * ((float) Math.sin(((x * 10) - 0.75f) * 2.0943952f))) + 1;
            }
        }
        return x;
    }
}
