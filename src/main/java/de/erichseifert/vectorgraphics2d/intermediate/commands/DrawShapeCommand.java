package de.erichseifert.vectorgraphics2d.intermediate.commands;

import de.erichseifert.vectorgraphics2d.util.GraphicsUtils;
import java.awt.Shape;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/DrawShapeCommand.class */
public class DrawShapeCommand extends Command<Shape> {
    public DrawShapeCommand(Shape shape) {
        super(GraphicsUtils.clone(shape));
    }
}
