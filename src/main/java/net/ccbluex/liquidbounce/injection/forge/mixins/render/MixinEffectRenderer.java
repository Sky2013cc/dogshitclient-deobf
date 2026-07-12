package net.ccbluex.liquidbounce.injection.forge.mixins.render;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityParticleEmitter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({EffectRenderer.class})
@SideOnly(Side.CLIENT)
/* loaded from: target.jar:net/ccbluex/liquidbounce/injection/forge/mixins/render/MixinEffectRenderer.class */
public abstract class MixinEffectRenderer {

    @Shadow
    private List<EntityParticleEmitter> field_178933_d;

    @Shadow
    protected abstract void func_178922_a(int i);

    @Overwrite
    public void func_78868_a() {
        for (int i = 0; i < 4; i++) {
            try {
                func_178922_a(i);
            } catch (ConcurrentModificationException e) {
                return;
            }
        }
        Iterator<EntityParticleEmitter> it = this.field_178933_d.iterator();
        while (it.hasNext()) {
            EntityParticleEmitter entityParticleEmitter = it.next();
            entityParticleEmitter.func_70071_h_();
            if (entityParticleEmitter.field_70128_L) {
                it.remove();
            }
        }
    }
}
