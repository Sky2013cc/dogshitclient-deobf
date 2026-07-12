package net.ccbluex.liquidbounce.utils.movement;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.ccbluex.liquidbounce.event.EventHook;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.Listenable;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.extensions.MathExtensionsKt;
import net.ccbluex.liquidbounce.utils.extensions.PlayerExtensionKt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: MovementUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0006\n��\n\u0002\u0018\u0002\n\u0002\b\u001d\bÆ\u0002\u0018��2\u00020\u00012\u00020\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J9\u0010\u001f\u001a\u0004\u0018\u00010 2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010!\u001a\u00020\u00062\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010$\u001a\u00020%H\u0007¢\u0006\u0002\u0010&J2\u0010\u001f\u001a\u00020'*\u00020'2\b\b\u0002\u0010(\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020%2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010)\u001a\u00020\u0006J\u0015\u0010*\u001a\u0004\u0018\u00010 2\u0006\u0010+\u001a\u00020%¢\u0006\u0002\u0010,J\u000e\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020%R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u0011\u0010-\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b.\u0010/R\u001a\u00102\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b3\u0010\u0015\"\u0004\b4\u00105R\u001a\u00106\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b7\u0010/\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b;\u0010/\"\u0004\b<\u00109R\u001a\u0010=\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b>\u0010/\"\u0004\b?\u00109R\u0013\u0010@\u001a\u00020 ¢\u0006\n\n\u0002\u0010C\u001a\u0004\bA\u0010B¨\u0006D"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/movement/MovementUtils;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", "Lnet/ccbluex/liquidbounce/event/Listenable;", Constants.CTOR, "()V", "affectSprintOnAttack", "", "getAffectSprintOnAttack", "()Ljava/lang/Boolean;", "setAffectSprintOnAttack", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "value", "", "speed", "getSpeed", "()F", "setSpeed", "(F)V", "hasMotion", "getHasMotion", "()Z", "airTicks", "", "getAirTicks", "()I", "setAirTicks", "(I)V", "groundTicks", "getGroundTicks", "setGroundTicks", "strafe", "", "stopWhenNoInput", "moveEvent", "Lnet/ccbluex/liquidbounce/event/MoveEvent;", "strength", "", "(FZLnet/ccbluex/liquidbounce/event/MoveEvent;D)Lkotlin/Unit;", "Lnet/minecraft/util/Vec3;", "yaw", "moveCheck", "forward", "distance", "(D)Lkotlin/Unit;", "direction", "getDirection", "()D", "isOnGround", "height", "serverOnGround", "getServerOnGround", "setServerOnGround", "(Z)V", "serverX", "getServerX", "setServerX", "(D)V", "serverY", "getServerY", "setServerY", "serverZ", "getServerZ", "setServerZ", "onPacket", "getOnPacket", "()Lkotlin/Unit;", "Lkotlin/Unit;", "haze"})
@SourceDebugExtension({"SMAP\nMovementUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MovementUtils.kt\nnet/ccbluex/liquidbounce/utils/movement/MovementUtils\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Listenable.kt\nnet/ccbluex/liquidbounce/event/ListenableKt\n*L\n1#1,139:1\n1#2:140\n22#3,7:141\n*S KotlinDebug\n*F\n+ 1 MovementUtils.kt\nnet/ccbluex/liquidbounce/utils/movement/MovementUtils\n*L\n121#1:141,7\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/movement/MovementUtils.class */
public final class MovementUtils implements MinecraftInstance, Listenable {

    @NotNull
    public static final MovementUtils INSTANCE = new MovementUtils();

    @Nullable
    private static Boolean affectSprintOnAttack;
    private static int airTicks;
    private static int groundTicks;
    private static boolean serverOnGround;
    private static double serverX;
    private static double serverY;
    private static double serverZ;

    @NotNull
    private static final Unit onPacket;

    private MovementUtils() {
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    public boolean handleEvents() {
        return Listenable.DefaultImpls.handleEvents(this);
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    @NotNull
    public Listenable[] getSubListeners() {
        return Listenable.DefaultImpls.getSubListeners(this);
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    @Nullable
    public Listenable getParent() {
        return Listenable.DefaultImpls.getParent(this);
    }

    @Nullable
    public final Boolean getAffectSprintOnAttack() {
        return affectSprintOnAttack;
    }

    public final void setAffectSprintOnAttack(@Nullable Boolean bool) {
        affectSprintOnAttack = bool;
    }

    public final float getSpeed() {
        EntityPlayerSP $this$_get_speed__u24lambda_u240 = getMc().field_71439_g;
        if ($this$_get_speed__u24lambda_u240 != null) {
            return (float) Math.sqrt(($this$_get_speed__u24lambda_u240.field_70159_w * $this$_get_speed__u24lambda_u240.field_70159_w) + ($this$_get_speed__u24lambda_u240.field_70179_y * $this$_get_speed__u24lambda_u240.field_70179_y));
        }
        return 0.0f;
    }

    public final void setSpeed(float value) {
        strafe$default(this, value, false, null, 0.0d, 14, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004b A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004f A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean getHasMotion() {
        boolean z;
        EntityPlayerSP $this$_get_hasMotion__u24lambda_u241 = getMc().field_71439_g;
        if ($this$_get_hasMotion__u24lambda_u241 == null) {
            return false;
        }
        if ($this$_get_hasMotion__u24lambda_u241.field_70159_w == 0.0d) {
            if ($this$_get_hasMotion__u24lambda_u241.field_70181_x == 0.0d) {
                if ($this$_get_hasMotion__u24lambda_u241.field_70179_y == 0.0d) {
                    z = false;
                    return !z;
                }
            }
        }
        z = true;
        if (!z) {
        }
    }

    public final int getAirTicks() {
        return airTicks;
    }

    public final void setAirTicks(int i) {
        airTicks = i;
    }

    public final int getGroundTicks() {
        return groundTicks;
    }

    public final void setGroundTicks(int i) {
        groundTicks = i;
    }

    public static /* synthetic */ Unit strafe$default(MovementUtils movementUtils, float f, boolean z, MoveEvent moveEvent, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            f = INSTANCE.getSpeed();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            moveEvent = null;
        }
        if ((i & 8) != 0) {
            d = 1.0d;
        }
        return movementUtils.strafe(f, z, moveEvent, d);
    }

    @JvmOverloads
    @Nullable
    public final Unit strafe(float speed, boolean stopWhenNoInput, @Nullable MoveEvent moveEvent, double strength) {
        EntityPlayerSP $this$strafe_u24lambda_u242 = getMc().field_71439_g;
        if ($this$strafe_u24lambda_u242 == null) {
            return null;
        }
        if (!PlayerExtensionKt.isMoving(INSTANCE.getMc().field_71439_g)) {
            if (stopWhenNoInput) {
                if (moveEvent != null) {
                    moveEvent.zeroXZ();
                }
                PlayerExtensionKt.stopXZ($this$strafe_u24lambda_u242);
            }
        } else {
            double prevX = $this$strafe_u24lambda_u242.field_70159_w * (1.0d - strength);
            double prevZ = $this$strafe_u24lambda_u242.field_70179_y * (1.0d - strength);
            double useSpeed = speed * strength;
            double yaw = INSTANCE.getDirection();
            double x = ((-Math.sin(yaw)) * useSpeed) + prevX;
            double z = (Math.cos(yaw) * useSpeed) + prevZ;
            if (moveEvent != null) {
                moveEvent.setX(x);
                moveEvent.setZ(z);
            }
            $this$strafe_u24lambda_u242.field_70159_w = x;
            $this$strafe_u24lambda_u242.field_70179_y = z;
        }
        return Unit.INSTANCE;
    }

    @JvmOverloads
    @Nullable
    public final Unit strafe(float speed, boolean stopWhenNoInput, @Nullable MoveEvent moveEvent) {
        return strafe$default(this, speed, stopWhenNoInput, moveEvent, 0.0d, 8, null);
    }

    @JvmOverloads
    @Nullable
    public final Unit strafe(float speed, boolean stopWhenNoInput) {
        return strafe$default(this, speed, stopWhenNoInput, null, 0.0d, 12, null);
    }

    @JvmOverloads
    @Nullable
    public final Unit strafe(float speed) {
        return strafe$default(this, speed, false, null, 0.0d, 14, null);
    }

    @JvmOverloads
    @Nullable
    public final Unit strafe() {
        return strafe$default(this, 0.0f, false, null, 0.0d, 15, null);
    }

    public static /* synthetic */ Vec3 strafe$default(MovementUtils movementUtils, Vec3 vec3, float f, double d, double d2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            f = MathExtensionsKt.toDegreesF(movementUtils.getDirection());
        }
        if ((i & 2) != 0) {
            d = Math.sqrt((vec3.field_72450_a * vec3.field_72450_a) + (vec3.field_72449_c * vec3.field_72449_c));
        }
        if ((i & 4) != 0) {
            d2 = 1.0d;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        return movementUtils.strafe(vec3, f, d, d2, z);
    }

    @NotNull
    public final Vec3 strafe(@NotNull Vec3 $this$strafe, float yaw, double speed, double strength, boolean moveCheck) {
        Intrinsics.checkNotNullParameter($this$strafe, "<this>");
        if (moveCheck) {
            $this$strafe.field_72450_a = 0.0d;
            $this$strafe.field_72449_c = 0.0d;
            return $this$strafe;
        }
        double prevX = $this$strafe.field_72450_a * (1.0d - strength);
        double prevZ = $this$strafe.field_72449_c * (1.0d - strength);
        double useSpeed = speed * strength;
        double angle = Math.toRadians(yaw);
        $this$strafe.field_72450_a = ((-Math.sin(angle)) * useSpeed) + prevX;
        $this$strafe.field_72449_c = (Math.cos(angle) * useSpeed) + prevZ;
        return $this$strafe;
    }

    @Nullable
    public final Unit forward(double distance) {
        EntityPlayerSP $this$forward_u24lambda_u243 = getMc().field_71439_g;
        if ($this$forward_u24lambda_u243 == null) {
            return null;
        }
        double yaw = MathExtensionsKt.toRadiansD($this$forward_u24lambda_u243.field_70177_z);
        $this$forward_u24lambda_u243.func_70107_b($this$forward_u24lambda_u243.field_70165_t - (Math.sin(yaw) * distance), $this$forward_u24lambda_u243.field_70163_u, $this$forward_u24lambda_u243.field_70161_v + (Math.cos(yaw) * distance));
        return Unit.INSTANCE;
    }

    public final double getDirection() {
        EntityPlayerSP $this$_get_direction__u24lambda_u244 = getMc().field_71439_g;
        if ($this$_get_direction__u24lambda_u244 != null) {
            float yaw = $this$_get_direction__u24lambda_u244.field_70177_z;
            float forward = 1.0f;
            if ($this$_get_direction__u24lambda_u244.field_71158_b.field_78900_b < 0.0f) {
                yaw += 180.0f;
                forward = -0.5f;
            } else if ($this$_get_direction__u24lambda_u244.field_71158_b.field_78900_b > 0.0f) {
                forward = 0.5f;
            }
            if ($this$_get_direction__u24lambda_u244.field_71158_b.field_78902_a < 0.0f) {
                yaw += 90.0f * forward;
            } else if ($this$_get_direction__u24lambda_u244.field_71158_b.field_78902_a > 0.0f) {
                yaw -= 90.0f * forward;
            }
            return MathExtensionsKt.toRadiansD(yaw);
        }
        return 0.0d;
    }

    public final boolean isOnGround(double height) {
        if (getMc().field_71441_e != null && getMc().field_71439_g != null) {
            WorldClient worldClient = getMc().field_71441_e;
            Entity entity = getMc().field_71439_g;
            AxisAlignedBB func_174813_aQ = getMc().field_71439_g.func_174813_aQ();
            Intrinsics.checkNotNullExpressionValue(func_174813_aQ, "getEntityBoundingBox(...)");
            List func_72945_a = worldClient.func_72945_a(entity, MathExtensionsKt.offset(func_174813_aQ, MathExtensionsKt.withY$default(MathExtensionsKt.getVec3_ZERO(), -height, false, 2, null)));
            Intrinsics.checkNotNullExpressionValue(func_72945_a, "getCollidingBoundingBoxes(...)");
            if (!func_72945_a.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public final boolean getServerOnGround() {
        return serverOnGround;
    }

    public final void setServerOnGround(boolean z) {
        serverOnGround = z;
    }

    public final double getServerX() {
        return serverX;
    }

    public final void setServerX(double d) {
        serverX = d;
    }

    public final double getServerY() {
        return serverY;
    }

    public final void setServerY(double d) {
        serverY = d;
    }

    public final double getServerZ() {
        return serverZ;
    }

    public final void setServerZ(double d) {
        serverZ = d;
    }

    @NotNull
    public final Unit getOnPacket() {
        return onPacket;
    }

    static {
        Listenable $this$handler_u24default$iv = INSTANCE;
        Function1 action$iv = MovementUtils::onPacket$lambda$5;
        EventManager.INSTANCE.registerEventHook(PacketEvent.class, new EventHook($this$handler_u24default$iv, false, (byte) 0, action$iv));
        onPacket = Unit.INSTANCE;
    }

    private static final Unit onPacket$lambda$5(PacketEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.isCancelled()) {
            return Unit.INSTANCE;
        }
        C03PacketPlayer packet = event.getPacket();
        if (packet instanceof C03PacketPlayer) {
            MovementUtils movementUtils = INSTANCE;
            serverOnGround = packet.field_149474_g;
            if (packet.func_149466_j()) {
                MovementUtils movementUtils2 = INSTANCE;
                serverX = packet.field_149479_a;
                MovementUtils movementUtils3 = INSTANCE;
                serverY = packet.field_149477_b;
                MovementUtils movementUtils4 = INSTANCE;
                serverZ = packet.field_149478_c;
            }
        }
        return Unit.INSTANCE;
    }
}
