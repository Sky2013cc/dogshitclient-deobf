package sun.tools.java;

import java.util.Hashtable;
import org.slf4j.Marker;

/* loaded from: target.jar:sun/tools/java/Type.class */
public class Type implements Constants {
    protected int typeCode;
    protected String typeSig;
    private static final Hashtable typeHash = new Hashtable(231);
    public static final Type[] noArgs = new Type[0];
    public static final Type tError = new Type(13, "?");
    public static final Type tPackage = new Type(13, sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR);
    public static final Type tNull = new Type(8, Marker.ANY_MARKER);
    public static final Type tVoid = new Type(11, RuntimeConstants.SIG_VOID);
    public static final Type tBoolean = new Type(0, RuntimeConstants.SIG_BOOLEAN);
    public static final Type tByte = new Type(1, "B");
    public static final Type tChar = new Type(2, "C");
    public static final Type tShort = new Type(3, "S");
    public static final Type tInt = new Type(4, "I");
    public static final Type tFloat = new Type(6, "F");
    public static final Type tLong = new Type(5, "J");
    public static final Type tDouble = new Type(7, "D");
    public static final Type tObject = tClass(idJavaLangObject);
    public static final Type tClassDesc = tClass(idJavaLangClass);
    public static final Type tString = tClass(idJavaLangString);
    public static final Type tCloneable = tClass(idJavaLangCloneable);
    public static final Type tSerializable = tClass(idJavaIoSerializable);

    /* JADX INFO: Access modifiers changed from: protected */
    public Type(int i, String str) {
        this.typeCode = i;
        this.typeSig = str;
        typeHash.put(str, this);
    }

    public final String getTypeSignature() {
        return this.typeSig;
    }

    public final int getTypeCode() {
        return this.typeCode;
    }

    public final int getTypeMask() {
        return 1 << this.typeCode;
    }

    public final boolean isType(int i) {
        return this.typeCode == i;
    }

    public boolean isVoidArray() {
        if (!isType(9)) {
            return false;
        }
        Type type = this;
        while (true) {
            Type type2 = type;
            if (type2.isType(9)) {
                type = type2.getElementType();
            } else {
                return type2.isType(11);
            }
        }
    }

    public final boolean inMask(int i) {
        return ((1 << this.typeCode) & i) != 0;
    }

    public static synchronized Type tArray(Type type) {
        String str = new String(RuntimeConstants.SIG_ARRAY + type.getTypeSignature());
        Type type2 = (Type) typeHash.get(str);
        if (type2 == null) {
            type2 = new ArrayType(str, type);
        }
        return type2;
    }

    public Type getElementType() {
        throw new CompilerError("getElementType");
    }

    public int getArrayDimension() {
        return 0;
    }

    public static synchronized Type tClass(Identifier identifier) {
        if (identifier.isInner()) {
            Type tClass = tClass(mangleInnerType(identifier));
            if (tClass.getClassName() != identifier) {
                changeClassName(tClass.getClassName(), identifier);
            }
            return tClass;
        }
        if (identifier.typeObject != null) {
            return identifier.typeObject;
        }
        String str = new String("L" + identifier.toString().replace('.', '/') + RuntimeConstants.SIG_ENDCLASS);
        Type type = (Type) typeHash.get(str);
        if (type == null) {
            type = new ClassType(str, identifier);
        }
        identifier.typeObject = type;
        return type;
    }

    public Identifier getClassName() {
        throw new CompilerError("getClassName:" + this);
    }

    public static Identifier mangleInnerType(Identifier identifier) {
        if (!identifier.isInner()) {
            return identifier;
        }
        Identifier lookup = Identifier.lookup(identifier.getFlatName().toString().replace('.', '$'));
        if (lookup.isInner()) {
            throw new CompilerError("mangle " + lookup);
        }
        return Identifier.lookup(identifier.getQualifier(), lookup);
    }

    static void changeClassName(Identifier identifier, Identifier identifier2) {
        ((ClassType) tClass(identifier)).className = identifier2;
    }

    public static synchronized Type tMethod(Type type) {
        return tMethod(type, noArgs);
    }

