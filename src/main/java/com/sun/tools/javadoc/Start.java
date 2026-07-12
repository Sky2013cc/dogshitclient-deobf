package com.sun.tools.javadoc;

import com.sun.javadoc.LanguageVersion;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.javac.main.CommandLine;
import com.sun.tools.javac.util.ClientCodeException;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javadoc.Messager;
import com.sun.tools.javadoc.ToolOption;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

/* loaded from: target.jar:com/sun/tools/javadoc/Start.class */
public class Start extends ToolOption.Helper {
    private final Context context;
    private final String defaultDocletClassName;
    private final ClassLoader docletParentClassLoader;
    private static final String javadocName = "javadoc";
    private static final String standardDocletClassName = "com.sun.tools.doclets.standard.Standard";
    private long defaultFilter;
    private final Messager messager;
    private DocletInvoker docletInvoker;
    private boolean apiMode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Start(String str, PrintWriter printWriter, PrintWriter printWriter2, PrintWriter printWriter3, String str2) {
        this(str, printWriter, printWriter2, printWriter3, str2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Start(String str, PrintWriter printWriter, PrintWriter printWriter2, PrintWriter printWriter3, String str2, ClassLoader classLoader) {
        this.defaultFilter = 5L;
        this.context = new Context();
        this.messager = new Messager(this.context, str, printWriter, printWriter2, printWriter3);
        this.defaultDocletClassName = str2;
        this.docletParentClassLoader = classLoader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Start(String str, String str2) {
        this(str, str2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Start(String str, String str2, ClassLoader classLoader) {
        this.defaultFilter = 5L;
        this.context = new Context();
        this.messager = new Messager(this.context, str);
        this.defaultDocletClassName = str2;
        this.docletParentClassLoader = classLoader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Start(String str, ClassLoader classLoader) {
        this(str, standardDocletClassName, classLoader);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Start(String str) {
        this(str, standardDocletClassName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Start(ClassLoader classLoader) {
        this(javadocName, classLoader);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Start() {
        this(javadocName);
    }

    public Start(Context context) {
        this.defaultFilter = 5L;
        context.getClass();
        this.context = context;
        this.apiMode = true;
        this.defaultDocletClassName = standardDocletClassName;
        this.docletParentClassLoader = null;
        Log log = (Log) context.get(Log.logKey);
        if (log instanceof Messager) {
            this.messager = (Messager) log;
        } else {
            PrintWriter printWriter = (PrintWriter) context.get(Log.outKey);
            this.messager = printWriter == null ? new Messager(context, javadocName) : new Messager(context, javadocName, printWriter, printWriter, printWriter);
        }
    }

    @Override // com.sun.tools.javadoc.ToolOption.Helper
    void usage() {
        usage(true);
    }

    void usage(boolean z) {
        usage("main.usage", "-help", null, z);
    }

    @Override // com.sun.tools.javadoc.ToolOption.Helper
    void Xusage() {
        Xusage(true);
    }

    void Xusage(boolean z) {
        usage("main.Xusage", "-X", "main.Xusage.foot", z);
    }

    private void usage(String str, String str2, String str3, boolean z) {
        this.messager.notice(str, new Object[0]);
        if (this.docletInvoker != null) {
            this.docletInvoker.optionLength(str2);
        }
        if (str3 != null) {
            this.messager.notice(str3, new Object[0]);
        }
        if (z) {
            exit();
        }
    }

    private void exit() {
        this.messager.exit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int begin(String... strArr) {
        return begin((Class<?>) null, strArr, Collections.emptySet()) ? 0 : 1;
    }

    public boolean begin(Class<?> cls, Iterable<String> iterable, Iterable<? extends JavaFileObject> iterable2) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return begin(cls, (String[]) arrayList.toArray(new String[arrayList.size()]), iterable2);
    }

    private boolean begin(Class<?> cls, String[] strArr, Iterable<? extends JavaFileObject> iterable) {
        boolean z = false;
        try {
            try {
                try {
                    try {
                        z = !parseAndExecute(cls, strArr, iterable);
                        this.messager.exitNotice();
                        this.messager.flush();
                    } catch (Error e) {
                        e.printStackTrace(System.err);
                        this.messager.error(Messager.NOPOS, "main.fatal.error", new Object[0]);
                        z = true;
                        this.messager.exitNotice();
                        this.messager.flush();
                    }
                } catch (ClientCodeException e2) {
                    throw e2;
                } catch (OutOfMemoryError e3) {
                    this.messager.error(Messager.NOPOS, "main.out.of.memory", new Object[0]);
                    z = true;
                    this.messager.exitNotice();
                    this.messager.flush();
                }
            } catch (Messager.ExitJavadoc e4) {
                this.messager.exitNotice();
                this.messager.flush();
            } catch (Exception e5) {
                e5.printStackTrace(System.err);
                this.messager.error(Messager.NOPOS, "main.fatal.exception", new Object[0]);
                z = true;
                this.messager.exitNotice();
                this.messager.flush();
            }
            return !((z | (this.messager.nerrors() > 0)) | (this.rejectWarnings && this.messager.nwarnings() > 0));
        } catch (Throwable th) {
            this.messager.exitNotice();
            this.messager.flush();
            throw th;
        }
    }

    private boolean parseAndExecute(Class<?> cls, String[] strArr, Iterable<? extends JavaFileObject> iterable) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        ListBuffer listBuffer = new ListBuffer();
        try {
            strArr = CommandLine.parse(strArr);
        } catch (FileNotFoundException e) {
            this.messager.error(Messager.NOPOS, "main.cant.read", e.getMessage());
            exit();
        } catch (IOException e2) {
            e2.printStackTrace(System.err);
            exit();
        }
        setDocletInvoker(cls, (JavaFileManager) this.context.get(JavaFileManager.class), strArr);
        this.compOpts = Options.instance(this.context);
        this.compOpts.put("-Xlint:-options", "-Xlint:-options");
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            ToolOption toolOption = ToolOption.get(str);
            if (toolOption != null) {
                if (toolOption == ToolOption.LOCALE && i > 0) {
                    usageError("main.locale_first", new Object[0]);
                }
                if (toolOption.hasArg) {
                    int i2 = i;
                    i++;
                    oneArg(strArr, i2);
                    toolOption.process(this, strArr[i]);
                } else {
                    setOption(str);
                    toolOption.process(this);
                }
            } else if (str.startsWith("-XD")) {
                String substring = str.substring("-XD".length());
                int indexOf = substring.indexOf(61);
                this.compOpts.put(indexOf < 0 ? substring : substring.substring(0, indexOf), indexOf < 0 ? substring : substring.substring(indexOf + 1));
            } else if (str.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                int optionLength = this.docletInvoker.optionLength(str);
                if (optionLength < 0) {
                    exit();
                } else if (optionLength == 0) {
                    usageError("main.invalid_flag", str);
                } else {
                    if (i + optionLength > strArr.length) {
                        usageError("main.requires_argument", str);
                    }
                    ListBuffer listBuffer2 = new ListBuffer();
                    for (int i3 = 0; i3 < optionLength - 1; i3++) {
                        i++;
                        listBuffer2.append(strArr[i]);
                    }
                    setOption(str, listBuffer2.toList());
                }
            } else {
                listBuffer.append(str);
            }
            i++;
        }
        this.compOpts.notifyListeners();
        if (listBuffer.isEmpty() && this.subPackages.isEmpty() && isEmpty(iterable)) {
            usageError("main.No_packages_or_classes_specified", new Object[0]);
        }
        if (!this.docletInvoker.validOptions(this.options.toList())) {
            exit();
        }
        JavadocTool make0 = JavadocTool.make0(this.context);
        if (make0 == null) {
            return false;
        }
        if (this.showAccess == null) {
            setFilter(this.defaultFilter);
        }
        LanguageVersion languageVersion = this.docletInvoker.languageVersion();
        RootDocImpl rootDocImpl = make0.getRootDocImpl(this.docLocale, this.encoding, this.showAccess, listBuffer.toList(), this.options.toList(), iterable, this.breakiterator, this.subPackages.toList(), this.excludedPackages.toList(), this.docClasses, languageVersion == null || languageVersion == LanguageVersion.JAVA_1_1, this.quiet);
        boolean z = rootDocImpl != null;
        if (z) {
            z = this.docletInvoker.start(rootDocImpl);
        }
        if (this.compOpts.get("-verbose") != null) {
            this.messager.notice("main.done_in", Long.toString(System.currentTimeMillis() - currentTimeMillis));
        }
        return z;
    }

    private <T> boolean isEmpty(Iterable<T> iterable) {
        return !iterable.iterator().hasNext();
    }

    private void setDocletInvoker(Class<?> cls, JavaFileManager javaFileManager, String[] strArr) {
        if (cls != null) {
            this.docletInvoker = new DocletInvoker(this.messager, cls, this.apiMode);
            return;
        }
        String str = null;
        String str2 = null;
        int i = 0;
        while (i < strArr.length) {
            String str3 = strArr[i];
            if (str3.equals(ToolOption.DOCLET.opt)) {
                int i2 = i;
                i++;
                oneArg(strArr, i2);
                if (str != null) {
                    usageError("main.more_than_one_doclet_specified_0_and_1", str, strArr[i]);
                }
                str = strArr[i];
            } else if (str3.equals(ToolOption.DOCLETPATH.opt)) {
                int i3 = i;
                i++;
                oneArg(strArr, i3);
                if (str2 == null) {
                    str2 = strArr[i];
                } else {
                    str2 = str2 + File.pathSeparator + strArr[i];
                }
            }
            i++;
        }
        if (str == null) {
            str = this.defaultDocletClassName;
        }
        this.docletInvoker = new DocletInvoker(this.messager, javaFileManager, str, str2, this.docletParentClassLoader, this.apiMode);
    }

    private void oneArg(String[] strArr, int i) {
        if (i + 1 < strArr.length) {
            setOption(strArr[i], strArr[i + 1]);
        } else {
            usageError("main.requires_argument", strArr[i]);
        }
    }

    @Override // com.sun.tools.javadoc.ToolOption.Helper
    void usageError(String str, Object... objArr) {
        this.messager.error(Messager.NOPOS, str, objArr);
        usage(true);
    }

    private void setOption(String str) {
        this.options.append(new String[]{str});
    }

    private void setOption(String str, String str2) {
        this.options.append(new String[]{str, str2});
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setOption(String str, List<String> list) {
        String[] strArr = new String[list.length() + 1];
        int i = 0 + 1;
        strArr[0] = str;
        List list2 = list;
        while (true) {
            List list3 = list2;
            if (list3.nonEmpty()) {
                int i2 = i;
                i++;
                strArr[i2] = (String) list3.head;
                list2 = list3.tail;
            } else {
                this.options.append(strArr);
                return;
            }
        }
    }
}
