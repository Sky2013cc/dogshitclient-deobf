package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JobSupport.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\"\n��\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0010\u0010��\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0001H��\u001a\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0001H��\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��\"\u0010\u0010\u0005\u001a\u00020\u00048��X\u0081\u0004¢\u0006\u0002\n��\"\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n��\"\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u000b\u001a\u00020\tX\u0082T¢\u0006\u0002\n��\"\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\u0010\u001a\u00020\tX\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0011\u001a\u00020\tX\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0012\u001a\u00020\tX\u0082T¢\u0006\u0002\n��¨\u0006\u0013"}, d2 = {"boxIncomplete", "", "unboxState", "COMPLETING_ALREADY", "Lkotlinx/coroutines/internal/Symbol;", "COMPLETING_WAITING_CHILDREN", "COMPLETING_RETRY", "TOO_LATE_TO_CANCEL", "RETRY", "", "FALSE", "TRUE", "SEALED", "EMPTY_NEW", "Lkotlinx/coroutines/Empty;", "EMPTY_ACTIVE", "LIST_ON_COMPLETION_PERMISSION", "LIST_CHILD_PERMISSION", "LIST_CANCELLATION_PERMISSION", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/JobSupportKt.class */
public final class JobSupportKt {
    private static final int RETRY = -1;
    private static final int FALSE = 0;
    private static final int TRUE = 1;
    private static final int LIST_ON_COMPLETION_PERMISSION = 1;
    private static final int LIST_CHILD_PERMISSION = 2;
    private static final int LIST_CANCELLATION_PERMISSION = 4;

    @NotNull
    private static final Symbol COMPLETING_ALREADY = new Symbol("COMPLETING_ALREADY");

    @JvmField
    @NotNull
    public static final Symbol COMPLETING_WAITING_CHILDREN = new Symbol("COMPLETING_WAITING_CHILDREN");

    @NotNull
    private static final Symbol COMPLETING_RETRY = new Symbol("COMPLETING_RETRY");

    @NotNull
    private static final Symbol TOO_LATE_TO_CANCEL = new Symbol("TOO_LATE_TO_CANCEL");

    @NotNull
    private static final Symbol SEALED = new Symbol("SEALED");

    @NotNull
    private static final Empty EMPTY_NEW = new Empty(false);

    @NotNull
    private static final Empty EMPTY_ACTIVE = new Empty(true);

    @Nullable
    public static final Object boxIncomplete(@Nullable Object $this$boxIncomplete) {
        return $this$boxIncomplete instanceof Incomplete ? new IncompleteStateBox((Incomplete) $this$boxIncomplete) : $this$boxIncomplete;
    }

    @Nullable
    public static final Object unboxState(@Nullable Object $this$unboxState) {
        IncompleteStateBox incompleteStateBox = $this$unboxState instanceof IncompleteStateBox ? (IncompleteStateBox) $this$unboxState : null;
        if (incompleteStateBox != null) {
            Incomplete incomplete = incompleteStateBox.state;
            if (incomplete != null) {
                return incomplete;
            }
        }
        return $this$unboxState;
    }
}
