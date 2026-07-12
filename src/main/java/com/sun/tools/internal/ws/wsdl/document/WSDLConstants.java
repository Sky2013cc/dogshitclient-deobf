package com.sun.tools.internal.ws.wsdl.document;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/WSDLConstants.class */
public interface WSDLConstants {
    public static final String NS_XMLNS = "http://www.w3.org/2000/xmlns/";
    public static final String NS_WSDL = "http://schemas.xmlsoap.org/wsdl/";
    public static final QName QNAME_BINDING = new QName("http://schemas.xmlsoap.org/wsdl/", "binding");
    public static final QName QNAME_DEFINITIONS = new QName("http://schemas.xmlsoap.org/wsdl/", Constants.TAG_DEFINITIONS);
    public static final QName QNAME_DOCUMENTATION = new QName("http://schemas.xmlsoap.org/wsdl/", Constants.TAG_DOCUMENTATION);
    public static final QName QNAME_FAULT = new QName("http://schemas.xmlsoap.org/wsdl/", Constants.TAG_FAULT);
    public static final QName QNAME_IMPORT = new QName("http://schemas.xmlsoap.org/wsdl/", "import");
    public static final QName QNAME_INPUT = new QName("http://schemas.xmlsoap.org/wsdl/", Constants.TAG_INPUT);
    public static final QName QNAME_MESSAGE = new QName("http://schemas.xmlsoap.org/wsdl/", "message");
    public static final QName QNAME_OPERATION = new QName("http://schemas.xmlsoap.org/wsdl/", Constants.TAG_OPERATION);
    public static final QName QNAME_OUTPUT = new QName("http://schemas.xmlsoap.org/wsdl/", Constants.TAG_OUTPUT);
    public static final QName QNAME_PART = new QName("http://schemas.xmlsoap.org/wsdl/", "part");
    public static final QName QNAME_PORT = new QName("http://schemas.xmlsoap.org/wsdl/", Constants.TAG_PORT);
    public static final QName QNAME_PORT_TYPE = new QName("http://schemas.xmlsoap.org/wsdl/", Constants.TAG_PORT_TYPE);
    public static final QName QNAME_SERVICE = new QName("http://schemas.xmlsoap.org/wsdl/", Constants.TAG_SERVICE);
    public static final QName QNAME_TYPES = new QName("http://schemas.xmlsoap.org/wsdl/", Constants.TAG_TYPES);
    public static final QName QNAME_ATTR_ARRAY_TYPE = new QName("http://schemas.xmlsoap.org/wsdl/", "arrayType");
}
