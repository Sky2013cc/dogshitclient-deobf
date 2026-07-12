package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: UInt.kt */
@SinceKotlin(version = "1.5")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b\u0003\b\u0087@\u0018�� x2\b\u0012\u0004\u0012\u00020��0\u0001:\u0001xB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0087\n¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020��H\u0097\n¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0015\u001a\u00020��2\u0006\u0010\t\u001a\u00020\nH\u0087\n¢\u0006\u0004\b\u0016\u0010\fJ\u0018\u0010\u0015\u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u0017\u0010\u000fJ\u0018\u0010\u0015\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\n¢\u0006\u0004\b\u0018\u0010\u0011J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\b\u0019\u0010\u001aJ\u0018\u0010\u001b\u001a\u00020��2\u0006\u0010\t\u001a\u00020\nH\u0087\n¢\u0006\u0004\b\u001c\u0010\fJ\u0018\u0010\u001b\u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u001d\u0010\u000fJ\u0018\u0010\u001b\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\n¢\u0006\u0004\b\u001e\u0010\u0011J\u0018\u0010\u001b\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\b\u001f\u0010\u001aJ\u0018\u0010 \u001a\u00020��2\u0006\u0010\t\u001a\u00020\nH\u0087\n¢\u0006\u0004\b!\u0010\fJ\u0018\u0010 \u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\"\u0010\u000fJ\u0018\u0010 \u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\n¢\u0006\u0004\b#\u0010\u0011J\u0018\u0010 \u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\b$\u0010\u001aJ\u0018\u0010%\u001a\u00020��2\u0006\u0010\t\u001a\u00020\nH\u0087\n¢\u0006\u0004\b&\u0010\fJ\u0018\u0010%\u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\n¢\u0006\u0004\b'\u0010\u000fJ\u0018\u0010%\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\n¢\u0006\u0004\b(\u0010\u0011J\u0018\u0010%\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\b)\u0010\u001aJ\u0018\u0010*\u001a\u00020��2\u0006\u0010\t\u001a\u00020\nH\u0087\n¢\u0006\u0004\b+\u0010\fJ\u0018\u0010*\u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\n¢\u0006\u0004\b,\u0010\u000fJ\u0018\u0010*\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\n¢\u0006\u0004\b-\u0010\u0011J\u0018\u0010*\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\b.\u0010\u001aJ\u0018\u0010/\u001a\u00020��2\u0006\u0010\t\u001a\u00020\nH\u0087\b¢\u0006\u0004\b0\u0010\fJ\u0018\u0010/\u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\b¢\u0006\u0004\b1\u0010\u000fJ\u0018\u0010/\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\b¢\u0006\u0004\b2\u0010\u0011J\u0018\u0010/\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b3\u0010\u001aJ\u0018\u00104\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0087\b¢\u0006\u0004\b5\u00106J\u0018\u00104\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\rH\u0087\b¢\u0006\u0004\b7\u00108J\u0018\u00104\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\b¢\u0006\u0004\b9\u0010\u0011J\u0018\u00104\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b:\u0010\u001aJ\u0010\u0010;\u001a\u00020��H\u0087\n¢\u0006\u0004\b<\u0010\u0005J\u0010\u0010=\u001a\u00020��H\u0087\n¢\u0006\u0004\b>\u0010\u0005J\u0018\u0010?\u001a\u00020@2\u0006\u0010\t\u001a\u00020��H\u0087\n¢\u0006\u0004\bA\u0010BJ\u0018\u0010C\u001a\u00020@2\u0006\u0010\t\u001a\u00020��H\u0087\n¢\u0006\u0004\bD\u0010BJ\u0018\u0010E\u001a\u00020��2\u0006\u0010F\u001a\u00020\u0003H\u0087\f¢\u0006\u0004\bG\u0010\u0011J\u0018\u0010H\u001a\u00020��2\u0006\u0010F\u001a\u00020\u0003H\u0087\f¢\u0006\u0004\bI\u0010\u0011J\u0018\u0010J\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\f¢\u0006\u0004\bK\u0010\u0011J\u0018\u0010L\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\f¢\u0006\u0004\bM\u0010\u0011J\u0018\u0010N\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\f¢\u0006\u0004\bO\u0010\u0011J\u0010\u0010P\u001a\u00020��H\u0087\b¢\u0006\u0004\bQ\u0010\u0005J\u0010\u0010R\u001a\u00020SH\u0087\b¢\u0006\u0004\bT\u0010UJ\u0010\u0010V\u001a\u00020WH\u0087\b¢\u0006\u0004\bX\u0010YJ\u0010\u0010Z\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\b[\u0010\u0005J\u0010\u0010\\\u001a\u00020]H\u0087\b¢\u0006\u0004\b^\u0010_J\u0010\u0010`\u001a\u00020\nH\u0087\b¢\u0006\u0004\ba\u0010UJ\u0010\u0010b\u001a\u00020\rH\u0087\b¢\u0006\u0004\bc\u0010YJ\u0010\u0010d\u001a\u00020��H\u0087\b¢\u0006\u0004\be\u0010\u0005J\u0010\u0010f\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\bg\u0010_J\u0010\u0010h\u001a\u00020iH\u0087\b¢\u0006\u0004\bj\u0010kJ\u0010\u0010l\u001a\u00020mH\u0087\b¢\u0006\u0004\bn\u0010oJ\u000f\u0010p\u001a\u00020qH\u0016¢\u0006\u0004\br\u0010sJ\u0013\u0010t\u001a\u00020u2\b\u0010\t\u001a\u0004\u0018\u00010vHÖ\u0003J\t\u0010w\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006y"}, d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "compareTo", "other", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "compareTo-WZ4Q5Ns", "(II)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "plus", "plus-7apg3OU", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(IJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(IB)B", "mod-xj2QHRw", "(IS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-pVg5ArA", "dec", "dec-pVg5ArA", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-WZ4Q5Ns", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "and", "and-WZ4Q5Ns", "or", "or-WZ4Q5Ns", "xor", "xor-WZ4Q5Ns", "inv", "inv-pVg5ArA", "toByte", "", "toByte-impl", "(I)B", "toShort", "", "toShort-impl", "(I)S", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", "", "toFloat-impl", "(I)F", "toDouble", "", "toDouble-impl", "(I)D", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "kotlin-stdlib"})
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: target.jar:kotlin/UInt.class */
public final class UInt implements Comparable<UInt> {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final int data;
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = -1;
    public static final int SIZE_BYTES = 4;
    public static final int SIZE_BITS = 32;

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl */
    public static int m1306hashCodeimpl(int arg0) {
        return Integer.hashCode(arg0);
    }

