package net.ccbluex.liquidbounce.utils.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.MovingObjectPosition;
import org.jetbrains.annotations.NotNull;

/* compiled from: MovingObjectExtension.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u0015\u0010��\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b��\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u0006"}, d2 = {"isMiss", "", "Lnet/minecraft/util/MovingObjectPosition$MovingObjectType;", "(Lnet/minecraft/util/MovingObjectPosition$MovingObjectType;)Z", "isBlock", "isEntity", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/extensions/MovingObjectExtensionKt.class */
public final class MovingObjectExtensionKt {
    public static final boolean isMiss(@NotNull MovingObjectPosition.MovingObjectType $this$isMiss) {
        Intrinsics.checkNotNullParameter($this$isMiss, "<this>");
        return $this$isMiss == MovingObjectPosition.MovingObjectType.MISS;
    }

    public static final boolean isBlock(@NotNull MovingObjectPosition.MovingObjectType $this$isBlock) {
        Intrinsics.checkNotNullParameter($this$isBlock, "<this>");
        return $this$isBlock == MovingObjectPosition.MovingObjectType.BLOCK;
    }

    public static final boolean isEntity(@NotNull MovingObjectPosition.MovingObjectType $this$isEntity) {
        Intrinsics.checkNotNullParameter($this$isEntity, "<this>");
        return $this$isEntity == MovingObjectPosition.MovingObjectType.ENTITY;
    }
}
