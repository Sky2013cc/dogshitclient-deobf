package kotlin.io.encoding;

import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Base64IOStream.kt */
@Metadata(mv = {2, 1, 0}, k = 5, xi = 49, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0014\u0010��\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¨\u0006\u0006"}, d2 = {"decodingWith", "Ljava/io/InputStream;", "base64", "Lkotlin/io/encoding/Base64;", "encodingWith", "Ljava/io/OutputStream;", "kotlin-stdlib"}, xs = "kotlin/io/encoding/StreamEncodingKt")
/* loaded from: target.jar:kotlin/io/encoding/StreamEncodingKt__Base64IOStreamKt.class */
class StreamEncodingKt__Base64IOStreamKt {
    @ExperimentalEncodingApi
    @SinceKotlin(version = "1.8")
    @NotNull
    public static final InputStream decodingWith(@NotNull InputStream $this$decodingWith, @NotNull Base64 base64) {
        Intrinsics.checkNotNullParameter($this$decodingWith, "<this>");
        Intrinsics.checkNotNullParameter(base64, "base64");
        return new DecodeInputStream($this$decodingWith, base64);
    }

    @ExperimentalEncodingApi
    @SinceKotlin(version = "1.8")
    @NotNull
    public static final OutputStream encodingWith(@NotNull OutputStream $this$encodingWith, @NotNull Base64 base64) {
        Intrinsics.checkNotNullParameter($this$encodingWith, "<this>");
        Intrinsics.checkNotNullParameter(base64, "base64");
        return new EncodeOutputStream($this$encodingWith, base64);
    }
}
