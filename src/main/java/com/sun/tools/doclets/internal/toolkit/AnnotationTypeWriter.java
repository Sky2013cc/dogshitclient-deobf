package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.AnnotationTypeDoc;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/AnnotationTypeWriter.class */
public interface AnnotationTypeWriter {
    Content getHeader(String str);

    Content getAnnotationContentHeader();

    Content getAnnotationInfoTreeHeader();

    Content getAnnotationInfo(Content content);

    void addAnnotationTypeSignature(String str, Content content);

    void addAnnotationTypeDescription(Content content);

    void addAnnotationTypeTagInfo(Content content);

    void addAnnotationTypeDeprecationInfo(Content content);

    Content getMemberTreeHeader();

    Content getMemberTree(Content content);

    Content getMemberSummaryTree(Content content);

    Content getMemberDetailsTree(Content content);

    void addFooter(Content content);

    void printDocument(Content content) throws IOException;

    void close() throws IOException;

    AnnotationTypeDoc getAnnotationTypeDoc();
}
