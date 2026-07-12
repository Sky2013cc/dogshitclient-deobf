package kotlinx.coroutines.internal;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OnUndeliveredElement.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aC\u0010\u0004\u001a\u0004\u0018\u00010\u0005\"\u0004\b��\u0010\u0001*\u0018\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\u00062\u0006\u0010\u0007\u001a\u0002H\u00012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005H��¢\u0006\u0002\u0010\t\u001a=\u0010\n\u001a\u00020\u0003\"\u0004\b��\u0010\u0001*\u0018\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\u00062\u0006\u0010\u0007\u001a\u0002H\u00012\u0006\u0010\u000b\u001a\u00020\fH��¢\u0006\u0002\u0010\r**\b��\u0010��\u001a\u0004\b��\u0010\u0001\"\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002¨\u0006\u000e"}, d2 = {"OnUndeliveredElement", "E", "Lkotlin/Function1;", "", "callUndeliveredElementCatchingException", "Lkotlinx/coroutines/internal/UndeliveredElementException;", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", Constants.ATTR_ELEMENT, "undeliveredElementException", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlinx/coroutines/internal/UndeliveredElementException;)Lkotlinx/coroutines/internal/UndeliveredElementException;", "callUndeliveredElement", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/OnUndeliveredElementKt.class */
public final class OnUndeliveredElementKt {
    public static /* synthetic */ UndeliveredElementException callUndeliveredElementCatchingException$default(Function1 function1, Object obj, UndeliveredElementException undeliveredElementException, int i, Object obj2) {
        if ((i & 2) != 0) {
            undeliveredElementException = null;
        }
        return callUndeliveredElementCatchingException(function1, obj, undeliveredElementException);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static final <E> UndeliveredElementException callUndeliveredElementCatchingException(@NotNull Function1<? super E, Unit> function1, E e, @Nullable UndeliveredElementException undeliveredElementException) {
        try {
            function1.invoke(e);
        } catch (Throwable ex) {
            if (undeliveredElementException != null && undeliveredElementException.getCause() != ex) {
                ExceptionsKt.addSuppressed(undeliveredElementException, ex);
            } else {
                return new UndeliveredElementException("Exception in undelivered element handler for " + e, ex);
            }
        }
        return undeliveredElementException;
    }

    public static final <E> void callUndeliveredElement(@NotNull Function1<? super E, Unit> function1, E e, @NotNull CoroutineContext context) {
        UndeliveredElementException ex = callUndeliveredElementCatchingException(function1, e, null);
        if (ex != null) {
            CoroutineExceptionHandlerKt.handleCoroutineException(context, ex);
        }
    }
}
