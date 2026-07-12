package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Reduce.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 176)
@DebugMetadata(f = "Reduce.kt", l = {41}, i = {}, s = {}, n = {}, m = "emit", c = "kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$2")
/* loaded from: target.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$fold$2$emit$1.class */
public final class FlowKt__ReduceKt$fold$2$emit$1 extends ContinuationImpl {
    Object L$0;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__ReduceKt$fold$2<T> this$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__ReduceKt$fold$2$emit$1(FlowKt__ReduceKt$fold$2<? super T> flowKt__ReduceKt$fold$2, Continuation<? super FlowKt__ReduceKt$fold$2$emit$1> continuation) {
        super(continuation);
        this.this$0 = flowKt__ReduceKt$fold$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
