package com.sun.tools.internal.ws.wsdl.document.jaxws;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/jaxws/JAXWSBindingsConstants.class */
public interface JAXWSBindingsConstants {
    public static final String NS_JAXB_BINDINGS = "http://java.sun.com/xml/ns/jaxb";
    public static final String NS_XJC_BINDINGS = "http://java.sun.com/xml/ns/jaxb/xjc";
    public static final String WSDL_LOCATION_ATTR = "wsdlLocation";
    public static final String NODE_ATTR = "node";
    public static final String VERSION_ATTR = "version";
    public static final String NAME_ATTR = "name";
    public static final String PART_ATTR = "part";
    public static final String ELEMENT_ATTR = "childElementName";
    public static final String JAXB_BINDING_VERSION = "2.0";
    public static final String NS_JAXWS_BINDINGS = "http://java.sun.com/xml/ns/jaxws";
    public static final QName JAXWS_BINDINGS = new QName(NS_JAXWS_BINDINGS, "bindings");
    public static final QName PACKAGE = new QName(NS_JAXWS_BINDINGS, "package");
    public static final QName JAVADOC = new QName(NS_JAXWS_BINDINGS, "javadoc");
    public static final QName ENABLE_WRAPPER_STYLE = new QName(NS_JAXWS_BINDINGS, "enableWrapperStyle");
    public static final QName ENABLE_ASYNC_MAPPING = new QName(NS_JAXWS_BINDINGS, "enableAsyncMapping");
    public static final QName ENABLE_ADDITIONAL_SOAPHEADER_MAPPING = new QName(NS_JAXWS_BINDINGS, "enableAdditionalSOAPHeaderMapping");
    public static final QName ENABLE_MIME_CONTENT = new QName(NS_JAXWS_BINDINGS, "enableMIMEContent");
    public static final QName PROVIDER = new QName(NS_JAXWS_BINDINGS, "provider");
    public static final QName CLASS = new QName(NS_JAXWS_BINDINGS, "class");
    public static final QName METHOD = new QName(NS_JAXWS_BINDINGS, "method");
    public static final QName PARAMETER = new QName(NS_JAXWS_BINDINGS, "parameter");
    public static final QName EXCEPTION = new QName(NS_JAXWS_BINDINGS, "exception");
    public static final QName JAXB_BINDINGS = new QName("http://java.sun.com/xml/ns/jaxb", "bindings");
    public static final QName XSD_APPINFO = new QName("http://www.w3.org/2001/XMLSchema", "appinfo");
    public static final QName XSD_ANNOTATION = new QName("http://www.w3.org/2001/XMLSchema", "annotation");
}
