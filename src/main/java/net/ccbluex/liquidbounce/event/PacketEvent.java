package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.network.Packet;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018��2\u00020\u0001B\u001b\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lnet/ccbluex/liquidbounce/event/PacketEvent;", "Lnet/ccbluex/liquidbounce/event/CancellableEvent;", "packet", "Lnet/minecraft/network/Packet;", "eventType", "Lnet/ccbluex/liquidbounce/event/EventState;", Constants.CTOR, "(Lnet/minecraft/network/Packet;Lnet/ccbluex/liquidbounce/event/EventState;)V", "getPacket", "()Lnet/minecraft/network/Packet;", "getEventType", "()Lnet/ccbluex/liquidbounce/event/EventState;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/PacketEvent.class */
public final class PacketEvent extends CancellableEvent {

    @NotNull
    private final Packet<?> packet;

    @NotNull
    private final EventState eventType;

    public PacketEvent(@NotNull Packet<?> packet, @NotNull EventState eventType) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        this.packet = packet;
        this.eventType = eventType;
    }

    @NotNull
    public final Packet<?> getPacket() {
        return this.packet;
    }

    @NotNull
    public final EventState getEventType() {
        return this.eventType;
    }
}
