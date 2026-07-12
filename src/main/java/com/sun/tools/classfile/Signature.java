package com.sun.tools.classfile;

import com.sun.tools.classfile.Type;
import java.util.ArrayList;
import java.util.List;
import jdk.internal.dynalink.CallSiteDescriptor;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/classfile/Signature.class */
public class Signature extends Descriptor {
    private String sig;
    private int sigp;
    private Type type;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !Signature.class.desiredAssertionStatus();
    }

    public Signature(int i) {
        super(i);
    }

    public Type getType(ConstantPool constantPool) throws ConstantPoolException {
        if (this.type == null) {
            this.type = parse(getValue(constantPool));
        }
        return this.type;
    }

    @Override // com.sun.tools.classfile.Descriptor
    public int getParameterCount(ConstantPool constantPool) throws ConstantPoolException {
        return ((Type.MethodType) getType(constantPool)).paramTypes.size();
    }

    @Override // com.sun.tools.classfile.Descriptor
    public String getParameterTypes(ConstantPool constantPool) throws ConstantPoolException {
        Type.MethodType methodType = (Type.MethodType) getType(constantPool);
        StringBuilder sb = new StringBuilder();
        sb.append(RuntimeConstants.SIG_METHOD);
        String str = "";
        for (Type type : methodType.paramTypes) {
            sb.append(str);
            sb.append(type);
            str = ", ";
        }
        sb.append(RuntimeConstants.SIG_ENDMETHOD);
        return sb.toString();
    }

    @Override // com.sun.tools.classfile.Descriptor
    public String getReturnType(ConstantPool constantPool) throws ConstantPoolException {
        return ((Type.MethodType) getType(constantPool)).returnType.toString();
    }

    @Override // com.sun.tools.classfile.Descriptor
    public String getFieldType(ConstantPool constantPool) throws ConstantPoolException {
        return getType(constantPool).toString();
    }

    private Type parse(String str) {
        this.sig = str;
        this.sigp = 0;
        List<Type.TypeParamType> list = null;
        if (str.charAt(this.sigp) == '<') {
            list = parseTypeParamTypes();
        }
        if (str.charAt(this.sigp) == '(') {
            List<Type> parseTypeSignatures = parseTypeSignatures(')');
            Type parseTypeSignature = parseTypeSignature();
            ArrayList arrayList = null;
            while (this.sigp < str.length() && str.charAt(this.sigp) == '^') {
                this.sigp++;
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(parseTypeSignature());
            }
            return new Type.MethodType(list, parseTypeSignatures, parseTypeSignature, arrayList);
        }
        Type parseTypeSignature2 = parseTypeSignature();
        if (list == null && this.sigp == str.length()) {
            return parseTypeSignature2;
        }
        ArrayList arrayList2 = null;
        while (this.sigp < str.length()) {
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
            }
            arrayList2.add(parseTypeSignature());
        }
        return new Type.ClassSigType(list, parseTypeSignature2, arrayList2);
    }

    private Type parseTypeSignature() {
        switch (this.sig.charAt(this.sigp)) {
            case '*':
                this.sigp++;
                return new Type.WildcardType();
            case '+':
                this.sigp++;
                return new Type.WildcardType(Type.WildcardType.Kind.EXTENDS, parseTypeSignature());
            case ',':
            case '.':
            case '/':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case 'A':
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
                throw new IllegalStateException(debugInfo());
            case '-':
                this.sigp++;
                return new Type.WildcardType(Type.WildcardType.Kind.SUPER, parseTypeSignature());
            case 'B':
                this.sigp++;
                return new Type.SimpleType("byte");
            case 'C':
                this.sigp++;
                return new Type.SimpleType("char");
            case 'D':
                this.sigp++;
                return new Type.SimpleType(Constants.IDL_DOUBLE);
            case 'F':
                this.sigp++;
                return new Type.SimpleType(Constants.IDL_FLOAT);
            case 'I':
                this.sigp++;
                return new Type.SimpleType("int");
            case 'J':
                this.sigp++;
                return new Type.SimpleType(Constants.IDL_INT);
            case 'L':
                return parseClassTypeSignature();
            case 'S':
                this.sigp++;
                return new Type.SimpleType(Constants.IDL_SHORT);
            case 'T':
                return parseTypeVariableSignature();
            case 'V':
                this.sigp++;
                return new Type.SimpleType(Constants.IDL_VOID);
            case 'Z':
                this.sigp++;
                return new Type.SimpleType(Constants.IDL_BOOLEAN);
            case '[':
                this.sigp++;
                return new Type.ArrayType(parseTypeSignature());
        }
    }

    private List<Type> parseTypeSignatures(char c) {
        this.sigp++;
        ArrayList arrayList = new ArrayList();
        while (this.sig.charAt(this.sigp) != c) {
            arrayList.add(parseTypeSignature());
        }
        this.sigp++;
        return arrayList;
    }

    private Type parseClassTypeSignature() {
        if (!$assertionsDisabled && this.sig.charAt(this.sigp) != 'L') {
            throw new AssertionError();
        }
        this.sigp++;
        return parseClassTypeSignatureRest();
    }

    private Type parseClassTypeSignatureRest() {
        char charAt;
        StringBuilder sb = new StringBuilder();
        List<Type> list = null;
        Type.ClassType classType = null;
        do {
            charAt = this.sig.charAt(this.sigp);
            switch (charAt) {
                case '.':
                case ';':
                    this.sigp++;
                    classType = new Type.ClassType(classType, sb.toString(), list);
                    sb.setLength(0);
                    list = null;
                    break;
                case '<':
                    list = parseTypeSignatures('>');
                    break;
                default:
                    this.sigp++;
                    sb.append(charAt);
                    break;
            }
        } while (charAt != ';');
        return classType;
    }

    private List<Type.TypeParamType> parseTypeParamTypes() {
        if (!$assertionsDisabled && this.sig.charAt(this.sigp) != '<') {
            throw new AssertionError();
        }
        this.sigp++;
        ArrayList arrayList = new ArrayList();
        while (this.sig.charAt(this.sigp) != '>') {
            arrayList.add(parseTypeParamType());
        }
        this.sigp++;
        return arrayList;
    }

    private Type.TypeParamType parseTypeParamType() {
        int indexOf = this.sig.indexOf(CallSiteDescriptor.TOKEN_DELIMITER, this.sigp);
        String substring = this.sig.substring(this.sigp, indexOf);
        Type type = null;
        ArrayList arrayList = null;
        this.sigp = indexOf + 1;
        if (this.sig.charAt(this.sigp) != ':') {
            type = parseTypeSignature();
        }
        while (this.sig.charAt(this.sigp) == ':') {
            this.sigp++;
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(parseTypeSignature());
        }
        return new Type.TypeParamType(substring, type, arrayList);
    }

    private Type parseTypeVariableSignature() {
        this.sigp++;
        int indexOf = this.sig.indexOf(59, this.sigp);
        Type.SimpleType simpleType = new Type.SimpleType(this.sig.substring(this.sigp, indexOf));
        this.sigp = indexOf + 1;
        return simpleType;
    }

    private String debugInfo() {
        return this.sig.substring(0, this.sigp) + "!" + this.sig.charAt(this.sigp) + "!" + this.sig.substring(this.sigp + 1);
    }
}
