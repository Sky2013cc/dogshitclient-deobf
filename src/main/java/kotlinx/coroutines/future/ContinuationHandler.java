package kotlinx.coroutines.future;

import java.util.concurrent.CompletionException;
import java.util.function.BiFunction;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Future.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018��*\u0004\b��\u0010\u00012\u0018\u0012\u0006\u0012\u0004\u0018\u0001H\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00040\u0002B\u0017\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00018��2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\fR\u001a\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/future/ContinuationHandler;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Ljava/util/function/BiFunction;", "", "", "cont", "Lkotlin/coroutines/Continuation;", Constants.CTOR, "(Lkotlin/coroutines/Continuation;)V", "apply", "result", "exception", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/future/ContinuationHandler.class */
final class ContinuationHandler<T> implements BiFunction<T, Throwable, Unit> {

    @JvmField
    @Nullable
    public volatile Continuation<? super T> cont;

    public ContinuationHandler(@Nullable Continuation<? super T> continuation) {
        this.cont = continuation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.BiFunction
    public /* bridge */ /* synthetic */ Unit apply(Object obj, Throwable th) {
        apply2((ContinuationHandler<T>) obj, th);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        if (r0 == null) goto L17;
     */
    /* renamed from: apply, reason: avoid collision after fix types in other method */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void apply2(@Nullable T t, @Nullable Throwable exception) {
        Throwable th;
        Continuation cont = this.cont;
        if (cont == null) {
            return;
        }
        if (exception == null) {
            Result.Companion companion = Result.Companion;
            cont.resumeWith(Result.m1167constructorimpl(t));
            return;
        }
        CompletionException completionException = exception instanceof CompletionException ? (CompletionException) exception : null;
        if (completionException != null) {
            th = completionException.getCause();
        }
        th = exception;
        Result.Companion companion2 = Result.Companion;
        cont.resumeWith(Result.m1167constructorimpl(ResultKt.createFailure(th)));
    }
}
