package net.ccbluex.liquidbounce.script.api.global;

import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Setting.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\u001e\n��\n\u0002\u0010\b\n\u0002\u0010��\n��\n\u0002\u0010\u0007\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\u001a\f\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0002\u001a\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0082\u0002¨\u0006\t"}, d2 = {"toInt", "", "", "toFloat", "", PropertyDescriptor.GET, "Ljdk/nashorn/api/scripting/JSObject;", "key", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/script/api/global/SettingKt.class */
public final class SettingKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int toInt(Object $this$toInt) {
        Intrinsics.checkNotNull($this$toInt, "null cannot be cast to non-null type kotlin.Number");
        return ((Number) $this$toInt).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float toFloat(Object $this$toFloat) {
        Intrinsics.checkNotNull($this$toFloat, "null cannot be cast to non-null type kotlin.Number");
        return ((Number) $this$toFloat).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object get(JSObject $this$get, String key) {
        Intrinsics.checkNotNullParameter($this$get, "<this>");
        if ($this$get.hasMember(key)) {
            return $this$get.getMember(key);
        }
        return null;
    }
}
