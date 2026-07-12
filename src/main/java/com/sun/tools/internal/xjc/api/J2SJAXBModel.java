package com.sun.tools.internal.xjc.api;

import java.io.IOException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;

/* loaded from: target.jar:com/sun/tools/internal/xjc/api/J2SJAXBModel.class */
public interface J2SJAXBModel extends JAXBModel {
    QName getXmlTypeName(Reference reference);

    void generateSchema(SchemaOutputResolver schemaOutputResolver, ErrorListener errorListener) throws IOException;

    void generateEpisodeFile(Result result);
}
