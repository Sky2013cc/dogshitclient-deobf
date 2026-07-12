package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0007\n\u0002\b\n\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lnet/ccbluex/liquidbounce/event/SlowDownEvent;", "Lnet/ccbluex/liquidbounce/event/Event;", "strafe", "", "forward", Constants.CTOR, "(FF)V", "getStrafe", "()F", "setStrafe", "(F)V", "getForward", "setForward", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/SlowDownEvent.class */
public final class SlowDownEvent extends Event {
    private float strafe;
    private float forward;

    public SlowDownEvent(float strafe, float forward) {
        this.strafe = strafe;
        this.forward = forward;
    }

    public final float getStrafe() {
        return this.strafe;
    }

    public final void setStrafe(float f) {
        this.strafe = f;
    }

    public final float getForward() {
        return this.forward;
    }

    public final void setForward(float f) {
        this.forward = f;
    }
}
