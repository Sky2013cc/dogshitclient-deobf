package de.erichseifert.vectorgraphics2d.intermediate.commands;

import java.awt.geom.AffineTransform;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/TransformCommand.class */
public class TransformCommand extends AffineTransformCommand {
    private final AffineTransform a;

    public TransformCommand(AffineTransform affineTransform) {
        super(affineTransform);
        this.a = new AffineTransform(affineTransform);
    }

    public AffineTransform getTransform() {
        return this.a;
    }
}
