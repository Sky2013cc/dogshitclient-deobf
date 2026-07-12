package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.ClassDoc;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/ProfilePackageSummaryWriter.class */
public interface ProfilePackageSummaryWriter {
    Content getPackageHeader(String str);

    Content getContentHeader();

    Content getSummaryHeader();

    void addClassesSummary(ClassDoc[] classDocArr, String str, String str2, String[] strArr, Content content);

    void addPackageDescription(Content content);

    void addPackageTags(Content content);

    void addPackageFooter(Content content);

    void printDocument(Content content) throws IOException;

    void close() throws IOException;
}
