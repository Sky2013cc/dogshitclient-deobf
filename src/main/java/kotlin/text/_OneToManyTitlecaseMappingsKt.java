package kotlin.text;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: _OneToManyTitlecaseMappings.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u000e\n\u0002\u0010\f\n��\u001a\f\u0010��\u001a\u00020\u0001*\u00020\u0002H��¨\u0006\u0003"}, d2 = {"titlecaseImpl", "", "", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/text/_OneToManyTitlecaseMappingsKt.class */
public final class _OneToManyTitlecaseMappingsKt {
    @NotNull
    public static final String titlecaseImpl(char $this$titlecaseImpl) {
        String valueOf = String.valueOf($this$titlecaseImpl);
        Intrinsics.checkNotNull(valueOf, "null cannot be cast to non-null type java.lang.String");
        String uppercase = valueOf.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(uppercase, "toUpperCase(...)");
        if (uppercase.length() <= 1) {
            return String.valueOf(Character.toTitleCase($this$titlecaseImpl));
        }
        if ($this$titlecaseImpl == 329) {
            return uppercase;
        }
        char charAt = uppercase.charAt(0);
        Intrinsics.checkNotNull(uppercase, "null cannot be cast to non-null type java.lang.String");
        String substring = uppercase.substring(1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        Intrinsics.checkNotNull(substring, "null cannot be cast to non-null type java.lang.String");
        String lowerCase = substring.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return charAt + lowerCase;
    }
}
