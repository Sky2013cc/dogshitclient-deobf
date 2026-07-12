package net.ccbluex.liquidbounce.utils.render;

import java.awt.image.BufferedImage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.util.Constants;

/* compiled from: CustomTexture.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u000eH\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R \u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/CustomTexture;", "", "image", "Ljava/awt/image/BufferedImage;", Constants.CTOR, "(Ljava/awt/image/BufferedImage;)V", "unloaded", "", "value", "", "textureId", "getTextureId", "()I", "unload", "", "finalize", "haze"})
@SourceDebugExtension({"SMAP\nCustomTexture.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CustomTexture.kt\nnet/ccbluex/liquidbounce/utils/render/CustomTexture\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,39:1\n1#2:40\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/CustomTexture.class */
public final class CustomTexture {

    @NotNull
    private final BufferedImage image;
    private boolean unloaded;
    private int textureId;

    public CustomTexture(@NotNull BufferedImage image) {
        Intrinsics.checkNotNullParameter(image, "image");
        this.image = image;
        this.textureId = -1;
    }

    public final int getTextureId() {
        if (!(!this.unloaded)) {
            throw new IllegalStateException("Texture unloaded".toString());
        }
        if (this.textureId == -1) {
            this.textureId = TextureUtil.func_110989_a(TextureUtil.func_110996_a(), this.image, true, true);
        }
        return this.textureId;
    }

    public final void unload() {
        if (!this.unloaded) {
            GL11.glDeleteTextures(getTextureId());
            this.unloaded = true;
        }
    }

    protected final void finalize() {
        unload();
    }
}
