package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlinx.coroutines.internal.MainDispatchersKt;
import kotlinx.coroutines.internal.SystemPropsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: DefaultExecutor.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\b\u0010\b\u001a\u00020\u0003H\u0002\"\u000e\u0010��\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��\"\u001c\u0010\u0002\u001a\u00020\u00038��X\u0081\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"defaultMainDelayOptIn", "", "DefaultDelay", "Lkotlinx/coroutines/Delay;", "getDefaultDelay$annotations", "()V", "getDefaultDelay", "()Lkotlinx/coroutines/Delay;", "initializeDefaultDelay", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/DefaultExecutorKt.class */
public final class DefaultExecutorKt {
    private static final boolean defaultMainDelayOptIn = SystemPropsKt.systemProp("kotlinx.coroutines.main.delay", false);

    @NotNull
    private static final Delay DefaultDelay = initializeDefaultDelay();

    @PublishedApi
    public static /* synthetic */ void getDefaultDelay$annotations() {
    }

    @NotNull
    public static final Delay getDefaultDelay() {
        return DefaultDelay;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final Delay initializeDefaultDelay() {
        if (!defaultMainDelayOptIn) {
            return DefaultExecutor.INSTANCE;
        }
        MainCoroutineDispatcher main = Dispatchers.getMain();
        return (MainDispatchersKt.isMissing(main) || !(main instanceof Delay)) ? DefaultExecutor.INSTANCE : (Delay) main;
    }
}
