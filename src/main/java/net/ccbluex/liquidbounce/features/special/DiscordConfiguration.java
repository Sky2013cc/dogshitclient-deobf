package net.ccbluex.liquidbounce.features.special;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ClientRichPresence.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n��\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018��2\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lnet/ccbluex/liquidbounce/features/special/DiscordConfiguration;", "", "appID", "", "assets", "", "", Constants.CTOR, "(Ljava/lang/Long;Ljava/util/Map;)V", "getAppID", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getAssets", "()Ljava/util/Map;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/special/DiscordConfiguration.class */
final class DiscordConfiguration {

    @Nullable
    private final Long appID;

    @NotNull
    private final Map<String, String> assets;

    public DiscordConfiguration(@Nullable Long appID, @NotNull Map<String, String> assets) {
        Intrinsics.checkNotNullParameter(assets, "assets");
        this.appID = appID;
        this.assets = assets;
    }

    @Nullable
    public final Long getAppID() {
        return this.appID;
    }

    @NotNull
    public final Map<String, String> getAssets() {
        return this.assets;
    }
}
