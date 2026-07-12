package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Symbol.kt */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\b\b��\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u001e\u0010\u0007\u001a\u0002H\b\"\u0004\b��\u0010\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0086\b¢\u0006\u0002\u0010\nR\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/internal/Symbol;", "", "symbol", "", Constants.CTOR, "(Ljava/lang/String;)V", "toString", "unbox", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: target.jar:kotlinx/coroutines/internal/Symbol.class */
public final class Symbol {

    @JvmField
    @NotNull
    public final String symbol;

    public Symbol(@NotNull String symbol) {
        this.symbol = symbol;
    }

    @NotNull
    public String toString() {
        return '<' + this.symbol + '>';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T unbox(@Nullable Object obj) {
        if (obj == this) {
            return null;
        }
        return obj;
    }
}
