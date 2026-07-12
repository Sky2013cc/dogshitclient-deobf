package net.ccbluex.liquidbounce.utils.rotation;

import javax.vecmath.Vector2f;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.ccbluex.liquidbounce.event.StrafeEvent;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.extensions.MathExtensionsKt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Rotation.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018�� .2\u00020\u0001:\u0001.B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\u0010\u001a\u00020��2\u0006\u0010\u0011\u001a\u00020��H\u0086\u0002J\u0011\u0010\u0012\u001a\u00020��2\u0006\u0010\u0011\u001a\u00020��H\u0086\u0002J\u0011\u0010\u0013\u001a\u00020��2\u0006\u0010\u0014\u001a\u00020\u0003H\u0086\u0002J\u0011\u0010\u0015\u001a\u00020��2\u0006\u0010\u0014\u001a\u00020\u0003H\u0086\u0002J\u000e\u0010\u0016\u001a\u00020��2\u0006\u0010\u0011\u001a\u00020��J$\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001cJ\u0010\u0010\u001e\u001a\u00020��2\b\b\u0002\u0010\u001f\u001a\u00020\u0003J\u0018\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u001cJ\u0010\u0010$\u001a\u00020��2\b\b\u0002\u0010\u0014\u001a\u00020\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u001d\u0010'\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010(\u001a\u00020\u001c2\b\u0010\u0011\u001a\u0004\u0018\u00010)HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020-HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020��8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006/"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/rotation/Rotation;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", "yaw", "", "pitch", Constants.CTOR, "(FF)V", "getYaw", "()F", "setYaw", "(F)V", "getPitch", "setPitch", "abs", "getAbs", "()Lnet/ccbluex/liquidbounce/utils/rotation/Rotation;", "minus", "other", "plus", "times", "value", "div", "plusDiff", "toPlayer", "", "player", "Lnet/minecraft/entity/player/EntityPlayer;", "changeYaw", "", "changePitch", "fixedSensitivity", "sensitivity", "applyStrafeToPlayer", "event", "Lnet/ccbluex/liquidbounce/event/StrafeEvent;", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTRVALUE_STRICT, "withLimitedPitch", "component1", "component2", "copy", "equals", "", "hashCode", "", "toString", "", "Companion", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/rotation/Rotation.class */
public final class Rotation implements MinecraftInstance {
    private float yaw;
    private float pitch;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final Rotation ZERO = new Rotation(0.0f, 0.0f);

    public final float component1() {
        return this.yaw;
    }

    public final float component2() {
        return this.pitch;
    }

    @NotNull
    public final Rotation copy(float yaw, float pitch) {
        return new Rotation(yaw, pitch);
    }

