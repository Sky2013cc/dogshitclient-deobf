package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0007\n��\n\u0002\u0018\u0002\n\u0002\b\t\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lnet/ccbluex/liquidbounce/event/JumpEvent;", "Lnet/ccbluex/liquidbounce/event/CancellableEvent;", "motion", "", "eventState", "Lnet/ccbluex/liquidbounce/event/EventState;", Constants.CTOR, "(FLnet/ccbluex/liquidbounce/event/EventState;)V", "getMotion", "()F", "setMotion", "(F)V", "getEventState", "()Lnet/ccbluex/liquidbounce/event/EventState;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/JumpEvent.class */
public final class JumpEvent extends CancellableEvent {
    private float motion;

    @NotNull
    private final EventState eventState;

    public JumpEvent(float motion, @NotNull EventState eventState) {
        Intrinsics.checkNotNullParameter(eventState, "eventState");
        this.motion = motion;
        this.eventState = eventState;
    }

    public final float getMotion() {
        return this.motion;
    }

    public final void setMotion(float f) {
        this.motion = f;
    }

    @NotNull
    public final EventState getEventState() {
        return this.eventState;
    }
}
