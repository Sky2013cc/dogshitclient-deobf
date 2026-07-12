package com.sun.tools.internal.ws.wsdl.document.http;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/http/HTTPConstants.class */
public interface HTTPConstants {
    public static final String NS_WSDL_HTTP = "http://schemas.xmlsoap.org/wsdl/http/";
    public static final QName QNAME_ADDRESS = new QName("http://schemas.xmlsoap.org/wsdl/http/", "address");
    public static final QName QNAME_BINDING = new QName("http://schemas.xmlsoap.org/wsdl/http/", "binding");
    public static final QName QNAME_OPERATION = new QName("http://schemas.xmlsoap.org/wsdl/http/", Constants.TAG_OPERATION);
    public static final QName QNAME_URL_ENCODED = new QName("http://schemas.xmlsoap.org/wsdl/http/", "urlEncoded");
    public static final QName QNAME_URL_REPLACEMENT = new QName("http://schemas.xmlsoap.org/wsdl/http/", "urlReplacement");
}
