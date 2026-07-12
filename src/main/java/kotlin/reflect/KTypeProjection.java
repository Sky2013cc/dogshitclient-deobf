package kotlin.reflect;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Marker;

/* compiled from: KTypeProjection.kt */
@SinceKotlin(version = "1.1")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018�� \u00162\u00020\u0001:\u0001\u0016B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0010\u001a\u00020��2\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lkotlin/reflect/KTypeProjection;", "", "variance", "Lkotlin/reflect/KVariance;", Constants.ATTR_TYPE, "Lkotlin/reflect/KType;", org.spongepowered.asm.util.Constants.CTOR, "(Lkotlin/reflect/KVariance;Lkotlin/reflect/KType;)V", "getVariance", "()Lkotlin/reflect/KVariance;", "getType", "()Lkotlin/reflect/KType;", "toString", "", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "Companion", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/reflect/KTypeProjection.class */
public final class KTypeProjection {

    @Nullable
    private final KVariance variance;

    @Nullable
    private final KType type;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    public static final KTypeProjection star = new KTypeProjection(null, null);

    /* compiled from: KTypeProjection.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlin/reflect/KTypeProjection$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KVariance.values().length];
            try {
                iArr[KVariance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[KVariance.IN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[KVariance.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Nullable
    public final KVariance component1() {
        return this.variance;
    }

    @Nullable
    public final KType component2() {
        return this.type;
    }

    @NotNull
    public final KTypeProjection copy(@Nullable KVariance variance, @Nullable KType type) {
        return new KTypeProjection(variance, type);
    }

    public static /* synthetic */ KTypeProjection copy$default(KTypeProjection kTypeProjection, KVariance kVariance, KType kType, int i, Object obj) {
        if ((i & 1) != 0) {
            kVariance = kTypeProjection.variance;
        }
        if ((i & 2) != 0) {
            kType = kTypeProjection.type;
        }
        return kTypeProjection.copy(kVariance, kType);
    }

    public int hashCode() {
        int result = this.variance == null ? 0 : this.variance.hashCode();
        return (result * 31) + (this.type == null ? 0 : this.type.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KTypeProjection)) {
            return false;
        }
        KTypeProjection kTypeProjection = (KTypeProjection) other;
        return this.variance == kTypeProjection.variance && Intrinsics.areEqual(this.type, kTypeProjection.type);
    }

    public KTypeProjection(@Nullable KVariance variance, @Nullable KType type) {
        String str;
        this.variance = variance;
        this.type = type;
        if ((this.variance == null) == (this.type == null)) {
            return;
        }
        if (this.variance == null) {
            str = "Star projection must have no type specified.";
        } else {
            str = "The projection variance " + this.variance + " requires type to be specified.";
        }
        throw new IllegalArgumentException(str.toString());
    }

    @Nullable
    public final KVariance getVariance() {
        return this.variance;
    }

    @Nullable
    public final KType getType() {
        return this.type;
    }

    @NotNull
    public String toString() {
        KVariance kVariance = this.variance;
        switch (kVariance == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kVariance.ordinal()]) {
            case -1:
                return Marker.ANY_MARKER;
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
                return String.valueOf(this.type);
            case 2:
                return "in " + this.type;
            case 3:
                return "out " + this.type;
        }
    }

    /* compiled from: KTypeProjection.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u0016\u0010\u0004\u001a\u00020\u00058��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0006\u0010\u0003R\u0011\u0010\u0007\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lkotlin/reflect/KTypeProjection$Companion;", "", org.spongepowered.asm.util.Constants.CTOR, "()V", "star", "Lkotlin/reflect/KTypeProjection;", "getStar$annotations", "STAR", "getSTAR", "()Lkotlin/reflect/KTypeProjection;", "invariant", Constants.ATTR_TYPE, "Lkotlin/reflect/KType;", "contravariant", "covariant", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/reflect/KTypeProjection$Companion.class */
    public static final class Companion {
        @PublishedApi
        public static /* synthetic */ void getStar$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KTypeProjection getSTAR() {
            return KTypeProjection.star;
        }

        @JvmStatic
        @NotNull
        public final KTypeProjection invariant(@NotNull KType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return new KTypeProjection(KVariance.INVARIANT, type);
        }

        @JvmStatic
        @NotNull
        public final KTypeProjection contravariant(@NotNull KType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return new KTypeProjection(KVariance.IN, type);
        }

        @JvmStatic
        @NotNull
        public final KTypeProjection covariant(@NotNull KType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return new KTypeProjection(KVariance.OUT, type);
        }
    }

    @JvmStatic
    @NotNull
    public static final KTypeProjection invariant(@NotNull KType type) {
        return Companion.invariant(type);
    }

    @JvmStatic
    @NotNull
    public static final KTypeProjection contravariant(@NotNull KType type) {
        return Companion.contravariant(type);
    }

    @JvmStatic
    @NotNull
    public static final KTypeProjection covariant(@NotNull KType type) {
        return Companion.covariant(type);
    }
}
