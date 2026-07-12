package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Transform.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
@DebugMetadata(f = "Transform.kt", l = {105, 106}, i = {0}, s = {"L$0"}, n = {"this"}, m = "emit", c = "kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$1$1")
/* loaded from: target.jar:kotlinx/coroutines/flow/FlowKt__TransformKt$runningFold$1$1$emit$1.class */
public final class FlowKt__TransformKt$runningFold$1$1$emit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__TransformKt$runningFold$1$1<T> this$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__TransformKt$runningFold$1$1$emit$1(FlowKt__TransformKt$runningFold$1$1<? super T> flowKt__TransformKt$runningFold$1$1, Continuation<? super FlowKt__TransformKt$runningFold$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = flowKt__TransformKt$runningFold$1$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
