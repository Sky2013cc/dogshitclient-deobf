package kotlin;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.experimental.ExperimentalTypeInference;

/* compiled from: Inference.kt */
@Target({ElementType.METHOD})
@SinceKotlin(version = "1.4")
@ExperimentalTypeInference
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FUNCTION})
@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n��\b\u0087\u0002\u0018��2\u00020\u0001B��¨\u0006\u0002"}, d2 = {"Lkotlin/OverloadResolutionByLambdaReturnType;", "", "kotlin-stdlib"})
/* loaded from: target.jar:kotlin/OverloadResolutionByLambdaReturnType.class */
public @interface OverloadResolutionByLambdaReturnType {
}
