package org.spongepowered.asm.lib.util;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.TypePath;

/* loaded from: target.jar:org/spongepowered/asm/lib/util/TraceFieldVisitor.class */
public final class TraceFieldVisitor extends FieldVisitor {
    public final Printer p;

    public TraceFieldVisitor(Printer p) {
        this(null, p);
    }

    public TraceFieldVisitor(FieldVisitor fv, Printer p) {
        super(Opcodes.ASM5, fv);
        this.p = p;
    }

    @Override // org.spongepowered.asm.lib.FieldVisitor
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        Printer p = this.p.visitFieldAnnotation(desc, visible);
        AnnotationVisitor av = this.fv == null ? null : this.fv.visitAnnotation(desc, visible);
        return new TraceAnnotationVisitor(av, p);
    }

    @Override // org.spongepowered.asm.lib.FieldVisitor
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
        Printer p = this.p.visitFieldTypeAnnotation(typeRef, typePath, desc, visible);
        AnnotationVisitor av = this.fv == null ? null : this.fv.visitTypeAnnotation(typeRef, typePath, desc, visible);
        return new TraceAnnotationVisitor(av, p);
    }

    @Override // org.spongepowered.asm.lib.FieldVisitor
    public void visitAttribute(Attribute attr) {
        this.p.visitFieldAttribute(attr);
        super.visitAttribute(attr);
    }

    @Override // org.spongepowered.asm.lib.FieldVisitor
    public void visitEnd() {
        this.p.visitFieldEnd();
        super.visitEnd();
    }
}
