package net.ccbluex.liquidbounce.event;

import com.formdev.flatlaf.FlatClientProperties;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.event.Event;
import net.ccbluex.liquidbounce.utils.client.ClientUtils;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: EventHook.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0005\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n��\u0018��*\b\b��\u0010\u0001*\u00020\u00022\u00020\u0003B7\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00028��H��¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u0015\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012¨\u0006\u001c"}, d2 = {"Lnet/ccbluex/liquidbounce/event/EventHook;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lnet/ccbluex/liquidbounce/event/Event;", "", "owner", "Lnet/ccbluex/liquidbounce/event/Listenable;", FlatClientProperties.SELECT_ALL_ON_FOCUS_POLICY_ALWAYS, "", "priority", "", "action", "Lkotlin/Function1;", "", Constants.CTOR, "(Lnet/ccbluex/liquidbounce/event/Listenable;ZBLkotlin/jvm/functions/Function1;)V", "getOwner", "()Lnet/ccbluex/liquidbounce/event/Listenable;", "getAlways", "()Z", "getPriority", "()B", "isActive", "processEvent", "event", "processEvent$haze", "(Lnet/ccbluex/liquidbounce/event/Event;)V", "toString", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/EventHook.class */
public final class EventHook<T extends Event> {

    @NotNull
    private final Listenable owner;
    private final boolean always;
    private final byte priority;

    @NotNull
    private final Function1<T, Unit> action;

    /* JADX WARN: Multi-variable type inference failed */
    public EventHook(@NotNull Listenable owner, boolean always, byte priority, @NotNull Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(action, "action");
        this.owner = owner;
        this.always = always;
        this.priority = priority;
        this.action = action;
    }

    public /* synthetic */ EventHook(Listenable listenable, boolean z, byte b, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(listenable, (i & 2) != 0 ? false : z, (i & 4) != 0 ? (byte) 0 : b, function1);
    }

    @NotNull
    public final Listenable getOwner() {
        return this.owner;
    }

    public final boolean getAlways() {
        return this.always;
    }

    public final byte getPriority() {
        return this.priority;
    }

    public final boolean isActive() {
        return this.owner.handleEvents() || this.always;
    }

    public final void processEvent$haze(@NotNull T event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (!isActive()) {
            return;
        }
        try {
            this.action.invoke(event);
        } catch (Exception e) {
            ClientUtils.INSTANCE.getLOGGER().error("Exception during processing event, owner=" + this.owner.getClass().getSimpleName() + ", event=" + event, e);
        }
    }

    @NotNull
    public String toString() {
        return "EventHook{owner=" + this.owner + ", always=" + this.always + ", priority=" + ((int) this.priority) + ", action=" + this.action + '}';
    }
}
