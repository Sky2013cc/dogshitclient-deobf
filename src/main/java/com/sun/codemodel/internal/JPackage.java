package com.sun.codemodel.internal;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/codemodel/internal/JPackage.class */
public final class JPackage implements JDeclaration, JGenerable, JClassContainer, JAnnotatable, Comparable<JPackage>, JDocCommentable {
    private String name;
    private final JCodeModel owner;
    private final Map<String, JDefinedClass> upperCaseClassMap;
    private final Map<String, JDefinedClass> classes = new TreeMap();
    private final Set<JResourceFile> resources = new HashSet();
    private List<JAnnotationUse> annotations = null;
    private JDocComment jdoc = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JPackage(String name, JCodeModel cw) {
        this.owner = cw;
        if (name.equals(Constants.NAME_SEPARATOR)) {
            throw new IllegalArgumentException("Package name . is not allowed");
        }
        if (JCodeModel.isCaseSensitiveFileSystem) {
            this.upperCaseClassMap = null;
        } else {
            this.upperCaseClassMap = new HashMap();
        }
        this.name = name;
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public JClassContainer parentContainer() {
        return parent();
    }

    public JPackage parent() {
        if (this.name.length() == 0) {
            return null;
        }
        int idx = this.name.lastIndexOf(46);
        return this.owner._package(this.name.substring(0, idx));
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public boolean isClass() {
        return false;
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public boolean isPackage() {
        return true;
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public JPackage getPackage() {
        return this;
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public JDefinedClass _class(int mods, String name) throws JClassAlreadyExistsException {
        return _class(mods, name, ClassType.CLASS);
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public JDefinedClass _class(int mods, String name, boolean isInterface) throws JClassAlreadyExistsException {
        return _class(mods, name, isInterface ? ClassType.INTERFACE : ClassType.CLASS);
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public JDefinedClass _class(int mods, String name, ClassType classTypeVal) throws JClassAlreadyExistsException {
        if (this.classes.containsKey(name)) {
            throw new JClassAlreadyExistsException(this.classes.get(name));
        }
        JDefinedClass c = new JDefinedClass(this, mods, name, classTypeVal);
        if (this.upperCaseClassMap != null) {
            JDefinedClass dc = this.upperCaseClassMap.get(name.toUpperCase());
            if (dc != null) {
                throw new JClassAlreadyExistsException(dc);
            }
            this.upperCaseClassMap.put(name.toUpperCase(), c);
        }
        this.classes.put(name, c);
        return c;
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public JDefinedClass _class(String name) throws JClassAlreadyExistsException {
        return _class(1, name);
    }

    public JDefinedClass _getClass(String name) {
        if (this.classes.containsKey(name)) {
            return this.classes.get(name);
        }
        return null;
    }

    @Override // java.lang.Comparable
    public int compareTo(JPackage that) {
        return this.name.compareTo(that.name);
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public JDefinedClass _interface(int mods, String name) throws JClassAlreadyExistsException {
        return _class(mods, name, ClassType.INTERFACE);
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public JDefinedClass _interface(String name) throws JClassAlreadyExistsException {
        return _interface(1, name);
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public JDefinedClass _annotationTypeDeclaration(String name) throws JClassAlreadyExistsException {
        return _class(1, name, ClassType.ANNOTATION_TYPE_DECL);
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public JDefinedClass _enum(String name) throws JClassAlreadyExistsException {
        return _class(1, name, ClassType.ENUM);
    }

    public JResourceFile addResourceFile(JResourceFile rsrc) {
        this.resources.add(rsrc);
        return rsrc;
    }

    public boolean hasResourceFile(String name) {
        for (JResourceFile r : this.resources) {
            if (r.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<JResourceFile> propertyFiles() {
        return this.resources.iterator();
    }

    @Override // com.sun.codemodel.internal.JDocCommentable
    public JDocComment javadoc() {
        if (this.jdoc == null) {
            this.jdoc = new JDocComment(owner());
        }
        return this.jdoc;
    }

    public void remove(JClass c) {
        if (c._package() != this) {
            throw new IllegalArgumentException("the specified class is not a member of this package, or it is a referenced class");
        }
        this.classes.remove(c.name());
        if (this.upperCaseClassMap != null) {
            this.upperCaseClassMap.remove(c.name().toUpperCase());
        }
    }

    public JClass ref(String name) throws ClassNotFoundException {
        if (name.indexOf(46) >= 0) {
            throw new IllegalArgumentException("JClass name contains '.': " + name);
        }
        String n = "";
        if (!isUnnamed()) {
            n = this.name + '.';
        }
        return this.owner.ref(Class.forName(n + name));
    }

    public JPackage subPackage(String pkg) {
        return isUnnamed() ? owner()._package(pkg) : owner()._package(this.name + '.' + pkg);
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public Iterator<JDefinedClass> classes() {
        return this.classes.values().iterator();
    }

    public boolean isDefined(String classLocalName) {
        Iterator<JDefinedClass> itr = classes();
        while (itr.hasNext()) {
            if (itr.next().name().equals(classLocalName)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isUnnamed() {
        return this.name.length() == 0;
    }

    public String name() {
        return this.name;
    }

    @Override // com.sun.codemodel.internal.JClassContainer
    public final JCodeModel owner() {
        return this.owner;
    }

    @Override // com.sun.codemodel.internal.JAnnotatable
    public JAnnotationUse annotate(JClass clazz) {
        if (isUnnamed()) {
            throw new IllegalArgumentException("the root package cannot be annotated");
        }
        if (this.annotations == null) {
            this.annotations = new ArrayList();
        }
        JAnnotationUse a = new JAnnotationUse(clazz);
        this.annotations.add(a);
        return a;
    }

    @Override // com.sun.codemodel.internal.JAnnotatable
    public JAnnotationUse annotate(Class<? extends Annotation> clazz) {
        return annotate(this.owner.ref(clazz));
    }

    @Override // com.sun.codemodel.internal.JAnnotatable
    public <W extends JAnnotationWriter> W annotate2(Class<W> cls) {
        return (W) TypedAnnotationWriter.create(cls, this);
    }

    @Override // com.sun.codemodel.internal.JAnnotatable
    public Collection<JAnnotationUse> annotations() {
        if (this.annotations == null) {
            this.annotations = new ArrayList();
        }
        return Collections.unmodifiableList(this.annotations);
    }

    File toPath(File dir) {
        return this.name == null ? dir : new File(dir, this.name.replace('.', File.separatorChar));
    }

    @Override // com.sun.codemodel.internal.JDeclaration
    public void declare(JFormatter f) {
        if (this.name.length() != 0) {
            f.p("package").p(this.name).p(';').nl();
        }
    }

    @Override // com.sun.codemodel.internal.JGenerable
    public void generate(JFormatter f) {
        f.p(this.name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void build(CodeWriter src, CodeWriter res) throws IOException {
        for (JDefinedClass c : this.classes.values()) {
            if (!c.isHidden()) {
                JFormatter f = createJavaSourceFileWriter(src, c.name());
                f.write(c);
                f.close();
            }
        }
        if (this.annotations != null || this.jdoc != null) {
            JFormatter f2 = createJavaSourceFileWriter(src, "package-info");
            if (this.jdoc != null) {
                f2.g((JGenerable) this.jdoc);
            }
            if (this.annotations != null) {
                for (JAnnotationUse a : this.annotations) {
                    f2.g(a).nl();
                }
            }
            f2.d(this);
            f2.close();
        }
        for (JResourceFile rsrc : this.resources) {
            CodeWriter cw = rsrc.isResource() ? res : src;
            OutputStream os = new BufferedOutputStream(cw.openBinary(this, rsrc.name()));
            rsrc.build(os);
            os.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int countArtifacts() {
        int r = 0;
        for (JDefinedClass c : this.classes.values()) {
            if (!c.isHidden()) {
                r++;
            }
        }
        if (this.annotations != null || this.jdoc != null) {
            r++;
        }
        return r + this.resources.size();
    }

    private JFormatter createJavaSourceFileWriter(CodeWriter src, String className) throws IOException {
        Writer bw = new BufferedWriter(src.openSource(this, className + Constants.SOURCE_FILE_EXTENSION));
        return new JFormatter(new PrintWriter(bw));
    }
}
