package org.intellij.lang.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NonNls;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: target.jar:org/intellij/lang/annotations/MagicConstant.class */
public @interface MagicConstant {
    long[] intValues() default {};

    @NonNls
    String[] stringValues() default {};

    long[] flags() default {};

    Class<?> valuesFromClass() default void.class;

    Class<?> flagsFromClass() default void.class;
}
