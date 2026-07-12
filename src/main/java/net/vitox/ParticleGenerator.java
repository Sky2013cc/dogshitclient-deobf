package net.vitox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vitox.particle.util.RenderUtils;

@SideOnly(Side.CLIENT)
/* loaded from: target.jar:net/vitox/ParticleGenerator.class */
public class ParticleGenerator {
    private final List<Particle> particles = new ArrayList();
    private final int amount;
    private int prevWidth;
    private int prevHeight;

    public ParticleGenerator(int amount) {
        this.amount = amount;
    }

    public void draw(int mouseX, int mouseY) {
        if (this.particles.isEmpty() || this.prevWidth != MinecraftInstance.mc.field_71443_c || this.prevHeight != MinecraftInstance.mc.field_71440_d) {
            this.particles.clear();
            create();
        }
        this.prevWidth = MinecraftInstance.mc.field_71443_c;
        this.prevHeight = MinecraftInstance.mc.field_71440_d;
        for (Particle particle : this.particles) {
            particle.fall();
            particle.interpolation();
            int range = 50;
            boolean mouseOver = ((float) mouseX) >= particle.x - ((float) 50) && ((float) mouseY) >= particle.y - ((float) 50) && ((float) mouseX) <= particle.x + ((float) 50) && ((float) mouseY) <= particle.y + ((float) 50);
            if (mouseOver) {
                this.particles.stream().filter(part -> {
                    return part.getX() > particle.getX() && part.getX() - particle.getX() < ((float) range) && particle.getX() - part.getX() < ((float) range) && ((part.getY() > particle.getY() && part.getY() - particle.getY() < ((float) range)) || (particle.getY() > part.getY() && particle.getY() - part.getY() < ((float) range)));
                }).forEach(connectable -> {
                    particle.connect(connectable.getX(), connectable.getY());
                });
            }
            RenderUtils.drawCircle(particle.getX(), particle.getY(), particle.size, -1);
        }
    }

    private void create() {
        Random random = new Random();
        for (int i = 0; i < this.amount; i++) {
            this.particles.add(new Particle(random.nextInt(MinecraftInstance.mc.field_71443_c), random.nextInt(MinecraftInstance.mc.field_71440_d)));
        }
    }
}
