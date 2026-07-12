package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: CancellableContinuationImpl.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��N\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0082\b\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u0084\u0001\u0012\u0006\u0010\u0003\u001a\u00028��\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012O\b\u0002\u0010\u0006\u001aI\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118��¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0019\u001a\u00020\u000f2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001b2\u0006\u0010\u000b\u001a\u00020\bJ\u000e\u0010\u001c\u001a\u00028��HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003JP\u0010\u001f\u001aI\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118��¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0007HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0093\u0001\u0010\"\u001a\b\u0012\u0004\u0012\u00028��0��2\b\b\u0002\u0010\u0003\u001a\u00028��2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052O\b\u0002\u0010\u0006\u001aI\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118��¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020\u00162\b\u0010%\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020)HÖ\u0001R\u0012\u0010\u0003\u001a\u00028��8\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0002\n��RW\u0010\u0006\u001aI\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118��¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n��R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0002\n��R\u0012\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u0002\n��R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006*"}, d2 = {"Lkotlinx/coroutines/CompletedContinuation;", "R", "", "result", "cancelHandler", "Lkotlinx/coroutines/CancelHandler;", "onCancellation", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "cause", "value", "Lkotlin/coroutines/CoroutineContext;", "context", "", "idempotentResume", "cancelCause", Constants.CTOR, "(Ljava/lang/Object;Lkotlinx/coroutines/CancelHandler;Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Throwable;)V", Constants.OBJECT, "cancelled", "", "getCancelled", "()Z", "invokeHandlers", "cont", "Lkotlinx/coroutines/CancellableContinuationImpl;", "component1", "()Ljava/lang/Object;", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Object;Lkotlinx/coroutines/CancelHandler;Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Throwable;)Lkotlinx/coroutines/CompletedContinuation;", "equals", "other", "hashCode", "", "toString", "", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nCancellableContinuationImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CancellableContinuationImpl.kt\nkotlinx/coroutines/CompletedContinuation\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,701:1\n1#2:702\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/CompletedContinuation.class */
final class CompletedContinuation<R> {

    @JvmField
    public final R result;

    @JvmField
    @Nullable
    public final CancelHandler cancelHandler;

    @JvmField
    @Nullable
    public final Function3<Throwable, R, CoroutineContext, Unit> onCancellation;

    @JvmField
    @Nullable
    public final Object idempotentResume;

    @JvmField
    @Nullable
    public final Throwable cancelCause;

    public final R component1() {
        return this.result;
    }

    @Nullable
    public final CancelHandler component2() {
        return this.cancelHandler;
    }

    @Nullable
    public final Function3<Throwable, R, CoroutineContext, Unit> component3() {
        return this.onCancellation;
    }

    @Nullable
    public final Object component4() {
        return this.idempotentResume;
    }

    @Nullable
    public final Throwable component5() {
        return this.cancelCause;
    }

    @NotNull
    public final CompletedContinuation<R> copy(R r, @Nullable CancelHandler cancelHandler, @Nullable Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> function3, @Nullable Object idempotentResume, @Nullable Throwable cancelCause) {
        return new CompletedContinuation<>(r, cancelHandler, function3, idempotentResume, cancelCause);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CompletedContinuation copy$default(CompletedContinuation completedContinuation, Object obj, CancelHandler cancelHandler, Function3 function3, Object obj2, Throwable th, int i, Object obj3) {
        R r = obj;
        if ((i & 1) != 0) {
            r = completedContinuation.result;
        }
        if ((i & 2) != 0) {
            cancelHandler = completedContinuation.cancelHandler;
        }
        if ((i & 4) != 0) {
            function3 = completedContinuation.onCancellation;
        }
        if ((i & 8) != 0) {
            obj2 = completedContinuation.idempotentResume;
        }
        if ((i & 16) != 0) {
            th = completedContinuation.cancelCause;
        }
        return completedContinuation.copy(r, cancelHandler, function3, obj2, th);
    }

    @NotNull
    public String toString() {
        return "CompletedContinuation(result=" + this.result + ", cancelHandler=" + this.cancelHandler + ", onCancellation=" + this.onCancellation + ", idempotentResume=" + this.idempotentResume + ", cancelCause=" + this.cancelCause + ')';
    }

    public int hashCode() {
        int result = this.result == null ? 0 : this.result.hashCode();
        return (((((((result * 31) + (this.cancelHandler == null ? 0 : this.cancelHandler.hashCode())) * 31) + (this.onCancellation == null ? 0 : this.onCancellation.hashCode())) * 31) + (this.idempotentResume == null ? 0 : this.idempotentResume.hashCode())) * 31) + (this.cancelCause == null ? 0 : this.cancelCause.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CompletedContinuation)) {
            return false;
        }
        CompletedContinuation completedContinuation = (CompletedContinuation) other;
        return Intrinsics.areEqual(this.result, completedContinuation.result) && Intrinsics.areEqual(this.cancelHandler, completedContinuation.cancelHandler) && Intrinsics.areEqual(this.onCancellation, completedContinuation.onCancellation) && Intrinsics.areEqual(this.idempotentResume, completedContinuation.idempotentResume) && Intrinsics.areEqual(this.cancelCause, completedContinuation.cancelCause);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CompletedContinuation(R r, @Nullable CancelHandler cancelHandler, @Nullable Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> function3, @Nullable Object idempotentResume, @Nullable Throwable cancelCause) {
        this.result = r;
        this.cancelHandler = cancelHandler;
        this.onCancellation = function3;
        this.idempotentResume = idempotentResume;
        this.cancelCause = cancelCause;
    }

    public /* synthetic */ CompletedContinuation(Object obj, CancelHandler cancelHandler, Function3 function3, Object obj2, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? null : cancelHandler, (i & 4) != 0 ? null : function3, (i & 8) != 0 ? null : obj2, (i & 16) != 0 ? null : th);
    }

    public final boolean getCancelled() {
        return this.cancelCause != null;
    }

    public final void invokeHandlers(@NotNull CancellableContinuationImpl<?> cancellableContinuationImpl, @NotNull Throwable cause) {
        CancelHandler it = this.cancelHandler;
        if (it != null) {
            cancellableContinuationImpl.callCancelHandler(it, cause);
        }
        Function3 it2 = this.onCancellation;
        if (it2 != null) {
            cancellableContinuationImpl.callOnCancellation(it2, cause, this.result);
        }
    }
}
