package net.ccbluex.liquidbounce.utils.client;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.utils.attack.RollingArrayLongBuffer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: PPSCounter.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001:\u0001\u000eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bR\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000f"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/client/PPSCounter;", "", Constants.CTOR, "()V", "TIMESTAMP_BUFFERS", "", "Lnet/ccbluex/liquidbounce/utils/attack/RollingArrayLongBuffer;", "[Lnet/ccbluex/liquidbounce/utils/attack/RollingArrayLongBuffer;", "registerType", "", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_TYPE, "Lnet/ccbluex/liquidbounce/utils/client/PPSCounter$PacketType;", "getPPS", "", "PacketType", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/PPSCounter.class */
public final class PPSCounter {

    @NotNull
    public static final PPSCounter INSTANCE = new PPSCounter();

    @NotNull
    private static final RollingArrayLongBuffer[] TIMESTAMP_BUFFERS;

    private PPSCounter() {
    }

    static {
        int size = PacketType.getEntries().size();
        RollingArrayLongBuffer[] rollingArrayLongBufferArr = new RollingArrayLongBuffer[size];
        for (int i = 0; i < size; i++) {
            rollingArrayLongBufferArr[i] = new RollingArrayLongBuffer(99999);
        }
        TIMESTAMP_BUFFERS = rollingArrayLongBufferArr;
    }

    public final void registerType(@NotNull PacketType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        TIMESTAMP_BUFFERS[type.ordinal()].add(System.currentTimeMillis());
    }

    public final int getPPS(@NotNull PacketType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return TIMESTAMP_BUFFERS[type.ordinal()].getTimestampsSince(System.currentTimeMillis() - 1000);
    }

    /* compiled from: PPSCounter.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/client/PPSCounter$PacketType;", "", Constants.CTOR, "(Ljava/lang/String;I)V", "SEND", "RECEIVED", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/client/PPSCounter$PacketType.class */
    public enum PacketType {
        SEND,
        RECEIVED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        @NotNull
        public static EnumEntries<PacketType> getEntries() {
            return $ENTRIES;
        }
    }
}
