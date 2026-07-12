package com.sun.tools.internal.xjc.reader.dtd.bindinfo;

import java.util.StringTokenizer;
import org.w3c.dom.Element;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/dtd/bindinfo/BIInterface.class */
public final class BIInterface {
    private final Element dom;
    private final String name;
    private final String[] members;
    private final String[] fields;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BIInterface(Element e) {
        this.dom = e;
        this.name = DOMUtil.getAttribute(e, "name");
        this.members = parseTokens(DOMUtil.getAttribute(e, "members"));
        if (DOMUtil.getAttribute(e, "properties") != null) {
            this.fields = parseTokens(DOMUtil.getAttribute(e, "properties"));
            throw new AssertionError("//interface/@properties is not supported");
        }
        this.fields = new String[0];
    }

    public String name() {
        return this.name;
    }

    public String[] members() {
        return this.members;
    }

    public String[] fields() {
        return this.fields;
    }

    public Locator getSourceLocation() {
        return DOMLocator.getLocationInfo(this.dom);
    }

    private static String[] parseTokens(String value) {
        StringTokenizer tokens = new StringTokenizer(value);
        String[] r = new String[tokens.countTokens()];
        int i = 0;
        while (tokens.hasMoreTokens()) {
            int i2 = i;
            i++;
            r[i2] = tokens.nextToken();
        }
        return r;
    }
}
