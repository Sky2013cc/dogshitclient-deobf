package com.sun.codemodel.internal;

import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: target.jar:com/sun/codemodel/internal/JDocComment.class */
public class JDocComment extends JCommentPart implements JGenerable {
    private static final long serialVersionUID = 1;
    private final Map<String, JCommentPart> atParams = new HashMap();
    private final Map<String, Map<String, String>> atXdoclets = new HashMap();
    private final Map<JClass, JCommentPart> atThrows = new HashMap();
    private JCommentPart atReturn = null;
    private JCommentPart atDeprecated = null;
    private final JCodeModel owner;
    private static final String INDENT = " *     ";

    public JDocComment(JCodeModel owner) {
        this.owner = owner;
    }

    @Override // com.sun.codemodel.internal.JCommentPart
    public JDocComment append(Object o) {
        add(o);
        return this;
    }

    public JCommentPart addParam(String param) {
        JCommentPart p = this.atParams.get(param);
        if (p == null) {
            Map<String, JCommentPart> map = this.atParams;
            JCommentPart jCommentPart = new JCommentPart();
            p = jCommentPart;
            map.put(param, jCommentPart);
        }
        return p;
    }

    public JCommentPart addParam(JVar param) {
        return addParam(param.name());
    }

    public JCommentPart addThrows(Class<? extends Throwable> exception) {
        return addThrows(this.owner.ref(exception));
    }

    public JCommentPart addThrows(JClass exception) {
        JCommentPart p = this.atThrows.get(exception);
        if (p == null) {
            Map<JClass, JCommentPart> map = this.atThrows;
            JCommentPart jCommentPart = new JCommentPart();
            p = jCommentPart;
            map.put(exception, jCommentPart);
        }
        return p;
    }

    public JCommentPart addReturn() {
        if (this.atReturn == null) {
            this.atReturn = new JCommentPart();
        }
        return this.atReturn;
    }

    public JCommentPart addDeprecated() {
        if (this.atDeprecated == null) {
            this.atDeprecated = new JCommentPart();
        }
        return this.atDeprecated;
    }

    public Map<String, String> addXdoclet(String name) {
        Map<String, String> p = this.atXdoclets.get(name);
        if (p == null) {
            Map<String, Map<String, String>> map = this.atXdoclets;
            HashMap hashMap = new HashMap();
            p = hashMap;
            map.put(name, hashMap);
        }
        return p;
    }

    public Map<String, String> addXdoclet(String name, Map<String, String> attributes) {
        Map<String, String> p = this.atXdoclets.get(name);
        if (p == null) {
            Map<String, Map<String, String>> map = this.atXdoclets;
            HashMap hashMap = new HashMap();
            p = hashMap;
            map.put(name, hashMap);
        }
        p.putAll(attributes);
        return p;
    }

    public Map<String, String> addXdoclet(String name, String attribute, String value) {
        Map<String, String> p = this.atXdoclets.get(name);
        if (p == null) {
            Map<String, Map<String, String>> map = this.atXdoclets;
            HashMap hashMap = new HashMap();
            p = hashMap;
            map.put(name, hashMap);
        }
        p.put(attribute, value);
        return p;
    }

    @Override // com.sun.codemodel.internal.JGenerable
    public void generate(JFormatter f) {
        f.p("/**").nl();
        format(f, " * ");
        f.p(" * ").nl();
        for (Map.Entry<String, JCommentPart> e : this.atParams.entrySet()) {
            f.p(" * @param ").p(e.getKey()).nl();
            e.getValue().format(f, INDENT);
        }
        if (this.atReturn != null) {
            f.p(" * @return").nl();
            this.atReturn.format(f, INDENT);
        }
        for (Map.Entry<JClass, JCommentPart> e2 : this.atThrows.entrySet()) {
            f.p(" * @throws ").t(e2.getKey()).nl();
            e2.getValue().format(f, INDENT);
        }
        if (this.atDeprecated != null) {
            f.p(" * @deprecated").nl();
            this.atDeprecated.format(f, INDENT);
        }
        for (Map.Entry<String, Map<String, String>> e3 : this.atXdoclets.entrySet()) {
            f.p(" * @").p(e3.getKey());
            if (e3.getValue() != null) {
                for (Map.Entry<String, String> a : e3.getValue().entrySet()) {
                    f.p(" ").p(a.getKey()).p("= \"").p(a.getValue()).p(OperatorName.SHOW_TEXT_LINE_AND_SPACE);
                }
            }
            f.nl();
        }
        f.p(" */").nl();
    }
}
