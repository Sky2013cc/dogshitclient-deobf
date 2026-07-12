package net.ccbluex.liquidbounce.api;

import com.google.gson.annotations.SerializedName;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ResponseTypes.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u000bHÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003JY\u0010%\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0010R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0010R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0017¨\u0006,"}, d2 = {"Lnet/ccbluex/liquidbounce/api/AutoSettings;", "", "settingId", "", "name", Constants.ATTR_TYPE, "Lnet/ccbluex/liquidbounce/api/AutoSettingsType;", "description", "date", "contributors", "statusType", "Lnet/ccbluex/liquidbounce/api/AutoSettingsStatusType;", "statusDate", org.spongepowered.asm.util.Constants.CTOR, "(Ljava/lang/String;Ljava/lang/String;Lnet/ccbluex/liquidbounce/api/AutoSettingsType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lnet/ccbluex/liquidbounce/api/AutoSettingsStatusType;Ljava/lang/String;)V", "getSettingId", "()Ljava/lang/String;", "getName", "getType", "()Lnet/ccbluex/liquidbounce/api/AutoSettingsType;", "getDescription", "getDate", "setDate", "(Ljava/lang/String;)V", "getContributors", "getStatusType", "()Lnet/ccbluex/liquidbounce/api/AutoSettingsStatusType;", "getStatusDate", "setStatusDate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/api/AutoSettings.class */
public final class AutoSettings {

    @SerializedName("setting_id")
    @NotNull
    private final String settingId;

    @NotNull
    private final String name;

    @SerializedName("setting_type")
    @NotNull
    private final AutoSettingsType type;

    @NotNull
    private final String description;

    @NotNull
    private String date;

    @NotNull
    private final String contributors;

    @SerializedName("status_type")
    @NotNull
    private final AutoSettingsStatusType statusType;

    @SerializedName("status_date")
    @NotNull
    private String statusDate;

    @NotNull
    public final String component1() {
        return this.settingId;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final AutoSettingsType component3() {
        return this.type;
    }

    @NotNull
    public final String component4() {
        return this.description;
    }

    @NotNull
    public final String component5() {
        return this.date;
    }

    @NotNull
    public final String component6() {
        return this.contributors;
    }

    @NotNull
    public final AutoSettingsStatusType component7() {
        return this.statusType;
    }

    @NotNull
    public final String component8() {
        return this.statusDate;
    }

    @NotNull
    public final AutoSettings copy(@NotNull String settingId, @NotNull String name, @NotNull AutoSettingsType type, @NotNull String description, @NotNull String date, @NotNull String contributors, @NotNull AutoSettingsStatusType statusType, @NotNull String statusDate) {
        Intrinsics.checkNotNullParameter(settingId, "settingId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(contributors, "contributors");
        Intrinsics.checkNotNullParameter(statusType, "statusType");
        Intrinsics.checkNotNullParameter(statusDate, "statusDate");
        return new AutoSettings(settingId, name, type, description, date, contributors, statusType, statusDate);
    }

    public static /* synthetic */ AutoSettings copy$default(AutoSettings autoSettings, String str, String str2, AutoSettingsType autoSettingsType, String str3, String str4, String str5, AutoSettingsStatusType autoSettingsStatusType, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = autoSettings.settingId;
        }
        if ((i & 2) != 0) {
            str2 = autoSettings.name;
        }
        if ((i & 4) != 0) {
            autoSettingsType = autoSettings.type;
        }
        if ((i & 8) != 0) {
            str3 = autoSettings.description;
        }
        if ((i & 16) != 0) {
            str4 = autoSettings.date;
        }
        if ((i & 32) != 0) {
            str5 = autoSettings.contributors;
        }
        if ((i & 64) != 0) {
            autoSettingsStatusType = autoSettings.statusType;
        }
        if ((i & 128) != 0) {
            str6 = autoSettings.statusDate;
        }
        return autoSettings.copy(str, str2, autoSettingsType, str3, str4, str5, autoSettingsStatusType, str6);
    }

    @NotNull
    public String toString() {
        return "AutoSettings(settingId=" + this.settingId + ", name=" + this.name + ", type=" + this.type + ", description=" + this.description + ", date=" + this.date + ", contributors=" + this.contributors + ", statusType=" + this.statusType + ", statusDate=" + this.statusDate + ')';
    }

    public int hashCode() {
        int result = this.settingId.hashCode();
        return (((((((((((((result * 31) + this.name.hashCode()) * 31) + this.type.hashCode()) * 31) + this.description.hashCode()) * 31) + this.date.hashCode()) * 31) + this.contributors.hashCode()) * 31) + this.statusType.hashCode()) * 31) + this.statusDate.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutoSettings)) {
            return false;
        }
        AutoSettings autoSettings = (AutoSettings) other;
        return Intrinsics.areEqual(this.settingId, autoSettings.settingId) && Intrinsics.areEqual(this.name, autoSettings.name) && this.type == autoSettings.type && Intrinsics.areEqual(this.description, autoSettings.description) && Intrinsics.areEqual(this.date, autoSettings.date) && Intrinsics.areEqual(this.contributors, autoSettings.contributors) && this.statusType == autoSettings.statusType && Intrinsics.areEqual(this.statusDate, autoSettings.statusDate);
    }

    public AutoSettings(@NotNull String settingId, @NotNull String name, @NotNull AutoSettingsType type, @NotNull String description, @NotNull String date, @NotNull String contributors, @NotNull AutoSettingsStatusType statusType, @NotNull String statusDate) {
        Intrinsics.checkNotNullParameter(settingId, "settingId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(contributors, "contributors");
        Intrinsics.checkNotNullParameter(statusType, "statusType");
        Intrinsics.checkNotNullParameter(statusDate, "statusDate");
        this.settingId = settingId;
        this.name = name;
        this.type = type;
        this.description = description;
        this.date = date;
        this.contributors = contributors;
        this.statusType = statusType;
        this.statusDate = statusDate;
    }

    @NotNull
    public final String getSettingId() {
        return this.settingId;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final AutoSettingsType getType() {
        return this.type;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public final String getDate() {
        return this.date;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date = str;
    }

    @NotNull
    public final String getContributors() {
        return this.contributors;
    }

    @NotNull
    public final AutoSettingsStatusType getStatusType() {
        return this.statusType;
    }

    @NotNull
    public final String getStatusDate() {
        return this.statusDate;
    }

    public final void setStatusDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.statusDate = str;
    }
}
