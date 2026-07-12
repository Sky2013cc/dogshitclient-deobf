package net.ccbluex.liquidbounce.utils.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: GsonExtensions.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086\u0004J\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u000bH\u0086\u0004J\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\fH\u0086\u0004J\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0086\u0004J\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\rH\u0086\u0004J\u0017\u0010\u0006\u001a\u00020\u0007*\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0086\u0004J\u0006\u0010\u000e\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000f"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/io/JsonObjectBuilder;", "", Constants.CTOR, "()V", "backend", "Lcom/google/gson/JsonObject;", "to", "", "", "value", "Lcom/google/gson/JsonElement;", "", "", "", "build", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/io/JsonObjectBuilder.class */
public final class JsonObjectBuilder {

    @NotNull
    private final JsonObject backend = new JsonObject();

    public final void to(@NotNull String $this$to, @NotNull JsonElement value) {
        Intrinsics.checkNotNullParameter($this$to, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        this.backend.add($this$to, value);
    }

    public final void to(@NotNull String $this$to, char value) {
        Intrinsics.checkNotNullParameter($this$to, "<this>");
        this.backend.addProperty($this$to, Character.valueOf(value));
    }

    public final void to(@NotNull String $this$to, @NotNull Number value) {
        Intrinsics.checkNotNullParameter($this$to, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        this.backend.addProperty($this$to, value);
    }

    public final void to(@NotNull String $this$to, @NotNull String value) {
        Intrinsics.checkNotNullParameter($this$to, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        this.backend.addProperty($this$to, value);
    }

    public final void to(@NotNull String $this$to, boolean value) {
        Intrinsics.checkNotNullParameter($this$to, "<this>");
        this.backend.addProperty($this$to, Boolean.valueOf(value));
    }

    public final void to(@NotNull String $this$to, @Nullable Object value) {
        Intrinsics.checkNotNullParameter($this$to, "<this>");
        if (value != null) {
            if (!(value instanceof String)) {
                if (!(value instanceof Number)) {
                    if (!(value instanceof Boolean)) {
                        if (!(value instanceof JsonElement)) {
                            if (!(value instanceof JsonObjectBuilder)) {
                                throw new IllegalArgumentException("Unsupported type: " + value.getClass());
                            }
                            this.backend.add($this$to, ((JsonObjectBuilder) value).build());
                            return;
                        }
                        this.backend.add($this$to, (JsonElement) value);
                        return;
                    }
                    this.backend.addProperty($this$to, (Boolean) value);
                    return;
                }
                this.backend.addProperty($this$to, (Number) value);
                return;
            }
            this.backend.addProperty($this$to, (String) value);
            return;
        }
        this.backend.add($this$to, JsonNull.INSTANCE);
    }

    @NotNull
    public final JsonObject build() {
        return this.backend;
    }
}
