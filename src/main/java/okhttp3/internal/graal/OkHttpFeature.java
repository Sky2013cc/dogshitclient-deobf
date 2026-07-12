package okhttp3.internal.graal;

import kotlin.Metadata;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeResourceAccess;
import org.jetbrains.annotations.Nullable;

/* compiled from: OkHttpFeature.kt */
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0017¨\u0006\u0007"}, d2 = {"Lokhttp3/internal/graal/OkHttpFeature;", "Lorg/graalvm/nativeimage/hosted/Feature;", "()V", "beforeAnalysis", "", "access", "Lorg/graalvm/nativeimage/hosted/Feature$BeforeAnalysisAccess;", "okhttp"})
/* loaded from: target.jar:okhttp3/internal/graal/OkHttpFeature.class */
public final class OkHttpFeature implements Feature {
    @IgnoreJRERequirement
    public void beforeAnalysis(@Nullable Feature.BeforeAnalysisAccess access) {
        RuntimeResourceAccess.addResource(ClassLoader.getSystemClassLoader().getUnnamedModule(), "okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz");
    }
}
