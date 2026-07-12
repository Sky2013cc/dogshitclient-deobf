package com.sun.tools.doclets.internal.toolkit;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.SerialFieldTag;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/SerializedFormWriter.class */
public interface SerializedFormWriter {

    /* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/SerializedFormWriter$SerialFieldWriter.class */
    public interface SerialFieldWriter {
        Content getSerializableFieldsHeader();

        Content getFieldsContentHeader(boolean z);

        Content getSerializableFields(String str, Content content);

        void addMemberDeprecatedInfo(FieldDoc fieldDoc, Content content);

        void addMemberDescription(FieldDoc fieldDoc, Content content);

        void addMemberDescription(SerialFieldTag serialFieldTag, Content content);

        void addMemberTags(FieldDoc fieldDoc, Content content);

        void addMemberHeader(ClassDoc classDoc, String str, String str2, String str3, Content content);

        boolean shouldPrintOverview(FieldDoc fieldDoc);
    }

    /* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/SerializedFormWriter$SerialMethodWriter.class */
    public interface SerialMethodWriter {
        Content getSerializableMethodsHeader();

        Content getMethodsContentHeader(boolean z);

        Content getSerializableMethods(String str, Content content);

        Content getNoCustomizationMsg(String str);

        void addMemberHeader(MethodDoc methodDoc, Content content);

        void addDeprecatedMemberInfo(MethodDoc methodDoc, Content content);

        void addMemberDescription(MethodDoc methodDoc, Content content);

        void addMemberTags(MethodDoc methodDoc, Content content);
    }

    Content getHeader(String str);

    Content getSerializedSummariesHeader();

    Content getPackageSerializedHeader();

    Content getPackageHeader(String str);

    Content getClassSerializedHeader();

    Content getClassHeader(ClassDoc classDoc);

    Content getSerialUIDInfoHeader();

    void addSerialUIDInfo(String str, String str2, Content content);

    Content getClassContentHeader();

    SerialFieldWriter getSerialFieldWriter(ClassDoc classDoc);

    SerialMethodWriter getSerialMethodWriter(ClassDoc classDoc);

    void close() throws IOException;

    Content getSerializedContent(Content content);

    void addFooter(Content content);

    void printDocument(Content content) throws IOException;
}
