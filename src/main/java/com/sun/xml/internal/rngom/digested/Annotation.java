package com.sun.xml.internal.rngom.digested;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.util.LocatorImpl;
import com.sun.xml.internal.rngom.digested.DAnnotation;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/Annotation.class */
class Annotation implements Annotations<ElementWrapper, LocatorImpl, CommentListImpl> {
    private final DAnnotation a = new DAnnotation();

    @Override // com.sun.xml.internal.rngom.ast.builder.Annotations
    public void addAttribute(String ns, String localName, String prefix, String value, LocatorImpl loc) throws BuildException {
        this.a.attributes.put(new QName(ns, localName, prefix), new DAnnotation.Attribute(ns, localName, prefix, value, loc));
    }

    @Override // com.sun.xml.internal.rngom.ast.builder.Annotations
    public void addElement(ElementWrapper ea) throws BuildException {
        this.a.contents.add(ea.element);
    }

    @Override // com.sun.xml.internal.rngom.ast.builder.Annotations
    public void addComment(CommentListImpl comments) throws BuildException {
    }

    @Override // com.sun.xml.internal.rngom.ast.builder.Annotations
    public void addLeadingComment(CommentListImpl comments) throws BuildException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DAnnotation getResult() {
        return this.a;
    }
}
