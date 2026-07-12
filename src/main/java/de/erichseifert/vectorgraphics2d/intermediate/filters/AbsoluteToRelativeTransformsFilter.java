package de.erichseifert.vectorgraphics2d.intermediate.filters;

import de.erichseifert.vectorgraphics2d.intermediate.CommandSequence;
import de.erichseifert.vectorgraphics2d.intermediate.commands.AffineTransformCommand;
import de.erichseifert.vectorgraphics2d.intermediate.commands.Command;
import de.erichseifert.vectorgraphics2d.intermediate.commands.CreateCommand;
import de.erichseifert.vectorgraphics2d.intermediate.commands.DisposeCommand;
import de.erichseifert.vectorgraphics2d.intermediate.commands.SetTransformCommand;
import de.erichseifert.vectorgraphics2d.intermediate.commands.TransformCommand;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/filters/AbsoluteToRelativeTransformsFilter.class */
public class AbsoluteToRelativeTransformsFilter extends StreamingFilter {
    private final Stack<AffineTransform> a;

    public AbsoluteToRelativeTransformsFilter(CommandSequence commandSequence) {
        super(commandSequence);
        this.a = new Stack<>();
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.filters.StreamingFilter, java.util.Iterator
    public Command<?> next() {
        Command<?> next = super.next();
        if (!(next instanceof AffineTransformCommand)) {
            if (!(next instanceof CreateCommand)) {
                if (next instanceof DisposeCommand) {
                    this.a.pop();
                }
            } else {
                this.a.push(this.a.isEmpty() ? new AffineTransform() : new AffineTransform(a()));
            }
        } else {
            a().concatenate(((AffineTransformCommand) next).getValue());
        }
        return next;
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.filters.StreamingFilter
    protected List<Command<?>> filter(Command<?> command) {
        if (command instanceof SetTransformCommand) {
            AffineTransform value = ((SetTransformCommand) command).getValue();
            AffineTransform affineTransform = new AffineTransform();
            try {
                affineTransform = affineTransform;
                affineTransform.concatenate(a().createInverse());
            } catch (NoninvertibleTransformException e) {
                affineTransform.printStackTrace();
            }
            affineTransform.concatenate(value);
            return Collections.singletonList(new TransformCommand(affineTransform));
        }
        return Collections.singletonList(command);
    }

    private AffineTransform a() {
        return this.a.peek();
    }
}
