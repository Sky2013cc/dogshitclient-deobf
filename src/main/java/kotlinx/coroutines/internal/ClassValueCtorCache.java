package kotlinx.coroutines.internal;

import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: ExceptionsConstructor.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��'\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��*\u0001\u0005\bÃ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\t0\bj\u0002`\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\fH\u0016R\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/internal/ClassValueCtorCache;", "Lkotlinx/coroutines/internal/CtorCache;", Constants.CTOR, "()V", "cache", "kotlinx/coroutines/internal/ClassValueCtorCache$cache$1", "Lkotlinx/coroutines/internal/ClassValueCtorCache$cache$1;", PropertyDescriptor.GET, "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/Ctor;", "key", Constants.CLASS, "kotlinx-coroutines-core"})
@IgnoreJRERequirement
/* loaded from: target.jar:kotlinx/coroutines/internal/ClassValueCtorCache.class */
final class ClassValueCtorCache extends CtorCache {

    @NotNull
    public static final ClassValueCtorCache INSTANCE = new ClassValueCtorCache();

    @NotNull
    private static final ClassValueCtorCache$cache$1 cache = new ClassValue<Function1<? super Throwable, ? extends Throwable>>() { // from class: kotlinx.coroutines.internal.ClassValueCtorCache$cache$1
        @Override // java.lang.ClassValue
        /* renamed from: computeValue, reason: avoid collision after fix types in other method */
        public /* bridge */ /* synthetic */ Function1<? super Throwable, ? extends Throwable> computeValue2(Class p0) {
            return computeValue((Class<?>) p0);
        }

        @Override // java.lang.ClassValue
        protected Function1<? super Throwable, ? extends Throwable> computeValue(Class<?> cls) {
            Intrinsics.checkNotNull(cls, "null cannot be cast to non-null type java.lang.Class<out kotlin.Throwable>");
            return ExceptionsConstructorKt.access$createConstructor(cls);
        }
    };

    private ClassValueCtorCache() {
    }

    @Override // kotlinx.coroutines.internal.CtorCache
    @NotNull
    public Function1<Throwable, Throwable> get(@NotNull Class<? extends Throwable> cls) {
        return (Function1) cache.get(cls);
    }
}
