package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadLocalKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: EventLoop.common.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\f\u001a\u0004\u0018\u00010\u0006H��¢\u0006\u0002\b\rJ\r\u0010\u000e\u001a\u00020\u000fH��¢\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u0006H��¢\u0006\u0002\b\u0012R$\u0010\u0004\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0006`\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0014\u0010\t\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/ThreadLocalEventLoop;", "", Constants.CTOR, "()V", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_REF, "Ljava/lang/ThreadLocal;", "Lkotlinx/coroutines/EventLoop;", "Lkotlinx/coroutines/internal/CommonThreadLocal;", "Ljava/lang/ThreadLocal;", "eventLoop", "getEventLoop$kotlinx_coroutines_core", "()Lkotlinx/coroutines/EventLoop;", "currentOrNull", "currentOrNull$kotlinx_coroutines_core", "resetEventLoop", "", "resetEventLoop$kotlinx_coroutines_core", "setEventLoop", "setEventLoop$kotlinx_coroutines_core", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nEventLoop.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/ThreadLocalEventLoop\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,547:1\n1#2:548\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/ThreadLocalEventLoop.class */
public final class ThreadLocalEventLoop {

    @NotNull
    public static final ThreadLocalEventLoop INSTANCE = new ThreadLocalEventLoop();

    @NotNull
    private static final ThreadLocal<EventLoop> ref = ThreadLocalKt.commonThreadLocal(new Symbol("ThreadLocalEventLoop"));

    private ThreadLocalEventLoop() {
    }

    @NotNull
    public final EventLoop getEventLoop$kotlinx_coroutines_core() {
        EventLoop eventLoop = ref.get();
        if (eventLoop != null) {
            return eventLoop;
        }
        EventLoop it = EventLoopKt.createEventLoop();
        ref.set(it);
        return it;
    }

    @Nullable
    public final EventLoop currentOrNull$kotlinx_coroutines_core() {
        return ref.get();
    }

    public final void resetEventLoop$kotlinx_coroutines_core() {
        ref.set(null);
    }

    public final void setEventLoop$kotlinx_coroutines_core(@NotNull EventLoop eventLoop) {
        ref.set(eventLoop);
    }
}
