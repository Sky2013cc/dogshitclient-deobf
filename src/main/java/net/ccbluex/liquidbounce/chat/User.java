package net.ccbluex.liquidbounce.chat;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: User.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lnet/ccbluex/liquidbounce/chat/User;", "", "name", "", "uuid", "Ljava/util/UUID;", Constants.CTOR, "(Ljava/lang/String;Ljava/util/UUID;)V", "getName", "()Ljava/lang/String;", "getUuid", "()Ljava/util/UUID;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/chat/User.class */
public final class User {

    @SerializedName("name")
    @NotNull
    private final String name;

    @SerializedName("uuid")
    @NotNull
    private final UUID uuid;

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final UUID component2() {
        return this.uuid;
    }

    @NotNull
    public final User copy(@NotNull String name, @NotNull UUID uuid) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        return new User(name, uuid);
    }

    public static /* synthetic */ User copy$default(User user, String str, UUID uuid, int i, Object obj) {
        if ((i & 1) != 0) {
            str = user.name;
        }
        if ((i & 2) != 0) {
            uuid = user.uuid;
        }
        return user.copy(str, uuid);
    }

    @NotNull
    public String toString() {
        return "User(name=" + this.name + ", uuid=" + this.uuid + ')';
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (result * 31) + this.uuid.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User user = (User) other;
        return Intrinsics.areEqual(this.name, user.name) && Intrinsics.areEqual(this.uuid, user.uuid);
    }

    public User(@NotNull String name, @NotNull UUID uuid) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        this.name = name;
        this.uuid = uuid;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final UUID getUuid() {
        return this.uuid;
    }
}
