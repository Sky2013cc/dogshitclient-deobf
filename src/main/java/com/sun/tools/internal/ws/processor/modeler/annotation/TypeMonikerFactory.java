package com.sun.tools.internal.ws.processor.modeler.annotation;

import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/annotation/TypeMonikerFactory.class */
public class TypeMonikerFactory {
    public static TypeMoniker getTypeMoniker(TypeMirror typeMirror) {
        if (typeMirror == null) {
            throw new NullPointerException();
        }
        if (typeMirror.getKind().isPrimitive()) {
            return new PrimitiveTypeMoniker((PrimitiveType) typeMirror);
        }
        if (typeMirror.getKind().equals(TypeKind.ARRAY)) {
            return new ArrayTypeMoniker((ArrayType) typeMirror);
        }
        if (typeMirror.getKind().equals(TypeKind.DECLARED)) {
            return new DeclaredTypeMoniker((DeclaredType) typeMirror);
        }
        return getTypeMoniker(typeMirror.toString());
    }

    public static TypeMoniker getTypeMoniker(String typeName) {
        return new StringMoniker(typeName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/annotation/TypeMonikerFactory$ArrayTypeMoniker.class */
    public static class ArrayTypeMoniker implements TypeMoniker {
        private TypeMoniker arrayType;

        public ArrayTypeMoniker(ArrayType type) {
            this.arrayType = TypeMonikerFactory.getTypeMoniker(type.getComponentType());
        }

        @Override // com.sun.tools.internal.ws.processor.modeler.annotation.TypeMoniker
        public TypeMirror create(ProcessingEnvironment apEnv) {
            return apEnv.getTypeUtils().getArrayType(this.arrayType.create(apEnv));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/annotation/TypeMonikerFactory$DeclaredTypeMoniker.class */
    public static class DeclaredTypeMoniker implements TypeMoniker {
        private Name typeDeclName;
        private Collection<TypeMoniker> typeArgs = new ArrayList();

        public DeclaredTypeMoniker(DeclaredType type) {
            this.typeDeclName = type.asElement().getQualifiedName();
            for (TypeMirror arg : type.getTypeArguments()) {
                this.typeArgs.add(TypeMonikerFactory.getTypeMoniker(arg));
            }
        }

        @Override // com.sun.tools.internal.ws.processor.modeler.annotation.TypeMoniker
        public TypeMirror create(ProcessingEnvironment apEnv) {
            TypeElement typeDecl = apEnv.getElementUtils().getTypeElement(this.typeDeclName);
            TypeMirror[] tmpArgs = new TypeMirror[this.typeArgs.size()];
            int idx = 0;
            for (TypeMoniker moniker : this.typeArgs) {
                int i = idx;
                idx++;
                tmpArgs[i] = moniker.create(apEnv);
            }
            return apEnv.getTypeUtils().getDeclaredType(typeDecl, tmpArgs);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/annotation/TypeMonikerFactory$PrimitiveTypeMoniker.class */
    public static class PrimitiveTypeMoniker implements TypeMoniker {
        private TypeKind kind;

        public PrimitiveTypeMoniker(PrimitiveType type) {
            this.kind = type.getKind();
        }

        @Override // com.sun.tools.internal.ws.processor.modeler.annotation.TypeMoniker
        public TypeMirror create(ProcessingEnvironment apEnv) {
            return apEnv.getTypeUtils().getPrimitiveType(this.kind);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/annotation/TypeMonikerFactory$StringMoniker.class */
    public static class StringMoniker implements TypeMoniker {
        private String typeName;

        public StringMoniker(String typeName) {
            this.typeName = typeName;
        }

        @Override // com.sun.tools.internal.ws.processor.modeler.annotation.TypeMoniker
        public TypeMirror create(ProcessingEnvironment apEnv) {
            return apEnv.getTypeUtils().getDeclaredType(apEnv.getElementUtils().getTypeElement(this.typeName), new TypeMirror[0]);
        }
    }
}
