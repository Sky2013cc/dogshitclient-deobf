package de.erichseifert.vectorgraphics2d.intermediate.filters;

import de.erichseifert.vectorgraphics2d.intermediate.CommandSequence;
import de.erichseifert.vectorgraphics2d.intermediate.commands.Command;
import de.erichseifert.vectorgraphics2d.intermediate.commands.Group;
import java.util.Collections;
import java.util.List;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/filters/GroupingFilter.class */
public abstract class GroupingFilter extends StreamingFilter {
    private Group a;

    protected abstract boolean isGrouped(Command<?> command);

    public GroupingFilter(CommandSequence commandSequence) {
        super(commandSequence);
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.filters.StreamingFilter, java.util.Iterator
    public boolean hasNext() {
        return this.a != null || super.hasNext();
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.filters.StreamingFilter, java.util.Iterator
    public Command<?> next() {
        if (this.a == null) {
            return super.next();
        }
        Group group = this.a;
        this.a = null;
        return group;
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.filters.StreamingFilter
    protected List<Command<?>> filter(Command<?> command) {
        if (isGrouped(command)) {
            if (this.a == null) {
                this.a = new Group();
            }
            this.a.add(command);
            return null;
        }
        return Collections.singletonList(command);
    }
}
