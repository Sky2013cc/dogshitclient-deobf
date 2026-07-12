package net.ccbluex.liquidbounce.event.async;

import java.util.function.BooleanSupplier;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuation;

/* compiled from: General.kt */
@Metadata(mv = {2, 0, 0}, k = 3, xi = 176)
@SourceDebugExtension({"SMAP\nGeneral.kt\nKotlin\n*S Kotlin\n*F\n+ 1 General.kt\nnet/ccbluex/liquidbounce/event/async/GeneralKt$waitUntil$2$1\n+ 2 General.kt\nnet/ccbluex/liquidbounce/event/async/GeneralKt\n*L\n1#1,36:1\n69#2:37\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/async/GeneralKt$waitConditional$$inlined$waitUntil$1.class */
public final class GeneralKt$waitConditional$$inlined$waitUntil$1 implements BooleanSupplier {
    final /* synthetic */ Ref.IntRef $waitingTick;
    final /* synthetic */ CancellableContinuation $cont;
    final /* synthetic */ Ref.IntRef $elapsedTicks$inlined;
    final /* synthetic */ int $ticks$inlined;
    final /* synthetic */ Function1 $callback$inlined;

    public GeneralKt$waitConditional$$inlined$waitUntil$1(Ref.IntRef $waitingTick, CancellableContinuation $cont, Ref.IntRef intRef, int i, Function1 function1) {
        this.$waitingTick = $waitingTick;
        this.$cont = $cont;
        this.$elapsedTicks$inlined = intRef;
        this.$ticks$inlined = i;
        this.$callback$inlined = function1;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0053 A[Catch: Throwable -> 0x0079, TryCatch #0 {Throwable -> 0x0079, blocks: (B:3:0x0012, B:5:0x0023, B:9:0x0053), top: B:2:0x0012 }] */
    @Override // java.util.function.BooleanSupplier
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean getAsBoolean() {
        boolean z;
        boolean z2;
        boolean z3;
        this.$waitingTick.element++;
        try {
        } catch (Throwable e) {
            CancellableContinuation cancellableContinuation = this.$cont;
            Result.Companion companion = Result.Companion;
            cancellableContinuation.resumeWith(Result.m1167constructorimpl(ResultKt.createFailure(e)));
            z = true;
        }
        if (this.$elapsedTicks$inlined.element < this.$ticks$inlined) {
            Function1 function1 = this.$callback$inlined;
            int i = this.$elapsedTicks$inlined.element;
            this.$elapsedTicks$inlined.element = i + 1;
            if (!((Boolean) function1.invoke(Integer.valueOf(i))).booleanValue()) {
                z2 = false;
                if (!z2) {
                    CancellableContinuation cancellableContinuation2 = this.$cont;
                    Result.Companion companion2 = Result.Companion;
                    cancellableContinuation2.resumeWith(Result.m1167constructorimpl(Integer.valueOf(this.$waitingTick.element)));
                    z3 = true;
                } else {
                    z3 = false;
                }
                z = z3;
                return z;
            }
        }
        z2 = true;
        if (!z2) {
        }
        z = z3;
        return z;
    }
}
