package com.sun.codemodel.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: target.jar:com/sun/codemodel/internal/JClass.class */
public abstract class JClass extends JType {
    private final JCodeModel _owner;
    protected static final JTypeVar[] EMPTY_ARRAY = new JTypeVar[0];
    private JClass arrayClass;

    @Override // com.sun.codemodel.internal.JType
    public abstract String name();

    public abstract JPackage _package();

    public abstract JClass _extends();

    public abstract Iterator<JClass> _implements();

    public abstract boolean isInterface();

    public abstract boolean isAbstract();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract JClass substituteParams(JTypeVar[] jTypeVarArr, List<JClass> list);

    /* JADX INFO: Access modifiers changed from: protected */
    public JClass(JCodeModel _owner) {
        this._owner = _owner;
    }

    public JClass outer() {
        return null;
    }

    @Override // com.sun.codemodel.internal.JType
    public final JCodeModel owner() {
        return this._owner;
    }

    public JTypeVar[] typeParams() {
        return EMPTY_ARRAY;
    }

    public JPrimitiveType getPrimitiveType() {
        return null;
    }

    @Override // com.sun.codemodel.internal.JType
    public JClass boxify() {
        return this;
    }

    @Override // com.sun.codemodel.internal.JType
    public JType unboxify() {
        JPrimitiveType pt = getPrimitiveType();
        return pt == null ? this : pt;
    }

    @Override // com.sun.codemodel.internal.JType
    public JClass erasure() {
        return this;
    }

    public final boolean isAssignableFrom(JClass derived) {
        if ((derived instanceof JNullType) || this == derived || this == _package().owner().ref(Object.class)) {
            return true;
        }
        JClass b = derived._extends();
        if (b != null && isAssignableFrom(b)) {
            return true;
        }
        if (isInterface()) {
            Iterator<JClass> itfs = derived._implements();
            while (itfs.hasNext()) {
                if (isAssignableFrom(itfs.next())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final JClass getBaseClass(JClass baseType) {
        JClass bc;
        if (erasure().equals(baseType)) {
            return this;
        }
        JClass b = _extends();
        if (b != null && (bc = b.getBaseClass(baseType)) != null) {
            return bc;
        }
        Iterator<JClass> itfs = _implements();
        while (itfs.hasNext()) {
            JClass bc2 = itfs.next().getBaseClass(baseType);
            if (bc2 != null) {
                return bc2;
            }
        }
        return null;
    }

    public final JClass getBaseClass(Class<?> baseType) {
        return getBaseClass(owner().ref(baseType));
    }

    @Override // com.sun.codemodel.internal.JType
    public JClass array() {
        if (this.arrayClass == null) {
            this.arrayClass = new JArrayClass(owner(), this);
        }
        return this.arrayClass;
    }

    public JClass narrow(Class<?> clazz) {
        return narrow(owner().ref(clazz));
    }

    public JClass narrow(Class<?>... clazz) {
        JClass[] r = new JClass[clazz.length];
        for (int i = 0; i < clazz.length; i++) {
            r[i] = owner().ref(clazz[i]);
        }
        return narrow(r);
    }

    public JClass narrow(JClass clazz) {
        return new JNarrowedClass(this, clazz);
    }

    public JClass narrow(JType type) {
        return narrow(type.boxify());
    }

    public JClass narrow(JClass... clazz) {
        return new JNarrowedClass(this, (List<JClass>) Arrays.asList((Object[]) clazz.clone()));
    }

    public JClass narrow(List<? extends JClass> clazz) {
        return new JNarrowedClass(this, new ArrayList(clazz));
    }

    public List<JClass> getTypeParameters() {
        return Collections.emptyList();
    }

    public final boolean isParameterized() {
        return erasure() != this;
    }

    public final JClass wildcard() {
        return new JTypeWildcard(this);
    }

    @Override // com.sun.codemodel.internal.JType
    public String toString() {
        return getClass().getName() + '(' + name() + ')';
    }

    public final JExpression dotclass() {
        return JExpr.dotclass(this);
    }

    public final JInvocation staticInvoke(JMethod method) {
        return new JInvocation(this, method);
    }

    public final JInvocation staticInvoke(String method) {
        return new JInvocation(this, method);
    }

    public final JFieldRef staticRef(String field) {
        return new JFieldRef(this, field);
    }

    public final JFieldRef staticRef(JVar field) {
        return new JFieldRef(this, field);
    }

    @Override // com.sun.codemodel.internal.JGenerable
    public void generate(JFormatter f) {
        f.t(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void printLink(JFormatter f) {
        f.p("{@link ").g(this).p('}');
    }
}
