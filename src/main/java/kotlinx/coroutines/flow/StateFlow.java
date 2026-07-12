package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.SubclassOptInRequired;
import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* compiled from: StateFlow.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u000e\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018��*\u0006\b��\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002R\u0012\u0010\u0003\u001a\u00028��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/flow/StateFlow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/SharedFlow;", "value", "getValue", "()Ljava/lang/Object;", "kotlinx-coroutines-core"})
@SubclassOptInRequired(markerClass = {ExperimentalForInheritanceCoroutinesApi.class})
/* loaded from: target.jar:kotlinx/coroutines/flow/StateFlow.class */
public interface StateFlow<T> extends SharedFlow<T> {
    T getValue();
}
