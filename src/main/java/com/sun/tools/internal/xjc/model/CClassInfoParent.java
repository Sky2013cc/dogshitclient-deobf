package com.sun.tools.internal.xjc.model;

import com.sun.codemodel.internal.JPackage;

/* loaded from: target.jar:com/sun/tools/internal/xjc/model/CClassInfoParent.class */
public interface CClassInfoParent {

    /* loaded from: target.jar:com/sun/tools/internal/xjc/model/CClassInfoParent$Visitor.class */
    public interface Visitor<T> {
        T onBean(CClassInfo cClassInfo);

        T onPackage(JPackage jPackage);

        T onElement(CElementInfo cElementInfo);
    }

    String fullName();

    <T> T accept(Visitor<T> visitor);

    JPackage getOwnerPackage();

    /* loaded from: target.jar:com/sun/tools/internal/xjc/model/CClassInfoParent$Package.class */
    public static final class Package implements CClassInfoParent {
        public final JPackage pkg;

        public Package(JPackage pkg) {
            this.pkg = pkg;
        }

        @Override // com.sun.tools.internal.xjc.model.CClassInfoParent, com.sun.tools.internal.xjc.model.nav.NType
        public String fullName() {
            return this.pkg.name();
        }

        @Override // com.sun.tools.internal.xjc.model.CClassInfoParent
        public <T> T accept(Visitor<T> visitor) {
            return visitor.onPackage(this.pkg);
        }

        @Override // com.sun.tools.internal.xjc.model.CClassInfoParent
        public JPackage getOwnerPackage() {
            return this.pkg;
        }
    }
}
