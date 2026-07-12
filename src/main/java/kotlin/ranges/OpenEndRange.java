package kotlin.ranges;

import com.sun.tools.doclets.internal.toolkit.util.VisibleMemberMap;
import java.lang.Comparable;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: Range.kt */
@SinceKotlin(version = "1.9")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000f\n\u0002\u0010��\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\bg\u0018��*\u000e\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028��H\u0096\u0002¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\nH\u0016R\u0012\u0010\u0004\u001a\u00028��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00028��X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u000e"}, d2 = {"Lkotlin/ranges/OpenEndRange;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "", VisibleMemberMap.STARTLEVEL, "getStart", "()Ljava/lang/Comparable;", "endExclusive", "getEndExclusive", "contains", "", "value", "(Ljava/lang/Comparable;)Z", "isEmpty", "kotlin-stdlib"})
@WasExperimental(markerClass = {ExperimentalStdlibApi.class})
/* loaded from: target.jar:kotlin/ranges/OpenEndRange.class */
public interface OpenEndRange<T extends Comparable<? super T>> {
    @NotNull
    T getStart();

    @NotNull
    T getEndExclusive();

    boolean contains(@NotNull T t);

    boolean isEmpty();

    /* compiled from: Range.kt */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:kotlin/ranges/OpenEndRange$DefaultImpls.class */
    public static final class DefaultImpls {
        public static <T extends Comparable<? super T>> boolean contains(@NotNull OpenEndRange<T> openEndRange, @NotNull T value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return value.compareTo(openEndRange.getStart()) >= 0 && value.compareTo(openEndRange.getEndExclusive()) < 0;
        }

        public static <T extends Comparable<? super T>> boolean isEmpty(@NotNull OpenEndRange<T> openEndRange) {
            return openEndRange.getStart().compareTo(openEndRange.getEndExclusive()) >= 0;
        }
    }
}
