package org.spongepowered.asm.mixin.injection.invoke.arg;

/* loaded from: target.jar:org/spongepowered/asm/mixin/injection/invoke/arg/ArgumentCountException.class */
public class ArgumentCountException extends IllegalArgumentException {
    private static final long serialVersionUID = 1;

    public ArgumentCountException(int received, int expected, String desc) {
        super("Invalid number of arguments for setAll, received " + received + " but expected " + expected + ": " + desc);
    }
}
