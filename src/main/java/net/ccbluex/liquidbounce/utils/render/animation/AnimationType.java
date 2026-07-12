package net.ccbluex.liquidbounce.utils.render.animation;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: AnimationType.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/animation/AnimationType;", "", Constants.CTOR, "(Ljava/lang/String;I)V", "LINEAR", "EASE_IN_QUAD", "EASE_OUT_QUAD", "EASE_IN_OUT_QUAD", "EASE_IN_ELASTIC", "EASE_OUT_ELASTIC", "EASE_IN_OUT_ELASTIC", "EASE_IN_BACK", "EASE_OUT_BACK", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/animation/AnimationType.class */
public enum AnimationType {
    LINEAR,
    EASE_IN_QUAD,
    EASE_OUT_QUAD,
    EASE_IN_OUT_QUAD,
    EASE_IN_ELASTIC,
    EASE_OUT_ELASTIC,
    EASE_IN_OUT_ELASTIC,
    EASE_IN_BACK,
    EASE_OUT_BACK;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    @NotNull
    public static EnumEntries<AnimationType> getEntries() {
        return $ENTRIES;
    }
}
