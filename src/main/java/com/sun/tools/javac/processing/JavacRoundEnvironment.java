package com.sun.tools.javac.processing;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementScanner8;

/* loaded from: target.jar:com/sun/tools/javac/processing/JavacRoundEnvironment.class */
public class JavacRoundEnvironment implements RoundEnvironment {
    private final boolean processingOver;
    private final boolean errorRaised;
    private final ProcessingEnvironment processingEnv;
    private final Set<? extends Element> rootElements;
    private static final String NOT_AN_ANNOTATION_TYPE = "The argument does not represent an annotation type: ";

    /* JADX INFO: Access modifiers changed from: package-private */
    public JavacRoundEnvironment(boolean z, boolean z2, Set<? extends Element> set, ProcessingEnvironment processingEnvironment) {
        this.processingOver = z;
        this.errorRaised = z2;
        this.rootElements = set;
        this.processingEnv = processingEnvironment;
    }

    public String toString() {
        return String.format("[errorRaised=%b, rootElements=%s, processingOver=%b]", Boolean.valueOf(this.errorRaised), this.rootElements, Boolean.valueOf(this.processingOver));
    }

    public boolean processingOver() {
        return this.processingOver;
    }

    public boolean errorRaised() {
        return this.errorRaised;
    }

    public Set<? extends Element> getRootElements() {
        return this.rootElements;
    }

    public Set<? extends Element> getElementsAnnotatedWith(TypeElement typeElement) {
        Set<? extends Element> emptySet = Collections.emptySet();
        if (typeElement.getKind() != ElementKind.ANNOTATION_TYPE) {
            throw new IllegalArgumentException(NOT_AN_ANNOTATION_TYPE + typeElement);
        }
        AnnotationSetScanner annotationSetScanner = new AnnotationSetScanner(emptySet);
        Iterator<? extends Element> it = this.rootElements.iterator();
        while (it.hasNext()) {
            emptySet = (Set) annotationSetScanner.scan(it.next(), typeElement);
        }
        return emptySet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/javac/processing/JavacRoundEnvironment$AnnotationSetScanner.class */
    public class AnnotationSetScanner extends ElementScanner8<Set<Element>, TypeElement> {
        Set<Element> annotatedElements;

        AnnotationSetScanner(Set<Element> set) {
            super(set);
            this.annotatedElements = new LinkedHashSet();
        }

        public Set<Element> visitType(TypeElement typeElement, TypeElement typeElement2) {
            scan(typeElement.getTypeParameters(), typeElement2);
            return (Set) super.visitType(typeElement, typeElement2);
        }

        public Set<Element> visitExecutable(ExecutableElement executableElement, TypeElement typeElement) {
            scan(executableElement.getTypeParameters(), typeElement);
            return (Set) super.visitExecutable(executableElement, typeElement);
        }

        public Set<Element> scan(Element element, TypeElement typeElement) {
            Iterator it = JavacRoundEnvironment.this.processingEnv.getElementUtils().getAllAnnotationMirrors(element).iterator();
            while (it.hasNext()) {
                if (typeElement.equals(((AnnotationMirror) it.next()).getAnnotationType().asElement())) {
                    this.annotatedElements.add(element);
                }
            }
            element.accept(this, typeElement);
            return this.annotatedElements;
        }
    }

    public Set<? extends Element> getElementsAnnotatedWith(Class<? extends Annotation> cls) {
        if (!cls.isAnnotation()) {
            throw new IllegalArgumentException(NOT_AN_ANNOTATION_TYPE + cls);
        }
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            return Collections.emptySet();
        }
        TypeElement typeElement = this.processingEnv.getElementUtils().getTypeElement(canonicalName);
        if (typeElement == null) {
            return Collections.emptySet();
        }
        return getElementsAnnotatedWith(typeElement);
    }
}
