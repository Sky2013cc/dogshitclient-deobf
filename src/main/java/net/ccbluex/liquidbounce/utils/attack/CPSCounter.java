package net.ccbluex.liquidbounce.utils.attack;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.utils.client.ClientUtils;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: CPSCounter.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018��2\u00020\u0001:\u0001\u0010B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n��R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0011"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/attack/CPSCounter;", "", Constants.CTOR, "()V", "MAX_CPS", "", "TIMESTAMP_BUFFERS", "", "Lnet/ccbluex/liquidbounce/utils/attack/RollingArrayLongBuffer;", "[Lnet/ccbluex/liquidbounce/utils/attack/RollingArrayLongBuffer;", "registerClick", "", "button", "Lnet/ccbluex/liquidbounce/utils/attack/CPSCounter$MouseButton;", "getCPS", "timeStampsSince", "MouseButton", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/attack/CPSCounter.class */
public final class CPSCounter {

    @NotNull
    public static final CPSCounter INSTANCE = new CPSCounter();
    private static final int MAX_CPS = 50;

    @NotNull
    private static final RollingArrayLongBuffer[] TIMESTAMP_BUFFERS;

    private CPSCounter() {
    }

    static {
        int size = MouseButton.getEntries().size();
        RollingArrayLongBuffer[] rollingArrayLongBufferArr = new RollingArrayLongBuffer[size];
        for (int i = 0; i < size; i++) {
            rollingArrayLongBufferArr[i] = new RollingArrayLongBuffer(50);
        }
        TIMESTAMP_BUFFERS = rollingArrayLongBufferArr;
    }

    public final void registerClick(@NotNull MouseButton button) {
        Intrinsics.checkNotNullParameter(button, "button");
        TIMESTAMP_BUFFERS[button.ordinal()].add(ClientUtils.INSTANCE.getRunTimeTicks());
    }

    public static /* synthetic */ int getCPS$default(CPSCounter cPSCounter, MouseButton mouseButton, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = ClientUtils.INSTANCE.getRunTimeTicks() - 20;
        }
        return cPSCounter.getCPS(mouseButton, i);
    }

    public final int getCPS(@NotNull MouseButton button, int timeStampsSince) {
        Intrinsics.checkNotNullParameter(button, "button");
        return TIMESTAMP_BUFFERS[button.ordinal()].getTimestampsSince(timeStampsSince);
    }

    /* compiled from: CPSCounter.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/attack/CPSCounter$MouseButton;", "", Constants.CTOR, "(Ljava/lang/String;I)V", "LEFT", "MIDDLE", "RIGHT", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/attack/CPSCounter$MouseButton.class */
    public enum MouseButton {
        LEFT,
        MIDDLE,
        RIGHT;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        @NotNull
        public static EnumEntries<MouseButton> getEntries() {
            return $ENTRIES;
        }
    }
}
