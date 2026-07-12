package net.ccbluex.liquidbounce.chat.packet.packets;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ServerPackets.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lnet/ccbluex/liquidbounce/chat/packet/packets/ServerLoginMojangPacket;", "Lnet/ccbluex/liquidbounce/chat/packet/packets/Packet;", "name", "", "uuid", "Ljava/util/UUID;", "allowMessages", "", Constants.CTOR, "(Ljava/lang/String;Ljava/util/UUID;Z)V", "getName", "()Ljava/lang/String;", "getUuid", "()Ljava/util/UUID;", "getAllowMessages", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/chat/packet/packets/ServerLoginMojangPacket.class */
public final class ServerLoginMojangPacket implements Packet {

    @SerializedName("name")
    @NotNull
    private final String name;

    @SerializedName("uuid")
    @NotNull
    private final UUID uuid;

    @SerializedName("allow_messages")
    private final boolean allowMessages;

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final UUID component2() {
        return this.uuid;
    }

    public final boolean component3() {
        return this.allowMessages;
    }

    @NotNull
    public final ServerLoginMojangPacket copy(@NotNull String name, @NotNull UUID uuid, boolean allowMessages) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        return new ServerLoginMojangPacket(name, uuid, allowMessages);
    }

    public static /* synthetic */ ServerLoginMojangPacket copy$default(ServerLoginMojangPacket serverLoginMojangPacket, String str, UUID uuid, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serverLoginMojangPacket.name;
        }
        if ((i & 2) != 0) {
            uuid = serverLoginMojangPacket.uuid;
        }
        if ((i & 4) != 0) {
            z = serverLoginMojangPacket.allowMessages;
        }
        return serverLoginMojangPacket.copy(str, uuid, z);
    }

    @NotNull
    public String toString() {
        return "ServerLoginMojangPacket(name=" + this.name + ", uuid=" + this.uuid + ", allowMessages=" + this.allowMessages + ')';
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (((result * 31) + this.uuid.hashCode()) * 31) + Boolean.hashCode(this.allowMessages);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServerLoginMojangPacket)) {
            return false;
        }
        ServerLoginMojangPacket serverLoginMojangPacket = (ServerLoginMojangPacket) other;
        return Intrinsics.areEqual(this.name, serverLoginMojangPacket.name) && Intrinsics.areEqual(this.uuid, serverLoginMojangPacket.uuid) && this.allowMessages == serverLoginMojangPacket.allowMessages;
    }

    public ServerLoginMojangPacket(@NotNull String name, @NotNull UUID uuid, boolean allowMessages) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        this.name = name;
        this.uuid = uuid;
        this.allowMessages = allowMessages;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final UUID getUuid() {
        return this.uuid;
    }

    public final boolean getAllowMessages() {
        return this.allowMessages;
    }
}
