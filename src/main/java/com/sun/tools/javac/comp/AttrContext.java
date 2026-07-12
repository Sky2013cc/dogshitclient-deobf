package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.comp.Attr;
import com.sun.tools.javac.comp.Resolve;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;

/* loaded from: target.jar:com/sun/tools/javac/comp/AttrContext.class */
public class AttrContext {
    Lint lint;
    JCTree preferredTreeForDiagnostics;
    Scope scope = null;
    int staticLevel = 0;
    boolean isSelfCall = false;
    boolean selectSuper = false;
    boolean isSerializable = false;
    Resolve.MethodResolutionPhase pendingResolutionPhase = null;
    Symbol enclVar = null;
    Attr.ResultInfo returnResult = null;
    Type defaultSuperCallSite = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AttrContext dup(Scope scope) {
        AttrContext attrContext = new AttrContext();
        attrContext.scope = scope;
        attrContext.staticLevel = this.staticLevel;
        attrContext.isSelfCall = this.isSelfCall;
        attrContext.selectSuper = this.selectSuper;
        attrContext.pendingResolutionPhase = this.pendingResolutionPhase;
        attrContext.lint = this.lint;
        attrContext.enclVar = this.enclVar;
        attrContext.returnResult = this.returnResult;
        attrContext.defaultSuperCallSite = this.defaultSuperCallSite;
        attrContext.isSerializable = this.isSerializable;
        attrContext.preferredTreeForDiagnostics = this.preferredTreeForDiagnostics;
        return attrContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AttrContext dup() {
        return dup(this.scope);
    }

    public Iterable<Symbol> getLocalElements() {
        if (this.scope == null) {
            return List.nil();
        }
        return this.scope.getElements();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean lastResolveVarargs() {
        return this.pendingResolutionPhase != null && this.pendingResolutionPhase.isVarargsRequired();
    }

    public String toString() {
        return "AttrContext[" + this.scope.toString() + "]";
    }
}
