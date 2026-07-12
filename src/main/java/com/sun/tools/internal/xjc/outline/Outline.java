package com.sun.tools.internal.xjc.outline;

import com.sun.codemodel.internal.JClass;
import com.sun.codemodel.internal.JClassContainer;
import com.sun.codemodel.internal.JCodeModel;
import com.sun.codemodel.internal.JPackage;
import com.sun.codemodel.internal.JType;
import com.sun.tools.internal.xjc.ErrorReceiver;
import com.sun.tools.internal.xjc.model.CClassInfo;
import com.sun.tools.internal.xjc.model.CClassInfoParent;
import com.sun.tools.internal.xjc.model.CElementInfo;
import com.sun.tools.internal.xjc.model.CEnumLeafInfo;
import com.sun.tools.internal.xjc.model.CPropertyInfo;
import com.sun.tools.internal.xjc.model.CTypeRef;
import com.sun.tools.internal.xjc.model.Model;
import com.sun.tools.internal.xjc.util.CodeModelClassFactory;
import java.util.Collection;

/* loaded from: target.jar:com/sun/tools/internal/xjc/outline/Outline.class */
public interface Outline {
    Model getModel();

    JCodeModel getCodeModel();

    FieldOutline getField(CPropertyInfo cPropertyInfo);

    PackageOutline getPackageContext(JPackage jPackage);

    Collection<? extends ClassOutline> getClasses();

    ClassOutline getClazz(CClassInfo cClassInfo);

    ElementOutline getElement(CElementInfo cElementInfo);

    EnumOutline getEnum(CEnumLeafInfo cEnumLeafInfo);

    Collection<EnumOutline> getEnums();

    Iterable<? extends PackageOutline> getAllPackageContexts();

    CodeModelClassFactory getClassFactory();

    ErrorReceiver getErrorReceiver();

    JClassContainer getContainer(CClassInfoParent cClassInfoParent, Aspect aspect);

    JType resolve(CTypeRef cTypeRef, Aspect aspect);

    JClass addRuntime(Class cls);
}
