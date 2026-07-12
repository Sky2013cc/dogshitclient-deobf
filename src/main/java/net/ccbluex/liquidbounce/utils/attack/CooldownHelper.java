package net.ccbluex.liquidbounce.utils.attack;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: CooldownHelper.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0010\u0006\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0006\u0010\f\u001a\u00020\u0007J\u0006\u0010\r\u001a\u00020\u0007J\u0006\u0010\u000e\u001a\u00020\tJ\u0006\u0010\u000f\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0010"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/attack/CooldownHelper;", "", Constants.CTOR, "()V", "lastAttackedTicks", "", "genericAttackSpeed", "", "updateGenericAttackSpeed", "", "itemStack", "Lnet/minecraft/item/ItemStack;", "getAttackCooldownProgressPerTick", "getAttackCooldownProgress", "resetLastAttackedTicks", "incrementLastAttackedTicks", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/attack/CooldownHelper.class */
public final class CooldownHelper {

    @NotNull
    public static final CooldownHelper INSTANCE = new CooldownHelper();
    private static int lastAttackedTicks;
    private static double genericAttackSpeed;

    /* compiled from: CooldownHelper.kt */
    @Metadata(mv = {2, 0, 0}, k = 3, xi = 48)
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/attack/CooldownHelper$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Item.ToolMaterial.values().length];
            try {
                iArr[Item.ToolMaterial.IRON.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Item.ToolMaterial.WOOD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Item.ToolMaterial.STONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private CooldownHelper() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void updateGenericAttackSpeed(@Nullable ItemStack itemStack) {
        double d;
        Item func_77973_b = itemStack != null ? itemStack.func_77973_b() : null;
        if (func_77973_b instanceof ItemSword) {
            d = 1.6d;
        } else if (func_77973_b instanceof ItemAxe) {
            ItemAxe func_77973_b2 = itemStack.func_77973_b();
            Intrinsics.checkNotNull(func_77973_b2, "null cannot be cast to non-null type net.minecraft.item.ItemAxe");
            ItemAxe axe = func_77973_b2;
            Item.ToolMaterial func_150913_i = axe.func_150913_i();
            switch (func_150913_i == null ? -1 : WhenMappings.$EnumSwitchMapping$0[func_150913_i.ordinal()]) {
                case 1:
                    d = 0.9d;
                    break;
                case 2:
                case 3:
                    d = 0.8d;
                    break;
                default:
                    d = 1.0d;
                    break;
            }
        } else if (func_77973_b instanceof ItemPickaxe) {
            d = 1.2d;
        } else if (func_77973_b instanceof ItemSpade) {
            d = 1.0d;
        } else if (func_77973_b instanceof ItemHoe) {
            ItemHoe func_77973_b3 = itemStack.func_77973_b();
            Intrinsics.checkNotNull(func_77973_b3, "null cannot be cast to non-null type net.minecraft.item.ItemHoe");
            ItemHoe hoe = func_77973_b3;
            String func_77842_f = hoe.func_77842_f();
            if (func_77842_f != null) {
                switch (func_77842_f.hashCode()) {
                    case -1921929932:
                        if (func_77842_f.equals("DIAMOND")) {
                            d = 4.0d;
                            break;
                        }
                        break;
                    case 2256072:
                        if (func_77842_f.equals("IRON")) {
                            d = 3.0d;
                            break;
                        }
                        break;
                    case 79233093:
                        if (func_77842_f.equals("STONE")) {
                            d = 2.0d;
                            break;
                        }
                        break;
                }
            }
            d = 1.0d;
        } else {
            d = 4.0d;
        }
        genericAttackSpeed = d;
        if (MinecraftInstance.mc.field_71439_g.func_70644_a(Potion.field_76419_f)) {
            genericAttackSpeed *= 1.0d - Math.min(1.0d, 0.1d * (MinecraftInstance.mc.field_71439_g.func_70660_b(Potion.field_76419_f).func_76458_c() + 1));
        }
        if (MinecraftInstance.mc.field_71439_g.func_70644_a(Potion.field_76422_e)) {
            genericAttackSpeed *= 1.0d + (0.1d * (MinecraftInstance.mc.field_71439_g.func_70660_b(Potion.field_76422_e).func_76458_c() + 1));
        }
    }

    public final double getAttackCooldownProgressPerTick() {
        return (1.0d / genericAttackSpeed) * 20.0d;
    }

    public final double getAttackCooldownProgress() {
        return MathHelper.func_151237_a((lastAttackedTicks + MinecraftInstance.mc.field_71428_T.field_74281_c) / getAttackCooldownProgressPerTick(), 0.0d, 1.0d);
    }

    public final void resetLastAttackedTicks() {
        lastAttackedTicks = 0;
    }

    public final void incrementLastAttackedTicks() {
        lastAttackedTicks++;
    }
}
