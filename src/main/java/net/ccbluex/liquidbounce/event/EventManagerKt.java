package net.ccbluex.liquidbounce.event;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: EventManager.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��,\n��\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\u001a\"\u0010��\u001a\u00020\u0001*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u00022\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002\u001aF\u0010\u0005\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\u0012\u0004\u0012\u0002H\t0\u0006\"\b\b��\u0010\t*\u00020\n2\u001a\u0010\u000b\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\u0012\u0004\u0012\u0002H\t0\fH\u0080\bø\u0001��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"findIndexByPriority", "", "", "Lnet/ccbluex/liquidbounce/event/EventHook;", "item", "createEventMap", "", Constants.CLASS, "Lnet/ccbluex/liquidbounce/event/Event;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "valueSelector", "Lkotlin/Function1;", "haze"})
@SourceDebugExtension({"SMAP\nEventManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventManager.kt\nnet/ccbluex/liquidbounce/event/EventManagerKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,100:1\n9469#2,4:101\n*S KotlinDebug\n*F\n+ 1 EventManager.kt\nnet/ccbluex/liquidbounce/event/EventManagerKt\n*L\n37#1:101,4\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/event/EventManagerKt.class */
public final class EventManagerKt {
    public static final /* synthetic */ int access$findIndexByPriority(List $receiver, EventHook item) {
        return findIndexByPriority($receiver, item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int findIndexByPriority(List<? extends EventHook<?>> list, EventHook<?> eventHook) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            EventHook midVal = list.get(mid);
            if (eventHook.getPriority() < midVal.getPriority()) {
                low = mid + 1;
            } else if (eventHook.getPriority() > midVal.getPriority()) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return low ^ (-1);
    }

    @NotNull
    public static final <T> Map<Class<? extends Event>, T> createEventMap(@NotNull Function1<? super Class<? extends Event>, ? extends T> valueSelector) {
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        Class<? extends Event>[] all_event_classes = EventsKt.getALL_EVENT_CLASSES();
        Map destination$iv = new IdentityHashMap(EventsKt.getALL_EVENT_CLASSES().length);
        for (Class<? extends Event> cls : all_event_classes) {
            destination$iv.put(cls, valueSelector.invoke(cls));
        }
        return destination$iv;
    }
}
