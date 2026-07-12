package net.ccbluex.liquidbounce.utils.render.shader;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Background.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b6\u0018�� \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH$J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\u000f\u0010¨\u0006\u0011"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/shader/Background;", "", "backgroundFile", "Ljava/io/File;", Constants.CTOR, "(Ljava/io/File;)V", "getBackgroundFile", "()Ljava/io/File;", "initBackground", "", "drawBackground", "width", "", "height", "Companion", "Lnet/ccbluex/liquidbounce/utils/render/shader/ImageBackground;", "Lnet/ccbluex/liquidbounce/utils/render/shader/ShaderBackground;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/shader/Background.class */
public abstract class Background {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final File backgroundFile;

    protected abstract void initBackground();

    public abstract void drawBackground(int i, int i2);

    public /* synthetic */ Background(File backgroundFile, DefaultConstructorMarker $constructor_marker) {
        this(backgroundFile);
    }

    /* compiled from: Background.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/shader/Background$Companion;", "", Constants.CTOR, "()V", "fromFile", "Lnet/ccbluex/liquidbounce/utils/render/shader/Background;", "backgroundFile", "Ljava/io/File;", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/shader/Background$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:10:0x004a, code lost:
        
            if (r0.equals("frag") == false) goto L18;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0062, code lost:
        
            if (r0.equals("glsl") == false) goto L18;
         */
        /* JADX WARN: Code restructure failed: missing block: B:4:0x003e, code lost:
        
            if (r0.equals("shader") == false) goto L18;
         */
        /* JADX WARN: Code restructure failed: missing block: B:5:0x0076, code lost:
        
            r0 = new net.ccbluex.liquidbounce.utils.render.shader.ShaderBackground(r5);
         */
        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000f. Please report as an issue. */
        @NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Background fromFile(@NotNull File backgroundFile) {
            Intrinsics.checkNotNullParameter(backgroundFile, "backgroundFile");
            String extension = FilesKt.getExtension(backgroundFile);
            switch (extension.hashCode()) {
                case -903579675:
                    break;
                case 111145:
                    if (extension.equals("png")) {
                        ImageBackground imageBackground = new ImageBackground(backgroundFile);
                        Background it = imageBackground;
                        it.initBackground();
                        return it;
                    }
                    throw new IllegalArgumentException("Invalid background file extension");
                case 3151346:
                    break;
                case 3175934:
                    break;
                default:
                    throw new IllegalArgumentException("Invalid background file extension");
            }
        }
    }

    private Background(File backgroundFile) {
        this.backgroundFile = backgroundFile;
    }

    @NotNull
    public final File getBackgroundFile() {
        return this.backgroundFile;
    }
}
