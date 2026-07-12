package net.ccbluex.liquidbounce.utils.render.shader.shaders;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.utils.render.shader.FramebufferShader;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.util.Constants;

/* compiled from: GlowShader.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/shader/shaders/GlowShader;", "Lnet/ccbluex/liquidbounce/utils/render/shader/FramebufferShader;", Constants.CTOR, "()V", "setupUniforms", "", "updateUniforms", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/shader/shaders/GlowShader.class */
public final class GlowShader extends FramebufferShader {

    @NotNull
    public static final GlowShader INSTANCE = new GlowShader();

    private GlowShader() {
        super("glow.frag");
    }

    @Override // net.ccbluex.liquidbounce.utils.render.shader.Shader
    public void setupUniforms() {
        setupUniform("texture");
        setupUniform("texelSize");
        setupUniform("color");
        setupUniform("fade");
        setupUniform("radius");
        setupUniform("targetAlpha");
    }

    @Override // net.ccbluex.liquidbounce.utils.render.shader.Shader
    public void updateUniforms() {
        GL20.glUniform1i(getUniform("texture"), 0);
        GL20.glUniform2f(getUniform("texelSize"), (1.0f / getMc().field_71443_c) * getRenderScale(), (1.0f / getMc().field_71440_d) * getRenderScale());
        GL20.glUniform3f(getUniform("color"), getRed(), getGreen(), getBlue());
        GL20.glUniform1f(getUniform("fade"), getFade());
        GL20.glUniform1i(getUniform("radius"), getRadius());
        GL20.glUniform1f(getUniform("targetAlpha"), getTargetAlpha());
    }
}
