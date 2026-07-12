package net.ccbluex.liquidbounce.features.module.modules.combat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.utils.rotation.Rotation;
import net.minecraft.util.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: FakeLag.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u0007\n��\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/combat/PositionData;", "", "pos", "Lnet/minecraft/util/Vec3;", "time", "", "body", "", "rotation", "Lnet/ccbluex/liquidbounce/utils/rotation/Rotation;", Constants.CTOR, "(Lnet/minecraft/util/Vec3;JFLnet/ccbluex/liquidbounce/utils/rotation/Rotation;)V", "getPos", "()Lnet/minecraft/util/Vec3;", "getTime", "()J", "getBody", "()F", "getRotation", "()Lnet/ccbluex/liquidbounce/utils/rotation/Rotation;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/combat/PositionData.class */
public final class PositionData {

    @NotNull
    private final Vec3 pos;
    private final long time;
    private final float body;

    @NotNull
    private final Rotation rotation;

    @NotNull
    public final Vec3 component1() {
        return this.pos;
    }

    public final long component2() {
        return this.time;
    }

    public final float component3() {
        return this.body;
    }

    @NotNull
    public final Rotation component4() {
        return this.rotation;
    }

    @NotNull
    public final PositionData copy(@NotNull Vec3 pos, long time, float body, @NotNull Rotation rotation) {
        Intrinsics.checkNotNullParameter(pos, "pos");
        Intrinsics.checkNotNullParameter(rotation, "rotation");
        return new PositionData(pos, time, body, rotation);
    }

    public static /* synthetic */ PositionData copy$default(PositionData positionData, Vec3 vec3, long j, float f, Rotation rotation, int i, Object obj) {
        if ((i & 1) != 0) {
            vec3 = positionData.pos;
        }
        if ((i & 2) != 0) {
            j = positionData.time;
        }
        if ((i & 4) != 0) {
            f = positionData.body;
        }
        if ((i & 8) != 0) {
            rotation = positionData.rotation;
        }
        return positionData.copy(vec3, j, f, rotation);
    }

    @NotNull
    public String toString() {
        return "PositionData(pos=" + this.pos + ", time=" + this.time + ", body=" + this.body + ", rotation=" + this.rotation + ')';
    }

    public int hashCode() {
        int result = this.pos.hashCode();
        return (((((result * 31) + Long.hashCode(this.time)) * 31) + Float.hashCode(this.body)) * 31) + this.rotation.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PositionData)) {
            return false;
        }
        PositionData positionData = (PositionData) other;
        return Intrinsics.areEqual(this.pos, positionData.pos) && this.time == positionData.time && Float.compare(this.body, positionData.body) == 0 && Intrinsics.areEqual(this.rotation, positionData.rotation);
    }

    public PositionData(@NotNull Vec3 pos, long time, float body, @NotNull Rotation rotation) {
        Intrinsics.checkNotNullParameter(pos, "pos");
        Intrinsics.checkNotNullParameter(rotation, "rotation");
        this.pos = pos;
        this.time = time;
        this.body = body;
        this.rotation = rotation;
    }

    @NotNull
    public final Vec3 getPos() {
        return this.pos;
    }

    public final long getTime() {
        return this.time;
    }

    public final float getBody() {
        return this.body;
    }

    @NotNull
    public final Rotation getRotation() {
        return this.rotation;
    }
}
