package net.ccbluex.liquidbounce.utils.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KProperty;
import net.ccbluex.liquidbounce.event.EventHook;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.Listenable;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: EntityLookup.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n��\u0018��*\b\b��\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u0004BC\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0006\b��\u0012\u00028��0\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0016\b\u0002\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b��\u0012\u00028��0\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028��0\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0086\u0002J\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028��0��2\u000e\u0010\u001d\u001a\n\u0012\u0006\b��\u0012\u00028��0\fJ\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028��0��2\u000e\u0010\u001d\u001a\n\u0012\u0006\b��\u0012\u00028��0\fJ\b\u0010\"\u001a\u00020#H\u0016R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u0006\u001a\n\u0012\u0006\b��\u0012\u00028��0\u0007X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R\u001e\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b��\u0012\u00028��0\f0\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n��R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028��0\u0012X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\u001a\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u0014\u0010\u001f\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b \u0010!¨\u0006$"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/client/EntityLookup;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lnet/minecraft/entity/Entity;", "Lnet/ccbluex/liquidbounce/event/Listenable;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", "owner", "entityClass", Constants.CLASS, "updateCycle", "", "predicates", "", "Ljava/util/function/Predicate;", Constants.CTOR, "(Lnet/ccbluex/liquidbounce/event/Listenable;Ljava/lang/Class;I[Ljava/util/function/Predicate;)V", "[Ljava/util/function/Predicate;", "ticks", "entities", "", "clear", "", "getValue", "thisRef", "", "property", "Lkotlin/reflect/KProperty;", "onUpdate", "Lkotlin/Unit;", "filter", "predicate", "filterNot", "parent", "getParent", "()Lnet/ccbluex/liquidbounce/event/Listenable;", "handleEvents", "", "haze"})
@SourceDebugExtension({"SMAP\nEntityLookup.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EntityLookup.kt\nnet/ccbluex/liquidbounce/utils/client/EntityLookup\n+ 2 Listenable.kt\nnet/ccbluex/liquidbounce/event/ListenableKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,72:1\n22#2,7:73\n774#3:80\n865#3:81\n866#3:84\n12364#4,2:82\n*S KotlinDebug\n*F\n+ 1 EntityLookup.kt\nnet/ccbluex/liquidbounce/utils/client/EntityLookup\n*L\n30#1:73,7\n39#1:80\n39#1:81\n39#1:84\n41#1:82,2\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/EntityLookup.class */
public final class EntityLookup<T extends Entity> implements Listenable, MinecraftInstance {

    @NotNull
    private final Listenable owner;

    @NotNull
    private final Class<? super T> entityClass;
    private final int updateCycle;

    @NotNull
    private Predicate<? super T>[] predicates;
    private int ticks;

    @NotNull
    private Collection<? extends T> entities;

    @NotNull
    private final Unit onUpdate;

    @NotNull
    private final Listenable parent;

    public EntityLookup(@NotNull Listenable owner, @NotNull Class<? super T> entityClass, int updateCycle, @NotNull Predicate<? super T>[] predicates) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(entityClass, "entityClass");
        Intrinsics.checkNotNullParameter(predicates, "predicates");
        this.owner = owner;
        this.entityClass = entityClass;
        this.updateCycle = updateCycle;
        this.predicates = predicates;
        this.entities = CollectionsKt.emptyList();
        EntityLookup<T> $this$handler_u24default$iv = this;
        Function1 action$iv = (v1) -> {
            return onUpdate$lambda$2(r1, v1);
        };
        EventManager.INSTANCE.registerEventHook(UpdateEvent.class, new EventHook($this$handler_u24default$iv, false, (byte) 0, action$iv));
        this.onUpdate = Unit.INSTANCE;
        this.parent = this.owner;
    }

    public /* synthetic */ EntityLookup(Listenable listenable, Class cls, int i, Predicate[] predicateArr, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(listenable, (i2 & 2) != 0 ? Entity.class : cls, (i2 & 4) != 0 ? 1 : i, (i2 & 8) != 0 ? new Predicate[0] : predicateArr);
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    @NotNull
    public Listenable[] getSubListeners() {
        return Listenable.DefaultImpls.getSubListeners(this);
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    private final void clear() {
        if (!this.entities.isEmpty()) {
            this.entities = CollectionsKt.emptyList();
        }
        this.ticks = 0;
    }

    @NotNull
    public final Collection<T> getValue(@Nullable Object obj, @NotNull KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        return this.entities;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00e8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final Unit onUpdate$lambda$2(EntityLookup this$0, UpdateEvent it) {
        ArrayList arrayList;
        Iterable iterable;
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.ticks++;
        this$0.ticks %= this$0.updateCycle;
        if (this$0.ticks != 0) {
            return Unit.INSTANCE;
        }
        EntityLookup entityLookup = this$0;
        WorldClient worldClient = this$0.getMc().field_71441_e;
        if (worldClient == null || (iterable = worldClient.field_72996_f) == null) {
            arrayList = null;
        } else {
            Iterable $this$filter$iv = iterable;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                Entity entity = (Entity) element$iv$iv;
                if (this$0.entityClass.isAssignableFrom(entity.getClass())) {
                    Predicate<? super T>[] predicateArr = this$0.predicates;
                    int i = 0;
                    int length = predicateArr.length;
                    while (true) {
                        if (i >= length) {
                            z2 = true;
                            break;
                        }
                        Predicate<? super T> predicate = predicateArr[i];
                        Intrinsics.checkNotNull(entity, "null cannot be cast to non-null type T of net.ccbluex.liquidbounce.utils.client.EntityLookup");
                        if (!predicate.test(entity)) {
                            z2 = false;
                            break;
                        }
                        i++;
                    }
                    if (z2) {
                        z = true;
                        if (!z) {
                            destination$iv$iv.add(element$iv$iv);
                        }
                    }
                }
                z = false;
                if (!z) {
                }
            }
            ArrayList arrayList2 = (List) destination$iv$iv;
            entityLookup = entityLookup;
            arrayList = arrayList2;
        }
        ArrayList arrayList3 = arrayList;
        List list = arrayList3 instanceof Collection ? arrayList3 : null;
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        entityLookup.entities = list;
        return Unit.INSTANCE;
    }

    @NotNull
    public final EntityLookup<T> filter(@NotNull Predicate<? super T> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        this.predicates = (Predicate[]) ArraysKt.plus(this.predicates, predicate);
        return this;
    }

    @NotNull
    public final EntityLookup<T> filterNot(@NotNull Predicate<? super T> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        this.predicates = (Predicate[]) ArraysKt.plus(this.predicates, predicate.negate());
        return this;
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    @NotNull
    public Listenable getParent() {
        return this.parent;
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    public boolean handleEvents() {
        boolean it = this.owner.handleEvents();
        if (!it) {
            clear();
        }
        return it;
    }
}
