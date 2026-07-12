package com.sun.tools.classfile;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: target.jar:com/sun/tools/classfile/AccessFlags.class */
public class AccessFlags {
    public static final int ACC_PUBLIC = 1;
    public static final int ACC_PRIVATE = 2;
    public static final int ACC_PROTECTED = 4;
    public static final int ACC_STATIC = 8;
    public static final int ACC_FINAL = 16;
    public static final int ACC_SUPER = 32;
    public static final int ACC_SYNCHRONIZED = 32;
    public static final int ACC_VOLATILE = 64;
    public static final int ACC_BRIDGE = 64;
    public static final int ACC_TRANSIENT = 128;
    public static final int ACC_VARARGS = 128;
    public static final int ACC_NATIVE = 256;
    public static final int ACC_INTERFACE = 512;
    public static final int ACC_ABSTRACT = 1024;
    public static final int ACC_STRICT = 2048;
    public static final int ACC_SYNTHETIC = 4096;
    public static final int ACC_ANNOTATION = 8192;
    public static final int ACC_ENUM = 16384;
    public static final int ACC_MANDATED = 32768;
    private static final int[] classModifiers = {1, 16, 1024};
    private static final int[] classFlags = {1, 16, 32, 512, 1024, 4096, 8192, 16384};
    private static final int[] innerClassModifiers = {1, 2, 4, 8, 16, 1024};
    private static final int[] innerClassFlags = {1, 2, 4, 8, 16, 32, 512, 1024, 4096, 8192, 16384};
    private static final int[] fieldModifiers = {1, 2, 4, 8, 16, 64, 128};
    private static final int[] fieldFlags = {1, 2, 4, 8, 16, 64, 128, 4096, 16384};
    private static final int[] methodModifiers = {1, 2, 4, 8, 16, 32, 256, 1024, 2048};
    private static final int[] methodFlags = {1, 2, 4, 8, 16, 32, 64, 128, 256, 1024, 2048, 4096};
    public final int flags;

    /* loaded from: target.jar:com/sun/tools/classfile/AccessFlags$Kind.class */
    public enum Kind {
        Class,
        InnerClass,
        Field,
        Method
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessFlags(ClassReader classReader) throws IOException {
        this(classReader.readUnsignedShort());
    }

    public AccessFlags(int i) {
        this.flags = i;
    }

    public AccessFlags ignore(int i) {
        return new AccessFlags(this.flags & (i ^ (-1)));
    }

    public boolean is(int i) {
        return (this.flags & i) != 0;
    }

    public int byteLength() {
        return 2;
    }

    public Set<String> getClassModifiers() {
        return getModifiers((this.flags & 512) != 0 ? this.flags & (-1025) : this.flags, classModifiers, Kind.Class);
    }

    public Set<String> getClassFlags() {
        return getFlags(classFlags, Kind.Class);
    }

    public Set<String> getInnerClassModifiers() {
        return getModifiers((this.flags & 512) != 0 ? this.flags & (-1025) : this.flags, innerClassModifiers, Kind.InnerClass);
    }

    public Set<String> getInnerClassFlags() {
        return getFlags(innerClassFlags, Kind.InnerClass);
    }

    public Set<String> getFieldModifiers() {
        return getModifiers(fieldModifiers, Kind.Field);
    }

    public Set<String> getFieldFlags() {
        return getFlags(fieldFlags, Kind.Field);
    }

    public Set<String> getMethodModifiers() {
        return getModifiers(methodModifiers, Kind.Method);
    }

    public Set<String> getMethodFlags() {
        return getFlags(methodFlags, Kind.Method);
    }

    private Set<String> getModifiers(int[] iArr, Kind kind) {
        return getModifiers(this.flags, iArr, kind);
    }

    private static Set<String> getModifiers(int i, int[] iArr, Kind kind) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int i2 : iArr) {
            if ((i & i2) != 0) {
                linkedHashSet.add(flagToModifier(i2, kind));
            }
        }
        return linkedHashSet;
    }

    private Set<String> getFlags(int[] iArr, Kind kind) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = this.flags;
        for (int i2 : iArr) {
            if ((i & i2) != 0) {
                linkedHashSet.add(flagToName(i2, kind));
                i &= i2 ^ (-1);
            }
        }
        while (i != 0) {
            int highestOneBit = Integer.highestOneBit(i);
            linkedHashSet.add("0x" + Integer.toHexString(highestOneBit));
            i &= highestOneBit ^ (-1);
        }
        return linkedHashSet;
    }

    private static String flagToModifier(int i, Kind kind) {
        switch (i) {
            case 1:
                return Constants.ATTR_PUBLIC;
            case 2:
                return "private";
            case 4:
                return "protected";
            case 8:
                return "static";
            case 16:
                return Constants.ATTR_FINAL;
            case 32:
                return "synchronized";
            case 64:
                return "volatile";
            case 128:
                if (kind == Kind.Field) {
                    return "transient";
                }
                return null;
            case 256:
                return "native";
            case 1024:
                return Constants.ATTR_ABSTRACT;
            case 2048:
                return "strictfp";
            case 32768:
                return "mandated";
            default:
                return null;
        }
    }

    private static String flagToName(int i, Kind kind) {
        switch (i) {
            case 1:
                return "ACC_PUBLIC";
            case 2:
                return "ACC_PRIVATE";
            case 4:
                return "ACC_PROTECTED";
            case 8:
                return "ACC_STATIC";
            case 16:
                return "ACC_FINAL";
            case 32:
                return kind == Kind.Class ? "ACC_SUPER" : "ACC_SYNCHRONIZED";
            case 64:
                return kind == Kind.Field ? "ACC_VOLATILE" : "ACC_BRIDGE";
            case 128:
                return kind == Kind.Field ? "ACC_TRANSIENT" : "ACC_VARARGS";
            case 256:
                return "ACC_NATIVE";
            case 512:
                return "ACC_INTERFACE";
            case 1024:
                return "ACC_ABSTRACT";
            case 2048:
                return "ACC_STRICT";
            case 4096:
                return "ACC_SYNTHETIC";
            case 8192:
                return "ACC_ANNOTATION";
            case 16384:
                return "ACC_ENUM";
            case 32768:
                return "ACC_MANDATED";
            default:
                return null;
        }
    }
}
