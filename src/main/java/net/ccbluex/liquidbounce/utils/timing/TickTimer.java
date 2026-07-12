package net.ccbluex.liquidbounce.utils.timing;

import kotlin.Metadata;
import org.spongepowered.asm.util.Constants;

/* compiled from: TickTimer.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\f"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/timing/TickTimer;", "", Constants.CTOR, "()V", "tick", "", "update", "", "reset", "hasTimePassed", "", "ticks", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/timing/TickTimer.class */
public final class TickTimer {
    private int tick;

    public final void update() {
        this.tick++;
    }

    public final void reset() {
        this.tick = 0;
    }

    public final boolean hasTimePassed(int ticks) {
        return this.tick >= ticks;
    }
}
