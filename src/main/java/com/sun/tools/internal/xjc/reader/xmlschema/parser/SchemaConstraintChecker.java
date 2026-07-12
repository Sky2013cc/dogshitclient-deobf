package com.sun.tools.internal.xjc.reader.xmlschema.parser;

import com.sun.tools.internal.xjc.ConsoleErrorReporter;
import com.sun.tools.internal.xjc.ErrorReceiver;
import com.sun.tools.internal.xjc.util.ErrorReceiverFilter;
import com.sun.xml.internal.bind.v2.util.XmlFactory;
import java.io.File;
import java.io.IOException;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/parser/SchemaConstraintChecker.class */
public class SchemaConstraintChecker {
    public static boolean check(InputSource[] schemas, ErrorReceiver errorHandler, final EntityResolver entityResolver, boolean disableXmlSecurity) {
        ErrorReceiverFilter errorFilter = new ErrorReceiverFilter(errorHandler);
        boolean hadErrors = false;
        SchemaFactory sf = XmlFactory.createSchemaFactory("http://www.w3.org/2001/XMLSchema", disableXmlSecurity);
        XmlFactory.allowExternalAccess(sf, "all", disableXmlSecurity);
        sf.setErrorHandler(errorFilter);
        if (entityResolver != null) {
            sf.setResourceResolver(new LSResourceResolver() { // from class: com.sun.tools.internal.xjc.reader.xmlschema.parser.SchemaConstraintChecker.1
                @Override // org.w3c.dom.ls.LSResourceResolver
                public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
                    try {
                        InputSource is = entityResolver.resolveEntity(namespaceURI, systemId);
                        if (is == null) {
                            return null;
                        }
                        return new LSInputSAXWrapper(is);
                    } catch (IOException e) {
                        return null;
                    } catch (SAXException e2) {
                        return null;
                    }
                }
            });
        }
        try {
            XmlFactory.allowExternalDTDAccess(sf, "all", disableXmlSecurity);
            sf.newSchema(getSchemaSource(schemas, entityResolver));
        } catch (OutOfMemoryError e) {
            errorHandler.warning(null, Messages.format("SchemaConstraintChecker.UnableToCheckCorrectness", new Object[0]));
        } catch (SAXException e2) {
            hadErrors = true;
        }
        return (hadErrors || errorFilter.hadError()) ? false : true;
    }

    private static Source[] getSchemaSource(InputSource[] schemas, EntityResolver entityResolver) throws SAXException {
        SAXSource[] sources = new SAXSource[schemas.length];
        for (int i = 0; i < schemas.length; i++) {
            sources[i] = new SAXSource(schemas[i]);
        }
        return sources;
    }

    public static void main(String[] args) throws IOException {
        InputSource[] sources = new InputSource[args.length];
        for (int i = 0; i < args.length; i++) {
            sources[i] = new InputSource(new File(args[i]).toURL().toExternalForm());
        }
        check(sources, new ConsoleErrorReporter(), null, true);
    }
}
