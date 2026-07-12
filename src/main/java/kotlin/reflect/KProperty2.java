package kotlin.reflect;

import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.tools.java.RuntimeConstants;

/* compiled from: KProperty.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0006\b\u0002\u0010\u0003 \u00012\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005:\u0001\u0012J\u001d\u0010\u0006\u001a\u00028\u00022\u0006\u0010\u0007\u001a\u00028��2\u0006\u0010\b\u001a\u00028\u0001H&¢\u0006\u0002\u0010\tJ\u001f\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00028��2\u0006\u0010\b\u001a\u00028\u0001H'¢\u0006\u0002\u0010\tR*\u0010\f\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\rX¦\u0004¢\u0006\f\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lkotlin/reflect/KProperty2;", "D", "E", RuntimeConstants.SIG_VOID, "Lkotlin/reflect/KProperty;", "Lkotlin/Function2;", PropertyDescriptor.GET, "receiver1", "receiver2", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getDelegate", "", "getter", "Lkotlin/reflect/KProperty2$Getter;", "getGetter$annotations", "()V", "getGetter", "()Lkotlin/reflect/KProperty2$Getter;", "Getter", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/reflect/KProperty2.class */
public interface KProperty2<D, E, V> extends KProperty<V>, Function2<D, E, V> {

    /* compiled from: KProperty.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlin/reflect/KProperty2$DefaultImpls.class */
    public static final class DefaultImpls {
        public static /* synthetic */ void getGetter$annotations() {
        }
    }

    /* compiled from: KProperty.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\bf\u0018��*\u0004\b\u0003\u0010\u0001*\u0004\b\u0004\u0010\u0002*\u0006\b\u0005\u0010\u0003 \u00012\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/reflect/KProperty2$Getter;", "D", "E", RuntimeConstants.SIG_VOID, "Lkotlin/reflect/KProperty$Getter;", "Lkotlin/Function2;", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/reflect/KProperty2$Getter.class */
    public interface Getter<D, E, V> extends KProperty.Getter<V>, Function2<D, E, V> {
    }

    V get(D d, E e);

    @SinceKotlin(version = "1.1")
    @Nullable
    Object getDelegate(D d, E e);

    @Override // kotlin.reflect.KProperty, kotlin.reflect.KProperty0
    @NotNull
    Getter<D, E, V> getGetter();
}
