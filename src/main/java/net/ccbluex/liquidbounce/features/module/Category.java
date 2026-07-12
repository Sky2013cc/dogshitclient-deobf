package net.ccbluex.liquidbounce.features.module;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Category.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0014"}, d2 = {"Lnet/ccbluex/liquidbounce/features/module/Category;", "", "displayName", "", Constants.CTOR, "(Ljava/lang/String;ILjava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "COMBAT", "PLAYER", "MOVEMENT", "RENDER", "WORLD", "MISC", "EXPLOIT", "FUN", "iconResourceLocation", "Lnet/minecraft/util/ResourceLocation;", "getIconResourceLocation", "()Lnet/minecraft/util/ResourceLocation;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/module/Category.class */
public enum Category {
    COMBAT("Combat"),
    PLAYER("Player"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    WORLD("World"),
    MISC("Misc"),
    EXPLOIT("Exploit"),
    FUN("Fun");


    @NotNull
    private final String displayName;

    @NotNull
    private final ResourceLocation iconResourceLocation;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    Category(String displayName) {
        this.displayName = displayName;
        StringBuilder sb = new StringBuilder();
        String lowerCase = LiquidBounce.CLIENT_NAME.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        StringBuilder append = sb.append(lowerCase).append("/tabgui/");
        String lowerCase2 = name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        this.iconResourceLocation = new ResourceLocation(append.append(lowerCase2).append(".png").toString());
    }

    @NotNull
    public final String getDisplayName() {
        return this.displayName;
    }

    @NotNull
    public final ResourceLocation getIconResourceLocation() {
        return this.iconResourceLocation;
    }

    @NotNull
    public static EnumEntries<Category> getEntries() {
        return $ENTRIES;
    }
}
