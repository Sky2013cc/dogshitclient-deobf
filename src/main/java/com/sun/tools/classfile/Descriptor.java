package com.sun.tools.classfile;

import java.io.IOException;
import okhttp3.internal.url._UrlKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/classfile/Descriptor.class */
public class Descriptor {
    public final int index;
    private int count;

    /* loaded from: target.jar:com/sun/tools/classfile/Descriptor$InvalidDescriptor.class */
    public static class InvalidDescriptor extends DescriptorException {
        private static final long serialVersionUID = 1;
        public final String desc;
        public final int index;

        InvalidDescriptor(String str) {
            this.desc = str;
            this.index = -1;
        }

        InvalidDescriptor(String str, int i) {
            this.desc = str;
            this.index = i;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            if (this.index == -1) {
                return "invalid descriptor \"" + this.desc + OperatorName.SHOW_TEXT_LINE_AND_SPACE;
            }
            return "descriptor is invalid at offset " + this.index + " in \"" + this.desc + OperatorName.SHOW_TEXT_LINE_AND_SPACE;
        }
    }

    public Descriptor(ClassReader classReader) throws IOException {
        this(classReader.readUnsignedShort());
    }

    public Descriptor(int i) {
        this.index = i;
    }

    public String getValue(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getUTF8Value(this.index);
    }

    public int getParameterCount(ConstantPool constantPool) throws ConstantPoolException, InvalidDescriptor {
        String value = getValue(constantPool);
        int indexOf = value.indexOf(RuntimeConstants.SIG_ENDMETHOD);
        if (indexOf == -1) {
            throw new InvalidDescriptor(value);
        }
        parse(value, 0, indexOf + 1);
        return this.count;
    }

    public String getParameterTypes(ConstantPool constantPool) throws ConstantPoolException, InvalidDescriptor {
        String value = getValue(constantPool);
        int indexOf = value.indexOf(RuntimeConstants.SIG_ENDMETHOD);
        if (indexOf == -1) {
            throw new InvalidDescriptor(value);
        }
        return parse(value, 0, indexOf + 1);
    }

    public String getReturnType(ConstantPool constantPool) throws ConstantPoolException, InvalidDescriptor {
        String value = getValue(constantPool);
        int indexOf = value.indexOf(RuntimeConstants.SIG_ENDMETHOD);
        if (indexOf == -1) {
            throw new InvalidDescriptor(value);
        }
        return parse(value, indexOf + 1, value.length());
    }

    public String getFieldType(ConstantPool constantPool) throws ConstantPoolException, InvalidDescriptor {
        String value = getValue(constantPool);
        return parse(value, 0, value.length());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0026. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:17:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01cc A[LOOP:1: B:21:0x01c7->B:23:0x01cc, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String parse(String str, int i, int i2) throws InvalidDescriptor {
        String str2;
        int i3 = i;
        StringBuilder sb = new StringBuilder();
        int i4 = 0;
        this.count = 0;
        while (i3 < i2) {
            int i5 = i3;
            i3++;
            switch (str.charAt(i5)) {
                case '(':
                    sb.append('(');
                case ')':
                    sb.append(')');
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
                    throw new InvalidDescriptor(str, i3 - 1);
                case 'B':
                    str2 = "byte";
                    if (sb.length() > 1 && sb.charAt(0) == '(') {
                        sb.append(", ");
                    }
                    sb.append(str2);
                    while (i4 > 0) {
                        sb.append(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI);
                        i4--;
                    }
                    this.count++;
                    break;
                case 'C':
                    str2 = "char";
                    if (sb.length() > 1) {
                        sb.append(", ");
                        break;
                    }
                    sb.append(str2);
                    while (i4 > 0) {
                    }
                    this.count++;
                case 'D':
                    str2 = Constants.IDL_DOUBLE;
                    if (sb.length() > 1) {
                    }
                    sb.append(str2);
                    while (i4 > 0) {
                    }
                    this.count++;
                    break;
                case 'F':
                    str2 = Constants.IDL_FLOAT;
                    if (sb.length() > 1) {
                    }
                    sb.append(str2);
                    while (i4 > 0) {
                    }
                    this.count++;
                    break;
                case 'I':
                    str2 = "int";
                    if (sb.length() > 1) {
                    }
                    sb.append(str2);
                    while (i4 > 0) {
                    }
                    this.count++;
                    break;
                case 'J':
                    str2 = Constants.IDL_INT;
                    if (sb.length() > 1) {
                    }
                    sb.append(str2);
                    while (i4 > 0) {
                    }
                    this.count++;
                    break;
                case 'L':
                    int indexOf = str.indexOf(59, i3);
                    if (indexOf == -1) {
                        throw new InvalidDescriptor(str, i3 - 1);
                    }
                    str2 = str.substring(i3, indexOf).replace('/', '.');
                    i3 = indexOf + 1;
                    if (sb.length() > 1) {
                    }
                    sb.append(str2);
                    while (i4 > 0) {
                    }
                    this.count++;
                    break;
                case 'S':
                    str2 = Constants.IDL_SHORT;
                    if (sb.length() > 1) {
                    }
                    sb.append(str2);
                    while (i4 > 0) {
                    }
                    this.count++;
                    break;
                case 'V':
                    str2 = Constants.IDL_VOID;
                    if (sb.length() > 1) {
                    }
                    sb.append(str2);
                    while (i4 > 0) {
                    }
                    this.count++;
                    break;
                case 'Z':
                    str2 = Constants.IDL_BOOLEAN;
                    if (sb.length() > 1) {
                    }
                    sb.append(str2);
                    while (i4 > 0) {
                    }
                    this.count++;
                    break;
                case '[':
                    i4++;
            }
        }
        return sb.toString();
    }
}
