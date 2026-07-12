package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\n\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lnet/ccbluex/liquidbounce/event/ClientSlotChangeEvent;", "Lnet/ccbluex/liquidbounce/event/Event;", "supposedSlot", "", "modifiedSlot", Constants.CTOR, "(II)V", "getSupposedSlot", "()I", "setSupposedSlot", "(I)V", "getModifiedSlot", "setModifiedSlot", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/ClientSlotChangeEvent.class */
public final class ClientSlotChangeEvent extends Event {
    private int supposedSlot;
    private int modifiedSlot;

    public ClientSlotChangeEvent(int supposedSlot, int modifiedSlot) {
        this.supposedSlot = supposedSlot;
        this.modifiedSlot = modifiedSlot;
    }

    public final int getSupposedSlot() {
        return this.supposedSlot;
    }

    public final void setSupposedSlot(int i) {
        this.supposedSlot = i;
    }

    public final int getModifiedSlot() {
        return this.modifiedSlot;
    }

    public final void setModifiedSlot(int i) {
        this.modifiedSlot = i;
    }
}
