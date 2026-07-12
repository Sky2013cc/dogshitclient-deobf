package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;

/* compiled from: Reduce.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 176)
/* loaded from: target.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$fold$2.class */
public final class FlowKt__ReduceKt$fold$2<T> implements FlowCollector {
    final /* synthetic */ Ref.ObjectRef<R> $accumulator;
    final /* synthetic */ Function3<R, T, Continuation<? super R>, Object> $operation;

    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__ReduceKt$fold$2(Ref.ObjectRef<R> objectRef, Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        this.$accumulator = objectRef;
        this.$operation = function3;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0058  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t, Continuation<? super Unit> continuation) {
        FlowKt__ReduceKt$fold$2$emit$1 flowKt__ReduceKt$fold$2$emit$1;
        Ref.ObjectRef objectRef;
        Object obj;
        if (continuation instanceof FlowKt__ReduceKt$fold$2$emit$1) {
            flowKt__ReduceKt$fold$2$emit$1 = (FlowKt__ReduceKt$fold$2$emit$1) continuation;
            if ((flowKt__ReduceKt$fold$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$fold$2$emit$1.label -= Integer.MIN_VALUE;
                Object obj2 = flowKt__ReduceKt$fold$2$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (flowKt__ReduceKt$fold$2$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj2);
                        objectRef = this.$accumulator;
                        flowKt__ReduceKt$fold$2$emit$1.L$0 = objectRef;
                        flowKt__ReduceKt$fold$2$emit$1.label = 1;
                        obj = this.$operation.invoke(this.$accumulator.element, t, flowKt__ReduceKt$fold$2$emit$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        objectRef = (Ref.ObjectRef) flowKt__ReduceKt$fold$2$emit$1.L$0;
                        ResultKt.throwOnFailure(obj2);
                        obj = obj2;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef.element = (T) obj;
                return Unit.INSTANCE;
            }
        }
        flowKt__ReduceKt$fold$2$emit$1 = new FlowKt__ReduceKt$fold$2$emit$1(this, continuation);
        Object obj22 = flowKt__ReduceKt$fold$2$emit$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (flowKt__ReduceKt$fold$2$emit$1.label) {
        }
        objectRef.element = (T) obj;
        return Unit.INSTANCE;
    }

    public final Object emit$$forInline(T t, Continuation<? super Unit> continuation) {
        InlineMarker.mark(4);
        new FlowKt__ReduceKt$fold$2$emit$1(this, continuation);
        InlineMarker.mark(5);
        this.$accumulator.element = (T) this.$operation.invoke(this.$accumulator.element, t, continuation);
        return Unit.INSTANCE;
    }
}
