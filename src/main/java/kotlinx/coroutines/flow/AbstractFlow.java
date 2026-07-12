package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.flow.internal.SafeCollector;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Flow.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0\tH\u0086@¢\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0\tH¦@¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/flow/AbstractFlow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/CancellableFlow;", Constants.CTOR, "()V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectSafely", "kotlinx-coroutines-core"})
@ExperimentalCoroutinesApi
/* loaded from: target.jar:kotlinx/coroutines/flow/AbstractFlow.class */
public abstract class AbstractFlow<T> implements Flow<T>, CancellableFlow<T> {
    @Nullable
    public abstract Object collectSafely(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation);

    /* JADX WARN: Removed duplicated region for block: B:21:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0058  */
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        AbstractFlow$collect$1 abstractFlow$collect$1;
        SafeCollector safeCollector;
        try {
            if (continuation instanceof AbstractFlow$collect$1) {
                abstractFlow$collect$1 = (AbstractFlow$collect$1) continuation;
                if ((abstractFlow$collect$1.label & Integer.MIN_VALUE) != 0) {
                    abstractFlow$collect$1.label -= Integer.MIN_VALUE;
                    Object $result = abstractFlow$collect$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (abstractFlow$collect$1.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            safeCollector = new SafeCollector(flowCollector, abstractFlow$collect$1.getContext());
                            abstractFlow$collect$1.L$0 = safeCollector;
                            abstractFlow$collect$1.label = 1;
                            if (collectSafely(safeCollector, abstractFlow$collect$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case 1:
                            safeCollector = (SafeCollector) abstractFlow$collect$1.L$0;
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    safeCollector.releaseIntercepted();
                    return Unit.INSTANCE;
                }
            }
            switch (abstractFlow$collect$1.label) {
            }
            safeCollector.releaseIntercepted();
            return Unit.INSTANCE;
        } finally {
            safeCollector.releaseIntercepted();
        }
        abstractFlow$collect$1 = new AbstractFlow$collect$1(this, continuation);
        Object $result2 = abstractFlow$collect$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }
}
