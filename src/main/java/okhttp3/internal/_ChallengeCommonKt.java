package okhttp3.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Challenge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: -ChallengeCommon.kt */
@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"��\u001e\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\u001a\u0014\u0010��\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0002\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\u0002¨\u0006\t"}, d2 = {"commonEquals", "", "Lokhttp3/Challenge;", "other", "", "commonHashCode", "", "commonToString", "", "okhttp"})
/* loaded from: target.jar:okhttp3/internal/_ChallengeCommonKt.class */
public final class _ChallengeCommonKt {
    public static final boolean commonEquals(@NotNull Challenge $this$commonEquals, @Nullable Object other) {
        Intrinsics.checkNotNullParameter($this$commonEquals, "<this>");
        return (other instanceof Challenge) && Intrinsics.areEqual(((Challenge) other).scheme(), $this$commonEquals.scheme()) && Intrinsics.areEqual(((Challenge) other).authParams(), $this$commonEquals.authParams());
    }

    public static final int commonHashCode(@NotNull Challenge $this$commonHashCode) {
        Intrinsics.checkNotNullParameter($this$commonHashCode, "<this>");
        int result = (31 * 29) + $this$commonHashCode.scheme().hashCode();
        return (31 * result) + $this$commonHashCode.authParams().hashCode();
    }

    @NotNull
    public static final String commonToString(@NotNull Challenge $this$commonToString) {
        Intrinsics.checkNotNullParameter($this$commonToString, "<this>");
        return $this$commonToString.scheme() + " authParams=" + $this$commonToString.authParams();
    }
}
