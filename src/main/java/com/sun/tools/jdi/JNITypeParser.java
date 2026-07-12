package com.sun.tools.jdi;

import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/jdi/JNITypeParser.class */
public class JNITypeParser {
    static final char SIGNATURE_ENDCLASS = ';';
    static final char SIGNATURE_FUNC = '(';
    static final char SIGNATURE_ENDFUNC = ')';
    private String signature;
    private List<String> typeNameList;
    private List<String> signatureList;
    private int currentIndex;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JNITypeParser(String str) {
        this.signature = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String typeNameToSignature(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        int indexOf = str.indexOf(91);
        int i = indexOf;
        while (true) {
            int i2 = i;
            if (i2 == -1) {
                break;
            }
            stringBuffer.append('[');
            i = str.indexOf(91, i2 + 1);
        }
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        if (str.equals(Constants.IDL_BOOLEAN)) {
            stringBuffer.append('Z');
        } else if (str.equals("byte")) {
            stringBuffer.append('B');
        } else if (str.equals("char")) {
            stringBuffer.append('C');
        } else if (str.equals(Constants.IDL_SHORT)) {
            stringBuffer.append('S');
        } else if (str.equals("int")) {
            stringBuffer.append('I');
        } else if (str.equals(Constants.IDL_INT)) {
            stringBuffer.append('J');
        } else if (str.equals(Constants.IDL_FLOAT)) {
            stringBuffer.append('F');
        } else if (str.equals(Constants.IDL_DOUBLE)) {
            stringBuffer.append('D');
        } else {
            stringBuffer.append('L');
            stringBuffer.append(str.replace('.', '/'));
            stringBuffer.append(';');
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String typeName() {
        return typeNameList().get(typeNameList().size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> argumentTypeNames() {
        return typeNameList().subList(0, typeNameList().size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String signature() {
        return signatureList().get(signatureList().size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> argumentSignatures() {
        return signatureList().subList(0, signatureList().size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int dimensionCount() {
        int i = 0;
        while (signature().charAt(i) == '[') {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String componentSignature(int i) {
        return signature().substring(i);
    }

    private synchronized List<String> signatureList() {
        if (this.signatureList == null) {
            this.signatureList = new ArrayList(10);
            this.currentIndex = 0;
            while (this.currentIndex < this.signature.length()) {
                this.signatureList.add(nextSignature());
            }
            if (this.signatureList.size() == 0) {
                throw new IllegalArgumentException("Invalid JNI signature '" + this.signature + OperatorName.SHOW_TEXT_LINE);
            }
        }
        return this.signatureList;
    }

    private synchronized List<String> typeNameList() {
        if (this.typeNameList == null) {
            this.typeNameList = new ArrayList(10);
            this.currentIndex = 0;
            while (this.currentIndex < this.signature.length()) {
                this.typeNameList.add(nextTypeName());
            }
            if (this.typeNameList.size() == 0) {
                throw new IllegalArgumentException("Invalid JNI signature '" + this.signature + OperatorName.SHOW_TEXT_LINE);
            }
        }
        return this.typeNameList;
    }

    private String nextSignature() {
        String str = this.signature;
        int i = this.currentIndex;
        this.currentIndex = i + 1;
        char charAt = str.charAt(i);
        switch (charAt) {
            case '(':
            case ')':
                return nextSignature();
            case '*':
            case '+':
            case ',':
            case '-':
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
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
                throw new IllegalArgumentException("Invalid JNI signature character '" + charAt + OperatorName.SHOW_TEXT_LINE);
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'V':
            case 'Z':
                return String.valueOf(charAt);
            case 'L':
                int indexOf = this.signature.indexOf(59, this.currentIndex);
                String substring = this.signature.substring(this.currentIndex - 1, indexOf + 1);
                this.currentIndex = indexOf + 1;
                return substring;
            case '[':
                return charAt + nextSignature();
        }
    }

    private String nextTypeName() {
        String str = this.signature;
        int i = this.currentIndex;
        this.currentIndex = i + 1;
        char charAt = str.charAt(i);
        switch (charAt) {
            case '(':
            case ')':
                return nextTypeName();
            case '*':
            case '+':
            case ',':
            case '-':
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
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
                throw new IllegalArgumentException("Invalid JNI signature character '" + charAt + OperatorName.SHOW_TEXT_LINE);
            case 'B':
                return "byte";
            case 'C':
                return "char";
            case 'D':
                return Constants.IDL_DOUBLE;
            case 'F':
                return Constants.IDL_FLOAT;
            case 'I':
                return "int";
            case 'J':
                return Constants.IDL_INT;
            case 'L':
                int indexOf = this.signature.indexOf(59, this.currentIndex);
                String replace = this.signature.substring(this.currentIndex, indexOf).replace('/', '.');
                this.currentIndex = indexOf + 1;
                return replace;
            case 'S':
                return Constants.IDL_SHORT;
            case 'V':
                return Constants.IDL_VOID;
            case 'Z':
                return Constants.IDL_BOOLEAN;
            case '[':
                return nextTypeName() + _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }
    }
}
