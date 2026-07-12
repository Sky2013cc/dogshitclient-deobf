package net.ccbluex.liquidbounce.utils.kotlin;

import com.sun.tools.doclets.internal.toolkit.util.VisibleMemberMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: StringUtils.kt */
@SideOnly(Side.CLIENT)
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0011\n��\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\bÇ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ \u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u0005J\"\u0010\u000f\u001a\u00020\u0010*\u0004\u0018\u00010\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0086\u0002¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/kotlin/StringUtils;", "", Constants.CTOR, "()V", "toCompleteString", "", "args", "", VisibleMemberMap.STARTLEVEL, "", "([Ljava/lang/String;I)Ljava/lang/String;", "replace", "string", "searchChars", "replaceChars", "contains", "", "substrings", "(Ljava/lang/String;[Ljava/lang/String;)Z", "haze"})
@SourceDebugExtension({"SMAP\nStringUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringUtils.kt\nnet/ccbluex/liquidbounce/utils/kotlin/StringUtils\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,50:1\n12567#2,2:51\n*S KotlinDebug\n*F\n+ 1 StringUtils.kt\nnet/ccbluex/liquidbounce/utils/kotlin/StringUtils\n*L\n47#1:51,2\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/kotlin/StringUtils.class */
public final class StringUtils {

    @NotNull
    public static final StringUtils INSTANCE = new StringUtils();

    private StringUtils() {
    }

    @NotNull
    public final String toCompleteString(@NotNull String[] args, int start) {
        Intrinsics.checkNotNullParameter(args, "args");
        return args.length <= start ? "" : CollectionsKt.joinToString$default(ArraysKt.drop(args, start), " ", null, null, 0, null, null, 62, null);
    }

    public static /* synthetic */ String replace$default(StringUtils stringUtils, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        return stringUtils.replace(str, str2, str3);
    }

    @NotNull
    public final String replace(@NotNull String string, @NotNull String searchChars, @NotNull String replaceChars) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(searchChars, "searchChars");
        Intrinsics.checkNotNullParameter(replaceChars, "replaceChars");
        if (!(string.length() == 0)) {
            if (!(searchChars.length() == 0) && !Intrinsics.areEqual(searchChars, replaceChars)) {
                int stringLength = string.length();
                int searchCharsLength = searchChars.length();
                StringBuilder stringBuilder = new StringBuilder(string);
                for (int i = 0; i < stringLength; i++) {
                    int start = stringBuilder.indexOf(searchChars, i);
                    if (start == -1) {
                        if (i == 0) {
                            return string;
                        }
                        String sb = stringBuilder.toString();
                        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
                        return sb;
                    }
                    stringBuilder.replace(start, start + searchCharsLength, replaceChars);
                }
                String sb2 = stringBuilder.toString();
                Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
                return sb2;
            }
        }
        return string;
    }

    public final boolean contains(@Nullable String $this$contains, @NotNull String[] substrings) {
        Intrinsics.checkNotNullParameter(substrings, "substrings");
        if ($this$contains == null) {
            return false;
        }
        String lowerCaseString = $this$contains.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCaseString, "toLowerCase(...)");
        if (lowerCaseString == null) {
            return false;
        }
        for (String str : substrings) {
            if (StringsKt.contains$default((CharSequence) lowerCaseString, (CharSequence) str, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }
}
