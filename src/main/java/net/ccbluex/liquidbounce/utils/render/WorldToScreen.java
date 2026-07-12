package net.ccbluex.liquidbounce.utils.render;

import java.nio.FloatBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import org.spongepowered.asm.util.Constants;

/* compiled from: WorldToScreen.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J8\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u0007¨\u0006\u0010"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/WorldToScreen;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", Constants.CTOR, "()V", "getMatrix", "Lorg/lwjgl/util/vector/Matrix4f;", "matrix", "", "worldToScreen", "Lorg/lwjgl/util/vector/Vector2f;", "pointInWorld", "Lorg/lwjgl/util/vector/Vector3f;", "viewMatrix", "projectionMatrix", "screenWidth", "screenHeight", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/WorldToScreen.class */
public final class WorldToScreen implements MinecraftInstance {

    @NotNull
    public static final WorldToScreen INSTANCE = new WorldToScreen();

    private WorldToScreen() {
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    @NotNull
    public final Matrix4f getMatrix(int matrix) {
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
        GL11.glGetFloat(matrix, floatBuffer);
        Matrix4f load = new Matrix4f().load(floatBuffer);
        Intrinsics.checkNotNull(load, "null cannot be cast to non-null type org.lwjgl.util.vector.Matrix4f");
        return load;
    }

    public static /* synthetic */ Vector2f worldToScreen$default(WorldToScreen worldToScreen, Vector3f vector3f, Matrix4f matrix4f, Matrix4f matrix4f2, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            matrix4f = worldToScreen.getMatrix(2982);
        }
        if ((i3 & 4) != 0) {
            matrix4f2 = worldToScreen.getMatrix(2983);
        }
        if ((i3 & 8) != 0) {
            i = worldToScreen.getMc().field_71443_c;
        }
        if ((i3 & 16) != 0) {
            i2 = worldToScreen.getMc().field_71440_d;
        }
        return worldToScreen.worldToScreen(vector3f, matrix4f, matrix4f2, i, i2);
    }

    @Nullable
    public final Vector2f worldToScreen(@NotNull Vector3f pointInWorld, @NotNull Matrix4f viewMatrix, @NotNull Matrix4f projectionMatrix, int screenWidth, int screenHeight) {
        Vector4f times;
        Vector4f clipSpacePos;
        Intrinsics.checkNotNullParameter(pointInWorld, "pointInWorld");
        Intrinsics.checkNotNullParameter(viewMatrix, "viewMatrix");
        Intrinsics.checkNotNullParameter(projectionMatrix, "projectionMatrix");
        times = WorldToScreenKt.times(new Vector4f(pointInWorld.x, pointInWorld.y, pointInWorld.z, 1.0f), viewMatrix);
        clipSpacePos = WorldToScreenKt.times(times, projectionMatrix);
        Vector3f scale = new Vector3f(clipSpacePos.x, clipSpacePos.y, clipSpacePos.z).scale(1 / clipSpacePos.w);
        Intrinsics.checkNotNull(scale, "null cannot be cast to non-null type org.lwjgl.util.vector.Vector3f");
        Vector3f ndcSpacePos = scale;
        float screenX = ((ndcSpacePos.x + 1.0f) / 2.0f) * screenWidth;
        float screenY = ((1.0f - ndcSpacePos.y) / 2.0f) * screenHeight;
        if (Math.abs(ndcSpacePos.z) > 1.0f) {
            return null;
        }
        return new Vector2f(screenX, screenY);
    }
}
