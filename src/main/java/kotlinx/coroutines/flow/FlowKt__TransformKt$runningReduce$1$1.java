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
/* compiled from: Transform.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
/* loaded from: target.jar:kotlinx/coroutines/flow/FlowKt__TransformKt$runningReduce$1$1.class */
public final class FlowKt__TransformKt$runningReduce$1$1<T> implements FlowCollector {
    final /* synthetic */ Ref.ObjectRef<Object> $accumulator;
    final /* synthetic */ Function3<T, T, Continuation<? super T>, Object> $operation;
    final /* synthetic */ FlowCollector<T> $this_flow;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__TransformKt$runningReduce$1$1(Ref.ObjectRef<Object> objectRef, Function3<? super T, ? super T, ? super Continuation<? super T>, ? extends Object> function3, FlowCollector<? super T> flowCollector) {
        this.$accumulator = objectRef;
        this.$operation = function3;
        this.$this_flow = flowCollector;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0043. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x005c  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t, Continuation<? super Unit> continuation) {
        FlowKt__TransformKt$runningReduce$1$1$emit$1 flowKt__TransformKt$runningReduce$1$1$emit$1;
        Ref.ObjectRef<Object> objectRef;
        T t2;
        Ref.ObjectRef<Object> objectRef2;
        T t3;
        if (continuation instanceof FlowKt__TransformKt$runningReduce$1$1$emit$1) {
            flowKt__TransformKt$runningReduce$1$1$emit$1 = (FlowKt__TransformKt$runningReduce$1$1$emit$1) continuation;
            if ((flowKt__TransformKt$runningReduce$1$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                flowKt__TransformKt$runningReduce$1$1$emit$1.label -= Integer.MIN_VALUE;
                Object obj = flowKt__TransformKt$runningReduce$1$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (flowKt__TransformKt$runningReduce$1$1$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        objectRef2 = this.$accumulator;
                        if (this.$accumulator.element == NullSurrogateKt.NULL) {
                            t3 = t;
                            objectRef2.element = t3;
                            flowKt__TransformKt$runningReduce$1$1$emit$1.L$0 = null;
                            flowKt__TransformKt$runningReduce$1$1$emit$1.L$1 = null;
                            flowKt__TransformKt$runningReduce$1$1$emit$1.label = 2;
                            if (this.$this_flow.emit(this.$accumulator.element, flowKt__TransformKt$runningReduce$1$1$emit$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        }
                        objectRef = objectRef2;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.L$0 = this;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.L$1 = objectRef;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.label = 1;
                        Object invoke = this.$operation.invoke(this.$accumulator.element, t, flowKt__TransformKt$runningReduce$1$1$emit$1);
                        t2 = invoke;
                        if (invoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        T t4 = t2;
                        objectRef2 = objectRef;
                        t3 = t4;
                        objectRef2.element = t3;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.L$0 = null;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.L$1 = null;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.label = 2;
                        if (this.$this_flow.emit(this.$accumulator.element, flowKt__TransformKt$runningReduce$1$1$emit$1) == coroutine_suspended) {
                        }
                        return Unit.INSTANCE;
                    case 1:
                        objectRef = (Ref.ObjectRef) flowKt__TransformKt$runningReduce$1$1$emit$1.L$1;
                        this = (FlowKt__TransformKt$runningReduce$1$1) flowKt__TransformKt$runningReduce$1$1$emit$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        t2 = obj;
                        T t42 = t2;
                        objectRef2 = objectRef;
                        t3 = t42;
                        objectRef2.element = t3;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.L$0 = null;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.L$1 = null;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.label = 2;
                        if (this.$this_flow.emit(this.$accumulator.element, flowKt__TransformKt$runningReduce$1$1$emit$1) == coroutine_suspended) {
                        }
                        return Unit.INSTANCE;
                    case 2:
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        flowKt__TransformKt$runningReduce$1$1$emit$1 = new FlowKt__TransformKt$runningReduce$1$1$emit$1(this, continuation);
        Object obj2 = flowKt__TransformKt$runningReduce$1$1$emit$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (flowKt__TransformKt$runningReduce$1$1$emit$1.label) {
        }
    }
}
