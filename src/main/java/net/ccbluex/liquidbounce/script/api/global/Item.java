package net.ccbluex.liquidbounce.script.api.global;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.utils.inventory.ItemUtils;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Item.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lnet/ccbluex/liquidbounce/script/api/global/Item;", "", Constants.CTOR, "()V", sun.rmi.rmic.iiop.Constants.IDL_CONSTRUCTOR, "Lnet/minecraft/item/ItemStack;", "itemArguments", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/script/api/global/Item.class */
public final class Item {

    @NotNull
    public static final Item INSTANCE = new Item();

    private Item() {
    }

    @JvmStatic
    @Nullable
    public static final ItemStack create(@NotNull String itemArguments) {
        Intrinsics.checkNotNullParameter(itemArguments, "itemArguments");
        return ItemUtils.INSTANCE.createItem(itemArguments);
    }
}
