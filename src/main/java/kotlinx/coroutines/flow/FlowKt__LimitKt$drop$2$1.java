package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Limit.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
/* loaded from: target.jar:kotlinx/coroutines/flow/FlowKt__LimitKt$drop$2$1.class */
public final class FlowKt__LimitKt$drop$2$1<T> implements FlowCollector {
    final /* synthetic */ Ref.IntRef $skipped;
    final /* synthetic */ int $count;
    final /* synthetic */ FlowCollector<T> $this_flow;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__LimitKt$drop$2$1(Ref.IntRef $skipped, int $count, FlowCollector<? super T> flowCollector) {
        this.$skipped = $skipped;
        this.$count = $count;
        this.$this_flow = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0058  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t, Continuation<? super Unit> continuation) {
        FlowKt__LimitKt$drop$2$1$emit$1 flowKt__LimitKt$drop$2$1$emit$1;
        if (continuation instanceof FlowKt__LimitKt$drop$2$1$emit$1) {
            flowKt__LimitKt$drop$2$1$emit$1 = (FlowKt__LimitKt$drop$2$1$emit$1) continuation;
            if ((flowKt__LimitKt$drop$2$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                flowKt__LimitKt$drop$2$1$emit$1.label -= Integer.MIN_VALUE;
                Object $result = flowKt__LimitKt$drop$2$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (flowKt__LimitKt$drop$2$1$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        if (this.$skipped.element >= this.$count) {
                            flowKt__LimitKt$drop$2$1$emit$1.label = 1;
                            if (this.$this_flow.emit(t, flowKt__LimitKt$drop$2$1$emit$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            this.$skipped.element++;
                            int i = this.$skipped.element;
                            return Unit.INSTANCE;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Unit.INSTANCE;
            }
        }
        flowKt__LimitKt$drop$2$1$emit$1 = new FlowKt__LimitKt$drop$2$1$emit$1(this, continuation);
        Object $result2 = flowKt__LimitKt$drop$2$1$emit$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (flowKt__LimitKt$drop$2$1$emit$1.label) {
        }
        return Unit.INSTANCE;
    }
}
