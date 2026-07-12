package com.sun.tools.javah;

import com.sun.tools.doclint.DocLint;
import com.sun.tools.javah.TypeSignature;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/javah/Mangle.class */
public class Mangle {
    private Elements elems;
    private Types types;

    /* loaded from: target.jar:com/sun/tools/javah/Mangle$Type.class */
    public static class Type {
        public static final int CLASS = 1;
        public static final int FIELDSTUB = 2;
        public static final int FIELD = 3;
        public static final int JNI = 4;
        public static final int SIGNATURE = 5;
        public static final int METHOD_JDK_1 = 6;
        public static final int METHOD_JNI_SHORT = 7;
        public static final int METHOD_JNI_LONG = 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Mangle(Elements elements, Types types) {
        this.elems = elements;
        this.types = types;
    }

    public final String mangle(CharSequence charSequence, int i) {
        StringBuilder sb = new StringBuilder(100);
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = charSequence.charAt(i2);
            if (isalnum(charAt)) {
                sb.append(charAt);
            } else if (charAt == '.' && i == 1) {
                sb.append('_');
            } else if (charAt == '$' && i == 1) {
                sb.append('_');
                sb.append('_');
            } else if (charAt == '_' && i == 2) {
                sb.append('_');
            } else if (charAt == '_' && i == 1) {
                sb.append('_');
            } else if (i == 4) {
                String str = null;
                if (charAt == '_') {
                    str = "_1";
                } else if (charAt == '.') {
                    str = "_";
                } else if (charAt == ';') {
                    str = "_2";
                } else if (charAt == '[') {
                    str = "_3";
                }
                if (str != null) {
                    sb.append(str);
                } else {
                    sb.append(mangleChar(charAt));
                }
            } else if (i == 5) {
                if (isprint(charAt)) {
                    sb.append(charAt);
                } else {
                    sb.append(mangleChar(charAt));
                }
            } else {
                sb.append(mangleChar(charAt));
            }
        }
        return sb.toString();
    }

    public String mangleMethod(ExecutableElement executableElement, TypeElement typeElement, int i) throws TypeSignature.SignatureException {
        StringBuilder sb = new StringBuilder(100);
        sb.append("Java_");
        if (i == 6) {
            sb.append(mangle(typeElement.getQualifiedName(), 1));
            sb.append('_');
            sb.append(mangle(executableElement.getSimpleName(), 3));
            sb.append("_stub");
            return sb.toString();
        }
        sb.append(mangle(getInnerQualifiedName(typeElement), 4));
        sb.append('_');
        sb.append(mangle(executableElement.getSimpleName(), 4));
        if (i == 8) {
            sb.append("__");
            String substring = new TypeSignature(this.elems).getTypeSignature(signature(executableElement), executableElement.getReturnType()).substring(1);
            sb.append(mangle(substring.substring(0, substring.lastIndexOf(41)).replace('/', '.'), 4));
        }
        return sb.toString();
    }

    private String getInnerQualifiedName(TypeElement typeElement) {
        return this.elems.getBinaryName(typeElement).toString();
    }

    public final String mangleChar(char c) {
        String hexString = Integer.toHexString(c);
        int length = 5 - hexString.length();
        char[] cArr = new char[6];
        cArr[0] = '_';
        for (int i = 1; i <= length; i++) {
            cArr[i] = '0';
        }
        int i2 = length + 1;
        int i3 = 0;
        while (i2 < 6) {
            cArr[i2] = hexString.charAt(i3);
            i2++;
            i3++;
        }
        return new String(cArr);
    }

    private String signature(ExecutableElement executableElement) {
        StringBuilder sb = new StringBuilder();
        String str = RuntimeConstants.SIG_METHOD;
        for (VariableElement variableElement : executableElement.getParameters()) {
            sb.append(str);
            sb.append(this.types.erasure(variableElement.asType()).toString());
            str = DocLint.TAGS_SEPARATOR;
        }
        sb.append(RuntimeConstants.SIG_ENDMETHOD);
        return sb.toString();
    }

    private static final boolean isalnum(char c) {
        return c <= 127 && ((c >= 'A' && c <= 'Z') || ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')));
    }

    private static final boolean isprint(char c) {
        return c >= ' ' && c <= '~';
    }
}
