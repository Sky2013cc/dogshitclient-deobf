package kotlin.jvm.internal;

import kotlin.Function;
import kotlin.SinceKotlin;

@SinceKotlin(version = "1.4")
/* loaded from: target.jar:kotlin/jvm/internal/FunctionAdapter.class */
public interface FunctionAdapter {
    Function<?> getFunctionDelegate();
}
