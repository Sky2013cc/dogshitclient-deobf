package net.ccbluex.liquidbounce.utils.io;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: GsonExtensions.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0086\u0002J\u0006\u0010\t\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\n"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/io/JsonArrayBuilder;", "", Constants.CTOR, "()V", "backend", "Lcom/google/gson/JsonArray;", "unaryPlus", "", "Lcom/google/gson/JsonElement;", "build", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/io/JsonArrayBuilder.class */
public final class JsonArrayBuilder {

    @NotNull
    private final JsonArray backend = new JsonArray();

    public final void unaryPlus(@NotNull JsonElement $this$unaryPlus) {
        Intrinsics.checkNotNullParameter($this$unaryPlus, "<this>");
        this.backend.add($this$unaryPlus);
    }

    @NotNull
    public final JsonArray build() {
        return this.backend;
    }
}
