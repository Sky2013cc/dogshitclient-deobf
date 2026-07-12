package com.sun.tools.internal.ws.wscompile;

import com.sun.tools.internal.ws.api.WsgenExtension;
import com.sun.tools.internal.ws.api.WsgenProtocol;
import com.sun.tools.internal.ws.resources.WscompileMessages;
import com.sun.xml.internal.ws.api.BindingID;
import com.sun.xml.internal.ws.util.ServiceFinder;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.jws.WebService;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/WsgenOptions.class */
public class WsgenOptions extends Options {
    public QName serviceName;
    public QName portName;
    public File nonclassDestDir;
    public boolean genWsdl;
    public boolean inlineSchemas;
    public File wsgenReport;
    public boolean doNotOverWrite;
    private static final String SERVICENAME_OPTION = "-servicename";
    private static final String PORTNAME_OPTION = "-portname";
    private static final String HTTP = "http";
    private static final String SOAP11 = "soap1.1";
    public static final String X_SOAP12 = "Xsoap1.2";
    public Class endpoint;
    private boolean isImplClass;
    public String protocol = SOAP11;
    public Set<String> protocols = new LinkedHashSet();
    public Map<String, String> nonstdProtocols = new LinkedHashMap();
    public boolean protocolSet = false;
    public List<String> externalMetadataFiles = new ArrayList();
    List<String> endpoints = new ArrayList();

