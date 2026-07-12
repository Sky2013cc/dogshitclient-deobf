package com.sun.xml.internal.xsom;

import com.sun.xml.internal.xsom.visitor.XSWildcardFunction;
import com.sun.xml.internal.xsom.visitor.XSWildcardVisitor;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSWildcard.class */
public interface XSWildcard extends XSComponent, XSTerm {
    public static final int LAX = 1;
    public static final int STRTICT = 2;
    public static final int SKIP = 3;

    /* loaded from: target.jar:com/sun/xml/internal/xsom/XSWildcard$Any.class */
    public interface Any extends XSWildcard {
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/XSWildcard$Other.class */
    public interface Other extends XSWildcard {
        String getOtherNamespace();
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/XSWildcard$Union.class */
    public interface Union extends XSWildcard {
        Iterator<String> iterateNamespaces();

        Collection<String> getNamespaces();
    }

    int getMode();

    boolean acceptsNamespace(String str);

    void visit(XSWildcardVisitor xSWildcardVisitor);

    <T> T apply(XSWildcardFunction<T> xSWildcardFunction);
}
