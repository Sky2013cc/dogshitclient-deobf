package kotlin.coroutines.jvm.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.JvmName;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* compiled from: DebugMetadata.kt */
@Target({ElementType.TYPE})
@SinceKotlin(version = "1.3")
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0015\n��\n\u0002\u0010\u0011\n\u0002\b\u0011\b\u0081\u0002\u0018��2\u00020\u0001B\\\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005R\u0011\u0010\u0002\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t8G¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\t8G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\f\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\r\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0011¨\u0006\u001a"}, d2 = {"Lkotlin/coroutines/jvm/internal/DebugMetadata;", "", "version", "", "sourceFile", "", "lineNumbers", "", "localNames", "", "spilled", "indexToLabel", "methodName", "className", OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, "()I", "f", "()Ljava/lang/String;", OperatorName.LINE_TO, "()[I", OperatorName.ENDPATH, "()[Ljava/lang/String;", OperatorName.CLOSE_AND_STROKE, OperatorName.SET_FLATNESS, "m", "c", "kotlin-stdlib"})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: target.jar:kotlin/coroutines/jvm/internal/DebugMetadata.class */
public @interface DebugMetadata {
    @JvmName(name = OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT)
    int v() default 1;

    @JvmName(name = "f")
    String f() default "";

    @JvmName(name = OperatorName.LINE_TO)
    int[] l() default {};

    @JvmName(name = OperatorName.ENDPATH)
    String[] n() default {};

    @JvmName(name = OperatorName.CLOSE_AND_STROKE)
    String[] s() default {};

    @JvmName(name = OperatorName.SET_FLATNESS)
    int[] i() default {};

    @JvmName(name = "m")
    String m() default "";

    @JvmName(name = "c")
    String c() default "";
}
