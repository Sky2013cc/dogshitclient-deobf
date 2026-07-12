package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lnet/ccbluex/liquidbounce/event/PlayerTickEvent;", "Lnet/ccbluex/liquidbounce/event/CancellableEvent;", "state", "Lnet/ccbluex/liquidbounce/event/EventState;", Constants.CTOR, "(Lnet/ccbluex/liquidbounce/event/EventState;)V", "getState", "()Lnet/ccbluex/liquidbounce/event/EventState;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/PlayerTickEvent.class */
public final class PlayerTickEvent extends CancellableEvent {

    @NotNull
    private final EventState state;

    public PlayerTickEvent(@NotNull EventState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    @NotNull
    public final EventState getState() {
        return this.state;
    }
}
