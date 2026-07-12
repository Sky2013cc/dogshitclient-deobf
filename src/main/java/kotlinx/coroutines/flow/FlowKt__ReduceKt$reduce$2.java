package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Reduce.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
/* loaded from: target.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$reduce$2.class */
public final class FlowKt__ReduceKt$reduce$2<T> implements FlowCollector {
    final /* synthetic */ Ref.ObjectRef<Object> $accumulator;
    final /* synthetic */ Function3<S, T, Continuation<? super S>, Object> $operation;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__ReduceKt$reduce$2(Ref.ObjectRef<Object> objectRef, Function3<? super S, ? super T, ? super Continuation<? super S>, ? extends Object> function3) {
        this.$accumulator = objectRef;
        this.$operation = function3;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0043. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0058  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t, Continuation<? super Unit> continuation) {
        FlowKt__ReduceKt$reduce$2$emit$1 flowKt__ReduceKt$reduce$2$emit$1;
        Ref.ObjectRef<Object> objectRef;
        T t2;
        T t3;
        Ref.ObjectRef<Object> objectRef2;
        if (continuation instanceof FlowKt__ReduceKt$reduce$2$emit$1) {
            flowKt__ReduceKt$reduce$2$emit$1 = (FlowKt__ReduceKt$reduce$2$emit$1) continuation;
            if ((flowKt__ReduceKt$reduce$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$reduce$2$emit$1.label -= Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$reduce$2$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (flowKt__ReduceKt$reduce$2$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        objectRef2 = this.$accumulator;
                        if (this.$accumulator.element != NullSurrogateKt.NULL) {
                            objectRef = objectRef2;
                            flowKt__ReduceKt$reduce$2$emit$1.L$0 = objectRef;
                            flowKt__ReduceKt$reduce$2$emit$1.label = 1;
                            Object invoke = this.$operation.invoke(this.$accumulator.element, t, flowKt__ReduceKt$reduce$2$emit$1);
                            t2 = invoke;
                            if (invoke == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            T t4 = t2;
                            objectRef2 = objectRef;
                            t3 = t4;
                            objectRef2.element = t3;
                            return Unit.INSTANCE;
                        }
                        t3 = t;
                        objectRef2.element = t3;
                        return Unit.INSTANCE;
                    case 1:
                        objectRef = (Ref.ObjectRef) flowKt__ReduceKt$reduce$2$emit$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        t2 = obj;
                        T t42 = t2;
                        objectRef2 = objectRef;
                        t3 = t42;
                        objectRef2.element = t3;
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        flowKt__ReduceKt$reduce$2$emit$1 = new FlowKt__ReduceKt$reduce$2$emit$1(this, continuation);
        Object obj2 = flowKt__ReduceKt$reduce$2$emit$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (flowKt__ReduceKt$reduce$2$emit$1.label) {
        }
    }
}
