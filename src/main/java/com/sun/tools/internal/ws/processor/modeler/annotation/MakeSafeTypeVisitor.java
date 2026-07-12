package com.sun.tools.internal.ws.processor.modeler.annotation;

import java.util.Collection;
import java.util.Map;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor6;
import javax.lang.model.util.Types;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/annotation/MakeSafeTypeVisitor.class */
public class MakeSafeTypeVisitor extends SimpleTypeVisitor6<TypeMirror, Types> {
    TypeElement collectionType;
    TypeElement mapType;

    public MakeSafeTypeVisitor(ProcessingEnvironment processingEnvironment) {
        this.collectionType = processingEnvironment.getElementUtils().getTypeElement(Collection.class.getName());
        this.mapType = processingEnvironment.getElementUtils().getTypeElement(Map.class.getName());
    }

    public TypeMirror visitDeclared(DeclaredType t, Types types) {
        if (TypeModeler.isSubElement(t.asElement(), this.collectionType) || TypeModeler.isSubElement(t.asElement(), this.mapType)) {
            Collection<? extends TypeMirror> args = t.getTypeArguments();
            TypeMirror[] safeArgs = new TypeMirror[args.size()];
            int i = 0;
            for (TypeMirror arg : args) {
                int i2 = i;
                i++;
                safeArgs[i2] = (TypeMirror) visit(arg, types);
            }
            return types.getDeclaredType(t.asElement(), safeArgs);
        }
        return types.erasure(t);
    }

    public TypeMirror visitNoType(NoType type, Types types) {
        return type;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeMirror defaultAction(TypeMirror e, Types types) {
        return types.erasure(e);
    }
}
