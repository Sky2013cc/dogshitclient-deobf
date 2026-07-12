package net.ccbluex.liquidbounce.chat.packet.packets;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ServerPackets.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lnet/ccbluex/liquidbounce/chat/packet/packets/ServerLoginJWTPacket;", "Lnet/ccbluex/liquidbounce/chat/packet/packets/Packet;", "token", "", "allowMessages", "", Constants.CTOR, "(Ljava/lang/String;Z)V", "getToken", "()Ljava/lang/String;", "getAllowMessages", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/chat/packet/packets/ServerLoginJWTPacket.class */
public final class ServerLoginJWTPacket implements Packet {

    @SerializedName("token")
    @NotNull
    private final String token;

    @SerializedName("allow_messages")
    private final boolean allowMessages;

    @NotNull
    public final String component1() {
        return this.token;
    }

    public final boolean component2() {
        return this.allowMessages;
    }

    @NotNull
    public final ServerLoginJWTPacket copy(@NotNull String token, boolean allowMessages) {
        Intrinsics.checkNotNullParameter(token, "token");
        return new ServerLoginJWTPacket(token, allowMessages);
    }

    public static /* synthetic */ ServerLoginJWTPacket copy$default(ServerLoginJWTPacket serverLoginJWTPacket, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serverLoginJWTPacket.token;
        }
        if ((i & 2) != 0) {
            z = serverLoginJWTPacket.allowMessages;
        }
        return serverLoginJWTPacket.copy(str, z);
    }

    @NotNull
    public String toString() {
        return "ServerLoginJWTPacket(token=" + this.token + ", allowMessages=" + this.allowMessages + ')';
    }

    public int hashCode() {
        int result = this.token.hashCode();
        return (result * 31) + Boolean.hashCode(this.allowMessages);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServerLoginJWTPacket)) {
            return false;
        }
        ServerLoginJWTPacket serverLoginJWTPacket = (ServerLoginJWTPacket) other;
        return Intrinsics.areEqual(this.token, serverLoginJWTPacket.token) && this.allowMessages == serverLoginJWTPacket.allowMessages;
    }

    public ServerLoginJWTPacket(@NotNull String token, boolean allowMessages) {
        Intrinsics.checkNotNullParameter(token, "token");
        this.token = token;
        this.allowMessages = allowMessages;
    }

    @NotNull
    public final String getToken() {
        return this.token;
    }

    public final boolean getAllowMessages() {
        return this.allowMessages;
    }
}
