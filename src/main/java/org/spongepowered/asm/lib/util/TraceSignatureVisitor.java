package org.spongepowered.asm.lib.util;

import okhttp3.internal.url._UrlKt;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.signature.SignatureVisitor;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:org/spongepowered/asm/lib/util/TraceSignatureVisitor.class */
public final class TraceSignatureVisitor extends SignatureVisitor {
    private final StringBuilder declaration;
    private boolean isInterface;
    private boolean seenFormalParameter;
    private boolean seenInterfaceBound;
    private boolean seenParameter;
    private boolean seenInterface;
    private StringBuilder returnType;
    private StringBuilder exceptions;
    private int argumentStack;
    private int arrayStack;
    private String separator;

    public TraceSignatureVisitor(int access) {
        super(Opcodes.ASM5);
        this.separator = "";
        this.isInterface = (access & 512) != 0;
        this.declaration = new StringBuilder();
    }

    private TraceSignatureVisitor(StringBuilder buf) {
        super(Opcodes.ASM5);
        this.separator = "";
        this.declaration = buf;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitFormalTypeParameter(String name) {
        this.declaration.append(this.seenFormalParameter ? ", " : "<").append(name);
        this.seenFormalParameter = true;
        this.seenInterfaceBound = false;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitClassBound() {
        this.separator = " extends ";
        startType();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitInterfaceBound() {
        this.separator = this.seenInterfaceBound ? ", " : " extends ";
        this.seenInterfaceBound = true;
        startType();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitSuperclass() {
        endFormals();
        this.separator = " extends ";
        startType();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitInterface() {
        this.separator = this.seenInterface ? ", " : this.isInterface ? " extends " : " implements ";
        this.seenInterface = true;
        startType();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitParameterType() {
        endFormals();
        if (this.seenParameter) {
            this.declaration.append(", ");
        } else {
            this.seenParameter = true;
            this.declaration.append('(');
        }
        startType();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitReturnType() {
        endFormals();
        if (this.seenParameter) {
            this.seenParameter = false;
        } else {
            this.declaration.append('(');
        }
        this.declaration.append(')');
        this.returnType = new StringBuilder();
        return new TraceSignatureVisitor(this.returnType);
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitExceptionType() {
        if (this.exceptions == null) {
            this.exceptions = new StringBuilder();
        } else {
            this.exceptions.append(", ");
        }
        return new TraceSignatureVisitor(this.exceptions);
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitBaseType(char descriptor) {
        switch (descriptor) {
            case 'B':
                this.declaration.append("byte");
                break;
            case 'C':
                this.declaration.append("char");
                break;
            case 'D':
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'L':
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
                this.declaration.append(Constants.IDL_DOUBLE);
                break;
            case 'F':
                this.declaration.append(Constants.IDL_FLOAT);
                break;
            case 'I':
                this.declaration.append("int");
                break;
            case 'J':
                this.declaration.append(Constants.IDL_INT);
                break;
            case 'S':
                this.declaration.append(Constants.IDL_SHORT);
                break;
            case 'V':
                this.declaration.append(Constants.IDL_VOID);
                break;
            case 'Z':
                this.declaration.append(Constants.IDL_BOOLEAN);
                break;
        }
        endType();
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitTypeVariable(String name) {
        this.declaration.append(name);
        endType();
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitArrayType() {
        startType();
        this.arrayStack |= 1;
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitClassType(String name) {
        if ("java/lang/Object".equals(name)) {
            boolean needObjectClass = this.argumentStack % 2 != 0 || this.seenParameter;
            if (needObjectClass) {
                this.declaration.append(this.separator).append(name.replace('/', '.'));
            }
        } else {
            this.declaration.append(this.separator).append(name.replace('/', '.'));
        }
        this.separator = "";
        this.argumentStack *= 2;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitInnerClassType(String name) {
        if (this.argumentStack % 2 != 0) {
            this.declaration.append('>');
        }
        this.argumentStack /= 2;
        this.declaration.append('.');
        this.declaration.append(this.separator).append(name.replace('/', '.'));
        this.separator = "";
        this.argumentStack *= 2;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitTypeArgument() {
        if (this.argumentStack % 2 == 0) {
            this.argumentStack++;
            this.declaration.append('<');
        } else {
            this.declaration.append(", ");
        }
        this.declaration.append('?');
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitTypeArgument(char tag) {
        if (this.argumentStack % 2 == 0) {
            this.argumentStack++;
            this.declaration.append('<');
        } else {
            this.declaration.append(", ");
        }
        if (tag == '+') {
            this.declaration.append("? extends ");
        } else if (tag == '-') {
            this.declaration.append("? super ");
        }
        startType();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitEnd() {
        if (this.argumentStack % 2 != 0) {
            this.declaration.append('>');
        }
        this.argumentStack /= 2;
        endType();
    }

    public String getDeclaration() {
        return this.declaration.toString();
    }

    public String getReturnType() {
        if (this.returnType == null) {
            return null;
        }
        return this.returnType.toString();
    }

    public String getExceptions() {
        if (this.exceptions == null) {
            return null;
        }
        return this.exceptions.toString();
    }

    private void endFormals() {
        if (this.seenFormalParameter) {
            this.declaration.append('>');
            this.seenFormalParameter = false;
        }
    }

    private void startType() {
        this.arrayStack *= 2;
    }

    private void endType() {
        if (this.arrayStack % 2 == 0) {
            this.arrayStack /= 2;
            return;
        }
        while (this.arrayStack % 2 != 0) {
            this.arrayStack /= 2;
            this.declaration.append(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI);
        }
    }
}
