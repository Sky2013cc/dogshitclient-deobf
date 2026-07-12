package net.ccbluex.liquidbounce.tabs;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: HeadsTab.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\t\b\u0002\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lnet/ccbluex/liquidbounce/tabs/HeadInfo;", "", "name", "", "uuid", "value", Constants.CTOR, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getUuid", "getValue", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/tabs/HeadInfo.class */
final class HeadInfo {

    @NotNull
    private final String name;

    @NotNull
    private final String uuid;

    @NotNull
    private final String value;

    public HeadInfo(@NotNull String name, @NotNull String uuid, @NotNull String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(value, "value");
        this.name = name;
        this.uuid = uuid;
        this.value = value;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getUuid() {
        return this.uuid;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
