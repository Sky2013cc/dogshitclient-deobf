package kotlin;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: UnsignedJVM.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��8\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n��\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001f\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u0007\u0010\u0005\u001a\u001f\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0001¢\u0006\u0004\b\n\u0010\u000b\u001a\u001f\u0010\f\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0001¢\u0006\u0004\b\r\u0010\u000b\u001a\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000fH\u0001\u001a\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0011H\u0001\u001a\u0016\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000fH\u0081\b¢\u0006\u0002\u0010\u0014\u001a\u0011\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000fH\u0081\b\u001a\u0011\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u000fH\u0081\b\u001a\u0016\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0017H\u0081\b¢\u0006\u0002\u0010\u0019\u001a\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u000fH\u0001\u001a\u0015\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u001bH\u0001¢\u0006\u0002\u0010\u001d\u001a\u0011\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0011H\u0081\b\u001a\u0016\u0010\u001f\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0017H\u0081\b¢\u0006\u0002\u0010 \u001a\u0010\u0010!\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0011H\u0001\u001a\u0015\u0010\"\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u001bH\u0001¢\u0006\u0002\u0010#\u001a\u0011\u0010$\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u000fH\u0081\b\u001a\u0019\u0010$\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u000fH\u0081\b\u001a\u0011\u0010'\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u0011H\u0081\b\u001a\u0018\u0010'\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u000fH��¨\u0006("}, d2 = {"uintRemainder", "Lkotlin/UInt;", "v1", "v2", "uintRemainder-J1ME1BU", "(II)I", "uintDivide", "uintDivide-J1ME1BU", "ulongDivide", "Lkotlin/ULong;", "ulongDivide-eb3DHEI", "(JJ)J", "ulongRemainder", "ulongRemainder-eb3DHEI", "uintCompare", "", "ulongCompare", "", "uintToULong", "value", "(I)J", "uintToLong", "uintToFloat", "", "floatToUInt", "(F)I", "uintToDouble", "", "doubleToUInt", "(D)I", "ulongToFloat", "floatToULong", "(F)J", "ulongToDouble", "doubleToULong", "(D)J", "uintToString", "", Constants.ATTR_BASE, "ulongToString", "kotlin-stdlib"})
@JvmName(name = "UnsignedKt")
/* loaded from: target.jar:kotlin/UnsignedKt.class */
public final class UnsignedKt {
    @PublishedApi
    /* renamed from: uintRemainder-J1ME1BU, reason: not valid java name */
    public static final int m1520uintRemainderJ1ME1BU(int v1, int v2) {
        return UInt.m1308constructorimpl((int) ((v1 & 4294967295L) % (v2 & 4294967295L)));
    }

    @PublishedApi
    /* renamed from: uintDivide-J1ME1BU, reason: not valid java name */
    public static final int m1521uintDivideJ1ME1BU(int v1, int v2) {
        return UInt.m1308constructorimpl((int) ((v1 & 4294967295L) / (v2 & 4294967295L)));
    }

    @PublishedApi
    /* renamed from: ulongDivide-eb3DHEI, reason: not valid java name */
    public static final long m1522ulongDivideeb3DHEI(long v1, long v2) {
        if (v2 < 0) {
            return Long.compareUnsigned(v1, v2) < 0 ? ULong.m1388constructorimpl(0L) : ULong.m1388constructorimpl(1L);
        }
        if (v1 < 0) {
            long quotient = ((v1 >>> 1) / v2) << 1;
            long rem = v1 - (quotient * v2);
            return ULong.m1388constructorimpl(quotient + (Long.compareUnsigned(ULong.m1388constructorimpl(rem), ULong.m1388constructorimpl(v2)) >= 0 ? 1 : 0));
        }
        return ULong.m1388constructorimpl(v1 / v2);
    }

