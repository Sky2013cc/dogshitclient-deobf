package com.sun.tools.internal.xjc.reader.internalizer;

import org.w3c.dom.Element;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/internalizer/InternalizationLogic.class */
public interface InternalizationLogic {
    XMLFilterImpl createExternalReferenceFinder(DOMForest dOMForest);

    boolean checkIfValidTargetNode(DOMForest dOMForest, Element element, Element element2);

    Element refineTarget(Element element);
}
