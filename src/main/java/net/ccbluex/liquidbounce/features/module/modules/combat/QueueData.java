package net.ccbluex.liquidbounce.features.module.modules.combat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.network.Packet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Backtrack.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u001b\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020��2\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/modules/combat/QueueData;", "", "packet", "Lnet/minecraft/network/Packet;", "time", "", Constants.CTOR, "(Lnet/minecraft/network/Packet;J)V", "getPacket", "()Lnet/minecraft/network/Packet;", "getTime", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/modules/combat/QueueData.class */
public final class QueueData {

    @NotNull
    private final Packet<?> packet;
    private final long time;

    @NotNull
    public final Packet<?> component1() {
        return this.packet;
    }

    public final long component2() {
        return this.time;
    }

    @NotNull
    public final QueueData copy(@NotNull Packet<?> packet, long time) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        return new QueueData(packet, time);
    }

    public static /* synthetic */ QueueData copy$default(QueueData queueData, Packet packet, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            packet = queueData.packet;
        }
        if ((i & 2) != 0) {
            j = queueData.time;
        }
        return queueData.copy(packet, j);
    }

    @NotNull
    public String toString() {
        return "QueueData(packet=" + this.packet + ", time=" + this.time + ')';
    }

    public int hashCode() {
        int result = this.packet.hashCode();
        return (result * 31) + Long.hashCode(this.time);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueData)) {
            return false;
        }
        QueueData queueData = (QueueData) other;
        return Intrinsics.areEqual(this.packet, queueData.packet) && this.time == queueData.time;
    }

    public QueueData(@NotNull Packet<?> packet, long time) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        this.packet = packet;
        this.time = time;
    }

    @NotNull
    public final Packet<?> getPacket() {
        return this.packet;
    }

    public final long getTime() {
        return this.time;
    }
}
