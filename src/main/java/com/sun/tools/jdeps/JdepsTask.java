package com.sun.tools.jdeps;

import com.sun.tools.classfile.AccessFlags;
import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Dependencies;
import com.sun.tools.classfile.Dependency;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.jdeps.Analyzer;
import com.sun.tools.jdeps.PlatformClassPath;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.tools.java.RuntimeConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:com/sun/tools/jdeps/JdepsTask.class */
public class JdepsTask {
    static Option[] recognizedOptions = {new Option(false, "-h", "-?", "-help") { // from class: com.sun.tools.jdeps.JdepsTask.1
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.help = true;
        }
    }, new Option(true, "-dotoutput") { // from class: com.sun.tools.jdeps.JdepsTask.2
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) throws BadArgs {
            Path path = Paths.get(str2, new String[0]);
            if (!Files.exists(path, new LinkOption[0]) || (Files.isDirectory(path, new LinkOption[0]) && Files.isWritable(path))) {
                jdepsTask.options.dotOutputDir = str2;
                return;
            }
            throw new BadArgs("err.invalid.path", str2);
        }
    }, new Option(false, "-s", "-summary") { // from class: com.sun.tools.jdeps.JdepsTask.3
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.showSummary = true;
            jdepsTask.options.verbose = Analyzer.Type.SUMMARY;
        }
    }, new Option(false, "-v", "-verbose", "-verbose:package", "-verbose:class") { // from class: com.sun.tools.jdeps.JdepsTask.4
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) throws BadArgs {
            boolean z = -1;
            switch (str.hashCode()) {
                case -1162609037:
                    if (str.equals("-verbose:class")) {
                        z = 3;
                        break;
                    }
                    break;
                case 1513:
                    if (str.equals("-v")) {
                        z = false;
                        break;
                    }
                    break;
                case 1468161205:
                    if (str.equals("-verbose")) {
                        z = true;
                        break;
                    }
                    break;
                case 2058499713:
                    if (str.equals("-verbose:package")) {
                        z = 2;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                case true:
                    jdepsTask.options.verbose = Analyzer.Type.VERBOSE;
                    jdepsTask.options.filterSameArchive = false;
                    jdepsTask.options.filterSamePackage = false;
                    return;
                case true:
                    jdepsTask.options.verbose = Analyzer.Type.PACKAGE;
                    return;
                case true:
                    jdepsTask.options.verbose = Analyzer.Type.CLASS;
                    return;
                default:
                    throw new BadArgs("err.invalid.arg.for.option", str);
            }
        }
    }, new Option(true, "-cp", "-classpath") { // from class: com.sun.tools.jdeps.JdepsTask.5
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.classpath = str2;
        }
    }, new Option(true, "-p", "-package") { // from class: com.sun.tools.jdeps.JdepsTask.6
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.packageNames.add(str2);
        }
    }, new Option(true, "-e", "-regex") { // from class: com.sun.tools.jdeps.JdepsTask.7
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.regex = str2;
        }
    }, new Option(true, "-f", "-filter") { // from class: com.sun.tools.jdeps.JdepsTask.8
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.filterRegex = str2;
        }
    }, new Option(false, "-filter:package", "-filter:archive", "-filter:none") { // from class: com.sun.tools.jdeps.JdepsTask.9
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            boolean z = -1;
            switch (str.hashCode()) {
                case 345825201:
                    if (str.equals("-filter:package")) {
                        z = false;
                        break;
                    }
                    break;
                case 404786221:
                    if (str.equals("-filter:archive")) {
                        z = true;
                        break;
                    }
                    break;
                case 589476781:
                    if (str.equals("-filter:none")) {
                        z = 2;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    jdepsTask.options.filterSamePackage = true;
                    jdepsTask.options.filterSameArchive = false;
                    return;
                case true:
                    jdepsTask.options.filterSameArchive = true;
                    jdepsTask.options.filterSamePackage = false;
                    return;
                case true:
                    jdepsTask.options.filterSameArchive = false;
                    jdepsTask.options.filterSamePackage = false;
                    return;
                default:
                    return;
            }
        }
    }, new Option(true, "-include") { // from class: com.sun.tools.jdeps.JdepsTask.10
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) throws BadArgs {
            jdepsTask.options.includePattern = Pattern.compile(str2);
        }
    }, new Option(false, "-P", "-profile") { // from class: com.sun.tools.jdeps.JdepsTask.11
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) throws BadArgs {
            jdepsTask.options.showProfile = true;
            if (Profile.getProfileCount() == 0) {
                throw new BadArgs("err.option.unsupported", str, JdepsTask.getMessage("err.profiles.msg", new Object[0]));
            }
        }
    }, new Option(false, "-apionly") { // from class: com.sun.tools.jdeps.JdepsTask.12
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.apiOnly = true;
        }
    }, new Option(false, "-R", "-recursive") { // from class: com.sun.tools.jdeps.JdepsTask.13
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.depth = 0;
            jdepsTask.options.filterSameArchive = false;
            jdepsTask.options.filterSamePackage = false;
        }
    }, new Option(false, "-jdkinternals") { // from class: com.sun.tools.jdeps.JdepsTask.14
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.findJDKInternals = true;
            jdepsTask.options.verbose = Analyzer.Type.CLASS;
            if (jdepsTask.options.includePattern == null) {
                jdepsTask.options.includePattern = Pattern.compile(".*");
            }
        }
    }, new Option(false, "-version") { // from class: com.sun.tools.jdeps.JdepsTask.15
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.version = true;
        }
    }, new HiddenOption(false, "-fullversion") { // from class: com.sun.tools.jdeps.JdepsTask.16
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.fullVersion = true;
        }
    }, new HiddenOption(false, "-showlabel") { // from class: com.sun.tools.jdeps.JdepsTask.17
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.showLabel = true;
        }
    }, new HiddenOption(false, "-q", "-quiet") { // from class: com.sun.tools.jdeps.JdepsTask.18
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) {
            jdepsTask.options.nowarning = true;
        }
    }, new HiddenOption(true, "-depth") { // from class: com.sun.tools.jdeps.JdepsTask.19
        @Override // com.sun.tools.jdeps.JdepsTask.Option
        void process(JdepsTask jdepsTask, String str, String str2) throws BadArgs {
            try {
                jdepsTask.options.depth = Integer.parseInt(str2);
            } catch (NumberFormatException e) {
                throw new BadArgs("err.invalid.arg.for.option", str);
            }
        }
    }};
    private static final String PROGNAME = "jdeps";
    private PrintWriter log;
    static final int EXIT_OK = 0;
    static final int EXIT_ERROR = 1;
    static final int EXIT_CMDERR = 2;
    static final int EXIT_SYSERR = 3;
    static final int EXIT_ABNORMAL = 4;
    private final Options options = new Options();
    private final List<String> classes = new ArrayList();
    private final List<Archive> sourceLocations = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/jdeps/JdepsTask$BadArgs.class */
    public static class BadArgs extends Exception {
        static final long serialVersionUID = 8765093759964640721L;
        final String key;
        final Object[] args;
        boolean showUsage;

        BadArgs(String str, Object... objArr) {
            super(JdepsTask.getMessage(str, objArr));
            this.key = str;
            this.args = objArr;
        }

        BadArgs showUsage(boolean z) {
            this.showUsage = z;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/jdeps/JdepsTask$Option.class */
    public static abstract class Option {
        final boolean hasArg;
        final String[] aliases;

        abstract void process(JdepsTask jdepsTask, String str, String str2) throws BadArgs;

        Option(boolean z, String... strArr) {
            this.hasArg = z;
            this.aliases = strArr;
        }

        boolean isHidden() {
            return false;
        }

        boolean matches(String str) {
            for (String str2 : this.aliases) {
                if (str2.equals(str)) {
                    return true;
                }
                if (this.hasArg && str.startsWith(str2 + "=")) {
                    return true;
                }
            }
            return false;
        }

        boolean ignoreRest() {
            return false;
        }
    }

    /* loaded from: target.jar:com/sun/tools/jdeps/JdepsTask$HiddenOption.class */
    static abstract class HiddenOption extends Option {
        HiddenOption(boolean z, String... strArr) {
            super(z, strArr);
        }

        @Override // com.sun.tools.jdeps.JdepsTask.Option
        boolean isHidden() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLog(PrintWriter printWriter) {
        this.log = printWriter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int run(String[] strArr) {
        if (this.log == null) {
            this.log = new PrintWriter(System.out);
        }
        try {
            try {
                handleOptions(strArr);
                if (this.options.help) {
                    showHelp();
                }
                if (this.options.version || this.options.fullVersion) {
                    showVersion(this.options.fullVersion);
                }
                if (this.classes.isEmpty() && this.options.includePattern == null) {
                    if (this.options.help || this.options.version || this.options.fullVersion) {
                        this.log.flush();
                        return 0;
                    }
                    showHelp();
                    this.log.flush();
                    return 2;
                }
                if (this.options.regex != null && this.options.packageNames.size() > 0) {
                    showHelp();
                    this.log.flush();
                    return 2;
                }
                if (this.options.findJDKInternals && (this.options.regex != null || this.options.packageNames.size() > 0 || this.options.showSummary)) {
                    showHelp();
                    this.log.flush();
                    return 2;
                }
                if (!this.options.showSummary || this.options.verbose == Analyzer.Type.SUMMARY) {
                    int i = run() ? 0 : 1;
                    this.log.flush();
                    return i;
                }
                showHelp();
                this.log.flush();
                return 2;
            } catch (BadArgs e) {
                reportError(e.key, e.args);
                if (e.showUsage) {
                    this.log.println(getMessage("main.usage.summary", PROGNAME));
                }
                this.log.flush();
                return 2;
            } catch (IOException e2) {
                this.log.flush();
                return 4;
            }
        } catch (Throwable th) {
            this.log.flush();
            throw th;
        }
    }

    private boolean run() throws IOException {
        findDependencies();
        Analyzer analyzer = new Analyzer(this.options.verbose, new Analyzer.Filter() { // from class: com.sun.tools.jdeps.JdepsTask.20
            @Override // com.sun.tools.jdeps.Analyzer.Filter
            public boolean accepts(Dependency.Location location, Archive archive, Dependency.Location location2, Archive archive2) {
                return JdepsTask.this.options.findJDKInternals ? JdepsTask.this.isJDKArchive(archive2) && !((PlatformClassPath.JDKArchive) archive2).isExported(location2.getClassName()) : (JdepsTask.this.options.filterSameArchive && archive == archive2) ? false : true;
            }
        });
        analyzer.run(this.sourceLocations);
        if (this.options.dotOutputDir != null) {
            Path path = Paths.get(this.options.dotOutputDir, new String[0]);
            Files.createDirectories(path, new FileAttribute[0]);
            generateDotFiles(path, analyzer);
        } else {
            printRawOutput(this.log, analyzer);
        }
        if (this.options.findJDKInternals && !this.options.nowarning) {
            showReplacements(analyzer);
            return true;
        }
        return true;
    }

    private void generateSummaryDotFile(Path path, Analyzer analyzer) throws IOException {
        Analyzer.Type type = (this.options.verbose == Analyzer.Type.PACKAGE || this.options.verbose == Analyzer.Type.SUMMARY) ? Analyzer.Type.SUMMARY : Analyzer.Type.PACKAGE;
        PrintWriter printWriter = new PrintWriter(Files.newOutputStream(path.resolve("summary.dot"), new OpenOption[0]));
        Throwable th = null;
        try {
            SummaryDotFile summaryDotFile = new SummaryDotFile(printWriter, type);
            Throwable th2 = null;
            try {
                try {
                    for (Archive archive : this.sourceLocations) {
                        if (!archive.isEmpty()) {
                            if ((this.options.verbose == Analyzer.Type.PACKAGE || this.options.verbose == Analyzer.Type.SUMMARY) && this.options.showLabel) {
                                analyzer.visitDependences(archive, summaryDotFile.labelBuilder(), Analyzer.Type.PACKAGE);
                            }
                            analyzer.visitDependences(archive, summaryDotFile, type);
                        }
                    }
                    if (summaryDotFile != null) {
                        if (0 != 0) {
                            try {
                                summaryDotFile.close();
                            } catch (Throwable th3) {
                                th2.addSuppressed(th3);
                            }
                        } else {
                            summaryDotFile.close();
                        }
                    }
                    if (printWriter != null) {
                        if (0 == 0) {
                            printWriter.close();
                            return;
                        }
                        try {
                            printWriter.close();
                        } catch (Throwable th4) {
                            th.addSuppressed(th4);
                        }
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    throw th5;
                }
            } catch (Throwable th6) {
                if (summaryDotFile != null) {
                    if (th2 != null) {
                        try {
                            summaryDotFile.close();
                        } catch (Throwable th7) {
                            th2.addSuppressed(th7);
                        }
                    } else {
                        summaryDotFile.close();
                    }
                }
                throw th6;
            }
        } catch (Throwable th8) {
            if (printWriter != null) {
                if (0 != 0) {
                    try {
                        printWriter.close();
                    } catch (Throwable th9) {
                        th.addSuppressed(th9);
                    }
                } else {
                    printWriter.close();
                }
            }
            throw th8;
        }
    }

    private void generateDotFiles(Path path, Analyzer analyzer) throws IOException {
        if (this.options.verbose != Analyzer.Type.SUMMARY) {
            for (Archive archive : this.sourceLocations) {
                if (analyzer.hasDependences(archive)) {
                    PrintWriter printWriter = new PrintWriter(Files.newOutputStream(path.resolve(archive.getName() + ".dot"), new OpenOption[0]));
                    Throwable th = null;
                    try {
                        try {
                            DotFileFormatter dotFileFormatter = new DotFileFormatter(printWriter, archive);
                            Throwable th2 = null;
                            try {
                                try {
                                    analyzer.visitDependences(archive, dotFileFormatter);
                                    if (dotFileFormatter != null) {
                                        if (0 != 0) {
                                            try {
                                                dotFileFormatter.close();
                                            } catch (Throwable th3) {
                                                th2.addSuppressed(th3);
                                            }
                                        } else {
                                            dotFileFormatter.close();
                                        }
                                    }
                                    if (printWriter != null) {
                                        if (0 != 0) {
                                            try {
                                                printWriter.close();
                                            } catch (Throwable th4) {
                                                th.addSuppressed(th4);
                                            }
                                        } else {
                                            printWriter.close();
                                        }
                                    }
                                } finally {
                                }
                            } finally {
                            }
                        } finally {
                        }
                    } catch (Throwable th5) {
                        if (printWriter != null) {
                            if (th != null) {
                                try {
                                    printWriter.close();
                                } catch (Throwable th6) {
                                    th.addSuppressed(th6);
                                }
                            } else {
                                printWriter.close();
                            }
                        }
                        throw th5;
                    }
                }
            }
        }
        generateSummaryDotFile(path, analyzer);
    }

    private void printRawOutput(PrintWriter printWriter, Analyzer analyzer) {
        RawOutputFormatter rawOutputFormatter = new RawOutputFormatter(printWriter);
        RawSummaryFormatter rawSummaryFormatter = new RawSummaryFormatter(printWriter);
        for (Archive archive : this.sourceLocations) {
            if (!archive.isEmpty()) {
                analyzer.visitDependences(archive, rawSummaryFormatter, Analyzer.Type.SUMMARY);
                if (analyzer.hasDependences(archive) && this.options.verbose != Analyzer.Type.SUMMARY) {
                    analyzer.visitDependences(archive, rawOutputFormatter);
                }
            }
        }
    }

    private boolean isValidClassName(String str) {
        if (!Character.isJavaIdentifierStart(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != '.' && !Character.isJavaIdentifierPart(charAt)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/jdeps/JdepsTask$DependencyFilter.class */
    public class DependencyFilter implements Dependency.Filter {
        final Dependency.Filter filter;
        final Pattern filterPattern;

        DependencyFilter() {
            if (JdepsTask.this.options.regex == null) {
                if (JdepsTask.this.options.packageNames.size() > 0) {
                    this.filter = Dependencies.getPackageFilter(JdepsTask.this.options.packageNames, false);
                } else {
                    this.filter = null;
                }
            } else {
                this.filter = Dependencies.getRegexFilter(Pattern.compile(JdepsTask.this.options.regex));
            }
            this.filterPattern = JdepsTask.this.options.filterRegex != null ? Pattern.compile(JdepsTask.this.options.filterRegex) : null;
        }

        @Override // com.sun.tools.classfile.Dependency.Filter
        public boolean accepts(Dependency dependency) {
            if (dependency.getOrigin().equals(dependency.getTarget())) {
                return false;
            }
            String packageName = dependency.getTarget().getPackageName();
            if (JdepsTask.this.options.filterSamePackage && dependency.getOrigin().getPackageName().equals(packageName)) {
                return false;
            }
            if (this.filterPattern != null && this.filterPattern.matcher(packageName).matches()) {
                return false;
            }
            if (this.filter != null) {
                return this.filter.accepts(dependency);
            }
            return true;
        }
    }

    private boolean matches(String str, AccessFlags accessFlags) {
        if (this.options.apiOnly && !accessFlags.is(1)) {
            return false;
        }
        if (this.options.includePattern != null) {
            return this.options.includePattern.matcher(str.replace('/', '.')).matches();
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:111:0x0313, code lost:
    
        r0 = r19.getName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0332, code lost:
    
        if (r0.contains(r0) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0335, code lost:
    
        r0.add(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0345, code lost:
    
        if (isJDKArchive(r0) == false) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0348, code lost:
    
        ((com.sun.tools.jdeps.PlatformClassPath.JDKArchive) r0).processJdkExported(r19);
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0352, code lost:
    
        r0 = r8.findDependencies(r19).iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0368, code lost:
    
        if (r0.hasNext() == false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x036b, code lost:
    
        r0 = r0.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0379, code lost:
    
        if (r17 != 0) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0393, code lost:
    
        if (r0.accepts(r0) == false) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x03dc, code lost:
    
        r0.addClass(r0.getOrigin());
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0396, code lost:
    
        r0.addClass(r0.getOrigin(), r0.getTarget());
        r0 = r0.getTarget().getName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x03c0, code lost:
    
        if (r0.contains(r0) != false) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x03cc, code lost:
    
        if (r14.contains(r0) != false) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x03cf, code lost:
    
        r14.add(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x037c, code lost:
    
        r0.addClass(r0.getOrigin());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void findDependencies() throws IOException {
        Dependency.Finder classDependencyFinder;
        if (this.options.apiOnly) {
            classDependencyFinder = Dependencies.getAPIFinder(4);
        } else {
            classDependencyFinder = Dependencies.getClassDependencyFinder();
        }
        Dependency.Finder finder = classDependencyFinder;
        DependencyFilter dependencyFilter = new DependencyFilter();
        ArrayList<Archive> arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList2 = new ArrayList();
        for (String str : this.classes) {
            Path path = Paths.get(str, new String[0]);
            if (Files.exists(path, new LinkOption[0])) {
                arrayList2.add(path);
                arrayList.add(Archive.getInstance(path));
            } else if (isValidClassName(str)) {
                linkedList.add(str);
            } else {
                warning("warn.invalid.arg", str);
            }
        }
        this.sourceLocations.addAll(arrayList);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(getClassPathArchives(this.options.classpath, arrayList2));
        if (this.options.includePattern != null) {
            arrayList.addAll(arrayList3);
        }
        arrayList3.addAll(PlatformClassPath.getArchives());
        this.sourceLocations.addAll(arrayList3);
        for (Archive archive : this.sourceLocations) {
            if (archive.reader().isMultiReleaseJar()) {
                warning("warn.mrjar.usejdk9", archive.getPathName());
            }
        }
        LinkedList linkedList2 = new LinkedList();
        HashSet hashSet = new HashSet();
        for (Archive archive2 : arrayList) {
            for (ClassFile classFile : archive2.reader().getClassFiles()) {
                try {
                    String name = classFile.getName();
                    if (matches(name, classFile.access_flags)) {
                        if (!hashSet.contains(name)) {
                            hashSet.add(name);
                        }
                        for (Dependency dependency : finder.findDependencies(classFile)) {
                            if (dependencyFilter.accepts(dependency)) {
                                String name2 = dependency.getTarget().getName();
                                if (!hashSet.contains(name2) && !linkedList2.contains(name2)) {
                                    linkedList2.add(name2);
                                }
                                archive2.addClass(dependency.getOrigin(), dependency.getTarget());
                            } else {
                                archive2.addClass(dependency.getOrigin());
                            }
                        }
                        Iterator<String> it = archive2.reader().skippedEntries().iterator();
                        while (it.hasNext()) {
                            warning("warn.skipped.entry", it.next(), archive2.getPathName());
                        }
                    }
                } catch (ConstantPoolException e) {
                    throw new Dependencies.ClassFileError(e);
                }
            }
        }
        LinkedList linkedList3 = linkedList;
        int i = this.options.depth > 0 ? this.options.depth : Integer.MAX_VALUE;
        while (true) {
            String str2 = (String) linkedList3.poll();
            if (str2 != null) {
                if (hashSet.contains(str2)) {
                    continue;
                } else {
                    ClassFile classFile2 = null;
                    Iterator it2 = arrayList3.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        Archive archive3 = (Archive) it2.next();
                        classFile2 = archive3.reader().getClassFile(str2);
                        if (classFile2 != null) {
                            try {
                                break;
                            } catch (ConstantPoolException e2) {
                                throw new Dependencies.ClassFileError(e2);
                            }
                        }
                    }
                    if (classFile2 == null) {
                        hashSet.add(str2);
                    }
                }
            } else {
                linkedList3 = linkedList2;
                linkedList2 = new LinkedList();
                if (linkedList3.isEmpty()) {
                    return;
                }
                int i2 = i;
                i--;
                if (i2 <= 0) {
                    return;
                }
            }
        }
    }

    public void handleOptions(String[] strArr) throws BadArgs {
        int i = 0;
        while (i < strArr.length) {
            if (strArr[i].charAt(0) == '-') {
                String str = strArr[i];
                Option option = getOption(str);
                String str2 = null;
                if (option.hasArg) {
                    if (str.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR) && str.indexOf(61) > 0) {
                        str2 = str.substring(str.indexOf(61) + 1, str.length());
                    } else if (i + 1 < strArr.length) {
                        i++;
                        str2 = strArr[i];
                    }
                    if (str2 == null || str2.isEmpty() || str2.charAt(0) == '-') {
                        throw new BadArgs("err.missing.arg", str).showUsage(true);
                    }
                }
                option.process(this, str, str2);
                if (option.ignoreRest()) {
                    i = strArr.length;
                }
            } else {
                while (i < strArr.length) {
                    String str3 = strArr[i];
                    if (str3.charAt(0) == '-') {
                        throw new BadArgs("err.option.after.class", str3).showUsage(true);
                    }
                    this.classes.add(str3);
                    i++;
                }
            }
            i++;
        }
    }

    private Option getOption(String str) throws BadArgs {
        for (Option option : recognizedOptions) {
            if (option.matches(str)) {
                return option;
            }
        }
        throw new BadArgs("err.unknown.option", str).showUsage(true);
    }

    private void reportError(String str, Object... objArr) {
        this.log.println(getMessage("error.prefix", new Object[0]) + " " + getMessage(str, objArr));
    }

    private void warning(String str, Object... objArr) {
        this.log.println(getMessage("warn.prefix", new Object[0]) + " " + getMessage(str, objArr));
    }

    private void showHelp() {
        this.log.println(getMessage("main.usage", PROGNAME));
        for (Option option : recognizedOptions) {
            String substring = option.aliases[0].substring(1);
            String substring2 = substring.charAt(0) == '-' ? substring.substring(1) : substring;
            if (!option.isHidden() && !substring2.equals(OperatorName.CLOSE_PATH) && !substring2.startsWith("filter:")) {
                this.log.println(getMessage("main.opt." + substring2, new Object[0]));
            }
        }
    }

    private void showVersion(boolean z) {
        this.log.println(version(z ? "full" : "release"));
    }

    private String version(String str) {
        if (ResourceBundleHelper.versionRB == null) {
            return System.getProperty("java.version");
        }
        try {
            return ResourceBundleHelper.versionRB.getString(str);
        } catch (MissingResourceException e) {
            return getMessage("version.unknown", System.getProperty("java.version"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getMessage(String str, Object... objArr) {
        try {
            return MessageFormat.format(ResourceBundleHelper.bundle.getString(str), objArr);
        } catch (MissingResourceException e) {
            throw new InternalError("Missing message: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/jdeps/JdepsTask$Options.class */
    public static class Options {
        boolean help;
        boolean version;
        boolean fullVersion;
        boolean showProfile;
        boolean showSummary;
        boolean apiOnly;
        boolean showLabel;
        boolean findJDKInternals;
        boolean nowarning;
        Analyzer.Type verbose;
        boolean filterSamePackage;
        boolean filterSameArchive;
        String filterRegex;
        String dotOutputDir;
        String classpath;
        int depth;
        Set<String> packageNames;
        String regex;
        Pattern includePattern;

        private Options() {
            this.verbose = Analyzer.Type.PACKAGE;
            this.filterSamePackage = true;
            this.filterSameArchive = false;
            this.classpath = "";
            this.depth = 1;
            this.packageNames = new HashSet();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/jdeps/JdepsTask$ResourceBundleHelper.class */
    public static class ResourceBundleHelper {
        static final ResourceBundle versionRB;
        static final ResourceBundle bundle;
        static final ResourceBundle jdkinternals;

        private ResourceBundleHelper() {
        }

        static {
            Locale locale = Locale.getDefault();
            try {
                bundle = ResourceBundle.getBundle("com.sun.tools.jdeps.resources.jdeps", locale);
                try {
                    versionRB = ResourceBundle.getBundle("com.sun.tools.jdeps.resources.version");
                    try {
                        jdkinternals = ResourceBundle.getBundle("com.sun.tools.jdeps.resources.jdkinternals");
                    } catch (MissingResourceException e) {
                        throw new InternalError("Cannot find jdkinternals resource bundle");
                    }
                } catch (MissingResourceException e2) {
                    throw new InternalError("version.resource.missing");
                }
            } catch (MissingResourceException e3) {
                throw new InternalError("Cannot find jdeps resource bundle for locale " + locale);
            }
        }
    }

    private List<Archive> getClassPathArchives(String str, List<Path> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (str.isEmpty()) {
            return arrayList;
        }
        ArrayList<Path> arrayList2 = new ArrayList();
        for (String str2 : str.split(File.pathSeparator)) {
            if (str2.length() > 0) {
                int lastIndexOf = str2.lastIndexOf(".*");
                if (lastIndexOf > 0) {
                    DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(Paths.get(str2.substring(0, lastIndexOf), new String[0]), "*.jar");
                    Throwable th = null;
                    try {
                        try {
                            Iterator<Path> it = newDirectoryStream.iterator();
                            while (it.hasNext()) {
                                arrayList2.add(it.next());
                            }
                            if (newDirectoryStream != null) {
                                if (0 != 0) {
                                    try {
                                        newDirectoryStream.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                } else {
                                    newDirectoryStream.close();
                                }
                            }
                        } finally {
                        }
                    } catch (Throwable th3) {
                        if (newDirectoryStream != null) {
                            if (th != null) {
                                try {
                                    newDirectoryStream.close();
                                } catch (Throwable th4) {
                                    th.addSuppressed(th4);
                                }
                            } else {
                                newDirectoryStream.close();
                            }
                        }
                        throw th3;
                    }
                } else {
                    arrayList2.add(Paths.get(str2, new String[0]));
                }
            }
        }
        for (Path path : arrayList2) {
            if (Files.exists(path, new LinkOption[0]) && !hasSameFile(list, path)) {
                arrayList.add(Archive.getInstance(path));
            }
        }
        return arrayList;
    }

    private boolean hasSameFile(List<Path> list, Path path) throws IOException {
        Iterator<Path> it = list.iterator();
        while (it.hasNext()) {
            if (Files.isSameFile(it.next(), path)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/jdeps/JdepsTask$RawOutputFormatter.class */
    public class RawOutputFormatter implements Analyzer.Visitor {
        private final PrintWriter writer;
        private String pkg = "";

        RawOutputFormatter(PrintWriter printWriter) {
            this.writer = printWriter;
        }

        @Override // com.sun.tools.jdeps.Analyzer.Visitor
        public void visitDependence(String str, Archive archive, String str2, Archive archive2) {
            String tag = JdepsTask.this.toTag(str2, archive2);
            if (JdepsTask.this.options.verbose == Analyzer.Type.VERBOSE) {
                this.writer.format("   %-50s -> %-50s %s%n", str, str2, tag);
                return;
            }
            if (!str.equals(this.pkg)) {
                this.pkg = str;
                this.writer.format("   %s (%s)%n", str, archive.getName());
            }
            this.writer.format("      -> %-50s %s%n", str2, tag);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/jdeps/JdepsTask$RawSummaryFormatter.class */
    public class RawSummaryFormatter implements Analyzer.Visitor {
        private final PrintWriter writer;

        RawSummaryFormatter(PrintWriter printWriter) {
            this.writer = printWriter;
        }

        @Override // com.sun.tools.jdeps.Analyzer.Visitor
        public void visitDependence(String str, Archive archive, String str2, Archive archive2) {
            this.writer.format("%s -> %s", archive.getName(), archive2.getPathName());
            if (JdepsTask.this.options.showProfile && PlatformClassPath.JDKArchive.isProfileArchive(archive2)) {
                this.writer.format(" (%s)", str2);
            }
            this.writer.format("%n", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/jdeps/JdepsTask$DotFileFormatter.class */
    public class DotFileFormatter implements Analyzer.Visitor, AutoCloseable {
        private final PrintWriter writer;
        private final String name;

        DotFileFormatter(PrintWriter printWriter, Archive archive) {
            this.writer = printWriter;
            this.name = archive.getName();
            printWriter.format("digraph \"%s\" {%n", this.name);
            printWriter.format("    // Path: %s%n", archive.getPathName());
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            this.writer.println("}");
        }

        @Override // com.sun.tools.jdeps.Analyzer.Visitor
        public void visitDependence(String str, Archive archive, String str2, Archive archive2) {
            String tag = JdepsTask.this.toTag(str2, archive2);
            PrintWriter printWriter = this.writer;
            Object[] objArr = new Object[2];
            objArr[0] = String.format("\"%s\"", str);
            objArr[1] = tag.isEmpty() ? str2 : String.format("%s (%s)", str2, tag);
            printWriter.format("   %-50s -> \"%s\";%n", objArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/jdeps/JdepsTask$SummaryDotFile.class */
    public class SummaryDotFile implements Analyzer.Visitor, AutoCloseable {
        private final PrintWriter writer;
        private final Analyzer.Type type;
        private final Map<Archive, Map<Archive, StringBuilder>> edges = new HashMap();

        SummaryDotFile(PrintWriter printWriter, Analyzer.Type type) {
            this.writer = printWriter;
            this.type = type;
            printWriter.format("digraph \"summary\" {%n", new Object[0]);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            this.writer.println("}");
        }

        @Override // com.sun.tools.jdeps.Analyzer.Visitor
        public void visitDependence(String str, Archive archive, String str2, Archive archive2) {
            String name = this.type == Analyzer.Type.PACKAGE ? str2 : archive2.getName();
            if (this.type == Analyzer.Type.PACKAGE) {
                String tag = JdepsTask.this.toTag(str2, archive2, this.type);
                if (!tag.isEmpty()) {
                    name = name + " (" + tag + RuntimeConstants.SIG_ENDMETHOD;
                }
            } else if (JdepsTask.this.options.showProfile && PlatformClassPath.JDKArchive.isProfileArchive(archive2)) {
                name = name + " (" + str2 + RuntimeConstants.SIG_ENDMETHOD;
            }
            this.writer.format("  %-50s -> \"%s\"%s;%n", String.format("\"%s\"", str), name, getLabel(archive, archive2));
        }

        String getLabel(Archive archive, Archive archive2) {
            StringBuilder sb;
            return (this.edges.isEmpty() || (sb = this.edges.get(archive).get(archive2)) == null) ? "" : String.format(" [label=\"%s\",fontsize=9]", sb.toString());
        }

        Analyzer.Visitor labelBuilder() {
            return new Analyzer.Visitor() { // from class: com.sun.tools.jdeps.JdepsTask.SummaryDotFile.1
                @Override // com.sun.tools.jdeps.Analyzer.Visitor
                public void visitDependence(String str, Archive archive, String str2, Archive archive2) {
                    Map map = (Map) SummaryDotFile.this.edges.get(archive);
                    if (!SummaryDotFile.this.edges.containsKey(archive)) {
                        Map map2 = SummaryDotFile.this.edges;
                        HashMap hashMap = new HashMap();
                        map = hashMap;
                        map2.put(archive, hashMap);
                    }
                    StringBuilder sb = (StringBuilder) map.get(archive2);
                    if (sb == null) {
                        StringBuilder sb2 = new StringBuilder();
                        sb = sb2;
                        map.put(archive2, sb2);
                    }
                    addLabel(sb, str, str2, JdepsTask.this.toTag(str2, archive2, Analyzer.Type.PACKAGE));
                }

                void addLabel(StringBuilder sb, String str, String str2, String str3) {
                    sb.append(str).append(" -> ").append(str2);
                    if (!str3.isEmpty()) {
                        sb.append(" (" + str3 + RuntimeConstants.SIG_ENDMETHOD);
                    }
                    sb.append("\\n");
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isJDKArchive(Archive archive) {
        return PlatformClassPath.JDKArchive.class.isInstance(archive);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String toTag(String str, Archive archive, Analyzer.Type type) {
        boolean isExported;
        if (!isJDKArchive(archive)) {
            return archive.getName();
        }
        PlatformClassPath.JDKArchive jDKArchive = (PlatformClassPath.JDKArchive) archive;
        if (type == Analyzer.Type.CLASS || type == Analyzer.Type.VERBOSE) {
            isExported = jDKArchive.isExported(str);
        } else {
            isExported = jDKArchive.isExportedPackage(str);
        }
        Profile profile = getProfile(str, type);
        if (isExported) {
            return (!this.options.showProfile || profile == null) ? "" : profile.profileName();
        }
        return "JDK internal API (" + archive.getName() + RuntimeConstants.SIG_ENDMETHOD;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String toTag(String str, Archive archive) {
        return toTag(str, archive, this.options.verbose);
    }

    private Profile getProfile(String str, Analyzer.Type type) {
        String str2 = str;
        if (type == Analyzer.Type.CLASS || type == Analyzer.Type.VERBOSE) {
            int lastIndexOf = str.lastIndexOf(46);
            str2 = lastIndexOf > 0 ? str.substring(0, lastIndexOf) : "";
        }
        return Profile.getProfile(str2);
    }

    private String replacementFor(String str) {
        String str2 = str;
        String str3 = null;
        while (str3 == null && str2 != null) {
            try {
                str3 = ResourceBundleHelper.jdkinternals.getString(str2);
            } catch (MissingResourceException e) {
                int lastIndexOf = str2.lastIndexOf(46);
                str2 = lastIndexOf > 0 ? str2.substring(0, lastIndexOf) : null;
            }
        }
        return str3;
    }

    private void showReplacements(Analyzer analyzer) {
        TreeMap treeMap = new TreeMap();
        boolean z = false;
        for (Archive archive : this.sourceLocations) {
            z = z || analyzer.hasDependences(archive);
            for (String str : analyzer.dependences(archive)) {
                String replacementFor = replacementFor(str);
                if (replacementFor != null && !treeMap.containsKey(str)) {
                    treeMap.put(str, replacementFor);
                }
            }
        }
        if (z) {
            this.log.println();
            warning("warn.replace.useJDKInternals", getMessage("jdeps.wiki.url", new Object[0]));
        }
        if (!treeMap.isEmpty()) {
            this.log.println();
            this.log.format("%-40s %s%n", "JDK Internal API", "Suggested Replacement");
            this.log.format("%-40s %s%n", "----------------", "---------------------");
            for (Map.Entry entry : treeMap.entrySet()) {
                this.log.format("%-40s %s%n", entry.getKey(), entry.getValue());
            }
        }
    }
}
