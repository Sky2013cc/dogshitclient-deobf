package okio;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.rmi.rmic.iiop.Constants;

/* compiled from: Path.kt */
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010��\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� .2\b\u0012\u0004\u0012\u00020��0\u0001:\u0001.B\u000f\b��\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020��H\u0096\u0002J\u0016\u0010 \u001a\u00020��2\u0006\u0010!\u001a\u00020\rH\u0087\u0002¢\u0006\u0002\b\"J\u0016\u0010 \u001a\u00020��2\u0006\u0010!\u001a\u00020\u0003H\u0087\u0002¢\u0006\u0002\b\"J\u0016\u0010 \u001a\u00020��2\u0006\u0010!\u001a\u00020��H\u0087\u0002¢\u0006\u0002\b\"J\u0013\u0010#\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010$H\u0096\u0002J\b\u0010%\u001a\u00020\u001eH\u0016J\u0006\u0010&\u001a\u00020��J\u000e\u0010'\u001a\u00020��2\u0006\u0010\u001f\u001a\u00020��J\u0018\u0010\"\u001a\u00020��2\u0006\u0010!\u001a\u00020\r2\b\b\u0002\u0010(\u001a\u00020\bJ\u0018\u0010\"\u001a\u00020��2\u0006\u0010!\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\bJ\u0018\u0010\"\u001a\u00020��2\u0006\u0010!\u001a\u00020��2\b\b\u0002\u0010(\u001a\u00020\bJ\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020,J\b\u0010-\u001a\u00020\rH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0011\u0010\n\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\tR\u0011\u0010\u000b\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0006R\u0013\u0010\u0010\u001a\u0004\u0018\u00010��8G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001a\u0004\u0018\u00010��8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00158F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u001b8G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001c¨\u0006/"}, d2 = {"Lokio/Path;", "", "bytes", "Lokio/ByteString;", "(Lokio/ByteString;)V", "getBytes$okio", "()Lokio/ByteString;", "isAbsolute", "", "()Z", "isRelative", "isRoot", "name", "", "()Ljava/lang/String;", "nameBytes", "parent", "()Lokio/Path;", "root", "getRoot", "segments", "", "getSegments", "()Ljava/util/List;", "segmentsBytes", "getSegmentsBytes", "volumeLetter", "", "()Ljava/lang/Character;", "compareTo", "", "other", "div", "child", "resolve", "equals", "", "hashCode", "normalized", "relativeTo", "normalize", "toFile", "Ljava/io/File;", "toNioPath", "Ljava/nio/file/Path;", "toString", "Companion", "okio"})
@SourceDebugExtension({"SMAP\nPath.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Path.kt\nokio/Path\n+ 2 Path.kt\nokio/internal/-Path\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,132:1\n45#2,3:133\n53#2,28:136\n59#2,22:168\n112#2:190\n117#2:191\n122#2,6:192\n139#2,5:198\n149#2:203\n154#2,25:204\n194#2:229\n199#2,11:230\n204#2,6:241\n199#2,11:247\n204#2,6:258\n228#2,36:264\n268#2:300\n282#2:301\n287#2:302\n292#2:303\n297#2:304\n1549#3:164\n1620#3,3:165\n*S KotlinDebug\n*F\n+ 1 Path.kt\nokio/Path\n*L\n44#1:133,3\n47#1:136,28\n50#1:168,22\n53#1:190\n56#1:191\n60#1:192,6\n64#1:198,5\n68#1:203\n72#1:204,25\n75#1:229\n78#1:230,11\n81#1:241,6\n87#1:247,11\n90#1:258,6\n95#1:264,36\n97#1:300\n104#1:301\n106#1:302\n108#1:303\n110#1:304\n47#1:164\n47#1:165,3\n*E\n"})
/* loaded from: target.jar:okio/Path.class */
public final class Path implements Comparable<Path> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final ByteString bytes;

    @JvmField
    @NotNull
    public static final String DIRECTORY_SEPARATOR;

    @JvmStatic
    @JvmName(name = PropertyDescriptor.GET)
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull String $this$get, boolean normalize) {
        return Companion.get($this$get, normalize);
    }

    @JvmStatic
    @JvmName(name = PropertyDescriptor.GET)
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull File $this$get, boolean normalize) {
        return Companion.get($this$get, normalize);
    }

    @JvmStatic
    @JvmName(name = PropertyDescriptor.GET)
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull java.nio.file.Path $this$get, boolean normalize) {
        return Companion.get($this$get, normalize);
    }

    @JvmStatic
    @JvmName(name = PropertyDescriptor.GET)
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull String $this$get) {
        return Companion.get($this$get);
    }

    @JvmStatic
    @JvmName(name = PropertyDescriptor.GET)
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull File $this$get) {
        return Companion.get($this$get);
    }

    @JvmStatic
    @JvmName(name = PropertyDescriptor.GET)
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull java.nio.file.Path $this$get) {
        return Companion.get($this$get);
    }

    public Path(@NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.bytes = bytes;
    }

    @NotNull
    public final ByteString getBytes$okio() {
        return this.bytes;
    }

    @Nullable
    public final Path getRoot() {
        int rootLength$iv = okio.internal.Path.access$rootLength(this);
        if (rootLength$iv == -1) {
            return null;
        }
        return new Path(getBytes$okio().substring(0, rootLength$iv));
    }

    @NotNull
    public final List<String> getSegments() {
        Iterable result$iv$iv = (List) new ArrayList();
        int segmentStart$iv$iv = okio.internal.Path.access$rootLength(this);
        if (segmentStart$iv$iv == -1) {
            segmentStart$iv$iv = 0;
        } else if (segmentStart$iv$iv < getBytes$okio().size() && getBytes$okio().getByte(segmentStart$iv$iv) == 92) {
            segmentStart$iv$iv++;
        }
        int size = getBytes$okio().size();
        for (int i$iv$iv = segmentStart$iv$iv; i$iv$iv < size; i$iv$iv++) {
            if (getBytes$okio().getByte(i$iv$iv) == 47 || getBytes$okio().getByte(i$iv$iv) == 92) {
                ((Collection) result$iv$iv).add(getBytes$okio().substring(segmentStart$iv$iv, i$iv$iv));
                segmentStart$iv$iv = i$iv$iv + 1;
            }
        }
        if (segmentStart$iv$iv < getBytes$okio().size()) {
            ((Collection) result$iv$iv).add(getBytes$okio().substring(segmentStart$iv$iv, getBytes$okio().size()));
        }
        Iterable $this$map$iv$iv = result$iv$iv;
        Collection destination$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
        for (Object item$iv$iv$iv : $this$map$iv$iv) {
            ByteString it$iv = (ByteString) item$iv$iv$iv;
            destination$iv$iv$iv.add(it$iv.utf8());
        }
        return (List) destination$iv$iv$iv;
    }

    @NotNull
    public final List<ByteString> getSegmentsBytes() {
        List result$iv = new ArrayList();
        int segmentStart$iv = okio.internal.Path.access$rootLength(this);
        if (segmentStart$iv == -1) {
            segmentStart$iv = 0;
        } else if (segmentStart$iv < getBytes$okio().size() && getBytes$okio().getByte(segmentStart$iv) == 92) {
            segmentStart$iv++;
        }
        int size = getBytes$okio().size();
        for (int i$iv = segmentStart$iv; i$iv < size; i$iv++) {
            if (getBytes$okio().getByte(i$iv) == 47 || getBytes$okio().getByte(i$iv) == 92) {
                result$iv.add(getBytes$okio().substring(segmentStart$iv, i$iv));
                segmentStart$iv = i$iv + 1;
            }
        }
        if (segmentStart$iv < getBytes$okio().size()) {
            result$iv.add(getBytes$okio().substring(segmentStart$iv, getBytes$okio().size()));
        }
        return result$iv;
    }

    public final boolean isAbsolute() {
        return okio.internal.Path.access$rootLength(this) != -1;
    }

    public final boolean isRelative() {
        return okio.internal.Path.access$rootLength(this) == -1;
    }

    @JvmName(name = "volumeLetter")
    @Nullable
    public final Character volumeLetter() {
        if (ByteString.indexOf$default(getBytes$okio(), okio.internal.Path.access$getSLASH$p(), 0, 2, (Object) null) != -1 || getBytes$okio().size() < 2 || getBytes$okio().getByte(1) != 58) {
            return null;
        }
        char c$iv = (char) getBytes$okio().getByte(0);
        if (!('a' <= c$iv ? c$iv < '{' : false)) {
            if (!('A' <= c$iv ? c$iv < '[' : false)) {
                return null;
            }
        }
        return Character.valueOf(c$iv);
    }

    @JvmName(name = "nameBytes")
    @NotNull
    public final ByteString nameBytes() {
        int lastSlash$iv = okio.internal.Path.access$getIndexOfLastSlash(this);
        if (lastSlash$iv != -1) {
            return ByteString.substring$default(getBytes$okio(), lastSlash$iv + 1, 0, 2, null);
        }
        return (volumeLetter() == null || getBytes$okio().size() != 2) ? getBytes$okio() : ByteString.EMPTY;
    }

    @JvmName(name = "name")
    @NotNull
    public final String name() {
        return nameBytes().utf8();
    }

    @JvmName(name = "parent")
    @Nullable
    public final Path parent() {
        if (Intrinsics.areEqual(getBytes$okio(), okio.internal.Path.access$getDOT$p()) || Intrinsics.areEqual(getBytes$okio(), okio.internal.Path.access$getSLASH$p()) || Intrinsics.areEqual(getBytes$okio(), okio.internal.Path.access$getBACKSLASH$p()) || okio.internal.Path.access$lastSegmentIsDotDot(this)) {
            return null;
        }
        int lastSlash$iv = okio.internal.Path.access$getIndexOfLastSlash(this);
        if (lastSlash$iv == 2 && volumeLetter() != null) {
            if (getBytes$okio().size() == 3) {
                return null;
            }
            return new Path(ByteString.substring$default(getBytes$okio(), 0, 3, 1, null));
        }
        if (lastSlash$iv == 1 && getBytes$okio().startsWith(okio.internal.Path.access$getBACKSLASH$p())) {
            return null;
        }
        if (lastSlash$iv == -1 && volumeLetter() != null) {
            if (getBytes$okio().size() == 2) {
                return null;
            }
            return new Path(ByteString.substring$default(getBytes$okio(), 0, 2, 1, null));
        }
        if (lastSlash$iv == -1) {
            return new Path(okio.internal.Path.access$getDOT$p());
        }
        if (lastSlash$iv == 0) {
            return new Path(ByteString.substring$default(getBytes$okio(), 0, 1, 1, null));
        }
        return new Path(ByteString.substring$default(getBytes$okio(), 0, lastSlash$iv, 1, null));
    }

    public final boolean isRoot() {
        return okio.internal.Path.access$rootLength(this) == getBytes$okio().size();
    }

    @JvmName(name = "resolve")
    @NotNull
    public final Path resolve(@NotNull String child) {
        Intrinsics.checkNotNullParameter(child, "child");
        Buffer child$iv$iv = new Buffer().writeUtf8(child);
        return okio.internal.Path.commonResolve(this, okio.internal.Path.toPath(child$iv$iv, false), false);
    }

    @JvmName(name = "resolve")
    @NotNull
    public final Path resolve(@NotNull ByteString child) {
        Intrinsics.checkNotNullParameter(child, "child");
        Buffer child$iv$iv = new Buffer().write(child);
        return okio.internal.Path.commonResolve(this, okio.internal.Path.toPath(child$iv$iv, false), false);
    }

    @JvmName(name = "resolve")
    @NotNull
    public final Path resolve(@NotNull Path child) {
        Intrinsics.checkNotNullParameter(child, "child");
        return okio.internal.Path.commonResolve(this, child, false);
    }

    public static /* synthetic */ Path resolve$default(Path path, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return path.resolve(str, z);
    }

    @NotNull
    public final Path resolve(@NotNull String child, boolean normalize) {
        Intrinsics.checkNotNullParameter(child, "child");
        Buffer child$iv$iv = new Buffer().writeUtf8(child);
        return okio.internal.Path.commonResolve(this, okio.internal.Path.toPath(child$iv$iv, false), normalize);
    }

    public static /* synthetic */ Path resolve$default(Path path, ByteString byteString, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return path.resolve(byteString, z);
    }

    @NotNull
    public final Path resolve(@NotNull ByteString child, boolean normalize) {
        Intrinsics.checkNotNullParameter(child, "child");
        Buffer child$iv$iv = new Buffer().write(child);
        return okio.internal.Path.commonResolve(this, okio.internal.Path.toPath(child$iv$iv, false), normalize);
    }

    public static /* synthetic */ Path resolve$default(Path path, Path path2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return path.resolve(path2, z);
    }

    @NotNull
    public final Path resolve(@NotNull Path child, boolean normalize) {
        Intrinsics.checkNotNullParameter(child, "child");
        return okio.internal.Path.commonResolve(this, child, normalize);
    }

    @NotNull
    public final Path relativeTo(@NotNull Path other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (!Intrinsics.areEqual(getRoot(), other.getRoot())) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + this + " and " + other).toString());
        }
        List thisSegments$iv = getSegmentsBytes();
        List otherSegments$iv = other.getSegmentsBytes();
        int firstNewSegmentIndex$iv = 0;
        int minSegmentsSize$iv = Math.min(thisSegments$iv.size(), otherSegments$iv.size());
        while (firstNewSegmentIndex$iv < minSegmentsSize$iv && Intrinsics.areEqual(thisSegments$iv.get(firstNewSegmentIndex$iv), otherSegments$iv.get(firstNewSegmentIndex$iv))) {
            firstNewSegmentIndex$iv++;
        }
        if (firstNewSegmentIndex$iv == minSegmentsSize$iv && getBytes$okio().size() == other.getBytes$okio().size()) {
            return Companion.get$default(Companion, Constants.NAME_SEPARATOR, false, 1, (Object) null);
        }
        if (!(otherSegments$iv.subList(firstNewSegmentIndex$iv, otherSegments$iv.size()).indexOf(okio.internal.Path.access$getDOT_DOT$p()) == -1)) {
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + this + " and " + other).toString());
        }
        Buffer buffer$iv = new Buffer();
        ByteString access$getSlash = okio.internal.Path.access$getSlash(other);
        if (access$getSlash == null) {
            access$getSlash = okio.internal.Path.access$getSlash(this);
            if (access$getSlash == null) {
                access$getSlash = okio.internal.Path.access$toSlash(DIRECTORY_SEPARATOR);
            }
        }
        ByteString slash$iv = access$getSlash;
        int size = otherSegments$iv.size();
        for (int i$iv = firstNewSegmentIndex$iv; i$iv < size; i$iv++) {
            buffer$iv.write(okio.internal.Path.access$getDOT_DOT$p());
            buffer$iv.write(slash$iv);
        }
        int size2 = thisSegments$iv.size();
        for (int i$iv2 = firstNewSegmentIndex$iv; i$iv2 < size2; i$iv2++) {
            buffer$iv.write(thisSegments$iv.get(i$iv2));
            buffer$iv.write(slash$iv);
        }
        return okio.internal.Path.toPath(buffer$iv, false);
    }

    @NotNull
    public final Path normalized() {
        return Companion.get(toString(), true);
    }

    @NotNull
    public final File toFile() {
        return new File(toString());
    }

    @NotNull
    public final java.nio.file.Path toNioPath() {
        java.nio.file.Path path = Paths.get(toString(), new String[0]);
        Intrinsics.checkNotNullExpressionValue(path, "get(...)");
        return path;
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull Path other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return getBytes$okio().compareTo(other.getBytes$okio());
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof Path) && Intrinsics.areEqual(((Path) other).getBytes$okio(), getBytes$okio());
    }

    public int hashCode() {
        return getBytes$okio().hashCode();
    }

    @NotNull
    public String toString() {
        return getBytes$okio().utf8();
    }

    /* compiled from: Path.kt */
    @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\nJ\u001b\u0010\u0005\u001a\u00020\u0006*\u00020\u000b2\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\nJ\u001b\u0010\f\u001a\u00020\u0006*\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\nR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"Lokio/Path$Companion;", "", "()V", "DIRECTORY_SEPARATOR", "", "toOkioPath", "Lokio/Path;", "Ljava/io/File;", "normalize", "", PropertyDescriptor.GET, "Ljava/nio/file/Path;", "toPath", "okio"})
    /* loaded from: target.jar:okio/Path$Companion.class */
    public static final class Companion {
        @JvmStatic
        @JvmName(name = PropertyDescriptor.GET)
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull String $this$toPath) {
            Intrinsics.checkNotNullParameter($this$toPath, "<this>");
            return get$default(this, $this$toPath, false, 1, (Object) null);
        }

        @JvmStatic
        @JvmName(name = PropertyDescriptor.GET)
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull File $this$toOkioPath) {
            Intrinsics.checkNotNullParameter($this$toOkioPath, "<this>");
            return get$default(this, $this$toOkioPath, false, 1, (Object) null);
        }

        @JvmStatic
        @JvmName(name = PropertyDescriptor.GET)
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull java.nio.file.Path $this$toOkioPath) {
            Intrinsics.checkNotNullParameter($this$toOkioPath, "<this>");
            return get$default(this, $this$toOkioPath, false, 1, (Object) null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        @JvmName(name = PropertyDescriptor.GET)
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull String $this$toPath, boolean normalize) {
            Intrinsics.checkNotNullParameter($this$toPath, "<this>");
            return okio.internal.Path.commonToPath($this$toPath, normalize);
        }

        public static /* synthetic */ Path get$default(Companion companion, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(str, z);
        }

        @JvmStatic
        @JvmName(name = PropertyDescriptor.GET)
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull File $this$toOkioPath, boolean normalize) {
            Intrinsics.checkNotNullParameter($this$toOkioPath, "<this>");
            String file = $this$toOkioPath.toString();
            Intrinsics.checkNotNullExpressionValue(file, "toString(...)");
            return get(file, normalize);
        }

        public static /* synthetic */ Path get$default(Companion companion, File file, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(file, z);
        }

        @JvmStatic
        @JvmName(name = PropertyDescriptor.GET)
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull java.nio.file.Path $this$toOkioPath, boolean normalize) {
            Intrinsics.checkNotNullParameter($this$toOkioPath, "<this>");
            return get($this$toOkioPath.toString(), normalize);
        }

        public static /* synthetic */ Path get$default(Companion companion, java.nio.file.Path path, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(path, z);
        }
    }

    static {
        String separator = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        DIRECTORY_SEPARATOR = separator;
    }
}
