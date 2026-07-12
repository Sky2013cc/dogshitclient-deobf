package net.ccbluex.liquidbounce.features.command.shortcuts;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Tokens.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lnet/ccbluex/liquidbounce/features/command/shortcuts/Literal;", "Lnet/ccbluex/liquidbounce/features/command/shortcuts/Token;", Constants.ATTRVALUE_LITERAL, "", org.spongepowered.asm.util.Constants.CTOR, "(Ljava/lang/String;)V", "getLiteral", "()Ljava/lang/String;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/command/shortcuts/Literal.class */
public final class Literal extends Token {

    @NotNull
    private final String literal;

    public Literal(@NotNull String literal) {
        Intrinsics.checkNotNullParameter(literal, "literal");
        this.literal = literal;
    }

    @NotNull
    public final String getLiteral() {
        return this.literal;
    }
}
