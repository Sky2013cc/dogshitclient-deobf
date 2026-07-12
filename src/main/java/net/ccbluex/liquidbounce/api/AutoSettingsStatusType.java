package net.ccbluex.liquidbounce.api;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.apache.fontbox.ttf.OpenTypeScript;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: ResponseTypes.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lnet/ccbluex/liquidbounce/api/AutoSettingsStatusType;", "", "displayName", "", Constants.CTOR, "(Ljava/lang/String;ILjava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "NOT_BYPASSING", "BYPASSING", "UNDETECTABLE", "UNKNOWN", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/api/AutoSettingsStatusType.class */
public enum AutoSettingsStatusType {
    NOT_BYPASSING("Not Bypassing"),
    BYPASSING("Bypassing"),
    UNDETECTABLE("Undetectable"),
    UNKNOWN(OpenTypeScript.UNKNOWN);


    @NotNull
    private final String displayName;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    AutoSettingsStatusType(String displayName) {
        this.displayName = displayName;
    }

    @NotNull
    public final String getDisplayName() {
        return this.displayName;
    }

    @NotNull
    public static EnumEntries<AutoSettingsStatusType> getEntries() {
        return $ENTRIES;
    }
}
