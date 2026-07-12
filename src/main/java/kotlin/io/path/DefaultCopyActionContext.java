package kotlin.io.path;

import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PathRecursiveFunctions.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\bÃ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lkotlin/io/path/DefaultCopyActionContext;", "Lkotlin/io/path/CopyActionContext;", Constants.CTOR, "()V", "copyToIgnoringExistingDirectory", "Lkotlin/io/path/CopyActionResult;", "Ljava/nio/file/Path;", "target", "followLinks", "", "kotlin-stdlib-jdk7"})
@ExperimentalPathApi
/* loaded from: target.jar:kotlin/io/path/DefaultCopyActionContext.class */
public final class DefaultCopyActionContext implements CopyActionContext {

    @NotNull
    public static final DefaultCopyActionContext INSTANCE = new DefaultCopyActionContext();

    private DefaultCopyActionContext() {
    }

    @Override // kotlin.io.path.CopyActionContext
    @NotNull
    public CopyActionResult copyToIgnoringExistingDirectory(@NotNull Path $this$copyToIgnoringExistingDirectory, @NotNull Path target, boolean followLinks) {
        Intrinsics.checkNotNullParameter($this$copyToIgnoringExistingDirectory, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        LinkOption[] options = LinkFollowing.INSTANCE.toLinkOptions(followLinks);
        LinkOption[] linkOptionArr = (LinkOption[]) Arrays.copyOf(options, options.length);
        if (Files.isDirectory($this$copyToIgnoringExistingDirectory, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
            LinkOption[] linkOptionArr2 = {LinkOption.NOFOLLOW_LINKS};
            if (Files.isDirectory(target, (LinkOption[]) Arrays.copyOf(linkOptionArr2, linkOptionArr2.length))) {
                Unit unit = Unit.INSTANCE;
                return CopyActionResult.CONTINUE;
            }
        }
        CopyOption[] copyOptionArr = (CopyOption[]) Arrays.copyOf(options, options.length);
        Intrinsics.checkNotNullExpressionValue(Files.copy($this$copyToIgnoringExistingDirectory, target, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length)), "copy(...)");
        return CopyActionResult.CONTINUE;
    }
}
