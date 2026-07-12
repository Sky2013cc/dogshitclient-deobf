package com.sun.tools.hat.internal.server;

import com.sun.tools.hat.internal.model.JavaClass;
import com.sun.tools.hat.internal.model.JavaField;
import com.sun.tools.hat.internal.model.JavaHeapObject;
import com.sun.tools.hat.internal.model.JavaObject;
import com.sun.tools.hat.internal.model.JavaStatic;
import com.sun.tools.hat.internal.model.JavaThing;
import com.sun.tools.hat.internal.model.Root;
import com.sun.tools.hat.internal.model.Snapshot;
import com.sun.tools.hat.internal.model.StackFrame;
import com.sun.tools.hat.internal.model.StackTrace;
import com.sun.tools.hat.internal.util.Misc;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/hat/internal/server/QueryHandler.class */
abstract class QueryHandler {
    protected String urlStart;
    protected String query;
    protected PrintWriter out;
    protected Snapshot snapshot;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void run();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUrlStart(String str) {
        this.urlStart = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setQuery(String str) {
        this.query = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOutput(PrintWriter printWriter) {
        this.out = printWriter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    protected String encodeForURL(String str) {
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startHtml(String str) {
        this.out.print("<html><title>");
        print(str);
        this.out.println("</title>");
        this.out.println("<body bgcolor=\"#ffffff\"><center><h1>");
        print(str);
        this.out.println("</h1></center>");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void endHtml() {
        this.out.println("</body></html>");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void error(String str) {
        println(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void printAnchorStart() {
        this.out.print("<a href=\"");
        this.out.print(this.urlStart);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void printThingAnchorTag(long j) {
        printAnchorStart();
        this.out.print("object/");
        printHex(j);
        this.out.print("\">");
    }

    protected void printObject(JavaObject javaObject) {
        printThing(javaObject);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void printThing(JavaThing javaThing) {
        if (javaThing == null) {
            this.out.print("null");
            return;
        }
        if (javaThing instanceof JavaHeapObject) {
            JavaHeapObject javaHeapObject = (JavaHeapObject) javaThing;
            long id = javaHeapObject.getId();
            if (id != -1) {
                if (javaHeapObject.isNew()) {
                    this.out.println("<strong>");
                }
                printThingAnchorTag(id);
            }
            print(javaThing.toString());
            if (id != -1) {
                if (javaHeapObject.isNew()) {
                    this.out.println("[new]</strong>");
                }
                this.out.print(" (" + javaHeapObject.getSize() + " bytes)");
                this.out.println("</a>");
                return;
            }
            return;
        }
        print(javaThing.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void printRoot(Root root) {
        StackTrace stackTrace = root.getStackTrace();
        boolean z = (stackTrace == null || stackTrace.getFrames().length == 0) ? false : true;
        if (z) {
            printAnchorStart();
            this.out.print("rootStack/");
            printHex(root.getIndex());
            this.out.print("\">");
        }
        print(root.getDescription());
        if (z) {
            this.out.print("</a>");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void printClass(JavaClass javaClass) {
        if (javaClass == null) {
            this.out.println("null");
            return;
        }
        printAnchorStart();
        this.out.print("class/");
        print(encodeForURL(javaClass));
        this.out.print("\">");
        print(javaClass.toString());
        this.out.println("</a>");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String encodeForURL(JavaClass javaClass) {
        if (javaClass.getId() == -1) {
            return encodeForURL(javaClass.getName());
        }
        return javaClass.getIdString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void printField(JavaField javaField) {
        print(javaField.getName() + " (" + javaField.getSignature() + RuntimeConstants.SIG_ENDMETHOD);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void printStatic(JavaStatic javaStatic) {
        JavaField field = javaStatic.getField();
        printField(field);
        this.out.print(" : ");
        if (field.hasId()) {
            printThing(javaStatic.getValue());
        } else {
            print(javaStatic.getValue().toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void printStackTrace(StackTrace stackTrace) {
        for (StackFrame stackFrame : stackTrace.getFrames()) {
            String className = stackFrame.getClassName();
            this.out.print("<font color=purple>");
            print(className);
            this.out.print("</font>");
            print(Constants.NAME_SEPARATOR + stackFrame.getMethodName() + RuntimeConstants.SIG_METHOD + stackFrame.getMethodSignature() + RuntimeConstants.SIG_ENDMETHOD);
            this.out.print(" <bold>:</bold> ");
            print(stackFrame.getSourceFileName() + " line " + stackFrame.getLineNumber());
            this.out.println("<br>");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void printException(Throwable th) {
        println(th.getMessage());
        this.out.println("<pre>");
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        print(stringWriter.toString());
        this.out.println("</pre>");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void printHex(long j) {
        if (this.snapshot.getIdentifierSize() == 4) {
            this.out.print(Misc.toHex((int) j));
        } else {
            this.out.print(Misc.toHex(j));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long parseHex(String str) {
        return Misc.parseHex(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void print(String str) {
        this.out.print(Misc.encodeHtml(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void println(String str) {
        this.out.println(Misc.encodeHtml(str));
    }
}
