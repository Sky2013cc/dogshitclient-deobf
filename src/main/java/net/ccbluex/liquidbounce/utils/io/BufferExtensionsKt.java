package net.ccbluex.liquidbounce.utils.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: BufferExtensions.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\u001a\n\u0010��\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"flipSafely", "", "Ljava/nio/ByteBuffer;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/io/BufferExtensionsKt.class */
public final class BufferExtensionsKt {
    public static final void flipSafely(@NotNull ByteBuffer $this$flipSafely) {
        Intrinsics.checkNotNullParameter($this$flipSafely, "<this>");
        try {
            $this$flipSafely.flip();
        } catch (Exception e) {
            try {
                $this$flipSafely.flip();
            } catch (Exception any) {
                any.printStackTrace();
                Unit unit = Unit.INSTANCE;
            }
        }
    }
}
