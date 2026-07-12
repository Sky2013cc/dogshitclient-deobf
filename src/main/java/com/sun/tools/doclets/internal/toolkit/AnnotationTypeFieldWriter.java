package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MemberDoc;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/AnnotationTypeFieldWriter.class */
public interface AnnotationTypeFieldWriter {
    Content getMemberTreeHeader();

    void addAnnotationFieldDetailsMarker(Content content);

    void addAnnotationDetailsTreeHeader(ClassDoc classDoc, Content content);

    Content getAnnotationDocTreeHeader(MemberDoc memberDoc, Content content);

    Content getAnnotationDetails(Content content);

    Content getAnnotationDoc(Content content, boolean z);

    Content getSignature(MemberDoc memberDoc);

    void addDeprecated(MemberDoc memberDoc, Content content);

    void addComments(MemberDoc memberDoc, Content content);

    void addTags(MemberDoc memberDoc, Content content);

    void close() throws IOException;
}
