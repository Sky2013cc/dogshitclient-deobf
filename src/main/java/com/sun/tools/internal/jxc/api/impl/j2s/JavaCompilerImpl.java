package com.sun.tools.internal.jxc.api.impl.j2s;

import com.sun.tools.internal.jxc.ap.InlineAnnotationReaderImpl;
import com.sun.tools.internal.jxc.model.nav.ApNavigator;
import com.sun.tools.internal.xjc.api.J2SJAXBModel;
import com.sun.tools.internal.xjc.api.JavaCompiler;
import com.sun.tools.internal.xjc.api.Reference;
import com.sun.xml.internal.bind.v2.model.core.ErrorHandler;
import com.sun.xml.internal.bind.v2.model.core.Ref;
import com.sun.xml.internal.bind.v2.model.core.TypeInfoSet;
import com.sun.xml.internal.bind.v2.model.impl.ModelBuilder;
import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/jxc/api/impl/j2s/JavaCompilerImpl.class */
public class JavaCompilerImpl implements JavaCompiler {
    @Override // com.sun.tools.internal.xjc.api.JavaCompiler
    public J2SJAXBModel bind(Collection<Reference> rootClasses, Map<QName, Reference> additionalElementDecls, String defaultNamespaceRemap, ProcessingEnvironment env) {
        ModelBuilder<TypeMirror, TypeElement, VariableElement, ExecutableElement> builder = new ModelBuilder<>(InlineAnnotationReaderImpl.theInstance, new ApNavigator(env), Collections.emptyMap(), defaultNamespaceRemap);
        builder.setErrorHandler(new ErrorHandlerImpl(env.getMessager()));
        for (Reference ref : rootClasses) {
            TypeMirror t = ref.type;
            XmlJavaTypeAdapter xjta = ref.annotations.getAnnotation(XmlJavaTypeAdapter.class);
            XmlList xl = ref.annotations.getAnnotation(XmlList.class);
            builder.getTypeInfo(new Ref(builder, t, xjta, xl));
        }
        TypeInfoSet<TypeMirror, TypeElement, VariableElement, ExecutableElement> r = builder.link();
        if (r == null) {
            return null;
        }
        if (additionalElementDecls == null) {
            additionalElementDecls = Collections.emptyMap();
        } else {
            for (Map.Entry<QName, ? extends Reference> e : additionalElementDecls.entrySet()) {
                if (e.getKey() == null) {
                    throw new IllegalArgumentException("nulls in additionalElementDecls");
                }
            }
        }
        return new JAXBModelImpl(r, builder.reader, rootClasses, new HashMap(additionalElementDecls));
    }

    /* loaded from: target.jar:com/sun/tools/internal/jxc/api/impl/j2s/JavaCompilerImpl$ErrorHandlerImpl.class */
    private static final class ErrorHandlerImpl implements ErrorHandler {
        private final Messager messager;

        public ErrorHandlerImpl(Messager messager) {
            this.messager = messager;
        }

        public void error(IllegalAnnotationException e) {
            String error = e.toString();
            this.messager.printMessage(Diagnostic.Kind.ERROR, error);
            System.err.println(error);
        }
    }
}
