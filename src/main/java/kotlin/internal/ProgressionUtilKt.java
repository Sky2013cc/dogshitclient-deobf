package kotlin.internal;

import com.sun.tools.doclets.internal.toolkit.util.VisibleMemberMap;
import jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants;
import kotlin.Metadata;
import kotlin.PublishedApi;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* compiled from: progressionUtil.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\u001a\u0018\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0018\u0010��\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a \u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a \u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u001a \u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0001\u001a \u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0001¨\u0006\u000b"}, d2 = {"mod", "", "a", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "", "differenceModulo", "c", "getProgressionLastElement", VisibleMemberMap.STARTLEVEL, AsmConstants.END, "step", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/internal/ProgressionUtilKt.class */
public final class ProgressionUtilKt {
    private static final int mod(int a, int b) {
        int mod = a % b;
        return mod >= 0 ? mod : mod + b;
    }

    private static final long mod(long a, long b) {
        long mod = a % b;
        return mod >= 0 ? mod : mod + b;
    }

    private static final int differenceModulo(int a, int b, int c) {
        return mod(mod(a, c) - mod(b, c), c);
    }

    private static final long differenceModulo(long a, long b, long c) {
        return mod(mod(a, c) - mod(b, c), c);
    }

    @PublishedApi
    public static final int getProgressionLastElement(int start, int end, int step) {
        if (step > 0) {
            return start >= end ? end : end - differenceModulo(end, start, step);
        }
        if (step < 0) {
            return start <= end ? end : end + differenceModulo(start, end, -step);
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    @PublishedApi
    public static final long getProgressionLastElement(long start, long end, long step) {
        if (step > 0) {
            return start >= end ? end : end - differenceModulo(end, start, step);
        }
        if (step < 0) {
            return start <= end ? end : end + differenceModulo(start, end, -step);
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
