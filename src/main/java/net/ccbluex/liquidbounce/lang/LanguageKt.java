package net.ccbluex.liquidbounce.lang;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Language.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\u0016\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010��\n\u0002\b\u0005\u001a'\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\"\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a'\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\"\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a'\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\"\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a'\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\"\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\n"}, d2 = {"translationMenu", "", "key", "args", "", "", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "translationButton", "translationText", "translation", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/lang/LanguageKt.class */
public final class LanguageKt {
    @NotNull
    public static final String translationMenu(@NotNull String key, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(args, "args");
        return LanguageManager.INSTANCE.getTranslation("menu." + key, Arrays.copyOf(args, args.length));
    }

    @NotNull
    public static final String translationButton(@NotNull String key, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(args, "args");
        return LanguageManager.INSTANCE.getTranslation("button." + key, Arrays.copyOf(args, args.length));
    }

    @NotNull
    public static final String translationText(@NotNull String key, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(args, "args");
        return LanguageManager.INSTANCE.getTranslation("text." + key, Arrays.copyOf(args, args.length));
    }

    @NotNull
    public static final String translation(@NotNull String key, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(args, "args");
        return LanguageManager.INSTANCE.getTranslation(key, Arrays.copyOf(args, args.length));
    }
}
