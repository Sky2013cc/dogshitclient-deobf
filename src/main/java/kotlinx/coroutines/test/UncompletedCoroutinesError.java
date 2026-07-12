package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: TestScope.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0001\u0018��2\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/test/UncompletedCoroutinesError;", "Ljava/lang/AssertionError;", "Lkotlin/AssertionError;", "message", "", Constants.CTOR, "(Ljava/lang/String;)V", "kotlinx-coroutines-test"})
@ExperimentalCoroutinesApi
/* loaded from: target.jar:kotlinx/coroutines/test/UncompletedCoroutinesError.class */
public final class UncompletedCoroutinesError extends AssertionError {
    public UncompletedCoroutinesError(@NotNull String message) {
        super(message);
    }
}
