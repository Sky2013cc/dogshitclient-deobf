package okio;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;

/* compiled from: ExperimentalFileSystem.kt */
@Target({ElementType.TYPE, ElementType.METHOD})
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n��\b\u0087\u0002\u0018��2\u00020\u0001B��¨\u0006\u0002"}, d2 = {"Lokio/ExperimentalFileSystem;", "", "okio"})
@Deprecated(message = "This annotation is obsolete and should be removed.", level = DeprecationLevel.HIDDEN)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY})
@Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
/* loaded from: target.jar:okio/ExperimentalFileSystem.class */
public @interface ExperimentalFileSystem {
}
