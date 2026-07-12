package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1;

/* compiled from: Emitters.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 176)
@DebugMetadata(f = "Emitters.kt", l = {38}, i = {}, s = {}, n = {}, m = "emit", c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1$1")
/* loaded from: target.jar:kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1$1$emit$1.class */
public final class FlowKt__EmittersKt$transform$1$1$emit$1 extends ContinuationImpl {
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__EmittersKt$transform$1.AnonymousClass1<T> this$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__EmittersKt$transform$1$1$emit$1(FlowKt__EmittersKt$transform$1.AnonymousClass1<? super T> anonymousClass1, Continuation<? super FlowKt__EmittersKt$transform$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = anonymousClass1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
