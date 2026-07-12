package net.ccbluex.liquidbounce.chat.packet.packets;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ServerPackets.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lnet/ccbluex/liquidbounce/chat/packet/packets/ServerPrivateMessagePacket;", "Lnet/ccbluex/liquidbounce/chat/packet/packets/Packet;", "receiver", "", "content", Constants.CTOR, "(Ljava/lang/String;Ljava/lang/String;)V", "getReceiver", "()Ljava/lang/String;", "getContent", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/chat/packet/packets/ServerPrivateMessagePacket.class */
public final class ServerPrivateMessagePacket implements Packet {

    @SerializedName("receiver")
    @NotNull
    private final String receiver;

    @SerializedName("content")
    @NotNull
    private final String content;

    @NotNull
    public final String component1() {
        return this.receiver;
    }

    @NotNull
    public final String component2() {
        return this.content;
    }

    @NotNull
    public final ServerPrivateMessagePacket copy(@NotNull String receiver, @NotNull String content) {
        Intrinsics.checkNotNullParameter(receiver, "receiver");
        Intrinsics.checkNotNullParameter(content, "content");
        return new ServerPrivateMessagePacket(receiver, content);
    }

    public static /* synthetic */ ServerPrivateMessagePacket copy$default(ServerPrivateMessagePacket serverPrivateMessagePacket, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serverPrivateMessagePacket.receiver;
        }
        if ((i & 2) != 0) {
            str2 = serverPrivateMessagePacket.content;
        }
        return serverPrivateMessagePacket.copy(str, str2);
    }

    @NotNull
    public String toString() {
        return "ServerPrivateMessagePacket(receiver=" + this.receiver + ", content=" + this.content + ')';
    }

    public int hashCode() {
        int result = this.receiver.hashCode();
        return (result * 31) + this.content.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServerPrivateMessagePacket)) {
            return false;
        }
        ServerPrivateMessagePacket serverPrivateMessagePacket = (ServerPrivateMessagePacket) other;
        return Intrinsics.areEqual(this.receiver, serverPrivateMessagePacket.receiver) && Intrinsics.areEqual(this.content, serverPrivateMessagePacket.content);
    }

    public ServerPrivateMessagePacket(@NotNull String receiver, @NotNull String content) {
        Intrinsics.checkNotNullParameter(receiver, "receiver");
        Intrinsics.checkNotNullParameter(content, "content");
        this.receiver = receiver;
        this.content = content;
    }

    @NotNull
    public final String getReceiver() {
        return this.receiver;
    }

    @NotNull
    public final String getContent() {
        return this.content;
    }
}