    public static synchronized Type tMethod(Type type, Type[] typeArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(RuntimeConstants.SIG_METHOD);
        for (Type type2 : typeArr) {
            stringBuffer.append(type2.getTypeSignature());
        }
        stringBuffer.append(RuntimeConstants.SIG_ENDMETHOD);
        stringBuffer.append(type.getTypeSignature());
        String stringBuffer2 = stringBuffer.toString();
        Type type3 = (Type) typeHash.get(stringBuffer2);
        if (type3 == null) {
            type3 = new MethodType(stringBuffer2, type, typeArr);
        }
        return type3;
    }

    public Type getReturnType() {
        throw new CompilerError("getReturnType");
    }

    public Type[] getArgumentTypes() {
        throw new CompilerError("getArgumentTypes");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0093, code lost:
    
        if (r6.charAt(r1) == 'L') goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0096, code lost:
    
        r1 = r11;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a1, code lost:
    
        if (r6.charAt(r1) == ';') goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00aa, code lost:
    
        if (r9 != r8.length) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ad, code lost:
    
        r0 = new sun.tools.java.Type[r9 * 2];
        java.lang.System.arraycopy(r8, 0, r0, 0, r9);
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized Type tType(String str) {
        int i;
        Type type = (Type) typeHash.get(str);
        if (type != null) {
            return type;
        }
        switch (str.charAt(0)) {
            case '(':
                Type[] typeArr = new Type[8];
                int i2 = 0;
                int i3 = 1;
                while (true) {
                    int i4 = i3;
                    if (str.charAt(i4) != ')') {
                        int i5 = i4;
                        while (str.charAt(i5) == '[') {
                            i5++;
                        }
                        int i6 = i5;
                        i = i5 + 1;
                        break;
                    } else {
                        Type[] typeArr2 = new Type[i2];
                        System.arraycopy(typeArr, 0, typeArr2, 0, i2);
                        return tMethod(tType(str.substring(i4 + 1)), typeArr2);
                    }
                    int i7 = i2;
                    i2++;
                    typeArr[i7] = tType(str.substring(i4, i));
                    i3 = i;
                }
            case 'L':
                return tClass(Identifier.lookup(str.substring(1, str.length() - 1).replace('/', '.')));
            case '[':
                return tArray(tType(str.substring(1)));
            default:
                throw new CompilerError("invalid TypeSignature:" + str);
        }
    }

    public boolean equalArguments(Type type) {
        return false;
    }

    public int stackSize() {
        switch (this.typeCode) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 9:
            case 10:
                return 1;
            case 5:
            case 7:
                return 2;
            case 8:
            case 12:
            default:
                throw new CompilerError("stackSize " + toString());
            case 11:
            case 13:
                return 0;
        }
    }

    public int getTypeCodeOffset() {
        switch (this.typeCode) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return 0;
            case 5:
                return 1;
            case 6:
                return 2;
            case 7:
                return 3;
            case 8:
            case 9:
            case 10:
                return 4;
            default:
                throw new CompilerError("invalid typecode: " + this.typeCode);
        }
    }

    public String typeString(String str, boolean z, boolean z2) {
        String str2;
        switch (this.typeCode) {
            case 0:
                str2 = sun.rmi.rmic.iiop.Constants.IDL_BOOLEAN;
                break;
            case 1:
                str2 = "byte";
                break;
            case 2:
                str2 = "char";
                break;
            case 3:
                str2 = sun.rmi.rmic.iiop.Constants.IDL_SHORT;
                break;
            case 4:
                str2 = "int";
                break;
            case 5:
                str2 = sun.rmi.rmic.iiop.Constants.IDL_INT;
                break;
            case 6:
                str2 = sun.rmi.rmic.iiop.Constants.IDL_FLOAT;
                break;
            case 7:
                str2 = sun.rmi.rmic.iiop.Constants.IDL_DOUBLE;
                break;
            case 8:
                str2 = "null";
                break;
            case 9:
            case 10:
            case 12:
            default:
                str2 = "unknown";
                break;
            case 11:
                str2 = sun.rmi.rmic.iiop.Constants.IDL_VOID;
                break;
            case 13:
                str2 = "<error>";
                if (this == tPackage) {
                    str2 = "<package>";
                    break;
                }
                break;
        }
        return str.length() > 0 ? str2 + " " + str : str2;
    }

    public String typeString(String str) {
        return typeString(str, false, true);
    }

    public String toString() {
        return typeString("", false, true);
    }
}
