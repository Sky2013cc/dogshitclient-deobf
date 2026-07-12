package net.ccbluex.liquidbounce.lang;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Language.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n��\n\u0002\u0010$\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0003\u0018��2\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\b\u0010\tJ)\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0013\"\u00020\u0001¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lnet/ccbluex/liquidbounce/lang/Language;", "", "locale", "", "contributors", "", "translations", "", Constants.CTOR, "(Ljava/lang/String;Ljava/util/List;Ljava/util/Map;)V", "getLocale", "()Ljava/lang/String;", "getContributors", "()Ljava/util/List;", "getTranslations", "()Ljava/util/Map;", "getTranslation", "key", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/lang/Language.class */
public final class Language {

    @NotNull
    private final String locale;

    @NotNull
    private final List<String> contributors;

    @NotNull
    private final Map<String, String> translations;

    public Language(@NotNull String locale, @NotNull List<String> contributors, @NotNull Map<String, String> translations) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        Intrinsics.checkNotNullParameter(contributors, "contributors");
        Intrinsics.checkNotNullParameter(translations, "translations");
        this.locale = locale;
        this.contributors = contributors;
        this.translations = translations;
    }

    @NotNull
    public final String getLocale() {
        return this.locale;
    }

    @NotNull
    public final List<String> getContributors() {
        return this.contributors;
    }

    @NotNull
    public final Map<String, String> getTranslations() {
        return this.translations;
    }

    @Nullable
    public final String getTranslation(@NotNull String key, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(args, "args");
        String str = this.translations.get(key);
        if (str == null) {
            return null;
        }
        Object[] copyOf = Arrays.copyOf(args, args.length);
        String format = String.format(str, Arrays.copyOf(copyOf, copyOf.length));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    @NotNull
    public String toString() {
        return this.locale;
    }
}
