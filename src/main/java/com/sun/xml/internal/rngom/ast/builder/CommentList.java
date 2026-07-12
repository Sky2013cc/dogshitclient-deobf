package com.sun.xml.internal.rngom.ast.builder;

import com.sun.xml.internal.rngom.ast.om.Location;

/* loaded from: target.jar:com/sun/xml/internal/rngom/ast/builder/CommentList.class */
public interface CommentList<L extends Location> {
    void addComment(String str, L l) throws BuildException;
}
