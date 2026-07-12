package com.sun.tools.javac.util;

import com.sun.tools.javac.api.DiagnosticFormatter;
import java.util.Locale;
import java.util.Set;
import javax.tools.Diagnostic;

/* loaded from: target.jar:com/sun/tools/javac/util/ForwardingDiagnosticFormatter.class */
public class ForwardingDiagnosticFormatter<D extends Diagnostic<?>, F extends DiagnosticFormatter<D>> implements DiagnosticFormatter<D> {
    protected F formatter;
    protected ForwardingConfiguration configuration;

    public ForwardingDiagnosticFormatter(F f) {
        this.formatter = f;
        this.configuration = new ForwardingConfiguration(f.getConfiguration());
    }

    public F getDelegatedFormatter() {
        return this.formatter;
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public DiagnosticFormatter.Configuration getConfiguration() {
        return this.configuration;
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public boolean displaySource(D d) {
        return this.formatter.displaySource(d);
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public String format(D d, Locale locale) {
        return this.formatter.format(d, locale);
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public String formatKind(D d, Locale locale) {
        return this.formatter.formatKind(d, locale);
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public String formatMessage(D d, Locale locale) {
        return this.formatter.formatMessage(d, locale);
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public String formatPosition(D d, DiagnosticFormatter.PositionKind positionKind, Locale locale) {
        return this.formatter.formatPosition(d, positionKind, locale);
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public String formatSource(D d, boolean z, Locale locale) {
        return this.formatter.formatSource(d, z, locale);
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/ForwardingDiagnosticFormatter$ForwardingConfiguration.class */
    public static class ForwardingConfiguration implements DiagnosticFormatter.Configuration {
        protected DiagnosticFormatter.Configuration configuration;

        public ForwardingConfiguration(DiagnosticFormatter.Configuration configuration) {
            this.configuration = configuration;
        }

        public DiagnosticFormatter.Configuration getDelegatedConfiguration() {
            return this.configuration;
        }

        @Override // com.sun.tools.javac.api.DiagnosticFormatter.Configuration
        public int getMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit multilineLimit) {
            return this.configuration.getMultilineLimit(multilineLimit);
        }

        @Override // com.sun.tools.javac.api.DiagnosticFormatter.Configuration
        public Set<DiagnosticFormatter.Configuration.DiagnosticPart> getVisible() {
            return this.configuration.getVisible();
        }

        @Override // com.sun.tools.javac.api.DiagnosticFormatter.Configuration
        public void setMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit multilineLimit, int i) {
            this.configuration.setMultilineLimit(multilineLimit, i);
        }

        @Override // com.sun.tools.javac.api.DiagnosticFormatter.Configuration
        public void setVisible(Set<DiagnosticFormatter.Configuration.DiagnosticPart> set) {
            this.configuration.setVisible(set);
        }
    }
}
