package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: PrimitiveCompanionObjects.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\bÀ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n��\u0012\u0004\b\b\u0010\u0003R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n��\u0012\u0004\b\n\u0010\u0003¨\u0006\u000b"}, d2 = {"Lkotlin/jvm/internal/IntCompanionObject;", "", Constants.CTOR, "()V", "MIN_VALUE", "", "MAX_VALUE", "SIZE_BYTES", "getSIZE_BYTES$annotations", "SIZE_BITS", "getSIZE_BITS$annotations", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/jvm/internal/IntCompanionObject.class */
public final class IntCompanionObject {

    @NotNull
    public static final IntCompanionObject INSTANCE = new IntCompanionObject();
    public static final int MIN_VALUE = Integer.MIN_VALUE;
    public static final int MAX_VALUE = Integer.MAX_VALUE;
    public static final int SIZE_BYTES = 4;
    public static final int SIZE_BITS = 32;

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getSIZE_BYTES$annotations() {
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getSIZE_BITS$annotations() {
    }

    private IntCompanionObject() {
    }
}
