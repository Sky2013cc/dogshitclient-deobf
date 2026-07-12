package com.sun.tools.javah;

import com.sun.tools.javah.TypeSignature;
import com.sun.tools.javah.Util;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/javah/JNI.class */
public class JNI extends Gen {
    /* JADX INFO: Access modifiers changed from: package-private */
    public JNI(Util util) {
        super(util);
    }

    @Override // com.sun.tools.javah.Gen
    public String getIncludes() {
        return "#include <jni.h>";
    }

    @Override // com.sun.tools.javah.Gen
    public void write(OutputStream outputStream, TypeElement typeElement) throws Util.Exit {
        try {
            String mangle = this.mangler.mangle(typeElement.getQualifiedName(), 1);
            PrintWriter wrapWriter = wrapWriter(outputStream);
            wrapWriter.println(guardBegin(mangle));
            wrapWriter.println(cppGuardBegin());
            for (VariableElement variableElement : getAllFields(typeElement)) {
                if (variableElement.getModifiers().contains(Modifier.STATIC)) {
                    String defineForStatic = defineForStatic(typeElement, variableElement);
                    if (defineForStatic != null) {
                        wrapWriter.println(defineForStatic);
                    }
                }
            }
            List<ExecutableElement> methodsIn = ElementFilter.methodsIn(typeElement.getEnclosedElements());
            for (ExecutableElement executableElement : methodsIn) {
                if (executableElement.getModifiers().contains(Modifier.NATIVE)) {
                    TypeMirror erasure = this.types.erasure(executableElement.getReturnType());
                    String signature = signature(executableElement);
                    TypeSignature typeSignature = new TypeSignature(this.elems);
                    CharSequence simpleName = executableElement.getSimpleName();
                    boolean z = false;
                    for (ExecutableElement executableElement2 : methodsIn) {
                        if (executableElement2 != executableElement && simpleName.equals(executableElement2.getSimpleName()) && executableElement2.getModifiers().contains(Modifier.NATIVE)) {
                            z = true;
                        }
                    }
                    wrapWriter.println("/*");
                    wrapWriter.println(" * Class:     " + mangle);
                    wrapWriter.println(" * Method:    " + this.mangler.mangle(simpleName, 2));
                    wrapWriter.println(" * Signature: " + typeSignature.getTypeSignature(signature, erasure));
                    wrapWriter.println(" */");
                    wrapWriter.println("JNIEXPORT " + jniType(erasure) + " JNICALL " + this.mangler.mangleMethod(executableElement, typeElement, z ? 8 : 7));
                    wrapWriter.print("  (JNIEnv *, ");
                    List parameters = executableElement.getParameters();
                    ArrayList<TypeMirror> arrayList = new ArrayList();
                    Iterator it = parameters.iterator();
                    while (it.hasNext()) {
                        arrayList.add(this.types.erasure(((VariableElement) it.next()).asType()));
                    }
                    if (executableElement.getModifiers().contains(Modifier.STATIC)) {
                        wrapWriter.print("jclass");
                    } else {
                        wrapWriter.print("jobject");
                    }
                    for (TypeMirror typeMirror : arrayList) {
                        wrapWriter.print(", ");
                        wrapWriter.print(jniType(typeMirror));
                    }
                    wrapWriter.println(");" + this.lineSep);
                }
            }
            wrapWriter.println(cppGuardEnd());
            wrapWriter.println(guardEnd(mangle));
        } catch (TypeSignature.SignatureException e) {
            this.util.error("jni.sigerror", e.getMessage());
        }
    }

    protected final String jniType(TypeMirror typeMirror) throws Util.Exit {
        TypeElement typeElement = this.elems.getTypeElement("java.lang.Throwable");
        TypeElement typeElement2 = this.elems.getTypeElement("java.lang.Class");
        TypeElement typeElement3 = this.elems.getTypeElement("java.lang.String");
        Element asElement = this.types.asElement(typeMirror);
        switch (AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[typeMirror.getKind().ordinal()]) {
            case 1:
                return "jboolean";
            case 2:
                return "jbyte";
            case 3:
                return "jchar";
            case 4:
                return "jshort";
            case 5:
                return "jint";
            case 6:
                return "jlong";
            case 7:
                return "jfloat";
            case 8:
                return "jdouble";
            case 9:
                TypeMirror componentType = ((ArrayType) typeMirror).getComponentType();
                switch (AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[componentType.getKind().ordinal()]) {
                    case 1:
                        return "jbooleanArray";
                    case 2:
                        return "jbyteArray";
                    case 3:
                        return "jcharArray";
                    case 4:
                        return "jshortArray";
                    case 5:
                        return "jintArray";
                    case 6:
                        return "jlongArray";
                    case 7:
                        return "jfloatArray";
                    case 8:
                        return "jdoubleArray";
                    case 9:
                    case 10:
                        return "jobjectArray";
                    default:
                        throw new Error(componentType.toString());
                }
            case 10:
                if (asElement.equals(typeElement3)) {
                    return "jstring";
                }
                if (this.types.isAssignable(typeMirror, typeElement.asType())) {
                    return "jthrowable";
                }
                if (this.types.isAssignable(typeMirror, typeElement2.asType())) {
                    return "jclass";
                }
                return "jobject";
            case 11:
                return Constants.IDL_VOID;
            default:
                this.util.bug("jni.unknown.type");
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sun.tools.javah.JNI$1, reason: invalid class name */
    /* loaded from: target.jar:com/sun/tools/javah/JNI$1.class */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$type$TypeKind = new int[TypeKind.values().length];

        static {
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.CHAR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.SHORT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.INT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.LONG.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.DOUBLE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.ARRAY.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.DECLARED.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.VOID.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
        }
    }
}
