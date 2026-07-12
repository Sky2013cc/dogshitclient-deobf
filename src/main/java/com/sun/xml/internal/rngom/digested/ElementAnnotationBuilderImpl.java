package com.sun.xml.internal.rngom.digested;

import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.builder.CommentList;
import com.sun.xml.internal.rngom.ast.builder.ElementAnnotationBuilder;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;
import org.w3c.dom.Element;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/ElementAnnotationBuilderImpl.class */
class ElementAnnotationBuilderImpl implements ElementAnnotationBuilder {
    private final Element e;

    public ElementAnnotationBuilderImpl(Element e) {
        this.e = e;
    }

    @Override // com.sun.xml.internal.rngom.ast.builder.ElementAnnotationBuilder
    public void addText(String value, Location loc, CommentList comments) throws BuildException {
        this.e.appendChild(this.e.getOwnerDocument().createTextNode(value));
    }

    @Override // com.sun.xml.internal.rngom.ast.builder.ElementAnnotationBuilder
    public ParsedElementAnnotation makeElementAnnotation() throws BuildException {
        return new ElementWrapper(this.e);
    }

    @Override // com.sun.xml.internal.rngom.ast.builder.Annotations
    public void addAttribute(String ns, String localName, String prefix, String value, Location loc) throws BuildException {
        this.e.setAttributeNS(ns, localName, value);
    }

    @Override // com.sun.xml.internal.rngom.ast.builder.Annotations
    public void addElement(ParsedElementAnnotation ea) throws BuildException {
        this.e.appendChild(((ElementWrapper) ea).element);
    }

    @Override // com.sun.xml.internal.rngom.ast.builder.Annotations
    public void addComment(CommentList comments) throws BuildException {
    }

    @Override // com.sun.xml.internal.rngom.ast.builder.Annotations
    public void addLeadingComment(CommentList comments) throws BuildException {
    }
}
