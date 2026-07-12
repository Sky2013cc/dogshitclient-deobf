package com.sun.tools.internal.xjc;

import com.sun.codemodel.internal.CodeWriter;
import com.sun.codemodel.internal.writer.FileCodeWriter;
import com.sun.codemodel.internal.writer.PrologCodeWriter;
import com.sun.istack.internal.tools.DefaultAuthenticator;
import com.sun.org.apache.xml.internal.resolver.CatalogManager;
import com.sun.org.apache.xml.internal.resolver.tools.CatalogResolver;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.internal.xjc.api.ClassNameAllocator;
import com.sun.tools.internal.xjc.api.SpecVersion;
import com.sun.tools.internal.xjc.generator.bean.field.FieldRendererFactory;
import com.sun.xml.internal.bind.Util;
import com.sun.xml.internal.bind.api.impl.NameConverter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/internal/xjc/Options.class */
public class Options {
    public boolean debugMode;
    public boolean verbose;
    public boolean quiet;
    public boolean readOnly;
    public boolean noFileHeader;
    public boolean enableIntrospection;
    public boolean contentForWildcard;
    public String encoding;
    public boolean disableXmlSecurity;
    public static final int STRICT = 1;
    public static final int EXTENSION = 2;
    private static final Logger logger = Util.getClassLogger();
    public SpecVersion target;
    private List<Plugin> allPlugins;
    public ClassNameAllocator classNameAllocator;
    private static String pluginLoadFailure;
    public boolean strictCheck = true;
    public boolean runtime14 = false;
    public boolean automaticNameConflictResolution = false;
    public int compatibilityMode = 1;
    public File targetDir = new File(Constants.NAME_SEPARATOR);
    public EntityResolver entityResolver = null;
    private Language schemaLanguage = null;
    public String defaultPackage = null;
    public String defaultPackage2 = null;
    private final List<InputSource> grammars = new ArrayList();
    private final List<InputSource> bindFiles = new ArrayList();
    private String proxyHost = null;
    private String proxyPort = null;
    public String proxyAuth = null;
    public final List<Plugin> activePlugins = new ArrayList();
    public final Set<String> pluginURIs = new HashSet();
    public boolean packageLevelAnnotations = true;
    private FieldRendererFactory fieldRendererFactory = new FieldRendererFactory();
    private Plugin fieldRendererFactoryOwner = null;
    private NameConverter nameConverter = null;
    private Plugin nameConverterOwner = null;
    public final List<URL> classpaths = new ArrayList();

    public boolean isExtensionMode() {
        return this.compatibilityMode == 2;
    }

    public Options() {
        this.target = SpecVersion.LATEST;
        try {
            Class.forName("javax.xml.bind.JAXBPermission");
        } catch (ClassNotFoundException e) {
            this.target = SpecVersion.V2_1;
        }
    }

    public FieldRendererFactory getFieldRendererFactory() {
        return this.fieldRendererFactory;
    }

    public void setFieldRendererFactory(FieldRendererFactory frf, Plugin owner) throws BadCommandLineException {
        if (frf == null) {
            throw new IllegalArgumentException();
        }
        if (this.fieldRendererFactoryOwner != null) {
            throw new BadCommandLineException(Messages.format("FIELD_RENDERER_CONFLICT", this.fieldRendererFactoryOwner.getOptionName(), owner.getOptionName()));
        }
        this.fieldRendererFactoryOwner = owner;
        this.fieldRendererFactory = frf;
    }

    public NameConverter getNameConverter() {
        return this.nameConverter;
    }

    public void setNameConverter(NameConverter nc, Plugin owner) throws BadCommandLineException {
        if (nc == null) {
            throw new IllegalArgumentException();
        }
        if (this.nameConverter != null) {
            throw new BadCommandLineException(Messages.format("NAME_CONVERTER_CONFLICT", this.nameConverterOwner.getOptionName(), owner.getOptionName()));
        }
        this.nameConverterOwner = owner;
        this.nameConverter = nc;
    }

    public List<Plugin> getAllPlugins() {
        if (this.allPlugins == null) {
            this.allPlugins = new ArrayList();
            ClassLoader ucl = getUserClassLoader(SecureLoader.getClassClassLoader(getClass()));
            this.allPlugins.addAll(Arrays.asList(findServices(Plugin.class, ucl)));
        }
        return this.allPlugins;
    }

    public Language getSchemaLanguage() {
        if (this.schemaLanguage == null) {
            this.schemaLanguage = guessSchemaLanguage();
        }
        return this.schemaLanguage;
    }

