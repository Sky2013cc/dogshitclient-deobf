package kotlinx.coroutines.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;

/* compiled from: Concurrent.kt */
@Target({ElementType.FIELD})
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n��\b\u0081\u0002\u0018��2\u00020\u0001B��¨\u0006\u0002"}, d2 = {"Lkotlinx/coroutines/internal/BenignDataRace;", "", "kotlinx-coroutines-core"})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FIELD})
@Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
/* loaded from: target.jar:kotlinx/coroutines/internal/BenignDataRace.class */
public @interface BenignDataRace {
}
