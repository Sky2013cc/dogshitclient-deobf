package com.sun.tools.internal.jxc.ap;

import com.sun.xml.internal.bind.v2.model.annotation.AbstractInlineAnnotationReaderImpl;
import com.sun.xml.internal.bind.v2.model.annotation.Locatable;
import com.sun.xml.internal.bind.v2.model.annotation.LocatableAnnotation;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;

/* loaded from: target.jar:com/sun/tools/internal/jxc/ap/InlineAnnotationReaderImpl.class */
public final class InlineAnnotationReaderImpl extends AbstractInlineAnnotationReaderImpl<TypeMirror, TypeElement, VariableElement, ExecutableElement> {
    public static final InlineAnnotationReaderImpl theInstance;
    static final /* synthetic */ boolean $assertionsDisabled;

    public /* bridge */ /* synthetic */ boolean hasMethodAnnotation(Class cls, Object obj) {
        return hasMethodAnnotation((Class<? extends Annotation>) cls, (ExecutableElement) obj);
    }

    public /* bridge */ /* synthetic */ boolean hasClassAnnotation(Object obj, Class cls) {
        return hasClassAnnotation((TypeElement) obj, (Class<? extends Annotation>) cls);
    }

    public /* bridge */ /* synthetic */ boolean hasFieldAnnotation(Class cls, Object obj) {
        return hasFieldAnnotation((Class<? extends Annotation>) cls, (VariableElement) obj);
    }

    static {
        $assertionsDisabled = !InlineAnnotationReaderImpl.class.desiredAssertionStatus();
        theInstance = new InlineAnnotationReaderImpl();
    }

    private InlineAnnotationReaderImpl() {
    }

    public <A extends Annotation> A getClassAnnotation(Class<A> cls, TypeElement typeElement, Locatable locatable) {
        return (A) LocatableAnnotation.create(typeElement.getAnnotation(cls), locatable);
    }

    public <A extends Annotation> A getFieldAnnotation(Class<A> cls, VariableElement variableElement, Locatable locatable) {
        return (A) LocatableAnnotation.create(variableElement.getAnnotation(cls), locatable);
    }

    public boolean hasFieldAnnotation(Class<? extends Annotation> annotationType, VariableElement f) {
        return f.getAnnotation(annotationType) != null;
    }

    public boolean hasClassAnnotation(TypeElement clazz, Class<? extends Annotation> annotationType) {
        return clazz.getAnnotation(annotationType) != null;
    }

    public Annotation[] getAllFieldAnnotations(VariableElement field, Locatable srcPos) {
        return getAllAnnotations(field, srcPos);
    }

    public <A extends Annotation> A getMethodAnnotation(Class<A> cls, ExecutableElement executableElement, Locatable locatable) {
        return (A) LocatableAnnotation.create(executableElement.getAnnotation(cls), locatable);
    }

    public boolean hasMethodAnnotation(Class<? extends Annotation> a, ExecutableElement method) {
        return method.getAnnotation(a) != null;
    }

    public Annotation[] getAllMethodAnnotations(ExecutableElement method, Locatable srcPos) {
        return getAllAnnotations(method, srcPos);
    }

    private Annotation[] getAllAnnotations(Element decl, Locatable srcPos) {
        List<Annotation> r = new ArrayList<>();
        for (AnnotationMirror m : decl.getAnnotationMirrors()) {
            try {
                String fullName = m.getAnnotationType().asElement().getQualifiedName().toString();
                Annotation annotation = decl.getAnnotation(SecureLoader.getClassClassLoader(getClass()).loadClass(fullName).asSubclass(Annotation.class));
                if (annotation != null) {
                    r.add(LocatableAnnotation.create(annotation, srcPos));
                }
            } catch (ClassNotFoundException e) {
            }
        }
        return (Annotation[]) r.toArray(new Annotation[r.size()]);
    }

    public <A extends Annotation> A getMethodParameterAnnotation(Class<A> cls, ExecutableElement executableElement, int i, Locatable locatable) {
        return (A) LocatableAnnotation.create(((VariableElement[]) executableElement.getParameters().toArray(new VariableElement[executableElement.getParameters().size()]))[i].getAnnotation(cls), locatable);
    }

    public <A extends Annotation> A getPackageAnnotation(Class<A> cls, TypeElement typeElement, Locatable locatable) {
        return (A) LocatableAnnotation.create(typeElement.getEnclosingElement().getAnnotation(cls), locatable);
    }

    /* renamed from: getClassValue, reason: merged with bridge method [inline-methods] */
    public TypeMirror m185getClassValue(Annotation a, String name) {
        try {
            a.annotationType().getMethod(name, new Class[0]).invoke(a, new Object[0]);
            if ($assertionsDisabled) {
                throw new IllegalStateException("should throw a MirroredTypeException");
            }
            throw new AssertionError();
        } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
        } catch (NoSuchMethodException e2) {
            throw new NoSuchMethodError(e2.getMessage());
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof MirroredTypeException) {
                MirroredTypeException me2 = e3.getCause();
                return me2.getTypeMirror();
            }
            throw new RuntimeException(e3);
        }
    }

    /* renamed from: getClassArrayValue, reason: merged with bridge method [inline-methods] */
    public TypeMirror[] m184getClassArrayValue(Annotation a, String name) {
        try {
            a.annotationType().getMethod(name, new Class[0]).invoke(a, new Object[0]);
            if ($assertionsDisabled) {
                throw new IllegalStateException("should throw a MirroredTypesException");
            }
            throw new AssertionError();
        } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
        } catch (NoSuchMethodException e2) {
            throw new NoSuchMethodError(e2.getMessage());
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof MirroredTypesException) {
                MirroredTypesException me2 = e3.getCause();
                Collection<? extends TypeMirror> r = me2.getTypeMirrors();
                return (TypeMirror[]) r.toArray(new TypeMirror[r.size()]);
            }
            if (e3.getCause() instanceof MirroredTypeException) {
                MirroredTypeException me3 = e3.getCause();
                TypeMirror tr = me3.getTypeMirror();
                TypeMirror[] trArr = {tr};
                return trArr;
            }
            throw new RuntimeException(e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String fullName(ExecutableElement m) {
        return m.getEnclosingElement().getQualifiedName().toString() + '#' + m.getSimpleName();
    }
}
