package com.sun.tools.javah;

import com.sun.tools.doclint.DocLint;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleTypeVisitor8;
import okhttp3.internal.url._UrlKt;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/javah/TypeSignature.class */
public class TypeSignature {
    Elements elems;
    private static final String SIG_VOID = "V";
    private static final String SIG_BOOLEAN = "Z";
    private static final String SIG_BYTE = "B";
    private static final String SIG_CHAR = "C";
    private static final String SIG_SHORT = "S";
    private static final String SIG_INT = "I";
    private static final String SIG_LONG = "J";
    private static final String SIG_FLOAT = "F";
    private static final String SIG_DOUBLE = "D";
    private static final String SIG_ARRAY = "[";
    private static final String SIG_CLASS = "L";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javah/TypeSignature$SignatureException.class */
    public static class SignatureException extends Exception {
        private static final long serialVersionUID = 1;

        SignatureException(String str) {
            super(str);
        }
    }

    public TypeSignature(Elements elements) {
        this.elems = elements;
    }

    public String getTypeSignature(String str) throws SignatureException {
        return getParamJVMSignature(str);
    }

    public String getTypeSignature(String str, TypeMirror typeMirror) throws SignatureException {
        String str2 = null;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        if (str != null) {
            i2 = str.indexOf(RuntimeConstants.SIG_METHOD);
            i3 = str.indexOf(RuntimeConstants.SIG_ENDMETHOD);
        }
        if (i2 != -1 && i3 != -1 && i2 + 1 < str.length() && i3 < str.length()) {
            str2 = str.substring(i2 + 1, i3);
        }
        if (str2 != null) {
            if (str2.indexOf(DocLint.TAGS_SEPARATOR) != -1) {
                StringTokenizer stringTokenizer = new StringTokenizer(str2, DocLint.TAGS_SEPARATOR);
                if (stringTokenizer != null) {
                    while (stringTokenizer.hasMoreTokens()) {
                        arrayList.add(stringTokenizer.nextToken());
                    }
                }
            } else {
                arrayList.add(str2);
            }
        }
        String str3 = RuntimeConstants.SIG_METHOD;
        while (!arrayList.isEmpty()) {
            String paramJVMSignature = getParamJVMSignature(((String) arrayList.remove(0)).trim());
            if (paramJVMSignature != null) {
                str3 = str3 + paramJVMSignature;
            }
        }
        String str4 = str3 + RuntimeConstants.SIG_ENDMETHOD;
        String str5 = "";
        if (typeMirror != null) {
            i = dimensions(typeMirror);
        }
        while (true) {
            int i4 = i;
            i--;
            if (i4 <= 0) {
                break;
            }
            str5 = str5 + "[";
        }
        if (typeMirror != null) {
            str5 = str5 + getComponentType(qualifiedTypeName(typeMirror));
        } else {
            System.out.println("Invalid return type.");
        }
        return str4 + str5;
    }

    private String getParamJVMSignature(String str) throws SignatureException {
        String str2;
        String str3 = "";
        if (str != null) {
            if (str.indexOf(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI) != -1) {
                int indexOf = str.indexOf(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI);
                str2 = str.substring(0, indexOf);
                String substring = str.substring(indexOf);
                if (substring != null) {
                    while (substring.indexOf(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI) != -1) {
                        str3 = str3 + "[";
                        int indexOf2 = substring.indexOf("]") + 1;
                        if (indexOf2 < substring.length()) {
                            substring = substring.substring(indexOf2);
                        } else {
                            substring = "";
                        }
                    }
                }
            } else {
                str2 = str;
            }
            str3 = str3 + getComponentType(str2);
        }
        return str3;
    }

    private String getComponentType(String str) throws SignatureException {
        String str2 = "";
        if (str != null) {
            if (str.equals(Constants.IDL_VOID)) {
                str2 = str2 + "V";
            } else if (str.equals(Constants.IDL_BOOLEAN)) {
                str2 = str2 + "Z";
            } else if (str.equals("byte")) {
                str2 = str2 + "B";
            } else if (str.equals("char")) {
                str2 = str2 + "C";
            } else if (str.equals(Constants.IDL_SHORT)) {
                str2 = str2 + "S";
            } else if (str.equals("int")) {
                str2 = str2 + "I";
            } else if (str.equals(Constants.IDL_INT)) {
                str2 = str2 + "J";
            } else if (str.equals(Constants.IDL_FLOAT)) {
                str2 = str2 + "F";
            } else if (str.equals(Constants.IDL_DOUBLE)) {
                str2 = str2 + "D";
            } else if (!str.equals("")) {
                TypeElement typeElement = this.elems.getTypeElement(str);
                if (typeElement == null) {
                    throw new SignatureException(str);
                }
                str2 = ((str2 + "L") + typeElement.getQualifiedName().toString().replace('.', '/')) + RuntimeConstants.SIG_ENDCLASS;
            }
        }
        return str2;
    }

    int dimensions(TypeMirror typeMirror) {
        if (typeMirror.getKind() != TypeKind.ARRAY) {
            return 0;
        }
        return 1 + dimensions(((ArrayType) typeMirror).getComponentType());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String qualifiedTypeName(TypeMirror typeMirror) {
        return ((Name) new SimpleTypeVisitor8<Name, Void>() { // from class: com.sun.tools.javah.TypeSignature.1
            public Name visitArray(ArrayType arrayType, Void r6) {
                return (Name) arrayType.getComponentType().accept(this, r6);
            }

            public Name visitDeclared(DeclaredType declaredType, Void r4) {
                return declaredType.asElement().getQualifiedName();
            }

            public Name visitPrimitive(PrimitiveType primitiveType, Void r5) {
                return TypeSignature.this.elems.getName(primitiveType.toString());
            }

            public Name visitNoType(NoType noType, Void r6) {
                if (noType.getKind() == TypeKind.VOID) {
                    return TypeSignature.this.elems.getName(Constants.IDL_VOID);
                }
                return (Name) defaultAction(noType, r6);
            }

            public Name visitTypeVariable(TypeVariable typeVariable, Void r6) {
                return (Name) typeVariable.getUpperBound().accept(this, r6);
            }
        }.visit(typeMirror)).toString();
    }
}
