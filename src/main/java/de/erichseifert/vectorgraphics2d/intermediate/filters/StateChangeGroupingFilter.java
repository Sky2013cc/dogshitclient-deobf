package de.erichseifert.vectorgraphics2d.intermediate.filters;

import de.erichseifert.vectorgraphics2d.intermediate.CommandSequence;
import de.erichseifert.vectorgraphics2d.intermediate.commands.Command;
import de.erichseifert.vectorgraphics2d.intermediate.commands.StateCommand;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/filters/StateChangeGroupingFilter.class */
public class StateChangeGroupingFilter extends GroupingFilter {
    public StateChangeGroupingFilter(CommandSequence commandSequence) {
        super(commandSequence);
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.filters.GroupingFilter
    protected boolean isGrouped(Command<?> command) {
        return command instanceof StateCommand;
    }
}
