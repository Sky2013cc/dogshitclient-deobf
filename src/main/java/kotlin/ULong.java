package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ULongRange;
import kotlin.ranges.URangesKt;
import okhttp3.internal.ws.WebSocketProtocol;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: ULong.kt */
@SinceKotlin(version = "1.5")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��l\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b\u0003\b\u0087@\u0018�� {2\b\u0012\u0004\u0012\u00020��0\u0001:\u0001{B\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020��H\u0097\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0016\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\u0017\u0010\u0018J\u0018\u0010\u0016\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b\u0019\u0010\u001aJ\u0018\u0010\u0016\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010\u0016\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b\u001d\u0010\u001eJ\u0018\u0010\u001f\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b \u0010\u0018J\u0018\u0010\u001f\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b!\u0010\u001aJ\u0018\u0010\u001f\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b\"\u0010\u001cJ\u0018\u0010\u001f\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b#\u0010\u001eJ\u0018\u0010$\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b%\u0010\u0018J\u0018\u0010$\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b&\u0010\u001aJ\u0018\u0010$\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b'\u0010\u001cJ\u0018\u0010$\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b(\u0010\u001eJ\u0018\u0010)\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b*\u0010\u0018J\u0018\u0010)\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b+\u0010\u001aJ\u0018\u0010)\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b,\u0010\u001cJ\u0018\u0010)\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b-\u0010\u001eJ\u0018\u0010.\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b/\u0010\u0018J\u0018\u0010.\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b0\u0010\u001aJ\u0018\u0010.\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b1\u0010\u001cJ\u0018\u0010.\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b2\u0010\u001eJ\u0018\u00103\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b4\u0010\u0018J\u0018\u00103\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000eH\u0087\b¢\u0006\u0004\b5\u0010\u001aJ\u0018\u00103\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\b6\u0010\u001cJ\u0018\u00103\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\b¢\u0006\u0004\b7\u0010\u001eJ\u0018\u00108\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b9\u0010:J\u0018\u00108\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000eH\u0087\b¢\u0006\u0004\b;\u0010<J\u0018\u00108\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\b=\u0010\u0013J\u0018\u00108\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\b¢\u0006\u0004\b>\u0010\u001eJ\u0010\u0010?\u001a\u00020��H\u0087\n¢\u0006\u0004\b@\u0010\u0005J\u0010\u0010A\u001a\u00020��H\u0087\n¢\u0006\u0004\bB\u0010\u0005J\u0018\u0010C\u001a\u00020D2\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\bE\u0010FJ\u0018\u0010G\u001a\u00020D2\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\bH\u0010FJ\u0018\u0010I\u001a\u00020��2\u0006\u0010J\u001a\u00020\tH\u0087\f¢\u0006\u0004\bK\u0010\u001cJ\u0018\u0010L\u001a\u00020��2\u0006\u0010J\u001a\u00020\tH\u0087\f¢\u0006\u0004\bM\u0010\u001cJ\u0018\u0010N\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\f¢\u0006\u0004\bO\u0010\u001eJ\u0018\u0010P\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\f¢\u0006\u0004\bQ\u0010\u001eJ\u0018\u0010R\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\f¢\u0006\u0004\bS\u0010\u001eJ\u0010\u0010T\u001a\u00020��H\u0087\b¢\u0006\u0004\bU\u0010\u0005J\u0010\u0010V\u001a\u00020WH\u0087\b¢\u0006\u0004\bX\u0010YJ\u0010\u0010Z\u001a\u00020[H\u0087\b¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020\tH\u0087\b¢\u0006\u0004\b_\u0010`J\u0010\u0010a\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bb\u0010\u0005J\u0010\u0010c\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\bd\u0010YJ\u0010\u0010e\u001a\u00020\u000eH\u0087\b¢\u0006\u0004\bf\u0010]J\u0010\u0010g\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\bh\u0010`J\u0010\u0010i\u001a\u00020��H\u0087\b¢\u0006\u0004\bj\u0010\u0005J\u0010\u0010k\u001a\u00020lH\u0087\b¢\u0006\u0004\bm\u0010nJ\u0010\u0010o\u001a\u00020pH\u0087\b¢\u0006\u0004\bq\u0010rJ\u000f\u0010s\u001a\u00020tH\u0016¢\u0006\u0004\bu\u0010vJ\u0013\u0010w\u001a\u00020x2\b\u0010\n\u001a\u0004\u0018\u00010yHÖ\u0003J\t\u0010z\u001a\u00020\tHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006|"}, d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "getData$annotations", "()V", "compareTo", "", "other", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "plus", "plus-7apg3OU", "(JB)J", "plus-xj2QHRw", "(JS)J", "plus-WZ4Q5Ns", "(JI)J", "plus-VKZWuLQ", "(JJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(JB)B", "mod-xj2QHRw", "(JS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-s-VKNKU", "dec", "dec-s-VKNKU", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rangeUntil", "rangeUntil-VKZWuLQ", "shl", "bitCount", "shl-s-VKNKU", "shr", "shr-s-VKNKU", "and", "and-VKZWuLQ", "or", "or-VKZWuLQ", "xor", "xor-VKZWuLQ", "inv", "inv-s-VKNKU", "toByte", "", "toByte-impl", "(J)B", "toShort", "", "toShort-impl", "(J)S", "toInt", "toInt-impl", "(J)I", "toLong", "toLong-impl", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", "", "toFloat-impl", "(J)F", "toDouble", "", "toDouble-impl", "(J)D", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "kotlin-stdlib"})
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: target.jar:kotlin/ULong.class */
public final class ULong implements Comparable<ULong> {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final long data;
    public static final long MIN_VALUE = 0;
    public static final long MAX_VALUE = -1;
    public static final int SIZE_BYTES = 8;
    public static final int SIZE_BITS = 64;

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl */
    public static int m1386hashCodeimpl(long arg0) {
        return Long.hashCode(arg0);
    }

