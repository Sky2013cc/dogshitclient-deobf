package net.ccbluex.liquidbounce.ui.client;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.spongepowered.asm.util.Constants;

/* compiled from: GuiContributors.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n��\n\u0002\u0010\b\n\u0002\b\u000b\b\u0002\u0018��2\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/GitHubWeek;", "", "timestamp", "", "additions", "", "deletions", "commits", Constants.CTOR, "(JIII)V", "getTimestamp", "()J", "getAdditions", "()I", "getDeletions", "getCommits", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/GitHubWeek.class */
final class GitHubWeek {

    @SerializedName(OperatorName.SET_LINE_WIDTH)
    private final long timestamp;

    @SerializedName("a")
    private final int additions;

    @SerializedName(OperatorName.SET_LINE_DASHPATTERN)
    private final int deletions;

    @SerializedName("c")
    private final int commits;

    public GitHubWeek(long timestamp, int additions, int deletions, int commits) {
        this.timestamp = timestamp;
        this.additions = additions;
        this.deletions = deletions;
        this.commits = commits;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final int getAdditions() {
        return this.additions;
    }

    public final int getDeletions() {
        return this.deletions;
    }

    public final int getCommits() {
        return this.commits;
    }
}
