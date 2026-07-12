package net.ccbluex.liquidbounce.utils.client;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: ClientUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\u001a\u000e\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"chat", "", "message", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/ClientUtilsKt.class */
public final class ClientUtilsKt {
    public static final void chat(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        ClientUtils.INSTANCE.displayChatMessage(message);
    }
}