    public int hashCode() {
        return m1386hashCodeimpl(this.data);
    }

    /* renamed from: equals-impl */
    public static boolean m1387equalsimpl(long arg0, Object other) {
        return (other instanceof ULong) && arg0 == ((ULong) other).m1390unboximpl();
    }

    public boolean equals(Object other) {
        return m1387equalsimpl(this.data, other);
    }

    @IntrinsicConstEvaluation
    @PublishedApi
    /* renamed from: constructor-impl */
    public static long m1388constructorimpl(long data) {
        return data;
    }

    /* renamed from: box-impl */
    public static final /* synthetic */ ULong m1389boximpl(long v) {
        return new ULong(v);
    }

    /* renamed from: unbox-impl */
    public final /* synthetic */ long m1390unboximpl() {
        return this.data;
    }

    /* renamed from: equals-impl0 */
    public static final boolean m1391equalsimpl0(long p1, long p2) {
        return p1 == p2;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ULong uLong) {
        return UnsignedKt.ulongCompare(m1390unboximpl(), uLong.m1390unboximpl());
    }

    @IntrinsicConstEvaluation
    @PublishedApi
    private /* synthetic */ ULong(long data) {
        this.data = data;
    }

    /* compiled from: ULong.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lkotlin/ULong$Companion;", "", Constants.CTOR, "()V", "MIN_VALUE", "Lkotlin/ULong;", "J", "MAX_VALUE", "SIZE_BYTES", "", "SIZE_BITS", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/ULong$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU */
    private static final int m1332compareTo7apg3OU(long arg0, byte other) {
        return Long.compareUnsigned(arg0, m1388constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw */
    private static final int m1333compareToxj2QHRw(long arg0, short other) {
        return Long.compareUnsigned(arg0, m1388constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns */
    private static final int m1334compareToWZ4Q5Ns(long arg0, int other) {
        return Long.compareUnsigned(arg0, m1388constructorimpl(other & 4294967295L));
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ */
    private int m1336compareToVKZWuLQ(long other) {
        return UnsignedKt.ulongCompare(m1390unboximpl(), other);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ */
    private static int m1335compareToVKZWuLQ(long arg0, long other) {
        return UnsignedKt.ulongCompare(arg0, other);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU */
    private static final long m1337plus7apg3OU(long arg0, byte other) {
        return m1388constructorimpl(arg0 + m1388constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw */
    private static final long m1338plusxj2QHRw(long arg0, short other) {
        return m1388constructorimpl(arg0 + m1388constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns */
    private static final long m1339plusWZ4Q5Ns(long arg0, int other) {
        return m1388constructorimpl(arg0 + m1388constructorimpl(other & 4294967295L));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ */
    private static final long m1340plusVKZWuLQ(long arg0, long other) {
        return m1388constructorimpl(arg0 + other);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU */
    private static final long m1341minus7apg3OU(long arg0, byte other) {
        return m1388constructorimpl(arg0 - m1388constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw */
    private static final long m1342minusxj2QHRw(long arg0, short other) {
        return m1388constructorimpl(arg0 - m1388constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns */
    private static final long m1343minusWZ4Q5Ns(long arg0, int other) {
        return m1388constructorimpl(arg0 - m1388constructorimpl(other & 4294967295L));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ */
    private static final long m1344minusVKZWuLQ(long arg0, long other) {
        return m1388constructorimpl(arg0 - other);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU */
    private static final long m1345times7apg3OU(long arg0, byte other) {
        return m1388constructorimpl(arg0 * m1388constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw */
    private static final long m1346timesxj2QHRw(long arg0, short other) {
        return m1388constructorimpl(arg0 * m1388constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns */
    private static final long m1347timesWZ4Q5Ns(long arg0, int other) {
        return m1388constructorimpl(arg0 * m1388constructorimpl(other & 4294967295L));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ */
    private static final long m1348timesVKZWuLQ(long arg0, long other) {
        return m1388constructorimpl(arg0 * other);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU */
    private static final long m1349div7apg3OU(long arg0, byte other) {
        return Long.divideUnsigned(arg0, m1388constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw */
    private static final long m1350divxj2QHRw(long arg0, short other) {
        return Long.divideUnsigned(arg0, m1388constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns */
    private static final long m1351divWZ4Q5Ns(long arg0, int other) {
        return Long.divideUnsigned(arg0, m1388constructorimpl(other & 4294967295L));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ */
    private static final long m1352divVKZWuLQ(long arg0, long other) {
        return UnsignedKt.m1522ulongDivideeb3DHEI(arg0, other);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU */
    private static final long m1353rem7apg3OU(long arg0, byte other) {
        return Long.remainderUnsigned(arg0, m1388constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw */
    private static final long m1354remxj2QHRw(long arg0, short other) {
        return Long.remainderUnsigned(arg0, m1388constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns */
    private static final long m1355remWZ4Q5Ns(long arg0, int other) {
        return Long.remainderUnsigned(arg0, m1388constructorimpl(other & 4294967295L));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ */
    private static final long m1356remVKZWuLQ(long arg0, long other) {
        return UnsignedKt.m1523ulongRemaindereb3DHEI(arg0, other);
    }

    @InlineOnly
    /* renamed from: floorDiv-7apg3OU */
    private static final long m1357floorDiv7apg3OU(long arg0, byte other) {
        return Long.divideUnsigned(arg0, m1388constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw */
    private static final long m1358floorDivxj2QHRw(long arg0, short other) {
        return Long.divideUnsigned(arg0, m1388constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns */
    private static final long m1359floorDivWZ4Q5Ns(long arg0, int other) {
        return Long.divideUnsigned(arg0, m1388constructorimpl(other & 4294967295L));
    }

    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ */
    private static final long m1360floorDivVKZWuLQ(long arg0, long other) {
        return Long.divideUnsigned(arg0, other);
    }

    @InlineOnly
    /* renamed from: mod-7apg3OU */
    private static final byte m1361mod7apg3OU(long arg0, byte other) {
        return UByte.m1228constructorimpl((byte) Long.remainderUnsigned(arg0, m1388constructorimpl(other & 255)));
    }

    @InlineOnly
    /* renamed from: mod-xj2QHRw */
    private static final short m1362modxj2QHRw(long arg0, short other) {
        return UShort.m1495constructorimpl((short) Long.remainderUnsigned(arg0, m1388constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX)));
    }

    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns */
    private static final int m1363modWZ4Q5Ns(long arg0, int other) {
        return UInt.m1308constructorimpl((int) Long.remainderUnsigned(arg0, m1388constructorimpl(other & 4294967295L)));
    }

    @InlineOnly
    /* renamed from: mod-VKZWuLQ */
    private static final long m1364modVKZWuLQ(long arg0, long other) {
        return Long.remainderUnsigned(arg0, other);
    }

    @InlineOnly
    /* renamed from: inc-s-VKNKU */
    private static final long m1365incsVKNKU(long arg0) {
        return m1388constructorimpl(arg0 + 1);
    }

    @InlineOnly
    /* renamed from: dec-s-VKNKU */
    private static final long m1366decsVKNKU(long arg0) {
        return m1388constructorimpl(arg0 - 1);
    }

    @InlineOnly
    /* renamed from: rangeTo-VKZWuLQ */
    private static final ULongRange m1367rangeToVKZWuLQ(long arg0, long other) {
        return new ULongRange(arg0, other, null);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: rangeUntil-VKZWuLQ */
    private static final ULongRange m1368rangeUntilVKZWuLQ(long arg0, long other) {
        return URangesKt.m2510untileb3DHEI(arg0, other);
    }

    @InlineOnly
    /* renamed from: shl-s-VKNKU */
    private static final long m1369shlsVKNKU(long arg0, int bitCount) {
        return m1388constructorimpl(arg0 << bitCount);
    }

    @InlineOnly
    /* renamed from: shr-s-VKNKU */
    private static final long m1370shrsVKNKU(long arg0, int bitCount) {
        return m1388constructorimpl(arg0 >>> bitCount);
    }

    @InlineOnly
    /* renamed from: and-VKZWuLQ */
    private static final long m1371andVKZWuLQ(long arg0, long other) {
        return m1388constructorimpl(arg0 & other);
    }

    @InlineOnly
    /* renamed from: or-VKZWuLQ */
    private static final long m1372orVKZWuLQ(long arg0, long other) {
        return m1388constructorimpl(arg0 | other);
    }

    @InlineOnly
    /* renamed from: xor-VKZWuLQ */
    private static final long m1373xorVKZWuLQ(long arg0, long other) {
        return m1388constructorimpl(arg0 ^ other);
    }

    @InlineOnly
    /* renamed from: inv-s-VKNKU */
    private static final long m1374invsVKNKU(long arg0) {
        return m1388constructorimpl(arg0 ^ (-1));
    }

    @InlineOnly
    /* renamed from: toByte-impl */
    private static final byte m1375toByteimpl(long arg0) {
        return (byte) arg0;
    }

    @InlineOnly
    /* renamed from: toShort-impl */
    private static final short m1376toShortimpl(long arg0) {
        return (short) arg0;
    }

    @InlineOnly
    /* renamed from: toInt-impl */
    private static final int m1377toIntimpl(long arg0) {
        return (int) arg0;
    }

    @InlineOnly
    /* renamed from: toLong-impl */
    private static final long m1378toLongimpl(long arg0) {
        return arg0;
    }

    @InlineOnly
    /* renamed from: toUByte-w2LRezQ */
    private static final byte m1379toUBytew2LRezQ(long arg0) {
        return UByte.m1228constructorimpl((byte) arg0);
    }

    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg */
    private static final short m1380toUShortMh2AYeg(long arg0) {
        return UShort.m1495constructorimpl((short) arg0);
    }

    @InlineOnly
    /* renamed from: toUInt-pVg5ArA */
    private static final int m1381toUIntpVg5ArA(long arg0) {
        return UInt.m1308constructorimpl((int) arg0);
    }

    @InlineOnly
    /* renamed from: toULong-s-VKNKU */
    private static final long m1382toULongsVKNKU(long arg0) {
        return arg0;
    }

    @InlineOnly
    /* renamed from: toFloat-impl */
    private static final float m1383toFloatimpl(long arg0) {
        return (float) UnsignedKt.ulongToDouble(arg0);
    }

    @InlineOnly
    /* renamed from: toDouble-impl */
    private static final double m1384toDoubleimpl(long arg0) {
        return UnsignedKt.ulongToDouble(arg0);
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m1385toStringimpl(long arg0) {
        return UnsignedKt.ulongToString(arg0, 10);
    }

    @NotNull
    public String toString() {
        return m1385toStringimpl(this.data);
    }
}
