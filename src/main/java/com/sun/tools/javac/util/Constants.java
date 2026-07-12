package com.sun.tools.javac.util;

import com.sun.tools.javac.code.Type;

/* loaded from: target.jar:com/sun/tools/javac/util/Constants.class */
public class Constants {
    public static Object decode(Object obj, Type type) {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            switch (type.getTag()) {
                case BOOLEAN:
                    return Boolean.valueOf(intValue != 0);
                case CHAR:
                    return Character.valueOf((char) intValue);
                case BYTE:
                    return Byte.valueOf((byte) intValue);
                case SHORT:
                    return Short.valueOf((short) intValue);
            }
        }
        return obj;
    }

    public static String format(Object obj, Type type) {
        Object decode = decode(obj, type);
        switch (type.getTag()) {
            case CHAR:
                return formatChar(((Character) decode).charValue());
            case BYTE:
                return formatByte(((Byte) decode).byteValue());
            case SHORT:
            default:
                if (decode instanceof String) {
                    return formatString((String) decode);
                }
                return decode + "";
            case LONG:
                return formatLong(((Long) decode).longValue());
            case FLOAT:
                return formatFloat(((Float) decode).floatValue());
            case DOUBLE:
                return formatDouble(((Double) decode).doubleValue());
        }
    }

    public static String format(Object obj) {
        if (obj instanceof Byte) {
            return formatByte(((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return formatShort(((Short) obj).shortValue());
        }
        if (obj instanceof Long) {
            return formatLong(((Long) obj).longValue());
        }
        if (obj instanceof Float) {
            return formatFloat(((Float) obj).floatValue());
        }
        if (obj instanceof Double) {
            return formatDouble(((Double) obj).doubleValue());
        }
        if (obj instanceof Character) {
            return formatChar(((Character) obj).charValue());
        }
        if (obj instanceof String) {
            return formatString((String) obj);
        }
        if ((obj instanceof Integer) || (obj instanceof Boolean)) {
            return obj.toString();
        }
        throw new IllegalArgumentException("Argument is not a primitive type or a string; it " + (obj == null ? "is a null value." : "has class " + obj.getClass().getName()) + sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR);
    }

    private static String formatByte(byte b) {
        return String.format("(byte)0x%02x", Byte.valueOf(b));
    }

    private static String formatShort(short s) {
        return String.format("(short)%d", Short.valueOf(s));
    }

    private static String formatLong(long j) {
        return j + "L";
    }

    private static String formatFloat(float f) {
        if (Float.isNaN(f)) {
            return "0.0f/0.0f";
        }
        if (Float.isInfinite(f)) {
            return f < 0.0f ? "-1.0f/0.0f" : "1.0f/0.0f";
        }
        return f + "f";
    }

    private static String formatDouble(double d) {
        if (Double.isNaN(d)) {
            return "0.0/0.0";
        }
        if (Double.isInfinite(d)) {
            return d < 0.0d ? "-1.0/0.0" : "1.0/0.0";
        }
        return d + "";
    }

    private static String formatChar(char c) {
        return '\'' + Convert.quote(c) + '\'';
    }

    private static String formatString(String str) {
        return '\"' + Convert.quote(str) + '\"';
    }
}
