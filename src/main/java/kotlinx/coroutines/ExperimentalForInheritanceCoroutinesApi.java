package kotlinx.coroutines;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.RequiresOptIn;
import kotlin.annotation.AnnotationTarget;

/* compiled from: Annotations.kt */
@Target({ElementType.TYPE})
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n��\b\u0087\u0002\u0018��2\u00020\u0001B��¨\u0006\u0002"}, d2 = {"Lkotlinx/coroutines/ExperimentalForInheritanceCoroutinesApi;", "", "kotlinx-coroutines-core"})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@RequiresOptIn(message = "Inheriting from this kotlinx.coroutines API is unstable. Either new methods may be added in the future, which would break the inheritance, or correctly inheriting from it requires fulfilling contracts that may change in the future.", level = RequiresOptIn.Level.WARNING)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: target.jar:kotlinx/coroutines/ExperimentalForInheritanceCoroutinesApi.class */
public @interface ExperimentalForInheritanceCoroutinesApi {
}
