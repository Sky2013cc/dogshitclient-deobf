package com.sun.tools.internal.jxc.gen.config;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/tools/internal/jxc/gen/config/NGCCEventSource.class */
public interface NGCCEventSource {
    int replace(NGCCEventReceiver nGCCEventReceiver, NGCCEventReceiver nGCCEventReceiver2);

    void sendEnterElement(int i, String str, String str2, String str3, Attributes attributes) throws SAXException;

    void sendLeaveElement(int i, String str, String str2, String str3) throws SAXException;

    void sendEnterAttribute(int i, String str, String str2, String str3) throws SAXException;

    void sendLeaveAttribute(int i, String str, String str2, String str3) throws SAXException;

    void sendText(int i, String str) throws SAXException;
}