    public void setSchemaLanguage(Language _schemaLanguage) {
        this.schemaLanguage = _schemaLanguage;
    }

    public InputSource[] getGrammars() {
        return (InputSource[]) this.grammars.toArray(new InputSource[this.grammars.size()]);
    }

    public void addGrammar(InputSource is) {
        this.grammars.add(absolutize(is));
    }

    private InputSource fileToInputSource(File source) {
        try {
            String url = source.toURL().toExternalForm();
            return new InputSource(com.sun.tools.internal.xjc.reader.Util.escapeSpace(url));
        } catch (MalformedURLException e) {
            return new InputSource(source.getPath());
        }
    }

    public void addGrammar(File source) {
        addGrammar(fileToInputSource(source));
    }

    public void addGrammarRecursive(File dir) {
        addRecursive(dir, ".xsd", this.grammars);
    }

    private void addRecursive(File dir, String suffix, List<InputSource> result) {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                addRecursive(f, suffix, result);
            } else if (f.getPath().endsWith(suffix)) {
                result.add(absolutize(fileToInputSource(f)));
            }
        }
    }

    private InputSource absolutize(InputSource is) {
        try {
            URL baseURL = new File(Constants.NAME_SEPARATOR).getCanonicalFile().toURL();
            is.setSystemId(new URL(baseURL, is.getSystemId()).toExternalForm());
        } catch (IOException e) {
            logger.log(Level.FINE, "{0}, {1}", new Object[]{is.getSystemId(), e.getLocalizedMessage()});
        }
        return is;
    }

    public InputSource[] getBindFiles() {
        return (InputSource[]) this.bindFiles.toArray(new InputSource[this.bindFiles.size()]);
    }

    public void addBindFile(InputSource is) {
        this.bindFiles.add(absolutize(is));
    }

    public void addBindFile(File bindFile) {
        this.bindFiles.add(fileToInputSource(bindFile));
    }

    public void addBindFileRecursive(File dir) {
        addRecursive(dir, ".xjb", this.bindFiles);
    }

    public ClassLoader getUserClassLoader(ClassLoader parent) {
        if (this.classpaths.isEmpty()) {
            return parent;
        }
        return new URLClassLoader((URL[]) this.classpaths.toArray(new URL[this.classpaths.size()]), parent);
    }

    public int parseArgument(String[] args, int i) throws BadCommandLineException {
        if (args[i].equals("-classpath") || args[i].equals("-cp")) {
            String a = requireArgument(args[i], args, i + 1);
            for (String p : a.split(File.pathSeparator)) {
                File file = new File(p);
                try {
                    this.classpaths.add(file.toURL());
                } catch (MalformedURLException e) {
                    throw new BadCommandLineException(Messages.format("Driver.NotAValidFileName", file), e);
                }
            }
            return 2;
        }
        if (args[i].equals("-d")) {
            this.targetDir = new File(requireArgument("-d", args, i + 1));
            if (!this.targetDir.exists()) {
                throw new BadCommandLineException(Messages.format("Driver.NonExistentDir", this.targetDir));
            }
            return 2;
        }
        if (args[i].equals("-readOnly")) {
            this.readOnly = true;
            return 1;
        }
        if (args[i].equals("-p")) {
            this.defaultPackage = requireArgument("-p", args, i + 1);
            if (this.defaultPackage.length() == 0) {
                this.packageLevelAnnotations = false;
                return 2;
            }
            return 2;
        }
        if (args[i].equals("-debug")) {
            this.debugMode = true;
            this.verbose = true;
            return 1;
        }
        if (args[i].equals("-nv")) {
            this.strictCheck = false;
            return 1;
        }
        if (args[i].equals("-npa")) {
            this.packageLevelAnnotations = false;
            return 1;
        }
        if (args[i].equals("-no-header")) {
            this.noFileHeader = true;
            return 1;
        }
        if (args[i].equals("-verbose")) {
            this.verbose = true;
            return 1;
        }
        if (args[i].equals("-quiet")) {
            this.quiet = true;
            return 1;
        }
        if (args[i].equals("-XexplicitAnnotation")) {
            this.runtime14 = true;
            return 1;
        }
        if (args[i].equals("-enableIntrospection")) {
            this.enableIntrospection = true;
            return 1;
        }
        if (args[i].equals(com.sun.tools.internal.jxc.ap.Options.DISABLE_XML_SECURITY)) {
            this.disableXmlSecurity = true;
            return 1;
        }
        if (args[i].equals("-contentForWildcard")) {
            this.contentForWildcard = true;
            return 1;
        }
        if (args[i].equals("-XautoNameResolution")) {
            this.automaticNameConflictResolution = true;
            return 1;
        }
        if (args[i].equals("-b")) {
            addFile(requireArgument("-b", args, i + 1), this.bindFiles, ".xjb");
            return 2;
        }
        if (args[i].equals("-dtd")) {
            this.schemaLanguage = Language.DTD;
            return 1;
        }
        if (args[i].equals("-relaxng")) {
            this.schemaLanguage = Language.RELAXNG;
            return 1;
        }
        if (args[i].equals("-relaxng-compact")) {
            this.schemaLanguage = Language.RELAXNG_COMPACT;
            return 1;
        }
        if (args[i].equals("-xmlschema")) {
            this.schemaLanguage = Language.XMLSCHEMA;
            return 1;
        }
        if (args[i].equals("-wsdl")) {
            this.schemaLanguage = Language.WSDL;
            return 1;
        }
        if (args[i].equals("-extension")) {
            this.compatibilityMode = 2;
            return 1;
        }
        if (args[i].equals("-target")) {
            String token = requireArgument("-target", args, i + 1);
            this.target = SpecVersion.parse(token);
            if (this.target == null) {
                throw new BadCommandLineException(Messages.format("Driver.ILLEGAL_TARGET_VERSION", token));
            }
            return 2;
        }
        if (args[i].equals("-httpproxyfile")) {
            if (i == args.length - 1 || args[i + 1].startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                throw new BadCommandLineException(Messages.format("Driver.MISSING_PROXYFILE", new Object[0]));
            }
            File file2 = new File(args[i + 1]);
            if (!file2.exists()) {
                throw new BadCommandLineException(Messages.format("Driver.NO_SUCH_FILE", file2));
            }
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file2), "UTF-8"));
                parseProxy(in.readLine());
                in.close();
                return 2;
            } catch (IOException e2) {
                throw new BadCommandLineException(Messages.format("Driver.FailedToParse", file2, e2.getMessage()), e2);
            }
        }
        if (args[i].equals("-httpproxy")) {
            if (i == args.length - 1 || args[i + 1].startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                throw new BadCommandLineException(Messages.format("Driver.MISSING_PROXY", new Object[0]));
            }
            parseProxy(args[i + 1]);
            return 2;
        }
        if (args[i].equals("-host")) {
            this.proxyHost = requireArgument("-host", args, i + 1);
            return 2;
        }
        if (args[i].equals("-port")) {
            this.proxyPort = requireArgument("-port", args, i + 1);
            return 2;
        }
        if (args[i].equals("-catalog")) {
            File catalogFile = new File(requireArgument("-catalog", args, i + 1));
            try {
                addCatalog(catalogFile);
                return 2;
            } catch (IOException e3) {
                throw new BadCommandLineException(Messages.format("Driver.FailedToParse", catalogFile, e3.getMessage()), e3);
            }
        }
        if (args[i].equals("-Xtest-class-name-allocator")) {
            this.classNameAllocator = new ClassNameAllocator() { // from class: com.sun.tools.internal.xjc.Options.1
                @Override // com.sun.tools.internal.xjc.api.ClassNameAllocator
                public String assignClassName(String packageName, String className) {
                    System.out.printf("assignClassName(%s,%s)\n", packageName, className);
                    return className + "_Type";
                }
            };
            return 1;
        }
        if (args[i].equals("-encoding")) {
            this.encoding = requireArgument("-encoding", args, i + 1);
            try {
                if (!Charset.isSupported(this.encoding)) {
                    throw new BadCommandLineException(Messages.format("Driver.UnsupportedEncoding", this.encoding));
                }
                return 2;
            } catch (IllegalCharsetNameException e4) {
                throw new BadCommandLineException(Messages.format("Driver.UnsupportedEncoding", this.encoding));
            }
        }
        for (Plugin plugin : getAllPlugins()) {
            try {
                if (('-' + plugin.getOptionName()).equals(args[i])) {
                    this.activePlugins.add(plugin);
                    plugin.onActivated(this);
                    this.pluginURIs.addAll(plugin.getCustomizationURIs());
                    int r = plugin.parseArgument(this, args, i);
                    if (r != 0) {
                        return r;
                    }
                    return 1;
                }
                int r2 = plugin.parseArgument(this, args, i);
                if (r2 != 0) {
                    return r2;
                }
            } catch (IOException e5) {
                throw new BadCommandLineException(e5.getMessage(), e5);
            }
        }
        return 0;
    }

    private void parseProxy(String text) throws BadCommandLineException {
        int i = text.lastIndexOf(64);
        int j = text.lastIndexOf(58);
        if (i > 0) {
            this.proxyAuth = text.substring(0, i);
            if (j > i) {
                this.proxyHost = text.substring(i + 1, j);
                this.proxyPort = text.substring(j + 1);
            } else {
                this.proxyHost = text.substring(i + 1);
                this.proxyPort = "80";
            }
        } else if (j < 0) {
            this.proxyHost = text;
            this.proxyPort = "80";
        } else {
            this.proxyHost = text.substring(0, j);
            this.proxyPort = text.substring(j + 1);
        }
        try {
            Integer.valueOf(this.proxyPort);
        } catch (NumberFormatException e) {
            throw new BadCommandLineException(Messages.format("Driver.ILLEGAL_PROXY", text));
        }
    }

    public String requireArgument(String optionName, String[] args, int i) throws BadCommandLineException {
        if (i == args.length || args[i].startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
            throw new BadCommandLineException(Messages.format("Driver.MissingOperand", optionName));
        }
        return args[i];
    }

    private void addFile(String name, List<InputSource> target, String suffix) throws BadCommandLineException {
        try {
            Object src = com.sun.tools.internal.xjc.reader.Util.getFileOrURL(name);
            if (src instanceof URL) {
                target.add(absolutize(new InputSource(com.sun.tools.internal.xjc.reader.Util.escapeSpace(((URL) src).toExternalForm()))));
                return;
            }
            File fsrc = (File) src;
            if (fsrc.isDirectory()) {
                addRecursive(fsrc, suffix, target);
            } else {
                target.add(absolutize(fileToInputSource(fsrc)));
            }
        } catch (IOException e) {
            throw new BadCommandLineException(Messages.format("Driver.NotAFileNorURL", name));
        }
    }

    public void addCatalog(File catalogFile) throws IOException {
        if (this.entityResolver == null) {
            CatalogManager.getStaticManager().setIgnoreMissingProperties(true);
            this.entityResolver = new CatalogResolver(true);
        }
        this.entityResolver.getCatalog().parseCatalog(catalogFile.getPath());
    }

    public void parseArguments(String[] args) throws BadCommandLineException {
        int i = 0;
        while (i < args.length) {
            if (args[i].length() == 0) {
                throw new BadCommandLineException();
            }
            if (args[i].charAt(0) == '-') {
                int j = parseArgument(args, i);
                if (j == 0) {
                    throw new BadCommandLineException(Messages.format("Driver.UnrecognizedParameter", args[i]));
                }
                i += j - 1;
            } else if (args[i].endsWith(".jar")) {
                scanEpisodeFile(new File(args[i]));
            } else {
                addFile(args[i], this.grammars, ".xsd");
            }
            i++;
        }
        if (this.proxyHost != null || this.proxyPort != null) {
            if (this.proxyHost != null && this.proxyPort != null) {
                System.setProperty("http.proxyHost", this.proxyHost);
                System.setProperty("http.proxyPort", this.proxyPort);
                System.setProperty("https.proxyHost", this.proxyHost);
                System.setProperty("https.proxyPort", this.proxyPort);
                if (this.proxyAuth != null) {
                    DefaultAuthenticator.getAuthenticator().setProxyAuth(this.proxyAuth);
                }
            } else {
                if (this.proxyHost == null) {
                    throw new BadCommandLineException(Messages.format("Driver.MissingProxyHost", new Object[0]));
                }
                throw new BadCommandLineException(Messages.format("Driver.MissingProxyPort", new Object[0]));
            }
        }
        if (this.grammars.isEmpty()) {
            throw new BadCommandLineException(Messages.format("Driver.MissingGrammar", new Object[0]));
        }
        if (this.schemaLanguage == null) {
            this.schemaLanguage = guessSchemaLanguage();
        }
        if (pluginLoadFailure != null) {
            throw new BadCommandLineException(Messages.format("PLUGIN_LOAD_FAILURE", pluginLoadFailure));
        }
    }

    public void scanEpisodeFile(File jar) throws BadCommandLineException {
        try {
            URLClassLoader ucl = new URLClassLoader(new URL[]{jar.toURL()});
            Enumeration<URL> resources = ucl.findResources("META-INF/sun-jaxb.episode");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                addBindFile(new InputSource(url.toExternalForm()));
            }
        } catch (IOException e) {
            throw new BadCommandLineException(Messages.format("FAILED_TO_LOAD", jar, e.getMessage()), e);
        }
    }

    public Language guessSchemaLanguage() {
        if (this.grammars != null && this.grammars.size() > 0) {
            String name = this.grammars.get(0).getSystemId().toLowerCase();
            if (name.endsWith(".rng")) {
                return Language.RELAXNG;
            }
            if (name.endsWith(".rnc")) {
                return Language.RELAXNG_COMPACT;
            }
            if (name.endsWith(".dtd")) {
                return Language.DTD;
            }
            if (name.endsWith(".wsdl")) {
                return Language.WSDL;
            }
        }
        return Language.XMLSCHEMA;
    }

    public CodeWriter createCodeWriter() throws IOException {
        return createCodeWriter(new FileCodeWriter(this.targetDir, this.readOnly, this.encoding));
    }

    public CodeWriter createCodeWriter(CodeWriter core) {
        if (this.noFileHeader) {
            return core;
        }
        return new PrologCodeWriter(core, getPrologComment());
    }

    public String getPrologComment() {
        String format = Messages.format("Driver.DateFormat", new Object[0]) + " '" + Messages.format("Driver.At", new Object[0]) + "' " + Messages.format("Driver.TimeFormat", new Object[0]);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        return Messages.format("Driver.FilePrologComment", dateFormat.format(new Date()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T[] findServices(Class<T> cls, ClassLoader classLoader) {
        boolean z = com.sun.tools.internal.xjc.util.Util.getSystemProperty(Options.class, "findServices") != null;
        try {
            Class<?> cls2 = Class.forName("java.util.ServiceLoader");
            if (z) {
                System.out.println("Using java.util.ServiceLoader");
            }
            Iterable iterable = (Iterable) cls2.getMethod("load", Class.class, ClassLoader.class).invoke(null, cls, classLoader);
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            return (T[]) arrayList.toArray((Object[]) Array.newInstance((Class<?>) cls, arrayList.size()));
        } catch (ClassNotFoundException e) {
            String str = "META-INF/services/" + cls.getName();
            HashSet hashSet = new HashSet();
            if (z) {
                System.out.println("Looking for " + str + " for add-ons");
            }
            try {
                Enumeration<URL> resources = classLoader.getResources(str);
                if (resources == null) {
                    return (T[]) ((Object[]) Array.newInstance((Class<?>) cls, 0));
                }
                ArrayList arrayList2 = new ArrayList();
                while (resources.hasMoreElements()) {
                    URL nextElement = resources.nextElement();
                    BufferedReader bufferedReader = null;
                    if (z) {
                        System.out.println("Checking " + nextElement + " for an add-on");
                    }
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(nextElement.openStream()));
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            String trim = readLine.trim();
                            if (hashSet.add(trim)) {
                                Class<?> loadClass = classLoader.loadClass(trim);
                                if (!cls.isAssignableFrom(loadClass)) {
                                    pluginLoadFailure = trim + " is not a subclass of " + cls + ". Skipping";
                                    if (z) {
                                        System.out.println(pluginLoadFailure);
                                    }
                                } else {
                                    if (z) {
                                        System.out.println("Attempting to instanciate " + trim);
                                    }
                                    arrayList2.add(cls.cast(loadClass.newInstance()));
                                }
                            }
                        }
                        bufferedReader.close();
                    } catch (Exception e2) {
                        StringWriter stringWriter = new StringWriter();
                        e2.printStackTrace(new PrintWriter(stringWriter));
                        pluginLoadFailure = stringWriter.toString();
                        if (z) {
                            System.out.println(pluginLoadFailure);
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                            }
                        }
                    }
                }
                return (T[]) arrayList2.toArray((Object[]) Array.newInstance((Class<?>) cls, arrayList2.size()));
            } catch (Throwable th) {
                StringWriter stringWriter2 = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter2));
                pluginLoadFailure = stringWriter2.toString();
                if (z) {
                    System.out.println(pluginLoadFailure);
                }
                return (T[]) ((Object[]) Array.newInstance((Class<?>) cls, 0));
            }
        } catch (IllegalAccessException e4) {
            IllegalAccessError illegalAccessError = new IllegalAccessError();
            illegalAccessError.initCause(e4);
            throw illegalAccessError;
        } catch (NoSuchMethodException e5) {
            NoSuchMethodError noSuchMethodError = new NoSuchMethodError();
            noSuchMethodError.initCause(e5);
            throw noSuchMethodError;
        } catch (InvocationTargetException e6) {
            Throwable targetException = e6.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            if (targetException instanceof Error) {
                throw ((Error) targetException);
            }
            throw new Error(targetException);
        }
    }

    public static String getBuildID() {
        return Messages.format("Driver.BuildID", new Object[0]);
    }

    public static String normalizeSystemId(String systemId) {
        try {
            systemId = new URI(systemId).normalize().toString();
        } catch (URISyntaxException e) {
        }
        return systemId;
    }
}
