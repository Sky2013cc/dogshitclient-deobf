package de.erichseifert.vectorgraphics2d.intermediate.commands;

import java.awt.geom.AffineTransform;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/SetTransformCommand.class */
public class SetTransformCommand extends StateCommand<AffineTransform> {
    public SetTransformCommand(AffineTransform affineTransform) {
        super(new AffineTransform(affineTransform));
    }
}
