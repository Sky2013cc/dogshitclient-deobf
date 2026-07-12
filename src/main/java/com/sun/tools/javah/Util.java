package com.sun.tools.javah;

import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

/* loaded from: target.jar:com/sun/tools/javah/Util.class */
public class Util {
    public boolean verbose = false;
    public PrintWriter log;
    public DiagnosticListener<? super JavaFileObject> dl;
    private ResourceBundle m;

    /* loaded from: target.jar:com/sun/tools/javah/Util$Exit.class */
    public static class Exit extends Error {
        private static final long serialVersionUID = 430820978114067221L;
        public final int exitValue;
        public final Throwable cause;

        Exit(int i) {
            this(i, null);
        }

        Exit(int i, Throwable th) {
            super(th);
            this.exitValue = i;
            this.cause = th;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Exit(Exit exit) {
            this(exit.exitValue, exit.cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Util(PrintWriter printWriter, DiagnosticListener<? super JavaFileObject> diagnosticListener) {
        this.log = printWriter;
        this.dl = diagnosticListener;
    }

    public void log(String str) {
        this.log.println(str);
    }

    private void initMessages() throws Exit {
        try {
            this.m = ResourceBundle.getBundle("com.sun.tools.javah.resources.l10n");
        } catch (MissingResourceException e) {
            fatal("Error loading resources.  Please file a bug report.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getText(String str, Object... objArr) throws Exit {
        if (this.m == null) {
            initMessages();
        }
        try {
            return MessageFormat.format(this.m.getString(str), objArr);
        } catch (MissingResourceException e) {
            fatal("Key " + str + " not found in resources.", e);
            return null;
        }
    }

    public void usage() throws Exit {
        this.log.println(getText("usage", new Object[0]));
    }

    public void version() throws Exit {
        this.log.println(getText("javah.version", System.getProperty("java.version"), null));
    }

    public void bug(String str) throws Exit {
        bug(str, null);
    }

    public void bug(String str, Exception exc) throws Exit {
        this.dl.report(createDiagnostic(Diagnostic.Kind.ERROR, str, new Object[0]));
        this.dl.report(createDiagnostic(Diagnostic.Kind.NOTE, "bug.report", new Object[0]));
        throw new Exit(11, exc);
    }

    public void error(String str, Object... objArr) throws Exit {
        this.dl.report(createDiagnostic(Diagnostic.Kind.ERROR, str, objArr));
        throw new Exit(15);
    }

    private void fatal(String str, Exception exc) throws Exit {
        this.dl.report(createDiagnostic(Diagnostic.Kind.ERROR, "", str));
        throw new Exit(10, exc);
    }

    private Diagnostic<JavaFileObject> createDiagnostic(final Diagnostic.Kind kind, final String str, final Object... objArr) {
        return new Diagnostic<JavaFileObject>() { // from class: com.sun.tools.javah.Util.1
            public String getCode() {
                return str;
            }

            public long getColumnNumber() {
                return -1L;
            }

            public long getEndPosition() {
                return -1L;
            }

            public Diagnostic.Kind getKind() {
                return kind;
            }

            public long getLineNumber() {
                return -1L;
            }

            public String getMessage(Locale locale) {
                if (str.length() != 0) {
                    return Util.this.getText(str, objArr);
                }
                return (String) objArr[0];
            }

            public long getPosition() {
                return -1L;
            }

            /* renamed from: getSource, reason: merged with bridge method [inline-methods] */
            public JavaFileObject m653getSource() {
                return null;
            }

            public long getStartPosition() {
                return -1L;
            }
        };
    }
}
