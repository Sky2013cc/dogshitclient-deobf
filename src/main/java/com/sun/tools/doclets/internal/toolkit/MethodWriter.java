package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Type;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/MethodWriter.class */
public interface MethodWriter {
    Content getMethodDetailsTreeHeader(ClassDoc classDoc, Content content);

    Content getMethodDocTreeHeader(MethodDoc methodDoc, Content content);

    Content getSignature(MethodDoc methodDoc);

    void addDeprecated(MethodDoc methodDoc, Content content);

    void addComments(Type type, MethodDoc methodDoc, Content content);

    void addTags(MethodDoc methodDoc, Content content);

    Content getMethodDetails(Content content);

    Content getMethodDoc(Content content, boolean z);

    void close() throws IOException;
}
