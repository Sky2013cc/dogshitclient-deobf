package net.ccbluex.liquidbounce.event.async;

import java.util.function.BooleanSupplier;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CancellableContinuation;

/* compiled from: General.kt */
@Metadata(mv = {2, 0, 0}, k = 3, xi = 176)
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/async/GeneralKt$waitUntil$2$1.class */
public final class GeneralKt$waitUntil$2$1 implements BooleanSupplier {
    final /* synthetic */ Ref.IntRef $waitingTick;
    final /* synthetic */ Function0<Boolean> $condition;
    final /* synthetic */ CancellableContinuation<Integer> $cont;

    /* JADX WARN: Multi-variable type inference failed */
    public GeneralKt$waitUntil$2$1(Ref.IntRef $waitingTick, Function0<Boolean> function0, CancellableContinuation<? super Integer> cancellableContinuation) {
        this.$waitingTick = $waitingTick;
        this.$condition = function0;
        this.$cont = cancellableContinuation;
    }

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        boolean z;
        boolean z2;
        this.$waitingTick.element++;
        try {
            if (this.$condition.invoke().booleanValue()) {
                CancellableContinuation<Integer> cancellableContinuation = this.$cont;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m1167constructorimpl(Integer.valueOf(this.$waitingTick.element)));
                z2 = true;
            } else {
                z2 = false;
            }
            z = z2;
        } catch (Throwable e) {
            CancellableContinuation<Integer> cancellableContinuation2 = this.$cont;
            Result.Companion companion2 = Result.Companion;
            cancellableContinuation2.resumeWith(Result.m1167constructorimpl(ResultKt.createFailure(e)));
            z = true;
        }
        return z;
    }
}
