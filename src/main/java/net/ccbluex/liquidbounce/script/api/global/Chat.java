package net.ccbluex.liquidbounce.script.api.global;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.utils.client.ClientUtilsKt;
import org.apache.pdfbox.pdmodel.interactive.action.PDWindowsLaunchParams;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Chat.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lnet/ccbluex/liquidbounce/script/api/global/Chat;", "", Constants.CTOR, "()V", PDWindowsLaunchParams.OPERATION_PRINT, "", "message", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/script/api/global/Chat.class */
public final class Chat {

    @NotNull
    public static final Chat INSTANCE = new Chat();

    private Chat() {
    }

    @JvmStatic
    public static final void print(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        ClientUtilsKt.chat(message);
    }
}
