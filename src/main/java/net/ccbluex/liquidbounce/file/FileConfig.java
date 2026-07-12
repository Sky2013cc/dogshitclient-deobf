package net.ccbluex.liquidbounce.file;

import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: FileConfig.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\tH&J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\b\u0010\u000e\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lnet/ccbluex/liquidbounce/file/FileConfig;", "", "file", "Ljava/io/File;", Constants.CTOR, "(Ljava/io/File;)V", "getFile", "()Ljava/io/File;", "loadConfig", "", "saveConfig", "createConfig", "", "hasConfig", "loadDefault", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/file/FileConfig.class */
public abstract class FileConfig {

    @NotNull
    private final File file;

    public abstract void loadConfig() throws IOException;

    public abstract void saveConfig() throws IOException;

    public FileConfig(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        this.file = file;
    }

    @NotNull
    public final File getFile() {
        return this.file;
    }

    public final boolean createConfig() throws IOException {
        return this.file.createNewFile();
    }

    public final boolean hasConfig() {
        return this.file.exists() && this.file.length() > 0;
    }

    public void loadDefault() {
    }
}
