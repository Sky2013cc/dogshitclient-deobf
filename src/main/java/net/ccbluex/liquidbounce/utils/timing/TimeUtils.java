package net.ccbluex.liquidbounce.utils.timing;

import kotlin.Metadata;
import kotlin.math.MathKt;
import net.ccbluex.liquidbounce.utils.extensions.MathExtensionsKt;
import net.ccbluex.liquidbounce.utils.kotlin.RandomUtils;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: TimeUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u0016\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005¨\u0006\u000b"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/timing/TimeUtils;", "", Constants.CTOR, "()V", "randomDelay", "", "minDelay", "maxDelay", "randomClickDelay", "minCPS", "maxCPS", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/timing/TimeUtils.class */
public final class TimeUtils {

    @NotNull
    public static final TimeUtils INSTANCE = new TimeUtils();

    private TimeUtils() {
    }

    public final int randomDelay(int minDelay, int maxDelay) {
        return RandomUtils.INSTANCE.nextInt(minDelay, maxDelay + 1);
    }

    public final int randomClickDelay(int minCPS, int maxCPS) {
        float minDelay = MathExtensionsKt.safeDiv(1000, minCPS);
        float maxDelay = MathExtensionsKt.safeDiv(1000, maxCPS);
        return MathKt.roundToInt((Math.random() * (minDelay - maxDelay)) + maxDelay);
    }
}
