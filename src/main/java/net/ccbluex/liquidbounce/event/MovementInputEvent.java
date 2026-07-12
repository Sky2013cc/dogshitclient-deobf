package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.MovementInput;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\t"}, d2 = {"Lnet/ccbluex/liquidbounce/event/MovementInputEvent;", "Lnet/ccbluex/liquidbounce/event/Event;", "originalInput", "Lnet/minecraft/util/MovementInput;", Constants.CTOR, "(Lnet/minecraft/util/MovementInput;)V", "getOriginalInput", "()Lnet/minecraft/util/MovementInput;", "setOriginalInput", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/MovementInputEvent.class */
public final class MovementInputEvent extends Event {

    @NotNull
    private MovementInput originalInput;

    public MovementInputEvent(@NotNull MovementInput originalInput) {
        Intrinsics.checkNotNullParameter(originalInput, "originalInput");
        this.originalInput = originalInput;
    }

    @NotNull
    public final MovementInput getOriginalInput() {
        return this.originalInput;
    }

    public final void setOriginalInput(@NotNull MovementInput movementInput) {
        Intrinsics.checkNotNullParameter(movementInput, "<set-?>");
        this.originalInput = movementInput;
    }
}
