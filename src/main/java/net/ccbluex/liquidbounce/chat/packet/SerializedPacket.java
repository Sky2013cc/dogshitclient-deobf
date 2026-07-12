package net.ccbluex.liquidbounce.chat.packet;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.chat.packet.packets.Packet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: SerializedPacket.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lnet/ccbluex/liquidbounce/chat/packet/SerializedPacket;", "", "packetName", "", "packetContent", "Lnet/ccbluex/liquidbounce/chat/packet/packets/Packet;", Constants.CTOR, "(Ljava/lang/String;Lnet/ccbluex/liquidbounce/chat/packet/packets/Packet;)V", "getPacketName", "()Ljava/lang/String;", "getPacketContent", "()Lnet/ccbluex/liquidbounce/chat/packet/packets/Packet;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/chat/packet/SerializedPacket.class */
public final class SerializedPacket {

    @SerializedName("m")
    @NotNull
    private final String packetName;

    @SerializedName("c")
    @Nullable
    private final Packet packetContent;

    @NotNull
    public final String component1() {
        return this.packetName;
    }

    @Nullable
    public final Packet component2() {
        return this.packetContent;
    }

    @NotNull
    public final SerializedPacket copy(@NotNull String packetName, @Nullable Packet packetContent) {
        Intrinsics.checkNotNullParameter(packetName, "packetName");
        return new SerializedPacket(packetName, packetContent);
    }

    public static /* synthetic */ SerializedPacket copy$default(SerializedPacket serializedPacket, String str, Packet packet, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serializedPacket.packetName;
        }
        if ((i & 2) != 0) {
            packet = serializedPacket.packetContent;
        }
        return serializedPacket.copy(str, packet);
    }

    @NotNull
    public String toString() {
        return "SerializedPacket(packetName=" + this.packetName + ", packetContent=" + this.packetContent + ')';
    }

    public int hashCode() {
        int result = this.packetName.hashCode();
        return (result * 31) + (this.packetContent == null ? 0 : this.packetContent.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SerializedPacket)) {
            return false;
        }
        SerializedPacket serializedPacket = (SerializedPacket) other;
        return Intrinsics.areEqual(this.packetName, serializedPacket.packetName) && Intrinsics.areEqual(this.packetContent, serializedPacket.packetContent);
    }

    public SerializedPacket(@NotNull String packetName, @Nullable Packet packetContent) {
        Intrinsics.checkNotNullParameter(packetName, "packetName");
        this.packetName = packetName;
        this.packetContent = packetContent;
    }

    @NotNull
    public final String getPacketName() {
        return this.packetName;
    }

    @Nullable
    public final Packet getPacketContent() {
        return this.packetContent;
    }
}
