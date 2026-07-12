package de.erichseifert.vectorgraphics2d.intermediate.commands;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/Command.class */
public abstract class Command<T> {
    private final T a;

    public Command(T t) {
        this.a = t;
    }

    public T getValue() {
        return this.a;
    }

    public String toString() {
        return String.format(null, "%s[value=%s]", getClass().getName(), getValue());
    }

    public boolean equals(Object obj) {
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        Command command = (Command) obj;
        return this.a == command.a || this.a.equals(command.a);
    }
}
