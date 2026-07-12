package net.ccbluex.liquidbounce.tabs;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: HeadsTab.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lnet/ccbluex/liquidbounce/tabs/HeadsConfiguration;", "", "enabled", "", "url", "", Constants.CTOR, "(ZLjava/lang/String;)V", "getEnabled", "()Z", "getUrl", "()Ljava/lang/String;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/tabs/HeadsConfiguration.class */
final class HeadsConfiguration {
    private final boolean enabled;

    @NotNull
    private final String url;

    public HeadsConfiguration(boolean enabled, @NotNull String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.enabled = enabled;
        this.url = url;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }
}
