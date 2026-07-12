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
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n��\b\u0087\u0002\u0018��2\u00020\u0001B��¨\u0006\u0002"}, d2 = {"Lkotlinx/coroutines/InternalForInheritanceCoroutinesApi;", "", "kotlinx-coroutines-core"})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@RequiresOptIn(message = "This is a kotlinx.coroutines API that is not intended to be inherited from, as the library may handle predefined instances of this in a special manner. This will be an error in a future release. If you need to inherit from this, please describe your use case in https://github.com/Kotlin/kotlinx.coroutines/issues, so that we can provide a stable API for inheritance. ", level = RequiresOptIn.Level.WARNING)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: target.jar:kotlinx/coroutines/InternalForInheritanceCoroutinesApi.class */
public @interface InternalForInheritanceCoroutinesApi {
}
