package de.erichseifert.vectorgraphics2d.intermediate.filters;

import de.erichseifert.vectorgraphics2d.intermediate.CommandSequence;
import de.erichseifert.vectorgraphics2d.intermediate.commands.AffineTransformCommand;
import de.erichseifert.vectorgraphics2d.intermediate.commands.Command;
import de.erichseifert.vectorgraphics2d.intermediate.commands.SetHintCommand;
import de.erichseifert.vectorgraphics2d.intermediate.commands.StateCommand;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/filters/OptimizeFilter.class */
public class OptimizeFilter extends StreamingFilter {
    private final Queue<Command<?>> a;

    public OptimizeFilter(CommandSequence commandSequence) {
        super(commandSequence);
        this.a = new LinkedList();
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.filters.StreamingFilter, java.util.Iterator
    public boolean hasNext() {
        return super.hasNext();
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.filters.StreamingFilter, java.util.Iterator
    public Command<?> next() {
        if (this.a.isEmpty()) {
            return super.next();
        }
        return this.a.poll();
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.filters.StreamingFilter
    protected List<Command<?>> filter(Command<?> command) {
        if (!((!(command instanceof StateCommand) || (command instanceof AffineTransformCommand) || (command instanceof SetHintCommand)) ? false : true)) {
            return Collections.singletonList(command);
        }
        Iterator<Command<?>> it = this.a.iterator();
        Class<?> cls = command.getClass();
        while (it.hasNext()) {
            if (cls.equals(it.next().getClass())) {
                it.remove();
            }
        }
        this.a.add(command);
        return null;
    }
}
