package kotlin.reflect;

import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.tools.java.RuntimeConstants;

/* compiled from: KProperty.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018��*\u0006\b��\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u000eJ\r\u0010\u0004\u001a\u00028��H&¢\u0006\u0002\u0010\u0005J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H'R\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0\tX¦\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lkotlin/reflect/KProperty0;", RuntimeConstants.SIG_VOID, "Lkotlin/reflect/KProperty;", "Lkotlin/Function0;", PropertyDescriptor.GET, "()Ljava/lang/Object;", "getDelegate", "", "getter", "Lkotlin/reflect/KProperty0$Getter;", "getGetter$annotations", "()V", "getGetter", "()Lkotlin/reflect/KProperty0$Getter;", "Getter", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/reflect/KProperty0.class */
public interface KProperty0<V> extends KProperty<V>, Function0<V> {

    /* compiled from: KProperty.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlin/reflect/KProperty0$DefaultImpls.class */
    public static final class DefaultImpls {
        public static /* synthetic */ void getGetter$annotations() {
        }
    }

    /* compiled from: KProperty.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0010\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\bf\u0018��*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/reflect/KProperty0$Getter;", RuntimeConstants.SIG_VOID, "Lkotlin/reflect/KProperty$Getter;", "Lkotlin/Function0;", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/reflect/KProperty0$Getter.class */
    public interface Getter<V> extends KProperty.Getter<V>, Function0<V> {
    }

    V get();

    @SinceKotlin(version = "1.1")
    @Nullable
    Object getDelegate();

    @NotNull
    Getter<V> getGetter();
}
