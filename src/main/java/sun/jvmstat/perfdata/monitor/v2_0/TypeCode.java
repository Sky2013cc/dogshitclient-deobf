package sun.jvmstat.perfdata.monitor.v2_0;

import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/v2_0/TypeCode.class */
public class TypeCode {
    private final String name;
    private final char value;
    public static final TypeCode BOOLEAN = new TypeCode(Constants.IDL_BOOLEAN, 'Z');
    public static final TypeCode CHAR = new TypeCode("char", 'C');
    public static final TypeCode FLOAT = new TypeCode(Constants.IDL_FLOAT, 'F');
    public static final TypeCode DOUBLE = new TypeCode(Constants.IDL_DOUBLE, 'D');
    public static final TypeCode BYTE = new TypeCode("byte", 'B');
    public static final TypeCode SHORT = new TypeCode(Constants.IDL_SHORT, 'S');
    public static final TypeCode INT = new TypeCode("int", 'I');
    public static final TypeCode LONG = new TypeCode(Constants.IDL_INT, 'J');
    public static final TypeCode OBJECT = new TypeCode("object", 'L');
    public static final TypeCode ARRAY = new TypeCode("array", '[');
    public static final TypeCode VOID = new TypeCode(Constants.IDL_VOID, 'V');
    private static TypeCode[] basicTypes = {LONG, BYTE, BOOLEAN, CHAR, FLOAT, DOUBLE, SHORT, INT, OBJECT, ARRAY, VOID};

    public String toString() {
        return this.name;
    }

    public int toChar() {
        return this.value;
    }

    public static TypeCode toTypeCode(char c) {
        for (int i = 0; i < basicTypes.length; i++) {
            if (basicTypes[i].value == c) {
                return basicTypes[i];
            }
        }
        throw new IllegalArgumentException();
    }

    public static TypeCode toTypeCode(byte b) {
        return toTypeCode((char) b);
    }

    private TypeCode(String str, char c) {
        this.name = str;
        this.value = c;
    }
}
