package org.spongepowered.asm.lib.commons;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Opcodes;

/* loaded from: target.jar:org/spongepowered/asm/lib/commons/AnnotationRemapper.class */
public class AnnotationRemapper extends AnnotationVisitor {
    protected final Remapper remapper;

    public AnnotationRemapper(AnnotationVisitor av, Remapper remapper) {
        this(Opcodes.ASM5, av, remapper);
    }

    protected AnnotationRemapper(int api, AnnotationVisitor av, Remapper remapper) {
        super(api, av);
        this.remapper = remapper;
    }

    @Override // org.spongepowered.asm.lib.AnnotationVisitor
    public void visit(String name, Object value) {
        this.av.visit(name, this.remapper.mapValue(value));
    }

    @Override // org.spongepowered.asm.lib.AnnotationVisitor
    public void visitEnum(String name, String desc, String value) {
        this.av.visitEnum(name, this.remapper.mapDesc(desc), value);
    }

    @Override // org.spongepowered.asm.lib.AnnotationVisitor
    public AnnotationVisitor visitAnnotation(String name, String desc) {
        AnnotationVisitor v = this.av.visitAnnotation(name, this.remapper.mapDesc(desc));
        if (v == null) {
            return null;
        }
        return v == this.av ? this : new AnnotationRemapper(v, this.remapper);
    }

    @Override // org.spongepowered.asm.lib.AnnotationVisitor
    public AnnotationVisitor visitArray(String name) {
        AnnotationVisitor v = this.av.visitArray(name);
        if (v == null) {
            return null;
        }
        return v == this.av ? this : new AnnotationRemapper(v, this.remapper);
    }
}
