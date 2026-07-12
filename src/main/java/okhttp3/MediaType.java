package okhttp3;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.nio.charset.Charset;
import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._MediaTypeCommonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MediaType.kt */
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018�� \u001b2\u00020\u0001:\u0001\u001bB-\b��\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0017\u001a\u00020\u0003J\r\u0010\u0005\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0018J\b\u0010\u0019\u001a\u00020\u0003H\u0016J\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u001aR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0005\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\nR\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0004\u0010\n¨\u0006\u001c"}, d2 = {"Lokhttp3/MediaType;", "", "mediaType", "", Constants.ATTR_TYPE, "subtype", "parameterNamesAndValues", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "getMediaType$okhttp", "()Ljava/lang/String;", "getParameterNamesAndValues$okhttp", "()[Ljava/lang/String;", "[Ljava/lang/String;", "charset", "Ljava/nio/charset/Charset;", "defaultValue", "equals", "", "other", "hashCode", "", "parameter", "name", "-deprecated_subtype", "toString", "-deprecated_type", "Companion", "okhttp"})
/* loaded from: target.jar:okhttp3/MediaType.class */
public final class MediaType {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final String mediaType;

    @NotNull
    private final String type;

    @NotNull
    private final String subtype;

    @NotNull
    private final String[] parameterNamesAndValues;

    @JvmOverloads
    @Nullable
    public final Charset charset() {
        return charset$default(this, null, 1, null);
    }

    @JvmStatic
    @JvmName(name = PropertyDescriptor.GET)
    @NotNull
    public static final MediaType get(@NotNull String $this$get) {
        return Companion.get($this$get);
    }

    @JvmStatic
    @JvmName(name = "parse")
    @Nullable
    public static final MediaType parse(@NotNull String $this$parse) {
        return Companion.parse($this$parse);
    }

    public MediaType(@NotNull String mediaType, @NotNull String type, @NotNull String subtype, @NotNull String[] parameterNamesAndValues) {
        Intrinsics.checkNotNullParameter(mediaType, "mediaType");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(subtype, "subtype");
        Intrinsics.checkNotNullParameter(parameterNamesAndValues, "parameterNamesAndValues");
        this.mediaType = mediaType;
        this.type = type;
        this.subtype = subtype;
        this.parameterNamesAndValues = parameterNamesAndValues;
    }

    @NotNull
    public final String getMediaType$okhttp() {
        return this.mediaType;
    }

    @JvmName(name = Constants.ATTR_TYPE)
    @NotNull
    public final String type() {
        return this.type;
    }

    @JvmName(name = "subtype")
    @NotNull
    public final String subtype() {
        return this.subtype;
    }

    @NotNull
    public final String[] getParameterNamesAndValues$okhttp() {
        return this.parameterNamesAndValues;
    }

    public static /* synthetic */ Charset charset$default(MediaType mediaType, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = null;
        }
        return mediaType.charset(charset);
    }

    @JvmOverloads
    @Nullable
    public final Charset charset(@Nullable Charset defaultValue) {
        Charset charset;
        String charset2 = parameter("charset");
        if (charset2 == null) {
            return defaultValue;
        }
        try {
            charset = Charset.forName(charset2);
        } catch (IllegalArgumentException e) {
            charset = defaultValue;
        }
        return charset;
    }

    @Nullable
    public final String parameter(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return _MediaTypeCommonKt.commonParameter(this, name);
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = Constants.ATTR_TYPE, imports = {}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_type")
    @NotNull
    /* renamed from: -deprecated_type, reason: not valid java name */
    public final String m3652deprecated_type() {
        return this.type;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "subtype", imports = {}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_subtype")
    @NotNull
    /* renamed from: -deprecated_subtype, reason: not valid java name */
    public final String m3653deprecated_subtype() {
        return this.subtype;
    }

    @NotNull
    public String toString() {
        return _MediaTypeCommonKt.commonToString(this);
    }

    public boolean equals(@Nullable Object other) {
        return _MediaTypeCommonKt.commonEquals(this, other);
    }

    public int hashCode() {
        return _MediaTypeCommonKt.commonHashCode(this);
    }

    /* compiled from: MediaType.kt */
    @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0007J\u0017\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\tJ\u0011\u0010\n\u001a\u00020\u0004*\u00020\u0006H\u0007¢\u0006\u0002\b\u0003J\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0004*\u00020\u0006H\u0007¢\u0006\u0002\b\b¨\u0006\f"}, d2 = {"Lokhttp3/MediaType$Companion;", "", "()V", PropertyDescriptor.GET, "Lokhttp3/MediaType;", "mediaType", "", "-deprecated_get", "parse", "-deprecated_parse", "toMediaType", "toMediaTypeOrNull", "okhttp"})
    /* loaded from: target.jar:okhttp3/MediaType$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        @JvmName(name = PropertyDescriptor.GET)
        @NotNull
        public final MediaType get(@NotNull String $this$toMediaType) {
            Intrinsics.checkNotNullParameter($this$toMediaType, "<this>");
            return _MediaTypeCommonKt.commonToMediaType($this$toMediaType);
        }

        @JvmStatic
        @JvmName(name = "parse")
        @Nullable
        public final MediaType parse(@NotNull String $this$toMediaTypeOrNull) {
            Intrinsics.checkNotNullParameter($this$toMediaTypeOrNull, "<this>");
            return _MediaTypeCommonKt.commonToMediaTypeOrNull($this$toMediaTypeOrNull);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaType()", imports = {"okhttp3.MediaType.Companion.toMediaType"}), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_get")
        @NotNull
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final MediaType m3655deprecated_get(@NotNull String mediaType) {
            Intrinsics.checkNotNullParameter(mediaType, "mediaType");
            return get(mediaType);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaTypeOrNull()", imports = {"okhttp3.MediaType.Companion.toMediaTypeOrNull"}), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_parse")
        @Nullable
        /* renamed from: -deprecated_parse, reason: not valid java name */
        public final MediaType m3656deprecated_parse(@NotNull String mediaType) {
            Intrinsics.checkNotNullParameter(mediaType, "mediaType");
            return parse(mediaType);
        }
    }
}
