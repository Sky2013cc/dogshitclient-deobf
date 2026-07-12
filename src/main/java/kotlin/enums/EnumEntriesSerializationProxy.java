package kotlin.enums;

import java.io.Serializable;
import java.lang.Enum;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: EnumEntriesSerializationProxy.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0002\b��\u0018�� \r*\u000e\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004:\u0001\rB\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00028��0\nX\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000e"}, d2 = {"Lkotlin/enums/EnumEntriesSerializationProxy;", "E", "", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "entries", "", Constants.CTOR, "([Ljava/lang/Enum;)V", "c", Constants.CLASS, "readResolve", "", "Companion", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/enums/EnumEntriesSerializationProxy.class */
public final class EnumEntriesSerializationProxy<E extends Enum<E>> implements Serializable {

    @NotNull
    private static final Companion Companion = new Companion(null);

    @NotNull
    private final Class<E> c;
    private static final long serialVersionUID = 0;

    public EnumEntriesSerializationProxy(@NotNull E[] entries) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        Class<E> cls = (Class<E>) entries.getClass().getComponentType();
        Intrinsics.checkNotNull(cls);
        this.c = cls;
    }

    /* compiled from: EnumEntriesSerializationProxy.kt */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\t\n��\b\u0082\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lkotlin/enums/EnumEntriesSerializationProxy$Companion;", "", Constants.CTOR, "()V", sun.rmi.rmic.iiop.Constants.SERIAL_VERSION_UID, "", "kotlin-stdlib"})
    /* loaded from: target.jar:kotlin/enums/EnumEntriesSerializationProxy$Companion.class */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private final Object readResolve() {
        E[] enumConstants = this.c.getEnumConstants();
        Intrinsics.checkNotNullExpressionValue(enumConstants, "getEnumConstants(...)");
        return EnumEntriesKt.enumEntries(enumConstants);
    }
}
