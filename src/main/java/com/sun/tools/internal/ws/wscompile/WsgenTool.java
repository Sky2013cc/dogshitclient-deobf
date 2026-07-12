package com.sun.tools.internal.ws.wscompile;

import com.oracle.webservices.internal.api.databinding.WSDLResolver;
import com.sun.istack.internal.tools.ParallelWorldClassLoader;
import com.sun.tools.internal.ws.ToolVersion;
import com.sun.tools.internal.ws.processor.modeler.annotation.WebServiceAp;
import com.sun.tools.internal.ws.processor.modeler.wsdl.ConsoleErrorReporter;
import com.sun.tools.internal.ws.resources.WscompileMessages;
import com.sun.tools.internal.ws.wscompile.Options;
import com.sun.tools.internal.xjc.util.NullStream;
import com.sun.xml.internal.txw2.TXW;
import com.sun.xml.internal.txw2.TypedXmlWriter;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;
import com.sun.xml.internal.txw2.output.StreamSerializer;
import com.sun.xml.internal.ws.api.BindingID;
import com.sun.xml.internal.ws.api.databinding.DatabindingConfig;
import com.sun.xml.internal.ws.api.databinding.DatabindingFactory;
import com.sun.xml.internal.ws.api.databinding.WSDLGenInfo;
import com.sun.xml.internal.ws.api.server.Container;
import com.sun.xml.internal.ws.api.wsdl.writer.WSDLGeneratorExtension;
import com.sun.xml.internal.ws.binding.WebServiceFeatureList;
import com.sun.xml.internal.ws.db.DatabindingImpl;
import com.sun.xml.internal.ws.model.AbstractSEIModelImpl;
import com.sun.xml.internal.ws.model.ExternalMetadataReader;
import com.sun.xml.internal.ws.util.ServiceFinder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.Holder;
import org.xml.sax.SAXParseException;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/WsgenTool.class */
public class WsgenTool {
    private final PrintStream out;
    private final WsgenOptions options;
    private final Container container;

    public WsgenTool(OutputStream out, Container container) {
        this.options = new WsgenOptions();
        this.out = out instanceof PrintStream ? (PrintStream) out : new PrintStream(out);
        this.container = container;
    }

    public WsgenTool(OutputStream out) {
        this(out, null);
    }

    public boolean run(String[] args) {
        Listener listener = new Listener();
        for (String arg : args) {
            try {
                if (arg.equals("-version")) {
                    listener.message(WscompileMessages.WSGEN_VERSION(ToolVersion.VERSION.MAJOR_VERSION));
                    return true;
                }
                if (arg.equals("-fullversion")) {
                    listener.message(WscompileMessages.WSGEN_FULLVERSION(ToolVersion.VERSION.toString()));
                    return true;
                }
            } catch (Throwable th) {
                if (!this.options.keep) {
                    this.options.removeGeneratedFiles();
                }
                throw th;
            }
        }
        try {
            try {
                this.options.parseArguments(args);
                this.options.validate();
                if (!buildModel(this.options.endpoint.getName(), listener)) {
                    if (!this.options.keep) {
                        this.options.removeGeneratedFiles();
                    }
                    return false;
                }
                if (this.options.keep) {
                    return true;
                }
                this.options.removeGeneratedFiles();
                return true;
            } catch (Options.WeAreDone done) {
                usage(done.getOptions());
                if (this.options.keep) {
                    return true;
                }
                this.options.removeGeneratedFiles();
                return true;
            }
        } catch (AbortException e) {
            if (this.options.keep) {
                return true;
            }
            this.options.removeGeneratedFiles();
            return true;
        } catch (BadCommandLineException e2) {
            if (e2.getMessage() != null) {
                System.out.println(e2.getMessage());
                System.out.println();
            }
            usage(e2.getOptions());
            if (!this.options.keep) {
                this.options.removeGeneratedFiles();
            }
            return false;
        }
    }

