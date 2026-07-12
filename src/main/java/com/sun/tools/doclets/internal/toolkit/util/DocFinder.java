package com.sun.tools.doclets.internal.toolkit.util;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Doc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ProgramElementDoc;
import com.sun.javadoc.Tag;
import com.sun.tools.doclets.internal.toolkit.taglets.InheritableTaglet;
import java.util.ArrayList;
import java.util.List;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/util/DocFinder.class */
public class DocFinder {

    /* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/util/DocFinder$Output.class */
    public static class Output {
        public Tag holderTag;
        public Doc holder;
        public Tag[] inlineTags = new Tag[0];
        public boolean isValidInheritDocTag = true;
        public List<Tag> tagList = new ArrayList();
    }

    /* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/util/DocFinder$Input.class */
    public static class Input {
        public ProgramElementDoc element;
        public InheritableTaglet taglet;
        public String tagId;
        public Tag tag;
        public boolean isFirstSentence;
        public boolean isInheritDocTag;
        public boolean isTypeVariableParamTag;

        public Input(ProgramElementDoc programElementDoc, InheritableTaglet inheritableTaglet, Tag tag, boolean z, boolean z2) {
            this(programElementDoc);
            this.taglet = inheritableTaglet;
            this.tag = tag;
            this.isFirstSentence = z;
            this.isInheritDocTag = z2;
        }

        public Input(ProgramElementDoc programElementDoc, InheritableTaglet inheritableTaglet, String str) {
            this(programElementDoc);
            this.taglet = inheritableTaglet;
            this.tagId = str;
        }

        public Input(ProgramElementDoc programElementDoc, InheritableTaglet inheritableTaglet, String str, boolean z) {
            this(programElementDoc);
            this.taglet = inheritableTaglet;
            this.tagId = str;
            this.isTypeVariableParamTag = z;
        }

        public Input(ProgramElementDoc programElementDoc, InheritableTaglet inheritableTaglet) {
            this(programElementDoc);
            this.taglet = inheritableTaglet;
        }

        public Input(ProgramElementDoc programElementDoc) {
            this.taglet = null;
            this.tagId = null;
            this.tag = null;
            this.isFirstSentence = false;
            this.isInheritDocTag = false;
            this.isTypeVariableParamTag = false;
            if (programElementDoc == null) {
                throw new NullPointerException();
            }
            this.element = programElementDoc;
        }

        public Input(ProgramElementDoc programElementDoc, boolean z) {
            this(programElementDoc);
            this.isFirstSentence = z;
        }

        public Input copy() {
            Input input = new Input(this.element);
            input.taglet = this.taglet;
            input.tagId = this.tagId;
            input.tag = this.tag;
            input.isFirstSentence = this.isFirstSentence;
            input.isInheritDocTag = this.isInheritDocTag;
            input.isTypeVariableParamTag = this.isTypeVariableParamTag;
            if (input.element == null) {
                throw new NullPointerException();
            }
            return input;
        }
    }

    public static Output search(Input input) {
        ClassDoc superclass;
        Tag[] inlineTags;
        Output output = new Output();
        if (!input.isInheritDocTag) {
            if (input.taglet == null) {
                if (input.isFirstSentence) {
                    inlineTags = input.element.firstSentenceTags();
                } else {
                    inlineTags = input.element.inlineTags();
                }
                output.inlineTags = inlineTags;
                output.holder = input.element;
            } else {
                input.taglet.inherit(input, output);
            }
        }
        if (output.inlineTags != null && output.inlineTags.length > 0) {
            return output;
        }
        output.isValidInheritDocTag = false;
        Input copy = input.copy();
        copy.isInheritDocTag = false;
        if (input.element instanceof MethodDoc) {
            MethodDoc overriddenMethod = ((MethodDoc) input.element).overriddenMethod();
            if (overriddenMethod != null) {
                copy.element = overriddenMethod;
                output = search(copy);
                output.isValidInheritDocTag = true;
                if (output.inlineTags.length > 0) {
                    return output;
                }
            }
            for (MethodDoc methodDoc : new ImplementedMethods((MethodDoc) input.element, null).build(false)) {
                copy.element = methodDoc;
                output = search(copy);
                output.isValidInheritDocTag = true;
                if (output.inlineTags.length > 0) {
                    return output;
                }
            }
        } else if ((input.element instanceof ClassDoc) && (superclass = ((ClassDoc) input.element).superclass()) != null) {
            copy.element = superclass;
            output = search(copy);
            output.isValidInheritDocTag = true;
            if (output.inlineTags.length > 0) {
                return output;
            }
        }
        return output;
    }
}
