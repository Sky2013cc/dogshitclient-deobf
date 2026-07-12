package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.ClassDoc;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/ClassWriter.class */
public interface ClassWriter {
    Content getHeader(String str);

    Content getClassContentHeader();

    void addClassTree(Content content);

    Content getClassInfoTreeHeader();

    void addTypeParamInfo(Content content);

    void addSuperInterfacesInfo(Content content);

    void addImplementedInterfacesInfo(Content content);

    void addSubClassInfo(Content content);

    void addSubInterfacesInfo(Content content);

    void addInterfaceUsageInfo(Content content);

    void addFunctionalInterfaceInfo(Content content);

    void addNestedClassInfo(Content content);

    Content getClassInfo(Content content);

    void addClassDeprecationInfo(Content content);

    void addClassSignature(String str, Content content);

    void addClassDescription(Content content);

    void addClassTagInfo(Content content);

    Content getMemberTreeHeader();

    void addFooter(Content content);

    void printDocument(Content content) throws IOException;

    void close() throws IOException;

    ClassDoc getClassDoc();

    Content getMemberSummaryTree(Content content);

    Content getMemberDetailsTree(Content content);
}
