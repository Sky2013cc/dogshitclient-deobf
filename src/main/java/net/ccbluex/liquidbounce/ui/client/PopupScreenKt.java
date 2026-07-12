package net.ccbluex.liquidbounce.ui.client;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.ui.client.PopupScreen;
import org.jetbrains.annotations.NotNull;

/* compiled from: PopupScreen.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\u001a\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\u001a%\u0010��\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, d2 = {"PopupScreen", "Lnet/ccbluex/liquidbounce/ui/client/PopupScreen;", "builderAction", "Lkotlin/Function1;", "Lnet/ccbluex/liquidbounce/ui/client/PopupScreen$Builder;", "", "Lkotlin/ExtensionFunctionType;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/PopupScreenKt.class */
public final class PopupScreenKt {
    @NotNull
    public static final PopupScreen PopupScreen(@NotNull Function1<? super PopupScreen.Builder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        PopupScreen.Builder builder = new PopupScreen.Builder();
        builderAction.invoke(builder);
        return builder.build();
    }
}
