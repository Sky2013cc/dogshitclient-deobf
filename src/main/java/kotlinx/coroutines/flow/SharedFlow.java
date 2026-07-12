package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.SubclassOptInRequired;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SharedFlow.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0001\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018��*\u0006\b��\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u001c\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028��0\nH¦@¢\u0006\u0002\u0010\u000bR\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/flow/SharedFlow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/Flow;", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
@SubclassOptInRequired(markerClass = {ExperimentalForInheritanceCoroutinesApi.class})
/* loaded from: target.jar:kotlinx/coroutines/flow/SharedFlow.class */
public interface SharedFlow<T> extends Flow<T> {
    @NotNull
    List<T> getReplayCache();

    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<?> continuation);
}
