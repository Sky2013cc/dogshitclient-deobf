package kotlin.io.path;

import java.nio.file.Path;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PathTreeWalk.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010(\n\u0002\b\u0005\b\u0002\u0018��2\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010��¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010��¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020��\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lkotlin/io/path/PathNode;", "", "path", "Ljava/nio/file/Path;", "key", "parent", Constants.CTOR, "(Ljava/nio/file/Path;Ljava/lang/Object;Lkotlin/io/path/PathNode;)V", "getPath", "()Ljava/nio/file/Path;", "getKey", "()Ljava/lang/Object;", "getParent", "()Lkotlin/io/path/PathNode;", "contentIterator", "", "getContentIterator", "()Ljava/util/Iterator;", "setContentIterator", "(Ljava/util/Iterator;)V", "kotlin-stdlib-jdk7"})
/* loaded from: target.jar:kotlin/io/path/PathNode.class */
public final class PathNode {

    @NotNull
    private final Path path;

    @Nullable
    private final Object key;

    @Nullable
    private final PathNode parent;

    @Nullable
    private Iterator<PathNode> contentIterator;

    public PathNode(@NotNull Path path, @Nullable Object key, @Nullable PathNode parent) {
        Intrinsics.checkNotNullParameter(path, "path");
        this.path = path;
        this.key = key;
        this.parent = parent;
    }

    @NotNull
    public final Path getPath() {
        return this.path;
    }

    @Nullable
    public final Object getKey() {
        return this.key;
    }

    @Nullable
    public final PathNode getParent() {
        return this.parent;
    }

    @Nullable
    public final Iterator<PathNode> getContentIterator() {
        return this.contentIterator;
    }

    public final void setContentIterator(@Nullable Iterator<PathNode> it) {
        this.contentIterator = it;
    }
}
