package net.ccbluex.liquidbounce.utils.render;

import kotlin.Metadata;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vitox.ParticleGenerator;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: ParticleUtils.kt */
@SideOnly(Side.CLIENT)
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/render/ParticleUtils;", "", Constants.CTOR, "()V", "particleGenerator", "Lnet/vitox/ParticleGenerator;", "drawParticles", "", "mouseX", "", "mouseY", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/render/ParticleUtils.class */
public final class ParticleUtils {

    @NotNull
    public static final ParticleUtils INSTANCE = new ParticleUtils();

    @NotNull
    private static final ParticleGenerator particleGenerator = new ParticleGenerator(100);

    private ParticleUtils() {
    }

    public final void drawParticles(int mouseX, int mouseY) {
        particleGenerator.draw(mouseX, mouseY);
    }
}
