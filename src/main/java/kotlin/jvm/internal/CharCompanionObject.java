package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: PrimitiveCompanionObjects.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\bÀ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n��\u0012\u0004\b\u0006\u0010\u0003R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n��\u0012\u0004\b\b\u0010\u0003R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u0016\u0010\u000f\u001a\u00020\u00108\u0006X\u0087T¢\u0006\b\n��\u0012\u0004\b\u0011\u0010\u0003R\u0016\u0010\u0012\u001a\u00020\u00108\u0006X\u0087T¢\u0006\b\n��\u0012\u0004\b\u0013\u0010\u0003¨\u0006\u0014"}, d2 = {"Lkotlin/jvm/internal/CharCompanionObject;", "", Constants.CTOR, "()V", "MIN_VALUE", "", "getMIN_VALUE$annotations", "MAX_VALUE", "getMAX_VALUE$annotations", "MIN_HIGH_SURROGATE", "MAX_HIGH_SURROGATE", "MIN_LOW_SURROGATE", "MAX_LOW_SURROGATE", "MIN_SURROGATE", "MAX_SURROGATE", "SIZE_BYTES", "", "getSIZE_BYTES$annotations", "SIZE_BITS", "getSIZE_BITS$annotations", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/jvm/internal/CharCompanionObject.class */
public final class CharCompanionObject {

    @NotNull
    public static final CharCompanionObject INSTANCE = new CharCompanionObject();
    public static final char MIN_VALUE = 0;
    public static final char MAX_VALUE = 65535;
    public static final char MIN_HIGH_SURROGATE = 55296;
    public static final char MAX_HIGH_SURROGATE = 56319;
    public static final char MIN_LOW_SURROGATE = 56320;
    public static final char MAX_LOW_SURROGATE = 57343;
    public static final char MIN_SURROGATE = 55296;
    public static final char MAX_SURROGATE = 57343;
    public static final int SIZE_BYTES = 2;
    public static final int SIZE_BITS = 16;

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getMIN_VALUE$annotations() {
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getMAX_VALUE$annotations() {
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getSIZE_BYTES$annotations() {
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getSIZE_BITS$annotations() {
    }

    private CharCompanionObject() {
    }
}
