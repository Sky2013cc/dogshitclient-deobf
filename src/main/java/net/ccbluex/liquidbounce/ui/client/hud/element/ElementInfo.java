package net.ccbluex.liquidbounce.ui.client.hud.element;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

/* compiled from: Element.kt */
@Retention(RetentionPolicy.RUNTIME)
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010\u001b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0002\u0018��2\u00020\u0001B0\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\tR\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\nR\u000f\u0010\u0004\u001a\u00020\u0005¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u000bR\u000f\u0010\u0006\u001a\u00020\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u000bR\u000f\u0010\u0007\u001a\u00020\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u000bR\u000f\u0010\b\u001a\u00020\t¢\u0006\u0006\u001a\u0004\b\b\u0010\f¨\u0006\r"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/hud/element/ElementInfo;", "", "name", "", "single", "", "force", "disableScale", "priority", "", "()Ljava/lang/String;", "()Z", "()I", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/hud/element/ElementInfo.class */
public @interface ElementInfo {
    String name();

    boolean single() default false;

    boolean force() default false;

    boolean disableScale() default false;

    int priority() default 0;
}
