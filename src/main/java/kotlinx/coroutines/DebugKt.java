package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.internal.SystemPropsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: Debug.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��(\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u001a\b\u0010\u0014\u001a\u00020\u0015H��\u001a\u0017\u0010\u0016\u001a\u00020\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018H\u0081\b\"\u000e\u0010��\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n��\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n��\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n��\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n��\"\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\t\"\u0014\u0010\n\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\t\"\u001c\u0010\f\u001a\u00020\u00078��X\u0081\u0004¢\u0006\u000e\n��\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\t\"\u0014\u0010\u0010\u001a\u00020\u0011X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0019"}, d2 = {"DEBUG_PROPERTY_NAME", "", "STACKTRACE_RECOVERY_PROPERTY_NAME", "DEBUG_PROPERTY_VALUE_AUTO", "DEBUG_PROPERTY_VALUE_ON", "DEBUG_PROPERTY_VALUE_OFF", "ASSERTIONS_ENABLED", "", "getASSERTIONS_ENABLED", "()Z", "DEBUG", "getDEBUG", "RECOVER_STACK_TRACES", "getRECOVER_STACK_TRACES$annotations", "()V", "getRECOVER_STACK_TRACES", "COROUTINE_ID", "Ljava/util/concurrent/atomic/AtomicLong;", "getCOROUTINE_ID", "()Ljava/util/concurrent/atomic/AtomicLong;", "resetCoroutineId", "", "assert", "value", "Lkotlin/Function0;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/DebugKt.class */
public final class DebugKt {

    @NotNull
    public static final String DEBUG_PROPERTY_NAME = "kotlinx.coroutines.debug";

    @NotNull
    public static final String STACKTRACE_RECOVERY_PROPERTY_NAME = "kotlinx.coroutines.stacktrace.recovery";

    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_AUTO = "auto";

    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_ON = "on";

    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_OFF = "off";
    private static final boolean ASSERTIONS_ENABLED = CoroutineId.class.desiredAssertionStatus();
    private static final boolean DEBUG;
    private static final boolean RECOVER_STACK_TRACES;

    @NotNull
    private static final AtomicLong COROUTINE_ID;

    @PublishedApi
    public static /* synthetic */ void getRECOVER_STACK_TRACES$annotations() {
    }

    public static final boolean getASSERTIONS_ENABLED() {
        return ASSERTIONS_ENABLED;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0056, code lost:
    
        if (r0.equals("auto") == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006e, code lost:
    
        if (r0.equals("on") == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x004a, code lost:
    
        if (r0.equals("") == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x007a, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001a. Please report as an issue. */
    static {
        boolean z;
        String value = SystemPropsKt.systemProp(DEBUG_PROPERTY_NAME);
        if (value != null) {
            switch (value.hashCode()) {
                case 0:
                    break;
                case 3551:
                    break;
                case 109935:
                    if (value.equals("off")) {
                        z = false;
                        DEBUG = z;
                        RECOVER_STACK_TRACES = !DEBUG && SystemPropsKt.systemProp(STACKTRACE_RECOVERY_PROPERTY_NAME, true);
                        COROUTINE_ID = new AtomicLong(0L);
                    }
                    throw new IllegalStateException(("System property 'kotlinx.coroutines.debug' has unrecognized value '" + value + '\'').toString());
                case 3005871:
                    break;
                default:
                    throw new IllegalStateException(("System property 'kotlinx.coroutines.debug' has unrecognized value '" + value + '\'').toString());
            }
        }
        z = ASSERTIONS_ENABLED;
        DEBUG = z;
        RECOVER_STACK_TRACES = !DEBUG && SystemPropsKt.systemProp(STACKTRACE_RECOVERY_PROPERTY_NAME, true);
        COROUTINE_ID = new AtomicLong(0L);
    }

    public static final boolean getDEBUG() {
        return DEBUG;
    }

    public static final boolean getRECOVER_STACK_TRACES() {
        return RECOVER_STACK_TRACES;
    }

    @NotNull
    public static final AtomicLong getCOROUTINE_ID() {
        return COROUTINE_ID;
    }

    public static final void resetCoroutineId() {
        COROUTINE_ID.set(0L);
    }

    @InlineOnly
    /* renamed from: assert, reason: not valid java name */
    private static final void m2772assert(Function0<Boolean> function0) {
        if (getASSERTIONS_ENABLED() && !function0.invoke().booleanValue()) {
            throw new AssertionError();
        }
    }
}
