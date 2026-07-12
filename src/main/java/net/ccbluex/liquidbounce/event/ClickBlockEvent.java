package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018��2\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lnet/ccbluex/liquidbounce/event/ClickBlockEvent;", "Lnet/ccbluex/liquidbounce/event/Event;", "clickedBlock", "Lnet/minecraft/util/BlockPos;", "enumFacing", "Lnet/minecraft/util/EnumFacing;", Constants.CTOR, "(Lnet/minecraft/util/BlockPos;Lnet/minecraft/util/EnumFacing;)V", "getClickedBlock", "()Lnet/minecraft/util/BlockPos;", "getEnumFacing", "()Lnet/minecraft/util/EnumFacing;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/ClickBlockEvent.class */
public final class ClickBlockEvent extends Event {

    @Nullable
    private final BlockPos clickedBlock;

    @Nullable
    private final EnumFacing enumFacing;

    public ClickBlockEvent(@Nullable BlockPos clickedBlock, @Nullable EnumFacing enumFacing) {
        this.clickedBlock = clickedBlock;
        this.enumFacing = enumFacing;
    }

    @Nullable
    public final BlockPos getClickedBlock() {
        return this.clickedBlock;
    }

    @Nullable
    public final EnumFacing getEnumFacing() {
        return this.enumFacing;
    }
}
