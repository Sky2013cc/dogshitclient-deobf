package kotlin;

import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: AssertionsJVM.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bÁ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u00020\u00058��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0006\u0010\u0003¨\u0006\u0007"}, d2 = {"Lkotlin/_Assertions;", "", Constants.CTOR, "()V", "ENABLED", "", "getENABLED$annotations", "kotlin-stdlib"})
@PublishedApi
/* loaded from: target.jar:kotlin/_Assertions.class */
public final class _Assertions {

    @NotNull
    public static final _Assertions INSTANCE = new _Assertions();

    @JvmField
    public static final boolean ENABLED = INSTANCE.getClass().desiredAssertionStatus();

    @PublishedApi
    public static /* synthetic */ void getENABLED$annotations() {
    }

    private _Assertions() {
    }
}
