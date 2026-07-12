package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.MemberDoc;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/AnnotationTypeOptionalMemberWriter.class */
public interface AnnotationTypeOptionalMemberWriter extends AnnotationTypeRequiredMemberWriter {
    void addDefaultValueInfo(MemberDoc memberDoc, Content content);
}
