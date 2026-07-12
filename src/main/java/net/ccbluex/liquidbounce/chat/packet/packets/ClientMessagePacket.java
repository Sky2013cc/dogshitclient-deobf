package net.ccbluex.liquidbounce.chat.packet.packets;

import com.google.gson.annotations.SerializedName;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.chat.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ClientPackets.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lnet/ccbluex/liquidbounce/chat/packet/packets/ClientMessagePacket;", "Lnet/ccbluex/liquidbounce/chat/packet/packets/Packet;", Constants.ATTR_ID, "", "user", "Lnet/ccbluex/liquidbounce/chat/User;", "content", org.spongepowered.asm.util.Constants.CTOR, "(Ljava/lang/String;Lnet/ccbluex/liquidbounce/chat/User;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getUser", "()Lnet/ccbluex/liquidbounce/chat/User;", "getContent", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/chat/packet/packets/ClientMessagePacket.class */
public final class ClientMessagePacket implements Packet {

    @SerializedName("author_id")
    @NotNull
    private final String id;

    @SerializedName("author_info")
    @NotNull
    private final User user;

    @SerializedName("content")
    @NotNull
    private final String content;

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final User component2() {
        return this.user;
    }

    @NotNull
    public final String component3() {
        return this.content;
    }

    @NotNull
    public final ClientMessagePacket copy(@NotNull String id, @NotNull User user, @NotNull String content) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(content, "content");
        return new ClientMessagePacket(id, user, content);
    }

    public static /* synthetic */ ClientMessagePacket copy$default(ClientMessagePacket clientMessagePacket, String str, User user, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = clientMessagePacket.id;
        }
        if ((i & 2) != 0) {
            user = clientMessagePacket.user;
        }
        if ((i & 4) != 0) {
            str2 = clientMessagePacket.content;
        }
        return clientMessagePacket.copy(str, user, str2);
    }

    @NotNull
    public String toString() {
        return "ClientMessagePacket(id=" + this.id + ", user=" + this.user + ", content=" + this.content + ')';
    }

    public int hashCode() {
        int result = this.id.hashCode();
        return (((result * 31) + this.user.hashCode()) * 31) + this.content.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClientMessagePacket)) {
            return false;
        }
        ClientMessagePacket clientMessagePacket = (ClientMessagePacket) other;
        return Intrinsics.areEqual(this.id, clientMessagePacket.id) && Intrinsics.areEqual(this.user, clientMessagePacket.user) && Intrinsics.areEqual(this.content, clientMessagePacket.content);
    }

    public ClientMessagePacket(@NotNull String id, @NotNull User user, @NotNull String content) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(content, "content");
        this.id = id;
        this.user = user;
        this.content = content;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final User getUser() {
        return this.user;
    }

    @NotNull
    public final String getContent() {
        return this.content;
    }
}
