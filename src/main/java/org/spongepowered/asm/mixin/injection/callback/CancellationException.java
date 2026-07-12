package org.spongepowered.asm.mixin.injection.callback;

/* loaded from: target.jar:org/spongepowered/asm/mixin/injection/callback/CancellationException.class */
public class CancellationException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public CancellationException() {
    }

    public CancellationException(String message) {
        super(message);
    }

    public CancellationException(Throwable cause) {
        super(cause);
    }

    public CancellationException(String message, Throwable cause) {
        super(message, cause);
    }
}
