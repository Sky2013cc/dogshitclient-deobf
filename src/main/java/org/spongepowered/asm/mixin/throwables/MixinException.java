package org.spongepowered.asm.mixin.throwables;

/* loaded from: target.jar:org/spongepowered/asm/mixin/throwables/MixinException.class */
public class MixinException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public MixinException() {
    }

    public MixinException(String message) {
        super(message);
    }

    public MixinException(Throwable cause) {
        super(cause);
    }

    public MixinException(String message, Throwable cause) {
        super(message, cause);
    }
}
