package net.ccbluex.liquidbounce.utils.client;

import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.event.Listenable;
import net.minecraft.entity.Entity;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* compiled from: EntityLookup.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\u001a)\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b��\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a9\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b��\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b��\u0012\u0002H\u00020\bH\u0086\b¨\u0006\t"}, d2 = {"EntityLookup", "Lnet/ccbluex/liquidbounce/utils/client/EntityLookup;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lnet/minecraft/entity/Entity;", "Lnet/ccbluex/liquidbounce/event/Listenable;", "updateCycle", "", "predicate", "Ljava/util/function/Predicate;", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/EntityLookupKt.class */
public final class EntityLookupKt {
    public static /* synthetic */ EntityLookup EntityLookup$default(Listenable $this$EntityLookup_u24default, int updateCycle, int i, Object obj) {
        if ((i & 1) != 0) {
            updateCycle = 1;
        }
        Intrinsics.checkNotNullParameter($this$EntityLookup_u24default, "<this>");
        Intrinsics.reifiedOperationMarker(4, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
        return new EntityLookup($this$EntityLookup_u24default, Entity.class, updateCycle, null, 8, null);
    }

    public static final /* synthetic */ <T extends Entity> EntityLookup<T> EntityLookup(Listenable $this$EntityLookup, int updateCycle) {
        Intrinsics.checkNotNullParameter($this$EntityLookup, "<this>");
        Intrinsics.reifiedOperationMarker(4, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
        return new EntityLookup<>($this$EntityLookup, Entity.class, updateCycle, null, 8, null);
    }

    public static /* synthetic */ EntityLookup EntityLookup$default(Listenable $this$EntityLookup_u24default, int updateCycle, Predicate predicate, int i, Object obj) {
        if ((i & 1) != 0) {
            updateCycle = 1;
        }
        Intrinsics.checkNotNullParameter($this$EntityLookup_u24default, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Intrinsics.reifiedOperationMarker(4, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
        return new EntityLookup($this$EntityLookup_u24default, Entity.class, updateCycle, new Predicate[]{predicate});
    }

    public static final /* synthetic */ <T extends Entity> EntityLookup<T> EntityLookup(Listenable $this$EntityLookup, int updateCycle, Predicate<? super T> predicate) {
        Intrinsics.checkNotNullParameter($this$EntityLookup, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Intrinsics.reifiedOperationMarker(4, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
        return new EntityLookup<>($this$EntityLookup, Entity.class, updateCycle, new Predicate[]{predicate});
    }
}
