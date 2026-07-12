package de.erichseifert.vectorgraphics2d.intermediate.commands;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/SetHintCommand.class */
public class SetHintCommand extends StateCommand<Object> {
    private final Object a;

    public SetHintCommand(Object obj, Object obj2) {
        super(obj2);
        this.a = obj;
    }

    public Object getKey() {
        return this.a;
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.commands.Command
    public String toString() {
        return String.format(null, "%s[key=%s, value=%s]", getClass().getName(), getKey(), getValue());
    }
}
