package com.sun.tools.javah;

import com.sun.tools.javah.TypeSignature;
import com.sun.tools.javah.Util;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.SimpleTypeVisitor8;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/javah/LLNI.class */
public class LLNI extends Gen {
    protected final char innerDelim = '$';
    protected Set<String> doneHandleTypes;
    List<VariableElement> fields;
    List<ExecutableElement> methods;
    private boolean doubleAlign;
    private int padFieldNum;
    private static final boolean isWindows = System.getProperty("os.name").startsWith("Windows");

    /* JADX INFO: Access modifiers changed from: package-private */
    public LLNI(boolean z, Util util) {
        super(util);
        this.innerDelim = '$';
        this.padFieldNum = 0;
        this.doubleAlign = z;
    }

    @Override // com.sun.tools.javah.Gen
    protected String getIncludes() {
        return "";
    }

    @Override // com.sun.tools.javah.Gen
    protected void write(OutputStream outputStream, TypeElement typeElement) throws Util.Exit {
        try {
            String mangleClassName = mangleClassName(typeElement.getQualifiedName().toString());
            PrintWriter wrapWriter = wrapWriter(outputStream);
            this.fields = ElementFilter.fieldsIn(typeElement.getEnclosedElements());
            this.methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
            generateDeclsForClass(wrapWriter, typeElement, mangleClassName);
        } catch (TypeSignature.SignatureException e) {
            this.util.error("llni.sigerror", e.getMessage());
        }
    }

    protected void generateDeclsForClass(PrintWriter printWriter, TypeElement typeElement, String str) throws TypeSignature.SignatureException, Util.Exit {
        this.doneHandleTypes = new HashSet();
        genHandleType(null, "java.lang.Class");
        genHandleType(null, "java.lang.ClassLoader");
        genHandleType(null, "java.lang.Object");
        genHandleType(null, "java.lang.String");
        genHandleType(null, "java.lang.Thread");
        genHandleType(null, "java.lang.ThreadGroup");
        genHandleType(null, "java.lang.Throwable");
        printWriter.println("/* LLNI Header for class " + typeElement.getQualifiedName() + " */" + this.lineSep);
        printWriter.println("#ifndef _Included_" + str);
        printWriter.println("#define _Included_" + str);
        printWriter.println("#include \"typedefs.h\"");
        printWriter.println("#include \"llni.h\"");
        printWriter.println("#include \"jni.h\"" + this.lineSep);
        forwardDecls(printWriter, typeElement);
        structSectionForClass(printWriter, typeElement, str);
        methodSectionForClass(printWriter, typeElement, str);
        printWriter.println("#endif");
    }

    protected void genHandleType(PrintWriter printWriter, String str) {
        String mangleClassName = mangleClassName(str);
        if (!this.doneHandleTypes.contains(mangleClassName)) {
            this.doneHandleTypes.add(mangleClassName);
            if (printWriter != null) {
                printWriter.println("#ifndef DEFINED_" + mangleClassName);
                printWriter.println("    #define DEFINED_" + mangleClassName);
                printWriter.println("    GEN_HANDLE_TYPES(" + mangleClassName + ");");
                printWriter.println("#endif" + this.lineSep);
            }
        }
    }

    protected String mangleClassName(String str) {
        return str.replace('.', '_').replace('/', '_').replace('$', '_');
    }

    protected void forwardDecls(PrintWriter printWriter, TypeElement typeElement) throws TypeSignature.SignatureException {
        if (typeElement.equals(this.elems.getTypeElement("java.lang.Object"))) {
            return;
        }
        genHandleType(printWriter, typeElement.getQualifiedName().toString());
        TypeElement typeElement2 = (TypeElement) this.types.asElement(typeElement.getSuperclass());
        if (typeElement2 != null) {
            typeElement2.getQualifiedName().toString();
            forwardDecls(printWriter, typeElement2);
        }
        for (VariableElement variableElement : this.fields) {
            if (!variableElement.getModifiers().contains(Modifier.STATIC)) {
                TypeMirror erasure = this.types.erasure(variableElement.asType());
                TypeSignature typeSignature = new TypeSignature(this.elems);
                String typeSignature2 = typeSignature.getTypeSignature(typeSignature.qualifiedTypeName(erasure));
                if (typeSignature2.charAt(0) != '[') {
                    forwardDeclsFromSig(printWriter, typeSignature2);
                }
            }
        }
        for (ExecutableElement executableElement : this.methods) {
            if (executableElement.getModifiers().contains(Modifier.NATIVE)) {
                TypeMirror erasure2 = this.types.erasure(executableElement.getReturnType());
                String typeSignature3 = new TypeSignature(this.elems).getTypeSignature(signature(executableElement), erasure2);
                if (typeSignature3.charAt(0) != '[') {
                    forwardDeclsFromSig(printWriter, typeSignature3);
                }
            }
        }
    }