    private static boolean useBootClasspath(Class clazz) {
        try {
            ParallelWorldClassLoader.toJarUrl(clazz.getResource('/' + clazz.getName().replace('.', '/') + ".class"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean buildModel(String endpoint, Listener listener) throws BadCommandLineException {
        final ErrorReceiverFilter errReceiver = new ErrorReceiverFilter(listener);
        boolean bootCP = useBootClasspath(EndpointReference.class) || useBootClasspath(XmlSeeAlso.class);
        List<String> args = new ArrayList<>(6 + (bootCP ? 1 : 0) + (this.options.nocompile ? 1 : 0) + (this.options.encoding != null ? 2 : 0));
        args.add("-d");
        args.add(this.options.destDir.getAbsolutePath());
        args.add("-classpath");
        args.add(this.options.classpath);
        args.add("-s");
        args.add(this.options.sourceDir.getAbsolutePath());
        if (this.options.nocompile) {
            args.add("-proc:only");
        }
        if (this.options.encoding != null) {
            args.add("-encoding");
            args.add(this.options.encoding);
        }
        if (bootCP) {
            args.add("-Xbootclasspath/p:" + JavaCompilerHelper.getJarFile(EndpointReference.class) + File.pathSeparator + JavaCompilerHelper.getJarFile(XmlSeeAlso.class));
        }
        if (this.options.javacOptions != null) {
            args.addAll(this.options.getJavacOptions(args, listener));
        }
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, (Locale) null, (Charset) null);
        JavaCompiler.CompilationTask task = compiler.getTask((Writer) null, fileManager, diagnostics, args, Collections.singleton(endpoint.replaceAll("\\$", Constants.NAME_SEPARATOR)), (Iterable) null);
        task.setProcessors(Collections.singleton(new WebServiceAp(this.options, this.out)));
        boolean result = task.call().booleanValue();
        if (!result) {
            this.out.println(WscompileMessages.WSCOMPILE_ERROR(WscompileMessages.WSCOMPILE_COMPILATION_FAILED()));
            return false;
        }
        if (this.options.genWsdl) {
            DatabindingConfig config = new DatabindingConfig();
            List<String> externalMetadataFileNames = this.options.externalMetadataFiles;
            boolean disableXmlSecurity = this.options.disableXmlSecurity;
            if (externalMetadataFileNames != null && externalMetadataFileNames.size() > 0) {
                config.setMetadataReader(new ExternalMetadataReader(getExternalFiles(externalMetadataFileNames), (Collection) null, (ClassLoader) null, true, disableXmlSecurity));
            }
            String tmpPath = this.options.destDir.getAbsolutePath() + File.pathSeparator + this.options.classpath;
            ClassLoader classLoader = new URLClassLoader(Options.pathToURLs(tmpPath), getClass().getClassLoader());
            try {
                Class<?> endpointClass = classLoader.loadClass(endpoint);
                BindingID bindingID = this.options.getBindingID(this.options.protocol);
                if (!this.options.protocolSet) {
                    bindingID = BindingID.parse(endpointClass);
                }
                WebServiceFeatureList wsfeatures = new WebServiceFeatureList(endpointClass);
                if (this.options.portName != null) {
                    config.getMappingInfo().setPortName(this.options.portName);
                }
                DatabindingFactory fac = DatabindingFactory.newInstance();
                config.setEndpointClass(endpointClass);
                config.getMappingInfo().setServiceName(this.options.serviceName);
                config.setFeatures(wsfeatures.toArray());
                config.setClassLoader(classLoader);
                config.getMappingInfo().setBindingID(bindingID);
                DatabindingImpl rt = fac.createRuntime(config);
                final File[] wsdlFileName = new File[1];
                final Map<String, File> schemaFiles = new HashMap<>();
                WSDLGenInfo wsdlGenInfo = new WSDLGenInfo();
                wsdlGenInfo.setSecureXmlProcessingDisabled(disableXmlSecurity);
                wsdlGenInfo.setWsdlResolver(new WSDLResolver() { // from class: com.sun.tools.internal.ws.wscompile.WsgenTool.1
                    private File toFile(String suggestedFilename) {
                        return new File(WsgenTool.this.options.nonclassDestDir, suggestedFilename);
                    }

                    private Result toResult(File file) {
                        try {
                            Result result2 = new StreamResult(new FileOutputStream(file));
                            result2.setSystemId(file.getPath().replace('\\', '/'));
                            return result2;
                        } catch (FileNotFoundException e) {
                            errReceiver.error(e);
                            return null;
                        }
                    }

                    public Result getWSDL(String suggestedFilename) {
                        File f = toFile(suggestedFilename);
                        wsdlFileName[0] = f;
                        return toResult(f);
                    }

                    public Result getSchemaOutput(String namespace, String suggestedFilename) {
                        if (namespace == null) {
                            return null;
                        }
                        File f = toFile(suggestedFilename);
                        schemaFiles.put(namespace, f);
                        return toResult(f);
                    }

                    public Result getAbstractWSDL(Holder<String> filename) {
                        return toResult(toFile((String) filename.value));
                    }

                    public Result getSchemaOutput(String namespace, Holder<String> filename) {
                        return getSchemaOutput(namespace, (String) filename.value);
                    }
                });
                wsdlGenInfo.setContainer(this.container);
                wsdlGenInfo.setExtensions((WSDLGeneratorExtension[]) ServiceFinder.find(WSDLGeneratorExtension.class).toArray());
                wsdlGenInfo.setInlineSchemas(this.options.inlineSchemas);
                rt.generateWSDL(wsdlGenInfo);
                if (this.options.wsgenReport != null) {
                    generateWsgenReport(endpointClass, (AbstractSEIModelImpl) rt.getModel(), wsdlFileName[0], schemaFiles);
                    return true;
                }
                return true;
            } catch (ClassNotFoundException e) {
                throw new BadCommandLineException(WscompileMessages.WSGEN_CLASS_NOT_FOUND(endpoint));
            }
        }
        return true;
    }

    private List<File> getExternalFiles(List<String> exts) {
        List<File> files = new ArrayList<>();
        for (String ext : exts) {
            File file = new File(ext);
            if (!file.exists()) {
                file = new File(this.options.sourceDir.getAbsolutePath() + File.separator + ext);
            }
            files.add(file);
        }
        return files;
    }

    private void generateWsgenReport(Class<?> endpointClass, AbstractSEIModelImpl rtModel, File wsdlFile, Map<String, File> schemaFiles) {
        try {
            ReportOutput.Report report = (ReportOutput.Report) TXW.create(ReportOutput.Report.class, new StreamSerializer(new BufferedOutputStream(new FileOutputStream(this.options.wsgenReport))));
            report.wsdl(wsdlFile.getAbsolutePath());
            ReportOutput.writeQName(rtModel.getServiceQName(), report.service());
            ReportOutput.writeQName(rtModel.getPortName(), report.port());
            ReportOutput.writeQName(rtModel.getPortTypeName(), report.portType());
            report.implClass(endpointClass.getName());
            for (Map.Entry<String, File> e : schemaFiles.entrySet()) {
                ReportOutput.Schema s = report.schema();
                s.ns(e.getKey());
                s.location(e.getValue().getAbsolutePath());
            }
            report.commit();
        } catch (IOException e2) {
            throw new Error(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/WsgenTool$ReportOutput.class */
    public static class ReportOutput {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/WsgenTool$ReportOutput$QualifiedName.class */
        public interface QualifiedName extends TypedXmlWriter {
            @XmlAttribute
            void uri(String str);

            @XmlAttribute
            void localName(String str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @XmlElement("report")
        /* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/WsgenTool$ReportOutput$Report.class */
        public interface Report extends TypedXmlWriter {
            @XmlElement
            void wsdl(String str);

            @XmlElement
            QualifiedName portType();

            @XmlElement
            QualifiedName service();

            @XmlElement
            QualifiedName port();

            @XmlElement
            void implClass(String str);

            @XmlElement
            Schema schema();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/WsgenTool$ReportOutput$Schema.class */
        public interface Schema extends TypedXmlWriter {
            @XmlAttribute
            void ns(String str);

            @XmlAttribute
            void location(String str);
        }

        ReportOutput() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void writeQName(QName n, QualifiedName w) {
            w.uri(n.getNamespaceURI());
            w.localName(n.getLocalPart());
        }
    }

    protected void usage(Options options) {
        if (options == null) {
            options = this.options;
        }
        if (options instanceof WsgenOptions) {
            System.out.println(WscompileMessages.WSGEN_HELP("WSGEN", ((WsgenOptions) options).protocols, ((WsgenOptions) options).nonstdProtocols.keySet()));
            System.out.println(WscompileMessages.WSGEN_USAGE_EXTENSIONS());
            System.out.println(WscompileMessages.WSGEN_USAGE_EXAMPLES());
        }
    }

    /* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/WsgenTool$Listener.class */
    class Listener extends WsimportListener {
        ConsoleErrorReporter cer;

        Listener() {
            this.cer = new ConsoleErrorReporter(WsgenTool.this.out == null ? new PrintStream(new NullStream()) : WsgenTool.this.out);
        }

        @Override // com.sun.tools.internal.ws.wscompile.WsimportListener
        public void generatedFile(String fileName) {
            message(fileName);
        }

        @Override // com.sun.tools.internal.ws.wscompile.WsimportListener
        public void message(String msg) {
            WsgenTool.this.out.println(msg);
        }

        @Override // com.sun.tools.internal.ws.wscompile.WsimportListener, com.sun.tools.internal.xjc.api.ErrorListener
        public void error(SAXParseException exception) {
            this.cer.error(exception);
        }

        @Override // com.sun.tools.internal.ws.wscompile.WsimportListener, com.sun.tools.internal.xjc.api.ErrorListener
        public void fatalError(SAXParseException exception) {
            this.cer.fatalError(exception);
        }

        @Override // com.sun.tools.internal.ws.wscompile.WsimportListener, com.sun.tools.internal.xjc.api.ErrorListener
        public void warning(SAXParseException exception) {
            this.cer.warning(exception);
        }

        @Override // com.sun.tools.internal.ws.wscompile.WsimportListener, com.sun.tools.internal.xjc.api.ErrorListener
        public void info(SAXParseException exception) {
            this.cer.info(exception);
        }
    }
}
