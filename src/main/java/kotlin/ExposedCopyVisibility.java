package kotlin;

import com.sun.tools.internal.ws.wsdl.document.jaxws.JAXWSBindingsConstants;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;

/* compiled from: ConsistentCopyVisibility.kt */
@Target({ElementType.TYPE})
@SinceKotlin(version = JAXWSBindingsConstants.JAXB_BINDING_VERSION)
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n��\b\u0087\u0002\u0018��2\u00020\u0001B��¨\u0006\u0002"}, d2 = {"Lkotlin/ExposedCopyVisibility;", "", "kotlin-stdlib"})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
/* loaded from: target.jar:kotlin/ExposedCopyVisibility.class */
public @interface ExposedCopyVisibility {
}
