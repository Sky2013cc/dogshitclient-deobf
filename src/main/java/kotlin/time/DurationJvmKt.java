package kotlin.time;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.jetbrains.annotations.NotNull;

/* compiled from: DurationJvm.kt */
@Metadata(mv = {2, 1, 0}, k = 2, xi = 48, d1 = {"��,\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0006\n��\u001a\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH��\"\u0014\u0010��\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003\"\u001c\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u0010"}, d2 = {"durationAssertionsEnabled", "", "getDurationAssertionsEnabled", "()Z", "precisionFormats", "", "Ljava/lang/ThreadLocal;", "Ljava/text/DecimalFormat;", "[Ljava/lang/ThreadLocal;", "createFormatForDecimals", "decimals", "", "formatToExactDecimals", "", "value", "", "kotlin-stdlib"})
@SourceDebugExtension({"SMAP\nDurationJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DurationJvm.kt\nkotlin/time/DurationJvmKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,28:1\n1#2:29\n*E\n"})
/* loaded from: target.jar:kotlin/time/DurationJvmKt.class */
public final class DurationJvmKt {
    private static final boolean durationAssertionsEnabled = Duration.class.desiredAssertionStatus();

    @NotNull
    private static final ThreadLocal<DecimalFormat>[] precisionFormats;

    public static final boolean getDurationAssertionsEnabled() {
        return durationAssertionsEnabled;
    }

    static {
        ThreadLocal<DecimalFormat>[] threadLocalArr = new ThreadLocal[4];
        for (int i = 0; i < 4; i++) {
            threadLocalArr[i] = new ThreadLocal<>();
        }
        precisionFormats = threadLocalArr;
    }

    private static final DecimalFormat createFormatForDecimals(int decimals) {
        DecimalFormat $this$createFormatForDecimals_u24lambda_u240 = new DecimalFormat(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES);
        if (decimals > 0) {
            $this$createFormatForDecimals_u24lambda_u240.setMinimumFractionDigits(decimals);
        }
        $this$createFormatForDecimals_u24lambda_u240.setRoundingMode(RoundingMode.HALF_UP);
        return $this$createFormatForDecimals_u24lambda_u240;
    }

    @NotNull
    public static final String formatToExactDecimals(double value, int decimals) {
        DecimalFormat createFormatForDecimals;
        if (decimals < precisionFormats.length) {
            ThreadLocal<DecimalFormat> threadLocal = precisionFormats[decimals];
            DecimalFormat decimalFormat = threadLocal.get();
            if (decimalFormat == null) {
                DecimalFormat createFormatForDecimals2 = createFormatForDecimals(decimals);
                threadLocal.set(createFormatForDecimals2);
                decimalFormat = createFormatForDecimals2;
            }
            createFormatForDecimals = decimalFormat;
        } else {
            createFormatForDecimals = createFormatForDecimals(decimals);
        }
        DecimalFormat format = createFormatForDecimals;
        String format2 = format.format(value);
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        return format2;
    }
}
