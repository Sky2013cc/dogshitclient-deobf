package net.ccbluex.liquidbounce.injector;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: InjectorGUI.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lnet/ccbluex/liquidbounce/injector/ProcessInfo;", "", "pid", "", "name", "", "displayName", Constants.CTOR, "(JLjava/lang/String;Ljava/lang/String;)V", "getPid", "()J", "getName", "()Ljava/lang/String;", "getDisplayName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/injector/ProcessInfo.class */
public final class ProcessInfo {
    private final long pid;

    @NotNull
    private final String name;

    @NotNull
    private final String displayName;

    public final long component1() {
        return this.pid;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.displayName;
    }

    @NotNull
    public final ProcessInfo copy(long pid, @NotNull String name, @NotNull String displayName) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        return new ProcessInfo(pid, name, displayName);
    }

    public static /* synthetic */ ProcessInfo copy$default(ProcessInfo processInfo, long j, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = processInfo.pid;
        }
        if ((i & 2) != 0) {
            str = processInfo.name;
        }
        if ((i & 4) != 0) {
            str2 = processInfo.displayName;
        }
        return processInfo.copy(j, str, str2);
    }

    @NotNull
    public String toString() {
        return "ProcessInfo(pid=" + this.pid + ", name=" + this.name + ", displayName=" + this.displayName + ')';
    }

    public int hashCode() {
        int result = Long.hashCode(this.pid);
        return (((result * 31) + this.name.hashCode()) * 31) + this.displayName.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProcessInfo)) {
            return false;
        }
        ProcessInfo processInfo = (ProcessInfo) other;
        return this.pid == processInfo.pid && Intrinsics.areEqual(this.name, processInfo.name) && Intrinsics.areEqual(this.displayName, processInfo.displayName);
    }

    public ProcessInfo(long pid, @NotNull String name, @NotNull String displayName) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        this.pid = pid;
        this.name = name;
        this.displayName = displayName;
    }

    public final long getPid() {
        return this.pid;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getDisplayName() {
        return this.displayName;
    }
}
