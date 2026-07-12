package com.sun.xml.internal.rngom.ast.builder;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.CommentList;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;
import com.sun.xml.internal.rngom.ast.om.ParsedNameClass;
import java.util.List;

/* loaded from: target.jar:com/sun/xml/internal/rngom/ast/builder/NameClassBuilder.class */
public interface NameClassBuilder<N extends ParsedNameClass, E extends ParsedElementAnnotation, L extends Location, A extends Annotations<E, L, CL>, CL extends CommentList<L>> {
    N annotate(N n, A a) throws BuildException;

    N annotateAfter(N n, E e) throws BuildException;

    N commentAfter(N n, CL cl) throws BuildException;

    N makeChoice(List<N> list, L l, A a);

    N makeName(String str, String str2, String str3, L l, A a);

    N makeNsName(String str, L l, A a);

    N makeNsName(String str, N n, L l, A a);

    N makeAnyName(L l, A a);

    N makeAnyName(N n, L l, A a);

    N makeErrorNameClass();
}
