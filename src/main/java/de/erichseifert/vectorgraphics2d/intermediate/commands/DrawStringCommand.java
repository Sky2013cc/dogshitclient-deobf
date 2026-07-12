package de.erichseifert.vectorgraphics2d.intermediate.commands;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/commands/DrawStringCommand.class */
public class DrawStringCommand extends Command<String> {
    private final double a;
    private final double b;

    public DrawStringCommand(String str, double d, double d2) {
        super(str);
        this.a = d;
        this.b = d2;
    }

    public double getX() {
        return this.a;
    }

    public double getY() {
        return this.b;
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.commands.Command
    public String toString() {
        return String.format(null, "%s[value=%s, x=%f, y=%f]", getClass().getName(), getValue(), Double.valueOf(getX()), Double.valueOf(getY()));
    }
}
