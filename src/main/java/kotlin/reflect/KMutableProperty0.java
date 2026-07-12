package kotlin.reflect;

import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KMutableProperty;
import org.jetbrains.annotations.NotNull;
import sun.tools.java.RuntimeConstants;

/* compiled from: KProperty.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u000eJ\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028��H&¢\u0006\u0002\u0010\u0007R\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0\tX¦\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lkotlin/reflect/KMutableProperty0;", RuntimeConstants.SIG_VOID, "Lkotlin/reflect/KProperty0;", "Lkotlin/reflect/KMutableProperty;", PropertyDescriptor.SET, "", "value", "(Ljava/lang/Object;)V", "setter", "Lkotlin/reflect/KMutableProperty0$Setter;", "getSetter$annotations", "()V", "getSetter", "()Lkotlin/reflect/KMutableProperty0$Setter;", "Setter", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/reflect/KMutableProperty0.class */
public interface KMutableProperty0<V> extends KProperty0<V>, KMutableProperty<V> {

    /* compiled from: KProperty.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlin/reflect/KMutableProperty0$DefaultImpls.class */
    public static final class DefaultImpls {
        public static /* synthetic */ void getSetter$annotations() {
        }
    }

    /* compiled from: KProperty.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n��\bf\u0018��*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00040\u0003¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/KMutableProperty0$Setter;", RuntimeConstants.SIG_VOID, "Lkotlin/reflect/KMutableProperty$Setter;", "Lkotlin/Function1;", "", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/reflect/KMutableProperty0$Setter.class */
    public interface Setter<V> extends KMutableProperty.Setter<V>, Function1<V, Unit> {
    }

    void set(V v);

    @NotNull
    Setter<V> getSetter();
}
