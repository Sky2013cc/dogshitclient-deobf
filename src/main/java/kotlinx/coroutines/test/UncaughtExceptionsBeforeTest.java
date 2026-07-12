package kotlinx.coroutines.test;

import kotlin.Metadata;
import org.spongepowered.asm.util.Constants;

/* compiled from: TestScope.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b��\u0018��2\u00060\u0001j\u0002`\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/test/UncaughtExceptionsBeforeTest;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", Constants.CTOR, "()V", "kotlinx-coroutines-test"})
/* loaded from: target.jar:kotlinx/coroutines/test/UncaughtExceptionsBeforeTest.class */
public final class UncaughtExceptionsBeforeTest extends IllegalStateException {
    public UncaughtExceptionsBeforeTest() {
        super("There were uncaught exceptions before the test started. Please avoid this, as such exceptions are also reported in a platform-dependent manner so that they are not lost.");
    }
}
