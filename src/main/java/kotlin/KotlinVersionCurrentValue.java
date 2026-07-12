package kotlin;

import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: KotlinVersion.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\bÂ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"Lkotlin/KotlinVersionCurrentValue;", "", Constants.CTOR, "()V", PropertyDescriptor.GET, "Lkotlin/KotlinVersion;", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/KotlinVersionCurrentValue.class */
final class KotlinVersionCurrentValue {

    @NotNull
    public static final KotlinVersionCurrentValue INSTANCE = new KotlinVersionCurrentValue();

    private KotlinVersionCurrentValue() {
    }

    @JvmStatic
    @NotNull
    public static final KotlinVersion get() {
        return new KotlinVersion(2, 1, 0);
    }
}
