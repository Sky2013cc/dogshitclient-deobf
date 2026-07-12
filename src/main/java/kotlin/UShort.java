package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;
import okhttp3.internal.ws.WebSocketProtocol;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: UShort.kt */
@SinceKotlin(version = "1.5")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��l\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b\u0003\b\u0087@\u0018�� s2\b\u0012\u0004\u0012\u00020��0\u0001:\u0001sB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020��H\u0097\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\u0017\u0010\rJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b\u0018\u0010\u000fJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0019\u0010\u0012J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\u001d\u0010\rJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b\u001e\u0010\u000fJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u001f\u0010\u0012J\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b \u0010\u001bJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\"\u0010\rJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b#\u0010\u000fJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b$\u0010\u0012J\u0018\u0010!\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b%\u0010\u001bJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b'\u0010\rJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b(\u0010\u000fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b)\u0010\u0012J\u0018\u0010&\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b*\u0010\u001bJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b,\u0010\rJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b-\u0010\u000fJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b.\u0010\u0012J\u0018\u0010+\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b/\u0010\u001bJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b1\u0010\rJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\b¢\u0006\u0004\b2\u0010\u000fJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b3\u0010\u0012J\u0018\u00100\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b4\u0010\u001bJ\u0018\u00105\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b6\u00107J\u0018\u00105\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\b¢\u0006\u0004\b8\u00109J\u0018\u00105\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b:\u0010\u0012J\u0018\u00105\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b;\u0010\u001bJ\u0010\u0010<\u001a\u00020��H\u0087\n¢\u0006\u0004\b=\u0010\u0005J\u0010\u0010>\u001a\u00020��H\u0087\n¢\u0006\u0004\b?\u0010\u0005J\u0018\u0010@\u001a\u00020A2\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\bB\u0010CJ\u0018\u0010D\u001a\u00020A2\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\bE\u0010CJ\u0018\u0010F\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\f¢\u0006\u0004\bG\u00109J\u0018\u0010H\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\f¢\u0006\u0004\bI\u00109J\u0018\u0010J\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\f¢\u0006\u0004\bK\u00109J\u0010\u0010L\u001a\u00020��H\u0087\b¢\u0006\u0004\bM\u0010\u0005J\u0010\u0010N\u001a\u00020OH\u0087\b¢\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bS\u0010\u0005J\u0010\u0010T\u001a\u00020\tH\u0087\b¢\u0006\u0004\bU\u0010VJ\u0010\u0010W\u001a\u00020XH\u0087\b¢\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b\\\u0010QJ\u0010\u0010]\u001a\u00020��H\u0087\b¢\u0006\u0004\b^\u0010\u0005J\u0010\u0010_\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b`\u0010VJ\u0010\u0010a\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\bb\u0010ZJ\u0010\u0010c\u001a\u00020dH\u0087\b¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020hH\u0087\b¢\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0013\u0010o\u001a\u00020p2\b\u0010\n\u001a\u0004\u0018\u00010qHÖ\u0003J\t\u0010r\u001a\u00020\tHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006t"}, d2 = {"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "getData$annotations", "()V", "compareTo", "", "other", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "compareTo-xj2QHRw", "(SS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "plus", "plus-7apg3OU", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(SJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(SB)B", "mod-xj2QHRw", "(SS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-Mh2AYeg", "dec", "dec-Mh2AYeg", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-xj2QHRw", "and", "and-xj2QHRw", "or", "or-xj2QHRw", "xor", "xor-xj2QHRw", "inv", "inv-Mh2AYeg", "toByte", "", "toByte-impl", "(S)B", "toShort", "toShort-impl", "toInt", "toInt-impl", "(S)I", "toLong", "", "toLong-impl", "(S)J", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", "", "toFloat-impl", "(S)F", "toDouble", "", "toDouble-impl", "(S)D", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "kotlin-stdlib"})
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: target.jar:kotlin/UShort.class */
public final class UShort implements Comparable<UShort> {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final short data;
    public static final short MIN_VALUE = 0;
    public static final short MAX_VALUE = -1;
    public static final int SIZE_BYTES = 2;
    public static final int SIZE_BITS = 16;

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl */
    public static int m1493hashCodeimpl(short arg0) {
        return Short.hashCode(arg0);
    }

    public int hashCode() {
        return m1493hashCodeimpl(this.data);
    }

    /* renamed from: equals-impl */
    public static boolean m1494equalsimpl(short arg0, Object other) {
        return (other instanceof UShort) && arg0 == ((UShort) other).m1497unboximpl();
    }

    public boolean equals(Object other) {
        return m1494equalsimpl(this.data, other);
    }

    @IntrinsicConstEvaluation
    @PublishedApi
    /* renamed from: constructor-impl */
    public static short m1495constructorimpl(short data) {
        return data;
    }

    /* renamed from: box-impl */
    public static final /* synthetic */ UShort m1496boximpl(short v) {
        return new UShort(v);
    }

    /* renamed from: unbox-impl */
    public final /* synthetic */ short m1497unboximpl() {
        return this.data;
    }

    /* renamed from: equals-impl0 */
    public static final boolean m1498equalsimpl0(short p1, short p2) {
        return p1 == p2;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UShort uShort) {
        return Intrinsics.compare(m1497unboximpl() & 65535, uShort.m1497unboximpl() & 65535);
    }

    @IntrinsicConstEvaluation
    @PublishedApi
    private /* synthetic */ UShort(short data) {
        this.data = data;
    }

    /* compiled from: UShort.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lkotlin/UShort$Companion;", "", Constants.CTOR, "()V", "MIN_VALUE", "Lkotlin/UShort;", "S", "MAX_VALUE", "SIZE_BYTES", "", "SIZE_BITS", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/UShort$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU */
    private static final int m1441compareTo7apg3OU(short arg0, byte other) {
        return Intrinsics.compare(arg0 & 65535, other & 255);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw */
    private int m1443compareToxj2QHRw(short other) {
        return Intrinsics.compare(m1497unboximpl() & 65535, other & 65535);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw */
    private static int m1442compareToxj2QHRw(short arg0, short other) {
        return Intrinsics.compare(arg0 & 65535, other & 65535);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns */
    private static final int m1444compareToWZ4Q5Ns(short arg0, int other) {
        return Integer.compareUnsigned(UInt.m1308constructorimpl(arg0 & 65535), other);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ */
    private static final int m1445compareToVKZWuLQ(short arg0, long other) {
        return Long.compareUnsigned(ULong.m1388constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU */
    private static final int m1446plus7apg3OU(short arg0, byte other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 65535) + UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw */
    private static final int m1447plusxj2QHRw(short arg0, short other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 65535) + UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns */
    private static final int m1448plusWZ4Q5Ns(short arg0, int other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 65535) + other);
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ */
    private static final long m1449plusVKZWuLQ(short arg0, long other) {
        return ULong.m1388constructorimpl(ULong.m1388constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX) + other);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU */
    private static final int m1450minus7apg3OU(short arg0, byte other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 65535) - UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw */
    private static final int m1451minusxj2QHRw(short arg0, short other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 65535) - UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns */
    private static final int m1452minusWZ4Q5Ns(short arg0, int other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 65535) - other);
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ */
    private static final long m1453minusVKZWuLQ(short arg0, long other) {
        return ULong.m1388constructorimpl(ULong.m1388constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX) - other);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU */
    private static final int m1454times7apg3OU(short arg0, byte other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 65535) * UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw */
    private static final int m1455timesxj2QHRw(short arg0, short other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 65535) * UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns */
    private static final int m1456timesWZ4Q5Ns(short arg0, int other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 65535) * other);
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ */
    private static final long m1457timesVKZWuLQ(short arg0, long other) {
        return ULong.m1388constructorimpl(ULong.m1388constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX) * other);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU */
    private static final int m1458div7apg3OU(short arg0, byte other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 65535), UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw */
    private static final int m1459divxj2QHRw(short arg0, short other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 65535), UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns */
    private static final int m1460divWZ4Q5Ns(short arg0, int other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 65535), other);
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ */
    private static final long m1461divVKZWuLQ(short arg0, long other) {
        return Long.divideUnsigned(ULong.m1388constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU */
    private static final int m1462rem7apg3OU(short arg0, byte other) {
        return Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 65535), UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw */
    private static final int m1463remxj2QHRw(short arg0, short other) {
        return Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 65535), UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns */
    private static final int m1464remWZ4Q5Ns(short arg0, int other) {
        return Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 65535), other);
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ */
    private static final long m1465remVKZWuLQ(short arg0, long other) {
        return Long.remainderUnsigned(ULong.m1388constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    @InlineOnly
    /* renamed from: floorDiv-7apg3OU */
    private static final int m1466floorDiv7apg3OU(short arg0, byte other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 65535), UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw */
    private static final int m1467floorDivxj2QHRw(short arg0, short other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 65535), UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns */
    private static final int m1468floorDivWZ4Q5Ns(short arg0, int other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 65535), other);
    }

    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ */
    private static final long m1469floorDivVKZWuLQ(short arg0, long other) {
        return Long.divideUnsigned(ULong.m1388constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    @InlineOnly
    /* renamed from: mod-7apg3OU */
    private static final byte m1470mod7apg3OU(short arg0, byte other) {
        return UByte.m1228constructorimpl((byte) Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 65535), UInt.m1308constructorimpl(other & 255)));
    }

    @InlineOnly
    /* renamed from: mod-xj2QHRw */
    private static final short m1471modxj2QHRw(short arg0, short other) {
        return m1495constructorimpl((short) Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 65535), UInt.m1308constructorimpl(other & 65535)));
    }

    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns */
    private static final int m1472modWZ4Q5Ns(short arg0, int other) {
        return Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 65535), other);
    }

    @InlineOnly
    /* renamed from: mod-VKZWuLQ */
    private static final long m1473modVKZWuLQ(short arg0, long other) {
        return Long.remainderUnsigned(ULong.m1388constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    @InlineOnly
    /* renamed from: inc-Mh2AYeg */
    private static final short m1474incMh2AYeg(short arg0) {
        return m1495constructorimpl((short) (arg0 + 1));
    }

    @InlineOnly
    /* renamed from: dec-Mh2AYeg */
    private static final short m1475decMh2AYeg(short arg0) {
        return m1495constructorimpl((short) (arg0 - 1));
    }

    @InlineOnly
    /* renamed from: rangeTo-xj2QHRw */
    private static final UIntRange m1476rangeToxj2QHRw(short arg0, short other) {
        return new UIntRange(UInt.m1308constructorimpl(arg0 & 65535), UInt.m1308constructorimpl(other & 65535), null);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: rangeUntil-xj2QHRw */
    private static final UIntRange m1477rangeUntilxj2QHRw(short arg0, short other) {
        return URangesKt.m2509untilJ1ME1BU(UInt.m1308constructorimpl(arg0 & 65535), UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: and-xj2QHRw */
    private static final short m1478andxj2QHRw(short arg0, short other) {
        return m1495constructorimpl((short) (arg0 & other));
    }

    @InlineOnly
    /* renamed from: or-xj2QHRw */
    private static final short m1479orxj2QHRw(short arg0, short other) {
        return m1495constructorimpl((short) (arg0 | other));
    }

    @InlineOnly
    /* renamed from: xor-xj2QHRw */
    private static final short m1480xorxj2QHRw(short arg0, short other) {
        return m1495constructorimpl((short) (arg0 ^ other));
    }

    @InlineOnly
    /* renamed from: inv-Mh2AYeg */
    private static final short m1481invMh2AYeg(short arg0) {
        return m1495constructorimpl((short) (arg0 ^ (-1)));
    }

    @InlineOnly
    /* renamed from: toByte-impl */
    private static final byte m1482toByteimpl(short arg0) {
        return (byte) arg0;
    }

    @InlineOnly
    /* renamed from: toShort-impl */
    private static final short m1483toShortimpl(short arg0) {
        return arg0;
    }

    @InlineOnly
    /* renamed from: toInt-impl */
    private static final int m1484toIntimpl(short arg0) {
        return arg0 & 65535;
    }

    @InlineOnly
    /* renamed from: toLong-impl */
    private static final long m1485toLongimpl(short arg0) {
        return arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX;
    }

    @InlineOnly
    /* renamed from: toUByte-w2LRezQ */
    private static final byte m1486toUBytew2LRezQ(short arg0) {
        return UByte.m1228constructorimpl((byte) arg0);
    }

    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg */
    private static final short m1487toUShortMh2AYeg(short arg0) {
        return arg0;
    }

    @InlineOnly
    /* renamed from: toUInt-pVg5ArA */
    private static final int m1488toUIntpVg5ArA(short arg0) {
        return UInt.m1308constructorimpl(arg0 & 65535);
    }

    @InlineOnly
    /* renamed from: toULong-s-VKNKU */
    private static final long m1489toULongsVKNKU(short arg0) {
        return ULong.m1388constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    @InlineOnly
    /* renamed from: toFloat-impl */
    private static final float m1490toFloatimpl(short arg0) {
        return (float) UnsignedKt.uintToDouble(arg0 & 65535);
    }

    @InlineOnly
    /* renamed from: toDouble-impl */
    private static final double m1491toDoubleimpl(short arg0) {
        return UnsignedKt.uintToDouble(arg0 & 65535);
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m1492toStringimpl(short arg0) {
        return String.valueOf(arg0 & 65535);
    }

    @NotNull
    public String toString() {
        return m1492toStringimpl(this.data);
    }
}
