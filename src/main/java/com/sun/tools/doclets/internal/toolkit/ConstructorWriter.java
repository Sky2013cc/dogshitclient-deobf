package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/ConstructorWriter.class */
public interface ConstructorWriter {
    Content getConstructorDetailsTreeHeader(ClassDoc classDoc, Content content);

    Content getConstructorDocTreeHeader(ConstructorDoc constructorDoc, Content content);

    Content getSignature(ConstructorDoc constructorDoc);

    void addDeprecated(ConstructorDoc constructorDoc, Content content);

    void addComments(ConstructorDoc constructorDoc, Content content);

    void addTags(ConstructorDoc constructorDoc, Content content);

    Content getConstructorDetails(Content content);

    Content getConstructorDoc(Content content, boolean z);

    void setFoundNonPubConstructor(boolean z);

    void close() throws IOException;
}
