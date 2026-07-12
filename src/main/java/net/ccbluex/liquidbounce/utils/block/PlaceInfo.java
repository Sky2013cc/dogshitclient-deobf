package net.ccbluex.liquidbounce.utils.block;

import java.util.Iterator;
import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: PlaceInfo.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\f\u0018�� \u00122\u00020\u0001:\u0001\u0012B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/block/PlaceInfo;", "", "blockPos", "Lnet/minecraft/util/BlockPos;", "enumFacing", "Lnet/minecraft/util/EnumFacing;", "vec3", "Lnet/minecraft/util/Vec3;", Constants.CTOR, "(Lnet/minecraft/util/BlockPos;Lnet/minecraft/util/EnumFacing;Lnet/minecraft/util/Vec3;)V", "getBlockPos", "()Lnet/minecraft/util/BlockPos;", "getEnumFacing", "()Lnet/minecraft/util/EnumFacing;", "getVec3", "()Lnet/minecraft/util/Vec3;", "setVec3", "(Lnet/minecraft/util/Vec3;)V", "Companion", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/block/PlaceInfo.class */
public final class PlaceInfo {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final BlockPos blockPos;

    @NotNull
    private final EnumFacing enumFacing;

    @NotNull
    private Vec3 vec3;

    public PlaceInfo(@NotNull BlockPos blockPos, @NotNull EnumFacing enumFacing, @NotNull Vec3 vec3) {
        Intrinsics.checkNotNullParameter(blockPos, "blockPos");
        Intrinsics.checkNotNullParameter(enumFacing, "enumFacing");
        Intrinsics.checkNotNullParameter(vec3, "vec3");
        this.blockPos = blockPos;
        this.enumFacing = enumFacing;
        this.vec3 = vec3;
    }

    public /* synthetic */ PlaceInfo(BlockPos blockPos, EnumFacing enumFacing, Vec3 vec3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(blockPos, enumFacing, (i & 4) != 0 ? BlockExtensionKt.getCenter(blockPos) : vec3);
    }

    @NotNull
    public final BlockPos getBlockPos() {
        return this.blockPos;
    }

    @NotNull
    public final EnumFacing getEnumFacing() {
        return this.enumFacing;
    }

    @NotNull
    public final Vec3 getVec3() {
        return this.vec3;
    }

    public final void setVec3(@NotNull Vec3 vec3) {
        Intrinsics.checkNotNullParameter(vec3, "<set-?>");
        this.vec3 = vec3;
    }

    /* compiled from: PlaceInfo.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/block/PlaceInfo$Companion;", "", Constants.CTOR, "()V", PropertyDescriptor.GET, "Lnet/ccbluex/liquidbounce/utils/block/PlaceInfo;", "pos", "Lnet/minecraft/util/BlockPos;", "haze"})
    @SourceDebugExtension({"SMAP\nPlaceInfo.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlaceInfo.kt\nnet/ccbluex/liquidbounce/utils/block/PlaceInfo$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,24:1\n1#2:25\n*E\n"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/block/PlaceInfo$Companion.class */
    public static final class Companion {

        /* compiled from: PlaceInfo.kt */
        @Metadata(mv = {2, 0, 0}, k = 3, xi = 48)
        /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/block/PlaceInfo$Companion$EntriesMappings.class */
        public /* synthetic */ class EntriesMappings {
            public static final /* synthetic */ EnumEntries<EnumFacing> entries$0 = EnumEntriesKt.enumEntries(EnumFacing.values());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0054 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:19:? A[LOOP:0: B:2:0x0015->B:19:?, LOOP_END, SYNTHETIC] */
        @Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final PlaceInfo get(@NotNull BlockPos pos) {
            Object obj;
            boolean z;
            Intrinsics.checkNotNullParameter(pos, "pos");
            Iterator<E> it = EntriesMappings.entries$0.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                Object next = it.next();
                EnumFacing it2 = (EnumFacing) next;
                if (it2 != EnumFacing.UP) {
                    BlockPos func_177972_a = pos.func_177972_a(it2);
                    Intrinsics.checkNotNullExpressionValue(func_177972_a, "offset(...)");
                    if (BlockExtensionKt.canBeClicked(func_177972_a)) {
                        z = true;
                        if (!z) {
                            obj = next;
                            break;
                        }
                    }
                }
                z = false;
                if (!z) {
                }
            }
            EnumFacing side = (EnumFacing) obj;
            if (side == null) {
                return null;
            }
            BlockPos func_177972_a2 = pos.func_177972_a(side);
            Intrinsics.checkNotNullExpressionValue(func_177972_a2, "offset(...)");
            EnumFacing func_176734_d = side.func_176734_d();
            Intrinsics.checkNotNullExpressionValue(func_176734_d, "getOpposite(...)");
            return new PlaceInfo(func_177972_a2, func_176734_d, null, 4, null);
        }
    }
}
