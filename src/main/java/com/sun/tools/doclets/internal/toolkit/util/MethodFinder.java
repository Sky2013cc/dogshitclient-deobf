package com.sun.tools.doclets.internal.toolkit.util;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/util/MethodFinder.class */
public abstract class MethodFinder {
    abstract boolean isCorrectMethod(MethodDoc methodDoc);

    public MethodDoc search(ClassDoc classDoc, MethodDoc methodDoc) {
        MethodDoc searchInterfaces = searchInterfaces(classDoc, methodDoc);
        if (searchInterfaces != null) {
            return searchInterfaces;
        }
        ClassDoc superclass = classDoc.superclass();
        if (superclass != null) {
            MethodDoc findMethod = Util.findMethod(superclass, methodDoc);
            if (findMethod != null && isCorrectMethod(findMethod)) {
                return findMethod;
            }
            return search(superclass, methodDoc);
        }
        return null;
    }

    public MethodDoc searchInterfaces(ClassDoc classDoc, MethodDoc methodDoc) {
        MethodDoc[] build = new ImplementedMethods(methodDoc, null).build();
        for (int i = 0; i < build.length; i++) {
            if (isCorrectMethod(build[i])) {
                return build[i];
            }
        }
        return null;
    }
}
