package com.sun.xml.internal.xsom;

import com.sun.xml.internal.xsom.parser.SchemaDocument;
import com.sun.xml.internal.xsom.visitor.XSFunction;
import com.sun.xml.internal.xsom.visitor.XSVisitor;
import java.util.Collection;
import java.util.List;
import javax.xml.namespace.NamespaceContext;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSComponent.class */
public interface XSComponent {
    XSAnnotation getAnnotation();

    XSAnnotation getAnnotation(boolean z);

    List<? extends ForeignAttributes> getForeignAttributes();

    String getForeignAttribute(String str, String str2);

    Locator getLocator();

    XSSchema getOwnerSchema();

    XSSchemaSet getRoot();

    SchemaDocument getSourceDocument();

    Collection<XSComponent> select(String str, NamespaceContext namespaceContext);

    XSComponent selectSingle(String str, NamespaceContext namespaceContext);

    void visit(XSVisitor xSVisitor);

    <T> T apply(XSFunction<T> xSFunction);
}
