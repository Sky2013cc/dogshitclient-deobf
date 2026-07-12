package kotlin.io;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.Nullable;

/* compiled from: Closeable.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0003\n��\u001aH\u0010��\u001a\u0002H\u0001\"\n\b��\u0010\u0002*\u0004\u0018\u00010\u0003\"\u0004\b\u0001\u0010\u0001*\u0002H\u00022\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0005H\u0087\bø\u0001��\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0006\u001a\u0018\u0010\u0007\u001a\u00020\b*\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000b"}, d2 = {Constants.ATTR_USE, "R", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Ljava/io/Closeable;", Constants.ATTR_BLOCK, "Lkotlin/Function1;", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "closeFinally", "", "cause", "", "kotlin-stdlib"})
@JvmName(name = "CloseableKt")
/* loaded from: target.jar:kotlin/io/CloseableKt.class */
public final class CloseableKt {
    @InlineOnly
    private static final <T extends Closeable, R> R use(T t, Function1<? super T, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        Throwable exception = null;
        try {
            try {
                R invoke = block.invoke(t);
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    closeFinally(t, null);
                } else if (t != null) {
                    t.close();
                }
                InlineMarker.finallyEnd(1);
                return invoke;
            } finally {
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                closeFinally(t, exception);
            } else if (t != null) {
                if (exception == null) {
                    t.close();
                } else {
                    try {
                        t.close();
                    } catch (Throwable th2) {
                    }
                }
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    @SinceKotlin(version = "1.1")
    @PublishedApi
    public static final void closeFinally(@Nullable Closeable $this$closeFinally, @Nullable Throwable cause) {
        if ($this$closeFinally != null) {
            if (cause == null) {
                $this$closeFinally.close();
                return;
            }
            try {
                $this$closeFinally.close();
            } catch (Throwable closeException) {
                kotlin.ExceptionsKt.addSuppressed(cause, closeException);
            }
        }
    }
}
