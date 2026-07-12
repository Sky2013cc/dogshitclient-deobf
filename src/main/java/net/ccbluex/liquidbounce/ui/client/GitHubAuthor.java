package net.ccbluex.liquidbounce.ui.client;

import com.google.gson.annotations.SerializedName;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: GuiContributors.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\t\b\u0002\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\r\u0010\n¨\u0006\u000e"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/GitHubAuthor;", "", "name", "", Constants.ATTR_ID, "", "avatarUrl", org.spongepowered.asm.util.Constants.CTOR, "(Ljava/lang/String;ILjava/lang/String;)V", "getName", "()Ljava/lang/String;", "getId", "()I", "getAvatarUrl", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/GitHubAuthor.class */
final class GitHubAuthor {

    @SerializedName("login")
    @NotNull
    private final String name;
    private final int id;

    @SerializedName("avatar_url")
    @NotNull
    private final String avatarUrl;

    public GitHubAuthor(@NotNull String name, int id, @NotNull String avatarUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(avatarUrl, "avatarUrl");
        this.name = name;
        this.id = id;
        this.avatarUrl = avatarUrl;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final int getId() {
        return this.id;
    }

    @NotNull
    public final String getAvatarUrl() {
        return this.avatarUrl;
    }
}
