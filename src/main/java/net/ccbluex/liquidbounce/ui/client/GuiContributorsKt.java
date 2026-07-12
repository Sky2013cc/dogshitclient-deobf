package net.ccbluex.liquidbounce.ui.client;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: GuiContributors.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\b\n��\n\u0002\u0018\u0002\n��\"\u000e\u0010��\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0002"}, d2 = {"DECIMAL_FORMAT", "Ljava/text/DecimalFormat;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/GuiContributorsKt.class */
public final class GuiContributorsKt {

    @NotNull
    private static final DecimalFormat DECIMAL_FORMAT;

    static {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        Intrinsics.checkNotNull(numberFormat, "null cannot be cast to non-null type java.text.DecimalFormat");
        DECIMAL_FORMAT = (DecimalFormat) numberFormat;
    }
}
