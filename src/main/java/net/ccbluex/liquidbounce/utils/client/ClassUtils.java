package net.ccbluex.liquidbounce.utils.client;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: ClassUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u0007R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/client/ClassUtils;", "", Constants.CTOR, "()V", "cachedClasses", "", "", "", "hasClass", "className", "hasForge", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/ClassUtils.class */
public final class ClassUtils {

    @NotNull
    public static final ClassUtils INSTANCE = new ClassUtils();

    @NotNull
    private static final Map<String, Boolean> cachedClasses = new LinkedHashMap();

    private ClassUtils() {
    }

    public final boolean hasClass(@NotNull String className) {
        boolean z;
        Intrinsics.checkNotNullParameter(className, "className");
        if (cachedClasses.containsKey(className)) {
            Boolean bool = cachedClasses.get(className);
            Intrinsics.checkNotNull(bool);
            return bool.booleanValue();
        }
        try {
            Class.forName(className);
            cachedClasses.put(className, true);
            z = true;
        } catch (ClassNotFoundException e) {
            cachedClasses.put(className, false);
            z = false;
        }
        return z;
    }

    public final boolean hasForge() {
        return hasClass("net.minecraftforge.common.MinecraftForge");
    }
}
