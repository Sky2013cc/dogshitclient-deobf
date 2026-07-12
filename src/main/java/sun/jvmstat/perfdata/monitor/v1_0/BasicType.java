package sun.jvmstat.perfdata.monitor.v1_0;

import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/v1_0/BasicType.class */
public class BasicType {
    private final String name;
    private final int value;
    public static final BasicType BOOLEAN = new BasicType(Constants.IDL_BOOLEAN, 4);
    public static final BasicType CHAR = new BasicType("char", 5);
    public static final BasicType FLOAT = new BasicType(Constants.IDL_FLOAT, 6);
    public static final BasicType DOUBLE = new BasicType(Constants.IDL_DOUBLE, 7);
    public static final BasicType BYTE = new BasicType("byte", 8);
    public static final BasicType SHORT = new BasicType(Constants.IDL_SHORT, 9);
    public static final BasicType INT = new BasicType("int", 10);
    public static final BasicType LONG = new BasicType(Constants.IDL_INT, 11);
    public static final BasicType OBJECT = new BasicType("object", 12);
    public static final BasicType ARRAY = new BasicType("array", 13);
    public static final BasicType VOID = new BasicType(Constants.IDL_VOID, 14);
    public static final BasicType ADDRESS = new BasicType("address", 15);
    public static final BasicType ILLEGAL = new BasicType("illegal", 99);
    private static BasicType[] basicTypes = {BOOLEAN, CHAR, FLOAT, DOUBLE, BYTE, SHORT, INT, LONG, OBJECT, ARRAY, VOID, ADDRESS, ILLEGAL};

    public String toString() {
        return this.name;
    }

    public int intValue() {
        return this.value;
    }

    public static BasicType toBasicType(int i) {
        for (int i2 = 0; i2 < basicTypes.length; i2++) {
            if (basicTypes[i2].intValue() == i2) {
                return basicTypes[i2];
            }
        }
        return ILLEGAL;
    }

    private BasicType(String str, int i) {
        this.name = str;
        this.value = i;
    }
}
