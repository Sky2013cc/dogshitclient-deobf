package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.builder.CommentList;
import com.sun.xml.internal.rngom.ast.om.Location;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/host/CommentListHost.class */
class CommentListHost extends Base implements CommentList {
    final CommentList lhs;
    final CommentList rhs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CommentListHost(CommentList lhs, CommentList rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override // com.sun.xml.internal.rngom.ast.builder.CommentList
    public void addComment(String value, Location _loc) throws BuildException {
        LocationHost loc = cast(_loc);
        if (this.lhs != null) {
            this.lhs.addComment(value, loc.lhs);
        }
        if (this.rhs != null) {
            this.rhs.addComment(value, loc.rhs);
        }
    }
}
