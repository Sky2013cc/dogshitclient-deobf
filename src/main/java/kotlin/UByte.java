package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: UByte.kt */
@SinceKotlin(version = "1.5")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b\u0003\b\u0087@\u0018�� s2\b\u0012\u0004\u0012\u00020��0\u0001:\u0001sB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020��H\u0097\n¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b\u0017\u0010\fJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u0018\u0010\u000fJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0019\u0010\u0012J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b\u001d\u0010\fJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u001e\u0010\u000fJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u001f\u0010\u0012J\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b \u0010\u001bJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b\"\u0010\fJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b#\u0010\u000fJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b$\u0010\u0012J\u0018\u0010!\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b%\u0010\u001bJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b'\u0010\fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b(\u0010\u000fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b)\u0010\u0012J\u0018\u0010&\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b*\u0010\u001bJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\b,\u0010\fJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b-\u0010\u000fJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b.\u0010\u0012J\u0018\u0010+\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b/\u0010\u001bJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020��H\u0087\b¢\u0006\u0004\b1\u0010\fJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\b¢\u0006\u0004\b2\u0010\u000fJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b3\u0010\u0012J\u0018\u00100\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b4\u0010\u001bJ\u0018\u00105\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\b¢\u0006\u0004\b6\u00107J\u0018\u00105\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\rH\u0087\b¢\u0006\u0004\b8\u00109J\u0018\u00105\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b:\u0010\u0012J\u0018\u00105\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b;\u0010\u001bJ\u0010\u0010<\u001a\u00020��H\u0087\n¢\u0006\u0004\b=\u0010\u0005J\u0010\u0010>\u001a\u00020��H\u0087\n¢\u0006\u0004\b?\u0010\u0005J\u0018\u0010@\u001a\u00020A2\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\bB\u0010CJ\u0018\u0010D\u001a\u00020A2\u0006\u0010\n\u001a\u00020��H\u0087\n¢\u0006\u0004\bE\u0010CJ\u0018\u0010F\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\f¢\u0006\u0004\bG\u00107J\u0018\u0010H\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\f¢\u0006\u0004\bI\u00107J\u0018\u0010J\u001a\u00020��2\u0006\u0010\n\u001a\u00020��H\u0087\f¢\u0006\u0004\bK\u00107J\u0010\u0010L\u001a\u00020��H\u0087\b¢\u0006\u0004\bM\u0010\u0005J\u0010\u0010N\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bO\u0010\u0005J\u0010\u0010P\u001a\u00020QH\u0087\b¢\u0006\u0004\bR\u0010SJ\u0010\u0010T\u001a\u00020\tH\u0087\b¢\u0006\u0004\bU\u0010VJ\u0010\u0010W\u001a\u00020XH\u0087\b¢\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001a\u00020��H\u0087\b¢\u0006\u0004\b\\\u0010\u0005J\u0010\u0010]\u001a\u00020\rH\u0087\b¢\u0006\u0004\b^\u0010SJ\u0010\u0010_\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b`\u0010VJ\u0010\u0010a\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\bb\u0010ZJ\u0010\u0010c\u001a\u00020dH\u0087\b¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020hH\u0087\b¢\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0013\u0010o\u001a\u00020p2\b\u0010\n\u001a\u0004\u0018\u00010qHÖ\u0003J\t\u0010r\u001a\u00020\tHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006t"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "compareTo", "", "other", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "plus", "plus-7apg3OU", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(BJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(BB)B", "mod-xj2QHRw", "(BS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-w2LRezQ", "dec", "dec-w2LRezQ", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-7apg3OU", "and", "and-7apg3OU", "or", "or-7apg3OU", "xor", "xor-7apg3OU", "inv", "inv-w2LRezQ", "toByte", "toByte-impl", "toShort", "", "toShort-impl", "(B)S", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", "", "toFloat-impl", "(B)F", "toDouble", "", "toDouble-impl", "(B)D", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "kotlin-stdlib"})
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: target.jar:kotlin/UByte.class */
public final class UByte implements Comparable<UByte> {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final byte data;
    public static final byte MIN_VALUE = 0;
    public static final byte MAX_VALUE = -1;
    public static final int SIZE_BYTES = 1;
    public static final int SIZE_BITS = 8;

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl */
    public static int m1226hashCodeimpl(byte arg0) {
        return Byte.hashCode(arg0);
    }

    public int hashCode() {
        return m1226hashCodeimpl(this.data);
    }

    /* renamed from: equals-impl */
    public static boolean m1227equalsimpl(byte arg0, Object other) {
        return (other instanceof UByte) && arg0 == ((UByte) other).m1230unboximpl();
    }

    public boolean equals(Object other) {
        return m1227equalsimpl(this.data, other);
    }

    @IntrinsicConstEvaluation
    @PublishedApi
    /* renamed from: constructor-impl */
    public static byte m1228constructorimpl(byte data) {
        return data;
    }

    /* renamed from: box-impl */
    public static final /* synthetic */ UByte m1229boximpl(byte v) {
        return new UByte(v);
    }

    /* renamed from: unbox-impl */
    public final /* synthetic */ byte m1230unboximpl() {
        return this.data;
    }

    /* renamed from: equals-impl0 */
    public static final boolean m1231equalsimpl0(byte p1, byte p2) {
        return p1 == p2;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte uByte) {
        return Intrinsics.compare(m1230unboximpl() & 255, uByte.m1230unboximpl() & 255);
    }

    @IntrinsicConstEvaluation
    @PublishedApi
    private /* synthetic */ UByte(byte data) {
        this.data = data;
    }

    /* compiled from: UByte.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lkotlin/UByte$Companion;", "", Constants.CTOR, "()V", "MIN_VALUE", "Lkotlin/UByte;", "B", "MAX_VALUE", "SIZE_BYTES", "", "SIZE_BITS", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/UByte$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU */
    private int m1175compareTo7apg3OU(byte other) {
        return Intrinsics.compare(m1230unboximpl() & 255, other & 255);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU */
    private static int m1174compareTo7apg3OU(byte arg0, byte other) {
        return Intrinsics.compare(arg0 & 255, other & 255);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw */
    private static final int m1176compareToxj2QHRw(byte arg0, short other) {
        return Intrinsics.compare(arg0 & 255, other & 65535);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns */
    private static final int m1177compareToWZ4Q5Ns(byte arg0, int other) {
        return Integer.compareUnsigned(UInt.m1308constructorimpl(arg0 & 255), other);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ */
    private static final int m1178compareToVKZWuLQ(byte arg0, long other) {
        return Long.compareUnsigned(ULong.m1388constructorimpl(arg0 & 255), other);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU */
    private static final int m1179plus7apg3OU(byte arg0, byte other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 255) + UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw */
    private static final int m1180plusxj2QHRw(byte arg0, short other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 255) + UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns */
    private static final int m1181plusWZ4Q5Ns(byte arg0, int other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 255) + other);
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ */
    private static final long m1182plusVKZWuLQ(byte arg0, long other) {
        return ULong.m1388constructorimpl(ULong.m1388constructorimpl(arg0 & 255) + other);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU */
    private static final int m1183minus7apg3OU(byte arg0, byte other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 255) - UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw */
    private static final int m1184minusxj2QHRw(byte arg0, short other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 255) - UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns */
    private static final int m1185minusWZ4Q5Ns(byte arg0, int other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 255) - other);
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ */
    private static final long m1186minusVKZWuLQ(byte arg0, long other) {
        return ULong.m1388constructorimpl(ULong.m1388constructorimpl(arg0 & 255) - other);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU */
    private static final int m1187times7apg3OU(byte arg0, byte other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 255) * UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw */
    private static final int m1188timesxj2QHRw(byte arg0, short other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 255) * UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns */
    private static final int m1189timesWZ4Q5Ns(byte arg0, int other) {
        return UInt.m1308constructorimpl(UInt.m1308constructorimpl(arg0 & 255) * other);
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ */
    private static final long m1190timesVKZWuLQ(byte arg0, long other) {
        return ULong.m1388constructorimpl(ULong.m1388constructorimpl(arg0 & 255) * other);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU */
    private static final int m1191div7apg3OU(byte arg0, byte other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 255), UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw */
    private static final int m1192divxj2QHRw(byte arg0, short other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 255), UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns */
    private static final int m1193divWZ4Q5Ns(byte arg0, int other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 255), other);
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ */
    private static final long m1194divVKZWuLQ(byte arg0, long other) {
        return Long.divideUnsigned(ULong.m1388constructorimpl(arg0 & 255), other);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU */
    private static final int m1195rem7apg3OU(byte arg0, byte other) {
        return Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 255), UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw */
    private static final int m1196remxj2QHRw(byte arg0, short other) {
        return Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 255), UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns */
    private static final int m1197remWZ4Q5Ns(byte arg0, int other) {
        return Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 255), other);
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ */
    private static final long m1198remVKZWuLQ(byte arg0, long other) {
        return Long.remainderUnsigned(ULong.m1388constructorimpl(arg0 & 255), other);
    }

    @InlineOnly
    /* renamed from: floorDiv-7apg3OU */
    private static final int m1199floorDiv7apg3OU(byte arg0, byte other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 255), UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw */
    private static final int m1200floorDivxj2QHRw(byte arg0, short other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 255), UInt.m1308constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns */
    private static final int m1201floorDivWZ4Q5Ns(byte arg0, int other) {
        return Integer.divideUnsigned(UInt.m1308constructorimpl(arg0 & 255), other);
    }

    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ */
    private static final long m1202floorDivVKZWuLQ(byte arg0, long other) {
        return Long.divideUnsigned(ULong.m1388constructorimpl(arg0 & 255), other);
    }

    @InlineOnly
    /* renamed from: mod-7apg3OU */
    private static final byte m1203mod7apg3OU(byte arg0, byte other) {
        return m1228constructorimpl((byte) Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 255), UInt.m1308constructorimpl(other & 255)));
    }

    @InlineOnly
    /* renamed from: mod-xj2QHRw */
    private static final short m1204modxj2QHRw(byte arg0, short other) {
        return UShort.m1495constructorimpl((short) Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 255), UInt.m1308constructorimpl(other & 65535)));
    }

    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns */
    private static final int m1205modWZ4Q5Ns(byte arg0, int other) {
        return Integer.remainderUnsigned(UInt.m1308constructorimpl(arg0 & 255), other);
    }

    @InlineOnly
    /* renamed from: mod-VKZWuLQ */
    private static final long m1206modVKZWuLQ(byte arg0, long other) {
        return Long.remainderUnsigned(ULong.m1388constructorimpl(arg0 & 255), other);
    }

    @InlineOnly
    /* renamed from: inc-w2LRezQ */
    private static final byte m1207incw2LRezQ(byte arg0) {
        return m1228constructorimpl((byte) (arg0 + 1));
    }

    @InlineOnly
    /* renamed from: dec-w2LRezQ */
    private static final byte m1208decw2LRezQ(byte arg0) {
        return m1228constructorimpl((byte) (arg0 - 1));
    }

    @InlineOnly
    /* renamed from: rangeTo-7apg3OU */
    private static final UIntRange m1209rangeTo7apg3OU(byte arg0, byte other) {
        return new UIntRange(UInt.m1308constructorimpl(arg0 & 255), UInt.m1308constructorimpl(other & 255), null);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: rangeUntil-7apg3OU */
    private static final UIntRange m1210rangeUntil7apg3OU(byte arg0, byte other) {
        return URangesKt.m2509untilJ1ME1BU(UInt.m1308constructorimpl(arg0 & 255), UInt.m1308constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: and-7apg3OU */
    private static final byte m1211and7apg3OU(byte arg0, byte other) {
        return m1228constructorimpl((byte) (arg0 & other));
    }

    @InlineOnly
    /* renamed from: or-7apg3OU */
    private static final byte m1212or7apg3OU(byte arg0, byte other) {
        return m1228constructorimpl((byte) (arg0 | other));
    }

    @InlineOnly
    /* renamed from: xor-7apg3OU */
    private static final byte m1213xor7apg3OU(byte arg0, byte other) {
        return m1228constructorimpl((byte) (arg0 ^ other));
    }

    @InlineOnly
    /* renamed from: inv-w2LRezQ */
    private static final byte m1214invw2LRezQ(byte arg0) {
        return m1228constructorimpl((byte) (arg0 ^ (-1)));
    }

    @InlineOnly
    /* renamed from: toByte-impl */
    private static final byte m1215toByteimpl(byte arg0) {
        return arg0;
    }

    @InlineOnly
    /* renamed from: toShort-impl */
    private static final short m1216toShortimpl(byte arg0) {
        return (short) (arg0 & 255);
    }

    @InlineOnly
    /* renamed from: toInt-impl */
    private static final int m1217toIntimpl(byte arg0) {
        return arg0 & 255;
    }

    @InlineOnly
    /* renamed from: toLong-impl */
    private static final long m1218toLongimpl(byte arg0) {
        return arg0 & 255;
    }

    @InlineOnly
    /* renamed from: toUByte-w2LRezQ */
    private static final byte m1219toUBytew2LRezQ(byte arg0) {
        return arg0;
    }

    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg */
    private static final short m1220toUShortMh2AYeg(byte arg0) {
        return UShort.m1495constructorimpl((short) (arg0 & 255));
    }

    @InlineOnly
    /* renamed from: toUInt-pVg5ArA */
    private static final int m1221toUIntpVg5ArA(byte arg0) {
        return UInt.m1308constructorimpl(arg0 & 255);
    }

    @InlineOnly
    /* renamed from: toULong-s-VKNKU */
    private static final long m1222toULongsVKNKU(byte arg0) {
        return ULong.m1388constructorimpl(arg0 & 255);
    }

    @InlineOnly
    /* renamed from: toFloat-impl */
    private static final float m1223toFloatimpl(byte arg0) {
        return (float) UnsignedKt.uintToDouble(arg0 & 255);
    }

    @InlineOnly
    /* renamed from: toDouble-impl */
    private static final double m1224toDoubleimpl(byte arg0) {
        return UnsignedKt.uintToDouble(arg0 & 255);
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m1225toStringimpl(byte arg0) {
        return String.valueOf(arg0 & 255);
    }

    @NotNull
    public String toString() {
        return m1225toStringimpl(this.data);
    }
}
