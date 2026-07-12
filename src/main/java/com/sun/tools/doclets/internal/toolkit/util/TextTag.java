package com.sun.tools.doclets.internal.toolkit.util;

import com.sun.javadoc.Doc;
import com.sun.javadoc.SourcePosition;
import com.sun.javadoc.Tag;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/util/TextTag.class */
public class TextTag implements Tag {
    protected final String text;
    protected final String name = "Text";
    protected final Doc holder;

    public TextTag(Doc doc, String str) {
        this.holder = doc;
        this.text = str;
    }

    @Override // com.sun.javadoc.Tag
    public String name() {
        return "Text";
    }

    @Override // com.sun.javadoc.Tag
    public Doc holder() {
        return this.holder;
    }

    @Override // com.sun.javadoc.Tag
    public String kind() {
        return "Text";
    }

    @Override // com.sun.javadoc.Tag
    public String text() {
        return this.text;
    }

    @Override // com.sun.javadoc.Tag
    public String toString() {
        return "Text:" + this.text;
    }

    @Override // com.sun.javadoc.Tag
    public Tag[] inlineTags() {
        return new Tag[]{this};
    }

    @Override // com.sun.javadoc.Tag
    public Tag[] firstSentenceTags() {
        return new Tag[]{this};
    }

    @Override // com.sun.javadoc.Tag
    public SourcePosition position() {
        return this.holder.position();
    }
}
