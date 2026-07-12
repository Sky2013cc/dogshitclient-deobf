package kotlinx.coroutines.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: ExceptionsConstructor.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��(\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a!\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\b\b��\u0010\b*\u00020\u00042\u0006\u0010\t\u001a\u0002H\bH��¢\u0006\u0002\u0010\n\u001a2\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003j\u0002`\f\"\b\b��\u0010\b*\u00020\u00042\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\b0\u000eH\u0002\u001a.\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003j\u0002`\f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u0002\u001a\u0018\u0010\u0011\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\u001a\u001b\u0010\u0013\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u0001H\u0082\u0010\"\u000e\u0010��\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��*(\b\u0002\u0010\u0002\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¨\u0006\u0015"}, d2 = {"throwableFields", "", "Ctor", "Lkotlin/Function1;", "", "ctorCache", "Lkotlinx/coroutines/internal/CtorCache;", "tryCopyException", "E", "exception", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "createConstructor", "Lkotlinx/coroutines/internal/Ctor;", "clz", Constants.CLASS, "safeCtor", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_BLOCK, "fieldsCountOrDefault", "defaultValue", "fieldsCount", "accumulator", "kotlinx-coroutines-core"})
@SourceDebugExtension({"SMAP\nExceptionsConstructor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExceptionsConstructor.kt\nkotlinx/coroutines/internal/ExceptionsConstructorKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,112:1\n1#2:113\n11158#3:114\n11493#3,3:115\n12727#3,3:132\n1971#4,14:118\n*S KotlinDebug\n*F\n+ 1 ExceptionsConstructor.kt\nkotlinx/coroutines/internal/ExceptionsConstructorKt\n*L\n41#1:114\n41#1:115,3\n78#1:132,3\n59#1:118,14\n*E\n"})
/* loaded from: target.jar:kotlinx/coroutines/internal/ExceptionsConstructorKt.class */
public final class ExceptionsConstructorKt {
    private static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);

    @NotNull
    private static final CtorCache ctorCache;

    public static final /* synthetic */ Function1 access$createConstructor(Class clz) {
        return createConstructor(clz);
    }

    static {
        CtorCache ctorCache2;
        try {
            ctorCache2 = FastServiceLoaderKt.getANDROID_DETECTED() ? WeakMapCtorCache.INSTANCE : ClassValueCtorCache.INSTANCE;
        } catch (Throwable th) {
            ctorCache2 = WeakMapCtorCache.INSTANCE;
        }
        ctorCache = ctorCache2;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:4:0x0007
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:275)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:68)
        */
    @org.jetbrains.annotations.Nullable
    public static final <E extends java.lang.Throwable> E tryCopyException(@org.jetbrains.annotations.NotNull E r3) {
        /*
            r0 = r3
            boolean r0 = r0 instanceof kotlinx.coroutines.CopyableThrowable
            if (r0 == 0) goto L3d
        L8:
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch: java.lang.Throwable -> L1e
            r0 = 0
            r4 = r0
            r0 = r3
            kotlinx.coroutines.CopyableThrowable r0 = (kotlinx.coroutines.CopyableThrowable) r0     // Catch: java.lang.Throwable -> L1e
            java.lang.Throwable r0 = r0.createCopy()     // Catch: java.lang.Throwable -> L1e
            java.lang.Object r0 = kotlin.Result.m1167constructorimpl(r0)     // Catch: java.lang.Throwable -> L1e
            r4 = r0
            goto L2b
        L1e:
            r5 = move-exception
            kotlin.Result$Companion r0 = kotlin.Result.Companion
            r0 = r5
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m1167constructorimpl(r0)
            r4 = r0
        L2b:
            r0 = r4
            r4 = r0
            r0 = r4
            boolean r0 = kotlin.Result.m1161isFailureimpl(r0)
            if (r0 == 0) goto L38
            r0 = 0
            goto L39
        L38:
            r0 = r4
        L39:
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            return r0
        L3d:
            kotlinx.coroutines.internal.CtorCache r0 = kotlinx.coroutines.internal.ExceptionsConstructorKt.ctorCache
            r1 = r3
            java.lang.Class r1 = r1.getClass()
            kotlin.jvm.functions.Function1 r0 = r0.get(r1)
            r1 = r3
            java.lang.Object r0 = r0.invoke(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ExceptionsConstructorKt.tryCopyException(java.lang.Throwable):java.lang.Throwable");
    }

    public static final <E extends Throwable> Function1<Throwable, Throwable> createConstructor(Class<E> cls) {
        Object obj;
        Function1<Throwable, Throwable> function1;
        Pair pair;
        Function1 nullResult = new Function1() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$nullResult$1
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(Throwable it) {
                return null;
            }
        };
        if (throwableFields != fieldsCountOrDefault(cls, 0)) {
            return nullResult;
        }
        Constructor<?>[] constructors = cls.getConstructors();
        Collection destination$iv$iv = new ArrayList(constructors.length);
        for (Constructor<?> constructor : constructors) {
            Class[] p = constructor.getParameterTypes();
            switch (p.length) {
                case 0:
                    pair = TuplesKt.to(safeCtor((v1) -> {
                        return createConstructor$lambda$7$lambda$6(r0, v1);
                    }), 0);
                    break;
                case 1:
                    Class cls2 = p[0];
                    if (Intrinsics.areEqual(cls2, String.class)) {
                        pair = TuplesKt.to(safeCtor((v1) -> {
                            return createConstructor$lambda$7$lambda$3(r0, v1);
                        }), 2);
                        break;
                    } else if (Intrinsics.areEqual(cls2, Throwable.class)) {
                        pair = TuplesKt.to(safeCtor((v1) -> {
                            return createConstructor$lambda$7$lambda$4(r0, v1);
                        }), 1);
                        break;
                    } else {
                        pair = TuplesKt.to(null, -1);
                        break;
                    }
                case 2:
                    if (!Intrinsics.areEqual(p[0], String.class) || !Intrinsics.areEqual(p[1], Throwable.class)) {
                        pair = TuplesKt.to(null, -1);
                        break;
                    } else {
                        pair = TuplesKt.to(safeCtor((v1) -> {
                            return createConstructor$lambda$7$lambda$1(r0, v1);
                        }), 3);
                        break;
                    }
                default:
                    pair = TuplesKt.to(null, -1);
                    break;
            }
            destination$iv$iv.add(pair);
        }
        Iterable $this$maxByOrNull$iv = (List) destination$iv$iv;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            Object maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                Pair p0 = (Pair) maxElem$iv;
                int maxValue$iv = ((Number) p0.getSecond()).intValue();
                do {
                    Object e$iv = iterator$iv.next();
                    Pair p02 = (Pair) e$iv;
                    int v$iv = ((Number) p02.getSecond()).intValue();
                    if (maxValue$iv < v$iv) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
                obj = maxElem$iv;
            } else {
                obj = maxElem$iv;
            }
        } else {
            obj = null;
        }
        Pair pair2 = (Pair) obj;
        return (pair2 == null || (function1 = (Function1) pair2.getFirst()) == null) ? nullResult : function1;
    }

    private static final Throwable createConstructor$lambda$7$lambda$1(Constructor $constructor, Throwable e) {
        Object newInstance = $constructor.newInstance(e.getMessage(), e);
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
        return (Throwable) newInstance;
    }

    private static final Throwable createConstructor$lambda$7$lambda$3(Constructor $constructor, Throwable e) {
        Object newInstance = $constructor.newInstance(e.getMessage());
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
        Throwable it = (Throwable) newInstance;
        it.initCause(e);
        return it;
    }

    private static final Throwable createConstructor$lambda$7$lambda$4(Constructor $constructor, Throwable e) {
        Object newInstance = $constructor.newInstance(e);
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
        return (Throwable) newInstance;
    }

    private static final Throwable createConstructor$lambda$7$lambda$6(Constructor $constructor, Throwable e) {
        Object newInstance = $constructor.newInstance(new Object[0]);
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
        Throwable it = (Throwable) newInstance;
        it.initCause(e);
        return it;
    }

    private static final Function1<Throwable, Throwable> safeCtor(Function1<? super Throwable, ? extends Throwable> function1) {
        return (v1) -> {
            return safeCtor$lambda$9(r0, v1);
        };
    }

    private static final Throwable safeCtor$lambda$9(Function1 $block, Throwable e) {
        Object m1167constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            Throwable result = (Throwable) $block.invoke(e);
            m1167constructorimpl = Result.m1167constructorimpl((Intrinsics.areEqual(e.getMessage(), result.getMessage()) || Intrinsics.areEqual(result.getMessage(), e.toString())) ? result : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = m1167constructorimpl;
        return (Throwable) (Result.m1161isFailureimpl(obj) ? null : obj);
    }

    private static final int fieldsCountOrDefault(Class<?> cls, int defaultValue) {
        Object m1167constructorimpl;
        JvmClassMappingKt.getKotlinClass(cls);
        try {
            Result.Companion companion = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(Integer.valueOf(fieldsCount$default(cls, 0, 1, null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m1167constructorimpl = Result.m1167constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = m1167constructorimpl;
        return ((Number) (Result.m1161isFailureimpl(obj) ? Integer.valueOf(defaultValue) : obj)).intValue();
    }

    static /* synthetic */ int fieldsCount$default(Class cls, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return fieldsCount(cls, i);
    }

    private static final int fieldsCount(Class<?> cls, int accumulator) {
        while (true) {
            int count$iv = 0;
            for (Field field : cls.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    count$iv++;
                }
            }
            int fieldsCount = count$iv;
            int totalFields = accumulator + fieldsCount;
            Class<? super Object> superclass = cls.getSuperclass();
            if (superclass == null) {
                return totalFields;
            }
            cls = superclass;
            accumulator = totalFields;
        }
    }
}
