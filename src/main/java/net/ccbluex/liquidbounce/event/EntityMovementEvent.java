package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Events.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lnet/ccbluex/liquidbounce/event/EntityMovementEvent;", "Lnet/ccbluex/liquidbounce/event/Event;", "movedEntity", "Lnet/minecraft/entity/Entity;", Constants.CTOR, "(Lnet/minecraft/entity/Entity;)V", "getMovedEntity", "()Lnet/minecraft/entity/Entity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/EntityMovementEvent.class */
public final class EntityMovementEvent extends Event {

    @NotNull
    private final Entity movedEntity;

    @NotNull
    public final Entity component1() {
        return this.movedEntity;
    }

    @NotNull
    public final EntityMovementEvent copy(@NotNull Entity movedEntity) {
        Intrinsics.checkNotNullParameter(movedEntity, "movedEntity");
        return new EntityMovementEvent(movedEntity);
    }

    public static /* synthetic */ EntityMovementEvent copy$default(EntityMovementEvent entityMovementEvent, Entity entity, int i, Object obj) {
        if ((i & 1) != 0) {
            entity = entityMovementEvent.movedEntity;
        }
        return entityMovementEvent.copy(entity);
    }

    @NotNull
    public String toString() {
        return "EntityMovementEvent(movedEntity=" + this.movedEntity + ')';
    }

    public int hashCode() {
        return this.movedEntity.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof EntityMovementEvent) && Intrinsics.areEqual(this.movedEntity, ((EntityMovementEvent) other).movedEntity);
    }

    public EntityMovementEvent(@NotNull Entity movedEntity) {
        Intrinsics.checkNotNullParameter(movedEntity, "movedEntity");
        this.movedEntity = movedEntity;
    }

    @NotNull
    public final Entity getMovedEntity() {
        return this.movedEntity;
    }
}
