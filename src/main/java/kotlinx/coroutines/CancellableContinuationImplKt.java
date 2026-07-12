package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

/* compiled from: CancellableContinuationImpl.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n��\u001a\u0019\u0010\f\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0082\b\"\u000e\u0010��\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u0019\u0010\u0007\u001a\u00020\u0001*\u00020\u00018Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0019\u0010\n\u001a\u00020\u0001*\u00020\u00018Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t\"\u0010\u0010\r\u001a\u00020\u000e8��X\u0081\u0004¢\u0006\u0002\n��¨\u0006\u000f"}, d2 = {"UNDECIDED", "", DebugCoroutineInfoImplKt.SUSPENDED, "RESUMED", "DECISION_SHIFT", "INDEX_MASK", "NO_INDEX", "decision", "getDecision", "(I)I", "index", "getIndex", "decisionAndIndex", "RESUME_TOKEN", "Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/CancellableContinuationImplKt.class */
public final class CancellableContinuationImplKt {
    private static final int UNDECIDED = 0;
    private static final int SUSPENDED = 1;
    private static final int RESUMED = 2;
    private static final int DECISION_SHIFT = 29;
    private static final int INDEX_MASK = 536870911;
    private static final int NO_INDEX = 536870911;

    @JvmField
    @NotNull
    public static final Symbol RESUME_TOKEN = new Symbol("RESUME_TOKEN");

    private static final int getDecision(int $this$decision) {
        return $this$decision >> 29;
    }

    private static final int getIndex(int $this$index) {
        return $this$index & 536870911;
    }

    private static final int decisionAndIndex(int decision, int index) {
        return (decision << 29) + index;
    }
}
