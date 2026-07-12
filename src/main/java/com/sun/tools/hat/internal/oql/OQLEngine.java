package com.sun.tools.hat.internal.oql;

import com.sun.tools.hat.internal.model.JavaClass;
import com.sun.tools.hat.internal.model.JavaHeapObject;
import com.sun.tools.hat.internal.model.Snapshot;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.StringTokenizer;

/* loaded from: target.jar:com/sun/tools/hat/internal/oql/OQLEngine.class */
public class OQLEngine {
    private Object engine;
    private Method evalMethod;
    private Method invokeMethod;
    private Snapshot snapshot;
    private static boolean debug;
    private static boolean oqlSupported;

    static {
        try {
            Class<?> cls = Class.forName("javax.script.ScriptEngineManager");
            oqlSupported = cls.getMethod("getEngineByName", String.class).invoke(cls.newInstance(), "js") != null;
        } catch (Exception e) {
            oqlSupported = false;
        }
        debug = false;
    }

    public static boolean isOQLSupported() {
        return oqlSupported;
    }

    public OQLEngine(Snapshot snapshot) {
        if (!isOQLSupported()) {
            throw new UnsupportedOperationException("OQL not supported");
        }
        init(snapshot);
    }

    public synchronized void executeQuery(String str, ObjectVisitor objectVisitor) throws OQLException {
        debugPrint("query : " + str);
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        if (stringTokenizer.hasMoreTokens()) {
            if (!stringTokenizer.nextToken().equals("select")) {
                try {
                    objectVisitor.visit(evalScript(str));
                    return;
                } catch (Exception e) {
                    throw new OQLException(e);
                }
            }
            String str2 = "";
            boolean z = false;
            while (true) {
                if (!stringTokenizer.hasMoreTokens()) {
                    break;
                }
                String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals("from")) {
                    z = true;
                    break;
                }
                str2 = str2 + " " + nextToken;
            }
            if (str2.equals("")) {
                throw new OQLException("query syntax error: 'select' expression can not be empty");
            }
            String str3 = null;
            boolean z2 = false;
            String str4 = null;
            String str5 = null;
            if (z) {
                if (stringTokenizer.hasMoreTokens()) {
                    String nextToken2 = stringTokenizer.nextToken();
                    if (nextToken2.equals("instanceof")) {
                        z2 = true;
                        if (!stringTokenizer.hasMoreTokens()) {
                            throw new OQLException("no class name after 'instanceof'");
                        }
                        str3 = stringTokenizer.nextToken();
                    } else {
                        str3 = nextToken2;
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        str5 = stringTokenizer.nextToken();
                        if (str5.equals("where")) {
                            throw new OQLException("query syntax error: identifier should follow class name");
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            if (!stringTokenizer.nextToken().equals("where")) {
                                throw new OQLException("query syntax error: 'where' clause expected after 'from' clause");
                            }
                            String str6 = "";
                            while (true) {
                                str4 = str6;
                                if (!stringTokenizer.hasMoreTokens()) {
                                    break;
                                } else {
                                    str6 = str4 + " " + stringTokenizer.nextToken();
                                }
                            }
                            if (str4.equals("")) {
                                throw new OQLException("query syntax error: 'where' clause cannot have empty expression");
                            }
                        }
                    } else {
                        throw new OQLException("query syntax error: identifier should follow class name");
                    }
                } else {
                    throw new OQLException("query syntax error: class name must follow 'from'");
                }
            }
            executeQuery(new OQLQuery(str2, z2, str3, str5, str4), objectVisitor);
            return;
        }
        throw new OQLException("query syntax error: no 'select' clause");
    }

    private void executeQuery(OQLQuery oQLQuery, ObjectVisitor objectVisitor) throws OQLException {
        JavaClass javaClass = null;
        if (oQLQuery.className != null) {
            javaClass = this.snapshot.findClass(oQLQuery.className);
            if (javaClass == null) {
                throw new OQLException(oQLQuery.className + " is not found!");
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("function __select__(");
        if (oQLQuery.identifier != null) {
            stringBuffer.append(oQLQuery.identifier);
        }
        stringBuffer.append(") { return ");
        stringBuffer.append(oQLQuery.selectExpr.replace('\n', ' '));
        stringBuffer.append("; }");
        String stringBuffer2 = stringBuffer.toString();
        debugPrint(stringBuffer2);
        String str = null;
        if (oQLQuery.whereExpr != null) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("function __where__(");
            stringBuffer3.append(oQLQuery.identifier);
            stringBuffer3.append(") { return ");
            stringBuffer3.append(oQLQuery.whereExpr.replace('\n', ' '));
            stringBuffer3.append("; }");
            str = stringBuffer3.toString();
        }
        debugPrint(str);
        try {
            this.evalMethod.invoke(this.engine, stringBuffer2);
            if (str != null) {
                this.evalMethod.invoke(this.engine, str);
            }
            if (oQLQuery.className != null) {
                Enumeration instances = javaClass.getInstances(oQLQuery.isInstanceOf);
                while (instances.hasMoreElements()) {
                    Object[] objArr = {wrapJavaObject((JavaHeapObject) instances.nextElement())};
                    boolean z = str == null;
                    if (!z) {
                        Object call = call("__where__", objArr);
                        if (call instanceof Boolean) {
                            z = ((Boolean) call).booleanValue();
                        } else if (call instanceof Number) {
                            z = ((Number) call).intValue() != 0;
                        } else {
                            z = call != null;
                        }
                    }
                    if (z && objectVisitor.visit(call("__select__", objArr))) {
                        return;
                    }
                }
            } else {
                objectVisitor.visit(call("__select__", new Object[0]));
            }
        } catch (Exception e) {
            throw new OQLException(e);
        }
    }

    public Object evalScript(String str) throws Exception {
        return this.evalMethod.invoke(this.engine, str);
    }

    public Object wrapJavaObject(JavaHeapObject javaHeapObject) throws Exception {
        return call("wrapJavaObject", new Object[]{javaHeapObject});
    }

    public Object toHtml(Object obj) throws Exception {
        return call("toHtml", new Object[]{obj});
    }

    public Object call(String str, Object[] objArr) throws Exception {
        return this.invokeMethod.invoke(this.engine, str, objArr);
    }

    private static void debugPrint(String str) {
        if (debug) {
            System.out.println(str);
        }
    }

    private void init(Snapshot snapshot) throws RuntimeException {
        this.snapshot = snapshot;
        try {
            Class<?> cls = Class.forName("javax.script.ScriptEngineManager");
            this.engine = cls.getMethod("getEngineByName", String.class).invoke(cls.newInstance(), "js");
            InputStream initStream = getInitStream();
            Class<?> cls2 = Class.forName("javax.script.ScriptEngine");
            this.evalMethod = cls2.getMethod("eval", Reader.class);
            this.evalMethod.invoke(this.engine, new InputStreamReader(initStream));
            Class<?> cls3 = Class.forName("javax.script.Invocable");
            this.evalMethod = cls2.getMethod("eval", String.class);
            this.invokeMethod = cls3.getMethod("invokeFunction", String.class, Object[].class);
            cls2.getMethod("put", String.class, Object.class).invoke(this.engine, "heap", call("wrapHeapSnapshot", new Object[]{snapshot}));
        } catch (Exception e) {
            if (debug) {
                e.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    private InputStream getInitStream() {
        return getClass().getResourceAsStream("/com/sun/tools/hat/resources/hat.js");
    }
}
