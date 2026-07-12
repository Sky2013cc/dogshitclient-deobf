package com.sun.xml.internal.rngom.ast.builder;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.CommentList;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;
import com.sun.xml.internal.rngom.ast.om.ParsedPattern;

/* loaded from: target.jar:com/sun/xml/internal/rngom/ast/builder/ElementAnnotationBuilder.class */
public interface ElementAnnotationBuilder<P extends ParsedPattern, E extends ParsedElementAnnotation, L extends Location, A extends Annotations<E, L, CL>, CL extends CommentList<L>> extends Annotations<E, L, CL> {
    void addText(String str, L l, CL cl) throws BuildException;

    E makeElementAnnotation() throws BuildException;
}
