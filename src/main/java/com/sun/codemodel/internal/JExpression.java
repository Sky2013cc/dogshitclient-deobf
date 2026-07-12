package com.sun.codemodel.internal;

/* loaded from: target.jar:com/sun/codemodel/internal/JExpression.class */
public interface JExpression extends JGenerable {
    JExpression minus();

    JExpression not();

    JExpression complement();

    JExpression incr();

    JExpression decr();

    JExpression plus(JExpression jExpression);

    JExpression minus(JExpression jExpression);

    JExpression mul(JExpression jExpression);

    JExpression div(JExpression jExpression);

    JExpression mod(JExpression jExpression);

    JExpression shl(JExpression jExpression);

    JExpression shr(JExpression jExpression);

    JExpression shrz(JExpression jExpression);

    JExpression band(JExpression jExpression);

    JExpression bor(JExpression jExpression);

    JExpression cand(JExpression jExpression);

    JExpression cor(JExpression jExpression);

    JExpression xor(JExpression jExpression);

    JExpression lt(JExpression jExpression);

    JExpression lte(JExpression jExpression);

    JExpression gt(JExpression jExpression);

    JExpression gte(JExpression jExpression);

    JExpression eq(JExpression jExpression);

    JExpression ne(JExpression jExpression);

    JExpression _instanceof(JType jType);

    JInvocation invoke(JMethod jMethod);

    JInvocation invoke(String str);

    JFieldRef ref(JVar jVar);

    JFieldRef ref(String str);

    JArrayCompRef component(JExpression jExpression);
}
