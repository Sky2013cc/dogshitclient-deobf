package kotlin.properties;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;
import sun.tools.java.RuntimeConstants;

/* compiled from: ObservableProperty.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0006\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n��\b&\u0018��*\u0004\b��\u0010\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00028��¢\u0006\u0004\b\u0005\u0010\u0006J)\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00028��2\u0006\u0010\u000e\u001a\u00028��H\u0014¢\u0006\u0002\u0010\u000fJ)\u0010\u0010\u001a\u00020\u00112\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00028��2\u0006\u0010\u000e\u001a\u00028��H\u0014¢\u0006\u0002\u0010\u0012J$\u0010\u0013\u001a\u00028��2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0096\u0002¢\u0006\u0002\u0010\u0015J,\u0010\u0016\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0007\u001a\u00028��H\u0096\u0002¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0010\u0010\u0007\u001a\u00028��X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u001a"}, d2 = {"Lkotlin/properties/ObservableProperty;", RuntimeConstants.SIG_VOID, "Lkotlin/properties/ReadWriteProperty;", "", "initialValue", Constants.CTOR, "(Ljava/lang/Object;)V", "value", Constants.OBJECT, "beforeChange", "", "property", "Lkotlin/reflect/KProperty;", "oldValue", "newValue", "(Lkotlin/reflect/KProperty;Ljava/lang/Object;Ljava/lang/Object;)Z", "afterChange", "", "(Lkotlin/reflect/KProperty;Ljava/lang/Object;Ljava/lang/Object;)V", "getValue", "thisRef", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "toString", "", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/properties/ObservableProperty.class */
public abstract class ObservableProperty<V> implements ReadWriteProperty<Object, V> {
    private V value;

    public ObservableProperty(V v) {
        this.value = v;
    }

    protected boolean beforeChange(@NotNull KProperty<?> property, V v, V v2) {
        Intrinsics.checkNotNullParameter(property, "property");
        return true;
    }

    protected void afterChange(@NotNull KProperty<?> property, V v, V v2) {
        Intrinsics.checkNotNullParameter(property, "property");
    }

    @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
    public V getValue(@Nullable Object thisRef, @NotNull KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        return this.value;
    }

    @Override // kotlin.properties.ReadWriteProperty
    public void setValue(@Nullable Object thisRef, @NotNull KProperty<?> property, V v) {
        Intrinsics.checkNotNullParameter(property, "property");
        V v2 = this.value;
        if (!beforeChange(property, v2, v)) {
            return;
        }
        this.value = v;
        afterChange(property, v2, v);
    }

    @NotNull
    public String toString() {
        return "ObservableProperty(value=" + this.value + ')';
    }
}
