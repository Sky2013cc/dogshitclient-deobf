package com.sun.tools.javap;

import com.sun.tools.classfile.Attribute;
import com.sun.tools.classfile.Attributes;
import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Field;
import com.sun.tools.classfile.InnerClasses_attribute;
import com.sun.tools.classfile.Method;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.javap.DisassemblerTool;
import com.sun.tools.javap.InstructionDetailWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import jdk.internal.dynalink.CallSiteDescriptor;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/javap/JavapTask.class */
public class JavapTask implements DisassemblerTool.DisassemblerTask, Messages {
    static final int EXIT_OK = 0;
    static final int EXIT_ERROR = 1;
    static final int EXIT_CMDERR = 2;
    static final int EXIT_SYSERR = 3;
    static final int EXIT_ABNORMAL = 4;
    private static final String versionRBName = "com.sun.tools.javap.resources.version";
    private static ResourceBundle versionRB;
    protected Context context;
    JavaFileManager fileManager;
    JavaFileManager defaultFileManager;
    PrintWriter log;
    DiagnosticListener<? super JavaFileObject> diagnosticListener;
    List<String> classes;
    Options options;
    Locale task_locale;
    Map<Locale, ResourceBundle> bundles;
    protected Attribute.Factory attributeFactory;
    private static final String progname = "javap";
    static final Option[] recognizedOptions = {new Option(false, "-help", "--help", "-?") { // from class: com.sun.tools.javap.JavapTask.1
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.help = true;
        }
    }, new Option(false, "-version") { // from class: com.sun.tools.javap.JavapTask.2
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.version = true;
        }
    }, new Option(false, "-fullversion") { // from class: com.sun.tools.javap.JavapTask.3
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.fullVersion = true;
        }
    }, new Option(false, "-v", "-verbose", "-all") { // from class: com.sun.tools.javap.JavapTask.4
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.verbose = true;
            javapTask.options.showDescriptors = true;
            javapTask.options.showFlags = true;
            javapTask.options.showAllAttrs = true;
        }
    }, new Option(false, "-l") { // from class: com.sun.tools.javap.JavapTask.5
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.showLineAndLocalVariableTables = true;
        }
    }, new Option(false, "-public") { // from class: com.sun.tools.javap.JavapTask.6
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.accessOptions.add(str);
            javapTask.options.showAccess = 1;
        }
    }, new Option(false, "-protected") { // from class: com.sun.tools.javap.JavapTask.7
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.accessOptions.add(str);
            javapTask.options.showAccess = 4;
        }
    }, new Option(false, "-package") { // from class: com.sun.tools.javap.JavapTask.8
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.accessOptions.add(str);
            javapTask.options.showAccess = 0;
        }
    }, new Option(false, "-p", "-private") { // from class: com.sun.tools.javap.JavapTask.9
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            if (!javapTask.options.accessOptions.contains("-p") && !javapTask.options.accessOptions.contains("-private")) {
                javapTask.options.accessOptions.add(str);
            }
            javapTask.options.showAccess = 2;
        }
    }, new Option(false, "-c") { // from class: com.sun.tools.javap.JavapTask.10
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.showDisassembled = true;
        }
    }, new Option(false, "-s") { // from class: com.sun.tools.javap.JavapTask.11
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.showDescriptors = true;
        }
    }, new Option(false, "-sysinfo") { // from class: com.sun.tools.javap.JavapTask.12
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.sysInfo = true;
        }
    }, new Option(false, "-XDdetails") { // from class: com.sun.tools.javap.JavapTask.13
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.details = EnumSet.allOf(InstructionDetailWriter.Kind.class);
        }
    }, new Option(false, "-XDdetails:") { // from class: com.sun.tools.javap.JavapTask.14
        @Override // com.sun.tools.javap.JavapTask.Option
        boolean matches(String str) {
            int indexOf = str.indexOf(CallSiteDescriptor.TOKEN_DELIMITER);
            return indexOf != -1 && super.matches(str.substring(0, indexOf + 1));
        }

        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) throws BadArgs {
            for (String str3 : str.substring(str.indexOf(CallSiteDescriptor.TOKEN_DELIMITER) + 1).split("[,: ]+")) {
                if (!handleArg(javapTask, str3)) {
                    javapTask.getClass();
                    throw new BadArgs("err.invalid.arg.for.option", str3);
                }
            }
        }

        boolean handleArg(JavapTask javapTask, String str) {
            if (str.length() == 0) {
                return true;
            }
            if (str.equals("all")) {
                javapTask.options.details = EnumSet.allOf(InstructionDetailWriter.Kind.class);
                return true;
            }
            boolean z = true;
            if (str.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                z = false;
                str = str.substring(1);
            }
            for (InstructionDetailWriter.Kind kind : InstructionDetailWriter.Kind.values()) {
                if (str.equalsIgnoreCase(kind.option)) {
                    if (z) {
                        javapTask.options.details.add(kind);
                        return true;
                    }
                    javapTask.options.details.remove(kind);
                    return true;
                }
            }
            return false;
        }
    }, new Option(false, "-constants") { // from class: com.sun.tools.javap.JavapTask.15
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.showConstants = true;
        }
    }, new Option(false, "-XDinner") { // from class: com.sun.tools.javap.JavapTask.16
        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) {
            javapTask.options.showInnerClasses = true;
        }
    }, new Option(false, "-XDindent:") { // from class: com.sun.tools.javap.JavapTask.17
        @Override // com.sun.tools.javap.JavapTask.Option
        boolean matches(String str) {
            int indexOf = str.indexOf(CallSiteDescriptor.TOKEN_DELIMITER);
            return indexOf != -1 && super.matches(str.substring(0, indexOf + 1));
        }

        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) throws BadArgs {
            try {
                int intValue = Integer.valueOf(str.substring(str.indexOf(CallSiteDescriptor.TOKEN_DELIMITER) + 1)).intValue();
                if (intValue > 0) {
                    javapTask.options.indentWidth = intValue;
                }
            } catch (NumberFormatException e) {
            }
        }
    }, new Option(false, "-XDtab:") { // from class: com.sun.tools.javap.JavapTask.18
        @Override // com.sun.tools.javap.JavapTask.Option
        boolean matches(String str) {
            int indexOf = str.indexOf(CallSiteDescriptor.TOKEN_DELIMITER);
            return indexOf != -1 && super.matches(str.substring(0, indexOf + 1));
        }

        @Override // com.sun.tools.javap.JavapTask.Option
        void process(JavapTask javapTask, String str, String str2) throws BadArgs {
            try {
                int intValue = Integer.valueOf(str.substring(str.indexOf(CallSiteDescriptor.TOKEN_DELIMITER) + 1)).intValue();
                if (intValue > 0) {
                    javapTask.options.tabColumn = intValue;
                }
            } catch (NumberFormatException e) {
            }
        }
    }};
    private static final String nl = System.getProperty("line.separator");

    /* loaded from: target.jar:com/sun/tools/javap/JavapTask$BadArgs.class */
    public class BadArgs extends Exception {
        static final long serialVersionUID = 8765093759964640721L;
        final String key;
        final Object[] args;
        boolean showUsage;

        BadArgs(String str, Object... objArr) {
            super(JavapTask.this.getMessage(str, objArr));
            this.key = str;
            this.args = objArr;
        }

        BadArgs showUsage(boolean z) {
            this.showUsage = z;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javap/JavapTask$Option.class */
    public static abstract class Option {
        final boolean hasArg;
        final String[] aliases;

        abstract void process(JavapTask javapTask, String str, String str2) throws BadArgs;

        Option(boolean z, String... strArr) {
            this.hasArg = z;
            this.aliases = strArr;
        }

        boolean matches(String str) {
            for (String str2 : this.aliases) {
                if (str2.equals(str)) {
                    return true;
                }
            }
            return false;
        }

        boolean ignoreRest() {
            return false;
        }
    }

    public JavapTask() {
        this.context = new Context();
        this.context.put(Messages.class, this);
        this.options = Options.instance(this.context);
        this.attributeFactory = new Attribute.Factory();
    }

    public JavapTask(Writer writer, JavaFileManager javaFileManager, DiagnosticListener<? super JavaFileObject> diagnosticListener) {
        this();
        this.log = getPrintWriterForWriter(writer);
        this.fileManager = javaFileManager;
        this.diagnosticListener = diagnosticListener;
    }

    public JavapTask(Writer writer, JavaFileManager javaFileManager, DiagnosticListener<? super JavaFileObject> diagnosticListener, Iterable<String> iterable, Iterable<String> iterable2) {
        this(writer, javaFileManager, diagnosticListener);
        this.classes = new ArrayList();
        for (String str : iterable2) {
            str.getClass();
            this.classes.add(str);
        }
        if (iterable != null) {
            try {
                handleOptions(iterable, false);
            } catch (BadArgs e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    @Override // com.sun.tools.javap.DisassemblerTool.DisassemblerTask
    public void setLocale(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        this.task_locale = locale;
    }

    public void setLog(Writer writer) {
        this.log = getPrintWriterForWriter(writer);
    }

    public void setLog(OutputStream outputStream) {
        setLog(getPrintWriterForStream(outputStream));
    }

    private static PrintWriter getPrintWriterForStream(OutputStream outputStream) {
        return new PrintWriter(outputStream == null ? System.err : outputStream, true);
    }

    private static PrintWriter getPrintWriterForWriter(Writer writer) {
        if (writer == null) {
            return getPrintWriterForStream(null);
        }
        if (writer instanceof PrintWriter) {
            return (PrintWriter) writer;
        }
        return new PrintWriter(writer, true);
    }

    public void setDiagnosticListener(DiagnosticListener<? super JavaFileObject> diagnosticListener) {
        this.diagnosticListener = diagnosticListener;
    }

    public void setDiagnosticListener(OutputStream outputStream) {
        setDiagnosticListener((DiagnosticListener<? super JavaFileObject>) getDiagnosticListenerForStream(outputStream));
    }

    private DiagnosticListener<JavaFileObject> getDiagnosticListenerForStream(OutputStream outputStream) {
        return getDiagnosticListenerForWriter(getPrintWriterForStream(outputStream));
    }

    private DiagnosticListener<JavaFileObject> getDiagnosticListenerForWriter(Writer writer) {
        final PrintWriter printWriterForWriter = getPrintWriterForWriter(writer);
        return new DiagnosticListener<JavaFileObject>() { // from class: com.sun.tools.javap.JavapTask.19
            public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
                switch (AnonymousClass22.$SwitchMap$javax$tools$Diagnostic$Kind[diagnostic.getKind().ordinal()]) {
                    case 1:
                        printWriterForWriter.print(JavapTask.this.getMessage("err.prefix", new Object[0]));
                        break;
                    case 2:
                        printWriterForWriter.print(JavapTask.this.getMessage("warn.prefix", new Object[0]));
                        break;
                    case 3:
                        printWriterForWriter.print(JavapTask.this.getMessage("note.prefix", new Object[0]));
                        break;
                }
                printWriterForWriter.print(" ");
                printWriterForWriter.println(diagnostic.getMessage((Locale) null));
            }
        };
    }

    /* renamed from: com.sun.tools.javap.JavapTask$22, reason: invalid class name */
    /* loaded from: target.jar:com/sun/tools/javap/JavapTask$22.class */
    static /* synthetic */ class AnonymousClass22 {
        static final /* synthetic */ int[] $SwitchMap$javax$tools$Diagnostic$Kind = new int[Diagnostic.Kind.values().length];

        static {
            try {
                $SwitchMap$javax$tools$Diagnostic$Kind[Diagnostic.Kind.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$javax$tools$Diagnostic$Kind[Diagnostic.Kind.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$javax$tools$Diagnostic$Kind[Diagnostic.Kind.NOTE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int run(String[] strArr) {
        Object[] objArr;
        try {
            try {
                handleOptions(strArr);
                if (this.classes == null || this.classes.size() == 0) {
                    if (this.options.help || this.options.version || this.options.fullVersion) {
                        this.log.flush();
                        return 0;
                    }
                    this.log.flush();
                    return 2;
                }
                try {
                    int run = run();
                    if (this.defaultFileManager != null) {
                        try {
                            this.defaultFileManager.close();
                            this.defaultFileManager = null;
                        } catch (IOException e) {
                            throw new InternalError(e, new Object[0]);
                        }
                    }
                    this.log.flush();
                    return run;
                } catch (Throwable th) {
                    if (this.defaultFileManager != null) {
                        try {
                            this.defaultFileManager.close();
                            this.defaultFileManager = null;
                        } catch (IOException e2) {
                            throw new InternalError(e2, new Object[0]);
                        }
                    }
                    throw th;
                }
            } catch (InternalError e3) {
                if (e3.getCause() == null) {
                    objArr = e3.args;
                } else {
                    objArr = new Object[e3.args.length + 1];
                    objArr[0] = e3.getCause();
                    System.arraycopy(e3.args, 0, objArr, 1, e3.args.length);
                }
                reportError("err.internal.error", objArr);
                this.log.flush();
                return 4;
            } catch (BadArgs e4) {
                reportError(e4.key, e4.args);
                if (e4.showUsage) {
                    printLines(getMessage("main.usage.summary", progname));
                }
                this.log.flush();
                return 2;
            }
        } catch (Throwable th2) {
            this.log.flush();
            throw th2;
        }
    }

    public void handleOptions(String[] strArr) throws BadArgs {
        handleOptions(Arrays.asList(strArr), true);
    }

    private void handleOptions(Iterable<String> iterable, boolean z) throws BadArgs {
        if (this.log == null) {
            this.log = getPrintWriterForStream(System.out);
            if (this.diagnosticListener == null) {
                this.diagnosticListener = getDiagnosticListenerForStream(System.err);
            }
        } else if (this.diagnosticListener == null) {
            this.diagnosticListener = getDiagnosticListenerForWriter(this.log);
        }
        if (this.fileManager == null) {
            this.fileManager = getDefaultFileManager(this.diagnosticListener, this.log);
        }
        Iterator<String> it = iterable.iterator();
        boolean z2 = !it.hasNext();
        while (it.hasNext()) {
            String next = it.next();
            if (next.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                handleOption(next, it);
            } else if (z) {
                if (this.classes == null) {
                    this.classes = new ArrayList();
                }
                this.classes.add(next);
                while (it.hasNext()) {
                    this.classes.add(it.next());
                }
            } else {
                throw new BadArgs("err.unknown.option", next).showUsage(true);
            }
        }
        if (this.options.accessOptions.size() > 1) {
            StringBuilder sb = new StringBuilder();
            for (String str : this.options.accessOptions) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(str);
            }
            throw new BadArgs("err.incompatible.options", sb);
        }
        if ((this.classes == null || this.classes.size() == 0) && !z2 && !this.options.help && !this.options.version && !this.options.fullVersion) {
            throw new BadArgs("err.no.classes.specified", new Object[0]);
        }
        if (z2 || this.options.help) {
            showHelp();
        }
        if (this.options.version || this.options.fullVersion) {
            showVersion(this.options.fullVersion);
        }
    }

    private void handleOption(String str, Iterator<String> it) throws BadArgs {
        for (Option option : recognizedOptions) {
            if (option.matches(str)) {
                if (option.hasArg) {
                    if (it.hasNext()) {
                        option.process(this, str, it.next());
                    } else {
                        throw new BadArgs("err.missing.arg", str).showUsage(true);
                    }
                } else {
                    option.process(this, str, null);
                }
                if (option.ignoreRest()) {
                    while (it.hasNext()) {
                        it.next();
                    }
                    return;
                }
                return;
            }
        }
        try {
            if (!this.fileManager.handleOption(str, it)) {
                throw new BadArgs("err.unknown.option", str).showUsage(true);
            }
        } catch (IllegalArgumentException e) {
            throw new BadArgs("err.invalid.use.of.option", str).showUsage(true);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.tools.javap.DisassemblerTool.DisassemblerTask, java.util.concurrent.Callable
    public Boolean call() {
        return Boolean.valueOf(run() == 0);
    }

    public int run() {
        if (this.classes == null || this.classes.isEmpty()) {
            return 1;
        }
        this.context.put(PrintWriter.class, this.log);
        ClassWriter instance = ClassWriter.instance(this.context);
        SourceWriter.instance(this.context).setFileManager(this.fileManager);
        int i = 0;
        for (String str : this.classes) {
            try {
                i = writeClass(instance, str);
            } catch (ConstantPoolException e) {
                reportError("err.bad.constant.pool", str, e.getLocalizedMessage());
                i = 1;
            } catch (EOFException e2) {
                reportError("err.end.of.file", str);
                i = 1;
            } catch (FileNotFoundException e3) {
                reportError("err.file.not.found", e3.getLocalizedMessage());
                i = 1;
            } catch (IOException e4) {
                String localizedMessage = e4.getLocalizedMessage();
                if (localizedMessage == null) {
                    localizedMessage = e4;
                }
                reportError("err.ioerror", str, localizedMessage);
                i = 1;
            } catch (Throwable th) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                th.printStackTrace(printWriter);
                printWriter.close();
                reportError("err.crash", th.toString(), stringWriter.toString());
                i = 4;
            }
        }
        return i;
    }

    protected int writeClass(ClassWriter classWriter, String str) throws IOException, ConstantPoolException {
        JavaFileObject open = open(str);
        if (open == null) {
            reportError("err.class.not.found", str);
            return 1;
        }
        ClassFileInfo read = read(open);
        if (!str.endsWith(".class")) {
            String name = read.cf.getName();
            if (!name.replaceAll("[/$]", Constants.NAME_SEPARATOR).equals(str.replaceAll("[/$]", Constants.NAME_SEPARATOR))) {
                reportWarning("warn.unexpected.class", str, name.replace('/', '.'));
            }
        }
        write(read);
        if (this.options.showInnerClasses) {
            ClassFile classFile = read.cf;
            Attribute attribute = classFile.getAttribute(Attribute.InnerClasses);
            if (attribute instanceof InnerClasses_attribute) {
                InnerClasses_attribute innerClasses_attribute = (InnerClasses_attribute) attribute;
                int i = 0;
                for (int i2 = 0; i2 < innerClasses_attribute.classes.length; i2++) {
                    try {
                        if (classFile.constant_pool.getClassInfo(innerClasses_attribute.classes[i2].outer_class_info_index).getName().equals(classFile.getName())) {
                            String name2 = classFile.constant_pool.getClassInfo(innerClasses_attribute.classes[i2].inner_class_info_index).getName();
                            classWriter.println("// inner class " + name2.replaceAll("[/$]", Constants.NAME_SEPARATOR));
                            classWriter.println();
                            i = writeClass(classWriter, name2);
                            if (i != 0) {
                                return i;
                            }
                        }
                    } catch (ConstantPoolException e) {
                        reportError("err.bad.innerclasses.attribute", str);
                        return 1;
                    }
                }
                return i;
            }
            if (attribute != null) {
                reportError("err.bad.innerclasses.attribute", str);
                return 1;
            }
            return 0;
        }
        return 0;
    }

    protected JavaFileObject open(String str) throws IOException {
        JavaFileObject javaFileObject;
        JavaFileObject classFileObject;
        JavaFileObject classFileObject2 = getClassFileObject(str);
        if (classFileObject2 != null) {
            return classFileObject2;
        }
        String str2 = str;
        do {
            int lastIndexOf = str2.lastIndexOf(Constants.NAME_SEPARATOR);
            if (lastIndexOf != -1) {
                str2 = str2.substring(0, lastIndexOf) + "$" + str2.substring(lastIndexOf + 1);
                classFileObject = getClassFileObject(str2);
            } else {
                if (!str.endsWith(".class")) {
                    return null;
                }
                if ((this.fileManager instanceof StandardJavaFileManager) && (javaFileObject = (JavaFileObject) this.fileManager.getJavaFileObjects(new String[]{str}).iterator().next()) != null && javaFileObject.getLastModified() != 0) {
                    return javaFileObject;
                }
                if (str.matches("^[A-Za-z]+:.*")) {
                    try {
                        final URI uri = new URI(str);
                        final URL url = uri.toURL();
                        final URLConnection openConnection = url.openConnection();
                        return new JavaFileObject() { // from class: com.sun.tools.javap.JavapTask.20
                            public JavaFileObject.Kind getKind() {
                                return JavaFileObject.Kind.CLASS;
                            }

                            public boolean isNameCompatible(String str3, JavaFileObject.Kind kind) {
                                throw new UnsupportedOperationException();
                            }

                            public NestingKind getNestingKind() {
                                throw new UnsupportedOperationException();
                            }

                            public Modifier getAccessLevel() {
                                throw new UnsupportedOperationException();
                            }

                            public URI toUri() {
                                return uri;
                            }

                            public String getName() {
                                return url.toString();
                            }

                            public InputStream openInputStream() throws IOException {
                                return openConnection.getInputStream();
                            }

                            public OutputStream openOutputStream() throws IOException {
                                throw new UnsupportedOperationException();
                            }

                            public Reader openReader(boolean z) throws IOException {
                                throw new UnsupportedOperationException();
                            }

                            public CharSequence getCharContent(boolean z) throws IOException {
                                throw new UnsupportedOperationException();
                            }

                            public Writer openWriter() throws IOException {
                                throw new UnsupportedOperationException();
                            }

                            public long getLastModified() {
                                return openConnection.getLastModified();
                            }

                            public boolean delete() {
                                throw new UnsupportedOperationException();
                            }
                        };
                    } catch (IOException e) {
                        return null;
                    } catch (URISyntaxException e2) {
                        return null;
                    }
                }
                return null;
            }
        } while (classFileObject == null);
        return classFileObject;
    }

    /* loaded from: target.jar:com/sun/tools/javap/JavapTask$ClassFileInfo.class */
    public static class ClassFileInfo {
        public final JavaFileObject fo;
        public final ClassFile cf;
        public final byte[] digest;
        public final int size;

        ClassFileInfo(JavaFileObject javaFileObject, ClassFile classFile, byte[] bArr, int i) {
            this.fo = javaFileObject;
            this.cf = classFile;
            this.digest = bArr;
            this.size = i;
        }
    }

    public ClassFileInfo read(JavaFileObject javaFileObject) throws IOException, ConstantPoolException {
        InputStream openInputStream = javaFileObject.openInputStream();
        try {
            SizeInputStream sizeInputStream = null;
            MessageDigest messageDigest = null;
            if (this.options.sysInfo || this.options.verbose) {
                try {
                    messageDigest = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException e) {
                }
                SizeInputStream sizeInputStream2 = new SizeInputStream(new DigestInputStream(openInputStream, messageDigest));
                sizeInputStream = sizeInputStream2;
                openInputStream = sizeInputStream2;
            }
            ClassFileInfo classFileInfo = new ClassFileInfo(javaFileObject, ClassFile.read(openInputStream, this.attributeFactory), messageDigest == null ? null : messageDigest.digest(), sizeInputStream == null ? -1 : sizeInputStream.size());
            openInputStream.close();
            return classFileInfo;
        } catch (Throwable th) {
            openInputStream.close();
            throw th;
        }
    }

    public void write(ClassFileInfo classFileInfo) {
        ClassWriter instance = ClassWriter.instance(this.context);
        if (this.options.sysInfo || this.options.verbose) {
            instance.setFile(classFileInfo.fo.toUri());
            instance.setLastModified(classFileInfo.fo.getLastModified());
            instance.setDigest("MD5", classFileInfo.digest);
            instance.setFileSize(classFileInfo.size);
        }
        instance.write(classFileInfo.cf);
    }

    protected void setClassFile(ClassFile classFile) {
        ClassWriter.instance(this.context).setClassFile(classFile);
    }

    protected void setMethod(Method method) {
        ClassWriter.instance(this.context).setMethod(method);
    }

    protected void write(Attribute attribute) {
        AttributeWriter instance = AttributeWriter.instance(this.context);
        ClassFile classFile = ClassWriter.instance(this.context).getClassFile();
        instance.write(classFile, attribute, classFile.constant_pool);
    }

    protected void write(Attributes attributes) {
        AttributeWriter instance = AttributeWriter.instance(this.context);
        ClassFile classFile = ClassWriter.instance(this.context).getClassFile();
        instance.write(classFile, attributes, classFile.constant_pool);
    }

    protected void write(ConstantPool constantPool) {
        ConstantWriter.instance(this.context).writeConstantPool(constantPool);
    }

    protected void write(ConstantPool constantPool, int i) {
        ConstantWriter.instance(this.context).write(i);
    }

    protected void write(ConstantPool.CPInfo cPInfo) {
        ConstantWriter.instance(this.context).println(cPInfo);
    }

    protected void write(Field field) {
        ClassWriter.instance(this.context).writeField(field);
    }

    protected void write(Method method) {
        ClassWriter.instance(this.context).writeMethod(method);
    }

    private JavaFileManager getDefaultFileManager(DiagnosticListener<? super JavaFileObject> diagnosticListener, PrintWriter printWriter) {
        if (this.defaultFileManager == null) {
            this.defaultFileManager = JavapFileManager.create(diagnosticListener, printWriter);
        }
        return this.defaultFileManager;
    }

    private JavaFileObject getClassFileObject(String str) throws IOException {
        JavaFileObject javaFileForInput = this.fileManager.getJavaFileForInput(StandardLocation.PLATFORM_CLASS_PATH, str, JavaFileObject.Kind.CLASS);
        if (javaFileForInput == null) {
            javaFileForInput = this.fileManager.getJavaFileForInput(StandardLocation.CLASS_PATH, str, JavaFileObject.Kind.CLASS);
        }
        return javaFileForInput;
    }

    private void showHelp() {
        printLines(getMessage("main.usage", progname));
        for (Option option : recognizedOptions) {
            String substring = option.aliases[0].substring(1);
            if (!substring.startsWith("X") && !substring.equals("fullversion") && !substring.equals(OperatorName.CLOSE_PATH) && !substring.equals("verify")) {
                printLines(getMessage("main.opt." + substring, new Object[0]));
            }
        }
        for (String str : new String[]{"-classpath", "-cp", "-bootclasspath"}) {
            if (this.fileManager.isSupportedOption(str) != -1) {
                printLines(getMessage("main.opt." + str.substring(1), new Object[0]));
            }
        }
    }

    private void showVersion(boolean z) {
        printLines(version(z ? "full" : "release"));
    }

    private void printLines(String str) {
        this.log.println(str.replace("\n", nl));
    }

    private String version(String str) {
        if (versionRB == null) {
            try {
                versionRB = ResourceBundle.getBundle(versionRBName);
            } catch (MissingResourceException e) {
                return getMessage("version.resource.missing", System.getProperty("java.version"));
            }
        }
        try {
            return versionRB.getString(str);
        } catch (MissingResourceException e2) {
            return getMessage("version.unknown", System.getProperty("java.version"));
        }
    }

    private void reportError(String str, Object... objArr) {
        this.diagnosticListener.report(createDiagnostic(Diagnostic.Kind.ERROR, str, objArr));
    }

    private void reportNote(String str, Object... objArr) {
        this.diagnosticListener.report(createDiagnostic(Diagnostic.Kind.NOTE, str, objArr));
    }

    private void reportWarning(String str, Object... objArr) {
        this.diagnosticListener.report(createDiagnostic(Diagnostic.Kind.WARNING, str, objArr));
    }

    private Diagnostic<JavaFileObject> createDiagnostic(final Diagnostic.Kind kind, final String str, final Object... objArr) {
        return new Diagnostic<JavaFileObject>() { // from class: com.sun.tools.javap.JavapTask.21
            public Diagnostic.Kind getKind() {
                return kind;
            }

            /* renamed from: getSource, reason: merged with bridge method [inline-methods] */
            public JavaFileObject m658getSource() {
                return null;
            }

            public long getPosition() {
                return -1L;
            }

            public long getStartPosition() {
                return -1L;
            }

            public long getEndPosition() {
                return -1L;
            }

            public long getLineNumber() {
                return -1L;
            }

            public long getColumnNumber() {
                return -1L;
            }

            public String getCode() {
                return str;
            }

            public String getMessage(Locale locale) {
                return JavapTask.this.getMessage(locale, str, objArr);
            }

            public String toString() {
                return getClass().getName() + "[key=" + str + ",args=" + Arrays.asList(objArr) + "]";
            }
        };
    }

    @Override // com.sun.tools.javap.Messages
    public String getMessage(String str, Object... objArr) {
        return getMessage(this.task_locale, str, objArr);
    }

    @Override // com.sun.tools.javap.Messages
    public String getMessage(Locale locale, String str, Object... objArr) {
        if (this.bundles == null) {
            this.bundles = new HashMap();
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        ResourceBundle resourceBundle = this.bundles.get(locale);
        if (resourceBundle == null) {
            try {
                resourceBundle = ResourceBundle.getBundle("com.sun.tools.javap.resources.javap", locale);
                this.bundles.put(locale, resourceBundle);
            } catch (MissingResourceException e) {
                throw new InternalError("Cannot find javap resource bundle for locale " + locale);
            }
        }
        try {
            return MessageFormat.format(resourceBundle.getString(str), objArr);
        } catch (MissingResourceException e2) {
            throw new InternalError(e2, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/javap/JavapTask$SizeInputStream.class */
    public static class SizeInputStream extends FilterInputStream {
        private int size;

        SizeInputStream(InputStream inputStream) {
            super(inputStream);
        }

        int size() {
            return this.size;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read > 0) {
                this.size += read;
            }
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int read = super.read();
            this.size++;
            return read;
        }
    }
}
