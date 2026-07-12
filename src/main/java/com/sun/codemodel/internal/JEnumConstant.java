package com.sun.codemodel.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/codemodel/internal/JEnumConstant.class */
public final class JEnumConstant extends JExpressionImpl implements JDeclaration, JAnnotatable, JDocCommentable {
    private final String name;
    private final JDefinedClass type;
    private JDocComment jdoc = null;
    private List<JAnnotationUse> annotations = null;
    private List<JExpression> args = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JEnumConstant(JDefinedClass type, String name) {
        this.name = name;
        this.type = type;
    }

    public JEnumConstant arg(JExpression arg) {
        if (arg == null) {
            throw new IllegalArgumentException();
        }
        if (this.args == null) {
            this.args = new ArrayList();
        }
        this.args.add(arg);
        return this;
    }

    public String getName() {
        return this.type.fullName().concat(Constants.NAME_SEPARATOR).concat(this.name);
    }

    @Override // com.sun.codemodel.internal.JDocCommentable
    public JDocComment javadoc() {
        if (this.jdoc == null) {
            this.jdoc = new JDocComment(this.type.owner());
        }
        return this.jdoc;
    }

    @Override // com.sun.codemodel.internal.JAnnotatable
    public JAnnotationUse annotate(JClass clazz) {
        if (this.annotations == null) {
            this.annotations = new ArrayList();
        }
        JAnnotationUse a = new JAnnotationUse(clazz);
        this.annotations.add(a);
        return a;
    }

    @Override // com.sun.codemodel.internal.JAnnotatable
    public JAnnotationUse annotate(Class<? extends Annotation> clazz) {
        return annotate(this.type.owner().ref(clazz));
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

    @Override // com.sun.codemodel.internal.JDeclaration
    public void declare(JFormatter f) {
        if (this.jdoc != null) {
            f.nl().g((JGenerable) this.jdoc);
        }
        if (this.annotations != null) {
            for (int i = 0; i < this.annotations.size(); i++) {
                f.g(this.annotations.get(i)).nl();
            }
        }
        f.id(this.name);
        if (this.args != null) {
            f.p('(').g(this.args).p(')');
        }
    }

    @Override // com.sun.codemodel.internal.JGenerable
    public void generate(JFormatter f) {
        f.t((JClass) this.type).p('.').p(this.name);
    }
}
