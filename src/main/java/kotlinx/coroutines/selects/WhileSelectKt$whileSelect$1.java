package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WhileSelect.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 176)
@DebugMetadata(f = "WhileSelect.kt", l = {34}, i = {0}, s = {"L$0"}, n = {"builder"}, m = "whileSelect", c = "kotlinx.coroutines.selects.WhileSelectKt")
/* loaded from: target.jar:kotlinx/coroutines/selects/WhileSelectKt$whileSelect$1.class */
public final class WhileSelectKt$whileSelect$1 extends ContinuationImpl {
    Object L$0;
    /* synthetic */ Object result;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WhileSelectKt$whileSelect$1(Continuation<? super WhileSelectKt$whileSelect$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return WhileSelectKt.whileSelect(null, this);
    }
}
