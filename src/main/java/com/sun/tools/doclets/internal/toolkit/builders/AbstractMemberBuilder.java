package com.sun.tools.doclets.internal.toolkit.builders;

import com.sun.tools.doclets.internal.toolkit.Content;
import com.sun.tools.doclets.internal.toolkit.builders.AbstractBuilder;
import com.sun.tools.doclets.internal.toolkit.util.DocletAbortException;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/builders/AbstractMemberBuilder.class */
public abstract class AbstractMemberBuilder extends AbstractBuilder {
    public abstract boolean hasMembersToDocument();

    public AbstractMemberBuilder(AbstractBuilder.Context context) {
        super(context);
    }

    @Override // com.sun.tools.doclets.internal.toolkit.builders.AbstractBuilder
    public void build() throws DocletAbortException {
        throw new DocletAbortException("not supported");
    }

    @Override // com.sun.tools.doclets.internal.toolkit.builders.AbstractBuilder
    public void build(XMLNode xMLNode, Content content) {
        if (hasMembersToDocument()) {
            super.build(xMLNode, content);
        }
    }
}
