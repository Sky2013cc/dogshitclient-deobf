package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LimitedDispatcher.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\u001a\f\u0010��\u001a\u00020\u0001*\u00020\u0002H��\u001a\u0016\u0010\u0003\u001a\u00020\u0004*\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H��¨\u0006\u0007"}, d2 = {"checkParallelism", "", "", "namedOrThis", "Lkotlinx/coroutines/CoroutineDispatcher;", "name", "", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nLimitedDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LimitedDispatcher.kt\nkotlinx/coroutines/internal/LimitedDispatcherKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,135:1\n1#2:136\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/internal/LimitedDispatcherKt.class */
public final class LimitedDispatcherKt {
    public static final void checkParallelism(int $this$checkParallelism) {
        if (!($this$checkParallelism >= 1)) {
            throw new IllegalArgumentException(("Expected positive parallelism level, but got " + $this$checkParallelism).toString());
        }
    }

    @NotNull
    public static final CoroutineDispatcher namedOrThis(@NotNull CoroutineDispatcher $this$namedOrThis, @Nullable String name) {
        return name != null ? new NamedDispatcher($this$namedOrThis, name) : $this$namedOrThis;
    }
}
