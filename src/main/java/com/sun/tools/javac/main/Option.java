package com.sun.tools.javac.main;

import com.formdev.flatlaf.FlatClientProperties;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.doclint.DocLint;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.jvm.Profile;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.StringUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.lang.model.SourceVersion;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/javac/main/Option.class */
public enum Option {
    G("-g", "opt.g", OptionKind.STANDARD, OptionGroup.BASIC),
    G_NONE("-g:none", "opt.g.none", OptionKind.STANDARD, OptionGroup.BASIC) { // from class: com.sun.tools.javac.main.Option.1
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            optionHelper.put("-g:", FlatClientProperties.TABBED_PANE_TAB_ROTATION_NONE);
            return false;
        }
    },
    G_CUSTOM("-g:", "opt.g.lines.vars.source", OptionKind.STANDARD, OptionGroup.BASIC, ChoiceKind.ANYOF, "lines", "vars", "source"),
    XLINT("-Xlint", "opt.Xlint", OptionKind.EXTENDED, OptionGroup.BASIC),
    XLINT_CUSTOM("-Xlint:", "opt.Xlint.suboptlist", OptionKind.EXTENDED, OptionGroup.BASIC, ChoiceKind.ANYOF, getXLintChoices()),
    XDOCLINT("-Xdoclint", "opt.Xdoclint", OptionKind.EXTENDED, OptionGroup.BASIC),
    XDOCLINT_CUSTOM("-Xdoclint:", "opt.Xdoclint.subopts", "opt.Xdoclint.custom", OptionKind.EXTENDED, OptionGroup.BASIC) { // from class: com.sun.tools.javac.main.Option.2
        @Override // com.sun.tools.javac.main.Option
        public boolean matches(String str) {
            return DocLint.isValidOption(str.replace(XDOCLINT_CUSTOM.text, DocLint.XMSGS_CUSTOM_PREFIX));
        }

        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            String str2 = optionHelper.get(XDOCLINT_CUSTOM);
            optionHelper.put(XDOCLINT_CUSTOM.text, str2 == null ? str : str2 + " " + str);
            return false;
        }
    },
    NOWARN("-nowarn", "opt.nowarn", OptionKind.STANDARD, OptionGroup.BASIC) { // from class: com.sun.tools.javac.main.Option.3
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            optionHelper.put("-Xlint:none", str);
            return false;
        }
    },
    VERBOSE("-verbose", "opt.verbose", OptionKind.STANDARD, OptionGroup.BASIC),
    DEPRECATION("-deprecation", "opt.deprecation", OptionKind.STANDARD, OptionGroup.BASIC) { // from class: com.sun.tools.javac.main.Option.4
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            optionHelper.put("-Xlint:deprecation", str);
            return false;
        }
    },
    CLASSPATH("-classpath", "opt.arg.path", "opt.classpath", OptionKind.STANDARD, OptionGroup.FILEMANAGER),
    CP("-cp", "opt.arg.path", "opt.classpath", OptionKind.STANDARD, OptionGroup.FILEMANAGER) { // from class: com.sun.tools.javac.main.Option.5
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str, String str2) {
            return super.process(optionHelper, "-classpath", str2);
        }
    },
    SOURCEPATH("-sourcepath", "opt.arg.path", "opt.sourcepath", OptionKind.STANDARD, OptionGroup.FILEMANAGER),
    BOOTCLASSPATH("-bootclasspath", "opt.arg.path", "opt.bootclasspath", OptionKind.STANDARD, OptionGroup.FILEMANAGER) { // from class: com.sun.tools.javac.main.Option.6
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str, String str2) {
            optionHelper.remove("-Xbootclasspath/p:");
            optionHelper.remove("-Xbootclasspath/a:");
            return super.process(optionHelper, str, str2);
        }
    },
    XBOOTCLASSPATH_PREPEND("-Xbootclasspath/p:", "opt.arg.path", "opt.Xbootclasspath.p", OptionKind.EXTENDED, OptionGroup.FILEMANAGER),
    XBOOTCLASSPATH_APPEND("-Xbootclasspath/a:", "opt.arg.path", "opt.Xbootclasspath.a", OptionKind.EXTENDED, OptionGroup.FILEMANAGER),
    XBOOTCLASSPATH("-Xbootclasspath:", "opt.arg.path", "opt.bootclasspath", OptionKind.EXTENDED, OptionGroup.FILEMANAGER) { // from class: com.sun.tools.javac.main.Option.7
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str, String str2) {
            optionHelper.remove("-Xbootclasspath/p:");
            optionHelper.remove("-Xbootclasspath/a:");
            return super.process(optionHelper, "-bootclasspath", str2);
        }
    },
    EXTDIRS("-extdirs", "opt.arg.dirs", "opt.extdirs", OptionKind.STANDARD, OptionGroup.FILEMANAGER),
    DJAVA_EXT_DIRS("-Djava.ext.dirs=", "opt.arg.dirs", "opt.extdirs", OptionKind.EXTENDED, OptionGroup.FILEMANAGER) { // from class: com.sun.tools.javac.main.Option.8
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str, String str2) {
            return super.process(optionHelper, "-extdirs", str2);
        }
    },
    ENDORSEDDIRS("-endorseddirs", "opt.arg.dirs", "opt.endorseddirs", OptionKind.STANDARD, OptionGroup.FILEMANAGER),
    DJAVA_ENDORSED_DIRS("-Djava.endorsed.dirs=", "opt.arg.dirs", "opt.endorseddirs", OptionKind.EXTENDED, OptionGroup.FILEMANAGER) { // from class: com.sun.tools.javac.main.Option.9
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str, String str2) {
            return super.process(optionHelper, "-endorseddirs", str2);
        }
    },
    PROC("-proc:", "opt.proc.none.only", OptionKind.STANDARD, OptionGroup.BASIC, ChoiceKind.ONEOF, FlatClientProperties.TABBED_PANE_TAB_ROTATION_NONE, "only"),
    PROCESSOR("-processor", "opt.arg.class.list", "opt.processor", OptionKind.STANDARD, OptionGroup.BASIC),
    PROCESSORPATH("-processorpath", "opt.arg.path", "opt.processorpath", OptionKind.STANDARD, OptionGroup.FILEMANAGER),
    PARAMETERS("-parameters", "opt.parameters", OptionKind.STANDARD, OptionGroup.BASIC),
    D("-d", "opt.arg.directory", "opt.d", OptionKind.STANDARD, OptionGroup.FILEMANAGER),
    S("-s", "opt.arg.directory", "opt.sourceDest", OptionKind.STANDARD, OptionGroup.FILEMANAGER),
    H("-h", "opt.arg.directory", "opt.headerDest", OptionKind.STANDARD, OptionGroup.FILEMANAGER),
    IMPLICIT("-implicit:", "opt.implicit", OptionKind.STANDARD, OptionGroup.BASIC, ChoiceKind.ONEOF, FlatClientProperties.TABBED_PANE_TAB_ROTATION_NONE, "class"),
    ENCODING("-encoding", "opt.arg.encoding", "opt.encoding", OptionKind.STANDARD, OptionGroup.FILEMANAGER) { // from class: com.sun.tools.javac.main.Option.10
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str, String str2) {
            return super.process(optionHelper, str, str2);
        }
    },
    SOURCE("-source", "opt.arg.release", "opt.source", OptionKind.STANDARD, OptionGroup.BASIC) { // from class: com.sun.tools.javac.main.Option.11
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str, String str2) {
            if (Source.lookup(str2) == null) {
                optionHelper.error("err.invalid.source", str2);
                return true;
            }
            return super.process(optionHelper, str, str2);
        }
    },
    TARGET("-target", "opt.arg.release", "opt.target", OptionKind.STANDARD, OptionGroup.BASIC) { // from class: com.sun.tools.javac.main.Option.12
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str, String str2) {
            if (Target.lookup(str2) == null) {
                optionHelper.error("err.invalid.target", str2);
                return true;
            }
            return super.process(optionHelper, str, str2);
        }
    },
    PROFILE("-profile", "opt.arg.profile", "opt.profile", OptionKind.STANDARD, OptionGroup.BASIC) { // from class: com.sun.tools.javac.main.Option.13
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str, String str2) {
            if (Profile.lookup(str2) == null) {
                optionHelper.error("err.invalid.profile", str2);
                return true;
            }
            return super.process(optionHelper, str, str2);
        }
    },
    VERSION("-version", "opt.version", OptionKind.STANDARD, OptionGroup.INFO) { // from class: com.sun.tools.javac.main.Option.14
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            optionHelper.getLog().printLines(Log.PrefixKind.JAVAC, "version", optionHelper.getOwnName(), JavaCompiler.version());
            return super.process(optionHelper, str);
        }
    },
    FULLVERSION("-fullversion", null, OptionKind.HIDDEN, OptionGroup.INFO) { // from class: com.sun.tools.javac.main.Option.15
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            optionHelper.getLog().printLines(Log.PrefixKind.JAVAC, "fullVersion", optionHelper.getOwnName(), JavaCompiler.fullVersion());
            return super.process(optionHelper, str);
        }
    },
    DIAGS("-XDdiags=", null, OptionKind.HIDDEN, OptionGroup.INFO) { // from class: com.sun.tools.javac.main.Option.16
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            String substring = str.substring(str.indexOf(61) + 1);
            String str2 = (substring.contains("%") ? "-XDdiagsFormat=" : "-XDdiags=") + substring;
            if (XD.matches(str2)) {
                return XD.process(optionHelper, str2);
            }
            return false;
        }
    },
    HELP("-help", "opt.help", OptionKind.STANDARD, OptionGroup.INFO) { // from class: com.sun.tools.javac.main.Option.17
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            Log log = optionHelper.getLog();
            log.printLines(Log.PrefixKind.JAVAC, "msg.usage.header", optionHelper.getOwnName());
            Iterator<Option> it = getJavaCompilerOptions().iterator();
            while (it.hasNext()) {
                it.next().help(log, OptionKind.STANDARD);
            }
            log.printNewline();
            return super.process(optionHelper, str);
        }
    },
    A("-A", "opt.arg.key.equals.value", "opt.A", OptionKind.STANDARD, OptionGroup.BASIC, true) { // from class: com.sun.tools.javac.main.Option.18
        @Override // com.sun.tools.javac.main.Option
        public boolean matches(String str) {
            return str.startsWith("-A");
        }

        @Override // com.sun.tools.javac.main.Option
        public boolean hasArg() {
            return false;
        }

        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            int length = str.length();
            if (length == 2) {
                optionHelper.error("err.empty.A.argument", new Object[0]);
                return true;
            }
            int indexOf = str.indexOf(61);
            if (!JavacProcessingEnvironment.isValidOptionName(str.substring(2, indexOf != -1 ? indexOf : length))) {
                optionHelper.error("err.invalid.A.key", str);
                return true;
            }
            return process(optionHelper, str, str);
        }
    },
    X("-X", "opt.X", OptionKind.STANDARD, OptionGroup.INFO) { // from class: com.sun.tools.javac.main.Option.19
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            Log log = optionHelper.getLog();
            Iterator<Option> it = getJavaCompilerOptions().iterator();
            while (it.hasNext()) {
                it.next().help(log, OptionKind.EXTENDED);
            }
            log.printNewline();
            log.printLines(Log.PrefixKind.JAVAC, "msg.usage.nonstandard.footer", new Object[0]);
            return super.process(optionHelper, str);
        }
    },
    J("-J", "opt.arg.flag", "opt.J", OptionKind.STANDARD, OptionGroup.INFO, true) { // from class: com.sun.tools.javac.main.Option.20
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            throw new AssertionError("the -J flag should be caught by the launcher.");
        }
    },
    MOREINFO("-moreinfo", null, OptionKind.HIDDEN, OptionGroup.BASIC) { // from class: com.sun.tools.javac.main.Option.21
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            Type.moreInfo = true;
            return super.process(optionHelper, str);
        }
    },
    WERROR("-Werror", "opt.Werror", OptionKind.STANDARD, OptionGroup.BASIC),
    PROMPT("-prompt", null, OptionKind.HIDDEN, OptionGroup.BASIC),
    DOE("-doe", null, OptionKind.HIDDEN, OptionGroup.BASIC),
    PRINTSOURCE("-printsource", null, OptionKind.HIDDEN, OptionGroup.BASIC),
    WARNUNCHECKED("-warnunchecked", null, OptionKind.HIDDEN, OptionGroup.BASIC) { // from class: com.sun.tools.javac.main.Option.22
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            optionHelper.put("-Xlint:unchecked", str);
            return false;
        }
    },
    XMAXERRS("-Xmaxerrs", "opt.arg.number", "opt.maxerrs", OptionKind.EXTENDED, OptionGroup.BASIC),
    XMAXWARNS("-Xmaxwarns", "opt.arg.number", "opt.maxwarns", OptionKind.EXTENDED, OptionGroup.BASIC),
    XSTDOUT("-Xstdout", "opt.arg.file", "opt.Xstdout", OptionKind.EXTENDED, OptionGroup.INFO) { // from class: com.sun.tools.javac.main.Option.23
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str, String str2) {
            try {
                optionHelper.getLog().setWriters(new PrintWriter((Writer) new FileWriter(str2), true));
                return super.process(optionHelper, str, str2);
            } catch (IOException e) {
                optionHelper.error("err.error.writing.file", str2, e);
                return true;
            }
        }
    },
    XPRINT("-Xprint", "opt.print", OptionKind.EXTENDED, OptionGroup.BASIC),
    XPRINTROUNDS("-XprintRounds", "opt.printRounds", OptionKind.EXTENDED, OptionGroup.BASIC),
    XPRINTPROCESSORINFO("-XprintProcessorInfo", "opt.printProcessorInfo", OptionKind.EXTENDED, OptionGroup.BASIC),
    XPREFER("-Xprefer:", "opt.prefer", OptionKind.EXTENDED, OptionGroup.BASIC, ChoiceKind.ONEOF, "source", "newer"),
    XPKGINFO("-Xpkginfo:", "opt.pkginfo", OptionKind.EXTENDED, OptionGroup.BASIC, ChoiceKind.ONEOF, FlatClientProperties.SELECT_ALL_ON_FOCUS_POLICY_ALWAYS, "legacy", "nonempty"),
    O("-O", null, OptionKind.HIDDEN, OptionGroup.BASIC),
    XJCOV("-Xjcov", null, OptionKind.HIDDEN, OptionGroup.BASIC),
    PLUGIN("-Xplugin:", "opt.arg.plugin", "opt.plugin", OptionKind.EXTENDED, OptionGroup.BASIC) { // from class: com.sun.tools.javac.main.Option.24
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            String substring = str.substring(str.indexOf(58) + 1);
            String str2 = optionHelper.get(PLUGIN);
            optionHelper.put(PLUGIN.text, str2 == null ? substring : str2 + (char) 0 + substring.trim());
            return false;
        }
    },
    XDIAGS("-Xdiags:", "opt.diags", OptionKind.EXTENDED, OptionGroup.BASIC, ChoiceKind.ONEOF, FlatClientProperties.TABBED_PANE_TAB_WIDTH_MODE_COMPACT, "verbose"),
    XD("-XD", null, OptionKind.HIDDEN, OptionGroup.BASIC) { // from class: com.sun.tools.javac.main.Option.25
        @Override // com.sun.tools.javac.main.Option
        public boolean matches(String str) {
            return str.startsWith(this.text);
        }

        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            String substring = str.substring(this.text.length());
            int indexOf = substring.indexOf(61);
            optionHelper.put(indexOf < 0 ? substring : substring.substring(0, indexOf), indexOf < 0 ? substring : substring.substring(indexOf + 1));
            return false;
        }
    },
    AT("@", "opt.arg.file", "opt.AT", OptionKind.STANDARD, OptionGroup.INFO, true) { // from class: com.sun.tools.javac.main.Option.26
        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            throw new AssertionError("the @ flag should be caught by CommandLine.");
        }
    },
    SOURCEFILE("sourcefile", null, OptionKind.HIDDEN, OptionGroup.INFO) { // from class: com.sun.tools.javac.main.Option.27
        @Override // com.sun.tools.javac.main.Option
        public boolean matches(String str) {
            return str.endsWith(Constants.SOURCE_FILE_EXTENSION) || SourceVersion.isName(str);
        }

        @Override // com.sun.tools.javac.main.Option
        public boolean process(OptionHelper optionHelper, String str) {
            if (str.endsWith(Constants.SOURCE_FILE_EXTENSION)) {
                File file = new File(str);
                if (!file.exists()) {
                    optionHelper.error("err.file.not.found", file);
                    return true;
                }
                if (!file.isFile()) {
                    optionHelper.error("err.file.not.file", file);
                    return true;
                }
                optionHelper.addFile(file);
                return false;
            }
            optionHelper.addClassName(str);
            return false;
        }
    };

    public final String text;
    final OptionKind kind;
    final OptionGroup group;
    final String argsNameKey;
    final String descrKey;
    final boolean hasSuffix;
    final ChoiceKind choiceKind;
    final Map<String, Boolean> choices;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javac/main/Option$ChoiceKind.class */
    public enum ChoiceKind {
        ONEOF,
        ANYOF
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javac/main/Option$OptionGroup.class */
    public enum OptionGroup {
        BASIC,
        FILEMANAGER,
        INFO,
        OPERAND
    }

    /* loaded from: target.jar:com/sun/tools/javac/main/Option$OptionKind.class */
    public enum OptionKind {
        STANDARD,
        EXTENDED,
        HIDDEN
    }

    Option(String str, String str2, OptionKind optionKind, OptionGroup optionGroup) {
        this(str, null, str2, optionKind, optionGroup, null, null, false);
    }

    Option(String str, String str2, String str3, OptionKind optionKind, OptionGroup optionGroup) {
        this(str, str2, str3, optionKind, optionGroup, null, null, false);
    }

    Option(String str, String str2, String str3, OptionKind optionKind, OptionGroup optionGroup, boolean z) {
        this(str, str2, str3, optionKind, optionGroup, null, null, z);
    }

    Option(String str, String str2, OptionKind optionKind, OptionGroup optionGroup, ChoiceKind choiceKind, Map map) {
        this(str, null, str2, optionKind, optionGroup, choiceKind, map, false);
    }

    Option(String str, String str2, OptionKind optionKind, OptionGroup optionGroup, ChoiceKind choiceKind, String... strArr) {
        this(str, null, str2, optionKind, optionGroup, choiceKind, createChoices(strArr), false);
    }

    private static Map<String, Boolean> createChoices(String... strArr) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : strArr) {
            linkedHashMap.put(str, false);
        }
        return linkedHashMap;
    }

    Option(String str, String str2, String str3, OptionKind optionKind, OptionGroup optionGroup, ChoiceKind choiceKind, Map map, boolean z) {
        this.text = str;
        this.argsNameKey = str2;
        this.descrKey = str3;
        this.kind = optionKind;
        this.group = optionGroup;
        this.choiceKind = choiceKind;
        this.choices = map;
        char charAt = str.charAt(str.length() - 1);
        this.hasSuffix = z || charAt == ':' || charAt == '=';
    }

    public String getText() {
        return this.text;
    }

    public OptionKind getKind() {
        return this.kind;
    }

    public boolean hasArg() {
        return (this.argsNameKey == null || this.hasSuffix) ? false : true;
    }

    public boolean matches(String str) {
        if (!this.hasSuffix) {
            return str.equals(this.text);
        }
        if (!str.startsWith(this.text)) {
            return false;
        }
        if (this.choices != null) {
            String substring = str.substring(this.text.length());
            if (this.choiceKind == ChoiceKind.ONEOF) {
                return this.choices.keySet().contains(substring);
            }
            for (String str2 : substring.split(",+")) {
                if (!this.choices.keySet().contains(str2)) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public boolean process(OptionHelper optionHelper, String str, String str2) {
        if (this.choices != null) {
            if (this.choiceKind == ChoiceKind.ONEOF) {
                Iterator<String> it = this.choices.keySet().iterator();
                while (it.hasNext()) {
                    optionHelper.remove(str + it.next());
                }
                String str3 = str + str2;
                optionHelper.put(str3, str3);
                optionHelper.put(str.substring(0, str.length() - 1), str2);
            } else {
                for (String str4 : str2.split(",+")) {
                    String str5 = str + str4;
                    optionHelper.put(str5, str5);
                }
            }
        }
        optionHelper.put(str, str2);
        return false;
    }

    public boolean process(OptionHelper optionHelper, String str) {
        if (this.hasSuffix) {
            return process(optionHelper, this.text, str.substring(this.text.length()));
        }
        return process(optionHelper, str, str);
    }

    void help(Log log, OptionKind optionKind) {
        if (this.kind != optionKind) {
            return;
        }
        log.printRawLines(Log.WriterKind.NOTICE, String.format("  %-26s %s", helpSynopsis(log), log.localize(Log.PrefixKind.JAVAC, this.descrKey, new Object[0])));
    }

    private String helpSynopsis(Log log) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.text);
        if (this.argsNameKey == null) {
            if (this.choices != null) {
                String str = "{";
                for (Map.Entry<String, Boolean> entry : this.choices.entrySet()) {
                    if (!entry.getValue().booleanValue()) {
                        sb.append(str);
                        sb.append(entry.getKey());
                        str = DocLint.TAGS_SEPARATOR;
                    }
                }
                sb.append("}");
            }
        } else {
            if (!this.hasSuffix) {
                sb.append(" ");
            }
            sb.append(log.localize(Log.PrefixKind.JAVAC, this.argsNameKey, new Object[0]));
        }
        return sb.toString();
    }

    /* loaded from: target.jar:com/sun/tools/javac/main/Option$PkgInfo.class */
    public enum PkgInfo {
        ALWAYS,
        LEGACY,
        NONEMPTY;

        public static PkgInfo get(Options options) {
            String str = options.get(Option.XPKGINFO);
            return str == null ? LEGACY : valueOf(StringUtils.toUpperCase(str));
        }
    }

    private static Map<String, Boolean> getXLintChoices() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("all", false);
        for (Lint.LintCategory lintCategory : Lint.LintCategory.values()) {
            linkedHashMap.put(lintCategory.option, Boolean.valueOf(lintCategory.hidden));
        }
        for (Lint.LintCategory lintCategory2 : Lint.LintCategory.values()) {
            linkedHashMap.put(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR + lintCategory2.option, Boolean.valueOf(lintCategory2.hidden));
        }
        linkedHashMap.put(FlatClientProperties.TABBED_PANE_TAB_ROTATION_NONE, false);
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set<Option> getJavaCompilerOptions() {
        return EnumSet.allOf(Option.class);
    }

    public static Set<Option> getJavacFileManagerOptions() {
        return getOptions(EnumSet.of(OptionGroup.FILEMANAGER));
    }

    public static Set<Option> getJavacToolOptions() {
        return getOptions(EnumSet.of(OptionGroup.BASIC));
    }

    static Set<Option> getOptions(Set<OptionGroup> set) {
        EnumSet noneOf = EnumSet.noneOf(Option.class);
        for (Option option : values()) {
            if (set.contains(option.group)) {
                noneOf.add(option);
            }
        }
        return Collections.unmodifiableSet(noneOf);
    }
}