    public WsgenOptions() {
        this.protocols.add(SOAP11);
        this.protocols.add(X_SOAP12);
        this.nonstdProtocols.put(X_SOAP12, "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/");
        ServiceFinder<WsgenExtension> extn = ServiceFinder.find(WsgenExtension.class);
        Iterator it = extn.iterator();
        while (it.hasNext()) {
            WsgenExtension ext = (WsgenExtension) it.next();
            Class clazz = ext.getClass();
            WsgenProtocol pro = (WsgenProtocol) clazz.getAnnotation(WsgenProtocol.class);
            this.protocols.add(pro.token());
            this.nonstdProtocols.put(pro.token(), pro.lexical());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.tools.internal.ws.wscompile.Options
    public int parseArguments(String[] args, int i) throws BadCommandLineException {
        int j = super.parseArguments(args, i);
        if (args[i].equals(SERVICENAME_OPTION)) {
            int i2 = i + 1;
            this.serviceName = QName.valueOf(requireArgument(SERVICENAME_OPTION, args, i2));
            if (this.serviceName.getNamespaceURI() == null || this.serviceName.getNamespaceURI().length() == 0) {
                throw new BadCommandLineException(WscompileMessages.WSGEN_SERVICENAME_MISSING_NAMESPACE(args[i2]));
            }
            if (this.serviceName.getLocalPart() == null || this.serviceName.getLocalPart().length() == 0) {
                throw new BadCommandLineException(WscompileMessages.WSGEN_SERVICENAME_MISSING_LOCALNAME(args[i2]));
            }
            return 2;
        }
        if (args[i].equals(PORTNAME_OPTION)) {
            int i3 = i + 1;
            this.portName = QName.valueOf(requireArgument(PORTNAME_OPTION, args, i3));
            if (this.portName.getNamespaceURI() == null || this.portName.getNamespaceURI().length() == 0) {
                throw new BadCommandLineException(WscompileMessages.WSGEN_PORTNAME_MISSING_NAMESPACE(args[i3]));
            }
            if (this.portName.getLocalPart() == null || this.portName.getLocalPart().length() == 0) {
                throw new BadCommandLineException(WscompileMessages.WSGEN_PORTNAME_MISSING_LOCALNAME(args[i3]));
            }
            return 2;
        }
        if (args[i].equals("-r")) {
            this.nonclassDestDir = new File(requireArgument("-r", args, i + 1));
            if (!this.nonclassDestDir.exists()) {
                throw new BadCommandLineException(WscompileMessages.WSCOMPILE_NO_SUCH_DIRECTORY(this.nonclassDestDir.getPath()));
            }
            return 2;
        }
        if (args[i].startsWith("-wsdl")) {
            this.genWsdl = true;
            String value = args[i].substring(5);
            if (value.indexOf(58) == 0) {
                String value2 = value.substring(1);
                int index = value2.indexOf(47);
                if (index == -1) {
                    this.protocol = value2;
                } else {
                    this.protocol = value2.substring(0, index);
                }
                this.protocolSet = true;
                return 1;
            }
            return 1;
        }
        if (args[i].equals("-XwsgenReport")) {
            this.wsgenReport = new File(requireArgument("-XwsgenReport", args, i + 1));
            return 2;
        }
        if (args[i].equals("-Xdonotoverwrite")) {
            this.doNotOverWrite = true;
            return 1;
        }
        if (args[i].equals("-inlineSchemas")) {
            this.inlineSchemas = true;
            return 1;
        }
        if ("-x".equals(args[i])) {
            this.externalMetadataFiles.add(requireArgument("-x", args, i + 1));
            return 1;
        }
        return j;
    }

    @Override // com.sun.tools.internal.ws.wscompile.Options
    protected void addFile(String arg) {
        this.endpoints.add(arg);
    }

    public void validate() throws BadCommandLineException {
        if (this.nonclassDestDir == null) {
            this.nonclassDestDir = this.destDir;
        }
        if (!this.protocols.contains(this.protocol)) {
            throw new BadCommandLineException(WscompileMessages.WSGEN_INVALID_PROTOCOL(this.protocol, this.protocols));
        }
        if (this.endpoints.isEmpty()) {
            throw new BadCommandLineException(WscompileMessages.WSGEN_MISSING_FILE());
        }
        if (this.protocol == null || (this.protocol.equalsIgnoreCase(X_SOAP12) && !isExtensionMode())) {
            throw new BadCommandLineException(WscompileMessages.WSGEN_SOAP_12_WITHOUT_EXTENSION());
        }
        if (this.nonstdProtocols.containsKey(this.protocol) && !isExtensionMode()) {
            throw new BadCommandLineException(WscompileMessages.WSGEN_PROTOCOL_WITHOUT_EXTENSION(this.protocol));
        }
        if (this.inlineSchemas && !this.genWsdl) {
            throw new BadCommandLineException(WscompileMessages.WSGEN_INLINE_SCHEMAS_ONLY_WITH_WSDL());
        }
        validateEndpointClass();
        validateArguments();
    }

    private void validateEndpointClass() throws BadCommandLineException {
        Class clazz = null;
        for (String cls : this.endpoints) {
            clazz = getClass(cls);
            if (clazz != null && !clazz.isEnum() && !clazz.isInterface() && !clazz.isPrimitive()) {
                this.isImplClass = true;
                WebService webService = clazz.getAnnotation(WebService.class);
                if (webService != null) {
                    break;
                }
            }
        }
        if (clazz == null) {
            throw new BadCommandLineException(WscompileMessages.WSGEN_CLASS_NOT_FOUND(this.endpoints.get(0)));
        }
        if (!this.isImplClass) {
            throw new BadCommandLineException(WscompileMessages.WSGEN_CLASS_MUST_BE_IMPLEMENTATION_CLASS(clazz.getName()));
        }
        this.endpoint = clazz;
        validateBinding();
    }

    private void validateBinding() throws BadCommandLineException {
        if (this.genWsdl) {
            BindingID binding = BindingID.parse(this.endpoint);
            if ((binding.equals(BindingID.SOAP12_HTTP) || binding.equals(BindingID.SOAP12_HTTP_MTOM)) && (!this.protocol.equals(X_SOAP12) || !isExtensionMode())) {
                throw new BadCommandLineException(WscompileMessages.WSGEN_CANNOT_GEN_WSDL_FOR_SOAP_12_BINDING(binding.toString(), this.endpoint.getName()));
            }
            if (binding.equals(BindingID.XML_HTTP)) {
                throw new BadCommandLineException(WscompileMessages.WSGEN_CANNOT_GEN_WSDL_FOR_NON_SOAP_BINDING(binding.toString(), this.endpoint.getName()));
            }
        }
    }

    private void validateArguments() throws BadCommandLineException {
        if (!this.genWsdl) {
            if (this.serviceName != null) {
                throw new BadCommandLineException(WscompileMessages.WSGEN_WSDL_ARG_NO_GENWSDL(SERVICENAME_OPTION));
            }
            if (this.portName != null) {
                throw new BadCommandLineException(WscompileMessages.WSGEN_WSDL_ARG_NO_GENWSDL(PORTNAME_OPTION));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BindingID getBindingID(String protocol) {
        if (protocol.equals(SOAP11)) {
            return BindingID.SOAP11_HTTP;
        }
        if (protocol.equals(X_SOAP12)) {
            return BindingID.SOAP12_HTTP;
        }
        String lexical = this.nonstdProtocols.get(protocol);
        if (lexical != null) {
            return BindingID.parse(lexical);
        }
        return null;
    }

    private Class getClass(String className) {
        try {
            return getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
