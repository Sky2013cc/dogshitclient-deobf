package org.jetbrains.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: target.jar:org/jetbrains/annotations/ApiStatus.class */
public final class ApiStatus {

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
    @Documented
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: target.jar:org/jetbrains/annotations/ApiStatus$AvailableSince.class */
    public @interface AvailableSince {
        String value();
    }

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
    @Documented
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: target.jar:org/jetbrains/annotations/ApiStatus$Experimental.class */
    public @interface Experimental {
    }

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
    @Documented
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: target.jar:org/jetbrains/annotations/ApiStatus$Internal.class */
    public @interface Internal {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: target.jar:org/jetbrains/annotations/ApiStatus$NonExtendable.class */
    public @interface NonExtendable {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: target.jar:org/jetbrains/annotations/ApiStatus$OverrideOnly.class */
    public @interface OverrideOnly {
    }

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
    @Documented
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: target.jar:org/jetbrains/annotations/ApiStatus$ScheduledForRemoval.class */
    public @interface ScheduledForRemoval {
        String inVersion() default "";
    }

    private ApiStatus() {
        throw new AssertionError("ApiStatus should not be instantiated");
    }
}
