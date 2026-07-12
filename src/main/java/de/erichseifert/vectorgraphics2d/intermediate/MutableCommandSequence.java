package de.erichseifert.vectorgraphics2d.intermediate;

import de.erichseifert.vectorgraphics2d.intermediate.commands.Command;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/MutableCommandSequence.class */
public class MutableCommandSequence implements CommandSequence {
    private final List<Command<?>> a = new LinkedList();

    public void add(Command<?> command) {
        this.a.add(command);
    }

    @Override // java.lang.Iterable
    public Iterator<Command<?>> iterator() {
        return this.a.iterator();
    }
}
