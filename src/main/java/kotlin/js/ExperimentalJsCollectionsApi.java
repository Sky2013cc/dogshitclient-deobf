package kotlin.js;

import com.sun.tools.internal.ws.wsdl.document.jaxws.JAXWSBindingsConstants;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.RequiresOptIn;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;

/* compiled from: JsAnnotationsH.kt */
@Target({ElementType.TYPE, ElementType.METHOD})
@SinceKotlin(version = JAXWSBindingsConstants.JAXB_BINDING_VERSION)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.FUNCTION})
@RequiresOptIn(level = RequiresOptIn.Level.WARNING)
@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@MustBeDocumented
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n��\b\u0087\u0002\u0018��2\u00020\u0001B��¨\u0006\u0002"}, d2 = {"Lkotlin/js/ExperimentalJsCollectionsApi;", "", "kotlin-stdlib"})
@Documented
/* loaded from: target.jar:kotlin/js/ExperimentalJsCollectionsApi.class */
public @interface ExperimentalJsCollectionsApi {
}
