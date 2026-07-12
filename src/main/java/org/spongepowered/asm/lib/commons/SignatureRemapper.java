package org.spongepowered.asm.lib.commons;

import java.util.Stack;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.signature.SignatureVisitor;

/* loaded from: target.jar:org/spongepowered/asm/lib/commons/SignatureRemapper.class */
public class SignatureRemapper extends SignatureVisitor {
    private final SignatureVisitor v;
    private final Remapper remapper;
    private Stack<String> classNames;

    public SignatureRemapper(SignatureVisitor v, Remapper remapper) {
        this(Opcodes.ASM5, v, remapper);
    }

    protected SignatureRemapper(int api, SignatureVisitor v, Remapper remapper) {
        super(api);
        this.classNames = new Stack<>();
        this.v = v;
        this.remapper = remapper;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitClassType(String name) {
        this.classNames.push(name);
        this.v.visitClassType(this.remapper.mapType(name));
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitInnerClassType(String name) {
        String outerClassName = this.classNames.pop();
        String className = outerClassName + '$' + name;
        this.classNames.push(className);
        String remappedOuter = this.remapper.mapType(outerClassName) + '$';
        String remappedName = this.remapper.mapType(className);
        int index = remappedName.startsWith(remappedOuter) ? remappedOuter.length() : remappedName.lastIndexOf(36) + 1;
        this.v.visitInnerClassType(remappedName.substring(index));
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitFormalTypeParameter(String name) {
        this.v.visitFormalTypeParameter(name);
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitTypeVariable(String name) {
        this.v.visitTypeVariable(name);
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitArrayType() {
        this.v.visitArrayType();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitBaseType(char descriptor) {
        this.v.visitBaseType(descriptor);
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitClassBound() {
        this.v.visitClassBound();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitExceptionType() {
        this.v.visitExceptionType();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitInterface() {
        this.v.visitInterface();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitInterfaceBound() {
        this.v.visitInterfaceBound();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitParameterType() {
        this.v.visitParameterType();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitReturnType() {
        this.v.visitReturnType();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitSuperclass() {
        this.v.visitSuperclass();
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitTypeArgument() {
        this.v.visitTypeArgument();
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public SignatureVisitor visitTypeArgument(char wildcard) {
        this.v.visitTypeArgument(wildcard);
        return this;
    }

    @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
    public void visitEnd() {
        this.v.visitEnd();
        this.classNames.pop();
    }
}
