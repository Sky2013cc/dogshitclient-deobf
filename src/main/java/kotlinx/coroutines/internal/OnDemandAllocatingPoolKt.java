package kotlinx.coroutines.internal;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: OnDemandAllocatingPool.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u0018\n��\n\u0002\u0010\u0001\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010\b\n��\u001a\u0017\u0010��\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082\b\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n��¨\u0006\u0007"}, d2 = {"loop", "", Constants.ATTR_BLOCK, "Lkotlin/Function0;", "", "IS_CLOSED_MASK", "", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/OnDemandAllocatingPoolKt.class */
public final class OnDemandAllocatingPoolKt {
    private static final int IS_CLOSED_MASK = Integer.MIN_VALUE;

    private static final Void loop(Function0<Unit> function0) {
        while (true) {
            function0.invoke();
        }
    }
}
