package net.ccbluex.liquidbounce.chat.packet.packets;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ServerPackets.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lnet/ccbluex/liquidbounce/chat/packet/packets/ServerMessagePacket;", "Lnet/ccbluex/liquidbounce/chat/packet/packets/Packet;", "content", "", Constants.CTOR, "(Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/chat/packet/packets/ServerMessagePacket.class */
public final class ServerMessagePacket implements Packet {

    @SerializedName("content")
    @NotNull
    private final String content;

    @NotNull
    public final String component1() {
        return this.content;
    }

    @NotNull
    public final ServerMessagePacket copy(@NotNull String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        return new ServerMessagePacket(content);
    }

    public static /* synthetic */ ServerMessagePacket copy$default(ServerMessagePacket serverMessagePacket, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serverMessagePacket.content;
        }
        return serverMessagePacket.copy(str);
    }

    @NotNull
    public String toString() {
        return "ServerMessagePacket(content=" + this.content + ')';
    }

    public int hashCode() {
        return this.content.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ServerMessagePacket) && Intrinsics.areEqual(this.content, ((ServerMessagePacket) other).content);
    }

    public ServerMessagePacket(@NotNull String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.content = content;
    }

    @NotNull
    public final String getContent() {
        return this.content;
    }
}
