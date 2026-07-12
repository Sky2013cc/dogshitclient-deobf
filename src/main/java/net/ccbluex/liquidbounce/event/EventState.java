package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Event.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lnet/ccbluex/liquidbounce/event/EventState;", "", "stateName", "", Constants.CTOR, "(Ljava/lang/String;ILjava/lang/String;)V", "getStateName", "()Ljava/lang/String;", "PRE", "POST", "SEND", "RECEIVE", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/EventState.class */
public enum EventState {
    PRE("PRE"),
    POST("POST"),
    SEND("SEND"),
    RECEIVE("RECEIVE");


    @NotNull
    private final String stateName;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    EventState(String stateName) {
        this.stateName = stateName;
    }

    @NotNull
    public final String getStateName() {
        return this.stateName;
    }

    @NotNull
    public static EnumEntries<EventState> getEntries() {
        return $ENTRIES;
    }
}
