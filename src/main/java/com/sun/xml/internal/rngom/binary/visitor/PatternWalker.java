package com.sun.xml.internal.rngom.binary.visitor;

import com.sun.xml.internal.rngom.binary.Pattern;
import com.sun.xml.internal.rngom.nc.NameClass;
import org.relaxng.datatype.Datatype;

/* loaded from: target.jar:com/sun/xml/internal/rngom/binary/visitor/PatternWalker.class */
public class PatternWalker implements PatternVisitor {
    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitEmpty() {
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitNotAllowed() {
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitError() {
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitGroup(Pattern p1, Pattern p2) {
        visitBinary(p1, p2);
    }

    protected void visitBinary(Pattern p1, Pattern p2) {
        p1.accept(this);
        p2.accept(this);
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitInterleave(Pattern p1, Pattern p2) {
        visitBinary(p1, p2);
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitChoice(Pattern p1, Pattern p2) {
        visitBinary(p1, p2);
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitOneOrMore(Pattern p) {
        p.accept(this);
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitElement(NameClass nc, Pattern content) {
        content.accept(this);
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitAttribute(NameClass ns, Pattern value) {
        value.accept(this);
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitData(Datatype dt) {
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitDataExcept(Datatype dt, Pattern except) {
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitValue(Datatype dt, Object obj) {
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitText() {
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitList(Pattern p) {
        p.accept(this);
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitAfter(Pattern p1, Pattern p2) {
    }
}
