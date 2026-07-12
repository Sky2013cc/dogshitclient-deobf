package kotlin.internal;

import com.sun.tools.doclets.internal.toolkit.util.VisibleMemberMap;
import jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* compiled from: UProgressionUtil.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"�� \n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n��\n\u0002\u0010\t\n\u0002\b\u0002\u001a'\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010��\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u000f\u0010\u0006\u001a'\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0011\u0010\t¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", VisibleMemberMap.STARTLEVEL, AsmConstants.END, "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/internal/UProgressionUtilKt.class */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    private static final int m2406differenceModuloWZ9TVnA(int a, int b, int c) {
        int ac = Integer.remainderUnsigned(a, c);
        int bc = Integer.remainderUnsigned(b, c);
        return Integer.compareUnsigned(ac, bc) >= 0 ? UInt.m1308constructorimpl(ac - bc) : UInt.m1308constructorimpl(UInt.m1308constructorimpl(ac - bc) + c);
    }

    /* renamed from: differenceModulo-sambcqE, reason: not valid java name */
    private static final long m2407differenceModulosambcqE(long a, long b, long c) {
        long ac = Long.remainderUnsigned(a, c);
        long bc = Long.remainderUnsigned(b, c);
        return Long.compareUnsigned(ac, bc) >= 0 ? ULong.m1388constructorimpl(ac - bc) : ULong.m1388constructorimpl(ULong.m1388constructorimpl(ac - bc) + c);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m2408getProgressionLastElementNkh28Cs(int start, int end, int step) {
        if (step > 0) {
            return Integer.compareUnsigned(start, end) >= 0 ? end : UInt.m1308constructorimpl(end - m2406differenceModuloWZ9TVnA(end, start, UInt.m1308constructorimpl(step)));
        }
        if (step < 0) {
            return Integer.compareUnsigned(start, end) <= 0 ? end : UInt.m1308constructorimpl(end + m2406differenceModuloWZ9TVnA(start, end, UInt.m1308constructorimpl(-step)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m2409getProgressionLastElement7ftBX0g(long start, long end, long step) {
        if (step > 0) {
            return Long.compareUnsigned(start, end) >= 0 ? end : ULong.m1388constructorimpl(end - m2407differenceModulosambcqE(end, start, ULong.m1388constructorimpl(step)));
        }
        if (step < 0) {
            return Long.compareUnsigned(start, end) <= 0 ? end : ULong.m1388constructorimpl(end + m2407differenceModulosambcqE(start, end, ULong.m1388constructorimpl(-step)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
