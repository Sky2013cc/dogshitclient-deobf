package com.sun.codemodel.internal;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: target.jar:com/sun/codemodel/internal/JFormatter.class */
public final class JFormatter {
    private HashMap<String, ReferenceList> collectedReferences;
    private HashSet<JClass> importedClasses;
    private Mode mode;
    private int indentLevel;
    private final String indentSpace;
    private final PrintWriter pw;
    private char lastChar;
    private boolean atBeginningOfLine;
    private JPackage javaLang;
    static final char CLOSE_TYPE_ARGS = 65535;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/codemodel/internal/JFormatter$Mode.class */
    public enum Mode {
        COLLECTING,
        PRINTING
    }

    static {
        $assertionsDisabled = !JFormatter.class.desiredAssertionStatus();
    }

    public JFormatter(PrintWriter s, String space) {
        this.mode = Mode.PRINTING;
        this.lastChar = (char) 0;
        this.atBeginningOfLine = true;
        this.pw = s;
        this.indentSpace = space;
        this.collectedReferences = new HashMap<>();
        this.importedClasses = new HashSet<>();
    }

    public JFormatter(PrintWriter s) {
        this(s, "    ");
    }

    public JFormatter(Writer w) {
        this(new PrintWriter(w));
    }

    public void close() {
        this.pw.close();
    }

    public boolean isPrinting() {
        return this.mode == Mode.PRINTING;
    }

    public JFormatter o() {
        this.indentLevel--;
        return this;
    }

    public JFormatter i() {
        this.indentLevel++;
        return this;
    }

    private boolean needSpace(char c1, char c2) {
        if ((c1 == ']' && c2 == '{') || c1 == ';') {
            return true;
        }
        if (c1 == 65535) {
            if (c2 == '(') {
                return false;
            }
            return true;
        }
        if ((c1 == ')' && c2 == '{') || c1 == ',' || c1 == '=' || c2 == '=') {
            return true;
        }
        if (Character.isDigit(c1)) {
            if (c2 == '(' || c2 == ')' || c2 == ';' || c2 == ',') {
                return false;
            }
            return true;
        }
        if (Character.isJavaIdentifierPart(c1)) {
            switch (c2) {
                case '+':
                case '>':
                case '@':
                case '{':
                case '}':
                    return true;
                default:
                    return Character.isJavaIdentifierStart(c2);
            }
        }
        if (!Character.isJavaIdentifierStart(c2)) {
            return Character.isDigit(c2) && c1 != '(';
        }
        switch (c1) {
            case ')':
            case '+':
            case ']':
            case '}':
                return true;
            default:
                return false;
        }
    }

    private void spaceIfNeeded(char c) {
        if (this.atBeginningOfLine) {
            for (int i = 0; i < this.indentLevel; i++) {
                this.pw.print(this.indentSpace);
            }
            this.atBeginningOfLine = false;
            return;
        }
        if (this.lastChar != 0 && needSpace(this.lastChar, c)) {
            this.pw.print(' ');
        }
    }

    public JFormatter p(char c) {
        if (this.mode == Mode.PRINTING) {
            if (c == 65535) {
                this.pw.print('>');
            } else {
                spaceIfNeeded(c);
                this.pw.print(c);
            }
            this.lastChar = c;
        }
        return this;
    }

    public JFormatter p(String s) {
        if (this.mode == Mode.PRINTING) {
            spaceIfNeeded(s.charAt(0));
            this.pw.print(s);
            this.lastChar = s.charAt(s.length() - 1);
        }
        return this;
    }

    public JFormatter t(JType type) {
        if (type.isReference()) {
            return t((JClass) type);
        }
        return g(type);
    }

    public JFormatter t(JClass type) {
        switch (this.mode) {
            case PRINTING:
                if (this.importedClasses.contains(type)) {
                    p(type.name());
                    break;
                } else if (type.outer() != null) {
                    t(type.outer()).p('.').p(type.name());
                    break;
                } else {
                    p(type.fullName());
                    break;
                }
            case COLLECTING:
                String shortName = type.name();
                if (this.collectedReferences.containsKey(shortName)) {
                    this.collectedReferences.get(shortName).add(type);
                    break;
                } else {
                    ReferenceList tl = new ReferenceList();
                    tl.add(type);
                    this.collectedReferences.put(shortName, tl);
                    break;
                }
        }
        return this;
    }