    protected void forwardDeclsFromSig(PrintWriter printWriter, String str) {
        int length = str.length();
        int i = str.charAt(0) == '(' ? 1 : 0;
        while (i < length) {
            if (str.charAt(i) == 'L') {
                int i2 = i + 1;
                while (str.charAt(i2) != ';') {
                    i2++;
                }
                genHandleType(printWriter, str.substring(i + 1, i2));
                i = i2 + 1;
            } else {
                i++;
            }
        }
    }

    protected void structSectionForClass(PrintWriter printWriter, TypeElement typeElement, String str) {
        String obj = typeElement.getQualifiedName().toString();
        if (str.equals("java_lang_Object")) {
            printWriter.println("/* struct java_lang_Object is defined in typedefs.h. */");
            printWriter.println();
            return;
        }
        printWriter.println("#if !defined(__i386)");
        printWriter.println("#pragma pack(4)");
        printWriter.println("#endif");
        printWriter.println();
        printWriter.println("struct " + str + " {");
        printWriter.println("    ObjHeader h;");
        printWriter.print(fieldDefs(typeElement, str));
        if (obj.equals("java.lang.Class")) {
            printWriter.println("    Class *LLNI_mask(cClass);  /* Fake field; don't access (see oobj.h) */");
        }
        printWriter.println("};" + this.lineSep + this.lineSep + "#pragma pack()");
        printWriter.println();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/javah/LLNI$FieldDefsRes.class */
    public static class FieldDefsRes {
        public String className;
        public FieldDefsRes parent;
        public String s;
        public int byteSize;
        public boolean bottomMost;
        public boolean printedOne = false;

        FieldDefsRes(TypeElement typeElement, FieldDefsRes fieldDefsRes, boolean z) {
            this.className = typeElement.getQualifiedName().toString();
            this.parent = fieldDefsRes;
            this.bottomMost = z;
            if (fieldDefsRes != null) {
                this.s = fieldDefsRes.s;
            } else {
                this.s = "";
            }
        }
    }

    private boolean doField(FieldDefsRes fieldDefsRes, VariableElement variableElement, String str, boolean z) {
        String addStructMember = addStructMember(variableElement, str, z);
        if (addStructMember != null) {
            if (!fieldDefsRes.printedOne) {
                if (fieldDefsRes.bottomMost) {
                    if (fieldDefsRes.s.length() != 0) {
                        fieldDefsRes.s += "    /* local members: */" + this.lineSep;
                    }
                } else {
                    fieldDefsRes.s += "    /* inherited members from " + fieldDefsRes.className + ": */" + this.lineSep;
                }
                fieldDefsRes.printedOne = true;
            }
            fieldDefsRes.s += addStructMember;
            return true;
        }
        return false;
    }

    private int doTwoWordFields(FieldDefsRes fieldDefsRes, TypeElement typeElement, int i, String str, boolean z) {
        boolean z2 = true;
        for (VariableElement variableElement : ElementFilter.fieldsIn(typeElement.getEnclosedElements())) {
            TypeKind kind = variableElement.asType().getKind();
            if (kind == TypeKind.LONG || kind == TypeKind.DOUBLE) {
                if (doField(fieldDefsRes, variableElement, str, z2 && z)) {
                    i += 8;
                    z2 = false;
                }
            }
        }
        return i;
    }

    String fieldDefs(TypeElement typeElement, String str) {
        return fieldDefs(typeElement, str, true).s;
    }

    FieldDefsRes fieldDefs(TypeElement typeElement, String str, boolean z) {
        FieldDefsRes fieldDefsRes;
        int i;
        boolean z2 = false;
        TypeElement typeElement2 = (TypeElement) this.types.asElement(typeElement.getSuperclass());
        if (typeElement2 != null) {
            typeElement2.getQualifiedName().toString();
            fieldDefsRes = new FieldDefsRes(typeElement, fieldDefs(typeElement2, str, false), z);
            i = fieldDefsRes.parent.byteSize;
        } else {
            fieldDefsRes = new FieldDefsRes(typeElement, null, z);
            i = 0;
        }
        for (VariableElement variableElement : ElementFilter.fieldsIn(typeElement.getEnclosedElements())) {
            if (this.doubleAlign && !z2 && i % 8 == 0) {
                i = doTwoWordFields(fieldDefsRes, typeElement, i, str, false);
                z2 = true;
            }
            TypeKind kind = variableElement.asType().getKind();
            boolean z3 = kind == TypeKind.LONG || kind == TypeKind.DOUBLE;
            if (!this.doubleAlign || !z3) {
                if (doField(fieldDefsRes, variableElement, str, false)) {
                    i += 4;
                }
            }
        }
        if (this.doubleAlign && !z2) {
            if (i % 8 != 0) {
                i += 4;
            }
            i = doTwoWordFields(fieldDefsRes, typeElement, i, str, true);
        }
        fieldDefsRes.byteSize = i;
        return fieldDefsRes;
    }

    protected String addStructMember(VariableElement variableElement, String str, boolean z) {
        String str2;
        if (variableElement.getModifiers().contains(Modifier.STATIC)) {
            str2 = addStaticStructMember(variableElement, str);
        } else {
            TypeMirror erasure = this.types.erasure(variableElement.asType());
            if (z) {
                StringBuilder append = new StringBuilder().append("    java_int padWord");
                int i = this.padFieldNum;
                this.padFieldNum = i + 1;
                append.append(i).append(RuntimeConstants.SIG_ENDCLASS).append(this.lineSep).toString();
            }
            String str3 = "    " + llniType(erasure, false, false) + " " + llniFieldName(variableElement);
            if (isLongOrDouble(erasure)) {
                str3 = str3 + "[2]";
            }
            str2 = str3 + RuntimeConstants.SIG_ENDCLASS + this.lineSep;
        }
        return str2;
    }

    protected String addStaticStructMember(VariableElement variableElement, String str) {
        String str2 = null;
        if (variableElement.getModifiers().contains(Modifier.STATIC) && variableElement.getModifiers().contains(Modifier.FINAL)) {
            Object constantValue = variableElement.getConstantValue();
            if (constantValue != null) {
                String str3 = str + "_" + variableElement.getSimpleName();
                String str4 = null;
                long j = 0;
                if ((constantValue instanceof Byte) || (constantValue instanceof Short) || (constantValue instanceof Integer)) {
                    str4 = "L";
                    j = ((Number) constantValue).intValue();
                } else if (constantValue instanceof Long) {
                    str4 = isWindows ? "i64" : "LL";
                    j = ((Long) constantValue).longValue();
                } else if (constantValue instanceof Float) {
                    str4 = "f";
                } else if (constantValue instanceof Double) {
                    str4 = "";
                } else if (constantValue instanceof Character) {
                    str4 = "L";
                    j = ((Character) constantValue).charValue() & 65535;
                }
                if (str4 != null) {
                    str2 = ((str4.equals("L") && j == -2147483648L) || (str4.equals("LL") && j == Long.MIN_VALUE)) ? "    #undef  " + str3 + this.lineSep + "    #define " + str3 + " (" + (j + 1) + str4 + "-1)" + this.lineSep : (str4.equals("L") || str4.endsWith("LL")) ? "    #undef  " + str3 + this.lineSep + "    #define " + str3 + " " + j + str4 + this.lineSep : "    #undef  " + str3 + this.lineSep + "    #define " + str3 + " " + constantValue + str4 + this.lineSep;
                }
            }
            return str2;
        }
        return null;
    }

    protected void methodSectionForClass(PrintWriter printWriter, TypeElement typeElement, String str) throws TypeSignature.SignatureException, Util.Exit {
        String methodDecls = methodDecls(typeElement, str);
        if (methodDecls.length() != 0) {
            printWriter.println("/* Native method declarations: */" + this.lineSep);
            printWriter.println("#ifdef __cplusplus");
            printWriter.println("extern \"C\" {");
            printWriter.println("#endif" + this.lineSep);
            printWriter.println(methodDecls);
            printWriter.println("#ifdef __cplusplus");
            printWriter.println("}");
            printWriter.println("#endif");
        }
    }

    protected String methodDecls(TypeElement typeElement, String str) throws TypeSignature.SignatureException, Util.Exit {
        String str2 = "";
        for (ExecutableElement executableElement : this.methods) {
            if (executableElement.getModifiers().contains(Modifier.NATIVE)) {
                str2 = str2 + methodDecl(executableElement, typeElement, str);
            }
        }
        return str2;
    }

    protected String methodDecl(ExecutableElement executableElement, TypeElement typeElement, String str) throws TypeSignature.SignatureException, Util.Exit {
        TypeMirror erasure = this.types.erasure(executableElement.getReturnType());
        String typeSignature = new TypeSignature(this.elems).getTypeSignature(signature(executableElement), erasure);
        boolean needLongName = needLongName(executableElement, typeElement);
        if (typeSignature.charAt(0) != '(') {
            this.util.error("invalid.method.signature", typeSignature);
        }
        String str2 = "JNIEXPORT " + jniType(erasure) + " JNICALL" + this.lineSep + jniMethodName(executableElement, str, needLongName) + "(JNIEnv *, " + cRcvrDecl(executableElement, str);
        List parameters = executableElement.getParameters();
        ArrayList arrayList = new ArrayList();
        Iterator it = parameters.iterator();
        while (it.hasNext()) {
            arrayList.add(this.types.erasure(((VariableElement) it.next()).asType()));
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            str2 = str2 + ", " + jniType((TypeMirror) it2.next());
        }
        return str2 + ");" + this.lineSep;
    }

    protected final boolean needLongName(ExecutableElement executableElement, TypeElement typeElement) {
        Name simpleName = executableElement.getSimpleName();
        for (ExecutableElement executableElement2 : this.methods) {
            if (executableElement2 != executableElement && executableElement2.getModifiers().contains(Modifier.NATIVE) && simpleName.equals(executableElement2.getSimpleName())) {
                return true;
            }
        }
        return false;
    }

    protected final String jniMethodName(ExecutableElement executableElement, String str, boolean z) throws TypeSignature.SignatureException {
        String str2 = "Java_" + str + "_" + executableElement.getSimpleName();
        if (z) {
            this.types.erasure(executableElement.getReturnType());
            List parameters = executableElement.getParameters();
            ArrayList arrayList = new ArrayList();
            Iterator it = parameters.iterator();
            while (it.hasNext()) {
                arrayList.add(this.types.erasure(((VariableElement) it.next()).asType()));
            }
            str2 = str2 + "__";
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                str2 = str2 + nameToIdentifier(new TypeSignature(this.elems).getTypeSignature(((TypeMirror) it2.next()).toString()));
            }
        }
        return str2;
    }