    public static /* synthetic */ Rotation copy$default(Rotation rotation, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = rotation.yaw;
        }
        if ((i & 2) != 0) {
            f2 = rotation.pitch;
        }
        return rotation.copy(f, f2);
    }

    @NotNull
    public String toString() {
        return "Rotation(yaw=" + this.yaw + ", pitch=" + this.pitch + ')';
    }

    public int hashCode() {
        int result = Float.hashCode(this.yaw);
        return (result * 31) + Float.hashCode(this.pitch);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Rotation)) {
            return false;
        }
        Rotation rotation = (Rotation) other;
        return Float.compare(this.yaw, rotation.yaw) == 0 && Float.compare(this.pitch, rotation.pitch) == 0;
    }

    public Rotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public final float getYaw() {
        return this.yaw;
    }

    public final void setYaw(float f) {
        this.yaw = f;
    }

    public final float getPitch() {
        return this.pitch;
    }

    public final void setPitch(float f) {
        this.pitch = f;
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    @NotNull
    public final Rotation getAbs() {
        return new Rotation(Math.abs(this.yaw), Math.abs(this.pitch));
    }

    @NotNull
    public final Rotation minus(@NotNull Rotation other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return new Rotation(this.yaw - other.yaw, this.pitch - other.pitch);
    }

    @NotNull
    public final Rotation plus(@NotNull Rotation other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return new Rotation(this.yaw + other.yaw, this.pitch + other.pitch);
    }

    @NotNull
    public final Rotation times(float value) {
        return new Rotation(this.yaw * value, this.pitch * value);
    }

    @NotNull
    public final Rotation div(float value) {
        return new Rotation(this.yaw / value, this.pitch / value);
    }

    /* compiled from: Rotation.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/rotation/Rotation$Companion;", "", Constants.CTOR, "()V", "ZERO", "Lnet/ccbluex/liquidbounce/utils/rotation/Rotation;", "getZERO", "()Lnet/ccbluex/liquidbounce/utils/rotation/Rotation;", "of", "vec", "Ljavax/vecmath/Vector2f;", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/rotation/Rotation$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final Rotation getZERO() {
            return Rotation.ZERO;
        }

        @NotNull
        public final Rotation of(@NotNull Vector2f vec) {
            Intrinsics.checkNotNullParameter(vec, "vec");
            return new Rotation(vec.x, vec.y);
        }
    }

    @NotNull
    public final Rotation plusDiff(@NotNull Rotation other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return plus(Companion.of(RotationUtils.INSTANCE.angleDifferences(other, this)));
    }

    public static /* synthetic */ void toPlayer$default(Rotation rotation, EntityPlayer entityPlayer, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            entityPlayer = (EntityPlayer) rotation.getMc().field_71439_g;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        rotation.toPlayer(entityPlayer, z, z2);
    }

    public final void toPlayer(@NotNull EntityPlayer player, boolean changeYaw, boolean changePitch) {
        Intrinsics.checkNotNullParameter(player, "player");
        if (Float.isNaN(this.yaw) || Float.isNaN(this.pitch) || this.pitch > 90.0f || this.pitch < -90.0f) {
            return;
        }
        fixedSensitivity$default(this, 0.0f, 1, null);
        if (changeYaw) {
            player.field_70177_z = this.yaw;
        }
        if (changePitch) {
            player.field_70125_A = this.pitch;
        }
    }

    public static /* synthetic */ Rotation fixedSensitivity$default(Rotation rotation, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            f = rotation.getMc().field_71474_y.field_74341_c;
        }
        return rotation.fixedSensitivity(f);
    }

    @NotNull
    public final Rotation fixedSensitivity(float sensitivity) {
        float gcd = RotationUtils.INSTANCE.getFixedAngleDelta(sensitivity);
        this.yaw = RotationUtils.INSTANCE.getFixedSensitivityAngle(this.yaw, RotationUtils.INSTANCE.getServerRotation().yaw, gcd);
        this.pitch = RotationUtils.INSTANCE.getFixedSensitivityAngle(this.pitch, RotationUtils.INSTANCE.getServerRotation().pitch, gcd);
        return withLimitedPitch$default(this, 0.0f, 1, null);
    }

    public static /* synthetic */ void applyStrafeToPlayer$default(Rotation rotation, StrafeEvent strafeEvent, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        rotation.applyStrafeToPlayer(strafeEvent, z);
    }

    public final void applyStrafeToPlayer(@NotNull StrafeEvent event, boolean strict) {
        float calcForward;
        float calcStrafe;
        Intrinsics.checkNotNullParameter(event, "event");
        EntityPlayerSP player = getMc().field_71439_g;
        float diff = MathExtensionsKt.toRadians(player.field_70177_z - this.yaw);
        float friction = event.getFriction();
        if (!strict) {
            Pair pair = new Pair(Float.valueOf(event.getStrafe() / 0.98f), Float.valueOf(event.getForward() / 0.98f));
            float strafe = ((Number) pair.component1()).floatValue();
            float forward = ((Number) pair.component2()).floatValue();
            float modifiedForward = ((float) Math.ceil(Math.abs(forward))) * Math.signum(forward);
            float modifiedStrafe = ((float) Math.ceil(Math.abs(strafe))) * Math.signum(strafe);
            float calcForward2 = (float) Math.rint((modifiedForward * MathHelper.func_76134_b(diff)) + (modifiedStrafe * MathHelper.func_76126_a(diff)));
            float calcStrafe2 = (float) Math.rint((modifiedStrafe * MathHelper.func_76134_b(diff)) - (modifiedForward * MathHelper.func_76126_a(diff)));
            float f = !((event.getForward() > 0.0f ? 1 : (event.getForward() == 0.0f ? 0 : -1)) == 0) ? event.getForward() : event.getStrafe();
            calcForward = calcForward2 * Math.abs(f);
            calcStrafe = calcStrafe2 * Math.abs(f);
        } else {
            calcForward = event.getForward();
            calcStrafe = event.getStrafe();
        }
        float d = (calcStrafe * calcStrafe) + (calcForward * calcForward);
        if (d >= 1.0E-4f) {
            float d2 = friction / RangesKt.coerceAtLeast((float) Math.sqrt(d), 1.0f);
            float calcStrafe3 = calcStrafe * d2;
            float calcForward3 = calcForward * d2;
            float yawRad = MathExtensionsKt.toRadians(this.yaw);
            float yawSin = MathHelper.func_76126_a(yawRad);
            float yawCos = MathHelper.func_76134_b(yawRad);
            player.field_70159_w += (calcStrafe3 * yawCos) - (calcForward3 * yawSin);
            player.field_70179_y += (calcForward3 * yawCos) + (calcStrafe3 * yawSin);
        }
    }

    public static /* synthetic */ Rotation withLimitedPitch$default(Rotation rotation, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 90.0f;
        }
        return rotation.withLimitedPitch(f);
    }

    @NotNull
    public final Rotation withLimitedPitch(float value) {
        this.pitch = RangesKt.coerceIn(this.pitch, -value, value);
        return this;
    }
}
