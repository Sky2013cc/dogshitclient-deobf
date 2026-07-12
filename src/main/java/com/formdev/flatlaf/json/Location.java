package com.formdev.flatlaf.json;

import jdk.internal.dynalink.CallSiteDescriptor;

/* loaded from: target.jar:com/formdev/flatlaf/json/Location.class */
public class Location {
    public final int offset;
    public final int line;
    public final int column;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Location(int offset, int line, int column) {
        this.offset = offset;
        this.column = column;
        this.line = line;
    }

    public String toString() {
        return this.line + CallSiteDescriptor.TOKEN_DELIMITER + this.column;
    }

    public int hashCode() {
        return this.offset;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Location)) {
            return false;
        }
        Location other = (Location) obj;
        return this.offset == other.offset && this.column == other.column && this.line == other.line;
    }
}
