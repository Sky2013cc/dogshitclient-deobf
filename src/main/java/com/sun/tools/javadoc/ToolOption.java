package com.sun.tools.javadoc;

import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Options;
import java.util.StringTokenizer;
import jdk.internal.dynalink.CallSiteDescriptor;

/* loaded from: target.jar:com/sun/tools/javadoc/ToolOption.class */
public enum ToolOption {
    BOOTCLASSPATH("-bootclasspath", true) { // from class: com.sun.tools.javadoc.ToolOption.1
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.setCompilerOpt(this.opt, str);
        }
    },
    CLASSPATH("-classpath", true) { // from class: com.sun.tools.javadoc.ToolOption.2
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.setCompilerOpt(this.opt, str);
        }
    },
    CP("-cp", true) { // from class: com.sun.tools.javadoc.ToolOption.3
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.setCompilerOpt(this.opt, str);
        }
    },
    EXTDIRS("-extdirs", true) { // from class: com.sun.tools.javadoc.ToolOption.4
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.setCompilerOpt(this.opt, str);
        }
    },
    SOURCEPATH("-sourcepath", true) { // from class: com.sun.tools.javadoc.ToolOption.5
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.setCompilerOpt(this.opt, str);
        }
    },
    SYSCLASSPATH("-sysclasspath", true) { // from class: com.sun.tools.javadoc.ToolOption.6
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.setCompilerOpt("-bootclasspath", str);
        }
    },
    ENCODING("-encoding", true) { // from class: com.sun.tools.javadoc.ToolOption.7
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.encoding = str;
            helper.setCompilerOpt(this.opt, str);
        }
    },
    SOURCE("-source", true) { // from class: com.sun.tools.javadoc.ToolOption.8
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.setCompilerOpt(this.opt, str);
        }
    },
    XMAXERRS("-Xmaxerrs", true) { // from class: com.sun.tools.javadoc.ToolOption.9
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.setCompilerOpt(this.opt, str);
        }
    },
    XMAXWARNS("-Xmaxwarns", true) { // from class: com.sun.tools.javadoc.ToolOption.10
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.setCompilerOpt(this.opt, str);
        }
    },
    DOCLET("-doclet", true),
    DOCLETPATH("-docletpath", true),
    SUBPACKAGES("-subpackages", true) { // from class: com.sun.tools.javadoc.ToolOption.11
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.addToList(helper.subPackages, str);
        }
    },
    EXCLUDE("-exclude", true) { // from class: com.sun.tools.javadoc.ToolOption.12
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.addToList(helper.excludedPackages, str);
        }
    },
    PACKAGE("-package") { // from class: com.sun.tools.javadoc.ToolOption.13
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.setFilter(-9223372036854775803L);
        }
    },
    PRIVATE("-private") { // from class: com.sun.tools.javadoc.ToolOption.14
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.setFilter(-9223372036854775801L);
        }
    },
    PROTECTED("-protected") { // from class: com.sun.tools.javadoc.ToolOption.15
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.setFilter(5L);
        }
    },
    PUBLIC("-public") { // from class: com.sun.tools.javadoc.ToolOption.16
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.setFilter(1L);
        }
    },
    PROMPT("-prompt") { // from class: com.sun.tools.javadoc.ToolOption.17
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.compOpts.put("-prompt", "-prompt");
            helper.promptOnError = true;
        }
    },
    QUIET("-quiet") { // from class: com.sun.tools.javadoc.ToolOption.18
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.quiet = true;
        }
    },
    VERBOSE("-verbose") { // from class: com.sun.tools.javadoc.ToolOption.19
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.compOpts.put("-verbose", "");
        }
    },
    XWERROR("-Xwerror") { // from class: com.sun.tools.javadoc.ToolOption.20
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.rejectWarnings = true;
        }
    },
    BREAKITERATOR("-breakiterator") { // from class: com.sun.tools.javadoc.ToolOption.21
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.breakiterator = true;
        }
    },
    LOCALE("-locale", true) { // from class: com.sun.tools.javadoc.ToolOption.22
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper, String str) {
            helper.docLocale = str;
        }
    },
    OVERVIEW("-overview", true),
    XCLASSES("-Xclasses") { // from class: com.sun.tools.javadoc.ToolOption.23
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.docClasses = true;
        }
    },
    HELP("-help") { // from class: com.sun.tools.javadoc.ToolOption.24
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.usage();
        }
    },
    X("-X") { // from class: com.sun.tools.javadoc.ToolOption.25
        @Override // com.sun.tools.javadoc.ToolOption
        public void process(Helper helper) {
            helper.Xusage();
        }
    };

    public final String opt;
    public final boolean hasArg;

    ToolOption(String str) {
        this(str, false);
    }

    ToolOption(String str, boolean z) {
        this.opt = str;
        this.hasArg = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void process(Helper helper, String str) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void process(Helper helper) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ToolOption get(String str) {
        for (ToolOption toolOption : values()) {
            if (str.equals(toolOption.opt)) {
                return toolOption;
            }
        }
        return null;
    }

    /* loaded from: target.jar:com/sun/tools/javadoc/ToolOption$Helper.class */
    static abstract class Helper {
        Options compOpts;
        boolean promptOnError;
        final ListBuffer<String[]> options = new ListBuffer<>();
        final ListBuffer<String> subPackages = new ListBuffer<>();
        final ListBuffer<String> excludedPackages = new ListBuffer<>();
        String encoding = null;
        boolean breakiterator = false;
        boolean quiet = false;
        boolean docClasses = false;
        boolean rejectWarnings = false;
        String docLocale = "";
        ModifierFilter showAccess = null;

        abstract void usage();

        abstract void Xusage();

        abstract void usageError(String str, Object... objArr);

        protected void addToList(ListBuffer<String> listBuffer, String str) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, CallSiteDescriptor.TOKEN_DELIMITER);
            while (stringTokenizer.hasMoreTokens()) {
                listBuffer.append(stringTokenizer.nextToken());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setFilter(long j) {
            if (this.showAccess != null) {
                usageError("main.incompatible.access.flags", new Object[0]);
            }
            this.showAccess = new ModifierFilter(j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCompilerOpt(String str, String str2) {
            if (this.compOpts.get(str) != null) {
                usageError("main.option.already.seen", str);
            }
            this.compOpts.put(str, str2);
        }
    }
}
