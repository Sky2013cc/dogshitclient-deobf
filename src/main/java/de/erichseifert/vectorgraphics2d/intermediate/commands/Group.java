package de.erichseifert.vectorgraphics2d.intermediate.commands;

import java.util.LinkedList;
import java.util.List;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/Group.class */
public class Group extends Command<List<Command<?>>> {
    public Group() {
        super(new LinkedList());
    }

    public void add(Command<?> command) {
        getValue().add(command);
    }
}
