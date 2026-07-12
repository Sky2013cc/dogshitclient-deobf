package de.erichseifert.vectorgraphics2d.intermediate.commands;

import java.awt.Shape;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/SetClipCommand.class */
public class SetClipCommand extends StateCommand<Shape> {
    public SetClipCommand(Shape shape) {
        super(shape);
    }
}
