package net.ccbluex.liquidbounce.utils.timing;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: DelayTimer.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018��2\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\n\u001a\u00020\u000bH\u0016J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\rJ\u0006\u0010\u0010\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0011"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/timing/TickDelayTimer;", "", "minDelayValue", "", "maxDelayValue", "baseTimer", "Lnet/ccbluex/liquidbounce/utils/timing/TickTimer;", Constants.CTOR, "(IILnet/ccbluex/liquidbounce/utils/timing/TickTimer;)V", "ticks", "hasTimePassed", "", "resetTicks", "", "resetTimer", "update", "reset", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/timing/TickDelayTimer.class */
public class TickDelayTimer {
    private final int minDelayValue;
    private final int maxDelayValue;

    @NotNull
    private final TickTimer baseTimer;
    private int ticks;

    public TickDelayTimer(int minDelayValue, int maxDelayValue, @NotNull TickTimer baseTimer) {
        Intrinsics.checkNotNullParameter(baseTimer, "baseTimer");
        this.minDelayValue = minDelayValue;
        this.maxDelayValue = maxDelayValue;
        this.baseTimer = baseTimer;
    }

    public /* synthetic */ TickDelayTimer(int i, int i2, TickTimer tickTimer, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i3 & 2) != 0 ? i : i2, (i3 & 4) != 0 ? new TickTimer() : tickTimer);
    }

    public boolean hasTimePassed() {
        return this.baseTimer.hasTimePassed(this.ticks);
    }

    public final void resetTicks() {
        this.ticks = TimeUtils.INSTANCE.randomDelay(this.minDelayValue, this.maxDelayValue);
    }

    public final void resetTimer() {
        this.baseTimer.reset();
    }

    public final void update() {
        this.baseTimer.update();
    }

    public final void reset() {
        resetTimer();
        resetTicks();
    }
}
