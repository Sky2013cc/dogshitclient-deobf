package kotlin.jdk7;

import com.sun.tools.internal.ws.wsdl.document.jaxws.JAXWSBindingsConstants;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.Nullable;

/* compiled from: AutoCloseableJVM.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��.\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n��\u001a \u0010��\u001a\u00060\u0001j\u0002`\u00052\u000e\b\u0004\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0087\bø\u0001��\u001aN\u0010\t\u001a\u0002H\n\"\u0010\b��\u0010\u000b*\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0005\"\u0004\b\u0001\u0010\n*\u0002H\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\n0\rH\u0087\bø\u0001��\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u000e\u001a\u001e\u0010\u000f\u001a\u00020\b*\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0001*\u001a\b\u0007\u0010��\"\u00020\u00012\u00020\u0001B\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"AutoCloseable", "Ljava/lang/AutoCloseable;", "Lkotlin/SinceKotlin;", "version", JAXWSBindingsConstants.JAXB_BINDING_VERSION, "Lkotlin/AutoCloseable;", "closeAction", "Lkotlin/Function0;", "", Constants.ATTR_USE, "R", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, Constants.ATTR_BLOCK, "Lkotlin/Function1;", "(Ljava/lang/AutoCloseable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "closeFinally", "cause", "", "kotlin-stdlib"}, pn = "kotlin")
@JvmName(name = "AutoCloseableKt")
/* loaded from: target.jar:kotlin/jdk7/AutoCloseableKt.class */
public final class AutoCloseableKt {
    @SinceKotlin(version = JAXWSBindingsConstants.JAXB_BINDING_VERSION)
    public static /* synthetic */ void AutoCloseable$annotations() {
    }

    @SinceKotlin(version = JAXWSBindingsConstants.JAXB_BINDING_VERSION)
    @InlineOnly
    private static final AutoCloseable AutoCloseable(final Function0<Unit> closeAction) {
        Intrinsics.checkNotNullParameter(closeAction, "closeAction");
        return new AutoCloseable() { // from class: kotlin.jdk7.AutoCloseableKt$AutoCloseable$1
            @Override // java.lang.AutoCloseable
            public final void close() {
                closeAction.invoke();
            }
        };
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <T extends AutoCloseable, R> R use(T t, Function1<? super T, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        Throwable exception = null;
        try {
            try {
                R invoke = block.invoke(t);
                InlineMarker.finallyStart(1);
                closeFinally(t, null);
                InlineMarker.finallyEnd(1);
                return invoke;
            } finally {
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            closeFinally(t, exception);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final void closeFinally(@Nullable AutoCloseable $this$closeFinally, @Nullable Throwable cause) {
        if ($this$closeFinally != null) {
            if (cause == null) {
                $this$closeFinally.close();
                return;
            }
            try {
                $this$closeFinally.close();
            } catch (Throwable closeException) {
                ExceptionsKt.addSuppressed(cause, closeException);
            }
        }
    }
}
