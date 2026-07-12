package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;

/* compiled from: TestBuilders.kt */
@Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
/* loaded from: target.jar:kotlinx/coroutines/test/TestBuildersKt__TestBuildersKt$handleTimeout$activeChildren$1.class */
public final class TestBuildersKt__TestBuildersKt$handleTimeout$activeChildren$1 implements Function1<Job, Boolean> {
    public static final TestBuildersKt__TestBuildersKt$handleTimeout$activeChildren$1 INSTANCE = new TestBuildersKt__TestBuildersKt$handleTimeout$activeChildren$1();

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(Job it) {
        return Boolean.valueOf(it.isActive());
    }
}
