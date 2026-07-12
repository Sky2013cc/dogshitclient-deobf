package net.ccbluex.liquidbounce.utils.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

/* compiled from: WorldToScreen.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0082\u0002¨\u0006\u0004"}, d2 = {"times", "Lorg/lwjgl/util/vector/Vector4f;", "mat", "Lorg/lwjgl/util/vector/Matrix4f;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/WorldToScreenKt.class */
public final class WorldToScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Vector4f times(Vector4f $this$times, Matrix4f mat) {
        Intrinsics.checkNotNullParameter($this$times, "<this>");
        return new Vector4f(($this$times.x * mat.m00) + ($this$times.y * mat.m10) + ($this$times.z * mat.m20) + ($this$times.w * mat.m30), ($this$times.x * mat.m01) + ($this$times.y * mat.m11) + ($this$times.z * mat.m21) + ($this$times.w * mat.m31), ($this$times.x * mat.m02) + ($this$times.y * mat.m12) + ($this$times.z * mat.m22) + ($this$times.w * mat.m32), ($this$times.x * mat.m03) + ($this$times.y * mat.m13) + ($this$times.z * mat.m23) + ($this$times.w * mat.m33));
    }
}
