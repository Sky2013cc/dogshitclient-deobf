package okhttp3.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: -MediaTypeCommon.kt */
@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"��(\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0006\u001a\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH��\u001a\f\u0010\u000b\u001a\u00020\f*\u00020\bH��\u001a\u0016\u0010\r\u001a\u0004\u0018\u00010\u0003*\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0003H��\u001a\f\u0010\u000f\u001a\u00020\b*\u00020\u0003H��\u001a\f\u0010\u0010\u001a\u0004\u0018\u00010\b*\u00020\u0003\u001a\f\u0010\u0011\u001a\u00020\u0003*\u00020\bH��\"\u000e\u0010��\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0012"}, d2 = {"PARAMETER", "Lkotlin/text/Regex;", "QUOTED", "", "TOKEN", "TYPE_SUBTYPE", "commonEquals", "", "Lokhttp3/MediaType;", "other", "", "commonHashCode", "", "commonParameter", "name", "commonToMediaType", "commonToMediaTypeOrNull", "commonToString", "okhttp"})
@SourceDebugExtension({"SMAP\n-MediaTypeCommon.kt\nKotlin\n*S Kotlin\n*F\n+ 1 -MediaTypeCommon.kt\nokhttp3/internal/_MediaTypeCommonKt\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,98:1\n37#2,2:99\n*S KotlinDebug\n*F\n+ 1 -MediaTypeCommon.kt\nokhttp3/internal/_MediaTypeCommonKt\n*L\n87#1:99,2\n*E\n"})
/* loaded from: target.jar:okhttp3/internal/_MediaTypeCommonKt.class */
public final class _MediaTypeCommonKt {

    @NotNull
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";

    @NotNull
    private static final String QUOTED = "\"([^\"]*)\"";

    @NotNull
    private static final Regex TYPE_SUBTYPE = new Regex("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    @NotNull
    private static final Regex PARAMETER = new Regex(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    @Nullable
    public static final String commonParameter(@NotNull MediaType $this$commonParameter, @NotNull String name) {
        Intrinsics.checkNotNullParameter($this$commonParameter, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        int i = 0;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, $this$commonParameter.getParameterNamesAndValues$okhttp().length - 1, 2);
        if (0 <= progressionLastElement) {
            while (!StringsKt.equals($this$commonParameter.getParameterNamesAndValues$okhttp()[i], name, true)) {
                if (i == progressionLastElement) {
                    return null;
                }
                i += 2;
            }
            return $this$commonParameter.getParameterNamesAndValues$okhttp()[i + 1];
        }
        return null;
    }

    public static final boolean commonEquals(@NotNull MediaType $this$commonEquals, @Nullable Object other) {
        Intrinsics.checkNotNullParameter($this$commonEquals, "<this>");
        return (other instanceof MediaType) && Intrinsics.areEqual(((MediaType) other).getMediaType$okhttp(), $this$commonEquals.getMediaType$okhttp());
    }

    @NotNull
    public static final String commonToString(@NotNull MediaType $this$commonToString) {
        Intrinsics.checkNotNullParameter($this$commonToString, "<this>");
        return $this$commonToString.getMediaType$okhttp();
    }

    public static final int commonHashCode(@NotNull MediaType $this$commonHashCode) {
        Intrinsics.checkNotNullParameter($this$commonHashCode, "<this>");
        return $this$commonHashCode.getMediaType$okhttp().hashCode();
    }

    @NotNull
    public static final MediaType commonToMediaType(@NotNull String $this$commonToMediaType) {
        String str;
        Intrinsics.checkNotNullParameter($this$commonToMediaType, "<this>");
        MatchResult typeSubtype = _UtilCommonKt.matchAtPolyfill(TYPE_SUBTYPE, $this$commonToMediaType, 0);
        if (typeSubtype == null) {
            throw new IllegalArgumentException("No subtype found for: \"" + $this$commonToMediaType + '\"');
        }
        String type = typeSubtype.getGroupValues().get(1).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(type, "toLowerCase(...)");
        String subtype = typeSubtype.getGroupValues().get(2).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(subtype, "toLowerCase(...)");
        Collection parameterNamesAndValues = (List) new ArrayList();
        int last = typeSubtype.getRange().getLast();
        while (true) {
            int s = last + 1;
            if (s >= $this$commonToMediaType.length()) {
                Collection $this$toTypedArray$iv = parameterNamesAndValues;
                return new MediaType($this$commonToMediaType, type, subtype, (String[]) $this$toTypedArray$iv.toArray(new String[0]));
            }
            MatchResult parameter = _UtilCommonKt.matchAtPolyfill(PARAMETER, $this$commonToMediaType, s);
            if (!(parameter != null)) {
                StringBuilder append = new StringBuilder().append("Parameter is not formatted correctly: \"");
                String substring = $this$commonToMediaType.substring(s);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                throw new IllegalArgumentException(append.append(substring).append("\" for: \"").append($this$commonToMediaType).append('\"').toString().toString());
            }
            MatchGroup matchGroup = parameter.getGroups().get(1);
            String name = matchGroup != null ? matchGroup.getValue() : null;
            if (name == null) {
                last = parameter.getRange().getLast();
            } else {
                MatchGroup matchGroup2 = parameter.getGroups().get(2);
                String token = matchGroup2 != null ? matchGroup2.getValue() : null;
                if (token == null) {
                    MatchGroup matchGroup3 = parameter.getGroups().get(3);
                    Intrinsics.checkNotNull(matchGroup3);
                    str = matchGroup3.getValue();
                } else if (StringsKt.startsWith$default(token, OperatorName.SHOW_TEXT_LINE, false, 2, (Object) null) && StringsKt.endsWith$default(token, OperatorName.SHOW_TEXT_LINE, false, 2, (Object) null) && token.length() > 2) {
                    str = token.substring(1, token.length() - 1);
                    Intrinsics.checkNotNullExpressionValue(str, "substring(...)");
                } else {
                    str = token;
                }
                String value = str;
                parameterNamesAndValues.add(name);
                parameterNamesAndValues.add(value);
                last = parameter.getRange().getLast();
            }
        }
    }

    @Nullable
    public static final MediaType commonToMediaTypeOrNull(@NotNull String $this$commonToMediaTypeOrNull) {
        MediaType mediaType;
        Intrinsics.checkNotNullParameter($this$commonToMediaTypeOrNull, "<this>");
        try {
            mediaType = commonToMediaType($this$commonToMediaTypeOrNull);
        } catch (IllegalArgumentException e) {
            mediaType = null;
        }
        return mediaType;
    }
}
