package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u000b\u0018��2\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\n¨\u0006\u000e"}, d2 = {"Lnet/ccbluex/liquidbounce/event/ClickWindowEvent;", "Lnet/ccbluex/liquidbounce/event/CancellableEvent;", "windowId", "", "slotId", "mouseButtonClicked", "mode", Constants.CTOR, "(IIII)V", "getWindowId", "()I", "getSlotId", "getMouseButtonClicked", "getMode", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/ClickWindowEvent.class */
public final class ClickWindowEvent extends CancellableEvent {
    private final int windowId;
    private final int slotId;
    private final int mouseButtonClicked;
    private final int mode;

    public final int getWindowId() {
        return this.windowId;
    }

    public final int getSlotId() {
        return this.slotId;
    }

    public final int getMouseButtonClicked() {
        return this.mouseButtonClicked;
    }

    public final int getMode() {
        return this.mode;
    }

    public ClickWindowEvent(int windowId, int slotId, int mouseButtonClicked, int mode) {
        this.windowId = windowId;
        this.slotId = slotId;
        this.mouseButtonClicked = mouseButtonClicked;
        this.mode = mode;
    }
}
