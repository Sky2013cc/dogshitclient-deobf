package kotlin.io.encoding;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* compiled from: Base64.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u001c\n��\n\u0002\u0010\u0012\n��\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n��\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001\"\u000e\u0010��\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��\"\u0016\u0010\u0002\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��\"\u0016\u0010\u0007\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\b\u0010\u0005¨\u0006\r"}, d2 = {"base64EncodeMap", "", "base64DecodeMap", "", "getBase64DecodeMap$annotations", "()V", "base64UrlEncodeMap", "base64UrlDecodeMap", "getBase64UrlDecodeMap$annotations", "isInMimeAlphabet", "", "symbol", "", "kotlin-stdlib"})
@SourceDebugExtension({"SMAP\nBase64.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Base64.kt\nkotlin/io/encoding/Base64Kt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,826:1\n13477#2,3:827\n13477#2,3:830\n*S KotlinDebug\n*F\n+ 1 Base64.kt\nkotlin/io/encoding/Base64Kt\n*L\n762#1:827,3\n779#1:830,3\n*E\n"})
/* loaded from: target.jar:kotlin/io/encoding/Base64Kt.class */
public final class Base64Kt {

    @NotNull
    private static final byte[] base64EncodeMap = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    @NotNull
    private static final int[] base64DecodeMap;

    @NotNull
    private static final byte[] base64UrlEncodeMap;

    @NotNull
    private static final int[] base64UrlDecodeMap;

    @ExperimentalEncodingApi
    private static /* synthetic */ void getBase64DecodeMap$annotations() {
    }

    @ExperimentalEncodingApi
    private static /* synthetic */ void getBase64UrlDecodeMap$annotations() {
    }

    static {
        int[] $this$base64DecodeMap_u24lambda_u241 = new int[256];
        ArraysKt.fill$default($this$base64DecodeMap_u24lambda_u241, -1, 0, 0, 6, (Object) null);
        $this$base64DecodeMap_u24lambda_u241[61] = -2;
        byte[] $this$forEachIndexed$iv = base64EncodeMap;
        int index$iv = 0;
        for (byte item$iv : $this$forEachIndexed$iv) {
            int index = index$iv;
            index$iv++;
            $this$base64DecodeMap_u24lambda_u241[item$iv] = index;
        }
        base64DecodeMap = $this$base64DecodeMap_u24lambda_u241;
        base64UrlEncodeMap = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        int[] $this$base64UrlDecodeMap_u24lambda_u243 = new int[256];
        ArraysKt.fill$default($this$base64UrlDecodeMap_u24lambda_u243, -1, 0, 0, 6, (Object) null);
        $this$base64UrlDecodeMap_u24lambda_u243[61] = -2;
        byte[] $this$forEachIndexed$iv2 = base64UrlEncodeMap;
        int index$iv2 = 0;
        for (byte item$iv2 : $this$forEachIndexed$iv2) {
            int index2 = index$iv2;
            index$iv2++;
            $this$base64UrlDecodeMap_u24lambda_u243[item$iv2] = index2;
        }
        base64UrlDecodeMap = $this$base64UrlDecodeMap_u24lambda_u243;
    }

    @ExperimentalEncodingApi
    @SinceKotlin(version = "1.8")
    public static final boolean isInMimeAlphabet(int symbol) {
        return (0 <= symbol ? symbol < base64DecodeMap.length : false) && base64DecodeMap[symbol] != -1;
    }
}
