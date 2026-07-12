package net.ccbluex.liquidbounce.event;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0007\u0018��2\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0011¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u0011¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0013¨\u0006\u0018"}, d2 = {"Lnet/ccbluex/liquidbounce/event/BlockBBEvent;", "Lnet/ccbluex/liquidbounce/event/Event;", "blockPos", "Lnet/minecraft/util/BlockPos;", Constants.ATTR_BLOCK, "Lnet/minecraft/block/Block;", "boundingBox", "Lnet/minecraft/util/AxisAlignedBB;", org.spongepowered.asm.util.Constants.CTOR, "(Lnet/minecraft/util/BlockPos;Lnet/minecraft/block/Block;Lnet/minecraft/util/AxisAlignedBB;)V", "getBlock", "()Lnet/minecraft/block/Block;", "getBoundingBox", "()Lnet/minecraft/util/AxisAlignedBB;", "setBoundingBox", "(Lnet/minecraft/util/AxisAlignedBB;)V", SimpleTaglet.EXCLUDED, "", "getX", "()I", OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "getY", "z", "getZ", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/BlockBBEvent.class */
public final class BlockBBEvent extends Event {

    @NotNull
    private final Block block;

    @Nullable
    private AxisAlignedBB boundingBox;
    private final int x;
    private final int y;
    private final int z;

    public BlockBBEvent(@NotNull BlockPos blockPos, @NotNull Block block, @Nullable AxisAlignedBB boundingBox) {
        Intrinsics.checkNotNullParameter(blockPos, "blockPos");
        Intrinsics.checkNotNullParameter(block, "block");
        this.block = block;
        this.boundingBox = boundingBox;
        this.x = blockPos.func_177958_n();
        this.y = blockPos.func_177956_o();
        this.z = blockPos.func_177952_p();
    }

    @NotNull
    public final Block getBlock() {
        return this.block;
    }

    @Nullable
    public final AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }

    public final void setBoundingBox(@Nullable AxisAlignedBB axisAlignedBB) {
        this.boundingBox = axisAlignedBB;
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public final int getZ() {
        return this.z;
    }
}
