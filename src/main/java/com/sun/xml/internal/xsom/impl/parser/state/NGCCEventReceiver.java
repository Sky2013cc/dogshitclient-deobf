package com.sun.xml.internal.xsom.impl.parser.state;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/parser/state/NGCCEventReceiver.class */
public interface NGCCEventReceiver {
    void enterElement(String str, String str2, String str3, Attributes attributes) throws SAXException;

    void leaveElement(String str, String str2, String str3) throws SAXException;

    void text(String str) throws SAXException;

    void enterAttribute(String str, String str2, String str3) throws SAXException;

    void leaveAttribute(String str, String str2, String str3) throws SAXException;
}
