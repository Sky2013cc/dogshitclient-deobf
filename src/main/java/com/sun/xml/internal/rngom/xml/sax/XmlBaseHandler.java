package com.sun.xml.internal.rngom.xml.sax;

import com.sun.xml.internal.rngom.util.Uri;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/xml/internal/rngom/xml/sax/XmlBaseHandler.class */
public class XmlBaseHandler {
    private Locator loc;
    private int depth = 0;
    private Entry stack = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/xml/internal/rngom/xml/sax/XmlBaseHandler$Entry.class */
    public static class Entry {
        private Entry parent;
        private String attValue;
        private String systemId;
        private int depth;

        private Entry() {
        }
    }

    public void setLocator(Locator loc) {
        this.loc = loc;
    }

    public void startElement() {
        this.depth++;
    }

    public void endElement() {
        if (this.stack != null && this.stack.depth == this.depth) {
            this.stack = this.stack.parent;
        }
        this.depth--;
    }

    public void xmlBaseAttribute(String value) {
        Entry entry = new Entry();
        entry.parent = this.stack;
        this.stack = entry;
        entry.attValue = Uri.escapeDisallowedChars(value);
        entry.systemId = getSystemId();
        entry.depth = this.depth;
    }

    private String getSystemId() {
        if (this.loc == null) {
            return null;
        }
        return this.loc.getSystemId();
    }

    public String getBaseUri() {
        return getBaseUri1(getSystemId(), this.stack);
    }

    private static String getBaseUri1(String baseUri, Entry stack) {
        if (stack != null && (baseUri == null || baseUri.equals(stack.systemId))) {
            String baseUri2 = stack.attValue;
            if (!Uri.isAbsolute(baseUri2)) {
                return Uri.resolve(getBaseUri1(stack.systemId, stack.parent), baseUri2);
            }
            return baseUri2;
        }
        return baseUri;
    }
}