    @PublishedApi
    /* renamed from: ulongRemainder-eb3DHEI, reason: not valid java name */
    public static final long m1523ulongRemaindereb3DHEI(long v1, long v2) {
        if (v2 < 0) {
            if (Long.compareUnsigned(v1, v2) < 0) {
                return v1;
            }
            return ULong.m1388constructorimpl(v1 - v2);
        }
        if (v1 < 0) {
            long quotient = ((v1 >>> 1) / v2) << 1;
            long rem = v1 - (quotient * v2);
            return ULong.m1388constructorimpl(rem - (Long.compareUnsigned(ULong.m1388constructorimpl(rem), ULong.m1388constructorimpl(v2)) >= 0 ? v2 : 0L));
        }
        return ULong.m1388constructorimpl(v1 % v2);
    }

    @PublishedApi
    public static final int uintCompare(int v1, int v2) {
        return Intrinsics.compare(v1 ^ Integer.MIN_VALUE, v2 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    public static final int ulongCompare(long v1, long v2) {
        return Intrinsics.compare(v1 ^ Long.MIN_VALUE, v2 ^ Long.MIN_VALUE);
    }

    @PublishedApi
    @InlineOnly
    private static final long uintToULong(int value) {
        return ULong.m1388constructorimpl(value & 4294967295L);
    }

    @PublishedApi
    @InlineOnly
    private static final long uintToLong(int value) {
        return value & 4294967295L;
    }

    @PublishedApi
    @InlineOnly
    private static final float uintToFloat(int value) {
        return (float) uintToDouble(value);
    }

    @PublishedApi
    @InlineOnly
    private static final int floatToUInt(float value) {
        return doubleToUInt(value);
    }

    @PublishedApi
    public static final double uintToDouble(int value) {
        return (value & Integer.MAX_VALUE) + (((value >>> 31) << 30) * 2);
    }

    @PublishedApi
    public static final int doubleToUInt(double value) {
        if (Double.isNaN(value) || value <= uintToDouble(0)) {
            return 0;
        }
        if (value >= uintToDouble(-1)) {
            return -1;
        }
        return value <= 2.147483647E9d ? UInt.m1308constructorimpl((int) value) : UInt.m1308constructorimpl(UInt.m1308constructorimpl((int) (value - Integer.MAX_VALUE)) + UInt.m1308constructorimpl(Integer.MAX_VALUE));
    }

    @PublishedApi
    @InlineOnly
    private static final float ulongToFloat(long value) {
        return (float) ulongToDouble(value);
    }

    @PublishedApi
    @InlineOnly
    private static final long floatToULong(float value) {
        return doubleToULong(value);
    }

    @PublishedApi
    public static final double ulongToDouble(long value) {
        return ((value >>> 11) * 2048) + (value & 2047);
    }

    @PublishedApi
    public static final long doubleToULong(double value) {
        if (Double.isNaN(value) || value <= ulongToDouble(0L)) {
            return 0L;
        }
        if (value >= ulongToDouble(-1L)) {
            return -1L;
        }
        return value < 9.223372036854776E18d ? ULong.m1388constructorimpl((long) value) : ULong.m1388constructorimpl(ULong.m1388constructorimpl((long) (value - 9.223372036854776E18d)) - Long.MIN_VALUE);
    }

    @InlineOnly
    private static final String uintToString(int value) {
        return String.valueOf(value & 4294967295L);
    }

    @InlineOnly
    private static final String uintToString(int value, int base) {
        return ulongToString(value & 4294967295L, base);
    }

    @InlineOnly
    private static final String ulongToString(long value) {
        return ulongToString(value, 10);
    }

    @NotNull
    public static final String ulongToString(long value, int base) {
        if (value >= 0) {
            String l = Long.toString(value, CharsKt.checkRadix(base));
            Intrinsics.checkNotNullExpressionValue(l, "toString(...)");
            return l;
        }
        long quotient = ((value >>> 1) / base) << 1;
        long rem = value - (quotient * base);
        if (rem >= base) {
            rem -= base;
            quotient++;
        }
        StringBuilder sb = new StringBuilder();
        String l2 = Long.toString(quotient, CharsKt.checkRadix(base));
        Intrinsics.checkNotNullExpressionValue(l2, "toString(...)");
        StringBuilder append = sb.append(l2);
        String l3 = Long.toString(rem, CharsKt.checkRadix(base));
        Intrinsics.checkNotNullExpressionValue(l3, "toString(...)");
        return append.append(l3).toString();
    }
}
