package net.ccbluex.liquidbounce.utils.render.shader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import javax.imageio.ImageIO;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Background.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0014J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000e"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/shader/ImageBackground;", "Lnet/ccbluex/liquidbounce/utils/render/shader/Background;", "backgroundFile", "Ljava/io/File;", Constants.CTOR, "(Ljava/io/File;)V", "resourceLocation", "Lnet/minecraft/util/ResourceLocation;", "initBackground", "", "drawBackground", "width", "", "height", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/shader/ImageBackground.class */
public final class ImageBackground extends Background {

    @NotNull
    private final ResourceLocation resourceLocation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageBackground(@NotNull File backgroundFile) {
        super(backgroundFile, null);
        Intrinsics.checkNotNullParameter(backgroundFile, "backgroundFile");
        StringBuilder sb = new StringBuilder();
        String lowerCase = LiquidBounce.CLIENT_NAME.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        this.resourceLocation = new ResourceLocation(sb.append(lowerCase).append("/background.png").toString());
    }

    @Override // net.ccbluex.liquidbounce.utils.render.shader.Background
    protected void initBackground() {
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(getBackgroundFile()));
            MinecraftInstance.mc.func_110434_K().func_110579_a(this.resourceLocation, new DynamicTexture(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // net.ccbluex.liquidbounce.utils.render.shader.Background
    public void drawBackground(int width, int height) {
        MinecraftInstance.mc.func_110434_K().func_110577_a(this.resourceLocation);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        Gui.func_152125_a(0, 0, 0.0f, 0.0f, width, height, width, height, width, height);
    }
}
