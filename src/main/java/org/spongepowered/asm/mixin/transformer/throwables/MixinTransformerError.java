package org.spongepowered.asm.mixin.transformer.throwables;

/* loaded from: target.jar:org/spongepowered/asm/mixin/transformer/throwables/MixinTransformerError.class */
public class MixinTransformerError extends Error {
    private static final long serialVersionUID = 1;

    public MixinTransformerError(String message) {
        super(message);
    }

    public MixinTransformerError(Throwable cause) {
        super(cause);
    }

    public MixinTransformerError(String message, Throwable cause) {
        super(message, cause);
    }
}
