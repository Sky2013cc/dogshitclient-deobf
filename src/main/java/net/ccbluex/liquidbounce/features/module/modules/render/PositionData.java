package net.ccbluex.liquidbounce.features.module.modules.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Breadcrumbs.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0013\n��\n\u0002\u0010\t\n\u0002\b\u0007\b\u0002\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/render/PositionData;", "", "array", "", "time", "", Constants.CTOR, "([DJ)V", "getArray", "()[D", "getTime", "()J", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/render/PositionData.class */
public final class PositionData {

    @NotNull
    private final double[] array;
    private final long time;

    public PositionData(@NotNull double[] array, long time) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.array = array;
        this.time = time;
    }

    @NotNull
    public final double[] getArray() {
        return this.array;
    }

    public final long getTime() {
        return this.time;
    }
}
