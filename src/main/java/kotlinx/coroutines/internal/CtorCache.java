package kotlinx.coroutines.internal;

import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: ExceptionsConstructor.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b \u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005j\u0002`\u00072\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\tH&¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/internal/CtorCache;", "", Constants.CTOR, "()V", PropertyDescriptor.GET, "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/Ctor;", "key", Constants.CLASS, "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/CtorCache.class */
public abstract class CtorCache {
    @NotNull
    public abstract Function1<Throwable, Throwable> get(@NotNull Class<? extends Throwable> cls);
}
