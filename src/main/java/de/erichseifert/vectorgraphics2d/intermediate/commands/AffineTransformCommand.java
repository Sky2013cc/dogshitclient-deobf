package de.erichseifert.vectorgraphics2d.intermediate.commands;

import java.awt.geom.AffineTransform;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/AffineTransformCommand.class */
public abstract class AffineTransformCommand extends StateCommand<AffineTransform> {
    public AffineTransformCommand(AffineTransform affineTransform) {
        super(affineTransform);
    }
}
