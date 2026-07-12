package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/PropertyWriter.class */
public interface PropertyWriter {
    Content getPropertyDetailsTreeHeader(ClassDoc classDoc, Content content);

    Content getPropertyDocTreeHeader(MethodDoc methodDoc, Content content);

    Content getSignature(MethodDoc methodDoc);

    void addDeprecated(MethodDoc methodDoc, Content content);

    void addComments(MethodDoc methodDoc, Content content);

    void addTags(MethodDoc methodDoc, Content content);

    Content getPropertyDetails(Content content);

    Content getPropertyDoc(Content content, boolean z);

    void close() throws IOException;
}
