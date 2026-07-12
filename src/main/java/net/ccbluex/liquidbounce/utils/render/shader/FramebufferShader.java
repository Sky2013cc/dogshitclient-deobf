package net.ccbluex.liquidbounce.utils.render.shader;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.util.Constants;

/* compiled from: FramebufferShader.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018�� 12\u00020\u0001:\u00011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0007J&\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u0007J\u0018\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010-2\u0006\u0010\u001e\u001a\u00020\u0007J\u000e\u0010/\u001a\u00020'2\u0006\u00100\u001a\u00020-R\u001a\u0010\u0006\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n��\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n��\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u001a\u0010\u0012\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001a\u0010\u0015\u001a\u00020\u0016X\u0084\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0016X\u0084\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001a\u0010\u001e\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001f\u0010\t\"\u0004\b \u0010\u000bR\u001a\u0010!\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n��\u001a\u0004\b\"\u0010\t\"\u0004\b#\u0010\u000bR\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n��¨\u00062"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/shader/FramebufferShader;", "Lnet/ccbluex/liquidbounce/utils/render/shader/Shader;", "fragmentShader", "", Constants.CTOR, "(Ljava/lang/String;)V", "red", "", "getRed", "()F", "setRed", "(F)V", "green", "getGreen", "setGreen", "blue", "getBlue", "setBlue", "alpha", "getAlpha", "setAlpha", "radius", "", "getRadius", "()I", "setRadius", "(I)V", "fade", "getFade", "setFade", "renderScale", "getRenderScale", "setRenderScale", "targetAlpha", "getTargetAlpha", "setTargetAlpha", "entityShadows", "", "startDraw", "", "partialTicks", "stopDraw", "color", "Ljava/awt/Color;", "setupFrameBuffer", "Lnet/minecraft/client/shader/Framebuffer;", "frameBuffer", "drawFramebuffer", "framebuffer", "Companion", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/shader/FramebufferShader.class */
public abstract class FramebufferShader extends Shader {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private float red;
    private float green;
    private float blue;
    private float alpha;
    private int radius;
    private int fade;
    private float renderScale;
    private float targetAlpha;
    private boolean entityShadows;

    @Nullable
    private static Framebuffer framebuffer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FramebufferShader(@NotNull String fragmentShader) {
        super(fragmentShader);
        Intrinsics.checkNotNullParameter(fragmentShader, "fragmentShader");
        this.red = 1.0f;
        this.green = 1.0f;
        this.blue = 1.0f;
        this.alpha = 1.0f;
        this.radius = 5;
        this.fade = 10;
        this.renderScale = 1.0f;
    }

    /* compiled from: FramebufferShader.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/shader/FramebufferShader$Companion;", "", Constants.CTOR, "()V", "framebuffer", "Lnet/minecraft/client/shader/Framebuffer;", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/shader/FramebufferShader$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float getRed() {
        return this.red;
    }

    protected final void setRed(float f) {
        this.red = f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float getGreen() {
        return this.green;
    }

    protected final void setGreen(float f) {
        this.green = f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float getBlue() {
        return this.blue;
    }

    protected final void setBlue(float f) {
        this.blue = f;
    }

    protected final float getAlpha() {
        return this.alpha;
    }

    protected final void setAlpha(float f) {
        this.alpha = f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getRadius() {
        return this.radius;
    }

    protected final void setRadius(int i) {
        this.radius = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getFade() {
        return this.fade;
    }

    protected final void setFade(int i) {
        this.fade = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float getRenderScale() {
        return this.renderScale;
    }

    protected final void setRenderScale(float f) {
        this.renderScale = f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float getTargetAlpha() {
        return this.targetAlpha;
    }

    protected final void setTargetAlpha(float f) {
        this.targetAlpha = f;
    }

    public final void startDraw(float partialTicks, float renderScale) {
        this.renderScale = renderScale;
        GlStateManager.func_179094_E();
        GlStateManager.func_179141_d();
        GlStateManager.func_179123_a();
        Companion companion = Companion;
        framebuffer = setupFrameBuffer(framebuffer, renderScale);
        Framebuffer framebuffer2 = framebuffer;
        Intrinsics.checkNotNull(framebuffer2);
        framebuffer2.func_147614_f();
        Framebuffer framebuffer3 = framebuffer;
        Intrinsics.checkNotNull(framebuffer3);
        framebuffer3.func_147610_a(true);
        this.entityShadows = getMc().field_71474_y.field_181151_V;
        getMc().field_71474_y.field_181151_V = false;
        getMc().field_71460_t.func_78479_a(partialTicks, 0);
    }

    public final void stopDraw(@NotNull Color color, int radius, int fade, float targetAlpha) {
        Intrinsics.checkNotNullParameter(color, "color");
        getMc().field_71474_y.field_181151_V = this.entityShadows;
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        getMc().func_147110_a().func_147610_a(true);
        this.red = color.getRed() / 255.0f;
        this.green = color.getGreen() / 255.0f;
        this.blue = color.getBlue() / 255.0f;
        this.alpha = color.getAlpha() / 255.0f;
        this.radius = radius;
        this.fade = fade;
        this.targetAlpha = targetAlpha;
        getMc().field_71460_t.func_175072_h();
        RenderHelper.func_74518_a();
        startShader();
        getMc().field_71460_t.func_78478_c();
        Framebuffer framebuffer2 = framebuffer;
        Intrinsics.checkNotNull(framebuffer2);
        drawFramebuffer(framebuffer2);
        stopShader();
        getMc().field_71460_t.func_175072_h();
        GlStateManager.func_179121_F();
        GlStateManager.func_179099_b();
    }

    @NotNull
    public final Framebuffer setupFrameBuffer(@Nullable Framebuffer frameBuffer, float renderScale) {
        if (frameBuffer != null) {
            frameBuffer.func_147608_a();
        }
        return new Framebuffer(MathKt.roundToInt(getMc().field_71443_c * renderScale), MathKt.roundToInt(getMc().field_71440_d * renderScale), true);
    }

    public final void drawFramebuffer(@NotNull Framebuffer framebuffer2) {
        Intrinsics.checkNotNullParameter(framebuffer2, "framebuffer");
        ScaledResolution scaledResolution = new ScaledResolution(getMc());
        double scaledWidth = scaledResolution.func_78327_c();
        double scaledHeight = scaledResolution.func_78324_d();
        Tessellator tessellator = Tessellator.func_178181_a();
        WorldRenderer buffer = tessellator.func_178180_c();
        GL11.glBindTexture(3553, framebuffer2.field_147617_g);
        buffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        buffer.func_181662_b(0.0d, 0.0d, 1.0d).func_181673_a(0.0d, 1.0d).func_181675_d();
        buffer.func_181662_b(0.0d, scaledHeight, 1.0d).func_181673_a(0.0d, 0.0d).func_181675_d();
        buffer.func_181662_b(scaledWidth, scaledHeight, 1.0d).func_181673_a(1.0d, 0.0d).func_181675_d();
        buffer.func_181662_b(scaledWidth, 0.0d, 0.0d).func_181673_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
        GL20.glUseProgram(0);
    }
}
