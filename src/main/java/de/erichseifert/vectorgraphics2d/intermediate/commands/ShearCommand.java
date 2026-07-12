package de.erichseifert.vectorgraphics2d.intermediate.commands;

import java.awt.geom.AffineTransform;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/ShearCommand.class */
public class ShearCommand extends AffineTransformCommand {
    private final double a;
    private final double b;

    public ShearCommand(double d, double d2) {
        super(AffineTransform.getShearInstance(d, d2));
        this.a = d;
        this.b = d2;
    }

    public double getShearX() {
        return this.a;
    }

    public double getShearY() {
        return this.b;
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.commands.Command
    public String toString() {
        return String.format(null, "%s[shearX=%f, shearY=%f, value=%s]", getClass().getName(), Double.valueOf(getShearX()), Double.valueOf(getShearY()), getValue());
    }
}
