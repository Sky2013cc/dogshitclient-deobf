package net.vitox;

import java.util.Random;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vitox.particle.util.RenderUtils;

/* JADX INFO: Access modifiers changed from: package-private */
@SideOnly(Side.CLIENT)
/* loaded from: target.jar:net/vitox/Particle.class */
public class Particle {
    public float x;
    public float y;
    private int height;
    private int width;
    private final float ySpeed = new Random().nextInt(5);
    private final float xSpeed = new Random().nextInt(5);
    public final float size = genRandom();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Particle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private float lint1(float f) {
        return (1.02f * (1.0f - f)) + f;
    }

    private float lint2(float f) {
        return 1.02f + (f * (-0.01999998f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void connect(float x, float y) {
        RenderUtils.connectPoints(getX(), getY(), x, y);
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void interpolation() {
        for (int n = 0; n <= 64; n++) {
            float f = n / 64.0f;
            float p1 = lint1(f);
            float p2 = lint2(f);
            if (p1 != p2) {
                this.y -= f;
                this.x -= f;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fall() {
        ScaledResolution scaledResolution = new ScaledResolution(MinecraftInstance.mc);
        this.y += this.ySpeed;
        this.x += this.xSpeed;
        if (this.y > MinecraftInstance.mc.field_71440_d) {
            this.y = 1.0f;
        }
        if (this.x > MinecraftInstance.mc.field_71443_c) {
            this.x = 1.0f;
        }
        if (this.x < 1.0f) {
            this.x = scaledResolution.func_78326_a();
        }
        if (this.y < 1.0f) {
            this.y = scaledResolution.func_78328_b();
        }
    }

    private float genRandom() {
        return (float) (0.30000001192092896d + (Math.random() * 1.2999999523162842d));
    }
}
