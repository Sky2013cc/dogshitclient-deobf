package kotlin.uuid;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.HexExtensionsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Uuid.kt */
@Metadata(mv = {2, 1, 0}, k = 5, xi = 49, d1 = {"��,\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n��\n\u0002\u0010\t\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u0010\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a\u0019\u0010\u0004\u001a\u00020\u0005*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\b\u001a)\u0010\t\u001a\u00020\n*\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u000e\u001a\u0019\u0010\u000f\u001a\u00020\n*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u0012\u001a!\u0010\u0013\u001a\u00020\n*\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u0014¨\u0006\u0015"}, d2 = {"uuidFromRandomBytes", "Lkotlin/uuid/Uuid;", "randomBytes", "", "toLong", "", "startIndex", "", "toLong$UuidKt__UuidKt", "formatBytesInto", "", "dst", "dstOffset", "count", "formatBytesInto$UuidKt__UuidKt", "checkHyphenAt", "", "index", "checkHyphenAt$UuidKt__UuidKt", "toByteArray", "toByteArray$UuidKt__UuidKt", "kotlin-stdlib"}, xs = "kotlin/uuid/UuidKt")
@SourceDebugExtension({"SMAP\nUuid.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Uuid.kt\nkotlin/uuid/UuidKt__UuidKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,452:1\n1#2:453\n*E\n"})
/* loaded from: target.jar:kotlin/uuid/UuidKt__UuidKt.class */
public class UuidKt__UuidKt extends UuidKt__UuidJVMKt {
    @ExperimentalUuidApi
    @NotNull
    public static final Uuid uuidFromRandomBytes(@NotNull byte[] randomBytes) {
        Intrinsics.checkNotNullParameter(randomBytes, "randomBytes");
        randomBytes[6] = (byte) (randomBytes[6] & 15);
        randomBytes[6] = (byte) (randomBytes[6] | 64);
        randomBytes[8] = (byte) (randomBytes[8] & 63);
        randomBytes[8] = (byte) (randomBytes[8] | 128);
        return Uuid.Companion.fromByteArray(randomBytes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long toLong$UuidKt__UuidKt(byte[] $this$toLong, int startIndex) {
        return (($this$toLong[startIndex + 0] & 255) << 56) | (($this$toLong[startIndex + 1] & 255) << 48) | (($this$toLong[startIndex + 2] & 255) << 40) | (($this$toLong[startIndex + 3] & 255) << 32) | (($this$toLong[startIndex + 4] & 255) << 24) | (($this$toLong[startIndex + 5] & 255) << 16) | (($this$toLong[startIndex + 6] & 255) << 8) | ($this$toLong[startIndex + 7] & 255);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void formatBytesInto$UuidKt__UuidKt(long $this$formatBytesInto, byte[] dst, int dstOffset, int count) {
        long j = $this$formatBytesInto;
        int dstIndex = dstOffset + (2 * count);
        for (int i = 0; i < count; i++) {
            int byteDigits = HexExtensionsKt.getBYTE_TO_LOWER_CASE_HEX_DIGITS()[(int) (j & 255)];
            int dstIndex2 = dstIndex - 1;
            dst[dstIndex2] = (byte) byteDigits;
            dstIndex = dstIndex2 - 1;
            dst[dstIndex] = (byte) (byteDigits >> 8);
            j >>= 8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkHyphenAt$UuidKt__UuidKt(String $this$checkHyphenAt, int index) {
        if (!($this$checkHyphenAt.charAt(index) == '-')) {
            throw new IllegalArgumentException(("Expected '-' (hyphen) at index " + index + ", but was '" + $this$checkHyphenAt.charAt(index) + '\'').toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toByteArray$UuidKt__UuidKt(long $this$toByteArray, byte[] dst, int dstOffset) {
        for (int index = 0; index < 8; index++) {
            int shift = 8 * (7 - index);
            dst[dstOffset + index] = (byte) ($this$toByteArray >>> shift);
        }
    }
}
