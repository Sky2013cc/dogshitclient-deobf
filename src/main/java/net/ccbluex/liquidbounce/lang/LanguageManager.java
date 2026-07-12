package net.ccbluex.liquidbounce.lang;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.file.FileManager;
import net.ccbluex.liquidbounce.file.configs.models.ClientConfiguration;
import net.ccbluex.liquidbounce.utils.client.ClientUtils;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Language.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010��\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J'\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\n\"\u00020\u0017¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n��R\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR*\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u000fj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0010`\u0011X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0019"}, d2 = {"Lnet/ccbluex/liquidbounce/lang/LanguageManager;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", Constants.CTOR, "()V", "language", "", "getLanguage", "()Ljava/lang/String;", "COMMON_UNDERSTOOD_LANGUAGE", "knownLanguages", "", "getKnownLanguages", "()[Ljava/lang/String;", "[Ljava/lang/String;", "languageMap", "Ljava/util/HashMap;", "Lnet/ccbluex/liquidbounce/lang/Language;", "Lkotlin/collections/HashMap;", "loadLanguages", "", "getTranslation", "key", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "haze"})
@SourceDebugExtension({"SMAP\nLanguage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Language.kt\nnet/ccbluex/liquidbounce/lang/LanguageManager\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 GsonExtensions.kt\nnet/ccbluex/liquidbounce/utils/io/GsonExtensionsKt\n*L\n1#1,74:1\n1#2:75\n82#3:76\n*S KotlinDebug\n*F\n+ 1 Language.kt\nnet/ccbluex/liquidbounce/lang/LanguageManager\n*L\n49#1:76\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/lang/LanguageManager.class */
public final class LanguageManager implements MinecraftInstance {

    @NotNull
    public static final LanguageManager INSTANCE = new LanguageManager();

    @NotNull
    private static final String COMMON_UNDERSTOOD_LANGUAGE = "en_US";

    @NotNull
    private static final String[] knownLanguages = {COMMON_UNDERSTOOD_LANGUAGE, "pt_BR", "pt_PT", "zh_CN", "zh_TW", "bg_BG", "ru_RU"};

    @NotNull
    private static final HashMap<String, Language> languageMap = new HashMap<>();

    private LanguageManager() {
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    private final String getLanguage() {
        String language;
        String overrideLanguage = ClientConfiguration.INSTANCE.getOverrideLanguage();
        if (StringsKt.isBlank(overrideLanguage)) {
            language = INSTANCE.getMc().field_71474_y.field_74363_ab;
            Intrinsics.checkNotNullExpressionValue(language, "language");
        } else {
            language = overrideLanguage;
        }
        return language;
    }

    @NotNull
    public final String[] getKnownLanguages() {
        return knownLanguages;
    }

    public final void loadLanguages() {
        Object m1167constructorimpl;
        for (String language : knownLanguages) {
            try {
                Result.Companion companion = Result.Companion;
                LanguageManager $this$loadLanguages_u24lambda_u242 = this;
                HashMap<String, Language> hashMap = languageMap;
                InputStream resourceAsStream = $this$loadLanguages_u24lambda_u242.getClass().getResourceAsStream("/assets/minecraft/haze/lang/" + language + ".json");
                Intrinsics.checkNotNull(resourceAsStream);
                Reader inputStreamReader = new InputStreamReader(resourceAsStream, Charsets.UTF_8);
                Reader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                Throwable th = null;
                try {
                    try {
                        Reader $this$decodeJson_u24default$iv = (BufferedReader) bufferedReader;
                        Gson gson$iv = FileManager.INSTANCE.getPRETTY_GSON();
                        Language language2 = (Language) gson$iv.fromJson($this$decodeJson_u24default$iv, new TypeToken<Language>() { // from class: net.ccbluex.liquidbounce.lang.LanguageManager$loadLanguages$lambda$2$lambda$1$$inlined$decodeJson$default$1
                        }.getType());
                        CloseableKt.closeFinally(bufferedReader, null);
                        hashMap.put(language, language2);
                        m1167constructorimpl = Result.m1167constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th2) {
                        th = th2;
                        throw th2;
                        break;
                    }
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(bufferedReader, th);
                    throw th3;
                }
            } catch (Throwable th4) {
                Result.Companion companion2 = Result.Companion;
                m1167constructorimpl = Result.m1167constructorimpl(ResultKt.createFailure(th4));
            }
            Object obj = m1167constructorimpl;
            if (Result.m1160isSuccessimpl(obj)) {
                ClientUtils.INSTANCE.getLOGGER().info("Loaded language " + language);
            }
            Throwable it = Result.m1163exceptionOrNullimpl(obj);
            if (it != null) {
                ClientUtils.INSTANCE.getLOGGER().error("Failed to load language " + language, it);
            }
        }
    }

    @NotNull
    public final String getTranslation(@NotNull String key, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(args, "args");
        Language language = languageMap.get(getLanguage());
        if (language != null) {
            String translation = language.getTranslation(key, Arrays.copyOf(args, args.length));
            if (translation != null) {
                return translation;
            }
        }
        Language language2 = languageMap.get(COMMON_UNDERSTOOD_LANGUAGE);
        String translation2 = language2 != null ? language2.getTranslation(key, Arrays.copyOf(args, args.length)) : null;
        return translation2 == null ? key : translation2;
    }
}
