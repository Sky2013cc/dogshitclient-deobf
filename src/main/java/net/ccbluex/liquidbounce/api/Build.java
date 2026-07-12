package net.ccbluex.liquidbounce.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ResponseTypes.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b \b\u0086\b\u0018��2\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003Jc\u0010%\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u0005HÆ\u0001J\u0013\u0010&\u001a\u00020\n2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\t\u0010)\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0013R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0013R\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u0013¨\u0006*"}, d2 = {"Lnet/ccbluex/liquidbounce/api/Build;", "", "buildId", "", "commitId", "", "branch", "lbVersion", "mcVersion", "release", "", "date", "message", "url", Constants.CTOR, "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBuildId", "()I", "getCommitId", "()Ljava/lang/String;", "getBranch", "getLbVersion", "getMcVersion", "getRelease", "()Z", "getDate", "getMessage", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/api/Build.class */
public final class Build {

    @SerializedName("build_id")
    private final int buildId;

    @SerializedName("commit_id")
    @NotNull
    private final String commitId;

    @NotNull
    private final String branch;

    @SerializedName("lb_version")
    @NotNull
    private final String lbVersion;

    @SerializedName("mc_version")
    @NotNull
    private final String mcVersion;
    private final boolean release;

    @NotNull
    private final String date;

    @NotNull
    private final String message;

    @NotNull
    private final String url;

    public final int component1() {
        return this.buildId;
    }

    @NotNull
    public final String component2() {
        return this.commitId;
    }

    @NotNull
    public final String component3() {
        return this.branch;
    }

    @NotNull
    public final String component4() {
        return this.lbVersion;
    }

    @NotNull
    public final String component5() {
        return this.mcVersion;
    }

    public final boolean component6() {
        return this.release;
    }

    @NotNull
    public final String component7() {
        return this.date;
    }

    @NotNull
    public final String component8() {
        return this.message;
    }

    @NotNull
    public final String component9() {
        return this.url;
    }

    @NotNull
    public final Build copy(int buildId, @NotNull String commitId, @NotNull String branch, @NotNull String lbVersion, @NotNull String mcVersion, boolean release, @NotNull String date, @NotNull String message, @NotNull String url) {
        Intrinsics.checkNotNullParameter(commitId, "commitId");
        Intrinsics.checkNotNullParameter(branch, "branch");
        Intrinsics.checkNotNullParameter(lbVersion, "lbVersion");
        Intrinsics.checkNotNullParameter(mcVersion, "mcVersion");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(url, "url");
        return new Build(buildId, commitId, branch, lbVersion, mcVersion, release, date, message, url);
    }

    public static /* synthetic */ Build copy$default(Build build, int i, String str, String str2, String str3, String str4, boolean z, String str5, String str6, String str7, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = build.buildId;
        }
        if ((i2 & 2) != 0) {
            str = build.commitId;
        }
        if ((i2 & 4) != 0) {
            str2 = build.branch;
        }
        if ((i2 & 8) != 0) {
            str3 = build.lbVersion;
        }
        if ((i2 & 16) != 0) {
            str4 = build.mcVersion;
        }
        if ((i2 & 32) != 0) {
            z = build.release;
        }
        if ((i2 & 64) != 0) {
            str5 = build.date;
        }
        if ((i2 & 128) != 0) {
            str6 = build.message;
        }
        if ((i2 & 256) != 0) {
            str7 = build.url;
        }
        return build.copy(i, str, str2, str3, str4, z, str5, str6, str7);
    }

    @NotNull
    public String toString() {
        return "Build(buildId=" + this.buildId + ", commitId=" + this.commitId + ", branch=" + this.branch + ", lbVersion=" + this.lbVersion + ", mcVersion=" + this.mcVersion + ", release=" + this.release + ", date=" + this.date + ", message=" + this.message + ", url=" + this.url + ')';
    }

    public int hashCode() {
        int result = Integer.hashCode(this.buildId);
        return (((((((((((((((result * 31) + this.commitId.hashCode()) * 31) + this.branch.hashCode()) * 31) + this.lbVersion.hashCode()) * 31) + this.mcVersion.hashCode()) * 31) + Boolean.hashCode(this.release)) * 31) + this.date.hashCode()) * 31) + this.message.hashCode()) * 31) + this.url.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Build)) {
            return false;
        }
        Build build = (Build) other;
        return this.buildId == build.buildId && Intrinsics.areEqual(this.commitId, build.commitId) && Intrinsics.areEqual(this.branch, build.branch) && Intrinsics.areEqual(this.lbVersion, build.lbVersion) && Intrinsics.areEqual(this.mcVersion, build.mcVersion) && this.release == build.release && Intrinsics.areEqual(this.date, build.date) && Intrinsics.areEqual(this.message, build.message) && Intrinsics.areEqual(this.url, build.url);
    }

    public Build(int buildId, @NotNull String commitId, @NotNull String branch, @NotNull String lbVersion, @NotNull String mcVersion, boolean release, @NotNull String date, @NotNull String message, @NotNull String url) {
        Intrinsics.checkNotNullParameter(commitId, "commitId");
        Intrinsics.checkNotNullParameter(branch, "branch");
        Intrinsics.checkNotNullParameter(lbVersion, "lbVersion");
        Intrinsics.checkNotNullParameter(mcVersion, "mcVersion");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(url, "url");
        this.buildId = buildId;
        this.commitId = commitId;
        this.branch = branch;
        this.lbVersion = lbVersion;
        this.mcVersion = mcVersion;
        this.release = release;
        this.date = date;
        this.message = message;
        this.url = url;
    }

    public final int getBuildId() {
        return this.buildId;
    }

    @NotNull
    public final String getCommitId() {
        return this.commitId;
    }

    @NotNull
    public final String getBranch() {
        return this.branch;
    }

    @NotNull
    public final String getLbVersion() {
        return this.lbVersion;
    }

    @NotNull
    public final String getMcVersion() {
        return this.mcVersion;
    }

    public final boolean getRelease() {
        return this.release;
    }

    @NotNull
    public final String getDate() {
        return this.date;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }
}
