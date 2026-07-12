package com.sun.xml.internal.rngom.ast.builder;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.CommentList;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;
import com.sun.xml.internal.rngom.ast.om.ParsedPattern;
import com.sun.xml.internal.rngom.parse.Context;

/* loaded from: target.jar:com/sun/xml/internal/rngom/ast/builder/DataPatternBuilder.class */
public interface DataPatternBuilder<P extends ParsedPattern, E extends ParsedElementAnnotation, L extends Location, A extends Annotations<E, L, CL>, CL extends CommentList<L>> {
    void addParam(String str, String str2, Context context, String str3, L l, A a) throws BuildException;

    void annotation(E e);

    P makePattern(L l, A a) throws BuildException;

    P makePattern(P p, L l, A a) throws BuildException;
}
