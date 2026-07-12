package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Distinct.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
/* loaded from: target.jar:kotlinx/coroutines/flow/DistinctFlowImpl$collect$2.class */
public final class DistinctFlowImpl$collect$2<T> implements FlowCollector {
    final /* synthetic */ DistinctFlowImpl<T> this$0;
    final /* synthetic */ Ref.ObjectRef<Object> $previousKey;
    final /* synthetic */ FlowCollector<T> $collector;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public DistinctFlowImpl$collect$2(DistinctFlowImpl<T> distinctFlowImpl, Ref.ObjectRef<Object> objectRef, FlowCollector<? super T> flowCollector) {
        this.this$0 = distinctFlowImpl;
        this.$previousKey = objectRef;
        this.$collector = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0058  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t, Continuation<? super Unit> continuation) {
        DistinctFlowImpl$collect$2$emit$1 distinctFlowImpl$collect$2$emit$1;
        if (continuation instanceof DistinctFlowImpl$collect$2$emit$1) {
            distinctFlowImpl$collect$2$emit$1 = (DistinctFlowImpl$collect$2$emit$1) continuation;
            if ((distinctFlowImpl$collect$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                distinctFlowImpl$collect$2$emit$1.label -= Integer.MIN_VALUE;
                Object obj = distinctFlowImpl$collect$2$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (distinctFlowImpl$collect$2$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        T t2 = (T) this.this$0.keySelector.invoke(t);
                        if (this.$previousKey.element != NullSurrogateKt.NULL && this.this$0.areEquivalent.invoke(this.$previousKey.element, t2).booleanValue()) {
                            return Unit.INSTANCE;
                        }
                        this.$previousKey.element = t2;
                        distinctFlowImpl$collect$2$emit$1.label = 1;
                        if (this.$collector.emit(t, distinctFlowImpl$collect$2$emit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure(obj);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Unit.INSTANCE;
            }
        }
        distinctFlowImpl$collect$2$emit$1 = new DistinctFlowImpl$collect$2$emit$1(this, continuation);
        Object obj2 = distinctFlowImpl$collect$2$emit$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (distinctFlowImpl$collect$2$emit$1.label) {
        }
        return Unit.INSTANCE;
    }
}
