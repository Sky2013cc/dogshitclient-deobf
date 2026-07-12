package com.sun.tools.javac.util;

import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.util.JCDiagnostic;
import java.util.EnumSet;

/* loaded from: target.jar:com/sun/tools/javac/util/Warner.class */
public class Warner {
    private JCDiagnostic.DiagnosticPosition pos;
    protected boolean warned;
    private EnumSet<Lint.LintCategory> nonSilentLintSet;
    private EnumSet<Lint.LintCategory> silentLintSet;

    public JCDiagnostic.DiagnosticPosition pos() {
        return this.pos;
    }

    public void warn(Lint.LintCategory lintCategory) {
        this.nonSilentLintSet.add(lintCategory);
    }

    public void silentWarn(Lint.LintCategory lintCategory) {
        this.silentLintSet.add(lintCategory);
    }

    public Warner(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        this.pos = null;
        this.warned = false;
        this.nonSilentLintSet = EnumSet.noneOf(Lint.LintCategory.class);
        this.silentLintSet = EnumSet.noneOf(Lint.LintCategory.class);
        this.pos = diagnosticPosition;
    }

    public boolean hasSilentLint(Lint.LintCategory lintCategory) {
        return this.silentLintSet.contains(lintCategory);
    }

    public boolean hasNonSilentLint(Lint.LintCategory lintCategory) {
        return this.nonSilentLintSet.contains(lintCategory);
    }

    public boolean hasLint(Lint.LintCategory lintCategory) {
        return hasSilentLint(lintCategory) || hasNonSilentLint(lintCategory);
    }

    public void clear() {
        this.nonSilentLintSet.clear();
        this.silentLintSet.clear();
        this.warned = false;
    }

    public Warner() {
        this(null);
    }
}
