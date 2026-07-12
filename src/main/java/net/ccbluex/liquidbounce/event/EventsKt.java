package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"$\u0010��\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"ALL_EVENT_CLASSES", "", Constants.CLASS, "Lnet/ccbluex/liquidbounce/event/Event;", "getALL_EVENT_CLASSES", "()[Ljava/lang/Class;", "[Ljava/lang/Class;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/EventsKt.class */
public final class EventsKt {

    @NotNull
    private static final Class<? extends Event>[] ALL_EVENT_CLASSES = {PlayerTickEvent.class, StepConfirmEvent.class, SessionUpdateEvent.class, MovementInputEvent.class, GameLoopEvent.class, Render2DEvent.class, ClickWindowEvent.class, StartupEvent.class, SneakSlowDownEvent.class, PostSprintUpdateEvent.class, KeyEvent.class, SlowDownEvent.class, TickEndEvent.class, JumpEvent.class, MoveEvent.class, ClientShutdownEvent.class, GameTickEvent.class, StepEvent.class, BlockBBEvent.class, ClickBlockEvent.class, UpdateEvent.class, RotationSetEvent.class, EntityMovementEvent.class, ClientSlotChangeEvent.class, PacketEvent.class, CameraPositionEvent.class, RotationUpdateEvent.class, StrafeEvent.class, ScreenEvent.class, AttackEvent.class, BlockPushEvent.class, Render3DEvent.class, MotionEvent.class, WorldEvent.class, DelayedPacketProcessEvent.class};

    @NotNull
    public static final Class<? extends Event>[] getALL_EVENT_CLASSES() {
        return ALL_EVENT_CLASSES;
    }
}
