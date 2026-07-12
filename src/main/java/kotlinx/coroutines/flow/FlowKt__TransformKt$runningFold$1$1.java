package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Transform.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
/* loaded from: target.jar:kotlinx/coroutines/flow/FlowKt__TransformKt$runningFold$1$1.class */
public final class FlowKt__TransformKt$runningFold$1$1<T> implements FlowCollector {
    final /* synthetic */ Ref.ObjectRef<R> $accumulator;
    final /* synthetic */ Function3<R, T, Continuation<? super R>, Object> $operation;
    final /* synthetic */ FlowCollector<R> $this_flow;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__TransformKt$runningFold$1$1(Ref.ObjectRef<R> objectRef, Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3, FlowCollector<? super R> flowCollector) {
        this.$accumulator = objectRef;
        this.$operation = function3;
        this.$this_flow = flowCollector;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0043. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x005c  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t, Continuation<? super Unit> continuation) {
        FlowKt__TransformKt$runningFold$1$1$emit$1 flowKt__TransformKt$runningFold$1$1$emit$1;
        Ref.ObjectRef objectRef;
        Object obj;
        if (continuation instanceof FlowKt__TransformKt$runningFold$1$1$emit$1) {
            flowKt__TransformKt$runningFold$1$1$emit$1 = (FlowKt__TransformKt$runningFold$1$1$emit$1) continuation;
            if ((flowKt__TransformKt$runningFold$1$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                flowKt__TransformKt$runningFold$1$1$emit$1.label -= Integer.MIN_VALUE;
                Object obj2 = flowKt__TransformKt$runningFold$1$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (flowKt__TransformKt$runningFold$1$1$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj2);
                        objectRef = this.$accumulator;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$0 = this;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$1 = objectRef;
                        flowKt__TransformKt$runningFold$1$1$emit$1.label = 1;
                        obj = this.$operation.invoke(this.$accumulator.element, t, flowKt__TransformKt$runningFold$1$1$emit$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        objectRef.element = (T) obj;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$0 = null;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$1 = null;
                        flowKt__TransformKt$runningFold$1$1$emit$1.label = 2;
                        if (this.$this_flow.emit(this.$accumulator.element, flowKt__TransformKt$runningFold$1$1$emit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    case 1:
                        objectRef = (Ref.ObjectRef) flowKt__TransformKt$runningFold$1$1$emit$1.L$1;
                        this = (FlowKt__TransformKt$runningFold$1$1) flowKt__TransformKt$runningFold$1$1$emit$1.L$0;
                        ResultKt.throwOnFailure(obj2);
                        obj = obj2;
                        objectRef.element = (T) obj;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$0 = null;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$1 = null;
                        flowKt__TransformKt$runningFold$1$1$emit$1.label = 2;
                        if (this.$this_flow.emit(this.$accumulator.element, flowKt__TransformKt$runningFold$1$1$emit$1) == coroutine_suspended) {
                        }
                        return Unit.INSTANCE;
                    case 2:
                        ResultKt.throwOnFailure(obj2);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        flowKt__TransformKt$runningFold$1$1$emit$1 = new FlowKt__TransformKt$runningFold$1$1$emit$1(this, continuation);
        Object obj22 = flowKt__TransformKt$runningFold$1$1$emit$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (flowKt__TransformKt$runningFold$1$1$emit$1.label) {
        }
    }
}
