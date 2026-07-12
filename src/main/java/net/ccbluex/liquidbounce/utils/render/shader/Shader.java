package net.ccbluex.liquidbounce.utils.render.shader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.utils.client.ClientUtils;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.util.Constants;

/* compiled from: Shader.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010%\n��\n\u0002\u0010\u0002\n\u0002\b\u0010\b&\u0018��2\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H&J\b\u0010\u0013\u001a\u00020\u0010H&J\u0018\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\tH\u0002J\u001d\u0010\u0017\u001a\n \u0018*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0019\u001a\u00020\tH\u0002¢\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\tJ\u000e\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0003J\u000e\u0010\u001f\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u0003R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\u000eX\u0082\u0004¢\u0006\u0002\n��¨\u0006 "}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/shader/Shader;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", "fragmentShader", "", Constants.CTOR, "(Ljava/lang/String;)V", "Ljava/io/File;", "(Ljava/io/File;)V", "value", "", "programId", "getProgramId", "()I", "uniformsMap", "", "startShader", "", "stopShader", "setupUniforms", "updateUniforms", "createShader", "shaderSource", "shaderType", "getLogInfo", "kotlin.jvm.PlatformType", OperatorName.SET_FLATNESS, "(I)Ljava/lang/String;", "setUniform", "uniformName", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_LOCATION, "setupUniform", "getUniform", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/shader/Shader.class */
public abstract class Shader implements MinecraftInstance {
    private int programId;

    @NotNull
    private final Map<String, Integer> uniformsMap;

    public abstract void setupUniforms();

    public abstract void updateUniforms();

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    public final int getProgramId() {
        return this.programId;
    }

    public Shader(@NotNull String fragmentShader) {
        Intrinsics.checkNotNullParameter(fragmentShader, "fragmentShader");
        this.uniformsMap = new LinkedHashMap();
        try {
            InputStream vertexStream = getClass().getResourceAsStream("/assets/minecraft/haze/shader/vertex.vert");
            String iOUtils = IOUtils.toString(vertexStream);
            Intrinsics.checkNotNullExpressionValue(iOUtils, "toString(...)");
            int vertexShaderID = createShader(iOUtils, 35633);
            IOUtils.closeQuietly(vertexStream);
            InputStream fragmentStream = getClass().getResourceAsStream("/assets/minecraft/haze/shader/fragment/" + fragmentShader);
            String iOUtils2 = IOUtils.toString(fragmentStream);
            Intrinsics.checkNotNullExpressionValue(iOUtils2, "toString(...)");
            int fragmentShaderID = createShader(iOUtils2, 35632);
            IOUtils.closeQuietly(fragmentStream);
            if (vertexShaderID == 0 || fragmentShaderID == 0) {
                return;
            }
            this.programId = ARBShaderObjects.glCreateProgramObjectARB();
            if (this.programId == 0) {
                return;
            }
            ARBShaderObjects.glAttachObjectARB(this.programId, vertexShaderID);
            ARBShaderObjects.glAttachObjectARB(this.programId, fragmentShaderID);
            ARBShaderObjects.glLinkProgramARB(this.programId);
            ARBShaderObjects.glValidateProgramARB(this.programId);
            ClientUtils.INSTANCE.getLOGGER().info("[Shader] Successfully loaded: " + fragmentShader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Shader(@NotNull File fragmentShader) throws IOException {
        Intrinsics.checkNotNullParameter(fragmentShader, "fragmentShader");
        this.uniformsMap = new LinkedHashMap();
        InputStream vertexStream = getClass().getResourceAsStream("/assets/minecraft/liquidbounce/shader/vertex.vert");
        String iOUtils = IOUtils.toString(vertexStream);
        Intrinsics.checkNotNullExpressionValue(iOUtils, "toString(...)");
        int vertexShaderID = createShader(iOUtils, 35633);
        IOUtils.closeQuietly(vertexStream);
        InputStream fragmentStream = Files.newInputStream(fragmentShader.toPath(), new OpenOption[0]);
        String iOUtils2 = IOUtils.toString(fragmentStream);
        Intrinsics.checkNotNullExpressionValue(iOUtils2, "toString(...)");
        int fragmentShaderID = createShader(iOUtils2, 35632);
        IOUtils.closeQuietly(fragmentStream);
        if (vertexShaderID == 0 || fragmentShaderID == 0) {
            return;
        }
        this.programId = ARBShaderObjects.glCreateProgramObjectARB();
        if (this.programId == 0) {
            return;
        }
        ARBShaderObjects.glAttachObjectARB(this.programId, vertexShaderID);
        ARBShaderObjects.glAttachObjectARB(this.programId, fragmentShaderID);
        ARBShaderObjects.glLinkProgramARB(this.programId);
        ARBShaderObjects.glValidateProgramARB(this.programId);
        ClientUtils.INSTANCE.getLOGGER().info("[Shader] Successfully loaded: " + fragmentShader.getName());
    }

    public void startShader() {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.programId);
        if (this.uniformsMap.isEmpty()) {
            setupUniforms();
        }
        updateUniforms();
    }

    public void stopShader() {
        GL20.glUseProgram(0);
        GL11.glPopMatrix();
    }

    private final int createShader(String shaderSource, int shaderType) {
        int shader = 0;
        try {
            shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
            if (shader == 0) {
                return 0;
            }
            ARBShaderObjects.glShaderSourceARB(shader, shaderSource);
            ARBShaderObjects.glCompileShaderARB(shader);
            if (ARBShaderObjects.glGetObjectParameteriARB(shader, 35713) == 0) {
                throw new RuntimeException("Error creating shader: " + getLogInfo(shader));
            }
            return shader;
        } catch (Exception e) {
            ARBShaderObjects.glDeleteObjectARB(shader);
            throw e;
        }
    }

    private final String getLogInfo(int i) {
        return ARBShaderObjects.glGetInfoLogARB(i, ARBShaderObjects.glGetObjectParameteriARB(i, 35716));
    }

    public final void setUniform(@NotNull String uniformName, int location) {
        Intrinsics.checkNotNullParameter(uniformName, "uniformName");
        this.uniformsMap.put(uniformName, Integer.valueOf(location));
    }

    public final void setupUniform(@NotNull String uniformName) {
        Intrinsics.checkNotNullParameter(uniformName, "uniformName");
        setUniform(uniformName, GL20.glGetUniformLocation(this.programId, uniformName));
    }

    public final int getUniform(@NotNull String uniformName) {
        Intrinsics.checkNotNullParameter(uniformName, "uniformName");
        Integer num = this.uniformsMap.get(uniformName);
        Intrinsics.checkNotNull(num);
        return num.intValue();
    }
}
