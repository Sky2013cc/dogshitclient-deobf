package kotlin;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Result;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: Result.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��6\n��\n\u0002\u0010��\n��\n\u0002\u0010\u0003\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a\u0010\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a\u0015\u0010\u0004\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030\u0006H\u0001¢\u0006\u0002\u0010\u0007\u001a+\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b��\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH\u0087\bø\u0001��¢\u0006\u0002\u0010\f\u001a@\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b��\u0010\r\"\u0004\b\u0001\u0010\t*\u0002H\r2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\t0\u000e¢\u0006\u0002\b\u000fH\u0087\bø\u0001��¢\u0006\u0002\u0010\u0010\u001a\u001e\u0010\u0011\u001a\u0002H\r\"\u0004\b��\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u0006H\u0087\b¢\u0006\u0002\u0010\u0012\u001a[\u0010\u0013\u001a\u0002H\t\"\u0004\b��\u0010\t\"\b\b\u0001\u0010\r*\u0002H\t*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\bø\u0001��\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 ��¢\u0006\u0002\u0010\u0010\u001a0\u0010\u0017\u001a\u0002H\t\"\u0004\b��\u0010\t\"\b\b\u0001\u0010\r*\u0002H\t*\b\u0012\u0004\u0012\u0002H\r0\u00062\u0006\u0010\u0018\u001a\u0002H\tH\u0087\b¢\u0006\u0002\u0010\u0019\u001a\u0084\u0001\u0010\u001a\u001a\u0002H\t\"\u0004\b��\u0010\t\"\u0004\b\u0001\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u0002H\t0\u000e2!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\bø\u0001��\u0082\u0002\u0014\n\b\b\u0001\u0012\u0002\u0010\u0001 ��\n\b\b\u0001\u0012\u0002\u0010\u0002 ��¢\u0006\u0002\u0010\u001d\u001a]\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b��\u0010\t\"\u0004\b\u0001\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\bø\u0001��\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 ��¢\u0006\u0002\u0010\u0010\u001aP\u0010 \u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b��\u0010\t\"\u0004\b\u0001\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\bø\u0001��¢\u0006\u0002\u0010\u0010\u001aa\u0010!\u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b��\u0010\t\"\b\b\u0001\u0010\r*\u0002H\t*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\bø\u0001��\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 ��¢\u0006\u0002\u0010\u0010\u001aT\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\t0\u0006\"\u0004\b��\u0010\t\"\b\b\u0001\u0010\r*\u0002H\t*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\t0\u000eH\u0087\bø\u0001��¢\u0006\u0002\u0010\u0010\u001aW\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\r0\u0006\"\u0004\b��\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010#\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u00050\u000eH\u0087\bø\u0001��\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 ��¢\u0006\u0002\u0010\u0010\u001aW\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\r0\u0006\"\u0004\b��\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00062!\u0010#\u001a\u001d\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00050\u000eH\u0087\bø\u0001��\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 ��¢\u0006\u0002\u0010\u0010\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006$"}, d2 = {"createFailure", "", "exception", "", "throwOnFailure", "", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "runCatching", "R", Constants.ATTR_BLOCK, "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrThrow", "(Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "onFailure", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "fold", "onSuccess", "value", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "map", "transform", "mapCatching", "recover", "recoverCatching", "action", "kotlin-stdlib"})
@SourceDebugExtension({"SMAP\nResult.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Result.kt\nkotlin/ResultKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,340:1\n1#2:341\n*E\n"})
/* loaded from: target.jar:kotlin/ResultKt.class */
public final class ResultKt {
    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final Object createFailure(@NotNull Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new Result.Failure(exception);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static final void throwOnFailure(@NotNull Object $this$throwOnFailure) {
        if ($this$throwOnFailure instanceof Result.Failure) {
            throw ((Result.Failure) $this$throwOnFailure).exception;
        }
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R> Object runCatching(Function0<? extends R> block) {
        Object m1167constructorimpl;
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            Result.Companion companion = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(block.invoke());
        } catch (Throwable e) {
            Result.Companion companion2 = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(createFailure(e));
        }
        return m1167constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T, R> Object runCatching(T t, Function1<? super T, ? extends R> block) {
        Object m1167constructorimpl;
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            Result.Companion companion = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(block.invoke(t));
        } catch (Throwable e) {
            Result.Companion companion2 = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(createFailure(e));
        }
        return m1167constructorimpl;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> T getOrThrow(Object obj) {
        throwOnFailure(obj);
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> R getOrElse(Object obj, Function1<? super Throwable, ? extends R> onFailure) {
        Intrinsics.checkNotNullParameter(onFailure, "onFailure");
        Throwable exception = Result.m1163exceptionOrNullimpl(obj);
        return exception == null ? obj : onFailure.invoke(exception);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> R getOrDefault(Object obj, R r) {
        return Result.m1161isFailureimpl(obj) ? r : obj;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T> R fold(Object $this$fold, Function1<? super T, ? extends R> onSuccess, Function1<? super Throwable, ? extends R> onFailure) {
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onFailure, "onFailure");
        Throwable exception = Result.m1163exceptionOrNullimpl($this$fold);
        return exception == null ? onSuccess.invoke($this$fold) : onFailure.invoke(exception);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T> Object map(Object $this$map, Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        if (!Result.m1160isSuccessimpl($this$map)) {
            return Result.m1167constructorimpl($this$map);
        }
        Result.Companion companion = Result.Companion;
        return Result.m1167constructorimpl(transform.invoke($this$map));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T> Object mapCatching(Object $this$mapCatching, Function1<? super T, ? extends R> transform) {
        Object m1167constructorimpl;
        Intrinsics.checkNotNullParameter(transform, "transform");
        if (Result.m1160isSuccessimpl($this$mapCatching)) {
            try {
                Result.Companion companion = Result.Companion;
                m1167constructorimpl = Result.m1167constructorimpl(transform.invoke($this$mapCatching));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                m1167constructorimpl = Result.m1167constructorimpl(createFailure(th));
            }
            return m1167constructorimpl;
        }
        return Result.m1167constructorimpl($this$mapCatching);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> Object recover(Object $this$recover, Function1<? super Throwable, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        Throwable exception = Result.m1163exceptionOrNullimpl($this$recover);
        if (exception == null) {
            return $this$recover;
        }
        Result.Companion companion = Result.Companion;
        return Result.m1167constructorimpl(transform.invoke(exception));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <R, T extends R> Object recoverCatching(Object $this$recoverCatching, Function1<? super Throwable, ? extends R> transform) {
        Object m1167constructorimpl;
        Intrinsics.checkNotNullParameter(transform, "transform");
        Throwable exception = Result.m1163exceptionOrNullimpl($this$recoverCatching);
        if (exception == null) {
            return $this$recoverCatching;
        }
        try {
            Result.Companion companion = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(transform.invoke(exception));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(createFailure(th));
        }
        return m1167constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> Object onFailure(Object $this$onFailure, Function1<? super Throwable, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        Throwable it = Result.m1163exceptionOrNullimpl($this$onFailure);
        if (it != null) {
            action.invoke(it);
        }
        return $this$onFailure;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> Object onSuccess(Object $this$onSuccess, Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        if (Result.m1160isSuccessimpl($this$onSuccess)) {
            action.invoke($this$onSuccess);
        }
        return $this$onSuccess;
    }
}
