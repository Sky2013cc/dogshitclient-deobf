package net.ccbluex.liquidbounce.ui.client;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: GuiContributors.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018��2\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/GitHubContributor;", "", "totalContributions", "", "weeks", "", "Lnet/ccbluex/liquidbounce/ui/client/GitHubWeek;", "author", "Lnet/ccbluex/liquidbounce/ui/client/GitHubAuthor;", Constants.CTOR, "(ILjava/util/List;Lnet/ccbluex/liquidbounce/ui/client/GitHubAuthor;)V", "getTotalContributions", "()I", "getWeeks", "()Ljava/util/List;", "getAuthor", "()Lnet/ccbluex/liquidbounce/ui/client/GitHubAuthor;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/GitHubContributor.class */
final class GitHubContributor {

    @SerializedName("total")
    private final int totalContributions;

    @NotNull
    private final List<GitHubWeek> weeks;

    @Nullable
    private final GitHubAuthor author;

    public GitHubContributor(int totalContributions, @NotNull List<GitHubWeek> weeks, @Nullable GitHubAuthor author) {
        Intrinsics.checkNotNullParameter(weeks, "weeks");
        this.totalContributions = totalContributions;
        this.weeks = weeks;
        this.author = author;
    }

    public final int getTotalContributions() {
        return this.totalContributions;
    }

    @NotNull
    public final List<GitHubWeek> getWeeks() {
        return this.weeks;
    }

    @Nullable
    public final GitHubAuthor getAuthor() {
        return this.author;
    }
}
