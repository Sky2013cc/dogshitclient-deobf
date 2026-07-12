package de.erichseifert.vectorgraphics2d.intermediate.commands;

import java.awt.geom.AffineTransform;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/ScaleCommand.class */
public class ScaleCommand extends AffineTransformCommand {
    private final double a;
    private final double b;

    public ScaleCommand(double d, double d2) {
        super(AffineTransform.getScaleInstance(d, d2));
        this.a = d;
        this.b = d2;
    }

    public double getScaleX() {
        return this.a;
    }

    public double getScaleY() {
        return this.b;
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.commands.Command
    public String toString() {
        return String.format(null, "%s[scaleX=%f, scaleY=%f, value=%s]", getClass().getName(), Double.valueOf(getScaleX()), Double.valueOf(getScaleY()), getValue());
    }
}
