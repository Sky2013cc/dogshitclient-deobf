package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.PackageDoc;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/ConstantsSummaryWriter.class */
public interface ConstantsSummaryWriter {
    void close() throws IOException;

    Content getHeader();

    Content getContentsHeader();

    void addLinkToPackageContent(PackageDoc packageDoc, String str, Set<String> set, Content content);

    Content getContentsList(Content content);

    Content getConstantSummaries();

    void addPackageName(PackageDoc packageDoc, String str, Content content);

    Content getClassConstantHeader();

    void addConstantMembers(ClassDoc classDoc, List<FieldDoc> list, Content content);

    void addFooter(Content content);

    void printDocument(Content content) throws IOException;
}
