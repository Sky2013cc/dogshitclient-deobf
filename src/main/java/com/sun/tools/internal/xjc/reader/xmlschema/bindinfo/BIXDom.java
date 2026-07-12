package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dom", namespace = "http://java.sun.com/xml/ns/jaxb/xjc")
/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/bindinfo/BIXDom.class */
public class BIXDom extends BIDom {

    @XmlAttribute
    String type = "w3c";
}
