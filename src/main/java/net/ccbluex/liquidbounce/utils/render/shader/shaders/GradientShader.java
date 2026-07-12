package net.ccbluex.liquidbounce.utils.render.shader.shaders;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import java.io.Closeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.ccbluex.liquidbounce.utils.render.shader.Shader;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: GradientShader.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n��\bÆ\u0002\u0018��2\u00020\u00012\u00020\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020'H\u0016J\b\u0010)\u001a\u00020'H\u0016J\b\u0010*\u001a\u00020'H\u0016J\b\u0010+\u001a\u00020'H\u0016J>\u0010,\u001a\u00020��2\u0006\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\n2\u0006\u0010/\u001a\u00020\n2\f\u00100\u001a\b\u0012\u0004\u0012\u00020 012\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nH\u0007R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u00062"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/shader/shaders/GradientShader;", "Lnet/ccbluex/liquidbounce/utils/render/shader/Shader;", "Ljava/io/Closeable;", Constants.CTOR, "()V", "value", "", "isInUse", "()Z", "strengthX", "", "getStrengthX", "()F", "setStrengthX", "(F)V", "strengthY", "getStrengthY", "setStrengthY", "offset", "getOffset", "setOffset", "speed", "getSpeed", "setSpeed", "maxColors", "", "getMaxColors", "()I", "setMaxColors", "(I)V", "colors", "", "", "getColors", "()[[F", "setColors", "([[F)V", "[[F", "setupUniforms", "", "updateUniforms", "startShader", "stopShader", "close", "begin", "enable", SimpleTaglet.EXCLUDED, OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "gradient", "", "haze"})
@SourceDebugExtension({"SMAP\nGradientShader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GradientShader.kt\nnet/ccbluex/liquidbounce/utils/render/shader/shaders/GradientShader\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,86:1\n37#2:87\n36#2,3:88\n*S KotlinDebug\n*F\n+ 1 GradientShader.kt\nnet/ccbluex/liquidbounce/utils/render/shader/shaders/GradientShader\n*L\n77#1:87\n77#1:88,3\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/shader/shaders/GradientShader.class */
public final class GradientShader extends Shader implements Closeable {
    private static boolean isInUse;
    private static float strengthX;
    private static float strengthY;
    private static float offset;

    @NotNull
    private static float[][] colors;

    @NotNull
    public static final GradientShader INSTANCE = new GradientShader();
    private static float speed = 1.0f;
    private static int maxColors = 4;

    private GradientShader() {
        super("gradient_shader.frag");
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

    public final float getSpeed() {
        return speed;
    }

    public final void setSpeed(float f) {
        speed = f;
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [float[], float[][]] */
    static {
        GradientShader gradientShader = INSTANCE;
        int i = maxColors;
        ?? r0 = new float[i];
        for (int i2 = 0; i2 < i; i2++) {
            float[] fArr = new float[4];
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 1.0f;
            r0[i2] = fArr;
        }
        colors = r0;
    }

    public final int getMaxColors() {
        return maxColors;
    }

    public final void setMaxColors(int i) {
        maxColors = i;
    }

    @NotNull
    public final float[][] getColors() {
        return colors;
    }

    public final void setColors(@NotNull float[][] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<set-?>");
        colors = fArr;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:5:0x0020
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:275)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:68)
        */
    @Override // net.ccbluex.liquidbounce.utils.render.shader.Shader
    public void setupUniforms() {
        /*
            r4 = this;
            r0 = r4
            java.lang.String r1 = "offset"
            r0.setupUniform(r1)
            r0 = r4
            java.lang.String r1 = "strength"
            r0.setupUniform(r1)
            r0 = r4
            java.lang.String r1 = "speed"
            r0.setupUniform(r1)
            r0 = r4
            java.lang.String r1 = "maxColors"
            r0.setupUniform(r1)
            r0 = 0
            r5 = r0
        L1a:
            r0 = r5
            r1 = 9
            if (r0 >= r1) goto L6f
        L21:
            r0 = r4
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L40
            r2 = r1
            r2.<init>()     // Catch: java.lang.Exception -> L40
            java.lang.String r2 = "colors["
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Exception -> L40
            r2 = r5
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Exception -> L40
            r2 = 93
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Exception -> L40
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L40
            r0.setupUniform(r1)     // Catch: java.lang.Exception -> L40
            goto L69
        L40:
            r6 = move-exception
            net.ccbluex.liquidbounce.utils.client.ClientUtils r0 = net.ccbluex.liquidbounce.utils.client.ClientUtils.INSTANCE
            org.apache.logging.log4j.Logger r0 = r0.getLOGGER()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = r1
            r2.<init>()
            r2 = r4
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getName()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = " setup uniforms error."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = r6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r0.error(r1, r2)
        L69:
            int r5 = r5 + 1
            goto L1a
        L6f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.setupUniforms():void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:5:0x003e
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:275)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:68)
        */
    @Override // net.ccbluex.liquidbounce.utils.render.shader.Shader
    public void updateUniforms() {
        /*
            r7 = this;
            r0 = r7
            java.lang.String r1 = "strength"
            int r0 = r0.getUniform(r1)
            float r1 = net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.strengthX
            float r2 = net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.strengthY
            org.lwjgl.opengl.GL20.glUniform2f(r0, r1, r2)
            r0 = r7
            java.lang.String r1 = "offset"
            int r0 = r0.getUniform(r1)
            float r1 = net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.offset
            org.lwjgl.opengl.GL20.glUniform1f(r0, r1)
            r0 = r7
            java.lang.String r1 = "speed"
            int r0 = r0.getUniform(r1)
            float r1 = net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.speed
            org.lwjgl.opengl.GL20.glUniform1f(r0, r1)
            r0 = r7
            java.lang.String r1 = "maxColors"
            int r0 = r0.getUniform(r1)
            int r1 = net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.maxColors
            org.lwjgl.opengl.GL20.glUniform1i(r0, r1)
            r0 = 0
            r8 = r0
            int r0 = net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.maxColors
            r9 = r0
        L39:
            r0 = r8
            r1 = r9
            if (r0 >= r1) goto Lac
        L3f:
            r0 = r7
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L7d
            r2 = r1
            r2.<init>()     // Catch: java.lang.Exception -> L7d
            java.lang.String r2 = "colors["
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Exception -> L7d
            r2 = r8
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Exception -> L7d
            r2 = 93
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Exception -> L7d
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L7d
            int r0 = r0.getUniform(r1)     // Catch: java.lang.Exception -> L7d
            float[][] r1 = net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.colors     // Catch: java.lang.Exception -> L7d
            r2 = r8
            r1 = r1[r2]     // Catch: java.lang.Exception -> L7d
            r2 = 0
            r1 = r1[r2]     // Catch: java.lang.Exception -> L7d
            float[][] r2 = net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.colors     // Catch: java.lang.Exception -> L7d
            r3 = r8
            r2 = r2[r3]     // Catch: java.lang.Exception -> L7d
            r3 = 1
            r2 = r2[r3]     // Catch: java.lang.Exception -> L7d
            float[][] r3 = net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.colors     // Catch: java.lang.Exception -> L7d
            r4 = r8
            r3 = r3[r4]     // Catch: java.lang.Exception -> L7d
            r4 = 2
            r3 = r3[r4]     // Catch: java.lang.Exception -> L7d
            float[][] r4 = net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.colors     // Catch: java.lang.Exception -> L7d
            r5 = r8
            r4 = r4[r5]     // Catch: java.lang.Exception -> L7d
            r5 = 3
            r4 = r4[r5]     // Catch: java.lang.Exception -> L7d
            org.lwjgl.opengl.GL20.glUniform4f(r0, r1, r2, r3, r4)     // Catch: java.lang.Exception -> L7d
            goto La6
        L7d:
            r10 = move-exception
            net.ccbluex.liquidbounce.utils.client.ClientUtils r0 = net.ccbluex.liquidbounce.utils.client.ClientUtils.INSTANCE
            org.apache.logging.log4j.Logger r0 = r0.getLOGGER()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = r1
            r2.<init>()
            r2 = r7
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getName()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = " update uniforms error."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = r10
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r0.error(r1, r2)
        La6:
            int r8 = r8 + 1
            goto L39
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.ccbluex.liquidbounce.utils.render.shader.shaders.GradientShader.updateUniforms():void");
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

    @JvmStatic
    @NotNull
    public static final GradientShader begin(boolean enable, float x, float y, @NotNull List<float[]> gradient, float speed2, float offset2) {
        Intrinsics.checkNotNullParameter(gradient, "gradient");
        if (enable) {
            GradientShader gradientShader = INSTANCE;
            strengthX = x;
            GradientShader gradientShader2 = INSTANCE;
            strengthY = y;
            GradientShader gradientShader3 = INSTANCE;
            maxColors = gradient.size();
            GradientShader gradientShader4 = INSTANCE;
            List<float[]> $this$toTypedArray$iv = gradient;
            colors = (float[][]) $this$toTypedArray$iv.toArray((Object[]) new float[0]);
            GradientShader gradientShader5 = INSTANCE;
            speed = speed2;
            GradientShader gradientShader6 = INSTANCE;
            offset = offset2;
            INSTANCE.startShader();
        }
        return INSTANCE;
    }
}
