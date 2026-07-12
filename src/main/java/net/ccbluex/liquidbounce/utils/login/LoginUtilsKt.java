package net.ccbluex.liquidbounce.utils.login;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Session;
import org.jetbrains.annotations.NotNull;

/* compiled from: LoginUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\f\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u001a\n\u0010��\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"intoMinecraftSession", "Lnet/minecraft/util/Session;", "Lme/liuli/elixir/compat/Session;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/login/LoginUtilsKt.class */
public final class LoginUtilsKt {
    @NotNull
    public static final Session intoMinecraftSession(@NotNull me.liuli.elixir.compat.Session $this$intoMinecraftSession) {
        Intrinsics.checkNotNullParameter($this$intoMinecraftSession, "<this>");
        return new Session($this$intoMinecraftSession.getUsername(), $this$intoMinecraftSession.getUuid(), $this$intoMinecraftSession.getToken(), $this$intoMinecraftSession.getType());
    }
}
