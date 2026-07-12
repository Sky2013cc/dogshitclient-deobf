package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import org.spongepowered.asm.util.Constants;

/* compiled from: Event.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\b&\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tR\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lnet/ccbluex/liquidbounce/event/CancellableEvent;", "Lnet/ccbluex/liquidbounce/event/Event;", Constants.CTOR, "()V", "value", "", "isCancelled", "()Z", "cancelEvent", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/CancellableEvent.class */
public abstract class CancellableEvent extends Event {
    private boolean isCancelled;

    public final boolean isCancelled() {
        return this.isCancelled;
    }

    public final void cancelEvent() {
        this.isCancelled = true;
    }
}
