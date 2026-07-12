package net.ccbluex.liquidbounce.utils.timing;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: WaitTickUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 3, xi = 176)
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/timing/WaitTickUtils$schedule$2.class */
public final class WaitTickUtils$schedule$2 implements Function1<Integer, Boolean> {
    final /* synthetic */ Function0<Unit> $action;

    public WaitTickUtils$schedule$2(Function0<Unit> function0) {
        this.$action = function0;
    }

    public final Boolean invoke(int it) {
        this.$action.invoke();
        return null;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(Integer num) {
        return invoke(num.intValue());
    }
}
