package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ProgramElementDoc;
import com.sun.javadoc.Tag;
import java.io.IOException;
import java.util.List;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/MemberSummaryWriter.class */
public interface MemberSummaryWriter {
    Content getMemberSummaryHeader(ClassDoc classDoc, Content content);

    Content getSummaryTableTree(ClassDoc classDoc, List<Content> list);

    void addMemberSummary(ClassDoc classDoc, ProgramElementDoc programElementDoc, Tag[] tagArr, List<Content> list, int i);

    Content getInheritedSummaryHeader(ClassDoc classDoc);

    void addInheritedMemberSummary(ClassDoc classDoc, ProgramElementDoc programElementDoc, boolean z, boolean z2, Content content);

    Content getInheritedSummaryLinksTree();

    Content getMemberTree(Content content);

    void close() throws IOException;
}
