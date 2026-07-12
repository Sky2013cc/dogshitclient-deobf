package net.ccbluex.liquidbounce.utils.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.MovementInput;
import org.jetbrains.annotations.NotNull;

/* compiled from: InputExtensions.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\u0014\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\n\u0010��\u001a\u00020\u0001*\u00020\u0002\"\u0015\u0010\u0003\u001a\u00020\u0004*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005\"\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005¨\u0006\u0007"}, d2 = {"reset", "", "Lnet/minecraft/util/MovementInput;", "isSideways", "", "(Lnet/minecraft/util/MovementInput;)Z", "isMoving", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/extensions/InputExtensionsKt.class */
public final class InputExtensionsKt {
    public static final void reset(@NotNull MovementInput $this$reset) {
        Intrinsics.checkNotNullParameter($this$reset, "<this>");
        $this$reset.field_78900_b = 0.0f;
        $this$reset.field_78902_a = 0.0f;
        $this$reset.field_78901_c = false;
        $this$reset.field_78899_d = false;
    }

    public static final boolean isSideways(@NotNull MovementInput $this$isSideways) {
        Intrinsics.checkNotNullParameter($this$isSideways, "<this>");
        if (!($this$isSideways.field_78900_b == 0.0f)) {
            if (!($this$isSideways.field_78902_a == 0.0f)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isMoving(@NotNull MovementInput $this$isMoving) {
        Intrinsics.checkNotNullParameter($this$isMoving, "<this>");
        if ($this$isMoving.field_78900_b == 0.0f) {
            if ($this$isMoving.field_78902_a == 0.0f) {
                return false;
            }
        }
        return true;
    }
}