    public int hashCode() {
        return m1306hashCodeimpl(this.data);
    }

    /* renamed from: equals-impl */
    public static boolean m1307equalsimpl(int arg0, Object other) {
        return (other instanceof UInt) && arg0 == ((UInt) other).m1310unboximpl();
    }

    public boolean equals(Object other) {
        return m1307equalsimpl(this.data, other);
    }

    @IntrinsicConstEvaluation
    @PublishedApi
    /* renamed from: constructor-impl */
    public static int m1308constructorimpl(int data) {
        return data;
    }

    /* renamed from: box-impl */
    public static final /* synthetic */ UInt m1309boximpl(int v) {
        return new UInt(v);
    }

    /* renamed from: unbox-impl */
    public final /* synthetic */ int m1310unboximpl() {
        return this.data;
    }

    /* renamed from: equals-impl0 */
    public static final boolean m1311equalsimpl0(int p1, int p2) {
        return p1 == p2;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return UnsignedKt.uintCompare(m1310unboximpl(), uInt.m1310unboximpl());
    }

    @IntrinsicConstEvaluation
    @PublishedApi
    private /* synthetic */ UInt(int data) {
        this.data = data;
    }

    /* compiled from: UInt.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lkotlin/UInt$Companion;", "", Constants.CTOR, "()V", "MIN_VALUE", "Lkotlin/UInt;", "I", "MAX_VALUE", "SIZE_BYTES", "", "SIZE_BITS", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/UInt$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU */
    private static final int m1252compareTo7apg3OU(int arg0, byte other) {
        return Integer.compareUnsigned(arg0, m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw */
    private static final int m1253compareToxj2QHRw(int arg0, short other) {
        return Integer.compareUnsigned(arg0, m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns */
    private int m1255compareToWZ4Q5Ns(int other) {
        return UnsignedKt.uintCompare(m1310unboximpl(), other);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns */
    private static int m1254compareToWZ4Q5Ns(int arg0, int other) {
        return UnsignedKt.uintCompare(arg0, other);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ */
    private static final int m1256compareToVKZWuLQ(int arg0, long other) {
        return Long.compareUnsigned(ULong.m1388constructorimpl(arg0 & 4294967295L), other);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU */
    private static final int m1257plus7apg3OU(int arg0, byte other) {
        return m1308constructorimpl(arg0 + m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw */
    private static final int m1258plusxj2QHRw(int arg0, short other) {
        return m1308constructorimpl(arg0 + m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns */
    private static final int m1259plusWZ4Q5Ns(int arg0, int other) {
        return m1308constructorimpl(arg0 + other);
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ */
    private static final long m1260plusVKZWuLQ(int arg0, long other) {
        return ULong.m1388constructorimpl(ULong.m1388constructorimpl(arg0 & 4294967295L) + other);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU */
    private static final int m1261minus7apg3OU(int arg0, byte other) {
        return m1308constructorimpl(arg0 - m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw */
    private static final int m1262minusxj2QHRw(int arg0, short other) {
        return m1308constructorimpl(arg0 - m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns */
    private static final int m1263minusWZ4Q5Ns(int arg0, int other) {
        return m1308constructorimpl(arg0 - other);
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ */
    private static final long m1264minusVKZWuLQ(int arg0, long other) {
        return ULong.m1388constructorimpl(ULong.m1388constructorimpl(arg0 & 4294967295L) - other);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU */
    private static final int m1265times7apg3OU(int arg0, byte other) {
        return m1308constructorimpl(arg0 * m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw */
    private static final int m1266timesxj2QHRw(int arg0, short other) {
        return m1308constructorimpl(arg0 * m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns */
    private static final int m1267timesWZ4Q5Ns(int arg0, int other) {
        return m1308constructorimpl(arg0 * other);
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ */
    private static final long m1268timesVKZWuLQ(int arg0, long other) {
        return ULong.m1388constructorimpl(ULong.m1388constructorimpl(arg0 & 4294967295L) * other);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU */
    private static final int m1269div7apg3OU(int arg0, byte other) {
        return Integer.divideUnsigned(arg0, m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw */
    private static final int m1270divxj2QHRw(int arg0, short other) {
        return Integer.divideUnsigned(arg0, m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns */
    private static final int m1271divWZ4Q5Ns(int arg0, int other) {
        return UnsignedKt.m1521uintDivideJ1ME1BU(arg0, other);
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ */
    private static final long m1272divVKZWuLQ(int arg0, long other) {
        return Long.divideUnsigned(ULong.m1388constructorimpl(arg0 & 4294967295L), other);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU */
    private static final int m1273rem7apg3OU(int arg0, byte other) {
        return Integer.remainderUnsigned(arg0, m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw */
    private static final int m1274remxj2QHRw(int arg0, short other) {
        return Integer.remainderUnsigned(arg0, m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns */
    private static final int m1275remWZ4Q5Ns(int arg0, int other) {
        return UnsignedKt.m1520uintRemainderJ1ME1BU(arg0, other);
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ */
    private static final long m1276remVKZWuLQ(int arg0, long other) {
        return Long.remainderUnsigned(ULong.m1388constructorimpl(arg0 & 4294967295L), other);
    }

    @InlineOnly
    /* renamed from: floorDiv-7apg3OU */
    private static final int m1277floorDiv7apg3OU(int arg0, byte other) {
        return Integer.divideUnsigned(arg0, m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw */
    private static final int m1278floorDivxj2QHRw(int arg0, short other) {
        return Integer.divideUnsigned(arg0, m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns */
    private static final int m1279floorDivWZ4Q5Ns(int arg0, int other) {
        return Integer.divideUnsigned(arg0, other);
    }

    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ */
    private static final long m1280floorDivVKZWuLQ(int arg0, long other) {
        return Long.divideUnsigned(ULong.m1388constructorimpl(arg0 & 4294967295L), other);
    }

    @InlineOnly
    /* renamed from: mod-7apg3OU */
    private static final byte m1281mod7apg3OU(int arg0, byte other) {
        return UByte.m1228constructorimpl((byte) Integer.remainderUnsigned(arg0, m1308constructorimpl(other & 255)));
    }

    @InlineOnly
    /* renamed from: mod-xj2QHRw */
    private static final short m1282modxj2QHRw(int arg0, short other) {
        return UShort.m1495constructorimpl((short) Integer.remainderUnsigned(arg0, m1308constructorimpl(other & 65535)));
    }

    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns */
    private static final int m1283modWZ4Q5Ns(int arg0, int other) {
        return Integer.remainderUnsigned(arg0, other);
    }

    @InlineOnly
    /* renamed from: mod-VKZWuLQ */
    private static final long m1284modVKZWuLQ(int arg0, long other) {
        return Long.remainderUnsigned(ULong.m1388constructorimpl(arg0 & 4294967295L), other);
    }

    @InlineOnly
    /* renamed from: inc-pVg5ArA */
    private static final int m1285incpVg5ArA(int arg0) {
        return m1308constructorimpl(arg0 + 1);
    }

    @InlineOnly
    /* renamed from: dec-pVg5ArA */
    private static final int m1286decpVg5ArA(int arg0) {
        return m1308constructorimpl(arg0 - 1);
    }

    @InlineOnly
    /* renamed from: rangeTo-WZ4Q5Ns */
    private static final UIntRange m1287rangeToWZ4Q5Ns(int arg0, int other) {
        return new UIntRange(arg0, other, null);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: rangeUntil-WZ4Q5Ns */
    private static final UIntRange m1288rangeUntilWZ4Q5Ns(int arg0, int other) {
        return URangesKt.m2509untilJ1ME1BU(arg0, other);
    }

    @InlineOnly
    /* renamed from: shl-pVg5ArA */
    private static final int m1289shlpVg5ArA(int arg0, int bitCount) {
        return m1308constructorimpl(arg0 << bitCount);
    }

    @InlineOnly
    /* renamed from: shr-pVg5ArA */
    private static final int m1290shrpVg5ArA(int arg0, int bitCount) {
        return m1308constructorimpl(arg0 >>> bitCount);
    }

    @InlineOnly
    /* renamed from: and-WZ4Q5Ns */
    private static final int m1291andWZ4Q5Ns(int arg0, int other) {
        return m1308constructorimpl(arg0 & other);
    }

    @InlineOnly
    /* renamed from: or-WZ4Q5Ns */
    private static final int m1292orWZ4Q5Ns(int arg0, int other) {
        return m1308constructorimpl(arg0 | other);
    }

    @InlineOnly
    /* renamed from: xor-WZ4Q5Ns */
    private static final int m1293xorWZ4Q5Ns(int arg0, int other) {
        return m1308constructorimpl(arg0 ^ other);
    }

    @InlineOnly
    /* renamed from: inv-pVg5ArA */
    private static final int m1294invpVg5ArA(int arg0) {
        return m1308constructorimpl(arg0 ^ (-1));
    }

    @InlineOnly
    /* renamed from: toByte-impl */
    private static final byte m1295toByteimpl(int arg0) {
        return (byte) arg0;
    }

    @InlineOnly
    /* renamed from: toShort-impl */
    private static final short m1296toShortimpl(int arg0) {
        return (short) arg0;
    }

    @InlineOnly
    /* renamed from: toInt-impl */
    private static final int m1297toIntimpl(int arg0) {
        return arg0;
    }

    @InlineOnly
    /* renamed from: toLong-impl */
    private static final long m1298toLongimpl(int arg0) {
        return arg0 & 4294967295L;
    }

    @InlineOnly
    /* renamed from: toUByte-w2LRezQ */
    private static final byte m1299toUBytew2LRezQ(int arg0) {
        return UByte.m1228constructorimpl((byte) arg0);
    }

    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg */
    private static final short m1300toUShortMh2AYeg(int arg0) {
        return UShort.m1495constructorimpl((short) arg0);
    }

    @InlineOnly
    /* renamed from: toUInt-pVg5ArA */
    private static final int m1301toUIntpVg5ArA(int arg0) {
        return arg0;
    }

    @InlineOnly
    /* renamed from: toULong-s-VKNKU */
    private static final long m1302toULongsVKNKU(int arg0) {
        return ULong.m1388constructorimpl(arg0 & 4294967295L);
    }

    @InlineOnly
    /* renamed from: toFloat-impl */
    private static final float m1303toFloatimpl(int arg0) {
        return (float) UnsignedKt.uintToDouble(arg0);
    }

    @InlineOnly
    /* renamed from: toDouble-impl */
    private static final double m1304toDoubleimpl(int arg0) {
        return UnsignedKt.uintToDouble(arg0);
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m1305toStringimpl(int arg0) {
        return String.valueOf(arg0 & 4294967295L);
    }

    @NotNull
    public String toString() {
        return m1305toStringimpl(this.data);
    }
}
