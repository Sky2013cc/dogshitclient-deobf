package com.sun.tools.doclets.internal.toolkit.util;

import com.sun.javadoc.MethodDoc;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/util/CommentedMethodFinder.class */
public class CommentedMethodFinder extends MethodFinder {
    @Override // com.sun.tools.doclets.internal.toolkit.util.MethodFinder
    public boolean isCorrectMethod(MethodDoc methodDoc) {
        return methodDoc.inlineTags().length > 0;
    }
}
