package kotlinx.coroutines;

import java.io.Closeable;
import java.util.concurrent.Executor;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationText;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Executors.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018�� \r2\u00020\u00012\u00020\u00022\u00060\u0003j\u0002`\u0004:\u0001\rB\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH&R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Ljava/io/Closeable;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", Constants.CTOR, "()V", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "close", "", PDAnnotationText.NAME_KEY, "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/ExecutorCoroutineDispatcher.class */
public abstract class ExecutorCoroutineDispatcher extends CoroutineDispatcher implements Closeable, AutoCloseable {

    @NotNull
    public static final Key Key = new Key(null);

    @NotNull
    public abstract Executor getExecutor();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    /* compiled from: Executors.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018��2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcher$Key;", "Lkotlin/coroutines/AbstractCoroutineContextKey;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", Constants.CTOR, "()V", "kotlinx-coroutines-core"})
    @ExperimentalStdlibApi
    /* loaded from: target.jar:kotlinx/coroutines/ExecutorCoroutineDispatcher$Key.class */
    public static final class Key extends AbstractCoroutineContextKey<CoroutineDispatcher, ExecutorCoroutineDispatcher> {
        public /* synthetic */ Key(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Key() {
            super(CoroutineDispatcher.Key, Key::_init_$lambda$0);
        }

        private static final ExecutorCoroutineDispatcher _init_$lambda$0(CoroutineContext.Element it) {
            if (it instanceof ExecutorCoroutineDispatcher) {
                return (ExecutorCoroutineDispatcher) it;
            }
            return null;
        }
    }
}