    public JFormatter id(String id) {
        switch (this.mode) {
            case PRINTING:
                p(id);
                break;
            case COLLECTING:
                if (this.collectedReferences.containsKey(id)) {
                    if (!this.collectedReferences.get(id).getClasses().isEmpty()) {
                        for (JClass type : this.collectedReferences.get(id).getClasses()) {
                            if (type.outer() != null) {
                                this.collectedReferences.get(id).setId(false);
                                return this;
                            }
                        }
                    }
                    this.collectedReferences.get(id).setId(true);
                    break;
                } else {
                    ReferenceList tl = new ReferenceList();
                    tl.setId(true);
                    this.collectedReferences.put(id, tl);
                    break;
                }
        }
        return this;
    }

    public JFormatter nl() {
        if (this.mode == Mode.PRINTING) {
            this.pw.println();
            this.lastChar = (char) 0;
            this.atBeginningOfLine = true;
        }
        return this;
    }

    public JFormatter g(JGenerable g) {
        g.generate(this);
        return this;
    }

    public JFormatter g(Collection<? extends JGenerable> list) {
        boolean first = true;
        if (!list.isEmpty()) {
            for (JGenerable item : list) {
                if (!first) {
                    p(',');
                }
                g(item);
                first = false;
            }
        }
        return this;
    }

    public JFormatter d(JDeclaration d) {
        d.declare(this);
        return this;
    }

    public JFormatter s(JStatement s) {
        s.state(this);
        return this;
    }

    public JFormatter b(JVar v) {
        v.bind(this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void write(JDefinedClass c) {
        this.mode = Mode.COLLECTING;
        d(c);
        this.javaLang = c.owner()._package("java.lang");
        for (ReferenceList tl : this.collectedReferences.values()) {
            if (!tl.collisions(c) && !tl.isId()) {
                if (!$assertionsDisabled && tl.getClasses().size() != 1) {
                    throw new AssertionError();
                }
                this.importedClasses.add(tl.getClasses().get(0));
            }
        }
        this.importedClasses.add(c);
        this.mode = Mode.PRINTING;
        if (!$assertionsDisabled && !c.parentContainer().isPackage()) {
            throw new AssertionError("this method is only for a pacakge-level class");
        }
        JPackage pkg = (JPackage) c.parentContainer();
        if (!pkg.isUnnamed()) {
            nl().d(pkg);
            nl();
        }
        JClass[] imports = (JClass[]) this.importedClasses.toArray(new JClass[this.importedClasses.size()]);
        Arrays.sort(imports);
        for (JClass clazz : imports) {
            if (!supressImport(clazz, c)) {
                if (clazz instanceof JNarrowedClass) {
                    clazz = clazz.erasure();
                }
                p("import").p(clazz.fullName()).p(';').nl();
            }
        }
        nl();
        d(c);
    }

    private boolean supressImport(JClass clazz, JClass c) {
        if (clazz instanceof JNarrowedClass) {
            clazz = clazz.erasure();
        }
        if (clazz instanceof JAnonymousClass) {
            clazz = clazz._extends();
        }
        if (clazz._package().isUnnamed()) {
            return true;
        }
        String packageName = clazz._package().name();
        if (packageName.equals("java.lang")) {
            return true;
        }
        if (clazz._package() == c._package() && clazz.outer() == null) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/codemodel/internal/JFormatter$ReferenceList.class */
    public final class ReferenceList {
        private final ArrayList<JClass> classes = new ArrayList<>();
        private boolean id;

        ReferenceList() {
        }

        public boolean collisions(JDefinedClass enclosingClass) {
            if (this.classes.size() > 1) {
                return true;
            }
            if (this.id && this.classes.size() != 0) {
                return true;
            }
            Iterator<JClass> it = this.classes.iterator();
            while (it.hasNext()) {
                JClass c = it.next();
                if (c instanceof JAnonymousClass) {
                    c = c._extends();
                }
                if (c._package() == JFormatter.this.javaLang) {
                    Iterator<JDefinedClass> itr = enclosingClass._package().classes();
                    while (itr.hasNext()) {
                        JDefinedClass n = itr.next();
                        if (n.name().equals(c.name())) {
                            return true;
                        }
                    }
                }
                if (c.outer() != null) {
                    return true;
                }
            }
            return false;
        }

        public void add(JClass clazz) {
            if (!this.classes.contains(clazz)) {
                this.classes.add(clazz);
            }
        }

        public List<JClass> getClasses() {
            return this.classes;
        }

        public void setId(boolean value) {
            this.id = value;
        }

        public boolean isId() {
            return this.id && this.classes.size() == 0;
        }
    }
}
