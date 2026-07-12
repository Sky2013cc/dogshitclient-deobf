package sun.jvmstat.monitor;

import java.io.Serializable;

/* loaded from: target.jar:sun/jvmstat/monitor/Units.class */
public class Units implements Serializable {
    private static final int NUNITS = 8;
    private final String name;
    private final int value;
    private static final long serialVersionUID = 6992337162326171013L;
    private static Units[] map = new Units[8];
    public static final Units INVALID = new Units("Invalid", 0);
    public static final Units NONE = new Units("None", 1);
    public static final Units BYTES = new Units("Bytes", 2);
    public static final Units TICKS = new Units("Ticks", 3);
    public static final Units EVENTS = new Units("Events", 4);
    public static final Units STRING = new Units("String", 5);
    public static final Units HERTZ = new Units("Hertz", 6);

    public String toString() {
        return this.name;
    }

    public int intValue() {
        return this.value;
    }

    public static Units toUnits(int i) {
        if (i < 0 || i >= map.length || map[i] == null) {
            return INVALID;
        }
        return map[i];
    }

    private Units(String str, int i) {
        this.name = str;
        this.value = i;
        map[i] = this;
    }
}
