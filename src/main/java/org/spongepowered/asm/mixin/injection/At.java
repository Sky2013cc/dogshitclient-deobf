package org.spongepowered.asm.mixin.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/* loaded from: target.jar:org/spongepowered/asm/mixin/injection/At.class */
public @interface At {

    /* loaded from: target.jar:org/spongepowered/asm/mixin/injection/At$Shift.class */
    public enum Shift {
        NONE,
        BEFORE,
        AFTER,
        BY
    }

    String id() default "";

    String value();

    String slice() default "";

    Shift shift() default Shift.NONE;

    int by() default 0;

    String[] args() default {};

    String target() default "";

    int ordinal() default -1;

    int opcode() default -1;

    boolean remap() default true;
}
