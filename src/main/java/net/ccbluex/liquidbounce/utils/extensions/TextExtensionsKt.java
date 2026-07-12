package net.ccbluex.liquidbounce.utils.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: TextExtensions.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\b\n��\n\u0002\u0010\u000e\n��\u001a\n\u0010��\u001a\u00020\u0001*\u00020\u0001¨\u0006\u0002"}, d2 = {"toLowerCamelCase", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/extensions/TextExtensionsKt.class */
public final class TextExtensionsKt {
    @NotNull
    public static final String toLowerCamelCase(@NotNull String $this$toLowerCamelCase) {
        Intrinsics.checkNotNullParameter($this$toLowerCamelCase, "<this>");
        char[] $this$toLowerCamelCase_u24lambda_u240 = $this$toLowerCamelCase.toCharArray();
        Intrinsics.checkNotNullExpressionValue($this$toLowerCamelCase_u24lambda_u240, "toCharArray(...)");
        $this$toLowerCamelCase_u24lambda_u240[0] = Character.toLowerCase($this$toLowerCamelCase_u24lambda_u240[0]);
        return new String($this$toLowerCamelCase_u24lambda_u240);
    }
}
