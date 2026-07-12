package com.formdev.flatlaf.json;

/* loaded from: target.jar:com/formdev/flatlaf/json/ParseException.class */
public class ParseException extends RuntimeException {
    private final Location location;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParseException(String message, Location location) {
        super(message + " at " + location);
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    @Deprecated
    public int getOffset() {
        return this.location.offset;
    }

    @Deprecated
    public int getLine() {
        return this.location.line;
    }

    @Deprecated
    public int getColumn() {
        return this.location.column;
    }
}
