package net.ccbluex.liquidbounce.injection.forge.mixins.tweaks;

import java.io.DataInputStream;
import java.io.IOException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({AnvilChunkLoader.class})
/* loaded from: target.jar:net/ccbluex/liquidbounce/injection/forge/mixins/tweaks/MixinAnvilChunkLoader.class */
public class MixinAnvilChunkLoader {
    @Redirect(method = {"loadChunk__Async"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompressedStreamTools;read(Ljava/io/DataInputStream;)Lnet/minecraft/nbt/NBTTagCompound;"))
    private NBTTagCompound redirectReadChunkData(DataInputStream inputStream) throws IOException {
        Throwable th = null;
        try {
            try {
                NBTTagCompound func_74794_a = CompressedStreamTools.func_74794_a(inputStream);
                if (inputStream != null) {
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        inputStream.close();
                    }
                }
                return func_74794_a;
            } finally {
            }
        } catch (Throwable th3) {
            if (inputStream != null) {
                if (th != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                } else {
                    inputStream.close();
                }
            }
            throw th3;
        }
    }
}
