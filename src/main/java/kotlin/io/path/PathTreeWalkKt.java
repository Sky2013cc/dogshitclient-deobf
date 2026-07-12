package kotlin.io.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PathTreeWalk.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��$\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\u001a%\u0010��\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002¢\u0006\u0002\u0010\u0007\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0002¨\u0006\u000b"}, d2 = {"keyOf", "", "path", "Ljava/nio/file/Path;", "linkOptions", "", "Ljava/nio/file/LinkOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/lang/Object;", "createsCycle", "", "Lkotlin/io/path/PathNode;", "kotlin-stdlib-jdk7"})
/* loaded from: target.jar:kotlin/io/path/PathTreeWalkKt.class */
public final class PathTreeWalkKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Object keyOf(Path path, LinkOption[] linkOptions) {
        Object obj;
        try {
            LinkOption[] linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions, linkOptions.length);
            BasicFileAttributes readAttributes = Files.readAttributes(path, (Class<BasicFileAttributes>) BasicFileAttributes.class, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
            Intrinsics.checkNotNullExpressionValue(readAttributes, "readAttributes(...)");
            obj = readAttributes.fileKey();
        } catch (Throwable th) {
            obj = null;
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean createsCycle(PathNode $this$createsCycle) {
        PathNode parent = $this$createsCycle.getParent();
        while (true) {
            PathNode ancestor = parent;
            if (ancestor != null) {
                if (ancestor.getKey() != null && $this$createsCycle.getKey() != null) {
                    if (Intrinsics.areEqual(ancestor.getKey(), $this$createsCycle.getKey())) {
                        return true;
                    }
                } else {
                    try {
                        if (Files.isSameFile(ancestor.getPath(), $this$createsCycle.getPath())) {
                            return true;
                        }
                    } catch (IOException e) {
                    } catch (SecurityException e2) {
                    }
                }
                parent = ancestor.getParent();
            } else {
                return false;
            }
        }
    }
}
