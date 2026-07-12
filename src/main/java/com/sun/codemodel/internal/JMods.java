package com.sun.codemodel.internal;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: target.jar:com/sun/codemodel/internal/JMods.class */
public class JMods implements JGenerable {
    private static int VAR = 8;
    private static int FIELD = 799;
    private static int METHOD = 255;
    private static int CLASS = 63;
    private static int INTERFACE = 1;
    private int mods;

    private JMods(int mods) {
        this.mods = mods;
    }

    public int getValue() {
        return this.mods;
    }

    private static void check(int mods, int legal, String what) {
        if ((mods & (legal ^ (-1))) != 0) {
            throw new IllegalArgumentException("Illegal modifiers for " + what + ": " + new JMods(mods).toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JMods forVar(int mods) {
        check(mods, VAR, "variable");
        return new JMods(mods);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JMods forField(int mods) {
        check(mods, FIELD, "field");
        return new JMods(mods);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JMods forMethod(int mods) {
        check(mods, METHOD, "method");
        return new JMods(mods);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JMods forClass(int mods) {
        check(mods, CLASS, "class");
        return new JMods(mods);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JMods forInterface(int mods) {
        check(mods, INTERFACE, "class");
        return new JMods(mods);
    }

    public boolean isAbstract() {
        return (this.mods & 32) != 0;
    }

    public boolean isNative() {
        return (this.mods & 64) != 0;
    }

    public boolean isSynchronized() {
        return (this.mods & 128) != 0;
    }

    public void setSynchronized(boolean newValue) {
        setFlag(128, newValue);
    }

    public void setPrivate() {
        setFlag(1, false);
        setFlag(2, false);
        setFlag(4, true);
    }

    public void setProtected() {
        setFlag(1, false);
        setFlag(2, true);
        setFlag(4, false);
    }

    public void setPublic() {
        setFlag(1, true);
        setFlag(2, false);
        setFlag(4, false);
    }

    public void setFinal(boolean newValue) {
        setFlag(8, newValue);
    }

    private void setFlag(int bit, boolean newValue) {
        this.mods = (this.mods & (bit ^ (-1))) | (newValue ? bit : 0);
    }

    @Override // com.sun.codemodel.internal.JGenerable
    public void generate(JFormatter f) {
        if ((this.mods & 1) != 0) {
            f.p(Constants.ATTR_PUBLIC);
        }
        if ((this.mods & 2) != 0) {
            f.p("protected");
        }
        if ((this.mods & 4) != 0) {
            f.p("private");
        }
        if ((this.mods & 8) != 0) {
            f.p(Constants.ATTR_FINAL);
        }
        if ((this.mods & 16) != 0) {
            f.p("static");
        }
        if ((this.mods & 32) != 0) {
            f.p(Constants.ATTR_ABSTRACT);
        }
        if ((this.mods & 64) != 0) {
            f.p("native");
        }
        if ((this.mods & 128) != 0) {
            f.p("synchronized");
        }
        if ((this.mods & 256) != 0) {
            f.p("transient");
        }
        if ((this.mods & 512) != 0) {
            f.p("volatile");
        }
    }

    public String toString() {
        StringWriter s = new StringWriter();
        JFormatter f = new JFormatter(new PrintWriter(s));
        generate(f);
        return s.toString();
    }
}
