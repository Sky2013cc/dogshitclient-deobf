package com.sun.tools.javac.processing;

import com.sun.tools.doclint.DocLint;
import com.sun.tools.javac.util.StringUtils;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.Parameterizable;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleElementVisitor7;
import javax.lang.model.util.SimpleElementVisitor8;
import org.slf4j.Marker;
import sun.tools.java.RuntimeConstants;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({Marker.ANY_MARKER})
/* loaded from: target.jar:com/sun/tools/javac/processing/PrintingProcessor.class */
public class PrintingProcessor extends AbstractProcessor {
    PrintWriter writer = new PrintWriter(System.out);

    public void setWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Iterator it = roundEnvironment.getRootElements().iterator();
        while (it.hasNext()) {
            print((Element) it.next());
        }
        return true;
    }

    void print(Element element) {
        ((PrintingElementVisitor) new PrintingElementVisitor(this.writer, this.processingEnv.getElementUtils()).visit(element)).flush();
    }

    /* loaded from: target.jar:com/sun/tools/javac/processing/PrintingProcessor$PrintingElementVisitor.class */
    public static class PrintingElementVisitor extends SimpleElementVisitor8<PrintingElementVisitor, Boolean> {
        int indentation = 0;
        final PrintWriter writer;
        final Elements elementUtils;
        private static final String[] spaces = {"", "  ", "    ", "      ", "        ", "          ", "            ", "              ", "                ", "                  ", "                    "};

        public PrintingElementVisitor(Writer writer, Elements elements) {
            this.writer = new PrintWriter(writer);
            this.elementUtils = elements;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public PrintingElementVisitor defaultAction(Element element, Boolean bool) {
            if (bool != null && bool.booleanValue()) {
                this.writer.println();
            }
            printDocComment(element);
            printModifiers(element);
            return this;
        }

        /* JADX WARN: Type inference failed for: r1v26, types: [com.sun.tools.javac.processing.PrintingProcessor$PrintingElementVisitor$1] */
        public PrintingElementVisitor visitExecutable(ExecutableElement executableElement, Boolean bool) {
            ElementKind kind = executableElement.getKind();
            if (kind != ElementKind.STATIC_INIT && kind != ElementKind.INSTANCE_INIT) {
                Element enclosingElement = executableElement.getEnclosingElement();
                if (kind == ElementKind.CONSTRUCTOR && enclosingElement != null && NestingKind.ANONYMOUS == new SimpleElementVisitor7<NestingKind, Void>() { // from class: com.sun.tools.javac.processing.PrintingProcessor.PrintingElementVisitor.1
                    public NestingKind visitType(TypeElement typeElement, Void r4) {
                        return typeElement.getNestingKind();
                    }
                }.visit(enclosingElement)) {
                    return this;
                }
                defaultAction((Element) executableElement, (Boolean) true);
                printFormalTypeParameters(executableElement, true);
                switch (AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[kind.ordinal()]) {
                    case 1:
                        this.writer.print(executableElement.getEnclosingElement().getSimpleName());
                        break;
                    case 2:
                        this.writer.print(executableElement.getReturnType().toString());
                        this.writer.print(" ");
                        this.writer.print(executableElement.getSimpleName().toString());
                        break;
                }
                this.writer.print(RuntimeConstants.SIG_METHOD);
                printParameters(executableElement);
                this.writer.print(RuntimeConstants.SIG_ENDMETHOD);
                AnnotationValue defaultValue = executableElement.getDefaultValue();
                if (defaultValue != null) {
                    this.writer.print(" default " + defaultValue);
                }
                printThrows(executableElement);
                this.writer.println(RuntimeConstants.SIG_ENDCLASS);
            }
            return this;
        }

        public PrintingElementVisitor visitType(TypeElement typeElement, Boolean bool) {
            ElementKind kind = typeElement.getKind();
            NestingKind nestingKind = typeElement.getNestingKind();
            if (NestingKind.ANONYMOUS == nestingKind) {
                this.writer.print("new ");
                List interfaces = typeElement.getInterfaces();
                if (!interfaces.isEmpty()) {
                    this.writer.print(interfaces.get(0));
                } else {
                    this.writer.print(typeElement.getSuperclass());
                }
                this.writer.print(RuntimeConstants.SIG_METHOD);
                if (interfaces.isEmpty()) {
                    List constructorsIn = ElementFilter.constructorsIn(typeElement.getEnclosedElements());
                    if (!constructorsIn.isEmpty()) {
                        printParameters((ExecutableElement) constructorsIn.get(0));
                    }
                }
                this.writer.print(RuntimeConstants.SIG_ENDMETHOD);
            } else {
                if (nestingKind == NestingKind.TOP_LEVEL) {
                    PackageElement packageOf = this.elementUtils.getPackageOf(typeElement);
                    if (!packageOf.isUnnamed()) {
                        this.writer.print("package " + packageOf.getQualifiedName() + ";\n");
                    }
                }
                defaultAction((Element) typeElement, (Boolean) true);
                switch (AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[kind.ordinal()]) {
                    case 3:
                        this.writer.print("@interface");
                        break;
                    default:
                        this.writer.print(StringUtils.toLowerCase(kind.toString()));
                        break;
                }
                this.writer.print(" ");
                this.writer.print(typeElement.getSimpleName());
                printFormalTypeParameters(typeElement, false);
                if (kind == ElementKind.CLASS) {
                    DeclaredType superclass = typeElement.getSuperclass();
                    if (superclass.getKind() != TypeKind.NONE && superclass.asElement().getSuperclass().getKind() != TypeKind.NONE) {
                        this.writer.print(" extends " + superclass);
                    }
                }
                printInterfaces(typeElement);
            }
            this.writer.println(" {");
            this.indentation++;
            if (kind == ElementKind.ENUM) {
                ArrayList<Element> arrayList = new ArrayList(typeElement.getEnclosedElements());
                ArrayList arrayList2 = new ArrayList();
                for (Element element : arrayList) {
                    if (element.getKind() == ElementKind.ENUM_CONSTANT) {
                        arrayList2.add(element);
                    }
                }
                if (!arrayList2.isEmpty()) {
                    int i = 0;
                    while (i < arrayList2.size() - 1) {
                        visit((Element) arrayList2.get(i), true);
                        this.writer.print(DocLint.TAGS_SEPARATOR);
                        i++;
                    }
                    visit((Element) arrayList2.get(i), true);
                    this.writer.println(";\n");
                    arrayList.removeAll(arrayList2);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    visit((Element) it.next());
                }
            } else {
                Iterator it2 = typeElement.getEnclosedElements().iterator();
                while (it2.hasNext()) {
                    visit((Element) it2.next());
                }
            }
            this.indentation--;
            indent();
            this.writer.println("}");
            return this;
        }

        public PrintingElementVisitor visitVariable(VariableElement variableElement, Boolean bool) {
            ElementKind kind = variableElement.getKind();
            defaultAction((Element) variableElement, bool);
            if (kind == ElementKind.ENUM_CONSTANT) {
                this.writer.print(variableElement.getSimpleName());
            } else {
                this.writer.print(variableElement.asType().toString() + " " + variableElement.getSimpleName());
                Object constantValue = variableElement.getConstantValue();
                if (constantValue != null) {
                    this.writer.print(" = ");
                    this.writer.print(this.elementUtils.getConstantExpression(constantValue));
                }
                this.writer.println(RuntimeConstants.SIG_ENDCLASS);
            }
            return this;
        }

        public PrintingElementVisitor visitTypeParameter(TypeParameterElement typeParameterElement, Boolean bool) {
            this.writer.print(typeParameterElement.getSimpleName());
            return this;
        }

        public PrintingElementVisitor visitPackage(PackageElement packageElement, Boolean bool) {
            defaultAction((Element) packageElement, (Boolean) false);
            if (!packageElement.isUnnamed()) {
                this.writer.println("package " + packageElement.getQualifiedName() + RuntimeConstants.SIG_ENDCLASS);
            } else {
                this.writer.println("// Unnamed package");
            }
            return this;
        }

        public void flush() {
            this.writer.flush();
        }

        private void printDocComment(Element element) {
            String docComment = this.elementUtils.getDocComment(element);
            if (docComment != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(docComment, "\n\r");
                indent();
                this.writer.println("/**");
                while (stringTokenizer.hasMoreTokens()) {
                    indent();
                    this.writer.print(" *");
                    this.writer.println(stringTokenizer.nextToken());
                }
                indent();
                this.writer.println(" */");
            }
        }

        private void printModifiers(Element element) {
            ElementKind kind = element.getKind();
            if (kind == ElementKind.PARAMETER) {
                printAnnotationsInline(element);
            } else {
                printAnnotations(element);
                indent();
            }
            if (kind == ElementKind.ENUM_CONSTANT) {
                return;
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            linkedHashSet.addAll(element.getModifiers());
            switch (AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[kind.ordinal()]) {
                case 2:
                case 6:
                    Element enclosingElement = element.getEnclosingElement();
                    if (enclosingElement != null && enclosingElement.getKind().isInterface()) {
                        linkedHashSet.remove(Modifier.PUBLIC);
                        linkedHashSet.remove(Modifier.ABSTRACT);
                        linkedHashSet.remove(Modifier.STATIC);
                        linkedHashSet.remove(Modifier.FINAL);
                        break;
                    }
                    break;
                case 3:
                case 4:
                    linkedHashSet.remove(Modifier.ABSTRACT);
                    break;
                case 5:
                    linkedHashSet.remove(Modifier.FINAL);
                    linkedHashSet.remove(Modifier.ABSTRACT);
                    break;
            }
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                this.writer.print(((Modifier) it.next()).toString() + " ");
            }
        }

        private void printFormalTypeParameters(Parameterizable parameterizable, boolean z) {
            List<TypeParameterElement> typeParameters = parameterizable.getTypeParameters();
            if (typeParameters.size() > 0) {
                this.writer.print("<");
                boolean z2 = true;
                for (TypeParameterElement typeParameterElement : typeParameters) {
                    if (!z2) {
                        this.writer.print(", ");
                    }
                    printAnnotationsInline(typeParameterElement);
                    this.writer.print(typeParameterElement.toString());
                    z2 = false;
                }
                this.writer.print(">");
                if (z) {
                    this.writer.print(" ");
                }
            }
        }

        private void printAnnotationsInline(Element element) {
            Iterator it = element.getAnnotationMirrors().iterator();
            while (it.hasNext()) {
                this.writer.print((AnnotationMirror) it.next());
                this.writer.print(" ");
            }
        }

        private void printAnnotations(Element element) {
            for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
                indent();
                this.writer.println(annotationMirror);
            }
        }

        private void printParameters(ExecutableElement executableElement) {
            List<VariableElement> parameters = executableElement.getParameters();
            int size = parameters.size();
            switch (size) {
                case 0:
                    return;
                case 1:
                    for (VariableElement variableElement : parameters) {
                        printModifiers(variableElement);
                        if (executableElement.isVarArgs()) {
                            TypeMirror asType = variableElement.asType();
                            if (asType.getKind() != TypeKind.ARRAY) {
                                throw new AssertionError("Var-args parameter is not an array type: " + asType);
                            }
                            this.writer.print(((ArrayType) ArrayType.class.cast(asType)).getComponentType());
                            this.writer.print("...");
                        } else {
                            this.writer.print(variableElement.asType());
                        }
                        this.writer.print(" " + variableElement.getSimpleName());
                    }
                    return;
                default:
                    int i = 1;
                    for (VariableElement variableElement2 : parameters) {
                        if (i == 2) {
                            this.indentation++;
                        }
                        if (i > 1) {
                            indent();
                        }
                        printModifiers(variableElement2);
                        if (i == size && executableElement.isVarArgs()) {
                            TypeMirror asType2 = variableElement2.asType();
                            if (asType2.getKind() != TypeKind.ARRAY) {
                                throw new AssertionError("Var-args parameter is not an array type: " + asType2);
                            }
                            this.writer.print(((ArrayType) ArrayType.class.cast(asType2)).getComponentType());
                            this.writer.print("...");
                        } else {
                            this.writer.print(variableElement2.asType());
                        }
                        this.writer.print(" " + variableElement2.getSimpleName());
                        if (i < size) {
                            this.writer.println(DocLint.TAGS_SEPARATOR);
                        }
                        i++;
                    }
                    if (parameters.size() >= 2) {
                        this.indentation--;
                        return;
                    }
                    return;
            }
        }

        private void printInterfaces(TypeElement typeElement) {
            ElementKind kind = typeElement.getKind();
            if (kind != ElementKind.ANNOTATION_TYPE) {
                List<TypeMirror> interfaces = typeElement.getInterfaces();
                if (interfaces.size() > 0) {
                    this.writer.print(kind.isClass() ? " implements" : " extends");
                    boolean z = true;
                    for (TypeMirror typeMirror : interfaces) {
                        if (!z) {
                            this.writer.print(DocLint.TAGS_SEPARATOR);
                        }
                        this.writer.print(" ");
                        this.writer.print(typeMirror.toString());
                        z = false;
                    }
                }
            }
        }

        private void printThrows(ExecutableElement executableElement) {
            List<TypeMirror> thrownTypes = executableElement.getThrownTypes();
            int size = thrownTypes.size();
            if (size != 0) {
                this.writer.print(" throws");
                int i = 1;
                for (TypeMirror typeMirror : thrownTypes) {
                    if (i == 1) {
                        this.writer.print(" ");
                    }
                    if (i == 2) {
                        this.indentation++;
                    }
                    if (i >= 2) {
                        indent();
                    }
                    this.writer.print(typeMirror);
                    if (i != size) {
                        this.writer.println(", ");
                    }
                    i++;
                }
                if (size >= 2) {
                    this.indentation--;
                }
            }
        }

        private void indent() {
            int i = this.indentation;
            if (i < 0) {
                return;
            }
            int length = spaces.length - 1;
            while (i > length) {
                this.writer.print(spaces[length]);
                i -= length;
            }
            this.writer.print(spaces[i]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sun.tools.javac.processing.PrintingProcessor$1, reason: invalid class name */
    /* loaded from: target.jar:com/sun/tools/javac/processing/PrintingProcessor$1.class */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$element$ElementKind = new int[ElementKind.values().length];

        static {
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.CONSTRUCTOR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.METHOD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.ANNOTATION_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.INTERFACE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.ENUM.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.FIELD.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }
}
