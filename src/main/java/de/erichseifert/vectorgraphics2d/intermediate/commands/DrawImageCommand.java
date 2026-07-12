package de.erichseifert.vectorgraphics2d.intermediate.commands;

import java.awt.Image;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/DrawImageCommand.class */
public class DrawImageCommand extends Command<Image> {
    private final int a;
    private final int b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    public DrawImageCommand(Image image, int i, int i2, double d, double d2, double d3, double d4) {
        super(image);
        this.a = i;
        this.b = i2;
        this.c = d;
        this.d = d2;
        this.e = d3;
        this.f = d4;
    }

    public int getImageWidth() {
        return this.a;
    }

    public int getImageHeight() {
        return this.b;
    }

    public double getX() {
        return this.c;
    }

    public double getY() {
        return this.d;
    }

    public double getWidth() {
        return this.e;
    }

    public double getHeight() {
        return this.f;
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.commands.Command
    public String toString() {
        return String.format(null, "%s[value=%s, imageWidth=%d, imageHeight=%d, x=%f, y=%f, width=%f, height=%f]", getClass().getName(), getValue(), Integer.valueOf(getImageWidth()), Integer.valueOf(getImageHeight()), Double.valueOf(getX()), Double.valueOf(getY()), Double.valueOf(getWidth()), Double.valueOf(getHeight()));
    }
}
