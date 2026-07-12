package okio;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Base64.kt */
@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u000e\u0010\t\u001a\u0004\u0018\u00010\u0001*\u00020\nH��\u001a\u0016\u0010\u000b\u001a\u00020\n*\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\u0001H��\"\u001c\u0010��\u001a\u00020\u00018��X\u0081\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001c\u0010\u0006\u001a\u00020\u00018��X\u0081\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\u0005¨\u0006\r"}, d2 = {"BASE64", "", "getBASE64$annotations", "()V", "getBASE64", "()[B", "BASE64_URL_SAFE", "getBASE64_URL_SAFE$annotations", "getBASE64_URL_SAFE", "decodeBase64ToArray", "", "encodeBase64", "map", "okio"})
@JvmName(name = "-Base64")
/* renamed from: okio.-Base64, reason: invalid class name */
/* loaded from: target.jar:okio/-Base64.class */
public final class Base64 {

    @NotNull
    private static final byte[] BASE64 = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").getData$okio();

    @NotNull
    private static final byte[] BASE64_URL_SAFE = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").getData$okio();

    public static /* synthetic */ void getBASE64$annotations() {
    }

    public static /* synthetic */ void getBASE64_URL_SAFE$annotations() {
    }

    @NotNull
    public static final byte[] getBASE64() {
        return BASE64;
    }

    @NotNull
    public static final byte[] getBASE64_URL_SAFE() {
        return BASE64_URL_SAFE;
    }

    @Nullable
    public static final byte[] decodeBase64ToArray(@NotNull String $this$decodeBase64ToArray) {
        int bits;
        char c;
        Intrinsics.checkNotNullParameter($this$decodeBase64ToArray, "<this>");
        int limit = $this$decodeBase64ToArray.length();
        while (limit > 0 && ((c = $this$decodeBase64ToArray.charAt(limit - 1)) == '=' || c == '\n' || c == '\r' || c == ' ' || c == '\t')) {
            limit--;
        }
        byte[] out = new byte[(int) ((limit * 6) / 8)];
        int outCount = 0;
        int inCount = 0;
        int word = 0;
        int i = limit;
        for (int pos = 0; pos < i; pos++) {
            char c2 = $this$decodeBase64ToArray.charAt(pos);
            if ('A' <= c2 ? c2 < '[' : false) {
                bits = c2 - 'A';
            } else if ('a' <= c2 ? c2 < '{' : false) {
                bits = c2 - 'G';
            } else if ('0' <= c2 ? c2 < ':' : false) {
                bits = c2 + 4;
            } else if (c2 == '+' || c2 == '-') {
                bits = 62;
            } else if (c2 == '/' || c2 == '_') {
                bits = 63;
            } else {
                if (c2 != '\n' && c2 != '\r' && c2 != ' ' && c2 != '\t') {
                    return null;
                }
            }
            word = (word << 6) | bits;
            inCount++;
            if (inCount % 4 == 0) {
                int i2 = outCount;
                int outCount2 = outCount + 1;
                out[i2] = (byte) (word >> 16);
                int outCount3 = outCount2 + 1;
                out[outCount2] = (byte) (word >> 8);
                outCount = outCount3 + 1;
                out[outCount3] = (byte) word;
            }
        }
        int lastWordChars = inCount % 4;
        switch (lastWordChars) {
            case 1:
                return null;
            case 2:
                int i3 = outCount;
                outCount++;
                out[i3] = (byte) ((word << 12) >> 16);
                break;
            case 3:
                int word2 = word << 6;
                int i4 = outCount;
                int outCount4 = outCount + 1;
                out[i4] = (byte) (word2 >> 16);
                outCount = outCount4 + 1;
                out[outCount4] = (byte) (word2 >> 8);
                break;
        }
        if (outCount == out.length) {
            return out;
        }
        byte[] copyOf = Arrays.copyOf(out, outCount);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
        return copyOf;
    }

    public static /* synthetic */ String encodeBase64$default(byte[] bArr, byte[] bArr2, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr2 = BASE64;
        }
        return encodeBase64(bArr, bArr2);
    }

    @NotNull
    public static final String encodeBase64(@NotNull byte[] $this$encodeBase64, @NotNull byte[] map) {
        Intrinsics.checkNotNullParameter($this$encodeBase64, "<this>");
        Intrinsics.checkNotNullParameter(map, "map");
        int length = (($this$encodeBase64.length + 2) / 3) * 4;
        byte[] out = new byte[length];
        int index = 0;
        int end = $this$encodeBase64.length - ($this$encodeBase64.length % 3);
        int i = 0;
        while (i < end) {
            int i2 = i;
            int i3 = i + 1;
            byte b = $this$encodeBase64[i2];
            int i4 = i3 + 1;
            byte b2 = $this$encodeBase64[i3];
            i = i4 + 1;
            byte b3 = $this$encodeBase64[i4];
            int i5 = index;
            int index2 = index + 1;
            out[i5] = map[(b & 255) >> 2];
            int index3 = index2 + 1;
            out[index2] = map[((b & 3) << 4) | ((b2 & 255) >> 4)];
            int index4 = index3 + 1;
            out[index3] = map[((b2 & 15) << 2) | ((b3 & 255) >> 6)];
            index = index4 + 1;
            out[index4] = map[b3 & 63];
        }
        switch ($this$encodeBase64.length - end) {
            case 1:
                byte b4 = $this$encodeBase64[i];
                int i6 = index;
                int index5 = index + 1;
                out[i6] = map[(b4 & 255) >> 2];
                int index6 = index5 + 1;
                out[index5] = map[(b4 & 3) << 4];
                out[index6] = 61;
                out[index6 + 1] = 61;
                break;
            case 2:
                byte b5 = $this$encodeBase64[i];
                byte b6 = $this$encodeBase64[i + 1];
                int i7 = index;
                int index7 = index + 1;
                out[i7] = map[(b5 & 255) >> 2];
                int index8 = index7 + 1;
                out[index7] = map[((b5 & 3) << 4) | ((b6 & 255) >> 4)];
                out[index8] = map[(b6 & 15) << 2];
                out[index8 + 1] = 61;
                break;
        }
        return _JvmPlatformKt.toUtf8String(out);
    }
}
