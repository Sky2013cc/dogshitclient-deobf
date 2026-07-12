package net.ccbluex.liquidbounce.cape;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: CapeAPI.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0004\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lnet/ccbluex/liquidbounce/cape/CapeInfo;", "", "resourceLocation", "Lnet/minecraft/util/ResourceLocation;", "isCapeAvailable", "", Constants.CTOR, "(Lnet/minecraft/util/ResourceLocation;Z)V", "getResourceLocation", "()Lnet/minecraft/util/ResourceLocation;", "()Z", "setCapeAvailable", "(Z)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/cape/CapeInfo.class */
public final class CapeInfo {

    @NotNull
    private final ResourceLocation resourceLocation;
    private boolean isCapeAvailable;

    @NotNull
    public final ResourceLocation component1() {
        return this.resourceLocation;
    }

    public final boolean component2() {
        return this.isCapeAvailable;
    }

    @NotNull
    public final CapeInfo copy(@NotNull ResourceLocation resourceLocation, boolean isCapeAvailable) {
        Intrinsics.checkNotNullParameter(resourceLocation, "resourceLocation");
        return new CapeInfo(resourceLocation, isCapeAvailable);
    }

    public static /* synthetic */ CapeInfo copy$default(CapeInfo capeInfo, ResourceLocation resourceLocation, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            resourceLocation = capeInfo.resourceLocation;
        }
        if ((i & 2) != 0) {
            z = capeInfo.isCapeAvailable;
        }
        return capeInfo.copy(resourceLocation, z);
    }

    @NotNull
    public String toString() {
        return "CapeInfo(resourceLocation=" + this.resourceLocation + ", isCapeAvailable=" + this.isCapeAvailable + ')';
    }

    public int hashCode() {
        int result = this.resourceLocation.hashCode();
        return (result * 31) + Boolean.hashCode(this.isCapeAvailable);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CapeInfo)) {
            return false;
        }
        CapeInfo capeInfo = (CapeInfo) other;
        return Intrinsics.areEqual(this.resourceLocation, capeInfo.resourceLocation) && this.isCapeAvailable == capeInfo.isCapeAvailable;
    }

    public CapeInfo(@NotNull ResourceLocation resourceLocation, boolean isCapeAvailable) {
        Intrinsics.checkNotNullParameter(resourceLocation, "resourceLocation");
        this.resourceLocation = resourceLocation;
        this.isCapeAvailable = isCapeAvailable;
    }

    public /* synthetic */ CapeInfo(ResourceLocation resourceLocation, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(resourceLocation, (i & 2) != 0 ? false : z);
    }

    @NotNull
    public final ResourceLocation getResourceLocation() {
        return this.resourceLocation;
    }

    public final boolean isCapeAvailable() {
        return this.isCapeAvailable;
    }

    public final void setCapeAvailable(boolean z) {
        this.isCapeAvailable = z;
    }
}
