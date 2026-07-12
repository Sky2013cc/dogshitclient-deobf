package net.ccbluex.liquidbounce.utils.render.shader.shaders;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import java.io.Closeable;
import kotlin.Metadata;
import net.ccbluex.liquidbounce.utils.render.shader.Shader;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.util.Constants;

/* compiled from: RainbowFontShader.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\t\bÆ\u0002\u0018��2\u00020\u00012\u00020\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0016J&\u0010\u001b\u001a\u00020��2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nR\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000e¨\u0006\u001f"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/shader/shaders/RainbowFontShader;", "Lnet/ccbluex/liquidbounce/utils/render/shader/Shader;", "Ljava/io/Closeable;", Constants.CTOR, "()V", "value", "", "isInUse", "()Z", "strengthX", "", "getStrengthX", "()F", "setStrengthX", "(F)V", "strengthY", "getStrengthY", "setStrengthY", "offset", "getOffset", "setOffset", "setupUniforms", "", "updateUniforms", "startShader", "stopShader", "close", "begin", "enable", SimpleTaglet.EXCLUDED, OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/shader/shaders/RainbowFontShader.class */
public final class RainbowFontShader extends Shader implements Closeable {

    @NotNull
    public static final RainbowFontShader INSTANCE = new RainbowFontShader();
    private static boolean isInUse;
    private static float strengthX;
    private static float strengthY;
    private static float offset;

    private RainbowFontShader() {
        super("rainbow_font_shader.frag");
    }

    public final boolean isInUse() {
        return isInUse;
    }

    public final float getStrengthX() {
        return strengthX;
    }

    public final void setStrengthX(float f) {
        strengthX = f;
    }

    public final float getStrengthY() {
        return strengthY;
    }

    public final void setStrengthY(float f) {
        strengthY = f;
    }

    public final float getOffset() {
        return offset;
    }

    public final void setOffset(float f) {
        offset = f;
    }

    @Override // net.ccbluex.liquidbounce.utils.render.shader.Shader
    public void setupUniforms() {
        setupUniform("offset");
        setupUniform("strength");
    }

    @Override // net.ccbluex.liquidbounce.utils.render.shader.Shader
    public void updateUniforms() {
        GL20.glUniform2f(getUniform("strength"), strengthX, strengthY);
        GL20.glUniform1f(getUniform("offset"), offset);
    }

    @Override // net.ccbluex.liquidbounce.utils.render.shader.Shader
    public void startShader() {
        super.startShader();
        isInUse = true;
    }

    @Override // net.ccbluex.liquidbounce.utils.render.shader.Shader
    public void stopShader() {
        super.stopShader();
        isInUse = false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (isInUse) {
            stopShader();
        }
    }

    @NotNull
    public final RainbowFontShader begin(boolean enable, float x, float y, float offset2) {
        if (enable) {
            strengthX = x;
            strengthY = y;
            offset = offset2;
            startShader();
        }
        return this;
    }
}
