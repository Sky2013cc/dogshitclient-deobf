package com.sun.xml.internal.xsom.impl.parser;

import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/parser/PatcherManager.class */
public interface PatcherManager {

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/parser/PatcherManager$Patcher.class */
    public interface Patcher {
        void run() throws SAXException;
    }

    void addPatcher(Patch patch);

    void addErrorChecker(Patch patch);

    void reportError(String str, Locator locator) throws SAXException;
}
