package de.erichseifert.vectorgraphics2d.intermediate.filters;

import de.erichseifert.vectorgraphics2d.intermediate.CommandSequence;
import de.erichseifert.vectorgraphics2d.intermediate.commands.Command;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/filters/StreamingFilter.class */
public abstract class StreamingFilter implements Filter, Iterator<Command<?>> {
    private final Queue<Command<?>> a = new LinkedList();
    private final Iterator<Command<?>> b;

    protected abstract List<Command<?>> filter(Command<?> command);

    public StreamingFilter(CommandSequence commandSequence) {
        this.b = commandSequence.iterator();
    }

    @Override // java.lang.Iterable
    public Iterator<Command<?>> iterator() {
        return this;
    }

    public boolean hasNext() {
        a();
        return !this.a.isEmpty();
    }

    private void a() {
        while (this.a.isEmpty() && this.b.hasNext()) {
            List<Command<?>> filter = filter(this.b.next());
            if (filter != null) {
                this.a.addAll(filter);
            }
        }
    }

    @Override // java.util.Iterator
    public Command<?> next() {
        a();
        return this.a.poll();
    }

    @Override // java.util.Iterator
    public void remove() {
    }
}
