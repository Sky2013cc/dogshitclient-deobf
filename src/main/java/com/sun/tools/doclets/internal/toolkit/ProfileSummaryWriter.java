package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.PackageDoc;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/ProfileSummaryWriter.class */
public interface ProfileSummaryWriter {
    Content getProfileHeader(String str);

    Content getContentHeader();

    Content getSummaryHeader();

    Content getSummaryTree(Content content);

    Content getPackageSummaryHeader(PackageDoc packageDoc);

    Content getPackageSummaryTree(Content content);

    void addClassesSummary(ClassDoc[] classDocArr, String str, String str2, String[] strArr, Content content);

    void addProfileFooter(Content content);

    void printDocument(Content content) throws IOException;

    void close() throws IOException;
}
