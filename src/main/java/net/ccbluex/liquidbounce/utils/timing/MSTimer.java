package net.ccbluex.liquidbounce.utils.timing;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: MSTimer.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u000e"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/timing/MSTimer;", "", Constants.CTOR, "()V", "time", "", "hasTimePassed", "", "ms", "", "hasTimeLeft", "reset", "", "zero", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/timing/MSTimer.class */
public final class MSTimer {
    private long time = -1;

    public final boolean hasTimePassed(@NotNull Number ms) {
        Intrinsics.checkNotNullParameter(ms, "ms");
        return System.currentTimeMillis() >= this.time + ms.longValue();
    }

    public final long hasTimeLeft(@NotNull Number ms) {
        Intrinsics.checkNotNullParameter(ms, "ms");
        return (ms.longValue() + this.time) - System.currentTimeMillis();
    }

    public final void reset() {
        this.time = System.currentTimeMillis();
    }

    public final void zero() {
        this.time = -1L;
    }
}
