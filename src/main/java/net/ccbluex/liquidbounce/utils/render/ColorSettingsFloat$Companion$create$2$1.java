package net.ccbluex.liquidbounce.utils.render;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: ColorSettings.kt */
@Metadata(mv = {2, 0, 0}, k = 3, xi = 176)
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/ColorSettingsFloat$Companion$create$2$1.class */
public final class ColorSettingsFloat$Companion$create$2$1 implements Function0<Boolean> {
    final /* synthetic */ Function1<Integer, Boolean> $generalApply;
    final /* synthetic */ int $it;

    /* JADX WARN: Multi-variable type inference failed */
    public ColorSettingsFloat$Companion$create$2$1(Function1<? super Integer, Boolean> function1, int $it) {
        this.$generalApply = function1;
        this.$it = $it;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        return this.$generalApply.invoke(Integer.valueOf(this.$it));
    }
}
