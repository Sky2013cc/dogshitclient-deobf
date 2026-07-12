package com.sun.tools.doclets.internal.toolkit.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/builders/XMLNode.class */
public class XMLNode {
    final XMLNode parent;
    final String name;
    final Map<String, String> attrs = new HashMap();
    final List<XMLNode> children = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public XMLNode(XMLNode xMLNode, String str) {
        this.parent = xMLNode;
        this.name = str;
        if (xMLNode != null) {
            xMLNode.children.add(this);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.name);
        for (Map.Entry<String, String> entry : this.attrs.entrySet()) {
            sb.append(" " + entry.getKey() + "=\"" + entry.getValue() + OperatorName.SHOW_TEXT_LINE_AND_SPACE);
        }
        if (this.children.size() == 0) {
            sb.append("/>");
        } else {
            sb.append(">");
            Iterator<XMLNode> it = this.children.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
            }
            sb.append("</" + this.name + ">");
        }
        return sb.toString();
    }
}
