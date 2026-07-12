package kotlin.collections;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: Grouping.kt */
@SinceKotlin(version = "1.1")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0010(\n\u0002\b\u0004\bg\u0018��*\u0004\b��\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u00020\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005H&J\u0015\u0010\u0006\u001a\u00028\u00012\u0006\u0010\u0007\u001a\u00028��H&¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lkotlin/collections/Grouping;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, OperatorName.STROKING_COLOR_CMYK, "", "sourceIterator", "", "keyOf", Constants.ATTR_ELEMENT, "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/collections/Grouping.class */
public interface Grouping<T, K> {
    @NotNull
    Iterator<T> sourceIterator();

    K keyOf(T t);
}
