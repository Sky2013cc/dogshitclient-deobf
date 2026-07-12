package de.erichseifert.vectorgraphics2d.intermediate.commands;

import java.awt.geom.AffineTransform;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/TranslateCommand.class */
public class TranslateCommand extends AffineTransformCommand {
    private final double a;
    private final double b;

    public TranslateCommand(double d, double d2) {
        super(AffineTransform.getTranslateInstance(d, d2));
        this.a = d;
        this.b = d2;
    }

    public double getDeltaX() {
        return this.a;
    }

    public double getDeltaY() {
        return this.b;
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.commands.Command
    public String toString() {
        return String.format(null, "%s[deltaX=%f, deltaY=%f, value=%s]", getClass().getName(), Double.valueOf(getDeltaX()), Double.valueOf(getDeltaY()), getValue());
    }
}
