package net.ccbluex.liquidbounce.utils.inventory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: ItemUtils.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007JU\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n2\u001c\b\u0002\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f¢\u0006\u0002\u0010\u0011J\u0006\u0010\u0012\u001a\u00020\u0010¨\u0006\u0013"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/inventory/ItemUtils;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", Constants.CTOR, "()V", "createItem", "Lnet/minecraft/item/ItemStack;", "itemArguments", "", "getItems", "", "", "startInclusive", "endInclusive", "itemDelay", "filter", "Lkotlin/Function2;", "", "(IILjava/lang/Integer;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "isConsumingItem", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/inventory/ItemUtils.class */
public final class ItemUtils implements MinecraftInstance {

    @NotNull
    public static final ItemUtils INSTANCE = new ItemUtils();

    private ItemUtils() {
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0052 A[Catch: Exception -> 0x00cf, TryCatch #0 {Exception -> 0x00cf, blocks: (B:3:0x0006, B:5:0x0036, B:7:0x003d, B:8:0x0045, B:10:0x0052, B:12:0x0059, B:13:0x0061, B:17:0x0088, B:19:0x00a2), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0086 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0088 A[Catch: Exception -> 0x00cf, TryCatch #0 {Exception -> 0x00cf, blocks: (B:3:0x0006, B:5:0x0036, B:7:0x003d, B:8:0x0045, B:10:0x0052, B:12:0x0059, B:13:0x0061, B:17:0x0088, B:19:0x00a2), top: B:2:0x0006 }] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final ItemStack createItem(@NotNull String itemArguments) {
        ItemStack itemStack;
        List args;
        String str;
        int i;
        String str2;
        int i2;
        Item item;
        Intrinsics.checkNotNullParameter(itemArguments, "itemArguments");
        try {
            args = StringsKt.split$default((CharSequence) StringsKt.replace$default(itemArguments, '&', (char) 167, false, 4, (Object) null), new String[]{" "}, false, 0, 6, (Object) null);
            str = (String) CollectionsKt.getOrNull(args, 1);
        } catch (Exception exception) {
            exception.printStackTrace();
            itemStack = null;
        }
        if (str != null) {
            Integer intOrNull = StringsKt.toIntOrNull(str);
            if (intOrNull != null) {
                i = intOrNull.intValue();
                int amount = i;
                str2 = (String) CollectionsKt.getOrNull(args, 2);
                if (str2 != null) {
                    Integer intOrNull2 = StringsKt.toIntOrNull(str2);
                    if (intOrNull2 != null) {
                        i2 = intOrNull2.intValue();
                        int meta = i2;
                        ResourceLocation resourceLocation = new ResourceLocation((String) args.get(0));
                        item = (Item) Item.field_150901_e.func_82594_a(resourceLocation);
                        if (item == null) {
                            return null;
                        }
                        ItemStack itemStack2 = new ItemStack(item, amount, meta);
                        if (args.size() >= 4) {
                            String nbt = CollectionsKt.joinToString$default(CollectionsKt.drop(args, 3), " ", null, null, 0, null, null, 62, null);
                            itemStack2.func_77982_d(JsonToNBT.func_180713_a(nbt));
                        }
                        itemStack = itemStack2;
                        return itemStack;
                    }
                }
                i2 = 0;
                int meta2 = i2;
                ResourceLocation resourceLocation2 = new ResourceLocation((String) args.get(0));
                item = (Item) Item.field_150901_e.func_82594_a(resourceLocation2);
                if (item == null) {
                }
            }
        }
        i = 1;
        int amount2 = i;
        str2 = (String) CollectionsKt.getOrNull(args, 2);
        if (str2 != null) {
        }
        i2 = 0;
        int meta22 = i2;
        ResourceLocation resourceLocation22 = new ResourceLocation((String) args.get(0));
        item = (Item) Item.field_150901_e.func_82594_a(resourceLocation22);
        if (item == null) {
        }
    }

    public static /* synthetic */ Map getItems$default(ItemUtils itemUtils, int i, int i2, Integer num, Function2 function2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = 44;
        }
        if ((i3 & 4) != 0) {
            num = null;
        }
        if ((i3 & 8) != 0) {
            function2 = null;
        }
        return itemUtils.getItems(i, i2, num, function2);
    }

    @NotNull
    public final Map<Integer, ItemStack> getItems(int startInclusive, int endInclusive, @Nullable Integer itemDelay, @Nullable Function2<? super ItemStack, ? super Integer, Boolean> function2) {
        boolean z;
        Map items = new LinkedHashMap();
        int i = startInclusive;
        if (i <= endInclusive) {
            while (true) {
                EntityPlayer thePlayer = getMc().field_71439_g;
                Intrinsics.checkNotNullExpressionValue(thePlayer, "thePlayer");
                ItemStack itemStack = ItemUtilsKt.inventorySlot(thePlayer, i).func_75211_c();
                if (itemStack != null && !ItemUtilsKt.isEmpty(itemStack) && (itemDelay == null || ItemUtilsKt.hasItemAgePassed(itemStack, itemDelay.intValue()))) {
                    if (function2 != null) {
                        z = !function2.invoke(itemStack, Integer.valueOf(i)).booleanValue();
                    } else {
                        z = false;
                    }
                    if (!z) {
                        items.put(Integer.valueOf(i), itemStack);
                    }
                }
                if (i == endInclusive) {
                    break;
                }
                i++;
            }
        }
        return items;
    }

    public final boolean isConsumingItem() {
        if (!getMc().field_71439_g.func_71039_bw()) {
            return false;
        }
        Item usingItem = getMc().field_71439_g.field_71074_e.func_77973_b();
        return (usingItem instanceof ItemFood) || (usingItem instanceof ItemBucketMilk) || (usingItem instanceof ItemPotion);
    }
}
