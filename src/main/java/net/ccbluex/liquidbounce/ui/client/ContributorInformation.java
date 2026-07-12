package net.ccbluex.liquidbounce.ui.client;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: GuiContributors.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010 \n\u0002\b\t\b\u0002\u0018��2\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/ContributorInformation;", "", "name", "", "teamMember", "", "contributions", "", Constants.CTOR, "(Ljava/lang/String;ZLjava/util/List;)V", "getName", "()Ljava/lang/String;", "getTeamMember", "()Z", "getContributions", "()Ljava/util/List;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/ContributorInformation.class */
final class ContributorInformation {

    @NotNull
    private final String name;
    private final boolean teamMember;

    @NotNull
    private final List<String> contributions;

    public ContributorInformation(@NotNull String name, boolean teamMember, @NotNull List<String> contributions) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(contributions, "contributions");
        this.name = name;
        this.teamMember = teamMember;
        this.contributions = contributions;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final boolean getTeamMember() {
        return this.teamMember;
    }

    @NotNull
    public final List<String> getContributions() {
        return this.contributions;
    }
}
