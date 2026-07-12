package com.sun.xml.internal.rngom.ast.builder;

import com.sun.xml.internal.rngom.ast.builder.CommentList;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;

/* loaded from: target.jar:com/sun/xml/internal/rngom/ast/builder/Annotations.class */
public interface Annotations<E extends ParsedElementAnnotation, L extends Location, CL extends CommentList<L>> {
    void addAttribute(String str, String str2, String str3, String str4, L l) throws BuildException;

    void addElement(E e) throws BuildException;

    void addComment(CL cl) throws BuildException;

    void addLeadingComment(CL cl) throws BuildException;
}
