package com.sun.tools.internal.xjc.outline;

import com.sun.codemodel.internal.JEnumConstant;
import com.sun.tools.internal.xjc.model.CEnumConstant;

/* loaded from: target.jar:com/sun/tools/internal/xjc/outline/EnumConstantOutline.class */
public abstract class EnumConstantOutline {
    public final CEnumConstant target;
    public final JEnumConstant constRef;

    /* JADX INFO: Access modifiers changed from: protected */
    public EnumConstantOutline(CEnumConstant target, JEnumConstant constRef) {
        this.target = target;
        this.constRef = constRef;
    }
}
