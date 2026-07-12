package com.sun.codemodel.internal;

/* loaded from: target.jar:com/sun/codemodel/internal/JAssignmentTarget.class */
public interface JAssignmentTarget extends JGenerable, JExpression {
    JExpression assign(JExpression jExpression);

    JExpression assignPlus(JExpression jExpression);
}
