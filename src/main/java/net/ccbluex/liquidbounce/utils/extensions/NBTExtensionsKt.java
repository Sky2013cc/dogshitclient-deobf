package net.ccbluex.liquidbounce.utils.extensions;

import jdk.nashorn.internal.runtime.PropertyDescriptor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.jetbrains.annotations.NotNull;

/* compiled from: NBTExtensions.kt */
@Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"��J\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0002\u0010\u000b\n\u0002\u0010\u0012\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086\n\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007H\u0086\n\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH\u0086\n\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\tH\u0086\n\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\nH\u0086\n\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000bH\u0086\n\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086\n\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\fH\u0086\n\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\rH\u0086\n\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000eH\u0086\n\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0002H\u0086\n\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000fH\u0086\n\u001a%\u0010\u0010\u001a\u00020\u00022\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0013H\u0086\bø\u0001��\u001a%\u0010\u0014\u001a\u00020\u000f2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0013H\u0086\bø\u0001��\u001a)\u0010\u0015\u001a\u00020\u0001*\u00020\u000f2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0013H\u0086\bø\u0001��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"}, d2 = {PropertyDescriptor.SET, "", "Lnet/minecraft/nbt/NBTTagCompound;", "key", "", "value", "", "", "", "", "", "", "", "", "", "Lnet/minecraft/nbt/NBTTagList;", "NBTTagCompound", "builderAction", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "NBTTagList", "appendTag", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/extensions/NBTExtensionsKt.class */
public final class NBTExtensionsKt {
    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, byte value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        $this$set.func_74774_a(key, value);
    }

    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, short value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        $this$set.func_74777_a(key, value);
    }

    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, int value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        $this$set.func_74768_a(key, value);
    }

    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, long value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        $this$set.func_74772_a(key, value);
    }

    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, float value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        $this$set.func_74776_a(key, value);
    }

    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, double value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        $this$set.func_74780_a(key, value);
    }

    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, @NotNull String value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        $this$set.func_74778_a(key, value);
    }

    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, boolean value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        $this$set.func_74757_a(key, value);
    }

    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, @NotNull byte[] value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        $this$set.func_74773_a(key, value);
    }

    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, @NotNull int[] value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        $this$set.func_74783_a(key, value);
    }

    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, @NotNull NBTTagCompound value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        $this$set.func_74782_a(key, (NBTBase) value);
    }

    public static final void set(@NotNull NBTTagCompound $this$set, @NotNull String key, @NotNull NBTTagList value) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        $this$set.func_74782_a(key, (NBTBase) value);
    }

    @NotNull
    public static final NBTTagCompound NBTTagCompound(@NotNull Function1<? super NBTTagCompound, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        NBTTagCompound nBTTagCompound = new NBTTagCompound();
        builderAction.invoke(nBTTagCompound);
        return nBTTagCompound;
    }

    @NotNull
    public static final NBTTagList NBTTagList(@NotNull Function1<? super NBTTagList, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        NBTTagList nBTTagList = new NBTTagList();
        builderAction.invoke(nBTTagList);
        return nBTTagList;
    }

    public static final void appendTag(@NotNull NBTTagList $this$appendTag, @NotNull Function1<? super NBTTagCompound, Unit> builderAction) {
        Intrinsics.checkNotNullParameter($this$appendTag, "<this>");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        NBTBase nBTTagCompound = new NBTTagCompound();
        builderAction.invoke(nBTTagCompound);
        $this$appendTag.func_74742_a(nBTTagCompound);
    }
}
