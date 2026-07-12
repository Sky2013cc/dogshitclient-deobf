package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/EnumConstantWriter.class */
public interface EnumConstantWriter {
    Content getEnumConstantsDetailsTreeHeader(ClassDoc classDoc, Content content);

    Content getEnumConstantsTreeHeader(FieldDoc fieldDoc, Content content);

    Content getSignature(FieldDoc fieldDoc);

    void addDeprecated(FieldDoc fieldDoc, Content content);

    void addComments(FieldDoc fieldDoc, Content content);

    void addTags(FieldDoc fieldDoc, Content content);

    Content getEnumConstantsDetails(Content content);

    Content getEnumConstants(Content content, boolean z);

    void close() throws IOException;
}
