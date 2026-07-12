package org.jetbrains.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.intellij.lang.annotations.Language;

/* loaded from: target.jar:org/jetbrains/annotations/Debug.class */
public final class Debug {

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: target.jar:org/jetbrains/annotations/Debug$Renderer.class */
    public @interface Renderer {
        @Language(value = "JAVA", prefix = "class Renderer{String $text(){return ", suffix = ";}}")
        @NonNls
        String text() default "";

        @Language(value = "JAVA", prefix = "class Renderer{Object[] $childrenArray(){return ", suffix = ";}}")
        @NonNls
        String childrenArray() default "";

        @Language(value = "JAVA", prefix = "class Renderer{boolean $hasChildren(){return ", suffix = ";}}")
        @NonNls
        String hasChildren() default "";
    }

    private Debug() {
        throw new AssertionError("Debug should not be instantiated");
    }
}
