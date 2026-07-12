package net.ccbluex.liquidbounce.utils.inventory;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.ccbluex.liquidbounce.event.ClientSlotChangeEvent;
import net.ccbluex.liquidbounce.event.EventHook;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.event.Listenable;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: SilentHotbar.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n��\n\u0002\u0010��\n\u0002\b\u0017\bÆ\u0002\u0018��2\u00020\u00012\u00020\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004JG\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010#\u001a\u00020\u00132\b\b\u0002\u0010$\u001a\u00020\u00132\b\b\u0002\u0010%\u001a\u00020\u0013¢\u0006\u0002\u0010&J\u001c\u0010'\u001a\u00020\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\b\b\u0002\u0010#\u001a\u00020\u0013J\u0010\u0010(\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u0006\u0010)\u001a\u00020\u001eJ\u000e\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\u0013J+\u0010,\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\f2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010.\u001a\u00020\u0013H\u0002¢\u0006\u0002\u0010/J\b\u00106\u001a\u00020\u0013H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n��R\u0012\u0010\r\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0017\u0010\u0015\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001b\u0010\u0015\"\u0004\b\u001c\u0010\u0019R\u0013\u00100\u001a\u00020\u001e¢\u0006\n\n\u0002\u00103\u001a\u0004\b1\u00102R\u0013\u00104\u001a\u00020\u001e¢\u0006\n\n\u0002\u00103\u001a\u0004\b5\u00102¨\u00067"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/inventory/SilentHotbar;", "Lnet/ccbluex/liquidbounce/event/Listenable;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", Constants.CTOR, "()V", "hotbarState", "Lnet/ccbluex/liquidbounce/utils/inventory/SilentHotbarState;", "getHotbarState", "()Lnet/ccbluex/liquidbounce/utils/inventory/SilentHotbarState;", "setHotbarState", "(Lnet/ccbluex/liquidbounce/utils/inventory/SilentHotbarState;)V", "ticksSinceLastUpdate", "", "originalSlot", "Ljava/lang/Integer;", "currentSlot", "getCurrentSlot", "()I", "modifiedThisTick", "", "getModifiedThisTick", "()Z", "ignoreSlotChange", "getIgnoreSlotChange", "setIgnoreSlotChange", "(Z)V", "pressedAtSlot", "getPressedAtSlot", "setPressedAtSlot", "selectSlotSilently", "", "requester", "", "slot", "ticksUntilReset", "immediate", "render", "resetManually", "(Ljava/lang/Object;ILjava/lang/Integer;ZZZ)V", "resetSlot", "isSlotModified", "updateSilentSlot", "renderSlot", "option", "shouldReset", "other", "keyPressCheck", "(ILjava/lang/Integer;Z)Z", "onSlotChange", "getOnSlotChange", "()Lkotlin/Unit;", "Lkotlin/Unit;", "onPacket", "getOnPacket", "handleEvents", "haze"})
@SourceDebugExtension({"SMAP\nSilentHotbar.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SilentHotbar.kt\nnet/ccbluex/liquidbounce/utils/inventory/SilentHotbar\n+ 2 Listenable.kt\nnet/ccbluex/liquidbounce/event/ListenableKt\n*L\n1#1,150:1\n22#2,7:151\n22#2,7:158\n*S KotlinDebug\n*F\n+ 1 SilentHotbar.kt\nnet/ccbluex/liquidbounce/utils/inventory/SilentHotbar\n*L\n105#1:151,7\n128#1:158,7\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/inventory/SilentHotbar.class */
public final class SilentHotbar implements Listenable, MinecraftInstance {

    @NotNull
    public static final SilentHotbar INSTANCE = new SilentHotbar();

    @Nullable
    private static SilentHotbarState hotbarState;
    private static int ticksSinceLastUpdate;

    @Nullable
    private static Integer originalSlot;
    private static boolean ignoreSlotChange;
    private static boolean pressedAtSlot;

    @NotNull
    private static final Unit onSlotChange;

    @NotNull
    private static final Unit onPacket;

    private SilentHotbar() {
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    @NotNull
    public Listenable[] getSubListeners() {
        return Listenable.DefaultImpls.getSubListeners(this);
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    @Nullable
    public Listenable getParent() {
        return Listenable.DefaultImpls.getParent(this);
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    @Nullable
    public final SilentHotbarState getHotbarState() {
        return hotbarState;
    }

    public final void setHotbarState(@Nullable SilentHotbarState silentHotbarState) {
        hotbarState = silentHotbarState;
    }

    public final int getCurrentSlot() {
        SilentHotbarState silentHotbarState = hotbarState;
        if (silentHotbarState != null) {
            return silentHotbarState.getEnforcedSlot();
        }
        EntityPlayerSP entityPlayerSP = getMc().field_71439_g;
        if (entityPlayerSP != null) {
            InventoryPlayer inventoryPlayer = entityPlayerSP.field_71071_by;
            if (inventoryPlayer != null) {
                return inventoryPlayer.field_70461_c;
            }
        }
        return 0;
    }

    public final boolean getModifiedThisTick() {
        return ticksSinceLastUpdate == 0 && hotbarState != null;
    }

    public final boolean getIgnoreSlotChange() {
        return ignoreSlotChange;
    }

    public final void setIgnoreSlotChange(boolean z) {
        ignoreSlotChange = z;
    }

    public final boolean getPressedAtSlot() {
        return pressedAtSlot;
    }

    public final void setPressedAtSlot(boolean z) {
        pressedAtSlot = z;
    }

    public static /* synthetic */ void selectSlotSilently$default(SilentHotbar silentHotbar, Object obj, int i, Integer num, boolean z, boolean z2, boolean z3, int i2, Object obj2) {
        if ((i2 & 4) != 0) {
            num = null;
        }
        if ((i2 & 8) != 0) {
            z = false;
        }
        if ((i2 & 16) != 0) {
            z2 = true;
        }
        if ((i2 & 32) != 0) {
            z3 = false;
        }
        silentHotbar.selectSlotSilently(obj, i, num, z, z2, z3);
    }

    public final void selectSlotSilently(@Nullable Object requester, int slot, @Nullable Integer ticksUntilReset, boolean immediate, boolean render, boolean resetManually) {
        int i;
        if (originalSlot == null) {
            EntityPlayerSP entityPlayerSP = getMc().field_71439_g;
            if (entityPlayerSP != null) {
                InventoryPlayer inventoryPlayer = entityPlayerSP.field_71071_by;
                if (inventoryPlayer != null) {
                    i = Integer.valueOf(inventoryPlayer.field_70461_c);
                    originalSlot = i;
                }
            }
            i = 0;
            originalSlot = i;
        }
        hotbarState = new SilentHotbarState(slot, requester, ticksUntilReset, render, resetManually);
        ticksSinceLastUpdate = 0;
        if (immediate) {
            PlayerControllerMP playerControllerMP = getMc().field_71442_b;
            if (playerControllerMP != null) {
                playerControllerMP.func_78750_j();
            }
        }
    }

    public static /* synthetic */ void resetSlot$default(SilentHotbar silentHotbar, Object obj, boolean z, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        silentHotbar.resetSlot(obj, z);
    }

    public final void resetSlot(@Nullable Object requester, boolean immediate) {
        SilentHotbarState state = hotbarState;
        if (state == null) {
            return;
        }
        if (requester == null || Intrinsics.areEqual(state.getRequester(), requester)) {
            hotbarState = null;
            originalSlot = null;
            if (requester != null && immediate) {
                PlayerControllerMP playerControllerMP = getMc().field_71442_b;
                if (playerControllerMP != null) {
                    playerControllerMP.func_78750_j();
                }
            }
        }
    }

    public final boolean isSlotModified(@Nullable Object requester) {
        SilentHotbarState silentHotbarState = hotbarState;
        return Intrinsics.areEqual(silentHotbarState != null ? silentHotbarState.getRequester() : null, requester);
    }

    public final void updateSilentSlot() {
        pressedAtSlot = false;
        SilentHotbarState hotbarState2 = hotbarState;
        if (hotbarState2 == null) {
            return;
        }
        Integer resetTicks = hotbarState2.getResetTicks();
        if (resetTicks != null) {
            int ticksUntilReset = resetTicks.intValue();
            if (ticksSinceLastUpdate >= ticksUntilReset) {
                resetSlot$default(INSTANCE, hotbarState2.getRequester(), false, 2, null);
                return;
            }
        }
        ticksSinceLastUpdate++;
    }

    public final int renderSlot(boolean option) {
        EntityPlayerSP player = getMc().field_71439_g;
        if (player == null) {
            return 0;
        }
        int original = player.field_71071_by.field_70461_c;
        SilentHotbarState state = hotbarState;
        return state == null ? original : (option || state.getRender()) ? getCurrentSlot() : original;
    }

    static /* synthetic */ boolean shouldReset$default(SilentHotbar silentHotbar, int i, Integer num, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = originalSlot;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return silentHotbar.shouldReset(i, num, z);
    }

    private final boolean shouldReset(int slot, Integer other, boolean keyPressCheck) {
        if (other == null || slot != other.intValue() || keyPressCheck) {
            SilentHotbarState silentHotbarState = hotbarState;
            if (silentHotbarState != null ? silentHotbarState.getResetManually() : false) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public final Unit getOnSlotChange() {
        return onSlotChange;
    }

    static {
        Listenable $this$handler_u24default$iv = INSTANCE;
        Function1 action$iv = SilentHotbar::onSlotChange$lambda$1;
        EventManager.INSTANCE.registerEventHook(ClientSlotChangeEvent.class, new EventHook($this$handler_u24default$iv, false, (byte) 0, action$iv));
        onSlotChange = Unit.INSTANCE;
        Listenable $this$handler_u24default$iv2 = INSTANCE;
        Function1 action$iv2 = SilentHotbar::onPacket$lambda$2;
        EventManager.INSTANCE.registerEventHook(PacketEvent.class, new EventHook($this$handler_u24default$iv2, false, (byte) 0, action$iv2));
        onPacket = Unit.INSTANCE;
    }

    private static final Unit onSlotChange$lambda$1(ClientSlotChangeEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        SilentHotbar silentHotbar = INSTANCE;
        if (ignoreSlotChange) {
            event.setModifiedSlot(event.getSupposedSlot());
            SilentHotbar silentHotbar2 = INSTANCE;
            originalSlot = null;
            SilentHotbar silentHotbar3 = INSTANCE;
            ignoreSlotChange = false;
            return Unit.INSTANCE;
        }
        if (originalSlot != null) {
            SilentHotbar silentHotbar4 = INSTANCE;
            int supposedSlot = event.getSupposedSlot();
            Integer num = originalSlot;
            SilentHotbar silentHotbar5 = INSTANCE;
            if (silentHotbar4.shouldReset(supposedSlot, num, pressedAtSlot)) {
                resetSlot$default(INSTANCE, null, false, 3, null);
                event.setModifiedSlot(event.getSupposedSlot());
            }
        }
        return Unit.INSTANCE;
    }

    @NotNull
    public final Unit getOnPacket() {
        return onPacket;
    }

    private static final Unit onPacket$lambda$2(PacketEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        C09PacketHeldItemChange packet = event.getPacket();
        if (!(packet instanceof C09PacketHeldItemChange)) {
            return Unit.INSTANCE;
        }
        if (shouldReset$default(INSTANCE, packet.func_149614_c(), Integer.valueOf(INSTANCE.getCurrentSlot()), false, 4, null)) {
            resetSlot$default(INSTANCE, null, false, 3, null);
        }
        return Unit.INSTANCE;
    }

    @Override // net.ccbluex.liquidbounce.event.Listenable
    public boolean handleEvents() {
        return (getMc().field_71439_g == null || getMc().field_71441_e == null) ? false : true;
    }
}
