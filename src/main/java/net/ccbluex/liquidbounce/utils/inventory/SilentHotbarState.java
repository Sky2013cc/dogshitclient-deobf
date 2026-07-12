package net.ccbluex.liquidbounce.utils.inventory;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: SilentHotbar.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\u0018��2\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0017¨\u0006\u0019"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/inventory/SilentHotbarState;", "", "enforcedSlot", "", "requester", "resetTicks", "render", "", "resetManually", Constants.CTOR, "(ILjava/lang/Object;Ljava/lang/Integer;ZZ)V", "getEnforcedSlot", "()I", "getRequester", "()Ljava/lang/Object;", "setRequester", "(Ljava/lang/Object;)V", "getResetTicks", "()Ljava/lang/Integer;", "setResetTicks", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getRender", "()Z", "getResetManually", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/inventory/SilentHotbarState.class */
public final class SilentHotbarState {
    private final int enforcedSlot;

    @Nullable
    private Object requester;

    @Nullable
    private Integer resetTicks;
    private final boolean render;
    private final boolean resetManually;

    public SilentHotbarState(int enforcedSlot, @Nullable Object requester, @Nullable Integer resetTicks, boolean render, boolean resetManually) {
        this.enforcedSlot = enforcedSlot;
        this.requester = requester;
        this.resetTicks = resetTicks;
        this.render = render;
        this.resetManually = resetManually;
    }

    public final int getEnforcedSlot() {
        return this.enforcedSlot;
    }

    @Nullable
    public final Object getRequester() {
        return this.requester;
    }

    public final void setRequester(@Nullable Object obj) {
        this.requester = obj;
    }

    @Nullable
    public final Integer getResetTicks() {
        return this.resetTicks;
    }

    public final void setResetTicks(@Nullable Integer num) {
        this.resetTicks = num;
    }

    public final boolean getRender() {
        return this.render;
    }

    public final boolean getResetManually() {
        return this.resetManually;
    }
}
