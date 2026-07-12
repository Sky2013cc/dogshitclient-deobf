package net.ccbluex.liquidbounce.features.module.modules.combat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: KillAura.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/combat/SwingFailData;", "", "vec3", "Lnet/minecraft/util/Vec3;", "startTime", "", Constants.CTOR, "(Lnet/minecraft/util/Vec3;J)V", "getVec3", "()Lnet/minecraft/util/Vec3;", "getStartTime", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/combat/SwingFailData.class */
public final class SwingFailData {

    @NotNull
    private final Vec3 vec3;
    private final long startTime;

    @NotNull
    public final Vec3 component1() {
        return this.vec3;
    }

    public final long component2() {
        return this.startTime;
    }

    @NotNull
    public final SwingFailData copy(@NotNull Vec3 vec3, long startTime) {
        Intrinsics.checkNotNullParameter(vec3, "vec3");
        return new SwingFailData(vec3, startTime);
    }

    public static /* synthetic */ SwingFailData copy$default(SwingFailData swingFailData, Vec3 vec3, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            vec3 = swingFailData.vec3;
        }
        if ((i & 2) != 0) {
            j = swingFailData.startTime;
        }
        return swingFailData.copy(vec3, j);
    }

    @NotNull
    public String toString() {
        return "SwingFailData(vec3=" + this.vec3 + ", startTime=" + this.startTime + ')';
    }

    public int hashCode() {
        int result = this.vec3.hashCode();
        return (result * 31) + Long.hashCode(this.startTime);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SwingFailData)) {
            return false;
        }
        SwingFailData swingFailData = (SwingFailData) other;
        return Intrinsics.areEqual(this.vec3, swingFailData.vec3) && this.startTime == swingFailData.startTime;
    }

    public SwingFailData(@NotNull Vec3 vec3, long startTime) {
        Intrinsics.checkNotNullParameter(vec3, "vec3");
        this.vec3 = vec3;
        this.startTime = startTime;
    }

    @NotNull
    public final Vec3 getVec3() {
        return this.vec3;
    }

    public final long getStartTime() {
        return this.startTime;
    }
}
