package kotlinx.coroutines.internal;

import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: ExceptionsConstructor.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\t0\nj\u0002`\u000b2\u000e\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R4\u0010\u0006\u001a(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\t0\nj\u0002`\u000b0\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/internal/WeakMapCtorCache;", "Lkotlinx/coroutines/internal/CtorCache;", Constants.CTOR, "()V", "cacheLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "exceptionCtors", "Ljava/util/WeakHashMap;", Constants.CLASS, "", "Lkotlin/Function1;", "Lkotlinx/coroutines/internal/Ctor;", PropertyDescriptor.GET, "key", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nExceptionsConstructor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExceptionsConstructor.kt\nkotlinx/coroutines/internal/WeakMapCtorCache\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,112:1\n1#2:113\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/internal/WeakMapCtorCache.class */
final class WeakMapCtorCache extends CtorCache {

    @NotNull
    public static final WeakMapCtorCache INSTANCE = new WeakMapCtorCache();

    @NotNull
    private static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();

    @NotNull
    private static final WeakHashMap<Class<? extends Throwable>, Function1<Throwable, Throwable>> exceptionCtors = new WeakHashMap<>();

    private WeakMapCtorCache() {
    }

    /* JADX WARN: Finally extract failed */
    @Override // kotlinx.coroutines.internal.CtorCache
    @NotNull
    public Function1<Throwable, Throwable> get(@NotNull Class<? extends Throwable> cls) {
        Function1 it;
        ReentrantReadWriteLock.ReadLock readLock = cacheLock.readLock();
        readLock.lock();
        try {
            Function1 it2 = exceptionCtors.get(cls);
            if (it2 != null) {
                return it2;
            }
            readLock.unlock();
            ReentrantReadWriteLock reentrantReadWriteLock = cacheLock;
            ReentrantReadWriteLock.ReadLock readLock2 = reentrantReadWriteLock.readLock();
            int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
            for (int i = 0; i < readHoldCount; i++) {
                readLock2.unlock();
            }
            ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
            writeLock.lock();
            try {
                Function1 it3 = exceptionCtors.get(cls);
                if (it3 != null) {
                    for (int i2 = 0; i2 < readHoldCount; i2++) {
                        readLock2.lock();
                    }
                    writeLock.unlock();
                    return it3;
                }
                it = ExceptionsConstructorKt.createConstructor(cls);
                exceptionCtors.put(cls, it);
                for (int i3 = 0; i3 < readHoldCount; i3++) {
                    readLock2.lock();
                }
                writeLock.unlock();
                return it;
            } catch (Throwable th) {
                for (int i4 = 0; i4 < readHoldCount; i4++) {
                    readLock2.lock();
                }
                writeLock.unlock();
                throw th;
            }
        } finally {
            readLock.unlock();
        }
    }
}