    protected final String jniType(TypeMirror typeMirror) throws Util.Exit {
        TypeElement typeElement = this.elems.getTypeElement("java.lang.Throwable");
        TypeElement typeElement2 = this.elems.getTypeElement("java.lang.Class");
        TypeElement typeElement3 = this.elems.getTypeElement("java.lang.String");
        Element asElement = this.types.asElement(typeMirror);
        switch (AnonymousClass2.$SwitchMap$javax$lang$model$type$TypeKind[typeMirror.getKind().ordinal()]) {
            case 1:
                return "jboolean";
            case 2:
                return "jbyte";
            case 3:
                return "jchar";
            case 4:
                return "jshort";
            case 5:
                return "jint";
            case 6:
                return "jlong";
            case 7:
                return "jfloat";
            case 8:
                return "jdouble";
            case 9:
                TypeMirror componentType = ((ArrayType) typeMirror).getComponentType();
                switch (AnonymousClass2.$SwitchMap$javax$lang$model$type$TypeKind[componentType.getKind().ordinal()]) {
                    case 1:
                        return "jbooleanArray";
                    case 2:
                        return "jbyteArray";
                    case 3:
                        return "jcharArray";
                    case 4:
                        return "jshortArray";
                    case 5:
                        return "jintArray";
                    case 6:
                        return "jlongArray";
                    case 7:
                        return "jfloatArray";
                    case 8:
                        return "jdoubleArray";
                    case 9:
                    case 10:
                        return "jobjectArray";
                    default:
                        throw new Error(componentType.toString());
                }
            case 10:
                if (asElement.equals(typeElement3)) {
                    return "jstring";
                }
                if (this.types.isAssignable(typeMirror, typeElement.asType())) {
                    return "jthrowable";
                }
                if (this.types.isAssignable(typeMirror, typeElement2.asType())) {
                    return "jclass";
                }
                return "jobject";
            case 11:
                return Constants.IDL_VOID;
            default:
                this.util.bug("jni.unknown.type");
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sun.tools.javah.LLNI$2, reason: invalid class name */
    /* loaded from: target.jar:com/sun/tools/javah/LLNI$2.class */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$type$TypeKind = new int[TypeKind.values().length];

        static {
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.CHAR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.SHORT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.INT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.LONG.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.DOUBLE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.ARRAY.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.DECLARED.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.VOID.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    protected String llniType(TypeMirror typeMirror, boolean z, boolean z2) {
        String str;
        switch (AnonymousClass2.$SwitchMap$javax$lang$model$type$TypeKind[typeMirror.getKind().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                str = "java_int";
                break;
            case 6:
                str = z2 ? "java_long" : "val32 /* java_long */";
                break;
            case 7:
                str = "java_float";
                break;
            case 8:
                str = z2 ? "java_double" : "val32 /* java_double */";
                break;
            case 9:
                TypeMirror componentType = ((ArrayType) typeMirror).getComponentType();
                switch (AnonymousClass2.$SwitchMap$javax$lang$model$type$TypeKind[componentType.getKind().ordinal()]) {
                    case 1:
                        str = "IArrayOfBoolean";
                        break;
                    case 2:
                        str = "IArrayOfByte";
                        break;
                    case 3:
                        str = "IArrayOfChar";
                        break;
                    case 4:
                        str = "IArrayOfShort";
                        break;
                    case 5:
                        str = "IArrayOfInt";
                        break;
                    case 6:
                        str = "IArrayOfLong";
                        break;
                    case 7:
                        str = "IArrayOfFloat";
                        break;
                    case 8:
                        str = "IArrayOfDouble";
                        break;
                    case 9:
                    case 10:
                        str = "IArrayOfRef";
                        break;
                    default:
                        throw new Error(componentType.getKind() + " " + componentType);
                }
                if (!z) {
                    str = "DEREFERENCED_" + str;
                    break;
                }
                break;
            case 10:
                str = "I" + mangleClassName(this.types.asElement(typeMirror).getQualifiedName().toString());
                if (!z) {
                    str = "DEREFERENCED_" + str;
                    break;
                }
                break;
            case 11:
                str = Constants.IDL_VOID;
                break;
            default:
                throw new Error(typeMirror.getKind() + " " + typeMirror);
        }
        return str;
    }

    protected final String cRcvrDecl(Element element, String str) {
        return element.getModifiers().contains(Modifier.STATIC) ? "jclass" : "jobject";
    }

    protected String maskName(String str) {
        return "LLNI_mask(" + str + RuntimeConstants.SIG_ENDMETHOD;
    }

    protected String llniFieldName(VariableElement variableElement) {
        return maskName(variableElement.getSimpleName().toString());
    }

    protected final boolean isLongOrDouble(TypeMirror typeMirror) {
        return ((Boolean) new SimpleTypeVisitor8<Boolean, Void>() { // from class: com.sun.tools.javah.LLNI.1
            public Boolean defaultAction(TypeMirror typeMirror2, Void r4) {
                return false;
            }

            public Boolean visitArray(ArrayType arrayType, Void r6) {
                return (Boolean) visit(arrayType.getComponentType(), r6);
            }

            public Boolean visitPrimitive(PrimitiveType primitiveType, Void r5) {
                TypeKind kind = primitiveType.getKind();
                return Boolean.valueOf(kind == TypeKind.LONG || kind == TypeKind.DOUBLE);
            }
        }.visit(typeMirror, (Object) null)).booleanValue();
    }

    protected final String nameToIdentifier(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (isASCIILetterOrDigit(charAt)) {
                sb.append(charAt);
            } else if (charAt == '/') {
                sb.append('_');
            } else if (charAt == '.') {
                sb.append('_');
            } else if (charAt == '_') {
                sb.append("_1");
            } else if (charAt == ';') {
                sb.append("_2");
            } else if (charAt == '[') {
                sb.append("_3");
            } else {
                sb.append("_0" + ((int) charAt));
            }
        }
        return new String(sb);
    }

    protected final boolean isASCIILetterOrDigit(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
}
