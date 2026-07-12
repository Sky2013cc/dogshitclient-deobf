package com.sun.tools.internal.xjc.reader.relaxng;

import com.sun.tools.internal.xjc.reader.internalizer.AbstractReferenceFinderImpl;
import com.sun.tools.internal.xjc.reader.internalizer.DOMForest;
import com.sun.tools.internal.xjc.reader.internalizer.InternalizationLogic;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/relaxng/RELAXNGInternalizationLogic.class */
public class RELAXNGInternalizationLogic implements InternalizationLogic {

    /* loaded from: target.jar:com/sun/tools/internal/xjc/reader/relaxng/RELAXNGInternalizationLogic$ReferenceFinder.class */
    private static final class ReferenceFinder extends AbstractReferenceFinderImpl {
        ReferenceFinder(DOMForest parent) {
            super(parent);
        }

        @Override // com.sun.tools.internal.xjc.reader.internalizer.AbstractReferenceFinderImpl
        protected String findExternalResource(String nsURI, String localName, Attributes atts) {
            if ("http://relaxng.org/ns/structure/1.0".equals(nsURI)) {
                if ("include".equals(localName) || "externalRef".equals(localName)) {
                    return atts.getValue("href");
                }
                return null;
            }
            return null;
        }
    }

    @Override // com.sun.tools.internal.xjc.reader.internalizer.InternalizationLogic
    public XMLFilterImpl createExternalReferenceFinder(DOMForest parent) {
        return new ReferenceFinder(parent);
    }

    @Override // com.sun.tools.internal.xjc.reader.internalizer.InternalizationLogic
    public boolean checkIfValidTargetNode(DOMForest parent, Element bindings, Element target) {
        return "http://relaxng.org/ns/structure/1.0".equals(target.getNamespaceURI());
    }

    @Override // com.sun.tools.internal.xjc.reader.internalizer.InternalizationLogic
    public Element refineTarget(Element target) {
        return target;
    }
}
